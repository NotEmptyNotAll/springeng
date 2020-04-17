package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.AutomobileEngineDao;
import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {

    @Autowired
    private EngineDao engineDao;

    @Autowired
    private AutomobileEngineDao automobileEngineDao;

    public EngineService(){
        engineDao=new EngineDao();
    }

    public Engine findById(int id){

        engineDao.openCurrentSessionwithTransaction();
        Engine engine = engineDao.findById(id).get();
//        engineDao.getByParam(new EngineRequest(engine));
        engineDao.closeCurrentSessionwithTransaction();

        return engine;
    }


    public List<String> getAllType(){
        engineDao.openCurrentSessionwithTransaction();
        List<String> engines = engineDao.getAllType();
        engineDao.closeCurrentSessionwithTransaction();
        return engines;
    }

    public List<Engine> getAll(){
        engineDao.openCurrentSessionwithTransaction();
        List<Engine> engines = engineDao.getAll();
        engineDao.closeCurrentSessionwithTransaction();
        return engines;
    }

    public void update(Engine engine){
        if(engine!=null){
            engineDao.openCurrentSessionwithTransaction();
            engineDao.update(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }

    public void save(Engine engine){
        if(engine!=null){
            engineDao.openCurrentSessionwithTransaction();
            engineDao.save(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }

    public void delete(Engine engine){
        if(engine!=null){
            engineDao.openCurrentSessionwithTransaction();
            engineDao.save(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }


}
