package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ParamsRequest;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ElementsServiceImpl implements ElementsService {

    @Autowired
    private ElementsDao elementsDao;

    @Override
    public ElementsResponse getElements(Integer id) {
        elementsDao.openCurrentSessionwithTransaction();
        Elements elements = null;
        elements = elementsDao.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id : " + id));
        ElementsResponse response = new ElementsResponse(elements);
        elementsDao.closeCurrentSessionwithTransaction();
        return response;
    }

    //we get the elements according to the data
    // that the user measured and return a list of root id elements
    @Override
    public Set<Integer> getParentElem(EngineRequest request) {
        Set<Integer> elements = null;
        elementsDao.openCurrentSessionwithTransaction();
        try {
            elements = new HashSet<Integer>() {{
                for (ParamsRequest paramsRequest :
                        request.getParamList()) {
                    elementsDao.findParentsElemByParam(paramsRequest).forEach(element -> {
                        add(findParentElem((Elements) element));
                    });
                }
            }};
        } catch (ClassCastException e) {
            System.out.println(e);
        } finally {
            elementsDao.closeCurrentSessionwithTransaction();
        }
        return elements;
    }

    private Integer findParentElem(Elements elements) {
        while (elements.getParentElements() != null) {     //go to the root element
            elements = elements.getParentElements();
        }
        return elements.getElemId();
    }


}
