package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.RoleDao;
import com.vshvet.firstrelease.Entity.ERole;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Entity.Role;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    private Session currentSession;

    private Transaction currentTransaction;

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Optional<Role> findByName(ERole roleUser) {
        return Optional.of((Role) getCurrentSession().createQuery(" from Role where name='" + roleUser + "'").uniqueResult());
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
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    @Override
    @Transactional
    public void rollbackTransaction() {

    }

    @Override
    @Transactional
    public Optional<Role> findById(int id) {
        return Optional.of(getCurrentSession().get(Role.class, id));
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(Role role) {
        getCurrentSession().save(role);
    }

    @Override
    @Transactional
    public void update(Role newEngine, Role oldEngine) {
        getCurrentSession().update(newEngine);
            save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(Role role) {
        getCurrentSession().delete(role);
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}
