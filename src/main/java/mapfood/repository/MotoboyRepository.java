package mapfood.repository;

import mapfood.model.Motoboy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MotoboyRepository extends MongoRepository<Motoboy, Integer> {
}
