package mapfood.models;

import org.springframework.data.annotation.Id;

public class Motoboy {
    @Id
    private String _id;

    private int id_motoboy;
    private double longitude;
    private double latitude;

    public int getIdMotoboy() {
        return id_motoboy;
    }

    public void setIdMotoboy(int id_motoboy) {
        this.id_motoboy = id_motoboy;
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
}
