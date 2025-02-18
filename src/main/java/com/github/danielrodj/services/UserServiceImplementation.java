package com.github.danielrodj.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.danielrodj.interfaces.UserService;
import com.github.danielrodj.models.User;
import com.github.danielrodj.repositories.UserRepositoryCSV;

public class UserServiceImplementation implements UserService {

    UserRepositoryCSV userRepositoryCSV;

    public UserServiceImplementation() {
        this.userRepositoryCSV = new UserRepositoryCSV();
    }

    @Override
    public User get(int id) {
        return userRepositoryCSV.get(id);
    }

    @Override
    public Map<Integer, User> getAll() {
        return userRepositoryCSV.getAll();
    }

    @Override
    public int insert(User user) {
        return userRepositoryCSV.insert(user);
    }

    @Override
    public int update(User user) {
        return userRepositoryCSV.update(user);
    }

    @Override
    public int delete(User user) {
        return userRepositoryCSV.delete(user);
    }

    @Override
    public String getUsernameByUserId(Integer id) {
        return get(id).getUsername();
    }

    @Override
    public Optional<User> existByUsername(String username) {
        return getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> verifyUser(String username, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(getAll().values());
        return users;
    }

}
