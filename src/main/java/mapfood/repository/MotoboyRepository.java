package mapfood.repository;

import mapfood.model.Motoboy;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MotoboyRepository extends BaseRepository<Motoboy, Integer> {
}
