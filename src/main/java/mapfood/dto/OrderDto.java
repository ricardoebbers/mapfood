package mapfood.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import mapfood.model.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    String _id;
    OrderStatus orderStatus;
    Integer client;
    Integer motoboy;
    Integer restaurant;
    List<OrderItemDto> orderItems;
    Long deliveryExpectation;
    LocalDate date;

    public OrderDto(Order order) {

        BeanUtils.copyProperties(order, this);

        Client client = order.getClient();
        Motoboy motoboy = order.getMotoboy();
        Restaurant restaurant = order.getRestaurant();

        this.client = client != null ? client.get_id() : null;
        this.motoboy = motoboy != null ? motoboy.get_id() : null;
        this.restaurant = restaurant != null ? restaurant.get_id() : null;

    }

}