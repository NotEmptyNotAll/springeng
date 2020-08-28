package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.*;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomobileEngineServiceImpl implements AutomobileEngineService {

    @Autowired
    private AutomobileEngineDao automobileEngineDao;

    @Autowired
    private ElementsService elementsService;


    @Autowired
    private ParametrsService parametrsService;


    @Autowired
    private AutoModelService autoModelService;


    @Autowired
    private EngineService engineService;

    @Override
    @Transactional
    public List<Map<String, Object>> getAllAutoEngAndParam(ParametersPageRequest request) {
        List<AutomobileEngine> autoListByParam = elementsService.getParentElements(request.getParamList());
        List<AutomobileEngine> autoeng = automobileEngineDao.getPaginationAutoEng(request);
        if (autoeng.size() > 0) {
            return new ArrayList<Map<String, Object>>() {{
                if(autoListByParam==null){
                    autoeng.forEach(elem -> {
                        add(new AutoEngAndParamResponse(elem, parametrsService.getParamMap(elem.getId())).getMapThisElem());
                    });
                }else if (autoListByParam.size() > 0) {
                    autoeng.stream()
                            .filter(autoListByParam::contains)
                            .forEach(elem -> {
                                add(new AutoEngAndParamResponse(elem, parametrsService.getParamMap(elem.getId())).getMapThisElem());
                            });
                }/* else {
                    autoeng.forEach(elem -> {
                        add(new AutoEngAndParamResponse(elem, parametrsService.getParamMap(elem.getId())).getMapThisElem());
                    });
                }*/
            }};
        } else {
            return new ArrayList<Map<String, Object>>() {{
                autoListByParam.forEach(elem -> {
                    add(new AutoEngAndParamResponse(elem, parametrsService.getParamMap(elem.getId())).getMapThisElem());
                });
            }};
        }
    }


    @Override
    public AutomobileEngine findByNames(String autoModel, String engineType, String autoManuf, String years) {
        return automobileEngineDao.findByNames(autoModel,engineType,autoManuf,years);
    }

    @Override
    public void save(AutomobileEngine automobileEngine) {
        try {
             automobileEngineDao.save(automobileEngine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        AutomobileEngine automobileEngine = automobileEngineDao.findById(id).get();
        this.automobileEngineDao.delete(automobileEngine);
        return null;
    }

    @Override
    @Transactional
    public Integer getNumberOfPage(ParametersPageRequest request) {
        return (int) Math.ceil(Double.valueOf(automobileEngineDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));
    }

    //This service contains a list of auto engines
    // to return the result to the user.
    @Override
    @Transactional
    public List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest) {
        List<AutomobileEngineResponse> responses = null;
        if (!engineRequest.findOnlyByParam()) {
            Set<Integer> setElemId = elementsService.getParentElemId(engineRequest.getParamList());
            List<AutomobileEngine> listEng = automobileEngineDao.getAutoByParam(engineRequest);
            if (!(listEng.size() > 10)) {   //check load limit for engines
                responses = new ArrayList<AutomobileEngineResponse>() {{
                    listEng.forEach(automobileEngine -> {
                        add(new AutomobileEngineResponse(automobileEngine));
                    });
                }};
                //check if there is a request for special parameters
                // that were measured by the user
                if (setElemId.size() != 0) {
                    responses = responses.stream()
                            .filter(rsp -> setElemId.contains(rsp.getId()))
                            .collect(Collectors.toList());//filter car engines by element id
                }
            }
        } else {
            responses = new ArrayList<AutomobileEngineResponse>() {{
                elementsService.getParentElements(engineRequest.getParamList()).forEach(automobileEngine -> {
                    add(new AutomobileEngineResponse(automobileEngine));
                });
            }};
        }
        return responses;
    }

    @Override
    @Transactional
    public List<AutoEngineResponse> findByParamForUpdate(EngineRequest engineRequest) {
        List<AutoEngineResponse> responses = null;
        if (!engineRequest.findOnlyByParam()) {
            Set<Integer> setElemId = elementsService.getParentElemId(engineRequest.getParamList());
            List<AutomobileEngine> listEng = automobileEngineDao.getAutoByParam(engineRequest);
            if (!(listEng.size() > 10)) {   //check load limit for engines
                responses = new ArrayList<AutoEngineResponse>() {{
                    listEng.forEach(automobileEngine -> {
                        add(new AutoEngineResponse(automobileEngine));
                    });
                }};
                //check if there is a request for special parameters
                // that were measured by the user
                if (setElemId.size() != 0) {
                    responses = responses.stream()
                            .filter(rsp -> setElemId.contains(rsp.getId()))
                            .collect(Collectors.toList());//filter car engines by element id
                }
            } else {

            }
        } else {
            responses = elementsService.getParentElementsUpdate(engineRequest);
        }
        return responses;
    }

    @Override
    @Transactional
    public Boolean update(List<UpdateAutoEngineRequest> updateData) {
        try {
            updateData.forEach(elem -> {
                AutomobileEngine newAuto = automobileEngineDao.findById(elem.getId()).get();
                AutomobileEngine oldAuto = new AutomobileEngine(newAuto);
                newAuto.setStatus(new Status(elem.getStatus()));
                newAuto.setReleaseYearFrom(elem.getReleaseYearFrom());
                newAuto.setReleaseYearBy(elem.getReleaseYearBy());
                newAuto.setAutoModelByAutoModelFk(autoModelService.findById(elem.getAutoModelFk()));
                newAuto.setEngineByEngineFk(engineService.findById(elem.getEngineFk()));
                newAuto.setAutoManufactureByAutoManufactureFk(new AutoManufacture(elem.getAutoManufactureFk()));
                oldAuto.setDate(new Date(new java.util.Date().getTime()));
                automobileEngineDao.update(newAuto, oldAuto);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public String save(SaveAutoEngineRequest saveData) {
        try {
            AutomobileEngine automobileEngine = new AutomobileEngine();
            automobileEngine.setEngineByEngineFk(new Engine(saveData.getEngineFk()));
            automobileEngine.setAutoManufactureByAutoManufactureFk(new AutoManufacture(saveData.getAutoManufactureFk()));
            automobileEngine.setAutoModelByAutoModelFk(new AutoModel(saveData.getAutoModelFk()));
            automobileEngine.setReleaseYearBy(saveData.getReleaseYearBy());
            automobileEngine.setReleaseYearFrom(saveData.getReleaseYearFrom());
            automobileEngine.setStatus(new Status(saveData.getStatus()));
            automobileEngineDao.save(automobileEngine);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public String getNameAuto(Integer id) {
        AutomobileEngine autoEng = automobileEngineDao.findById(id).get();
        return autoEng.getAutoManufactureByAutoManufactureFk().getManufactureName()
                + " mod." + autoEng.getAutoModelByAutoModelFk().getModelName() + " year "
                + (autoEng.getReleaseYearFrom() != null ? autoEng.getReleaseYearFrom().toString() : "") +
                "-" + (autoEng.getReleaseYearBy() != null ? autoEng.getReleaseYearBy().toString() : "");
    }

    @Override
    public AutomobileEngine findById(Integer id) {
        return automobileEngineDao.findById(id).get();
    }

    @Override
    public AutoEngineResponse getById(Integer id) {
        return new AutoEngineResponse(automobileEngineDao.findById(id).get());
    }

    @Override
    public List<AutomobileEngine> getAllAutoEngine() {
        return automobileEngineDao.getAll();
    }

    @Override
    public List<AutoDataResponse> getAllAuto() {
        return new ArrayList<AutoDataResponse>() {{
            automobileEngineDao.getAll().forEach(elem -> {
                add(new AutoDataResponse(elem.getId(),
                        elem.getAutoManufactureByAutoManufactureFk().getManufactureName(),
                        elem.getAutoModelByAutoModelFk().getModelName(),
                        elem.getEngineByEngineFk().getEngineType(),
                        elem.getReleaseYearFrom(),
                        elem.getReleaseYearBy(),
                        elem.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public void imprt(ImprtAutoEngineRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }

}
