package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("automobileEngineDao")
public class AutomobileEngineDaoImpl  implements AutomobileEngineDao {

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
    public Optional<AutomobileEngine> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(AutomobileEngine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AutomobileEngine> getAll() {
        return (List<AutomobileEngine>) getCurrentSession()
                .createQuery("from AutomobileEngine ");
    }


    /*
    * query that finds a car engine according to the data entered.
    * All elements are checked for null,
    *  which allows the user to not know all the input data
    * */
    @SuppressWarnings("unchecked")
    @Override
    public List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select ae from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  AutoModel m on ae.autoModelFk=m.id " +
                        "INNER JOIN e.fuelTypeByFuelTypeFk ft " +
                        "where (:engineTypeParam IS NULL or  e.engineType=:engineTypeParam) " +
                        "and (:autoManufParam IS NULL or am.manufactureName=:autoManufParam ) " +
                        "and (:autoModelParam IS NULL or  m.modelName=:autoModelParam ) " +
                        "and (:fuelTypeParam IS NULL or  e.fuelTypeByFuelTypeFk.nameType=:fuelTypeParam) " +
                        "and (:engineCapParam IS NULL or  e.engineCapacity=:engineCapParam) " +
                        "and (:powerKwtParam IS NULL or  e.powerKwt=:powerKwtParam) " +
                        "and (:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) ");
        query.setParameter("engineTypeParam",engineRequest.getEngineType());
        query.setParameter("autoManufParam",engineRequest.getAutoManufacturer());
        query.setParameter("autoModelParam",engineRequest.getAutoModel());
        query.setParameter("fuelTypeParam",engineRequest.getFuelType());
        query.setParameter("engineCapParam",engineRequest.getEngineCapacity());
        query.setParameter("powerKwtParam",engineRequest.getPowerKWt());
        query.setParameter("releaseYearF",engineRequest.getProduceYear());
        List<AutomobileEngine> list = query.list();
        return list;
    }

    @Override
    public void save(AutomobileEngine automobileEngine) {
        getCurrentSession().save(automobileEngine);
    }

    @Override
    public void update(AutomobileEngine automobileEngine) {
        getCurrentSession().update(automobileEngine);
    }

    @Override
    public void delete(AutomobileEngine automobileEngine) {
        getCurrentSession().delete(automobileEngine);
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
