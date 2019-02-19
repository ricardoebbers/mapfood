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

}
