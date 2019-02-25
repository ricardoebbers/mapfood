package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.dto.OrderDto;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.service.DirectionsService;
import mapfood.service.OrderService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@BasePathAwareController
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DirectionsService directionsService;


    @GetMapping
    public List<OrderDto> getAll() {
        return this.orderService
            .findAll()
            .stream()
            .map(OrderDto::new)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(
            @RequestBody List<OrderItem> orderItemList,
            @RequestParam String idRestaurant,
            @RequestParam String idClient) {

        Order order = orderService.createOrder(idClient, idRestaurant, orderItemList);

        return new OrderDto(order);
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam String status) {

        Order order =  orderService.updateStatus(orderId, status);

        return new OrderDto(order);
    }

    @PutMapping("/{orderId}/motoboy")
    public OrderDto findAndSetMotoboy(
            @PathVariable String orderId) {
        return new OrderDto(orderService.findAndSetMotoboy(orderId));
    }

    @GetMapping("/{idRestaurant}")
    public List<OrderDto> getOrdersForRestaurant(
            @PathVariable String idRestaurant,
            @RequestParam(value = "status", required = false) String status) {
        return orderService.getOrderForRestaurant(idRestaurant, status)
            .stream()
            .map(OrderDto::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/deliveryForecast/{idOrder}")
    public Long getDeliveryForecast(@PathVariable String idOrder) {
        return orderService.deliveryForecast(idOrder);
    }
}
