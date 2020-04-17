package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ParamsRequest;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ElementsService {

    @Autowired
    private ElementsDao elementsDao;

    public ElementsResponse getElements(Integer id){
        elementsDao.openCurrentSessionwithTransaction();
        Elements elements = elementsDao.findById(id).get();
        ElementsResponse response = new ElementsResponse(elements);
        elementsDao.closeCurrentSessionwithTransaction();
        return response;
    }

    //we get the elements according to the data
    // that the user measured and return a list of root id elements

    public Set<Integer> getParentElem(EngineRequest request){
        Set<Integer> elements=null;
        elementsDao.openCurrentSessionwithTransaction();
        try {
        elements = new HashSet<Integer>(){{
            for (ParamsRequest paramsRequest:
                 request.getParamList()) {
                elementsDao.findParentsElemByParam(paramsRequest).forEach(element->{
                    add(findParentElem(element));
                });
            }
            }};}catch (ClassCastException e){
            System.out.println(e);
        }finally {
            elementsDao.closeCurrentSessionwithTransaction();
        }
        return elements;
    }

    private Integer findParentElem(Elements elements){
        while (elements.getParentElements()!=null){     //go to the root element
            elements=elements.getParentElements();
        }
    return elements.getElemId();
    }



}
