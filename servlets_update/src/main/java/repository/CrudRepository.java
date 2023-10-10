package repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findByLogin(T login);
}
