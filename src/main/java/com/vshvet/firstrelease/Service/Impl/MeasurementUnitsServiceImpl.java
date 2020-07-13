package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.MeasurementUnitsDao;
import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Entity.Status;
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

@Service
public class MeasurementUnitsServiceImpl implements MeasurementUnitsService {

    @Autowired
    private MeasurementUnitsDao measurementUnitsDao;

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
