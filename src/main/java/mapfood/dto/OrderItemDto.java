package mapfood.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import mapfood.model.OrderItem;
import mapfood.model.Product;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDto {

  Integer product;
  Integer quantity;

  public OrderItemDto(OrderItem item) {

    Product product = item.getProduct();

    this.product = product != null ? product.get_id() : null;
    this.quantity = item.getQuantity();

  }

}
