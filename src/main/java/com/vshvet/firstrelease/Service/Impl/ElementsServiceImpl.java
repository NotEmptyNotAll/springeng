package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.ElementsDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.AutomobileEngineService;
import com.vshvet.firstrelease.Service.ElementsService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ElementsServiceImpl implements ElementsService {

    @Autowired
    private ElementsDao elementsDao;

    @Autowired
    private AutomobileEngineService automobileEngineService;

    @Override
    @Transactional
    public ElementsResponse getElements(Integer id) {
        return new ElementsResponse(elementsDao.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id : " + id)));
    }

    @Override
    @Transactional
    public List<TreeElementsResponse> getTreeElements() {
        return new ArrayList<TreeElementsResponse>() {{
            elementsDao.getAllRootElemByAutoId().forEach(elements -> {
                add(new TreeElementsResponse(elements));
            });
        }};
    }

    @Override
    @Transactional
    public List<Elements> getAllNodeOfTree() {
        List<Elements> list = elementsDao.getAllNodeOfTree();
        return list;
    }

    @Override
    @Transactional
    public List<ElementsResponse> getAllRootElemByAutoId(Integer id) {
        return new ArrayList<ElementsResponse>() {{
            elementsDao.getAllRootElemByAutoId().forEach(elements -> {
                add(new ElementsResponse(elements, id));
            });
        }};
    }

    //we get the elements according to the data
    // that the user measured and return a list of root id elements
    @Override
    @Transactional
    public Set<Integer> getParentElemId(EngineRequest request) {
        Set<Integer> elements = new HashSet<>();
        List<AutomobileEngine> autoEng = null;

        try {
            for (ParamsRequest paramsRequest :
                    request.getParamList()) {
                if (autoEng == null) {
                    autoEng = getAutoEngByElem(paramsRequest);
                } else {
                    List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest);
                    autoEng.removeIf(eng -> !autoEngTemp.contains(eng));
                }
            }
            if (autoEng != null) {
                autoEng.forEach(auto -> elements.add(auto.getId()));
            }
        } catch (ClassCastException e) {
            System.out.println(e);
        } finally {
        }
        return elements;
    }

    @Override
    @Transactional
    public void save(List<SaveOrUpdateElementsRequest> saveData, List<SaveOrUpdateParametersRequest> listSaveParam, List<SaveOrUpdateParametersRequest> listUpdateParam) {

        Collections.sort(saveData);
        saveData.forEach(elem -> {
            //  this.elementsDao.findById(elem.getParentId());
            Optional<Elements> tempElements = this.elementsDao
                    .findById(elem.getParentId()).get()
                    .getChildElements().stream()
                    .filter(e -> e.getElemId() == elem.getElemId()).findFirst();
            if (!tempElements.isPresent()) {
                Elements elements = new Elements();
                elements.setElemId(elem.getElemId());
                elements.setParentElements(new Elements(elem.getParentId()));
                elements.setParameterNamesByParamNameFk(new ParameterNames(elem.getParamNameFk()));
                try {
                    elementsDao.save(elements);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }
        });

    }

    @Override
    public void save(List<SaveOrUpdateElementsRequest> listElem) {
        listElem.forEach(elem -> {
            try {
                Elements elements = new Elements();
                elements.setElemId(elem.getElemId());
                elements.setParentElements(new Elements(elem.getParentId()));
                elements.setParameterNamesByParamNameFk(new ParameterNames(elem.getParamNameFk()));
                try {
                    elementsDao.save(elements);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

    }

    @Override
    @Transactional
    public String save(SaveOrUpdateElementsRequest saveData) {
        try {
            Elements elements = new Elements();
            elements.setElemId(saveData.getElemId());
            elements.setParentElements(new Elements(saveData.getParentId()));
            elements.setParameterNamesByParamNameFk(new ParameterNames(saveData.getParamNameFk()));
            try {
                elementsDao.save(elements);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public Integer getMaxId() {
        Integer maxId = elementsDao.getMaxId();
        return maxId;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        return null;
    }

    @Override
    @Transactional
    public Boolean update(SaveOrUpdateElementsRequest updateData) {
        try {
            Elements  newElements= elementsDao.findById(updateData.getElemId()).get();
            Elements oldElements = new Elements();
            oldElements.setParameterNamesByParamNameFk(new ParameterNames(newElements.getParamNameFk()));
            newElements.setParameterNamesByParamNameFk(new ParameterNames(updateData.getParamNameFk()));
            oldElements.setElemId(elementsDao.getMaxId()+1);
            oldElements.setParentElements(oldElements.getParentElements());
            try {
                elementsDao.update(newElements,oldElements);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    @Transactional
    public List<AutomobileEngineResponse> getParentElements(EngineRequest request) {
        List<AutomobileEngineResponse> elements = new ArrayList<>();
        List<AutomobileEngine> autoEng = null;
        try {
            for (ParamsRequest paramsRequest :
                    request.getParamList()) {
                if (autoEng == null) {
                    autoEng = getAutoEngByElem(paramsRequest);
                } else {
                    List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest);
                    autoEng.removeIf(eng -> !autoEngTemp.contains(eng));
                }
            }
            if (autoEng != null) {
                autoEng.forEach(auto -> elements.add(new AutomobileEngineResponse(auto)));
            }
        } catch (ClassCastException e) {
            System.out.println(e);
        } finally {
        }
        return elements.size() < 10 ? elements : null;
    }

    @Override
    @Transactional
    public List<AutoEngineResponse> getParentElementsUpdate(EngineRequest request) {
        List<AutoEngineResponse> elements = new ArrayList<>();
        List<AutomobileEngine> autoEng = null;
        try {
            for (ParamsRequest paramsRequest :
                    request.getParamList()) {
                if (autoEng == null) {
                    autoEng = getAutoEngByElem(paramsRequest);
                } else {
                    List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest);
                    autoEng.removeIf(eng -> !autoEngTemp.contains(eng));
                }
            }
            if (autoEng != null) {
                autoEng.forEach(auto -> elements.add(new AutoEngineResponse(auto)));
            }
        } catch (ClassCastException e) {
            System.out.println(e);
        } finally {
        }
        return elements.size() < 10 ? elements : null;
    }


    private List<AutomobileEngine> getAutoEngByElem(ParamsRequest paramsRequest) {
        return new ArrayList<AutomobileEngine>() {{
            elementsDao.findParentsElemByParam(paramsRequest).forEach(this::add);
        }};

    }


    private Elements findParentElem(Elements elements) {
        while (elements.getParentElements() != null) {     //go to the root element
            elements = elements.getParentElements();
        }
        return elements;
    }


}
