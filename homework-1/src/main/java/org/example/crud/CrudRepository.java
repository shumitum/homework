package org.example.crud;

import java.util.List;

public interface CrudRepository<T> {
    void save(T t);

    void update(T t);

    void delete(Integer id);

    List<T> findAll();

    boolean existsById(Integer id );
}
