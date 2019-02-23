package mapfood;

import mapfood.model.Location;
import mapfood.model.Motoboy;
import mapfood.service.MotoboyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MotoboyTest {
    
    @Autowired
    MotoboyService service;
  
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void availableMotoboysTest() throws Exception {
        List<Motoboy> result2 = service.getAvailable(true);
        assertNotNull(result2);
    }
    
    @Test
    public void updateMotoboyLocationTest() throws Exception {
        // original location
        // -30.07518676
        // -51.216203
        Optional<Motoboy> result2 = service.getById(1);
        assertNotNull(result2);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(-30.07518676));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(-51.216203));
        
        Location l = new Location();
        l.setType("Point");
        l.setCoordinates(Arrays.asList(new Double[]{0.0,0.0}));
        result2.get().setLoc(l);
        service.save(result2.get());
    
        result2 = service.getById(1);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(0.0));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(0.0));
    
        l.setCoordinates(Arrays.asList(new Double[]{-30.07518676,-51.216203}));
        result2.get().setLoc(l);
        service.save(result2.get());
    
        result2 = service.getById(1);
        assertNotNull(result2);
        assertTrue(result2.get().getLoc().getCoordinates().get(0).equals(-30.07518676));
        assertTrue(result2.get().getLoc().getCoordinates().get(1).equals(-51.216203));
        
    }
}
