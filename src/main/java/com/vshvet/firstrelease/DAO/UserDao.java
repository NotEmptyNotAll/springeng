package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    void update(User user);

    void openSession();
}
