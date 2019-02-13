package mapfood.models;

public class Location {
    private static final String type = "Point";

    private Coordinate coordinates;

    public Location(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Location(double longitude, double latitude) {
        this.coordinates = new Coordinate(longitude, latitude);
    }

    public static String getType() {
        return type;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

}
