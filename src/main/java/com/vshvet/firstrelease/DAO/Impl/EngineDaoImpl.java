package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Entity.Engine;
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
    @Transactional
    public Optional<Engine> findById(int id) {

        return Optional.of(getCurrentSession()
                .get(Engine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> getAllType() {
        return (List<String>) getCurrentSession()
                .createQuery("select e.engineType from Engine e where  e.engineType IS NOT NULL and date is null").list();
    }

    @Override
    @Transactional
    public void update(Engine newEngine, Engine oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Set<Engine> getCroppedType(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select e " +
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


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Engine> getAll() {
        return (List<Engine>) getCurrentSession()
                .createQuery("from Engine e where  e.engineType IS NOT NULL and date is null").list();
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
        getCurrentSession().delete(engine);
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
