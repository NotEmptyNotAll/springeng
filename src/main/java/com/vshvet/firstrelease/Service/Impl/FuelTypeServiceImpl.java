package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.FuelTypeDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.FuelTypeService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeDao fuelTypeDao;

    @Override
    @Transactional
    public List<String> getAllName() {
        List<String> autoModels = fuelTypeDao.getAllName();
        return autoModels;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<DataByIdResponse>(){{
            fuelTypeDao.getPagination(request).forEach(item->{
                add(new DataByIdResponse(item.getNameType(),item.getId(),item.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(fuelTypeDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));

    }

    @Override
    @Transactional
    public List<FuelType> getAllFuelType() {
        List<FuelType> autoModels = fuelTypeDao.getAll();
        return autoModels;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        FuelType fuelType = fuelTypeDao.findById(id).get();
        List<Engine> engines= fuelType.getEnginesById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (engines.size()==0) {
            this.fuelTypeDao.delete(fuelType);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                engines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    public FuelType findByName(String name) {
        return fuelTypeDao.findByName(name);
    }

    @Override
    public void save(FuelType fuelType) {
        try {
            fuelTypeDao.save(fuelType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getCroppedData(EngineRequest engineRequest) {
        return new ArrayList<DataByIdResponse>() {{
            fuelTypeDao.getCroppedByParamType(engineRequest).forEach(elem -> {
                add(new DataByIdResponse(elem.getNameType(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
    }

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {
            FuelType fuelType = new FuelType();
            fuelType.setStatus(new Status(saveData.getStatus()));
            fuelType.setNameType(saveData.getSaveData());
            fuelTypeDao.save(fuelType);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {
            FuelType newFuelType = fuelTypeDao.findById(updateData.getObjToBeChanged()).get();
            FuelType oldFuelType = new FuelType(newFuelType);
            newFuelType.setStatus(new Status(updateData.getStatus()));
            newFuelType.setNameType(updateData.getUpdateData());
            newFuelType.setId(updateData.getObjToBeChanged());
            oldFuelType.setDate(new Date(new java.util.Date().getTime()));
            fuelTypeDao.update(newFuelType, oldFuelType);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            fuelTypeDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getNameType(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
        return all;
    }

    @Override
    public void imprt(ImprtDataRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }

}
