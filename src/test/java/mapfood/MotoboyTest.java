package mapfood;


import mapfood.model.Motoboy;
import mapfood.repository.MotoboyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class MotoboyTest {
    
    @Autowired
    MotoboyRepository repository;
    
    @Test
    public void getQuote() throws Exception {
        List<Motoboy> result1 = repository.getAvailable(true);
        List<Motoboy> result2 = repository.findByAvailable(true);
        assertNotNull(result1);
        assertNotNull(result2);
    }

}
