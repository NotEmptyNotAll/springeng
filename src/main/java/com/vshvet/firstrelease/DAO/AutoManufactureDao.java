package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutoManufactureDao implements Dao<AutoManufacture> {
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
    public Optional<AutoManufacture> findById(int id) {
        return Optional.of(getCurrentSession().get(AutoManufacture.class, id));
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<AutoManufacture> getAll() {
        return (List<AutoManufacture>) getCurrentSession()
                .createQuery("from AutoManufacture");
    }

    @Override
    public void save(AutoManufacture autoManufacture) {
        getCurrentSession().save(autoManufacture);
    }

    @Override
    public void update(AutoManufacture autoManufacture) {
        getCurrentSession().update(autoManufacture);
    }

    @Override
    public void delete(AutoManufacture autoManufacture) {
        getCurrentSession().delete(autoManufacture);
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
