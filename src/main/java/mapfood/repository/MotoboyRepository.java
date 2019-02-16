package mapfood.repository;

import mapfood.model.Location;
import mapfood.model.Motoboy;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MotoboyRepository extends BaseRepository<Motoboy, Integer> {
    
    List<Motoboy> findByAvailable(Boolean available);
}
