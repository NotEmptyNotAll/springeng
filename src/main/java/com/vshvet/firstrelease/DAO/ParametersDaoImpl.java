package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("parametersDao")
public class ParametersDaoImpl implements ParametersDao {

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
    public Optional<Parameters> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(Parameters.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Parameters> getAll() {
        return (List<Parameters>) getCurrentSession()
                .createQuery("from Parameters").list();
    }

    /*a request to find all the parameters
      that are relevant to this element.
      This is necessary for the final output
      of parameter information to the user.*/

    @SuppressWarnings("unchecked")
    @Override
    public Parameters findParamByElemId(Integer id) {
        Query query = getCurrentSession()
                .createQuery("from Parameters p where p.elemFk=:idParam  ");
        query.setParameter("idParam", id);
        return query.list().size() != 0 ?
                (Parameters) query.list().get(0) : null;
    }

    @Override
    public void save(Parameters parameters) {
        getCurrentSession().save(parameters);
    }

    @Override
    public void update(Parameters parameters) {
        getCurrentSession().update(parameters);
    }

    @Override
    public void delete(Parameters parameters) {
        getCurrentSession().delete(parameters);
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
