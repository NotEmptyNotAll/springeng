package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.CylindersDao;
import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
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

@Repository("cylindersDao")
@Transactional
public class CylindersDaoImpl implements CylindersDao {

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
    @Transactional(readOnly = true)
    public Optional<Cylinders> findById(int id) {
        return Optional.of(getCurrentSession().get(Cylinders.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Cylinders> getAll() {
        return (List<Cylinders>) getCurrentSession()
                .createQuery("from Cylinders c  where  date is null").list();
    }

    @Override
    @Transactional
    public void save(Cylinders Cylinders) {
        getCurrentSession().save(Cylinders);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(Cylinders newEngine, Cylinders oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }



    @Override
    @Transactional
    public void delete(Cylinders cylinders) {
        cylinders.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(cylinders);
       // getCurrentSession().delete(Cylinders);

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
    @Transactional(readOnly = true)
    public List<String> getAllType() {
        return getCurrentSession()
                .createQuery("select c.typeName from Cylinders c  where  date is null").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cylinders> getPagination(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select c from Cylinders c " +
                        "where (:dataParam IS NULL or  UPPER(c.typeName) like :dataParam) and c.date is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        Long  countRes=getCountResults(request);
        query.setMaxResults(request.getPageSize()>countRes? countRes.intValue():  request.getPageSize());
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountResults(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(c.id) from Cylinders c " +
                        "where (:dataParam IS NULL or  UPPER(c.typeName) like :dataParam) and c.date is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        return (Long) query.uniqueResult();
    }
}
