package mapfood.repositories;

import mapfood.models.Client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends MongoRepository<Client, String> {
    List<Client> findByIdClient(@Param("id_client") String id_client);
}
