package org.example.user.repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, String> registeredUser = new HashMap<>();

    public void saveUser(String userName, String password) {
        if (!userName.isEmpty() && !password.isEmpty() && !registeredUser.containsKey(userName)) {
            registeredUser.put(userName, password);
            System.out.printf("Пользователь \"%s\" зарегистрирован%n%n", userName);
            System.out.println(registeredUser);//todo DELETE THIS
        } else {
            System.out.printf("Пользователь \"%s\" уже зарегистрирован, выберете другое имя%n%n", userName);
            System.out.println(registeredUser);//todo DELETE THIS
        }
    }

    public boolean checkUserCredentials(String userName, String password) {
        System.out.println(registeredUser);//todo DELETE THIS
        return registeredUser.containsKey(userName) && registeredUser.get(userName).equals(password);

    }
}