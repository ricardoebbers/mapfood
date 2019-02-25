package mapfood.controller;

import com.google.maps.model.DirectionsResult;
import mapfood.model.Route;
import mapfood.service.DirectionsService;
import mapfood.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DirectionsControllerTest {

    @MockBean
    private DirectionsService directionsService;

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDirectionsShouldReturnAValidDirectionsResult() throws Exception {
        given(directionsService.getDirections("Recife", "Olinda"))
                .willReturn(new DirectionsResult());
        mvc.perform(get("/api/v1/directions?origin=Recife&" +
                "destination=Olinda")).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"geocodedWaypoints\" : null,\n" +
                        "  \"routes\" : null\n" +
                        "}"));
    }

    @Test
    public void getOrderDirectionsShouldReturnAListOfRoutesToRestaurantAndToClient()
            throws Exception {
        String mockOrderId = "5c740e62e072ed0004ddcde1";
        Route routeToRestaurant = new Route("Rota até o restaurante.",
                "1,1 km", "4 minutos", Arrays.asList(
                "Siga na direção norte",
                "Vire à esquerda",
                "Vire à esquerda"));
        Route routeToClient = new Route("Rota até o cliente.", "15,2 km",
                "28 minutos", Arrays.asList(
                "Siga na direção noroeste",
                "Continue para Av.",
                "Pegue a Rua"
        ));
        List<Route> routes = Arrays.asList(routeToRestaurant, routeToClient);
        given(orderService.getOrderDirections(mockOrderId)).willReturn(routes);
        mvc.perform(get("/api/v1/directions/5c740e62e072ed0004ddcde1"))
                .andExpect(status().isOk()).andExpect(content().json("[ {\n" +
                "  \"description\" : \"Rota até o restaurante.\",\n" +
                "  \"distance\" : \"1,1 km\",\n" +
                "  \"duration\" : \"4 minutos\",\n" +
                "  \"route\" : [ \"Siga na direção norte\"," +
                "\"Vire à esquerda\"," +
                "\"Vire à esquerda\" ]\n" +
                "}, {\n" +
                "  \"description\" : \"Rota até o cliente.\",\n" +
                "  \"distance\" : \"15,2 km\",\n" +
                "  \"duration\" : \"28 minutos\",\n" +
                "  \"route\" : [ \"Siga na direção noroeste\"," +
                "\"Continue para Av.\"," +
                "\"Pegue a Rua\" ]\n" +
                "} ]"));
    }

}