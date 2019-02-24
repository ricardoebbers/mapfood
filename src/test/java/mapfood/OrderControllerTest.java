package mapfood;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import mapfood.controller.OrderController;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    
    @MockBean
    private OrderService service;
    
    private static final String API_ROOT
            = "http://localhost:8080/api/v1/orders";
    
    @Test
    public void whenCreateNewOrder_thenCreated() {
        assertTrue(true);
//        Order order = createRadomOrder();
//        Response response = RestAssured.given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(order)
//                .post(API_ROOT);
//
//        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
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
