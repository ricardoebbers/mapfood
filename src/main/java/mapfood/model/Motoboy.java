package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Motoboy extends Person {

    private boolean available;

}
