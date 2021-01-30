package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.*;

import java.util.List;

public interface AutomobileEngineService {
    Boolean save(FastAutoEngineSaveOrUpdateRequest saveData);

    AutomobileEngine findByNames(String autoModel, String engineType, String autoManuf, String years);

    Boolean fastSaveAutoData(FastAutoEngineSaveOrUpdateRequest parametersPageRequest);

    Boolean fastUpdateAutoData(FastAutoEngineSaveOrUpdateRequest parametersPageRequest);

    void save(AutomobileEngine automobileEngine);

    List<DataByIdResponse> delete(Integer id);

    List<AutoDataResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);

    Integer getNumberOfPageByParam(ParametersPageRequest request,List<AutomobileEngine> automobileEngines);

    AutoEngineMapByParamResponse getAllAutoEngAndParam(ParametersPageRequest request);

    List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest);

    List<AutoEngineResponse> findByParamForUpdate(EngineRequest engineRequest);

    Boolean update(List<UpdateAutoEngineRequest> updateData);


    String save(SaveAutoEngineRequest saveData);

    String getNameAuto(Integer id);

    AutomobileEngine findById(Integer id);

    AutoEngineResponse getById(Integer id);

    List<AutomobileEngine> getAllAutoEngine();

    List<AutoDataResponse> getAllAuto();

    void imprt(ImprtAutoEngineRequest imprtData);

}
