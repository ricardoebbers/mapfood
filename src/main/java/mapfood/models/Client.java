package mapfood.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Client {
    @Id
    private ObjectId _id;

    private String idClient;
    private double longitude;
    private double latitude;

    public Client() {};

    public Client(ObjectId _id, String idCliente, double longitude, double latitude) {
        this._id = _id;
        this.idClient = idCliente;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getIdCliente() {
        return idClient;
    }

    public void setIdCliente(String idClient) {
        this.idClient = idClient;
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
