package mapfood;


import mapfood.model.Motoboy;
import mapfood.service.MotoboyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MotoboyTest {
    
    @Autowired
    MotoboyService service;
    @Autowired
    private TestRestTemplate restTemplate;
    
    private class MotoboyList {
        private List<Motoboy> motoboys;
        
        public MotoboyList() {
            motoboys = new ArrayList<>();
        }
    
        public List<Motoboy> getMotoboys() {
            return motoboys;
        }
    
        public void setMotoboys(List<Motoboy> motoboys) {
            this.motoboys = motoboys;
        }
    
        // standard constructor and getter/setter
    }
    
    @Test
    public void getAvaiablesFromService() throws Exception {
        List<Motoboy> result2 = service.getAvailable(true);
        assertNotNull(result2);
    }
    
    
//    @Test
//    public void getAvaiablesFromController() throws Exception {
//        ResponseEntity<MotoboyList> response;
//        response = restTemplate.getForEntity("/motoboys/available/true",MotoboyList.class);
//
//        //Motoboy resultList = response.getBody();
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        //assertNotNull(quote);
//    }

}
