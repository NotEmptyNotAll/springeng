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
import java.util.stream.Collectors;

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
    public Elements findByParentIdAndParamNameFk(Integer paramFk, Integer parentId) {
        return elementsDao.findByParentIdAndParamFk(paramFk, parentId);
    }

    @Override
    public void save(Elements elements) {
        try {
            elementsDao.save(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<String> getListFileUrlById(Integer id) {
        return new ArrayList<String>() {{
            elementsDao.findById(id).get().getFileStorages().forEach(elements -> {
                add(elements.getFileUrl());
            });
        }};
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
    public List<ElementsResponse> delete(Integer id) {
        Elements elements = elementsDao.findById(id).get();
        this.elementsDao.delete(elements);
        return null;
    }

    @Transactional(readOnly = true)
    public List<TreeToColumnsResponse> getTableColumn() {
        return new ArrayList<TreeToColumnsResponse>() {{
            elementsDao.getAllRootElemByAutoId().forEach(elem -> {
                add(new TreeToColumnsResponse(elem));
            });
        }};
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElementsResponse> getAllRootElemByAutoId(Integer id) {
        List<Integer> elemFkList = elementsDao.getElemFkListByAutoId(id);
        return new ArrayList<ElementsResponse>() {{
            elementsDao.getAllRootElemByAutoId().forEach(elements -> {
                add(new ElementsResponse(elements, elemFkList));
            });
        }};
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElementsResponse> getAllRootElem() {
        return new ArrayList<ElementsResponse>() {{
            elementsDao.getAllRootElemByAutoId().forEach(elements -> {
                add(new ElementsResponse(elements));
            });
        }};
    }

    //we get the elements according to the data
    // that the user measured and return a list of root id elements
    @Override
    @Transactional(readOnly = true)
    public Set<Integer> getParentElemId(List<ParamsRequest> request) {
        Set<Integer> elements = new HashSet<>();
        List<AutomobileEngine> autoEng = null;
        try {
            for (ParamsRequest paramsRequest :
                    request) {
                if (autoEng == null) {
                    autoEng = getAutoEngByElem(paramsRequest, 0);
                } else {
                    List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest, 0);
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
                elements.setSortNumber(elem.getSortNumber());
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
    @Transactional
    public void save(List<SaveOrUpdateElementsRequest> listElem) {
        listElem.forEach(elem -> {
            try {
                Elements elements = new Elements();
                elements.setElemId(elem.getElemId());
                elements.setSortNumber(elem.getSortNumber());
                elements.setColor(elem.getColor());
                elements.setParentElements(new Elements(elem.getParentId()));
                if (elem.getParamNameFk() != -1) {
                    elements.setParameterNamesByParamNameFk(new ParameterNames(elem.getParamNameFk()));
                } else {
                    elements.setParameterNamesByParamNameFk(new ParameterNames(1));//this name params id is the default for the empty node child
                }
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
            elements.setSortNumber(saveData.getSortNumber());
            elements.setParentElements(new Elements(saveData.getParentId()));
            if (saveData.getParamNameFk() != -1) {
                elements.setParameterNamesByParamNameFk(new ParameterNames(saveData.getParamNameFk()));
            } else {
                elements.setParameterNamesByParamNameFk(new ParameterNames(0));//this name params id is the default for the empty node child
            }
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
    @Transactional(readOnly = true)
    public List<ParamSizeNameResponse> getParametersSizeName(Integer id) {
        return new ArrayList<ParamSizeNameResponse>() {{
            elementsDao.getElementByParentId(id).forEach(elements -> {
                if (elements.getChildElements().size() == 0)
                    add(new ParamSizeNameResponse(elements.getParameterNamesByParamNameFk().getFullName(),
                            elements.getParameterNamesByParamNameFk().getName(),
                            elements.getSortNumber()));
            });
        }};
    }


    @Override
    @Transactional(readOnly = true)
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
            Elements newElements = elementsDao.findById(updateData.getElemId()).get();
            Elements oldElements = new Elements();
            oldElements.setParameterNamesByParamNameFk(new ParameterNames(newElements.getParamNameFk()));
            oldElements.setColor(newElements.getColor());
            oldElements.setSortNumber(newElements.getSortNumber());
            if (updateData.getParamNameFk() != null) {
                newElements.setParameterNamesByParamNameFk(new ParameterNames(updateData.getParamNameFk()));
            }
            if (updateData.getColor() != null) {
                newElements.setColor(updateData.getColor());
            }
            if (updateData.getSortNumber() != null) {
                newElements.setSortNumber(updateData.getSortNumber());
            }
            oldElements.setElemId(elementsDao.getMaxId() + 1);
            oldElements.setDate(new java.sql.Date(new java.util.Date().getTime()));
            oldElements.setParentElements(newElements.getParentElements());
            try {
                elementsDao.update(newElements, oldElements);
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
    public Boolean update(List<SaveOrUpdateElementsRequest> updateData) {
        try {
            updateData.forEach(this::update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean paramListIsEmpty(List<ParamsRequest> request) {
        if (request.size() > 0) {
            for (ParamsRequest paramsRequest :
                    request) {
                if (!paramsRequest.getParameterNumber().equals("")) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutomobileEngine> getParentElements(List<ParamsRequest> request, Integer searchPercent) {

        request = request.stream()
                .filter(item -> item.getParameterNumber().equals(""))
                .collect(Collectors.toList());

        List<AutomobileEngine> elements = new ArrayList<>();
        List<AutomobileEngine> autoEng = null;
        if (!paramListIsEmpty(request)) {
            try {
                for (ParamsRequest paramsRequest :
                        request) {
                    if (autoEng == null) {
                        autoEng = getAutoEngByElem(paramsRequest, searchPercent);
                    } else {
                        List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest, searchPercent);
                        autoEng.removeIf(eng -> !autoEngTemp.contains(eng));
                    }
                }
                if (autoEng != null) {
                    autoEng.forEach(elements::add);
                }
            } catch (ClassCastException e) {
                System.out.println(e);
            } finally {
            }
            return elements;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutoEngineResponse> getParentElementsUpdate(EngineRequest request) {
        List<AutoEngineResponse> elements = new ArrayList<>();
        List<AutomobileEngine> autoEng = null;
        try {
            for (ParamsRequest paramsRequest :
                    request.getParamList()) {
                if (autoEng == null) {
                    autoEng = getAutoEngByElem(paramsRequest, 0);
                } else {
                    List<AutomobileEngine> autoEngTemp = getAutoEngByElem(paramsRequest, 0);
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


    private List<AutomobileEngine> getAutoEngByElem(ParamsRequest paramsRequest, Integer searchPercent) {
        return elementsDao.findParentsElemByParam(paramsRequest, searchPercent);

    }


    private Elements findParentElem(Elements elements) {
        while (elements.getParentElements() != null) {     //go to the root element
            elements = elements.getParentElements();
        }
        return elements;
    }


}
