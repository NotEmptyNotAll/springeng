package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.SuperchargedType;

import java.util.List;

public interface SuperchargedTypeDao extends Dao<SuperchargedType> {
    List<String> getAllType();

}
