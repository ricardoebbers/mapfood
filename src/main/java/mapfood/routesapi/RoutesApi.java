package mapfood.routesapi;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.Unit;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class RoutesApi {

    private static final String API_KEY = "AIzaSyCqHmr8g-TBeVpWAyjxH0DwOwFbSqdtnTc";

    public static void main(String[] args) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        LatLng origin = new LatLng(Double.valueOf("-8.112234"), Double.valueOf("-35.021793"));
        LatLng destination = new LatLng(Double.valueOf("-8.109451"), Double.valueOf("-35.011515"));

        DirectionsResult direction = DirectionsApi.newRequest(context)
                .units(Unit.METRIC)
                .language("pt_br")
                .origin(origin)
                .destination(destination)
                .arrivalTime(Instant.now())
                .awaitIgnoreError();

        List<DirectionsStep> listSteps = Arrays.asList(direction.routes[0].legs[0].steps);

        System.out.println(listSteps);
    }

}
