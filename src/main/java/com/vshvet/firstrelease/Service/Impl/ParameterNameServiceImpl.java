package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Service.ParameterNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParameterNameServiceImpl implements ParameterNameService {

    @Autowired
    private Dao parameterNameDao;

    //get a list of parameter names and create an answer from them
    @Override
    public List<String> getAllNames() {
        parameterNameDao.openCurrentSessionwithTransaction();
        List<String> responses = new ArrayList<String>() {{
            parameterNameDao.getAll().forEach(parameterNames -> {
                add(((ParameterNames) parameterNames).getFullName());
            });
        }};
        parameterNameDao.closeCurrentSessionwithTransaction();
        return responses;
    }

}
