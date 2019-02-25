package mapfood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteTest {

    @Test
    public void routesShouldHaveDescriptionDurationDistanceAndWaypoints() {
        String description = "Rota até a casa do cliente.";
        String distance = "10 km";
        String duration = "10 mins";
        List<String> points = Collections.emptyList();
        Route route = new Route(description, distance, duration, points);
        assertEquals(description, route.getDescription());
        assertEquals(distance, route.getDistance());
        assertEquals(duration, route.getDuration());
        assertEquals(points, route.getRoute());
    }

    @Test
    public void routeTimesShouldContainDetailsOfRoutes() {
        String distance = "10 km";
        Long distanceMeters = 10000L;
        String timeHourMinutes = "10 mins";
        Long timeSeconds = 600L;
        RouteTimes route = new RouteTimes().builder()
                .distance(distance)
                .distanceMeters(distanceMeters)
                .timeHourMinutes(timeHourMinutes)
                .timeSeconds(timeSeconds)
                .build();
        assertEquals(distance, route.getDistance());
        assertEquals(distanceMeters, route.getDistanceMeters());
        assertEquals(timeHourMinutes, route.getTimeHourMinutes());
        assertEquals(timeSeconds, route.getTimeSeconds());


    }

}
