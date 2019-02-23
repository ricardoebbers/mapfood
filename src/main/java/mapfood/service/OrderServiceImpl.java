package mapfood.service;

import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private static final long PREPARATION_TIME = 10;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FindNearestService findNearestService;

    @Autowired
    private DirectionsService directionsService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Order createOrder(String idClient, String idRestaurant, List<OrderItem> orderItemList) {

        Optional<Client> client = clientRepository.findById(Integer.valueOf(idClient));
        Optional<Restaurant> restaurant = restaurantRepository.findById(Integer.valueOf(idRestaurant));
        int totalQuantity = 0;

        if (client.isPresent() && restaurant.isPresent()) {
            Client clientFinded = client.get();
            Restaurant restaurantFinded = restaurant.get();

            for (OrderItem orderItem : orderItemList) {
                totalQuantity += orderItem.getQuantity();
            }

            if (!orderItemList.isEmpty() && totalQuantity <= 5) {

                Order order = new Order();
                order.setClient(clientFinded);
                order.setOrderItems(orderItemList);
                order.setRestaurant(restaurantFinded);
                order.setOrderStatus(OrderEnum.NOVO);

                return orderRepository.save(order);
            }
        }
        return null;
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
}
