package mapfood.repositories;

import mapfood.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findBy_id(ObjectId _id);
}
