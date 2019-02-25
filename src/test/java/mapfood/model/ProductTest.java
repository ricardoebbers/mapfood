package mapfood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Test
    public void productShouldHaveADescription() {
        Product product = new Product();
        product.set_id(123);
        product.setItem_description("test description");
        assertEquals("test description", product.getItem_description());
        assertEquals(123, product.get_id(), 0.001);
    }

    @Test
    public void productShouldHaveAClassification() {
        Product product = new Product();
        product.set_id(123);
        product.setClassification("test classification");
        assertEquals("test classification", product.getClassification());
    }

    @Test
    public void productShouldHaveAPrice() {
        Product product = new Product();
        product.setUnit_price(5.5);
        assertEquals(5.5, product.getUnit_price(), 0.001);
    }

}
