package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.MeasurementUnitsDao;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("measurementUnitsDao")
@Transactional
public class MeasurementUnitsDaoImpl implements MeasurementUnitsDao {

    private Session currentSession;

    private Transaction currentTransaction;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    @Transactional
    public void rollbackTransaction() {
        currentTransaction.rollback();
    }

    @Override
    @Transactional
    public void closeCurrentSessionwithTransaction() {
        if (currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            currentTransaction.commit();
        }
        currentSession.close();
    }

    @Override
    @Transactional
    public Optional<MeasurementUnits> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(MeasurementUnits.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<MeasurementUnits> getAll() {
        return (List<MeasurementUnits>) getCurrentSession()
                .createQuery("from MeasurementUnits where  date is null").list();
    }

    @Override
    @Transactional
    public void save(MeasurementUnits measurementUnits) {
        getCurrentSession().save(measurementUnits);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(MeasurementUnits newEngine, MeasurementUnits oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(MeasurementUnits measurementUnits) {
        measurementUnits.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(measurementUnits);
       // getCurrentSession().delete(measurementUnits);
    }


    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
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
