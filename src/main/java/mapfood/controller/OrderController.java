package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.model.Product;
import mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{idRestaurant}/{idClient}")
    public Order createOrder(
            @RequestBody List<OrderItem> orderItemList,
            @PathVariable String idRestaurant,
            @PathVariable String idClient) {

            return orderService.createOrder(idClient, idRestaurant, orderItemList);

    }

    @GetMapping("/{idRestaurant}")
    public List<Order> getOrdersForRestaurant(
            @PathVariable String idRestaurant,
            @RequestParam(value = "status", required = false) String status) {
        return orderService.getOrderForRestaurant(idRestaurant, status);
    }
}
