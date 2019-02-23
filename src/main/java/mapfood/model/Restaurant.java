package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Restaurant {

    @Id Integer _id;
    String restaurant;
    String address_city;
    String dish_description;
    @Indexed
    Location loc;
    List<Product> products;

    public Restaurant(Integer _id) {
        this._id = _id;
    }

}
