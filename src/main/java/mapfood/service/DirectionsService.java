package mapfood.service;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import mapfood.model.Route;
import mapfood.model.RouteTimes;

public interface DirectionsService {

    DirectionsResult getDirections(String origin, String destination);

    DirectionsResult getDirectionsWithWaypoint(String origin, String waypoint,
                                               String destination);

    DirectionsResult getDirections(
            double originLat, double originLong, double destLat, double destLong
    );

    DirectionsResult getDirections(DirectionsApiRequest request);

    RouteTimes getTimeDistance(double originLat, double originLong, double destLat, double destLong);

    Route getRouteInstructions(DirectionsResult directionsResult, String description);

}
