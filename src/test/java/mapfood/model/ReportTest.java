package mapfood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportTest {

    @Test
    public void reportShouldBeCreatedWithAllArgs() {
        String id = "abcd";
        Restaurant restaurant = new Restaurant(123);
        String distanceToRestaurant = "1 km";
        String durationToRestaurant = "1 min";
        String distanceToClient = "10 km";
        String durationToClient = "10 min";
        LocalDate date = LocalDate.now();
        Report report = new Report(id, restaurant, distanceToRestaurant, durationToRestaurant,
                distanceToClient, durationToClient, date);
        assertEquals(id, report.getId());
        assertEquals(restaurant, report.getRestaurant());
        assertEquals(distanceToRestaurant, report.getDistanceToRestaurant());
        assertEquals(durationToRestaurant, report.getDurationToRestaurant());
        assertEquals(distanceToClient, report.getDistanceToClient());
        assertEquals(durationToClient, report.getDurationToClient());
        assertEquals(date, report.getDate());
    }

}
