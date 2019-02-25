package mapfood.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderItemTest {

    @Test
    public void orderItemShouldHaveAProduct() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        orderItem.setProduct(product);
        assertEquals(product, orderItem.getProduct());
    }

    @Test
    public void orderItemShouldHaveAQuantity() {
        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(1);
        assertEquals(Integer.valueOf(1), orderItem.getQuantity());
    }

}
