package mapfood.service;

import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FindNearestService findNearestService;

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
            Client clientFound = client.get();
            Restaurant restaurantFinded = restaurant.get();

            for (OrderItem orderItem : orderItemList) {
                totalQuantity += orderItem.getQuantity();
            }

            if (!orderItemList.isEmpty() && totalQuantity <= 5) {

                Order order = new Order();
                order.setClient(clientFound);
                order.setOrderItems(orderItemList);
                order.setRestaurant(restaurantFinded);
                order.setOrderStatus(OrderEnum.NOVO);

                Order createdOrder = orderRepository.save(order);

                CompletableFuture.runAsync(() -> {
                    //SIMULATION: Should throw flag to queue to be consumed by restaurant service and thrown back
                    //to queue to be consumed by this service when accepted by the restaurant, then inform the user
                    //that the order was accepted by the restaurant
                    RestaurantServiceSimulatorImpl controller = new RestaurantServiceSimulatorImpl();
                    controller.notifyRestaurant(createdOrder);
                });

                return createdOrder;
            }
        }
        return null;
    }

    @Override
    public Order alterOrderStatus(String idOrder) {
        Optional<Order> orderFound = orderRepository.findById(idOrder);
        if(orderFound.isPresent()){
            Order order = orderFound.get();
            OrderEnum currentStatus = order.getOrderStatus();
            order.setOrderStatus(currentStatus.getNextStatus());
            return orderRepository.save(order);
        }
        return null;
    }

}
