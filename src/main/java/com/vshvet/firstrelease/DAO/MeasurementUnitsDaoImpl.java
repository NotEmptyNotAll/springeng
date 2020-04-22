package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("measurementUnitsDao")
public class MeasurementUnitsDaoImpl implements Dao<MeasurementUnits> {

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
    public Optional<MeasurementUnits> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(MeasurementUnits.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MeasurementUnits> getAll() {
        return (List<MeasurementUnits>) getCurrentSession()
                .createQuery("from MeasurementUnits").list();
    }

    @Override
    public void save(MeasurementUnits measurementUnits) {
        getCurrentSession().save(measurementUnits);
    }

    @Override
    public void update(MeasurementUnits measurementUnits) {
        getCurrentSession().update(measurementUnits);
    }

    @Override
    public void delete(MeasurementUnits measurementUnits) {
        getCurrentSession().delete(measurementUnits);
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
