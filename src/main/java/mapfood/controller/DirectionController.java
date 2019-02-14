package mapfood.controller;

import com.google.maps.model.DirectionsResult;
import lombok.RequiredArgsConstructor;
import mapfood.service.DirectionsService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@BasePathAwareController
@RestController
@RequestMapping("/directions")
@RequiredArgsConstructor
public class DirectionController {

  private final DirectionsService directionsService;

  @GetMapping
  public DirectionsResult getDirections(
      @RequestParam String origin,
      @RequestParam String destination
  ) {

    return this.directionsService.getDirections(origin, destination);

  }

}
