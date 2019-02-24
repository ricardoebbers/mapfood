package mapfood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    private final String API_BASE_URL_PATH = "/api/v1/orders";
    
    @Test
    public void createOrder() throws Exception {
    
    }
    
    @Test
    public void getRestaurantOrdersList() throws Exception {
//        final String idRestaurant = "1";
//        final String idRestaurantParamPath = "/"+idRestaurant;
//
//        OrderList response = restTemplate.getForObject(
//                API_BASE_URL_PATH+idRestaurantParamPath,
//                OrderList.class);
//
//        List<Order> orders = response.getOrders();
//        assertNotNull(orders);
    }
    
    @Test
    public void updateOrderStatus() throws Exception {
    
    }
    
    @Test
    public void findAndSetMotoboy() throws Exception {
    
    }
    
    //getOrdersForRestaurant
    //getDeliveryForecast
}

