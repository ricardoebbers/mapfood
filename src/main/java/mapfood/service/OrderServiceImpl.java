package mapfood.service;

import com.google.maps.model.DirectionsResult;
import lombok.RequiredArgsConstructor;
import mapfood.dto.OrderDto;
import mapfood.dto.OrderItemDto;
import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final long PREPARATION_TIME = 10;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MotoboyService motoboyService;

    @Autowired
    private FindNearestService findNearestService;

    @Autowired
    private DirectionsService directionsService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReportService reportService;

    @Override
    public Order createOrder(OrderDto dto) {

        Order order = this.createOrderFromDto(dto);
        return this.orderRepository.save(order);

    }

    public Order createOrderFromDto(OrderDto dto) {

        Assert.notNull(dto.getClient(), "The client must be informed");
        Assert.notNull(dto.getRestaurant(), "The restaurant must be informed");

        Client client = this.clientRepository.findById(dto.getClient())
            .orElseThrow(IllegalArgumentException::new);

        Restaurant restaurant = this.restaurantRepository.findById(dto.getRestaurant())
            .orElseThrow(IllegalArgumentException::new);

        Motoboy motoboy = dto.getMotoboy() != null ?
            this.motoboyService.getById(dto.getMotoboy()).orElse(null) : null;

        Order order = new Order();

        BeanUtils.copyProperties(dto, order);

        order.setOrderStatus(OrderStatus.NEW);
        order.setClient(client);
        order.setMotoboy(motoboy);
        order.setRestaurant(restaurant);

        List<OrderItem> items = dto.getOrderItems().stream().map(
            i -> this.createOrderItemFromDto(i, restaurant)
        ).collect(Collectors.toList());

        order.setOrderItems(items);

        return order;

    }

    public OrderItem createOrderItemFromDto(OrderItemDto itemDto, Restaurant restaurant) {

        return restaurant.getProducts()
            .stream()
            .filter(product -> product.get_id().equals(itemDto.getProduct()))
            .map(product -> {

                OrderItem item = new OrderItem();

                item.setProduct(product);
                item.setQuantity(itemDto.getQuantity());

                return item;
            })
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public Order updateStatus(String orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId).get();

        if (status == OrderStatus.DELIVERED) {
            motoboyService.updateLocAndAvailability(order.getMotoboy(), order.getClient().getLoc());
        }

        order.setOrderStatus(status);
        orderRepository.save(order);

        return order;
    }

    @Override
    public Order findAndSetMotoboy(String orderId) {
        Order order = orderRepository.findById(orderId).get();
        Restaurant restaurant = order.getRestaurant();
        Motoboy motoboy = findNearestService.getNearestMotoboy(restaurant.get_id(), 5);
        order.setMotoboy(motoboy);
        motoboy.setAvailable(false);
        motoboyService.save(motoboy);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Route> getOrderDirections(String orderId) {
        List<Route> result = new ArrayList<>();
        Order order = orderRepository.findById(orderId).get();

        String motoboyLocation = order.getMotoboy().getLoc().coordinatesToString();
        String restaurantLocation = order.getRestaurant().getLoc().coordinatesToString();
        String clientLocation = order.getClient().getLoc().coordinatesToString();

        DirectionsResult requestToRestaurant =
                directionsService.getDirections(motoboyLocation, restaurantLocation);
        Route routeToRestaurant = directionsService.getRouteInstructions(requestToRestaurant,
                "Rota até o restaurante.");
        result.add(routeToRestaurant);

        DirectionsResult requestToClient = directionsService.getDirections(restaurantLocation,
                clientLocation);

        Route routeToClient = directionsService.getRouteInstructions(requestToClient,
                "Rota até o cliente.");

        reportService.setDistanceTimeForReport(routeToRestaurant.getDuration(), routeToRestaurant.getDistance(),
                routeToClient.getDuration(), routeToClient.getDistance(), order.getRestaurant());

        result.add(routeToClient);
        return result;
    }

    @Override
    public List<Order> getOrderForRestaurant(String idRestaurant, String status) {

        if (status == null || status.isEmpty()) {
            return orderRepository.findAllByRestaurant__id(Integer.valueOf(idRestaurant));
        }
        return orderRepository.findAllByRestaurant__idAndOrderStatus(Integer.valueOf(idRestaurant), status.toUpperCase());

    }

    /**
     * Receives the OrderId and returns the delivery time
     * @param idOrder
     * @return Delivery forecast
     */
    @Override
    public Long deliveryForecast(String idOrder){
        Optional<Order> order = orderRepository.findById(idOrder);

        if (order.isPresent()) {
            Order order1 = order.get();
            Motoboy motoboy = order1.getMotoboy();
            Restaurant restaurant = order1.getRestaurant();
            Client client = order1.getClient();

            Long expecting = calculateDeliveryForecast(motoboy, restaurant, client);

            order1.setDeliveryExpectation(expecting);

            orderRepository.save(order1);

            return expecting;
        }
        return null;
    }

    /**
     *
     * @param motoboy
     * @param restaurant
     * @param client
     * @return Delivery forecast
     */
    private long calculateDeliveryForecast(Motoboy motoboy, Restaurant restaurant, Client client) {
        //Receives the coordinates of the motoboy and the restaurant and transforms for minutes
        Long secondstimeMotoboyRestaurant = directionsService.getTimeDistance(motoboy.getLoc().getLongitude(),
                motoboy.getLoc().getLatitude(), restaurant.getLoc().getLongitude(), restaurant.getLoc().getLatitude()).getTimeSeconds();
        Long minutesTimeMotoboyRestaurant = secondstimeMotoboyRestaurant/60;

        //Receives the coordinates of the restaurant and the motoboy and transforms for minutes
        Long secondsTimeRestaurantClient = directionsService.getTimeDistance(restaurant.getLoc().getLongitude(),
                restaurant.getLoc().getLatitude(), client.getLoc().getLongitude(), client.getLoc().getLatitude()).getTimeSeconds();
        Long minutesTimeRestaurantClient = secondsTimeRestaurantClient/60;

        long expecting;

        //Calculates delivery time
        if(minutesTimeMotoboyRestaurant > PREPARATION_TIME){
            expecting = minutesTimeMotoboyRestaurant + minutesTimeRestaurantClient;
        } else {
            expecting = PREPARATION_TIME + minutesTimeRestaurantClient;
        }
        return expecting;
    }

    @Override
    public List<Order> findAllByDateAndOrderStatus (LocalDate date) {
        return orderRepository.findAllByDateAndOrderStatus(date, OrderStatus.RECEIVED.getValorStatus());
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }
}
