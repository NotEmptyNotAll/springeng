package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Payload.Request.ParamsRequest;

import java.util.List;

public interface ElementsDao extends Dao<Elements> {
    List<AutomobileEngine> findParentsElemByParam(ParamsRequest paramsRequest,Integer searchPercent) throws ClassCastException;

    List<Integer> getElemFkListByAutoId(Integer autoId);

    Elements findByParentIdAndParamFk(Integer paramFk, Integer parentId);

    List<Elements> getAllNodeOfTree();

    List<Elements> getElementByParentId(Integer id);

    Integer getMaxId();

    List<Elements> getAllRootElemByAutoId();

}
