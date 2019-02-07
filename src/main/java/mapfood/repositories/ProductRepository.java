package mapfood.repositories;

import mapfood.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product_restaurant", path = "products")
public interface ProductRepository extends MongoRepository<Product, String> {
}
