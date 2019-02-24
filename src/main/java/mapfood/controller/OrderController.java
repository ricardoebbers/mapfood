package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.service.DirectionsService;
import mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private final DirectionsService directionsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(
            @RequestBody List<OrderItem> orderItemList,
            @RequestParam String idRestaurant,
            @RequestParam String idClient) {

        return orderService.createOrder(idClient, idRestaurant, orderItemList);
    }

    @PutMapping("/{orderId}")
    public Order updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam String status) {
        return orderService.updateStatus(orderId, status);
    }

    @PutMapping("/{orderId}/motoboy")
    public Order findAndSetMotoboy(
            @PathVariable String orderId) {
        return orderService.findAndSetMotoboy(orderId);
    }

    @GetMapping("/{idRestaurant}")
    public List<Order> getOrdersForRestaurant(
            @PathVariable String idRestaurant,
            @RequestParam(value = "status", required = false) String status) {
        return orderService.getOrderForRestaurant(idRestaurant, status);
    }

    @GetMapping("/deliveryForecast/{idOrder}")
    public Long getDeliveryForecast(@PathVariable String idOrder) {
        return orderService.deliveryForecast(idOrder);
    }
}
