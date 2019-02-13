package mapfood.model;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String _id;

    private String item_description;
    private String classification;
    private double unit_price;

    public Product(String _id, String item_description, String classification, double unit_price) {
        this._id = _id;
        this.item_description = item_description;
        this.classification = classification;
        this.unit_price = unit_price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
