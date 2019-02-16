package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Person {

    @Id Integer _id;
    @Indexed
    Location loc;

}
