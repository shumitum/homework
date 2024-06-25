package org.example.user.repository;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, String> registeredUser = new HashMap<>();

    @Override
    public void saveUser(String userName, String password) {
        if (!userName.isEmpty() && !password.isEmpty() && !registeredUser.containsKey(userName)) {
            registeredUser.put(userName, password);
            System.out.printf("Пользователь \"%s\" зарегистрирован%n%n", userName);
        } else {
            System.out.printf("Пользователь \"%s\" уже зарегистрирован, выберете другое имя%n%n", userName);
        }
    }

    @Override
    public boolean checkUserCredentials(String userName, String password) {
        return registeredUser.containsKey(userName) && registeredUser.get(userName).equals(password);

    }
}