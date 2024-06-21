package org.example.user.service.impl;

import org.example.context.ApplicationContext;
import org.example.user.repository.UserRepository;

import java.util.Scanner;

public class RegistrationServiceImpl {

    private final UserRepository userRepository = ApplicationContext.getInstance().getUserRepository();

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        String userName = scanner.next();
        System.out.println("Введите пароль:");
        String password = scanner.next();
        userRepository.saveUser(userName, password);
    }
}
