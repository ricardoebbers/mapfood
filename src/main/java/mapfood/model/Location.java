package mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Transient;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {

    String type;
    List<Double> coordinates;

    public double getLongitude() {
        return this.coordinates.get(0);
    }

    public double getLatitude() {
        return this.coordinates.get(1);
    }

    public Point toPoint() {
        return new Point(this.getLongitude(), this.getLatitude());
    }

}
