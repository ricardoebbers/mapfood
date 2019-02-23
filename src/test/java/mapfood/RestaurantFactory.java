package mapfood;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import mapfood.model.Location;
import mapfood.model.Product;

import java.util.Arrays;
import java.util.List;

public class RestaurantFactory {

    public DBObject getValidRestaurant() {
        Integer _id = 999;
        String restaurant = "Subway - Cavalhada";
        String address_city = "PORTO ALEGRE";
        String dish_description = "Lanches";
        Location loc = new Location("Point", Arrays.asList(-30.12541, -51.223012));
        Product product = new Product(999,"Refrigerantes Lata","Bebidas",5.5);
        List<Product> products = Arrays.asList(product);
        return BasicDBObjectBuilder.start()
                .add("_id", _id)
                .add("restaurant", restaurant)
                .add("address_city", address_city)
                .add("dish_description", dish_description)
                .add("loc", loc)
                .add("products", products)
                .get();
    }

}
