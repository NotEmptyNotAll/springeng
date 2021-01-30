package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.SuperchargedTypeDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.SuperchargedTypeService;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperchargedTypeServiceImpl implements SuperchargedTypeService {

    @Autowired
    private SuperchargedTypeDao superchargedTypeDao;

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllNameOfModel() {
        List<String> allType = superchargedTypeDao.getAllType();
        return allType;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<DataByIdResponse>(){{
            superchargedTypeDao.getPagination(request).forEach(item->{
                add(new DataByIdResponse(item.getNameType(),item.getMark(),item.getId(),item.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(superchargedTypeDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));

    }

    @Override
    @Transactional
    public Boolean update(UpdateTwoDataRequest updateData) {
        try {
            SuperchargedType newSuperchargedType = superchargedTypeDao.findById(updateData.getObjToBeChanged()).get();
            SuperchargedType oldSuperchargedType = new SuperchargedType(newSuperchargedType);
            newSuperchargedType.setStatus(new Status(updateData.getStatus()));
            newSuperchargedType.setNameType(updateData.getSaveData_primary());
            newSuperchargedType.setMark(updateData.getSaveData_secondary());
            oldSuperchargedType.setDate(new Date(new java.util.Date().getTime()));
            superchargedTypeDao.update(newSuperchargedType, oldSuperchargedType);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        SuperchargedType superchargedType = superchargedTypeDao.findById(id).get();
        List<Engine> engines= superchargedType.getEnginesById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (engines.size() == 0) {
            this.superchargedTypeDao.delete(superchargedType);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                engines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            superchargedTypeDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getNameType(), elem.getMark(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
        return all;
    }


    @Override
    @Transactional(readOnly = true)
    public List<SuperchargedType> getAllSuperchargedType() {
        List<SuperchargedType> all = superchargedTypeDao.getAll();

        return all;
    }

    @Override
    @Transactional
    public String save(SaveTwoDataRequest saveData) {
        try {

            SuperchargedType superchargedType = new SuperchargedType();
            superchargedType.setStatus(new Status(saveData.getStatus()));
            superchargedType.setMark(saveData.getSaveData_secondary());
            superchargedType.setNameType(saveData.getSaveData_primary());
            superchargedTypeDao.save(superchargedType);
            return "збереження було успішним";
        } catch (Exception e) {

            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    public void imprt(ImprtTwoDataRequest imprtData) {
        imprtData.getList().forEach(this::save);

    }
}
