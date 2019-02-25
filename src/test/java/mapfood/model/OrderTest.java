package mapfood.model;

import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Test
    public void orderShouldHaveItems() {
        Order order = new Order();
        order.setOrderItems(Collections.emptyList());
        assertEquals(Collections.emptyList(), order.getOrderItems());
    }

    @Test
    public void orderShouldHaveADeliveryExpectation() {
        Order order = new Order();
        order.setDeliveryExpectation(10L);
        assertEquals(Optional.of(10L), Optional.ofNullable(order.getDeliveryExpectation()));
    }

    @Test
    public void orderShouldHaveACreationDate() {
        Order order = new Order();
        LocalDate date = LocalDate.now();
        order.setDate(date);
        assertEquals(date, order.getDate());
    }

    public void orderShouldBeCreatedWithAllArgs() {
        Client client = new Client();
        Motoboy motoboy = new Motoboy();
        Restaurant restaurant = new Restaurant(123);
        List<OrderItem> orderItems = Collections.singletonList(new OrderItem());
        LocalDate date = LocalDate.now();
        Order order = new Order("abcd", OrderEnum.NOVO, client, motoboy, restaurant,
                orderItems,10L, date);
        assertEquals("abcd", order.get_id());
        assertEquals(OrderEnum.NOVO.valorStatus(), order.getOrderStatus().valorStatus());
        assertEquals(client, order.getClient());
        assertEquals(motoboy, order.getMotoboy());
        assertEquals(restaurant, order.getRestaurant());
        assertEquals(orderItems, order.getOrderItems());
        assertEquals(Optional.of(10L), Optional.ofNullable(order.getDeliveryExpectation()));
        assertEquals(date, order.getDate());
    }
}
