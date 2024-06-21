package org.example.user.service.impl;

import org.example.context.ApplicationContext;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;

import java.util.Scanner;

public class AuthenticationServiceImpl {

    private User authorizedUser;
    private final UserRepository userRepository = ApplicationContext.getInstance().getUserRepository();

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        String userName = scanner.next();
        System.out.println("Введите пароль:");
        String password = scanner.next();

        if (userRepository.checkUserCredentials(userName, password)) {
            authorizedUser = new User(userName, password);
            System.out.printf("Добро пожаловать \"%s\"%n", userName);
        } else {
            authorizedUser = null;
            System.out.println((char) 27 + "[31mНеверный логин/пароль" + (char) 27 + "[0m");
        }
    }

    public String getAuthorizedUserName() {
        if (authorizedUser != null) {
            return authorizedUser.getUserName();
        } else {
            return "Пользователь не авторизован";
        }
    }
}