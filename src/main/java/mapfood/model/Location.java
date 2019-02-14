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

    @JsonIgnore
    @Transient
    private double longitude;
    @Transient
    @JsonIgnore
    private double latitude;
    String type;
    List<Double> coordinates;

    public Point toPoint() {
        return new Point(this.coordinates.get(0), this.coordinates.get(1));
    }

}
