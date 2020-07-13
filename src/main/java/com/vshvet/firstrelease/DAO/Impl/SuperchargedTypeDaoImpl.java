package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.SuperchargedTypeDao;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Entity.SuperchargedType;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SuperchargedTypeDaoImpl implements SuperchargedTypeDao {

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
    public void closeCurrentSessionwithTransaction() {
        if (currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            currentTransaction.commit();
        }
        currentSession.close();
    }

    @Override
    @Transactional
    public void rollbackTransaction() {
        currentTransaction.rollback();
    }

    @Override
    @Transactional
    public Optional<SuperchargedType> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(SuperchargedType.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SuperchargedType> getAll() {
        return (List<SuperchargedType>) getCurrentSession()
                .createQuery("from SuperchargedType where  date is null").list();
    }

    @Override
    @Transactional
    public void save(SuperchargedType superchargedType) {
        getCurrentSession().save(superchargedType);

    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(SuperchargedType newEngine, SuperchargedType oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }



    @Override
    @Transactional
    public void delete(SuperchargedType superchargedType) {
        getCurrentSession().delete(superchargedType);
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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> getAllType() {
        return getCurrentSession()
                .createQuery("select st.nameType from SuperchargedType st where  date is null").list();
    }
}
