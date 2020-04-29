package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Service.AutomobileEngineService;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AutomobileEngineServiceImpl implements AutomobileEngineService {

    @Autowired
    private AutomobileEngineDao automobileEngineDao;

    @Autowired
    private ElementsService elementsService;


    //This service contains a list of auto engines
    // to return the result to the user.
    @Override
    public List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest) {
        List<AutomobileEngineResponse> responses = null;
        if (!engineRequest.findOnlyByParam()) {
            Set<Integer> setElemId = elementsService.getParentElemId(engineRequest);
            automobileEngineDao.openCurrentSessionwithTransaction();
            List<AutomobileEngine> listEng = automobileEngineDao.getAutoByParam(engineRequest);
            if (!(listEng.size() > 10)) {   //check load limit for engines
                responses = new ArrayList<AutomobileEngineResponse>() {{
                    listEng.forEach(automobileEngine -> {
                        add(new AutomobileEngineResponse(automobileEngine));
                    });
                }};
                automobileEngineDao.closeCurrentSessionwithTransaction();
                //check if there is a request for special parameters
                // that were measured by the user
                if (setElemId.size()!=0) {
                    responses = responses.stream()
                            .filter(rsp -> setElemId.contains(rsp.getElemID()))
                            .collect(Collectors.toList());//filter car engines by element id
                }
            } else {
                automobileEngineDao.closeCurrentSessionwithTransaction();
            }
        } else {
            responses = elementsService.getParentElements(engineRequest);
        }
        return responses;
    }


}
