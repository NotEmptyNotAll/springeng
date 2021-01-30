package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.VerificationTokenDao;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Entity.VerificationToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class VerificationTokenDaoImpl implements VerificationTokenDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session openCurrentSessionwithTransaction() {
        return null;
    }

    @Override
    public void closeCurrentSessionwithTransaction() {

    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void setCurrentSession(Session session) {

    }

    @Override
    public void rollbackTransaction() {

    }

    @Override
    @Transactional
    public Optional<VerificationToken> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<VerificationToken> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(VerificationToken verificationToken) throws Exception {
        getCurrentSession().save(verificationToken);
    }

    @Override
    @Transactional
    public void update(VerificationToken newEngine, VerificationToken oldEngine) {

    }

    @Override
    @Transactional
    public void delete(VerificationToken verificationToken) {

    }

    @Override
    @Transactional
    public VerificationToken findByToken(String token) {
        return (VerificationToken) getCurrentSession()
                .createQuery(" from VerificationToken where token='" + token + "'").uniqueResult();
    }
}
