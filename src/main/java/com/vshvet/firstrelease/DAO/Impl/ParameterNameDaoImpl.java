package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ParameterNameDao;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("parameterNameDao")
@Transactional
public class ParameterNameDaoImpl implements ParameterNameDao {

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
    public Optional<ParameterNames> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(ParameterNames.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<ParameterNames> getAll() {
        return (List<ParameterNames>) getCurrentSession()
                .createQuery("from ParameterNames pn where   pn.dateCreate is null").list();
    }

    @Override
    @Transactional
    public void save(ParameterNames parameterNames) {
        getCurrentSession().save(parameterNames);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(ParameterNames newEngine, ParameterNames oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }



    @Override
    @Transactional
    public void delete(ParameterNames parameterNames) {
        getCurrentSession().delete(parameterNames);
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
    public List<ParameterNames> getAllTreeRootName() {
        return (List<ParameterNames>) getCurrentSession()
                .createQuery("from ParameterNames pn where treeRoot=true and pn.dateCreate is null").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Integer getMaxId() {
        return (Integer) getCurrentSession()
                .createQuery("select MAX(pn.id) from ParameterNames pn where treeRoot=true and dateCreate is null").list().get(0);
    }
}
