package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ParamsRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ElementsDao implements Dao<Elements> {

    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public Optional<Elements> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(Elements.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Elements> getAll() {
        return (List<Elements>) getCurrentSession()
                .createQuery("from Elements");
    }

    /*the request uses the data manually
    measured by the user to find the element in the tree,
     after which it returns all elements that match the condition*/

    @SuppressWarnings("unchecked")
    public List<Elements> findParentsElemByParam(ParamsRequest paramsRequest) throws ClassCastException {
        Query query = getCurrentSession()
                .createQuery("select e from Parameters p " +
                        "INNER JOIN p.elementsByElemFk e " +
                        "where e.parameterNamesByParamNameFk.fullName=:nameParam " +
                        "and p.measurementUnitsByMeasurementUnitsFk.fullNameM=:unitsParam " +
                        "and ((p.doubleMax>:numberParam and p.doubleMin<:numberParam) or p.doubleNum=:numberParam)");
        query.setParameter("nameParam", paramsRequest.getParameterName());
        query.setParameter("unitsParam", paramsRequest.getUnitsFullName());
        query.setParameter("numberParam", paramsRequest.getParameterNumber());
        return  (List<Elements>)query.list();
    }


    @Override
    public void save(Elements parentElements) {
        getCurrentSession().save(parentElements);
    }

    @Override
    public void update(Elements parentElements) {
        getCurrentSession().update(parentElements);

    }

    @Override
    public void delete(Elements parentElements) {
        getCurrentSession().delete(parentElements);

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
