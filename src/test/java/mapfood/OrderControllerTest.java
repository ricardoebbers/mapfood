package mapfood;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import mapfood.controller.OrderController;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.service.OrderService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void testCreate() {
        
        Order newOrder = createRadomOrder();
        
        HttpEntity<Order> entity = new HttpEntity<Order>(newOrder, headers);
        
        ResponseEntity<Order> response = restTemplate.exchange(
                createURLWithPort("/api/v1/orders?idRestaurant=1&idClient=1"),
                HttpMethod.POST, entity, Order.class);
        
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        
        assertTrue(actual.contains("/students/Student1/courses/"));
        
    }
    
    HttpHeaders headers = new HttpHeaders();
    
    private String createURLWithPort(String uri) {
        return "http://localhost:8080" + uri;
    }
    
    
    private Order createRadomOrder() {
    
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem item =  new OrderItem();

        item.setUnit_price(new Double(5.5));
        item.set_id(838);
        item.setItem_description("Refrigerantes Lata");
        item.setClassification("Bebidas");
        item.setQuantity(2);
    
        orderItemList.add(item);
        order.setOrderItems(orderItemList);
        
        return order;
    }
}
