package org.example.user.repository;

public interface UserRepository {
    void saveUser(String userName, String password);

    boolean checkUserCredentials(String userName, String password);
}