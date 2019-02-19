package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    String _id;
    OrderEnum orderStatus;
    Client client;
    Motoboy motoboy;
    Restaurant restaurant;
    List<OrderItem> orderItems;

    public Order() {
    }

    public Order(OrderEnum orderStatus, Client client, Motoboy motoboy, Restaurant restaurant, List<OrderItem> orderItems) {
        this.orderStatus = orderStatus;
        this.client = client;
        this.motoboy = motoboy;
        this.restaurant = restaurant;
        this.orderItems = orderItems;
    }
}
