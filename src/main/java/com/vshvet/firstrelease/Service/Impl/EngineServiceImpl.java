package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.CylindersDao;
import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.EngineService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.EngineDataResponse;
import com.vshvet.firstrelease.payload.Response.EngineResponse;
import com.vshvet.firstrelease.payload.Response.FullEngineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineDao engineDao;

    @Autowired
    private CylindersDao cylindersDao;

    @Override
    @Transactional
    public Engine findById(int id) {
        Engine engine = engineDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("id : " + id));
        //engineDao.getByParam(new EngineRequest(engine));
        return engine;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        Engine engine = engineDao.findById(id).get();
        List<AutomobileEngine> automobileEngines= engine.getAutomobileEnginesById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (automobileEngines.size()==0) {
            this.engineDao.delete(engine);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                automobileEngines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineByEngineFk().getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    @Transactional
    public List<EngineDataResponse> getDataByIdResponse() {
        List<EngineDataResponse> all = new ArrayList<EngineDataResponse>() {{
            engineDao.getAll().forEach(elem -> {
                add(new EngineDataResponse(
                        elem.getId(),
                        elem.getEngineType(),
                        elem.getEngineManufacturerByEngineManufacturerFk().getNameManufacturer(),
                        elem.getCylindersByCylindersPlacementFk().getTypeName(),
                        elem.getFuelTypeByFuelTypeFk().getNameType(),
                        elem.getSuperchargedTypeBySuperchargedTypeFk().getNameType(),
                        elem.getCylindersNumber(),
                        elem.getFlapNumber(),
                        elem.getPistonDiameter(),
                        elem.getPistonStroke(),
                        elem.getEngineCapacity(),
                        elem.getPowerKwt(),
                        elem.getHorsepower(),
                        elem.getDegreeCompression(),
                        elem.getReleaseYearFrom(),
                        elem.getReleaseYearBy(),
                        elem.getStatus().getStatus()
                ));
            });
        }};
        return all;
    }

    @Override
    @Transactional
    public List<String> getAllType() {
        List<String> engines = engineDao.getAllType();
        return engines;
    }

    @Override
    public Engine findByName(String name) {
        return engineDao.findByName(name);
    }

    @Override
    @Transactional
    public List<EngineDataResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<EngineDataResponse>(){{
            engineDao.getPaginationData(request).forEach(elem->{
                add(new EngineDataResponse(
                        elem.getId(),
                        elem.getEngineType(),
                        elem.getEngineManufacturerByEngineManufacturerFk().getNameManufacturer(),
                        elem.getCylindersByCylindersPlacementFk().getTypeName(),
                        elem.getFuelTypeByFuelTypeFk().getNameType(),
                        elem.getSuperchargedTypeBySuperchargedTypeFk().getNameType(),
                        elem.getCylindersNumber(),
                        elem.getFlapNumber(),
                        elem.getPistonDiameter(),
                        elem.getPistonStroke(),
                        elem.getEngineCapacity(),
                        elem.getPowerKwt(),
                        elem.getHorsepower(),
                        elem.getDegreeCompression(),
                        elem.getReleaseYearFrom(),
                        elem.getReleaseYearBy(),
                        elem.getStatus().getStatus()
                ));
            });
        }};    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(engineDao
                .getNumberOfPage(request)) / Double.valueOf(request.getPageSize()));
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getCroppedData(EngineRequest engineRequest) {
        return new ArrayList<DataByIdResponse>() {{
            engineDao.getCroppedType(engineRequest).forEach(elem -> {
                add(new DataByIdResponse(elem.getEngineType(),
                        elem.getId()));
            });
        }};
    }

    @Override
    @Transactional
    public String save(SaveOrUpdateEngineRequest saveData) {
        try {

            Engine engine = new Engine();
            //engine.setStatus(new Status(saveData.getStatus()));
            engine.setStatus(new Status(1));
            engine.setCylindersByCylindersPlacementFk(new Cylinders(saveData.getCylindersPlacementFk()));
            engine.setCylindersNumber(saveData.getCylindersNumber());
            engine.setDegreeCompression(saveData.getDegreeCompression());
            engine.setEngineCapacity(saveData.getEngineCapacity());
            engine.setEngineType(saveData.getEngineType());
            engine.setEngineManufacturerByEngineManufacturerFk(new EngineManufacturer(saveData.getEngineManufacturerFk()));
            engine.setFlapNumber(saveData.getFlapNumber());
            engine.setFuelTypeByFuelTypeFk(new FuelType(saveData.getFuelTypeFk()));
            engine.setHorsepower(saveData.getHorsepower());
            engine.setPistonDiameter(saveData.getPistonDiameter());
            engine.setPistonStroke(saveData.getPistonStroke());
            engine.setPowerKwt(saveData.getPowerKwt());
            engine.setReleaseYearBy(saveData.getReleaseYearBy());
            engine.setReleaseYearFrom(saveData.getReleaseYearFrom());
            engine.setSuperchargedTypeBySuperchargedTypeFk(new SuperchargedType(saveData.getSuperchargedTypeFk()));
            engineDao.save(engine);
            return "збереження було успішним";
        } catch (Exception e) {

            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public List<Engine> getAll() {
        List<Engine> engines = engineDao.getAll();
        return engines;
    }

    @Override
    @Transactional
    public Boolean update(SaveOrUpdateEngineRequest updateData) {
        try {

            Engine newEngine = engineDao.findById(updateData.getObjToBeChanged()).get();
            Engine oldEngine = new Engine(newEngine);
            newEngine.setStatus(new Status(1));
            newEngine.setCylindersByCylindersPlacementFk(new Cylinders(updateData.getCylindersPlacementFk()));
            newEngine.setCylindersNumber(updateData.getCylindersNumber());
            newEngine.setDegreeCompression(updateData.getDegreeCompression());
            newEngine.setEngineCapacity(updateData.getEngineCapacity());
            newEngine.setEngineType(updateData.getEngineType());
            newEngine.setEngineManufacturerByEngineManufacturerFk(new EngineManufacturer(updateData.getEngineManufacturerFk()));
            newEngine.setFlapNumber(updateData.getFlapNumber());
            newEngine.setFuelTypeByFuelTypeFk(new FuelType(updateData.getFuelTypeFk()));
            newEngine.setHorsepower(updateData.getHorsepower());
            newEngine.setPistonDiameter(updateData.getPistonDiameter());
            newEngine.setPistonStroke(updateData.getPistonStroke());
            newEngine.setPowerKwt(updateData.getPowerKwt());
            newEngine.setReleaseYearBy(updateData.getReleaseYearBy());
            newEngine.setReleaseYearFrom(updateData.getReleaseYearFrom());
            newEngine.setSuperchargedTypeBySuperchargedTypeFk(new SuperchargedType(updateData.getSuperchargedTypeFk()));
            oldEngine.setDate(new Date(new java.util.Date().getTime()));
            engineDao.update(newEngine, oldEngine);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void update(Engine engine) {

    }

    @Override
    @Transactional
    public void save(Engine engine) throws Exception {
        if (engine != null) {
            engineDao.save(engine);
        }
    }

    @Override
    @Transactional
    public void delete(Engine engine) throws Exception {
        if (engine != null) {
            engineDao.delete(engine);
        }
    }

    @Override
    public FullEngineResponse getEngineResponse(Integer id) {
        return new FullEngineResponse(findById(id));
    }

    @Override
    public void imprt(ImprtOrUpdateEngineRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }


}
