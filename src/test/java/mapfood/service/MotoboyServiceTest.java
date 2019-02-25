package mapfood.service;

import mapfood.model.Location;
import mapfood.model.Motoboy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MotoboyServiceTest {

    @Autowired
    MotoboyService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenAvailableConditionGetAvailableMotoboysByStatus() throws Exception {
        List<Motoboy> result = service.getAvailable(true);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0).isAvailable());
    }

    @Test
    public void givenIdGetMotoboyById() throws Exception {
        Optional<Motoboy> result = service.getById(1);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertTrue(result.get().get_id().equals(1));
    }

    private Motoboy createTestMotoboy(){

        Motoboy result = new Motoboy();
        result.setAvailable(true);
        result.set_id(-99);

        Location motoboyLocation = new Location();
        List<Double> coordinates = new ArrayList<>();

        motoboyLocation.setType("Point");
        coordinates.add(-30.08580672);
        coordinates.add(-51.142207);
        motoboyLocation.setCoordinates(coordinates);

        return result;
    }

    @Test
    public void givenMotoboySaveThanGetThanDelete() throws Exception {

        Motoboy motoboy = createTestMotoboy();
        service.save(motoboy);

        if(!motoboy.get_id().toString().isEmpty()){
        Optional<Motoboy> result = service.getById(motoboy.get_id());
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertTrue(result.get().get_id().equals(motoboy.get_id()));

        service.delete(result.get());
        result = service.getById(motoboy.get_id());
        assertNotNull(result);
        assertTrue(!result.isPresent());
        }
    }

    @Test
    public void updateMotoboyLocationAndAvailabilityTest() throws Exception {
        // original location
        // -30.07518676
        // -51.216203
        Optional<Motoboy> result2 = service.getById(1);
        assertNotNull(result2);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(-30.07518676));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(-51.216203));
        assertTrue(result2.get().isAvailable());

        // Setting new localtion and changing availability
        Location l = new Location();
        l.setType("Point");
        l.setCoordinates(Arrays.asList(0.0, 0.0));
        result2.get().setLoc(l);

        result2.get().setAvailable(false);

        service.save(result2.get());

        // testing changes
        result2 = service.getById(1);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(0.0));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(0.0));
        assertFalse(result2.get().isAvailable());

        // restore default values
        l.setCoordinates(Arrays.asList(-30.07518676, -51.216203));
        result2.get().setLoc(l);
        result2.get().setAvailable(true);
        service.save(result2.get());

        result2 = service.getById(1);
        assertNotNull(result2);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(-30.07518676));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(-51.216203));
        assertFalse(!result2.get().isAvailable());

    }
}
