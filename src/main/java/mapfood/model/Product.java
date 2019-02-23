package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Product {

    @Id Integer _id;
    String item_description;
    String classification;
    double unit_price;

}
