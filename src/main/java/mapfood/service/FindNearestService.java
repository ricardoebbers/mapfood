package mapfood.service;

import mapfood.model.Motoboy;

import java.util.List;

public interface FindNearestService {

    List<Motoboy> getNearestMotoboys(Integer restaurantId, Integer distance);

}
