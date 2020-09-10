package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.ParametersDao;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Response.ParamSizeNameResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("parametersDao")
@Transactional
public class ParametersDaoImpl implements ParametersDao {

    private Session currentSession;

    private Transaction currentTransaction;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Parameters> getParamByAutoId(Integer autoId)  {
        Query query =  getCurrentSession()
                .createQuery("from Parameters p  where p.elemFk>0 and p.autoId=:autoIdParam and p.date is null");
        query.setParameter("autoIdParam", autoId);
        return    query.list();
    }

    @Override
    @Transactional
    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
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
    public void rollbackTransaction() {
        currentTransaction.rollback();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Parameters> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(Parameters.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Parameters> getAll() {
        return (List<Parameters>) getCurrentSession()
                .createQuery("from Parameters where date is null").list();
    }

    /*a request to find all the parameters
      that are relevant to this element.
      This is necessary for the final output
      of parameter information to the user.*/

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Parameters findParamByElemId(Integer id) {
        Query query = getCurrentSession()
                .createQuery("from Parameters p where p.elemFk=:idParam and date is null  ");
        query.setParameter("idParam", id);
        return query.list().size() != 0 ?
                (Parameters) query.list().get(0) : null;
    }



    @Override
    @Transactional
    public void save(Parameters parameters) {
        getCurrentSession().save(parameters);
        Query query = getCurrentSession().createQuery("update Parameters set autoId = :autoIdParam, elemFk=:idElem" +
                "  where paramId = :idParam");
        query.setParameter("idParam", parameters.getParamId());
        query.setParameter("idElem", parameters.getElemFk());
        query.setParameter("autoIdParam", parameters.getAutoId());
        query.executeUpdate();
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(Parameters newEngine, Parameters oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(Parameters parameters) {
        parameters.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(parameters);
       // getCurrentSession().delete(parameters);
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
