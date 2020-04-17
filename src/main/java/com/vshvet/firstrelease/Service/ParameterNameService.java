package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.ParameterNameDao;
import com.vshvet.firstrelease.payload.Response.ParamNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParameterNameService {

    @Autowired
    private ParameterNameDao parameterNameDao;

    //get a list of parameter names and create an answer from them
    public List<String> getAllNames(){
        parameterNameDao.openCurrentSessionwithTransaction();
        List<String> responses= new ArrayList<String>(){{
            parameterNameDao.getAll().forEach(parameterNames -> {
                add(parameterNames.getFullName());
            });
        }};
        parameterNameDao.closeCurrentSessionwithTransaction();
        return responses;
    }

}
