package mapfood.controller;

import com.google.maps.model.DirectionsResult;
import lombok.RequiredArgsConstructor;
import mapfood.model.Order;
import mapfood.model.OrderItem;
import mapfood.service.DirectionsService;
import mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private final DirectionsService directionsService;
    
    @PostMapping("/{idRestaurant}/{idClient}")
    public Order createOrder(
            @RequestBody List<OrderItem> orderItemList,
            @PathVariable String idRestaurant,
            @PathVariable String idClient) {
        
        return orderService.createOrder(idClient, idRestaurant, orderItemList);
    }
    
    //Coletar rota via api do google Maps passando motoboy e pedido
    @GetMapping("/directions/{idMotoBoy}/{idOrder}")
    public List<DirectionsResult> getOrderDirections(
            @PathVariable String idMotoBoy,
            @PathVariable String idOrder
    ) {
        return orderService.getOrderDirections(idMotoBoy, idOrder);
    }
}
