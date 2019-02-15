package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Motoboy;
import mapfood.service.FindNearestService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@BasePathAwareController
@RestController
@RequestMapping("/nearest")
@RequiredArgsConstructor
public class NearestController {

    private final FindNearestService findNearestService;

    @GetMapping("/{restaurantId}")
    public List<Motoboy> getNearestMotoboys(
            @PathVariable("restaurantId") Integer restaurantId,
            @RequestParam(value = "distance", required = false) Integer distance) {
        return this.findNearestService.getNearestMotoboys(restaurantId, distance);
    }
}
