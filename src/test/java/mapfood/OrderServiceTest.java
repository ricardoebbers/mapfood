package mapfood;

import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import mapfood.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {
    
    @Autowired
    OrderService service;
    
    @Autowired
    private OrderRepository repository;

    private final String ID_CLIENT = "1";
    private final String ID_RESTAURANT = "1";
    
    @Test
    public void givenOrderItemAndClientAndMotoboyCreateOrderDeleteOrder() throws Exception {
        List<OrderItem> orderItems = new ArrayList<>();
        
        OrderItem orderItem = new OrderItem();
        orderItem.setUnit_price(5.5);
        orderItem.set_id(838);
        orderItem.setItem_description("Refrigerantes Lata TEST");
        orderItem.setClassification("Bebidas TEST");
        orderItem.setQuantity(2);
        
        orderItems.add(orderItem);

        Order result = service.createOrder(ID_CLIENT, ID_RESTAURANT,orderItems);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderEnum.NOVO));
        repository.deleteById(result.get_id());
        assertTrue(!repository.findById(result.get_id()).isPresent());
    }
    
    private Order createRadomOrder(){
    
        Order result = new Order();
    
        return result;
    }
    
}
