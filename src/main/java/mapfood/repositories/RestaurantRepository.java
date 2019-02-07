package mapfood.repositories;

import mapfood.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "restaurant_city", path = "restaurants")
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
