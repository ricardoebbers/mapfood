package mapfood.service;


import mapfood.model.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantServiceSimulatorImpl {

    public boolean notifyRestaurant(Order order){
        sendNetwordkRequest(order);
        return true;
    }

    private void sendNetwordkRequest(Order order){

        String url = "localhost:8080/" + order.getId();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url);
        Retrofit retrofit = builder.build();
        RestaurantServiceSimulator client = retrofit.create(RestaurantServiceSimulator.class);

        Call<Order> call = client.updateOrderStatus(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                System.out.println("Success");
            }

            @Override
            public void onFailure(Call<Order> call, Throwable throwable) {
                System.out.println("Fail");
            }
        });
    }






}
