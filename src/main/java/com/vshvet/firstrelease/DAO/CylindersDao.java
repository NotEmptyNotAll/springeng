package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CylindersDao implements Dao<Cylinders> {

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

    @Override
    public void update(Cylinders Cylinders) {
        getCurrentSession().update(Cylinders);
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
