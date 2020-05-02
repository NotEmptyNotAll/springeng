package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("engineDao")
public class EngineDaoImpl implements EngineDao {

    private Session currentSession;

    private Transaction currentTransaction;

    @Override
    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }


    @Override
    public Optional<Engine> findById(int id) {

        return Optional.of(getCurrentSession()
                .get(Engine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllType() {
        return (List<String>) getCurrentSession()
                .createQuery("select e.engineType from Engine e where  e.engineType IS NOT NULL").list();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Engine> getAll() {
        return (List<Engine>) getCurrentSession()
                .createQuery("from Engine");
    }


    @Override
    public void save(Engine engine) {
        getCurrentSession().save(engine);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(Engine engine) {
        engine.setDate(new Date(new java.util.Date().getTime()));
        save(engine);
    }

    @Override
    public void delete(Engine engine) {
        getCurrentSession().delete(engine);
    }


    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}