package mapfood.service;

import mapfood.model.Location;
import mapfood.model.Motoboy;

import java.util.List;
import java.util.Optional;

public interface MotoboyService {

    List<Motoboy> getAvailable(Boolean available);

    Optional<Motoboy> getById(Integer id);

    void save(Motoboy motoboy);

    void delete(Motoboy motoboy);

    void updateLocAndAvailability(Motoboy motoboy, Location location);

    List<Motoboy> getNearestMotoboys(Integer restaurantId, Integer distance);

    Motoboy getNearestMotoboy(Integer restaurantId, Integer distance);

}
