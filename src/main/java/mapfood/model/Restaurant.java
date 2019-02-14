package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {

    @Id String _id;
    String restaurant;
    String address_city;
    String dish_description;
    Location loc;
    List<Product> products;

}
