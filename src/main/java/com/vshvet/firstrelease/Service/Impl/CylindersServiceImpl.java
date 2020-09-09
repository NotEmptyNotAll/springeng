package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.CylindersDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.CylindersService;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.AutoDataResponse;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CylindersServiceImpl implements CylindersService {

    @Autowired
    private CylindersDao cylindersDao;

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<DataByIdResponse>(){{
            cylindersDao.getPagination(request).forEach(item->{
                add(new DataByIdResponse(item.getTypeName(),item.getId(),item.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(cylindersDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        Cylinders cylinders = cylindersDao.findById(id).get();
        List<Engine> engines= cylinders.getEnginesById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (engines.size()==0) {
            this.cylindersDao.delete(cylinders);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
               engines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {
            Cylinders cylinders = new Cylinders();
            cylinders.setStatus(new Status(saveData.getStatus()));
            cylinders.setTypeName(saveData.getSaveData());
            cylindersDao.save(cylinders);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public void imprt(ImprtDataRequest imprtDataRequest) {
        imprtDataRequest.getList().forEach(this::save);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Cylinders> getAllCylinders() {
        List<Cylinders> all = cylindersDao.getAll();
        return all;
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {
            Cylinders newCylinders = cylindersDao.findById(updateData.getObjToBeChanged()).get();
            Cylinders oldCylinders = new Cylinders(newCylinders);
            newCylinders.setStatus(new Status(updateData.getStatus()));
            newCylinders.setTypeName(updateData.getUpdateData());
            newCylinders.setId(updateData.getObjToBeChanged());
            oldCylinders.setDate(new Date(new java.util.Date().getTime()));
            cylindersDao.update(newCylinders, oldCylinders);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            cylindersDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getTypeName(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};

        return all;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllNameOfCylinders() {
        List<String> allType = cylindersDao.getAllType();
        return allType;
    }
}
