package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.MeasurementUnitsDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.MeasurementUnitsService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.MeasurementUnitsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementUnitsServiceImpl implements MeasurementUnitsService {

    @Autowired
    private MeasurementUnitsDao measurementUnitsDao;

    @Override
    @Transactional
    public List<DataByIdResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<DataByIdResponse>(){{
            measurementUnitsDao.getPagination(request).forEach(item->{
                add(new DataByIdResponse(item.getFullNameM(),item.getShortNameM(),item.getId(),item.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(measurementUnitsDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));

    }

    // get the units of calculation and create an answer from them
    @Override
    @Transactional
    public List<DataByIdResponse> getAllUnits() {
        List<DataByIdResponse> responses = new ArrayList<DataByIdResponse>() {{
            measurementUnitsDao.getAll().forEach(units -> {
                add(new DataByIdResponse(units.getFullNameM(), units.getShortNameM(),
                        units.getId(), units.getStatus().getStatus()));
            });
        }};
        return responses;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        MeasurementUnits measurementUnits = measurementUnitsDao.findById(id).get();
        List<Parameters> engines= measurementUnits.getParametersById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (engines.size()==0) {
            this.measurementUnitsDao.delete(measurementUnits);
        return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                engines.forEach(elem ->
                        add(new DataByIdResponse(elem.getElementsByElemFk().getParameterNamesByParamNameFk().getName(), elem.getParamId())));
            }};
        }
    }

    @Override
    @Transactional
    public Boolean update(UpdateTwoDataRequest updateData) {
        try {
            MeasurementUnits newMeasurementUnits = measurementUnitsDao.findById(updateData.getObjToBeChanged()).get();
            MeasurementUnits oldMeasurementUnits = new MeasurementUnits(newMeasurementUnits);
            newMeasurementUnits.setStatus(new Status(updateData.getStatus()));
            newMeasurementUnits.setFullNameM(updateData.getSaveData_primary());
            newMeasurementUnits.setShortNameM(updateData.getSaveData_secondary());
            oldMeasurementUnits.setDate(new Date(new java.util.Date().getTime()));
            measurementUnitsDao.update(newMeasurementUnits, oldMeasurementUnits);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public String save(SaveTwoDataRequest saveData) {
        try {
            MeasurementUnits measurementUnits = new MeasurementUnits();
            //measurementUnits.setFullNameA(saveData.getFullNameA());
            //measurementUnits.setFullNameM(saveData.getFullNameM());
            //measurementUnits.setShortNameA(saveData.getShortNameA());
            //measurementUnits.setShortNameM(saveData.getShortNameM());
            measurementUnits.setStatus(new Status(saveData.getStatus()));
            measurementUnits.setFullNameM(saveData.getSaveData_primary());
            measurementUnits.setShortNameM(saveData.getSaveData_secondary());
            measurementUnitsDao.save(measurementUnits);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    public void imprt(ImprtTwoDataRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }
}
