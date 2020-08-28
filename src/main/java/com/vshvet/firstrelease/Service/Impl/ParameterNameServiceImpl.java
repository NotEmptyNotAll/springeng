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
import com.vshvet.firstrelease.payload.Response.ParamNameResponse;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<DataByIdResponse> delete(Integer id) {
        ParameterNames parameterNames = parameterNameDao.findById(id).get();
        List<Elements> elements= parameterNames.getElementsById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (elements.size()==0) {
            this.parameterNameDao.delete(parameterNames);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                elements.forEach(elem ->
                        add(new DataByIdResponse(elem.getParentElements().getParameterNamesByParamNameFk().getName(),
                                elem.getParentElements().getElemId())));
            }};
        }
    }

    @Override
    @Transactional
    public Integer getMaxId() {
        Integer maxId = parameterNameDao.getMaxId();
        return maxId;
    }

    @Override
    public void save(ParameterNames parameterNames) {
        try {
            parameterNameDao.save(parameterNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ParameterNames findByName(String name) {
        return parameterNameDao.findByName(name);
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
        return new ArrayList<DataByIdResponse>() {{
            parameterNameDao.getAllTreeRootName().forEach(elem ->
                    add(new DataByIdResponse(elem.getFullName(), elem.getName(),
                            elem.getId(), elem.getStatus().getStatus()))
            );
        }};
    }

    @Override
    public List<DataByIdResponse> getAllParameterSizeName() {
        return new ArrayList<DataByIdResponse>() {{
            parameterNameDao.getAllParameterSizeName().forEach(elem ->
                    add(new DataByIdResponse(elem.getFullName(), elem.getName(),
                            elem.getId(), elem.getStatus().getStatus()))
            );
        }};
    }

    @Override
    @Transactional
    public List<ParameterNames> getAllParametersName() {
        return parameterNameDao.getAll();
    }

    @Override
    @Transactional
    public String save(SaveTwoDataRequest saveData) {
        try {
            ParameterNames parameterNames = new ParameterNames();
            parameterNames.setStatus(new Status(saveData.getStatus()));
            parameterNames.setName(saveData.getSaveData_primary());
            parameterNames.setFullName(saveData.getSaveData_secondary());
            parameterNames.setTreeRoot(true);
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
