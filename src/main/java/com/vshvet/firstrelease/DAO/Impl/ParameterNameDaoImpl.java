package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ParameterNameDao;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
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

import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

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
                .createQuery("from ParameterNames pn where pn.id<>1 and pn.dateCreate is null").list();
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
        parameterNames.setDateCreate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(parameterNames);
        //getCurrentSession().delete(parameterNames);
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
                .createQuery("from ParameterNames pn where pn.dateCreate is null").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<ParameterNames> getAllParameterSizeName() {
        return (List<ParameterNames>) getCurrentSession()
                .createQuery("from ParameterNames pn where treeRoot=false and pn.dateCreate is null").list();
    }

    @Override
    public ParameterNames findByName(String name) {
        try {
            Query query = getCurrentSession()
                    .createQuery("from ParameterNames where name=:nameParam or  fullName=:nameParam");
            query.setParameter("nameParam", name);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (ParameterNames) query.getSingleResult();
        } catch (
                NoResultException e) {
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Integer getMaxId() {
        return (Integer) getCurrentSession()
                .createQuery("select MAX(pn.id) from ParameterNames pn where treeRoot=true and dateCreate is null").list().get(0);
    }

    @Override
    @Transactional
    public List<ParameterNames> getPagination(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select am from ParameterNames am " +
                        "where (:dataParam IS NULL or  UPPER(am.fullName) like :dataParam  or  UPPER(am.name) like :dataParam) and am.dateCreate is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        Long  countRes=getCountRes(request);
        query.setMaxResults(request.getPageSize()>countRes? countRes.intValue():  request.getPageSize());
        return query.list();
    }

    @Transactional
    private Long getCountRes(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(am.id) from ParameterNames am " +
                        "where (:dataParam IS NULL or  UPPER(am.fullName) like :dataParam  or  UPPER(am.name) like :dataParam) and am.dateCreate is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);

        return (Long) query.uniqueResult();
    }

    @Override
    @Transactional
    public Long getCountResults(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(am.id) from ParameterNames am " +
                        "where (:dataParam IS NULL or  UPPER(am.fullName) like :dataParam  or  UPPER(am.name) like :dataParam) and am.treeRoot=true  and am.dateCreate is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);

        return (Long) query.uniqueResult();
    }

    @Override
    @Transactional
    public long getCountResultsParamSize(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(am.id) from ParameterNames am " +
                        "where (:dataParam IS NULL or  UPPER(am.fullName) like :dataParam  or  UPPER(am.name) like :dataParam) and am.treeRoot=false   and am.dateCreate is null");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        return (Long) query.uniqueResult();
    }
}
