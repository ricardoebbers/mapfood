package mapfood.service;

import mapfood.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface RestaurantServiceSimulator {

    @PUT("order")
    Call<Order> updateOrderStatus(@Body Order order);

}
