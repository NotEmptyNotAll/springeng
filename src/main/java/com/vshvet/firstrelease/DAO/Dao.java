package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;


// CRUD operations interface
public interface Dao<T> {

    Session openCurrentSessionwithTransaction();

    void closeCurrentSessionwithTransaction();

    Session getCurrentSession();

    void setCurrentSession(Session session);

    void rollbackTransaction();

    Optional<T> findById(int id);

    List<T> getAll();

    void save(T t) throws Exception;

    void update(T newEngine, T oldEngine);

    void delete(T t);
}
