package mapfood.service;

import mapfood.model.*;

import java.util.List;

public interface OrderService {

    Order createOrder(String idClient, String idRestaurant, List<OrderItem> orderItemList);

}
