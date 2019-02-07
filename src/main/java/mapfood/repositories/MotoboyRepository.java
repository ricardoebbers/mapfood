package mapfood.repositories;

import mapfood.models.Motoboy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "motoboys", path = "motoboys")
public interface MotoboyRepository extends MongoRepository<Motoboy, String> {
}
