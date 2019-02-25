package mapfood.service;

import lombok.RequiredArgsConstructor;
import mapfood.model.Location;
import mapfood.model.Motoboy;
import mapfood.model.Restaurant;
import mapfood.repository.MotoboyRepository;
import mapfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoboyServiceImpl implements MotoboyService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    public List<Motoboy> getAvailable(Boolean available) {
        return motoboyRepository.findByAvailable(available);
    }

    public Optional<Motoboy> getById(Integer id) {
        return motoboyRepository.findById(id);
    }

    public void save(Motoboy motoboy) {
        motoboyRepository.save(motoboy);
    }

    // Test purpose only
    public void delete(Motoboy motoboy) {
        motoboyRepository.delete(motoboy);
    }

    public void updateLocAndAvailability(Motoboy motoboy, Location location) {
        motoboy.setAvailable(true);
        motoboy.setLoc(location);
        this.save(motoboy);
    }

    @Override
    public List<Motoboy> getNearestMotoboys(Integer restaurantId, Integer kilometers) {
        if (kilometers == null) {
            kilometers = 5;
        }
        Distance distance = new Distance(kilometers, Metrics.KILOMETERS);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Point point = restaurant.getLoc().toPoint();
        return motoboyRepository.findTop10ByLocNearAndAvailable(point, distance, true);
    }

    @Override
    public Motoboy getNearestMotoboy(Integer restaurantId, Integer distance) {
        return this.getNearestMotoboys(restaurantId, distance).get(0);
    }

}
