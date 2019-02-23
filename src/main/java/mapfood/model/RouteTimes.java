package mapfood.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteTimes {

    String distance;
    Long distanceMeters ;
    String timeHourMinutes;
    Long timeSeconds;

}
