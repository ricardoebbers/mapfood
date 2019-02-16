package mapfood;


import mapfood.model.Location;
import mapfood.model.Motoboy;
import mapfood.repository.MotoboyRepository;
import mapfood.service.MotoboyService;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MotoboyTest {
    
    @Autowired
    MotoboyService service;
    
    @Test
    public void availableMotoboysTest() throws Exception {
        List<Motoboy> result2 = service.getAvailable(true);
        assertNotNull(result2);
    }
    
    @Test
    public void updateMotoboyLocationTest() throws Exception {
        Optional<Motoboy> result2 = service.getbyId(1);
        assertNotNull(result2);
    }
}
