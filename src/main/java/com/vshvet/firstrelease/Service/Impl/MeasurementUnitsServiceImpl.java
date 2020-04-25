package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.MeasurementUnitsDao;
import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Service.MeasurementUnitsService;
import com.vshvet.firstrelease.payload.Response.MeasurementUnitsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementUnitsServiceImpl implements MeasurementUnitsService {

    @Autowired
    private MeasurementUnitsDao measurementUnitsDao;

    // get the units of calculation and create an answer from them
    @Override
    public List<MeasurementUnitsResponse> getAllUnits() {
        measurementUnitsDao.openCurrentSessionwithTransaction();
        List<MeasurementUnitsResponse> responses = new ArrayList<MeasurementUnitsResponse>() {{
            measurementUnitsDao.getAll().forEach(units -> {
                add(new MeasurementUnitsResponse((MeasurementUnits) units));
            });
        }};
        measurementUnitsDao.closeCurrentSessionwithTransaction();
        return responses;
    }
}
