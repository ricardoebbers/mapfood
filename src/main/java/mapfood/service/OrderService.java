package mapfood.service;

import com.google.maps.model.DirectionsResult;
import mapfood.model.*;

import java.util.List;

public interface OrderService {

    Order createOrder(String idClient, String idRestaurant, List<OrderItem> orderItemList);
    
    List<DirectionsResult> getOrderDirections(String idMotoBoy, String idOrder);

    List<Order> getOrderForRestaurant(String idRestaurant, String status);

    Long deliveryForecast(String idOrder);
}
