package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.UserDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Role;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private Session currentSession;

    private Transaction currentTransaction;


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean existsByUsername(String username) {
        return (Long) getCurrentSession().createQuery("select count(*) from User where username='" + username + "'").uniqueResult() > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        return (Long) getCurrentSession().createQuery("select count(*) from User where email='" + email + "'").uniqueResult() > 0;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of((User) getCurrentSession().createQuery(" from User where username='" + username + "'").uniqueResult());

    }

    @Override
    public void openSession() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();

    }

    @Override
    public Session openCurrentSessionwithTransaction() {
        currentSession = HSessionFactoryUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    public void closeCurrentSessionwithTransaction() {
        if (currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            currentTransaction.commit();
        }
        currentSession.close();

    }


    @Override
    public void rollbackTransaction() {
        currentTransaction.rollback();
    }


    @Override
    public Optional<User> findById(int id) {
        return Optional.of(getCurrentSession().get(User.class, id));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(getCurrentSession().get(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of((User) getCurrentSession()
                .createQuery(" from User where email='" + email + "'").uniqueResult());

    }


    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {
        getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void update(User newEngine, User oldEngine) {
        getCurrentSession().update(newEngine);
        save(oldEngine);
    }


    @Override
    @Transactional
    public void update(User user) {
        getCurrentSession().update(user);
    }


    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
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
