package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.ConstValue;
import com.vshvet.firstrelease.DAO.Impl.ElementsDaoImpl;
import com.vshvet.firstrelease.DAO.ParametersDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Payload.Request.FastParamSaveRequest;
import com.vshvet.firstrelease.Payload.Response.TranslateParamResponse;
import com.vshvet.firstrelease.Payload.Response.TwoDataByIdResponse;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.Service.ParametrsService;
import com.vshvet.firstrelease.Payload.Request.SaveOrUpdateElementsRequest;
import com.vshvet.firstrelease.Payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.Payload.Response.ParametersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                            } else {
                                //  add(new ParametersResponse(elements.getParamNameFk()));
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
    @Transactional
    public Boolean fastSave(FastParamSaveRequest saveData) {
        saveData.getSaveList().forEach(param -> {
            param.setUserFk(saveData.getUserId());
            if (param.getId() != 1) {
                updateParam(param);
            } else {
                saveParam(param);
            }
        });
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getParamMap(AutomobileEngine automobileEngine, Integer langId) {
        List<Parameters> parametersList = automobileEngine.getParametersList();
        HashMap<String, Object> map = new HashMap<String, Object>();
        parametersList.forEach(parameter -> {
            if(parameter.getElemFk()==31650 && parameter.getAutoId()==5 && parameter.getLanguageFk()==1){
                System.out.println(parameter.getDoubleMax() );
            }
            if ((parameter.getLanguageFk() == 0 || parameter.getLanguageFk() == langId) && parameter.getDate()==null ) {
                map.remove(Integer.toString(parameter.getElementsByElemFk().getElemId()) );
                this.addParamToMap(map, parameter);
            } else if (parameter.getLanguageFk() == ConstValue.DEFAULT_ID_LANGUAGE
                    && !map.containsKey(Integer.toString(parameter.getElementsByElemFk().getElemId()))) {
                this.addParamToMap(map, parameter);
            }
        });
        return map;
    }

    @Override
    public List<TranslateParamResponse>  getParamTranslateById(Integer id, Integer autoId) {
        return new ArrayList<TranslateParamResponse>(){{
            parametersDao.getParamTranslateByElemIdAndAutoId(id,autoId).forEach(elem->{
                add(new TranslateParamResponse(elem.getTextData(),elem.getParamId(),elem.getLanguageFk()));
            });
        }};
    }
//
//    @Override
//    public HashMap<String, Object> getParamTranslateById(Integer autoId,Integer elemFk) {
//        // Parameters parameters = parametersDao.findById(id).get();
//       // Elements elements = elementsDao.findById(id).get();
//       List<Parameters> list= parametersDao.findParamByAutoAndElemIdList(autoId,elemFk);
//        return new HashMap<String, Object>() {{
//            list.forEach(param -> {
//                String temp = getValueParam(param);
//                if (!temp.equals("")) {
//                    put(String.valueOf(param.getLanguageFk()), temp);
//                }
//            });
//        }};
//    }

    private void addParamToMap(HashMap<String, Object> map, Parameters parameter) {
        if (parameter.getDate() == null) {
            String temp = getValueParam(parameter);
              /*
                if (parameter.getElementsByElemFk().getParentElements().getFileStorages() == null) {
                    List<FileStorage> tempList = parameter.getElementsByElemFk().getParentElements().getFileStorages();
                    if (tempList.size() > 0) {
                        put("listImage" + parameter.getElementsByElemFk().getElemId(), getFileUrl(tempList));
                    }
                }*/
            if (!temp.equals("")) {
                map.put(Integer.toString(parameter.getElementsByElemFk().getElemId()), temp);
            }
        }
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
            return String.format("%.3f", parameter.getDoubleMin()) + " - " + String.format("%.3f", parameter.getDoubleMax());
        } else if (parameter.getDoubleNum() != null) {
            return String.format("%.3f", parameter.getDoubleNum());
        } else if (parameter.getTextData() != null) {
            return parameter.getTextData();
        } else {
            return "";
        }
    }

    @Transactional
    Boolean updateParam(SaveOrUpdateParametersRequest param) {
        Parameters newParameters = parametersDao.findParamByAutoAndElemId(param.getElemId(), param.getAuto_id(),param.getLangId());
        Parameters oldParameters = new Parameters(newParameters);
        newParameters.setStatus(new Status(param.getStatus()));
        newParameters.setAuthor(param.getAuthor());
        newParameters.setDoubleMax(param.getDoubleMax());
        newParameters.setDoubleMin(param.getDoubleMin());
        newParameters.setLanguageFk(param.getLangId());
        newParameters.setUserFk(param.getUserFk());
        newParameters.setDoubleNum(param.getDoubleNum());
        newParameters.setLogic(param.getLogic());
        newParameters.setMeasurementUnitsByMeasurementUnitsFk(new MeasurementUnits(param.getUnits()));
        oldParameters.setMeasurementUnitsByMeasurementUnitsFk(new MeasurementUnits(5));
        oldParameters.setLanguageFk(newParameters.getLanguageFk());
        newParameters.setRecordStatus(param.getRecordStatus());
        newParameters.setSource(param.getSource());
        oldParameters.setDate(new Date(new java.util.Date().getTime()));
        newParameters.setTextData(param.getTextData());
        Optional<Elements> tempElements = this.elementsDao
                .findById(param.getElemId());
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
            return false;
        }
        return true;
    }

    private Boolean saveParam(SaveOrUpdateParametersRequest param) {
        Parameters parameters = new Parameters();
        parameters.setStatus(new Status(param.getStatus()));
        parameters.setAuthor(param.getAuthor());
        parameters.setDoubleMax(param.getDoubleMax());
        parameters.setDoubleMin(param.getDoubleMin());
        parameters.setDoubleNum(param.getDoubleNum());
        parameters.setUserFk(param.getUserFk());
        if (param.getTextData() == null) {
            parameters.setLanguageFk(param.getLangId());
        } else {
            parameters.setLanguageFk(param.getLangId());
        }
        parameters.setAutoId(param.getAuto_id());
        parameters.setLogic(param.getLogic());
        parameters.setMeasurementUnitsByMeasurementUnitsFk(
                new MeasurementUnits(param.getUnits()));
        parameters.setRecordStatus(param.getRecordStatus());
        parameters.setSource(param.getSource());
        parameters.setTextData(param.getTextData());

        parameters.setElementsByElemFk(
                new Elements(elementsDao.findById(param.getElemId()).get()));
        parameters.setElemFk(param.getElemId());
        try {
            parametersDao.save(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    @Transactional
    public Boolean update(List<SaveOrUpdateParametersRequest> updateData) {
        try {
            updateData.forEach(param -> {
                updateParam(param);
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
                saveParam(param);
            });
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }


}
