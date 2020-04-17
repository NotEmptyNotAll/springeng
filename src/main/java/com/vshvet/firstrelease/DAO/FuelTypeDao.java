package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuelTypeDao implements Dao<FuelType> {

    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public Optional<FuelType> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(FuelType.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FuelType> getAll() {
        return (List<FuelType>) getCurrentSession()
                .createQuery("from FuelType ");
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllName() {
        return (List<String>) getCurrentSession()
                .createQuery("select new java.lang.String(ft.nameType) from FuelType ft").list();
    }

    @Override
    public void save(FuelType fuelType) {
        getCurrentSession().save(fuelType);
    }

    @Override
    public void update(FuelType fuelType) {
        getCurrentSession().save(fuelType);
    }

    @Override
    public void delete(FuelType fuelType) {
        getCurrentSession().delete(fuelType);
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
