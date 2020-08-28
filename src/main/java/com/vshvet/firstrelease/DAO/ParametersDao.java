package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.payload.Response.ParamSizeNameResponse;

import java.util.List;

public interface ParametersDao extends Dao<Parameters> {

    Parameters findParamByElemId(Integer id);


     List<Parameters> getParamByAutoId(Integer autoId);

}
