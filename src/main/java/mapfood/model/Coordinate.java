package mapfood.model;

import java.util.Arrays;
import java.util.List;

public class Coordinate {
    private Double longitude;
    private Double latitude;

    public Coordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Double> toList() {
        return Arrays.asList(this.longitude, this.latitude);
    }
}
