package mapfood.repository;

import mapfood.model.Motoboy;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MotoboyRepository extends BaseRepository<Motoboy, Integer> {

    @Query(value = "SELECT * FROM motoboy WHERE available = :available")
    List<Motoboy> getAvailable(@Param("available") Boolean available);
    
    List<Motoboy> findByAvailable(Boolean available);

    List<Motoboy> findTop10ByLocNearAndAvailable(Point location, Distance distance,
                                                 boolean available);

}
