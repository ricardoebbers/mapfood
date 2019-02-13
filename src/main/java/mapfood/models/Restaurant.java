package mapfood.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Restaurant {

    @Id
    private String _id;

    private String restaurant;
    private String address_city;
    private String dish_description;
    private Location loc;
    private List<Product> products;


    public Restaurant(String _id, String restaurant, String address_city, String dish_description,
                      Location loc, List<Product> products) {
        this._id = _id;
        this.restaurant = restaurant;
        this.address_city = address_city;
        this.dish_description = dish_description;
        this.loc = loc;
        this.products = products;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getDish_description() {
        return dish_description;
    }

    public void setDish_description(String dish_description) {
        this.dish_description = dish_description;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
