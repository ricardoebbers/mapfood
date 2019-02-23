package mapfood.service;

import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.model.Route;

import java.util.List;

public interface OrderService {

    Order createOrder(String idClient, String idRestaurant, List<OrderItem> orderItemList);

    List<Route> getOrderDirections(String idOrder);

    List<Order> getOrderForRestaurant(String idRestaurant, String status);

    Long deliveryForecast(String idOrder);

    Order updateStatus(String orderId, String status);

    Order findAndSetMotoboy(String orderId);

}
