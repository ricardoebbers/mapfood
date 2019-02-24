package mapfood;

import mapfood.model.*;
import mapfood.repository.ClientRepository;
import mapfood.repository.MotoboyRepository;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import mapfood.service.MotoboyService;
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
    
    @Autowired
    private MotoboyService motoBoyServive;

    private final String ID_CLIENT = "1";
    private final String ID_RESTAURANT = "1";
    
    @Test
    public void givenOrderItemAndClientAndMotoboyCreateOrderDeleteOrder() throws Exception {
        List<OrderItem> orderItems = generateOrderItemsList();
        
        // create order
        Order result = service.createOrder(ID_CLIENT, ID_RESTAURANT,orderItems);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderEnum.NOVO));
        
        // delete order
        repository.deleteById(result.get_id());
        assertTrue(!repository.findById(result.get_id()).isPresent());
    }
    
    @Test
    public void givenOrderIdUpdateStatus() throws Exception {
        List<OrderItem> orderItems = generateOrderItemsList();
        
        // create order
        Order result = service.createOrder(ID_CLIENT, ID_RESTAURANT,orderItems);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderEnum.NOVO));
        
        // update order status
        service.updateStatus(result.get_id(),"CANCELADO");
        Optional<Order> updatedOrder = repository.findById(result.get_id());
        
        assertTrue(updatedOrder.isPresent());
        assertEquals(updatedOrder.get().getOrderStatus().toString(),OrderEnum.CANCELADO.toString());
        
        // delete order
        repository.deleteById(updatedOrder.get().get_id());
        assertTrue(!repository.findById(updatedOrder.get().get_id()).isPresent());
    }
    
    @Test
    public void givenOrderIdFindAndSetMotoboy() throws Exception {
        List<OrderItem> orderItems = generateOrderItemsList();
        
        // create order
        Order result = service.createOrder(ID_CLIENT, ID_RESTAURANT,orderItems);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderEnum.NOVO));
        
        assertNull(result.getMotoboy());
        
        // update find and set motoboy
        result = service.findAndSetMotoboy(result.get_id());
        assertNotNull(result.getMotoboy());
        assertFalse(result.getMotoboy().isAvailable());
        
        // turn motoboy available
        Motoboy motoboy  = result.getMotoboy();
        motoboy.setAvailable(true);
        motoBoyServive.save(motoboy);
    
        motoboy = motoBoyServive.getById(motoboy.get_id()).get();
        assertTrue(motoboy.isAvailable());
    
        // delete order
        repository.deleteById(result.get_id());
        assertTrue(!repository.findById(result.get_id()).isPresent());
    }
    
    
    @Test
    public void givenOrderIdGetDirections() throws Exception {
        List<OrderItem> orderItems = generateOrderItemsList();
        
        // create order
        Order newOrder = service.createOrder(ID_CLIENT, ID_RESTAURANT,orderItems);
        assertNotNull(newOrder);
        assertTrue(newOrder.getOrderStatus().equals(OrderEnum.NOVO));
        
        assertNull(newOrder.getMotoboy());
        
        // update find and set motoboy
        newOrder = service.findAndSetMotoboy(newOrder.get_id());
        assertNotNull(newOrder.getMotoboy());
        assertFalse(newOrder.getMotoboy().isAvailable());
        
        // get OrderDirections
        List<Route> routes = new ArrayList<>();
        routes = service.getOrderDirections(newOrder.get_id());
        assertTrue(!routes.isEmpty());
        
        // turn motoboy available
        Motoboy motoboy  = newOrder.getMotoboy();
        motoboy.setAvailable(true);
        motoBoyServive.save(motoboy);
        
        motoboy = motoBoyServive.getById(motoboy.get_id()).get();
        assertTrue(motoboy.isAvailable());
        
        // delete order
        repository.deleteById(newOrder.get_id());
        assertTrue(!repository.findById(newOrder.get_id()).isPresent());
    }
    
    
    private Order createRadomOrder(){
    
        Order result = new Order();
    
        return result;
    }
    
    private List<OrderItem> generateOrderItemsList(){
        List<OrderItem> orderItems = new ArrayList<>();
    
        OrderItem orderItem = new OrderItem();
        orderItem.setUnit_price(5.5);
        orderItem.set_id(838);
        orderItem.setItem_description("Refrigerantes Lata TEST");
        orderItem.setClassification("Bebidas TEST");
        orderItem.setQuantity(2);
    
        orderItems.add(orderItem);
        
        return orderItems;
    }
}
