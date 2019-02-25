package mapfood.controller;

import lombok.RequiredArgsConstructor;
import mapfood.model.Motoboy;
import mapfood.service.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/motoboys")
@RequiredArgsConstructor
public class MotoboyController {

    @Autowired
    private MotoboyService service;

    @RequestMapping(value = "/available/{available}")
    public List<Motoboy> getAvailables(@PathVariable("available") Boolean available) {
        return service.getAvailable(available);
    }

}
