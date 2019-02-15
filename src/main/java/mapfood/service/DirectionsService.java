package mapfood.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service that interacts with the Google's Direction API
 */
@Service
@RequiredArgsConstructor
public class DirectionsService {

  // The properties bellow are automatically injected by Spring
  // their values are configured on the GeocodingConfig class

  private final GeoApiContext context;
  private final Unit unit;

  @Qualifier("geocoding_language")
  private final String language;

  /**
   * Get the directions for the given locations descriptions.
   *
   * @param origin - String describing the origin location,
   *              like Shopping Rio Mar or Olinda
   * @param destination - String describing the destination location
   * @return DirectionResult
   */
  public DirectionsResult getDirections(String origin, String destination) {

    DirectionsApiRequest request = DirectionsApi.newRequest(this.context)
        .origin(origin)
        .destination(destination);

    return this.getDirections(request);

  }

  /**
   * Get the directions for the given coordinates.
   *
   * @param originLat - Origin's latitude
   * @param originLong - Origin's longitude
   * @param destLat - Destination's latitude
   * @param destLong - Destination's longitude
   * @return DirectionResult
   */
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

}