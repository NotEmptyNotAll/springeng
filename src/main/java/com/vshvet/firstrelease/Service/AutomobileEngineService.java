package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AutomobileEngineService {

    @Autowired
    private AutomobileEngineDao automobileEngineDao;

    @Autowired
    private ElementsService elementsService;

    //This service contains a list of auto engines
    // to return the result to the user.
    public List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest) {
        List<AutomobileEngineResponse> responses = null;
        Set<Integer> setElemId = elementsService.getParentElem(engineRequest);
        automobileEngineDao.openCurrentSessionwithTransaction();
        List<AutomobileEngine> listEng = automobileEngineDao.getAutoByParam(engineRequest);
        if (!(listEng.size() > 10)) {
            responses = new ArrayList<AutomobileEngineResponse>() {{
                listEng.forEach(automobileEngine -> {
                    add(new AutomobileEngineResponse(automobileEngine));
                });
            }};
            automobileEngineDao.closeCurrentSessionwithTransaction();
            //check if there is a request for special parameters
            // that were measured by the user
            if (engineRequest.getParamList().get(0).getParameterNumber() != null) {
               responses= responses.stream().filter(rsp -> setElemId.contains(rsp.getElemID())).collect(Collectors.toList());//filter car engines by element id
            }
        } else {
            automobileEngineDao.closeCurrentSessionwithTransaction();
        }
        return responses;


    }


}
