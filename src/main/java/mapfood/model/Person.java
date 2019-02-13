package mapfood.model;

import org.springframework.data.annotation.Id;

public class Person {
    @Id
    private String _id;

    private Location loc;

    public Person(String _id, Location loc) {
        this._id = _id;
        this.loc = loc;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
