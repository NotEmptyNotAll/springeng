package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.RoleDao;
import com.vshvet.firstrelease.DAO.UserDao;
import com.vshvet.firstrelease.Entity.Role;
import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Service.AutomobileEngineService;
import com.vshvet.firstrelease.payload.Response.AutoEngAndParamResponse;
import com.vshvet.firstrelease.payload.Response.TreeToColumnsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class AutoEngineServiceTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AutomobileEngineService automobileEngineService;

    @Test
    void getAllAutoEngAndParamTest() {
        User user =userDao.findById(2).get();
        Role role = roleDao.findById(2).get();
        Set<Role> roleSet=user.getRoles();
        roleSet.add(role);
        user.setRoles(roleSet);
        userDao.update(user);
    }


}
