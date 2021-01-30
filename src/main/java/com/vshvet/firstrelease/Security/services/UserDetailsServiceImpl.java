package com.vshvet.firstrelease.Security.services;

import com.vshvet.firstrelease.DAO.UserDao;
import com.vshvet.firstrelease.Entity.Role;
import com.vshvet.firstrelease.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;


//    @Override
//    @Transactional
//    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
//        //userDao.openCurrentSessionwithTransaction();
//
//        User user = userDao.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//        System.out.println(user.getRoles().toString());
//        //userDao.closeCurrentSessionwithTransaction();
//        return UserDetailsImpl.build(user);
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //userDao.openCurrentSessionwithTransaction();

        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        System.out.println(user.getRoles().toString());
        //userDao.closeCurrentSessionwithTransaction();
        user.setEnabled(true);
        return UserDetailsImpl.build(user);
    }


}
