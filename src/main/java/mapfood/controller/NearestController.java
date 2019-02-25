package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Motoboy;
import mapfood.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/nearest")
@RequiredArgsConstructor
public class NearestController {

    @Autowired
    private MotoboyService motoboyService;

    @GetMapping("/{restaurantId}")
    public List<Motoboy> getNearestMotoboys(
            @PathVariable("restaurantId") Integer restaurantId,
            @RequestParam(value = "distance", required = false) Integer distance) {
        return this.motoboyService.getNearestMotoboys(restaurantId, distance);
    }

}
