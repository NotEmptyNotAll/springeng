package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.DAO.ParametersDao;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametrsService {

    @Autowired
    private ParametersDao parametersDao;

    @Autowired
    private ElementsDao elementsDao;

    //get a list of parameters and create an answer from them
    public List<ParametersResponse> getParamByIdElem(Integer id) {
        elementsDao.openCurrentSessionwithTransaction();
        List<ParametersResponse> responses = null;
        try {
            responses = new ArrayList<ParametersResponse>() {{
                elementsDao.findById(id).get().getChildElements().forEach(
                        elements -> {
                            if (elements.getParametersByElemId().size() != 0)
                                add(new ParametersResponse(elements
                                        .getParametersByElemId().get(0)));
                        }
                );

            }};
            elementsDao.closeCurrentSessionwithTransaction();
        } catch (NullPointerException e) {
            elementsDao.closeCurrentSessionwithTransaction();
        }
        return responses;
    }


}
