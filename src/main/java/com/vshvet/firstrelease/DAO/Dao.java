package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;


// CRUD operations interface
public interface Dao<T> {

    public Session openCurrentSessionwithTransaction();

    public void closeCurrentSessionwithTransaction();

    Optional<T> findById(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
