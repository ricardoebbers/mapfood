package mapfood.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    String _id;
    OrderEnum orderStatus;
    Client client;
    Motoboy motoboy;
    Restaurant restaurant;
    List<OrderItem> orderItems;
    Long deliveryExpectation;

}
