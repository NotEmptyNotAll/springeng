package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
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

import javax.persistence.NoResultException;
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
    @Transactional(readOnly = true)
    public Integer getMaxId() {
        return (Integer) getCurrentSession()
                .createQuery("select MAX(e.id) from Elements e").list().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Elements> getAllRootElemByAutoId() {
        return (List<Elements>) getCurrentSession()
                .createQuery("select e from Elements e  where  e.parentId=0 ").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Elements> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(Elements.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Elements> getAll() {
        return (List<Elements>) getCurrentSession()
                .createQuery("from Elements").list();
    }

    /*the request uses the data manually
    measured by the user to find the element in the tree,
     after which it returns all elements that match the condition*/

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> findParentsElemByParam(ParamsRequest paramsRequest) throws ClassCastException {
        Double number;
        Query query;
        if (Double.parseDouble(paramsRequest.getParameterNumber()) > 0) {
            try {
                number = Math.floor(Double.parseDouble(paramsRequest.getParameterNumber()));
            } catch (NumberFormatException e) {
                System.out.println(e);
                number = -1d;
            }
            query = getCurrentSession()
                    .createQuery("select ae from Parameters p " +
                            "INNER JOIN p.elementsByElemFk ech " +
                            "INNER JOIN  AutomobileEngine ae on ae.id=p.autoId " +
                            "where ech.parentId=:nameParent  " +
                            //"and p.measurementUnitsFk=:unitsParam " +
                            "and ((round(p.doubleMax-0.5,0)>=:numberParam and round(p.doubleMin-0.5,0)<=:numberParam) or round(p.doubleNum-0.5,0)=:numberParam " +
                            "  or p.textData like :textDataParam ) and p.date is null");
        } else {
            number = Double.parseDouble(paramsRequest.getParameterNumber());
            query = getCurrentSession()
                    .createQuery("select ae from Parameters p " +
                            "INNER JOIN p.elementsByElemFk ech " +
                            "INNER JOIN  AutomobileEngine ae on ae.id=p.autoId " +
                            "where ech.parentId=:nameParent  " +
                            //"and p.measurementUnitsFk=:unitsParam " +
                            "and ((p.doubleMax-0.5>=:numberParam and p.doubleMin-0.5<=:numberParam) or p.doubleNum-0.5=:numberParam " +
                            "  or p.textData like :textDataParam ) and p.date is null");
        }


        query.setParameter("nameParent", paramsRequest.getParameterNodeId());
        // query.setParameter("nameChild", paramsRequest.getParameterChildId());
        //     query.setParameter("unitsParam", paramsRequest.getUnitsFullName());
        query.setParameter("numberParam", number);
        query.setParameter("textDataParam", "%" + paramsRequest.getParameterNumber() + "%");
        return (List<AutomobileEngine>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Integer> getElemFkListByAutoId(Integer autoId) {
        return (List<Integer>) getCurrentSession()
                .createQuery("select p.elemFk from Parameters p where p.autoId=:autoIdParam")
                .setParameter("autoIdParam", autoId).list();
    }


    @Transactional(readOnly = true)
    @Override
    public Elements findByParentIdAndParamFk(Integer paramFk, Integer parentId) {
        try {
            Query query = getCurrentSession()
                    .createQuery("from Elements where paramNameFk=:paramFkParam and  parentId=:parentIdParam");
            query.setParameter("paramFkParam", paramFk);
            query.setParameter("parentIdParam", parentId);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (Elements) query.getSingleResult();
        } catch (
                NoResultException e) {
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Elements> getElementByParentId(Integer id) {
        Query query = getCurrentSession()
                .createQuery("from Elements e " +
                        "where e.parentId=:idParam  and  e.childElements.size=0 " +
                        "and e.date is null  ");
        query.setFirstResult(0);
        query.setMaxResults(1);
        query.setParameter("idParam", id);
        return (List<Elements>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Elements> getAllNodeOfTree() {
        return (List<Elements>) getCurrentSession()
                .createQuery("from Elements where parentId is not null and parameterNamesByParamNameFk.treeRoot=true and date is null").list();
    }


    @Override
    @Transactional
    public void save(Elements parentElements) {
        getCurrentSession().save(parentElements);
        Query query = getCurrentSession().createQuery("update Elements set color = :colorParam," +
                " sortNumber=:sortParam where elemId = :idParam");
        query.setParameter("colorParam", parentElements.getColor());
        query.setParameter("sortParam", parentElements.getSortNumber());
        query.setParameter("idParam", parentElements.getElemId());
        query.executeUpdate();
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void update(Elements newEngine, Elements oldEngine) {
        Query query = getCurrentSession().createQuery("update Elements set color = :colorParam," +
                " sortNumber=:sortParam where elemId = :idParam");
        query.setParameter("colorParam", newEngine.getColor());
        query.setParameter("sortParam", newEngine.getSortNumber());
        query.setParameter("idParam", newEngine.getElemId());
        query.executeUpdate();
        //  getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(Elements elements) {
        elements.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(elements);
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
