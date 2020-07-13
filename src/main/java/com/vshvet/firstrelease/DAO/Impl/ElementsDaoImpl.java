package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.ParamsRequest;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;
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

@Repository("elementsDao")
@Transactional
public class ElementsDaoImpl implements ElementsDao {

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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Integer getMaxId() {
        return (Integer) getCurrentSession()
                .createQuery("select MAX(e.id) from Elements e").list().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Elements> getAllRootElemByAutoId() {
        return (List<Elements>) getCurrentSession()
                .createQuery("select e from Elements e where e.parentId=0").list();
    }

    @Override
    @Transactional
    public Optional<Elements> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(Elements.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Elements> getAll() {
        return (List<Elements>) getCurrentSession()
                .createQuery("from Elements").list();
    }

    /*the request uses the data manually
    measured by the user to find the element in the tree,
     after which it returns all elements that match the condition*/

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AutomobileEngine> findParentsElemByParam(ParamsRequest paramsRequest) throws ClassCastException {
        Double number ;
        try {
             number =Double.parseDouble(paramsRequest.getParameterNumber());
        }catch (NumberFormatException e){
            System.out.println(e);
            number=-1d;
        }

        Query query = getCurrentSession()
                .createQuery("select ae from Parameters p " +
                        "INNER JOIN p.elementsByElemFk ech " +
                        "INNER JOIN  AutomobileEngine ae on ae.id=p.autoId " +
                        "where ech.elemId=:nameChild  " +
                        //"and p.measurementUnitsFk=:unitsParam " +
                        "and ((p.doubleMax>=:numberParam and p.doubleMin<=:numberParam) or p.doubleNum=:numberParam or p.textData=:textData) and p.date is null");
       //query.setParameter("nameParent", paramsRequest.getParameterNodeId());
        query.setParameter("nameChild", paramsRequest.getParameterChildId());
   //     query.setParameter("unitsParam", paramsRequest.getUnitsFullName());
        query.setParameter("numberParam", number);
        query.setParameter("textData", paramsRequest.getParameterNumber());
        return (List<AutomobileEngine>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Elements> getAllNodeOfTree() {
        return (List<Elements>) getCurrentSession()
                .createQuery("from Elements where parentId is not null and parameterNamesByParamNameFk.treeRoot=true").list();
    }


    @Override
    @Transactional
    public void save(Elements parentElements) {
        getCurrentSession().save(parentElements);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(Elements newEngine, Elements oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }





    @Override
    @Transactional
    public void delete(Elements parentElements) {
        getCurrentSession().delete(parentElements);

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
