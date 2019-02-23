package mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Point;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {

    String type;
    List<Double> coordinates;

    @JsonIgnore
    public double getLongitude() {
        return this.coordinates.get(0);
    }

    @JsonIgnore
    public double getLatitude() {
        return this.coordinates.get(1);
    }

    public Point toPoint() {
        return new Point(this.getLongitude(), this.getLatitude());
    }

    public String coordinatesToString() {
        return this.coordinates.get(0) + "," + this.coordinates.get(1);
    }

}
