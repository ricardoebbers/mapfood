package mapfood.config;

import com.google.maps.GeoApiContext;
import com.google.maps.model.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Google's Directions API
 */
@Configuration
public class GeocodingConfig {

  //This value is read from an environment variable
  @Value("${MAPFOOD_API_KEY}")
  private String apiKey;

  // Language on which the directions instructions will be returned
  private static final String LANG = "pt_br";

  /**
   * Creates the GeoApiContext with the API key
   * read from the environment.
   *
   * @return GeoApiContext
   */
  @Bean
  public GeoApiContext getGeoApiContext() {

    GeoApiContext context = new GeoApiContext.Builder()
        .apiKey(this.apiKey)
        .build();

    return context;

  }

  /**
   * Configures the metric system used by the API.
   *
   * @return Unit
   */
  @Bean
  public Unit getUnit() {
    return Unit.METRIC;
  }

  /**
   * Configures the language used by the API.
   *
   * @return String - locale
   */
  @Bean("geocoding_language")
  public String getLanguage() {
    return LANG;
  }

}
