package mapfood.controller;

import com.google.maps.model.DirectionsResult;
import lombok.RequiredArgsConstructor;
import mapfood.model.Motoboy;
import mapfood.service.DirectionsService;
import mapfood.service.MotoboyService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BasePathAwareController
@RestController
@RequestMapping("/motoboys")
@RequiredArgsConstructor
public class MotoboyController {

  private final MotoboyService service;
  
  @RequestMapping(value="/available/{available}")
  public List<Motoboy> getAvailables(@PathVariable("available") Boolean available){
    return service.getAvailable(available);
  }

}
