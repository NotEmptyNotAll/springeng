package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.EngineManufactureDao;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EngineManufactureDaoImpl implements EngineManufactureDao {

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
    public Optional<EngineManufacturer> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(EngineManufacturer.class, id));
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<EngineManufacturer> getAll() {
        return (List<EngineManufacturer>) getCurrentSession()
                .createQuery("from EngineManufacturer ");
    }

    //reigns all engine manufacturers.
    // Needed for initial data

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllName() {
        return (List<String>) getCurrentSession()
                .createQuery("select new java.lang.String(em.nameManufacturer)" +
                        " from EngineManufacturer em").list();
    }

    @Override
    public void save(EngineManufacturer engineManufacturer) {
        getCurrentSession().save(engineManufacturer);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(EngineManufacturer engineManufacturer) {
        engineManufacturer.setDate(new Date(new java.util.Date().getTime()));
        save(engineManufacturer);
    }

    @Override
    public void delete(EngineManufacturer engineManufacturer) {
        getCurrentSession().delete(engineManufacturer);
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
