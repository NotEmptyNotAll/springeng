package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Impl.ElementsDaoImpl;
import com.vshvet.firstrelease.DAO.ParametersDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.Service.ParametrsService;
import com.vshvet.firstrelease.payload.Request.SaveOrUpdateElementsRequest;
import com.vshvet.firstrelease.payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.ParamSizeNameResponse;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.*;

@Service
public class ParametrsServiceImpl implements ParametrsService {

    @Autowired
    private ParametersDao parametersDao;

    @Autowired
    private ElementsDaoImpl elementsDao;

    @Autowired
    private ElementsService elementsService;

    //get a list of parameters and create an answer from them
    @Override
    @Transactional(readOnly = true)
    public List<ParametersResponse> getParamByIdElem(Integer id, Integer auto_id) {
        List<ParametersResponse> responses = null;
        try {
            responses = new ArrayList<ParametersResponse>() {{
                elementsDao.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("id : " + id))
                        .getChildElements().forEach(
                        elements -> {
                                if (elements.getParametersByElemId().size() > 0) {
                                    elements.getParametersByElemId().forEach(
                                            param -> {
                                                if (param.getAutoId() == auto_id && param.getDate() == null)
                                                    add(new ParametersResponse(param));
                                            });
                                }else {
                                    add(new ParametersResponse(elements.getParamNameFk()));
                                }
                        }
                );
            }};

        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return responses;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        Parameters parameters = parametersDao.findById(id).get();
        this.parametersDao.delete(parameters);
        return null;

    }

    @Override
    @Transactional
    public void save(Parameters parameter) {
        try {
            parametersDao.save(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getParamMap(AutomobileEngine automobileEngine) {
        return new HashMap<String, Object>() {{
            automobileEngine.getParametersList().forEach(parameter -> {
                String temp = getValueParam(parameter);
                List<FileStorage> tempList = parameter.getElementsByElemFk().getParentElements().getFileStorages();
                if (tempList.size() > 0 ) {
                    put("listImage" + parameter.getElementsByElemFk().getElemId(), getFileUrl(tempList));
                }
                if (!temp.equals("")) {
                    put(Integer.toString(parameter.getElementsByElemFk().getElemId()), temp);
                }
            });

        }};
    }

    @Transactional(readOnly = true)
    public List<String> getFileUrl(List<FileStorage> list) {
        return new ArrayList<String>() {{
            list.forEach(elem -> {
                add(elem.getFileUrl());
            });
        }};
    }

    @Transactional(readOnly = true)
    String getValueParam(Parameters parameter) {
        if (parameter.getDoubleMin() != null) {
            return parameter.getDoubleMin() + " - " + parameter.getDoubleMax();
        } else if (parameter.getDoubleNum() != null) {
            return parameter.getDoubleNum().toString();
        } else if (parameter.getTextData() != null) {
            return parameter.getTextData();
        } else {
            return "";
        }
    }


    @Override
    @Transactional
    public Boolean update(List<SaveOrUpdateParametersRequest> updateData) {
        try {
            updateData.forEach(param -> {
                Parameters newParameters = parametersDao.findById(param.getId()).get();
                Parameters oldParameters = new Parameters(newParameters);
                newParameters.setStatus(new Status(param.getStatus()));
                newParameters.setAuthor(param.getAuthor());
                newParameters.setDoubleMax(param.getDoubleMax());
                newParameters.setDoubleMin(param.getDoubleMin());
                newParameters.setDoubleNum(param.getDoubleNum());
                newParameters.setLogic(param.getLogic());
                newParameters.setMeasurementUnitsByMeasurementUnitsFk(new MeasurementUnits(param.getUnits()));
                newParameters.setRecordStatus(param.getRecordStatus());
                newParameters.setSource(param.getSource());
                oldParameters.setDate(new Date(new java.util.Date().getTime()));
                newParameters.setTextData(param.getTextData());
                Optional<Elements> tempElements = this.elementsDao
                        .findById(param.getElemId()).get()
                        .getChildElements().stream()
                        .filter(e -> {
                            return e.getParamNameFk().equals(param.getName());
                        }).findFirst();
                if (!tempElements.isPresent()) {
                    elementsService.update(new SaveOrUpdateElementsRequest(
                            oldParameters.getElemFk(),
                            param.getName()
                    ));
                } else {
                    newParameters.setElementsByElemFk(
                            new Elements(tempElements.get().getElemId()));
                }
                try {
                    parametersDao.update(newParameters, oldParameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public String save(List<SaveOrUpdateParametersRequest> saveData) {
        try {
            saveData.forEach(param -> {
                Optional<Elements> tempElements = this.elementsDao
                        .findById(param.getElemId()).get()
                        .getChildElements().stream()
                        .filter(e -> {
                            return e.getParamNameFk().equals(param.getName());
                        }).findFirst();
                Parameters parameters = new Parameters();
                parameters.setStatus(new Status(param.getStatus()));
                parameters.setAuthor(param.getAuthor());
                parameters.setDoubleMax(param.getDoubleMax());
                parameters.setDoubleMin(param.getDoubleMin());
                parameters.setDoubleNum(param.getDoubleNum());
                parameters.setAutoId(param.getAuto_id());
                parameters.setLogic(param.getLogic());
                parameters.setMeasurementUnitsByMeasurementUnitsFk(
                        new MeasurementUnits(param.getUnits()));
                parameters.setRecordStatus(param.getRecordStatus());
                parameters.setSource(param.getSource());
                parameters.setTextData(param.getTextData());
                if (!tempElements.isPresent()) {
                    elementsService.save(new SaveOrUpdateElementsRequest(
                            param.getNameElemId(),
                            param.getName(),
                            param.getElemId()
                    ));
                    parameters.setElementsByElemFk(
                            new Elements(param.getNameElemId()));
                } else {
                    parameters.setElementsByElemFk(
                            new Elements(tempElements.get().getElemId()));
                }
                try {
                    parametersDao.save(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }


}
