package mapfood.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Order {

    @Id
    String _id;
    OrderStatus orderStatus;
    Client client;
    Motoboy motoboy;
    Restaurant restaurant;
    List<OrderItem> orderItems;
    Long deliveryExpectation;
    LocalDate date;

}
