package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.SuperchargedTypeDao;
import com.vshvet.firstrelease.Entity.SuperchargedType;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SuperchargedTypeDaoImpl implements SuperchargedTypeDao {

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
    public Optional<SuperchargedType> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(SuperchargedType.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SuperchargedType> getAll() {
        return (List<SuperchargedType>) getCurrentSession()
                .createQuery("from SuperchargedType");
    }

    @Override
    public void save(SuperchargedType superchargedType) {
        getCurrentSession().save(superchargedType);

    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(SuperchargedType superchargedType) {
        superchargedType.setDate(new Date(new java.util.Date().getTime()));
        save(superchargedType);
    }

    @Override
    public void delete(SuperchargedType superchargedType) {
        getCurrentSession().delete(superchargedType);
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
