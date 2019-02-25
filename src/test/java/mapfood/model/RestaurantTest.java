package mapfood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantTest {

    @Test
    public void restaurantShouldHaveAllCsvAttributes() {
        Integer _id = 123;
        String restaurantName = "Restaurante da esquina";
        String address_city = "RECIFE";
        String dish_description = "Sanduiches";
        Location loc = new Location();
        List<Product> products = Collections.emptyList();
        Restaurant restaurant = new Restaurant(_id);
        restaurant.setRestaurant(restaurantName);
        restaurant.setAddress_city(address_city);
        restaurant.setDish_description(dish_description);
        restaurant.setLoc(loc);
        restaurant.setProducts(products);
        assertEquals(_id, restaurant.get_id());
        assertEquals(restaurantName, restaurant.getRestaurant());
        assertEquals(address_city, restaurant.getAddress_city());
        assertEquals(dish_description, restaurant.getDish_description());
        assertEquals(loc, restaurant.getLoc());
        assertEquals(products, restaurant.getProducts());
    }

}
