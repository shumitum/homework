package org.example.user.repository;

import org.example.user.model.User;

import java.util.Optional;

public interface UserRepository {
    void saveUser(String userName, String password);

    Optional<User> findUserByCredentials(String userName, String password);

    //boolean checkUserCredentials(String userName, String password);
}