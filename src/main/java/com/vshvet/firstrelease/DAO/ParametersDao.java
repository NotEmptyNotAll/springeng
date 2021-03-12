package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Parameters;

import java.util.List;

public interface ParametersDao extends Dao<Parameters> {

    Parameters findParamByElemId(Integer id);

    List<Parameters> findAllTranslate(Integer id);

    Parameters findParamByAutoAndElemId(Integer elemId, Integer autoId,Integer langId);

    List<Parameters> getParamByAutoId(Integer autoId);

    List<Parameters> getParamTranslateByElemIdAndAutoId(Integer elemId, Integer autoId);

    List<Parameters> findParamByAutoAndElemIdList(Integer autoId,Integer elemFk);

}
