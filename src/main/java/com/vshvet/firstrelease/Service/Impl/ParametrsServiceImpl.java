package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Impl.ElementsDaoImpl;
import com.vshvet.firstrelease.DAO.ParametersDao;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.ParametrsService;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametrsServiceImpl implements ParametrsService {

    @Autowired
    private ParametersDao parametersDao;

    @Autowired
    private ElementsDaoImpl elementsDao;

    //get a list of parameters and create an answer from them
    @Override
    public List<ParametersResponse> getParamByIdElem(Integer id) {
        elementsDao.openCurrentSessionwithTransaction();
        List<ParametersResponse> responses = null;
        try {
            responses = new ArrayList<ParametersResponse>() {{
                elementsDao.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("id : " + id))
                        .getChildElements().forEach(
                        elements -> {
                            if (elements.getParametersByElemId().size() != 0)
                                add(new ParametersResponse(elements
                                        .getParametersByElemId().get(0)));
                        }
                );

            }};

        } catch (NullPointerException e) {
            System.out.println(e);
        }finally {
            elementsDao.closeCurrentSessionwithTransaction();
        }
        return responses;
    }


}
