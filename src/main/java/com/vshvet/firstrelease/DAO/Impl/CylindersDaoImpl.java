package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.CylindersDao;
import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CylindersDaoImpl implements CylindersDao {

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
    public Optional<Cylinders> findById(int id) {
        return Optional.of(getCurrentSession().get(Cylinders.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cylinders> getAll() {
        return (List<Cylinders>) getCurrentSession()
                .createQuery("from Cylinders ");
    }

    @Override
    public void save(Cylinders Cylinders) {
        getCurrentSession().save(Cylinders);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(Cylinders cylinders) {
        cylinders.setDate(new Date(new java.util.Date().getTime()));
        save(cylinders);
    }

    @Override
    public void delete(Cylinders Cylinders) {
        getCurrentSession().delete(Cylinders);

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
