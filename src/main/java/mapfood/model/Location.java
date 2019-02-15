package mapfood.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.geo.Point;

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
