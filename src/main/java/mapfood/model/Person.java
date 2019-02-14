package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Person {

    @Id String _id;
    Location loc;

}
