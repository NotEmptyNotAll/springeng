package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.User;
import com.vshvet.firstrelease.Entity.VerificationToken;

public interface VerificationTokenDao extends Dao<VerificationToken> {
    VerificationToken findByToken(String token);

}
