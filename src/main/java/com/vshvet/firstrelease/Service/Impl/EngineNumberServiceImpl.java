package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.EngineNumberDao;
import com.vshvet.firstrelease.Service.EngineNumberService;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.EngineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineNumberServiceImpl implements EngineNumberService {

    @Autowired
    private EngineNumberDao engineNumberDao;


    @Override
    public List<String> getAllNumber() {
        engineNumberDao.openCurrentSessionwithTransaction();
        List<String> list = engineNumberDao.getAllName();
        engineNumberDao.closeCurrentSessionwithTransaction();
        return list;
    }

    @Override
    public EngineResponse getAutoEngByNumber(EngineRequest engineRequest) {
        engineNumberDao.openCurrentSessionwithTransaction();
        EngineResponse automobileEngineResponse = new EngineResponse(engineNumberDao
                .getAutoEngByNum(engineRequest.getNumberEng()));
        engineNumberDao.closeCurrentSessionwithTransaction();
        return automobileEngineResponse;
    }


}
