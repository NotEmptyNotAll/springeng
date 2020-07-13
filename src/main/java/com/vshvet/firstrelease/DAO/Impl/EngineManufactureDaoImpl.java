package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.EngineManufactureDao;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public class EngineManufactureDaoImpl implements EngineManufactureDao {

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
    }


    @Override
    @Transactional
    public Optional<EngineManufacturer> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(EngineManufacturer.class, id));
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<EngineManufacturer> getAll() {
        return (List<EngineManufacturer>) getCurrentSession()
                .createQuery("from EngineManufacturer ").list();
    }

    //reigns all engine manufacturers.
    // Needed for initial data

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> getAllName() {
        return (List<String>) getCurrentSession()
                .createQuery("select new java.lang.String(em.nameManufacturer)" +
                        " from EngineManufacturer em").list();
    }

    @Override
    @Transactional
    public Set<EngineManufacturer> getCroppedByParamName(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select  e.engineManufacturerByEngineManufacturerFk " +
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
                        "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and ae.date is null");
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
    @Transactional
    public void save(EngineManufacturer engineManufacturer) {
        getCurrentSession().save(engineManufacturer);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(EngineManufacturer newEngine, EngineManufacturer oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(EngineManufacturer engineManufacturer) {
        getCurrentSession().delete(engineManufacturer);
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
