package mapfood.repository;

import mapfood.model.Order;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface OrderRepository extends BaseRepository<Order, String> {

}
