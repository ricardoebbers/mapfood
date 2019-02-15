package mapfood.service;

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

@Service
public class FindNearestServiceImpl implements FindNearestService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;
    
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
}
