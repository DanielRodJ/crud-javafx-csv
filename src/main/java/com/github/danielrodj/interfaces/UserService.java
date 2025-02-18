package com.github.danielrodj.interfaces;

import java.util.Optional;

import com.github.danielrodj.models.User;

public interface UserService extends BasicService<User> {

    String getUsernameByUserId(Integer id);

    Optional<User> existByUsername(String username);

    Optional<User> verifyUser(String username, String password);

}
