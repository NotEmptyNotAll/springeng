package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Response.DefaultDataResponse;
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

@Repository("automobileEngineDao")
@Transactional
public class AutomobileEngineDaoImpl implements AutomobileEngineDao {

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
    public Optional<AutomobileEngine> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(AutomobileEngine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AutomobileEngine> getAll() {
        return (List<AutomobileEngine>) getCurrentSession()
                .createQuery("from AutomobileEngine where  date is null").list();
    }


    /*
     * query that finds a car engine according to the data entered.
     * All elements are checked for null,
     *  which allows the user to not know all the input data
     * */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select ae from AutomobileEngine ae " +
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
        List<AutomobileEngine> list = query.list();
        return list;
    }



    @Override
    @Transactional
    public void save(AutomobileEngine automobileEngine) {
        getCurrentSession().save(automobileEngine);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(AutomobileEngine newEngine, AutomobileEngine oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(AutomobileEngine automobileEngine) {
        getCurrentSession().delete(automobileEngine);
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
