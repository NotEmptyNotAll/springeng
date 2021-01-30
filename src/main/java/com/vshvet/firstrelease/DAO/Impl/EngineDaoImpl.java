package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.Payload.Request.EngineRequest;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("engineDao")
@Transactional
public class EngineDaoImpl implements EngineDao {

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
    public Optional<Engine> findById(int id) {

        return Optional.of(getCurrentSession()
                .get(Engine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<String> getAllType() {
        return (List<String>) getCurrentSession()
                .createQuery("select e.engineType from Engine e where  e.engineType IS NOT NULL and date is null ").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Engine findByName(String name) {
        try {
            Query query = getCurrentSession()
                    .createQuery("from Engine where engineType=:nameParam ");
            query.setParameter("nameParam", name);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (Engine) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void update(Engine newEngine, Engine oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Set<Engine> getCroppedType(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select DISTINCT e " +
                        "from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  ae.autoModelByAutoModelFk m " +
                        "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                        "where (:engineTypeParam IS NULL or  e.id=:engineTypeParam) " +
                        "and (:autoManufParam IS NULL or am.id=:autoManufParam ) " +
                        "and (:autoModelParam IS NULL or  m.id=:autoModelParam ) " +
                        "and (:fuelTypeParam IS NULL or  e.fuelTypeByFuelTypeFk.id=:fuelTypeParam) " +
                        "and (:engineCapParam IS NULL or  e.engineCapacity=:engineCapParam) " +
                        "and (:powerKwtParam IS NULL or  e.powerKwt=:powerKwtParam) " +
                        "and (((:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) and ae.releaseYearFrom is not null and ae.releaseYearBy is null ) " +
                        "or ((:releaseYearF IS NULL or ae.releaseYearBy=:releaseYearF ) and ae.releaseYearFrom is null and ae.releaseYearBy is not null ) " +
                        "or (   :releaseYearF IS NULL ) " +
                        "or ((:releaseYearF IS NULL or (ae.releaseYearBy>:releaseYearF and ae.releaseYearFrom<:releaseYearF) ) " +
                        "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and ae.date is null ");
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
    public List<Engine> getPaginationData(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select e from Engine e " +
                        "where (:dataParam IS NULL or  UPPER(e.engineType) like :dataParam ) and e.date is null");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        Long countRes = getNumberOfPage(request);
        query.setMaxResults(request.getPageSize() > countRes ? countRes.intValue() : request.getPageSize());
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getNumberOfPage(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(e.id) from Engine e " +
                        "where (:dataParam IS NULL or  UPPER(e.engineType) like :dataParam ) and e.date is null");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        return (Long) query.uniqueResult();
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Engine> getAll() {
        return (List<Engine>) getCurrentSession()
                .createQuery("from Engine e where  e.engineType IS NOT NULL and date is null ").setFirstResult(1)
                .setMaxResults(300).list();
    }


    @Override
    @Transactional
    public void save(Engine engine) {
        getCurrentSession().save(engine);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database

    @Override
    @Transactional
    public void delete(Engine engine) {
        engine.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(engine);
        // getCurrentSession().delete(engine);
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
