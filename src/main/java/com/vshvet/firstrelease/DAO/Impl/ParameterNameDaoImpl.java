package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ParameterNameDao;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("parameterNameDao")
public class ParameterNameDaoImpl implements ParameterNameDao {

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
    public Optional<ParameterNames> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(ParameterNames.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ParameterNames> getAll() {
        return (List<ParameterNames>) getCurrentSession()
                .createQuery("from ParameterNames pn").list();
    }

    @Override
    public void save(ParameterNames parameterNames) {
        getCurrentSession().save(parameterNames);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(ParameterNames parameterNames) {
        parameterNames.setDateCreate(new Date(new java.util.Date().getTime()));
        save(parameterNames);
    }

    @Override
    public void delete(ParameterNames parameterNames) {
        getCurrentSession().delete(parameterNames);
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
