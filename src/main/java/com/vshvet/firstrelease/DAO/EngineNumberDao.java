package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Entity.EngineNumber;
import com.vshvet.firstrelease.Entity.EngineType;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EngineNumberDao implements Dao<EngineNumber> {

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
    public Optional<EngineNumber> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(EngineNumber.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EngineNumber> getAll() {
        return (List<EngineNumber>) getCurrentSession()
                .createQuery("from EngineNumber ");
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllName() {
        return (List<String>) getCurrentSession()
                .createQuery("select new java.lang.String(en.number) from EngineNumber en").list();
    }

    public AutomobileEngine getAutoEngByNum(String number){
        Query query= getCurrentSession().createQuery("select e from  EngineNumber en " +
                "inner join en.engineByEngineFk e where en.number=:numberParam");
        query.setParameter("numberParam",number);
        return (AutomobileEngine) (query.list().size()!=0?query.list().get(0):null);

    }


    @Override
    public void save(EngineNumber engineNumber) {
        getCurrentSession().save(engineNumber);
    }

    @Override
    public void update(EngineNumber engineNumber) {
        getCurrentSession().update(engineNumber);
    }

    @Override
    public void delete(EngineNumber engineNumber) {
        getCurrentSession().delete(engineNumber);
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
