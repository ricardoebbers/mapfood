package mapfood.repository;

import mapfood.model.Restaurant;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RestaurantRepository extends BaseRepository<Restaurant, Integer> {
}
