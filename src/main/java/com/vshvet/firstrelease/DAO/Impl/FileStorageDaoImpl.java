package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.FileStorageDao;
import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.FileStorage;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class FileStorageDaoImpl implements FileStorageDao {
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
    public void closeCurrentSessionwithTransaction() {
        if (currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            currentTransaction.commit();
        }
        currentSession.close();
    }

    @Override
    @Transactional
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void setCurrentSession(Session session) {
        this.currentSession = session;

    }


    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    @Override
    @Transactional
    public void rollbackTransaction() {
        currentTransaction.rollback();
    }

    @Override
    @Transactional
    public Optional<FileStorage> findById(int id) {
        return Optional.of(getCurrentSession().get(FileStorage.class, id));

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<FileStorage> getAll() {
        return (List<FileStorage>) getCurrentSession()
                .createQuery("from FileStorage am where   date is null").list();

    }

    @Override
    @Transactional
    public void save(FileStorage fileStorage) throws Exception {
        getCurrentSession().save(fileStorage);

    }

    @Override
    @Transactional
    public void update(FileStorage newEngine, FileStorage oldEngine) {
        getCurrentSession().update(newEngine);
        getCurrentSession().save(oldEngine);
    }

    @Override
    @Transactional
    public void delete(FileStorage fileStorage) {
        fileStorage.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(fileStorage);
    }
}
