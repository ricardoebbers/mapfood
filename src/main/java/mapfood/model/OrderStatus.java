package mapfood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum OrderStatus implements Serializable {

    NEW("New"),
    RECEIVED("Received"),
    PREPARING("Preparing"),
    SEARCHING_DELIVERY("Searching for delivery"),
    WAITING_DELIVERY("Waiting for delivery"),
    EN_ROUTE("En route"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String valorStatus;

}
