package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("autoModelDao")
@Transactional
public class AutoModelDaoImpl implements AutoModelDao {

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
    public Optional<AutoModel> findById(int id) {
        return Optional.of(getCurrentSession().get(AutoModel.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<AutoModel> getAll() {
        return (List<AutoModel>) getCurrentSession()
                .createQuery("from AutoModel where  date is null").setFirstResult(1)
                .setMaxResults(300).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<String> getAllNameOfModel() {
        return getCurrentSession()
                .createQuery("select am.modelName from AutoModel am where  date is null").list();
    }

    @Override
    public AutoModel findByName(String name) {
        try {
            Query query = getCurrentSession()
                    .createQuery("from AutoModel where modelName=:nameParam");
            query.setParameter("nameParam", name);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (AutoModel) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AutoModel> getCroppedModel(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select DISTINCT ae.autoModelByAutoModelFk " +
                        "from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  ae.autoModelByAutoModelFk m " +
                        "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                        "where  ae.autoModelByAutoModelFk.id>0 and " +
                        "(:engineTypeParam IS NULL or  e.id=:engineTypeParam) " +
                        "and (:autoManufParam IS NULL or am.id=:autoManufParam ) " +
                        "and (:autoModelParam IS NULL or  m.id=:autoModelParam ) " +
                        "and (:fuelTypeParam IS NULL or  e.fuelTypeByFuelTypeFk.id=:fuelTypeParam) " +
                        "and (:engineCapParam IS NULL or  e.engineCapacity=:engineCapParam) " +
                        "and (:powerKwtParam IS NULL or  e.powerKwt=:powerKwtParam) " +
                        "and (((:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) and ae.releaseYearFrom is not null and ae.releaseYearBy is null ) " +
                        "or ((:releaseYearF IS NULL or ae.releaseYearBy=:releaseYearF ) and ae.releaseYearFrom is null and ae.releaseYearBy is not null ) " +
                        "or (   :releaseYearF IS NULL ) " +
                        "or ((:releaseYearF IS NULL or (ae.releaseYearBy>:releaseYearF and ae.releaseYearFrom<:releaseYearF) ) " +
                        "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and am.date is null");
        query.setParameter("engineTypeParam", engineRequest.getEngineType());
        query.setParameter("autoManufParam", engineRequest.getAutoManufacturer());
        query.setParameter("autoModelParam", engineRequest.getAutoModel());
        query.setParameter("fuelTypeParam", engineRequest.getFuelType());
        query.setParameter("engineCapParam", engineRequest.getEngineCapacity());
        query.setParameter("powerKwtParam", engineRequest.getPowerKWt());
        query.setParameter("releaseYearF", engineRequest.getProduceYear());
        return new HashSet<>(query.list());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutoModel> getPagination(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select am from AutoModel am " +
                        "where (:dataParam IS NULL or  UPPER(am.modelName) like :dataParam ) and am.date is null ");
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
                .createQuery("select count(am.id) from AutoModel am " +
                        "where (:dataParam IS NULL or  UPPER(am.modelName) like :dataParam ) and am.date is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return (Long) query.uniqueResult();
    }

    @Override
    @Transactional
    public void save(AutoModel autoModel) {
        getCurrentSession().save(autoModel);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(AutoModel newEngine, AutoModel oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(AutoModel autoModel) {
        autoModel.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(autoModel);
        //getCurrentSession().delete(autoModel);
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

}
