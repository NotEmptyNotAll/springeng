package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Dao;
import com.vshvet.firstrelease.DAO.ParameterNameDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.Service.ParameterNameService;
import com.vshvet.firstrelease.Util.HSessionFactoryUtil;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.ParamNameNodeResponse;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ParameterNameServiceImpl implements ParameterNameService {

    @Autowired
    private ParameterNameDao parameterNameDao;

    @Autowired
    private ElementsService elementsService;

    //get a list of parameter names and create an answer from them
    @Override
    @Transactional
    public List<ParamNameNodeResponse> getAllNames() {
        Set<Elements> elements = new TreeSet<>(elementsService.getAllNodeOfTree());
        return new ArrayList<ParamNameNodeResponse>() {{
            for (Elements elem :
                    elements) {
                add(new ParamNameNodeResponse(elem));
            }

        }};
    }

    @Override
    @Transactional
    public Integer getMaxId() {
        Integer maxId = parameterNameDao.getMaxId();
        return maxId;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            parameterNameDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getFullName(), elem.getName(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
        return all;
    }

    @Override
    @Transactional
    public Boolean update(UpdateTwoDataRequest updateData) {

        try {
            ParameterNames newParameterNames = parameterNameDao.findById(updateData.getObjToBeChanged()).get();
            ParameterNames oldParameterNames = new ParameterNames(newParameterNames);
            newParameterNames.setStatus(new Status(updateData.getStatus()));
            newParameterNames.setName(updateData.getSaveData_primary());
            newParameterNames.setFullName(updateData.getSaveData_secondary());
            oldParameterNames.setDateCreate(new java.sql.Date(new java.util.Date().getTime()));
            parameterNameDao.update(newParameterNames, oldParameterNames);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getAllTreeRootName() {
        List<DataByIdResponse> list = new ArrayList<DataByIdResponse>() {{
            parameterNameDao.getAllTreeRootName().forEach(elem ->
                    add(new DataByIdResponse(elem.getFullName(), elem.getName(),
                            elem.getId(), elem.getStatus().getStatus()))
            );
        }};
        return list;
    }

    @Override
    @Transactional
    public List<ParameterNames> getAllParametersName() {
        List all = parameterNameDao.getAll();
        return all;
    }

    @Override
    @Transactional
    public String save(SaveTwoDataRequest saveData) {
        try {
            ParameterNames parameterNames = new ParameterNames();
            parameterNames.setStatus(new Status(saveData.getStatus()));
            parameterNames.setName(saveData.getSaveData_primary());
            parameterNames.setFullName(saveData.getSaveData_secondary());
            parameterNames.setTreeRoot(false);
            parameterNameDao.save(parameterNames);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public String save(SaveParameterNameRequest saveData) {
        try {
            ParameterNames parameterNames = new ParameterNames();
            parameterNames.setStatus(new Status(2));
            parameterNames.setName(saveData.getName());
            parameterNames.setFullName(saveData.getFullName());
            parameterNames.setTreeRoot(saveData.isRoot());
            parameterNameDao.save(parameterNames);
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
