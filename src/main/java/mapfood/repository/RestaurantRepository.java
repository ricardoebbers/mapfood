package mapfood.repository;

import mapfood.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {
}
