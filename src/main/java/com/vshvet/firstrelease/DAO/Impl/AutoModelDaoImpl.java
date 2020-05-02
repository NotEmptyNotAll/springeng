package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("autoModelDao")
public class AutoModelDaoImpl implements AutoModelDao {

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
    public Optional<AutoModel> findById(int id) {
        return Optional.of(getCurrentSession().get(AutoModel.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AutoModel> getAll() {
        return (List<AutoModel>) getCurrentSession()
                .createQuery("from AutoModel ");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllNameOfModel() {
        return  getCurrentSession()
                .createQuery("select am.modelName from AutoModel am").list();
    }

    @Override
    public void save(AutoModel autoModel) {
        getCurrentSession().save(autoModel);
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    public void update(AutoModel autoModel) {
        autoModel.setDate(new Date(new java.util.Date().getTime()));
        save(autoModel);
    }

    @Override
    public void delete(AutoModel autoModel) {
        getCurrentSession().delete(autoModel);
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