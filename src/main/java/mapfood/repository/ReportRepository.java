package mapfood.repository;

import mapfood.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface ReportRepository extends MongoRepository<Report, String> {

    List<Report> findAllByDateAndRestaurant__id(LocalDate date, Integer id);

}
