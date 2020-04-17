package com.vshvet.firstrelease.DAO;

import java.util.List;
import java.util.Optional;


//interface that guarantees crude operations
public interface Dao<T> {
    Optional<T> findById(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
