package mapfood.service;

import mapfood.dto.OrderDto;
import mapfood.dto.OrderItemDto;
import mapfood.model.*;
import mapfood.repository.OrderRepository;
import mapfood.repository.RestaurantRepository;
import mapfood.service.MotoboyService;
import mapfood.service.OrderService;
import org.junit.Before;
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
    private MotoboyService motoboyService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private final Integer ID_CLIENT = 1;
    private final Integer ID_RESTAURANT = 1;
    private final Integer ID_PRODUCT = 838;

    private OrderDto dto;

    @Before
    public void beforeEach() {

        List<OrderItemDto> items = new ArrayList<>(1);

        items.add(new OrderItemDto(ID_PRODUCT, 3));

        this.dto = new OrderDto();
        dto.setClient(ID_CLIENT);
        dto.setRestaurant(ID_RESTAURANT);
        dto.setOrderItems(items);

    }

    @Test
    public void givenProductItemAndQuantityShouldCreateOrderItem() {

        Restaurant restaurant = this.restaurantRepository.findById(ID_RESTAURANT).get();

        OrderItemDto dto = new OrderItemDto(ID_PRODUCT, 3);
        assertNotNull("Order item created", service.createOrderItemFromDto(dto, restaurant));

    }

    @Test
    public void givenOrderItemAndClientAndMotoboyCreateOrderDeleteOrder() throws Exception {


        Order result = service.createOrder(this.dto);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderStatus.NEW));

        // delete order
        repository.deleteById(result.get_id());
        assertTrue(!repository.findById(result.get_id()).isPresent());
    }

    @Test
    public void givenOrderIdUpdateStatus() throws Exception {

        // create order
        Order result = service.createOrder(this.dto);
        assertNotNull(result);
        assertTrue(result.getOrderStatus().equals(OrderStatus.NEW));

        // update order status
        service.updateStatus(result.get_id(), OrderStatus.CANCELLED);
        Optional<Order> updatedOrder = repository.findById(result.get_id());

        assertTrue(updatedOrder.isPresent());
        assertEquals(updatedOrder.get().getOrderStatus().toString(), OrderStatus.CANCELLED.toString());

        // delete order
        repository.deleteById(updatedOrder.get().get_id());
        assertTrue(!repository.findById(updatedOrder.get().get_id()).isPresent());
    }

    @Test
    public void givenOrderIdFindAndSetMotoboy() throws Exception {

        // create order
        Order result = service.createOrder(this.dto);
        assertNotNull("The order was created", result);
        assertEquals("The creted order has status NEW", OrderStatus.NEW, result.getOrderStatus());

        assertNull("The created order has no motoboy assigned", result.getMotoboy());

        // update find and set motoboy
        result = service.findAndSetMotoboy(result.get_id());
        assertNotNull("The order was assigned a motoboy", result.getMotoboy());
        assertFalse("The assigned motoboy is not available", result.getMotoboy().isAvailable());

        // turn motoboy available
        Motoboy motoboy = result.getMotoboy();
        motoboy.setAvailable(true);
        motoboyService.save(motoboy);

        motoboy = motoboyService.getById(motoboy.get_id()).get();
        assertTrue("The motoboy is now available", motoboy.isAvailable());

        // delete order
        repository.deleteById(result.get_id());
        assertTrue("The order was deleted", !repository.findById(result.get_id()).isPresent());
    }

    @Test
    public void givenOrderIdGetDirections() throws Exception {

        // create order
        Order newOrder = service.createOrder(this.dto);
        assertNotNull(newOrder);
        assertTrue(newOrder.getOrderStatus().equals(OrderStatus.NEW));

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
        Motoboy motoboy = newOrder.getMotoboy();
        motoboy.setAvailable(true);
        motoboyService.save(motoboy);

        motoboy = motoboyService.getById(motoboy.get_id()).get();
        assertTrue(motoboy.isAvailable());

        // delete order
        repository.deleteById(newOrder.get_id());
        assertTrue(!repository.findById(newOrder.get_id()).isPresent());
    }

    private Order createRadomOrder() {

        Order result = new Order();

        return result;
    }


}
