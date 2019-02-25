package mapfood.repository;

import mapfood.model.Client;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends BaseRepository<Client, Integer> {

}
