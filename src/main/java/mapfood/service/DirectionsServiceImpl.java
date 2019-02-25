package mapfood.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import mapfood.model.Route;
import mapfood.model.RouteTimes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that interacts with the Google's Direction API
 */
@Service
@RequiredArgsConstructor
public class DirectionsServiceImpl implements DirectionsService {

    // The properties bellow are automatically injected by Spring
    // their values are configured on the GeocodingConfig class

    private final GeoApiContext context;
    private final Unit unit;

    @Qualifier("geocoding_language")
    private final String language;

    /**
     * Get the directions for the given locations descriptions.
     *
     * @param origin      - String describing the origin location,
     *                    like Shopping Rio Mar or Olinda
     * @param destination - String describing the destination location
     * @return DirectionResult
     */
    @Override
    public DirectionsResult getDirections(String origin, String destination) {

        DirectionsApiRequest request = DirectionsApi.newRequest(this.context)
                .origin(origin)
                .destination(destination);

        return this.getDirections(request);

    }

    /**
     * Get the directions for the given locations descriptions.
     *
     * @param origin      - String describing the origin location,
     *                    like Shopping Rio Mar or Olinda
     * @param destination - String describing the destination location
     * @param waypoint - String describring the waypoint
     * @return DirectionResult
     */
    @Override
    public DirectionsResult getDirectionsWithWaypoint(String origin, String waypoint,
                                                      String destination) {

        DirectionsApiRequest request = DirectionsApi.newRequest(this.context)
                .origin(origin)
                .destination(destination)
                .waypoints(waypoint);

        return this.getDirections(request);

    }

    /**
     * Get the directions for the given coordinates.
     *
     * @param originLat  - Origin's latitude
     * @param originLong - Origin's longitude
     * @param destLat    - Destination's latitude
     * @param destLong   - Destination's longitude
     * @return DirectionResult
     */
    @Override
    public DirectionsResult getDirections(
            double originLat, double originLong, double destLat, double destLong
    ) {

        DirectionsApiRequest request = DirectionsApi.newRequest(this.context)
                .origin(new LatLng(originLat, originLong))
                .destination(new LatLng(destLat, destLong));

        return this.getDirections(request);

    }

    /**
     * et the directions for the given DirectionsApiRequest.
     *
     * @param request
     * @return DirectionResult
     */
    @Override
    public DirectionsResult getDirections(DirectionsApiRequest request) {

        try {

            DirectionsResult result = request.units(this.unit)
                    .language(this.language)
                    .await();

            return result;

        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Get the RouteTimes for the given coordinates.
     * @param originLat
     * @param originLong
     * @param destLat
     * @param destLong
     * @return RouteTimes
     */
    @Override
    public RouteTimes getTimeDistance(double originLat, double originLong, double destLat, double destLong) {

        DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                .origins(new LatLng(originLat, originLong))
                .destinations(new LatLng(destLat, destLong))
                .awaitIgnoreError();

        DistanceMatrixElement matrixRoute = matrix.rows[0].elements[0];

        return new RouteTimes().builder()
                .distance(matrixRoute.distance.humanReadable)
                .distanceMeters(matrixRoute.distance.inMeters)
                .timeHourMinutes(matrixRoute.duration.humanReadable)
                .timeSeconds(matrixRoute.duration.inSeconds)
                .build();
    }

    @Override
    public Route getRouteInstructions(DirectionsResult directionsResult, String description) {
        DirectionsStep[] steps = directionsResult.routes[0].legs[0].steps;
        String distance = directionsResult.routes[0].legs[0].distance.humanReadable;
        String duration = directionsResult.routes[0].legs[0].duration.humanReadable;
        List<String> route = new ArrayList<>();
        for (int i = 0; i < steps.length; i++) {
            route.add(steps[i].htmlInstructions.replaceAll("<[^>]*>", ""));
        }
        return new Route(description, distance, duration, route);
    }

}
