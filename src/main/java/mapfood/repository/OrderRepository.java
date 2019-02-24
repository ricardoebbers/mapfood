package mapfood.repository;

import mapfood.model.Order;
import mapfood.model.Restaurant;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestController
public interface OrderRepository extends BaseRepository<Order, String> {

    List<Order> findAllByRestaurant__id(Integer id);

    List<Order> findAllByRestaurant__idAndOrderStatus(Integer id, String status);

    List<Order> findAllByDateAndOrderStatus(LocalDate date, String status);

}
