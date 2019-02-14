package mapfood.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    @Override
    @RestResource(exported = false)
    <S extends T> List<S> saveAll(Iterable<S> var1);

    @Override
    @RestResource(exported = false)
    <S extends T> S save(S var1);

    @Override
    @RestResource(exported = false)
    <S extends T> S insert(S var1);

    @Override
    @RestResource(exported = false)
    <S extends T> List<S> insert(Iterable<S> var1);

    @Override
    @RestResource(exported = false)
    void deleteById(ID var1);

    @Override
    @RestResource(exported = false)
    void delete(T var1);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends T> var1);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
