package mapfood.controller;

import com.google.maps.model.DirectionsResult;
import lombok.RequiredArgsConstructor;
import mapfood.model.Route;
import mapfood.service.DirectionsService;
import mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/directions")
@RequiredArgsConstructor
public class DirectionController {

    @Autowired
    private DirectionsService directionsService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public DirectionsResult getDirections(
            @RequestParam String origin,
            @RequestParam String destination
    ) {
        return directionsService.getDirections(origin, destination);
    }

    @GetMapping("/{orderId}")
    public List<Route> getOrderDirections(
            @PathVariable String orderId
    ) {
        return orderService.getOrderDirections(orderId);
    }

}
