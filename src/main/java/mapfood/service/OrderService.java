package mapfood.service;

import mapfood.dto.OrderDto;
import mapfood.dto.OrderItemDto;
import mapfood.model.*;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order createOrder(OrderDto order);

    List<Route> getOrderDirections(String idOrder);

    List<Order> getOrderForRestaurant(String idRestaurant, String status);

    Long deliveryForecast(String idOrder);

    Order updateStatus(String orderId, OrderStatus status);

    Order findAndSetMotoboy(String orderId);

    List<Order> findAllByDateAndOrderStatus(LocalDate date);

    List<Order> findAll();

    OrderItem createOrderItemFromDto(OrderItemDto itemDto, Restaurant restaurant);

}
