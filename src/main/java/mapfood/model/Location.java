package mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {

    @JsonIgnore
    private double longitude;
    @JsonIgnore
    private double latitude;
    String type;
    List<Double> coordinates = Arrays.asList(this.longitude, this.latitude);

    public Point toPoint() {
        return new Point(this.longitude, this.latitude);
    }

}
