package mapfood.models;

import org.springframework.data.annotation.Id;

public class Client extends Person {

    public Client(String _id, Location loc) {
        super(_id, loc);
    }
}
