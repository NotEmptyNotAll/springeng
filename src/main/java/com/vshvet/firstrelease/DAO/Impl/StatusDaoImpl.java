package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.StatusDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Status;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("statusDao")
public class StatusDaoImpl implements StatusDao {

    private Session currentSession;

    private Transaction currentTransaction;

    @Autowired
    private SessionFactory sessionFactory;

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
    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    @Transactional
    public void delete(Status status) {
        getCurrentSession().delete(status);
    }


    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    @Override
    public void rollbackTransaction() {

    }

    @Override
    public Optional<Status> findById(int id) {
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Status> getAll() {
        return (List<Status>) getCurrentSession()
                .createQuery("from Status ").list();

    }

    @Override
    public void save(Status status)  {

    }

    @Override
    @Transactional
    public void update(Status newEngine, Status oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }



    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}
