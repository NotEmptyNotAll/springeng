package com.vshvet.firstrelease.DAO.Impl;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Payload.Request.EngineRequest;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.Payload.Request.ParametersPageRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository("automobileEngineDao")
@Transactional
public class AutomobileEngineDaoImpl implements AutomobileEngineDao {

    private Session currentSession;

    private Transaction currentTransaction;

    private Long countResults = null;

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

    @Override
    @Transactional(readOnly = true)
    public Optional<AutomobileEngine> findById(int id) {
        return Optional.of(getCurrentSession()
                .get(AutomobileEngine.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> getAll() {
        return (List<AutomobileEngine>) getCurrentSession()
                .createQuery("from AutomobileEngine where  date is null ").setFirstResult(1)
                .setMaxResults(300).list();
    }


    /*
     * query that finds a car engine according to the data entered.
     * All elements are checked for null,
     *  which allows the user to not know all the input data
     * */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest) {
        Query query = getCurrentSession()
                .createQuery("select ae from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  ae.autoModelByAutoModelFk m " +
                        "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                        "where (:engineTypeParam IS NULL or  e.id=:engineTypeParam) " +
                        "and (:autoManufParam IS NULL or am.id=:autoManufParam ) " +
                        "and (:autoModelParam IS NULL or  m.id=:autoModelParam ) " +
                        "and (:fuelTypeParam IS NULL or  e.fuelTypeByFuelTypeFk.id=:fuelTypeParam) " +
                        "and (:engineCapParam IS NULL or  e.engineCapacity=:engineCapParam) " +
                        "and (:powerKwtParam IS NULL or  e.powerKwt=:powerKwtParam) " +
                        "and (((:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) and ae.releaseYearFrom is not null and ae.releaseYearBy is null ) " +
                        "or ((:releaseYearF IS NULL or ae.releaseYearBy=:releaseYearF ) and ae.releaseYearFrom is null and ae.releaseYearBy is not null ) " +
                        "or (   :releaseYearF IS NULL ) " +
                        "or ((:releaseYearF IS NULL or (ae.releaseYearBy>:releaseYearF and ae.releaseYearFrom<:releaseYearF) ) " +
                        "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and ae.date is null");
        query.setParameter("engineTypeParam", engineRequest.getEngineType());
        query.setParameter("autoManufParam", engineRequest.getAutoManufacturer());
        query.setParameter("autoModelParam", engineRequest.getAutoModel());
        query.setParameter("fuelTypeParam", engineRequest.getFuelType());
        query.setParameter("engineCapParam", engineRequest.getEngineCapacity());
        query.setParameter("powerKwtParam", engineRequest.getPowerKWt());
        query.setParameter("releaseYearF", engineRequest.getProduceYear());
        List<AutomobileEngine> list = query.list();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public AutomobileEngine findByNames(String autoModel, String engineType, String autoManuf, String years) {
        try {
            Integer year;
            try {
                year = Integer.parseInt(years);
            } catch (NumberFormatException e) {
                year = null;
            }
            Query query = getCurrentSession()
                    .createQuery("from AutomobileEngine ae where " +
                            "  (:nameEngineParam IS NULL or ae.engineByEngineFk.engineType=:nameEngineParam ) " +
                            " and (:autoModelParam IS NULL or ae.autoModelByAutoModelFk.modelName=:autoModelParam ) " +
                            " and (:autoManufParam IS NULL or ae.autoManufactureByAutoManufactureFk.manufactureName=:autoManufParam ) " +
                            "and ( :releaseYear IS NULL or ae.years=:releaseYear) " +
                            "and (:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF )");
            query.setParameter("nameEngineParam", engineType);
            query.setParameter("autoModelParam", autoModel);
            query.setParameter("autoManufParam", autoManuf);
            query.setParameter("releaseYearF", year);
            query.setParameter("releaseYear", years != null && year == null ? years : null);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (AutomobileEngine) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> getPaginationAutoEngByParam(ParametersPageRequest request, List<AutomobileEngine> automobileEngineList) {
        Integer year;
        try {
            year = Integer.parseInt(request.getReleaseYear());
        } catch (NumberFormatException e) {
            year = null;
        }

        StringBuilder paramSunQuery = new StringBuilder();
        paramSunQuery.append("select ae from AutomobileEngine ae " +
                "INNER JOIN ae.engineByEngineFk e " +
                "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                "INNER JOIN  ae.autoModelByAutoModelFk m " +
                "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                "where (:engineTypeParam IS NULL or  UPPER(e.engineType) like :engineTypeParam) " +
                "and (:cylinderPlace IS NULL or UPPER(e.cylindersByCylindersPlacementFk.typeName) like :cylinderPlace ) " +
                "and (:superchargedType IS NULL or UPPER(e.superchargedTypeBySuperchargedTypeFk.nameType) like :superchargedType ) " +
                "and (:autoManufParam IS NULL or UPPER(am.manufactureName) like :autoManufParam ) " +
                "and (:autoModelParam IS NULL or  upper(m.modelName) like :autoModelParam ) " +
                "and (:fuelTypeParam IS NULL or  upper(e.fuelTypeByFuelTypeFk.nameType) like :fuelTypeParam) " +
                "and (:engineCapParam IS NULL or   ( e.engineCapacity>=(:engineCapParam - :percentParam) and e.engineCapacity<=(:engineCapParam + :percentParam) )) " +
                "and (:cylinderNum IS NULL or  e.cylindersNumber=:cylinderNum) ");

        System.out.println(paramSunQuery.length());
        if (automobileEngineList != null) {

            for (AutomobileEngine automobileEngine :
                    automobileEngineList) {
                if (paramSunQuery.length() == 878) {
                    paramSunQuery.append("and ( ae.id in (" + automobileEngine.getId());
                } else {
                    paramSunQuery.append("," + automobileEngine.getId());

                }
            }
        }


        if (automobileEngineList != null && automobileEngineList.size() > 0) {
            paramSunQuery.append("))");
        }
        paramSunQuery.append("and (:pistonDiameter IS NULL or  ( e.pistonDiameter>=(:pistonDiameter - :percentParam) and e.pistonDiameter<=(:pistonDiameter + :percentParam) )) " +
                "and (:pistonStoke IS NULL or  ( e.pistonStroke>=(:pistonStoke - :percentParam) and e.pistonStroke<=(:pistonStoke + :percentParam) )) " +
                "and (   :releaseYear IS NULL or upper( ae.years) like  :releaseYear) " +
                "and (:degreeCompression IS NULL or  e.degreeCompression=:degreeCompression) " +
                "and (:powerKwtParam IS NULL or  upper(e.powerKwt) like :powerKwtParam) " +
                "and (((:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) and ae.releaseYearFrom is not null and ae.releaseYearBy is null ) " +
                "or ((:releaseYearF IS NULL or ae.releaseYearBy=:releaseYearF ) and ae.releaseYearFrom is null and ae.releaseYearBy is not null ) " +
                "or (   :releaseYearF IS NULL ) " +
                "or ((:releaseYearF IS NULL or (ae.releaseYearBy>=:releaseYearF and ae.releaseYearFrom<=:releaseYearF) ) " +
                "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and ae.date is null ORDER BY ae.id");


        //this.countResults = getCountResultsByParam(request);

        //  int lastPageNumber = (int) (Math.ceil(((double) this.countResults) / ((double) pageSize)));
        Query query = getCurrentSession()
                .createQuery(String.valueOf(paramSunQuery));
        query.setParameter("engineTypeParam", request.getEngineType() != null ? ("%" + request.getEngineType().toUpperCase() + "%") : null);
        query.setParameter("autoManufParam", request.getAutoManufacture() != null ? ("%" + request.getAutoManufacture().toUpperCase() + "%") : null);
        query.setParameter("autoModelParam", request.getModelName() != null ? ("%" + request.getModelName().toUpperCase() + "%") : null);
        query.setParameter("fuelTypeParam", request.getFuelType() != null ? ("%" + request.getFuelType().toUpperCase() + "%") : null);
        query.setParameter("cylinderPlace", request.getCylinderPlace() != null ? ("%" + request.getCylinderPlace().toUpperCase() + "%") : null);
        query.setParameter("cylinderNum", request.getCylinderNum());
        query.setParameter("pistonDiameter", request.getPistonDiameter());
        query.setParameter("pistonStoke", request.getPistonStoke());
        query.setParameter("superchargedType", request.getSuperchargedType() != null ? ("%" + request.getSuperchargedType().toUpperCase() + "%") : null);
        query.setParameter("degreeCompression", request.getDegreeCompression());
        query.setParameter("engineCapParam", request.getEngineCapacity());
        query.setParameter("powerKwtParam", request.getPowerKWT() != null ? ("%" + request.getPowerKWT().toUpperCase() + "%") : null);
        query.setParameter("releaseYearF", year);
        query.setParameter("percentParam", request.getSearchPercent());
        query.setParameter("releaseYear", request.getReleaseYear() != null && year == null ? ("%" + request.getReleaseYear().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        query.setMaxResults(request.getPageSize());
        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> getPaginationAutoEng(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select ae from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  ae.autoModelByAutoModelFk m " +
                        "where (:dataParam IS NULL or  UPPER(e.engineType) like :dataParam " +
                        "or  UPPER(am.manufactureName) like :dataParam  " +
                        "or  UPPER(m.modelName) like :dataParam ) and am.date is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        query.setFirstResult((request.getInitRecordFrom() - 1) * request.getPageSize());
        Long countRes = getCountResults(request);
        query.setMaxResults(request.getPageSize() > countRes ? countRes.intValue() : request.getPageSize());

        return query.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountResults(PaginationDataRequest request) {
        Query query = getCurrentSession()
                .createQuery("select count(ae.id) from AutomobileEngine ae " +
                        "INNER JOIN ae.engineByEngineFk e " +
                        "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                        "INNER JOIN  ae.autoModelByAutoModelFk m " +
                        "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                        "where (:dataParam IS NULL or  UPPER(e.engineType) like :dataParam " +
                        "or  UPPER(am.manufactureName) like :dataParam  " +
                        "or  UPPER(m.modelName) like :dataParam ) and am.date is null ");
        query.setParameter("dataParam", request.getData() != null ? ("%" + request.getData().toUpperCase() + "%") : null);
        return (Long) query.uniqueResult();
    }

    @Override
    @Transactional
    public void save(AutomobileEngine automobileEngine) {
        getCurrentSession().saveOrUpdate(automobileEngine);
      /*  Query query = getCurrentSession().createQuery("update AutomobileEngine  set " +
                " releaseYearFrom = :yearsFromParam," +
                " years = :yearsParam " +
                "  where id = :idParam");
        query.setParameter("yearsFromParam", automobileEngine.getReleaseYearFrom());
        query.setParameter("yearsParam", automobileEngine.getYears());
        query.setParameter("idParam", automobileEngine.getId());
        query.executeUpdate();*/
    }

    //we do not update the object,
    // but create a new one,
    // so the object does not get deleted from the database
    @Override
    @Transactional
    public void update(AutomobileEngine newEngine, AutomobileEngine oldEngine) {
        getCurrentSession().update(newEngine);
       // save(oldEngine);
    }


    @Override
    @Transactional
    public void delete(AutomobileEngine automobileEngine) {
        automobileEngine.setDate(new java.sql.Date(new java.util.Date().getTime()));
        getCurrentSession().update(automobileEngine);
        //getCurrentSession().delete(automobileEngine);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public Long getCountResultsByParam(ParametersPageRequest request, List<AutomobileEngine> automobileEngineList) {
        Integer year;
        try {
            year = Integer.parseInt(request.getReleaseYear());
        } catch (NumberFormatException e) {
            year = null;
        }
        StringBuilder paramSunQuery = new StringBuilder();
        paramSunQuery.append(" select count (ae.id) from AutomobileEngine ae " +
                "INNER JOIN ae.engineByEngineFk e " +
                "INNER JOIN ae.autoManufactureByAutoManufactureFk am " +
                "INNER JOIN  ae.autoModelByAutoModelFk m " +
                "INNER JOIN FuelType ft on e.fuelTypeFk=ft.id " +
                "where (:engineTypeParam IS NULL or  UPPER(e.engineType) like :engineTypeParam) " +
                "and (:cylinderPlace IS NULL or UPPER(e.cylindersByCylindersPlacementFk.typeName) like :cylinderPlace ) " +
                "and (:superchargedType IS NULL or UPPER(e.superchargedTypeBySuperchargedTypeFk.nameType) like :superchargedType ) " +
                "and (:autoManufParam IS NULL or UPPER(am.manufactureName) like :autoManufParam ) " +
                "and (:autoModelParam IS NULL or  upper(m.modelName) like :autoModelParam ) " +
                "and (:fuelTypeParam IS NULL or  upper(e.fuelTypeByFuelTypeFk.nameType) like :fuelTypeParam) " +
                "and (:engineCapParam IS NULL or  e.engineCapacity=:engineCapParam) " +
                "and (:cylinderNum IS NULL or  e.cylindersNumber=:cylinderNum) ");

        System.out.println(paramSunQuery.length());
        if (automobileEngineList != null) {

            for (AutomobileEngine automobileEngine :
                    automobileEngineList) {
                if (paramSunQuery.length() == 890) {
                    paramSunQuery.append("and ( ae.id in (" + automobileEngine.getId());
                } else {
                    paramSunQuery.append("," + automobileEngine.getId());

                }
            }
        }


        if (automobileEngineList != null && automobileEngineList.size() > 0) {
            paramSunQuery.append("))");
        }
        paramSunQuery.append(" and (:pistonDiameter IS NULL or  e.pistonDiameter=:pistonDiameter) " +
                "and (   :releaseYear IS NULL or upper( ae.years) like  :releaseYear) " +
                "and (:degreeCompression IS NULL or  e.degreeCompression=:degreeCompression) " +
                "and (:powerKwtParam IS NULL or  upper(e.powerKwt) like :powerKwtParam) " +
                "and (((:releaseYearF IS NULL or ae.releaseYearFrom=:releaseYearF ) and ae.releaseYearFrom is not null and ae.releaseYearBy is null ) " +
                "or ((:releaseYearF IS NULL or ae.releaseYearBy=:releaseYearF ) and ae.releaseYearFrom is null and ae.releaseYearBy is not null ) " +
                "or (   :releaseYearF IS NULL ) " +
                "or ((:releaseYearF IS NULL or (ae.releaseYearBy>=:releaseYearF and ae.releaseYearFrom<=:releaseYearF) ) " +
                "and ae.releaseYearFrom is not null and ae.releaseYearBy is not null )) and ae.date is null ORDER BY ae.id");
        //this.countResults = getCountResultsByParam(request);

        //  int lastPageNumber = (int) (Math.ceil(((double) this.countResults) / ((double) pageSize)));
        Query query = getCurrentSession()
                .createQuery(String.valueOf(paramSunQuery));
        query.setParameter("engineTypeParam", request.getEngineType() != null ? ("%" + request.getEngineType().toUpperCase() + "%") : null);
        query.setParameter("autoManufParam", request.getAutoManufacture() != null ? ("%" + request.getAutoManufacture().toUpperCase() + "%") : null);
        query.setParameter("autoModelParam", request.getModelName() != null ? ("%" + request.getModelName().toUpperCase() + "%") : null);
        query.setParameter("fuelTypeParam", request.getFuelType() != null ? ("%" + request.getFuelType().toUpperCase() + "%") : null);
        query.setParameter("cylinderPlace", request.getCylinderPlace() != null ? ("%" + request.getCylinderPlace().toUpperCase() + "%") : null);
        query.setParameter("cylinderNum", request.getCylinderNum());
        query.setParameter("pistonDiameter", request.getPistonDiameter());
        query.setParameter("superchargedType", request.getSuperchargedType() != null ? ("%" + request.getSuperchargedType().toUpperCase() + "%") : null);
        query.setParameter("degreeCompression", request.getDegreeCompression());
        query.setParameter("engineCapParam", request.getEngineCapacity());
        query.setParameter("powerKwtParam", request.getPowerKWT() != null ? ("%" + request.getPowerKWT().toUpperCase() + "%") : null);
        query.setParameter("releaseYearF", year);
        query.setParameter("releaseYear", request.getReleaseYear() != null && year == null ? ("%" + request.getReleaseYear().toUpperCase() + "%") : null);
        return (Long) query.uniqueResult();
    }

    @Override
    public Integer getMaxId() {
        return (Integer) getCurrentSession()
                .createQuery("select MAX(pn.id) from AutomobileEngine pn").list().get(0);
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
