package mapfood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationTest {

    @Test
    public void locationShouldHaveAValidType() {
        Location location = new Location();
        location.setType("Point");
        assertEquals("Point", location.getType());
    }

    @Test
    public void locationShouldHaveCoordinates() {
        // original location
        // -30.07518676
        // -51.216203
        Location location = new Location();
        location.setCoordinates(Arrays.asList(-30.07518676, -51.216203));
        assertEquals(-51.216203, location.getLatitude(), 0.001);
        assertEquals(-30.07518676, location.getLongitude(), 0.001);
    }
}
