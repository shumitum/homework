package org.example.user.service.impl;

import lombok.AccessLevel;
import lombok.Setter;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.example.user.service.AuthenticationService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Setter
public class AuthenticationServiceImpl implements AuthenticationService {

    @Setter(AccessLevel.NONE)
    private User authorizedUser;
    private UserRepository userRepository;
    private UserInput userInput;

    public AuthenticationServiceImpl() {
        this.userRepository = ApplicationContext.getInstance().getUserRepository();
        this.userInput = ApplicationContext.getInstance().getUserInput();
    }

    @Override
    public void authenticateUser() {
        String userName = userInput.stringInput("Введите имя пользователя:");
        String password = userInput.stringInput("Введите пароль:");
        Optional<User> user = userRepository.findUserByCredentials(userName, password);
        if (user.isPresent()) {
            authorizedUser = user.get();
            Output.printMessage(String.format("Добро пожаловать \"%s\"", userName));
        } else {
            authorizedUser = null;
            Output.printMessage((char) 27 + "[31mНеверный логин/пароль" + (char) 27 + "[0m");
        }
    }

    @Override
    public String getAuthorizedUserName() {
        if (authorizedUser != null) {
            return authorizedUser.getUserName();
        } else {
            return "Пользователь не авторизован";
        }
    }

    @Override
    public User getAuthorizedUser() {
        if (authorizedUser != null) {
            return authorizedUser;
        } else {
            throw new NoSuchElementException("Пользователь не авторизован");
        }
    }

    @Override
    public boolean checkAuthorizedUserExistence() {
        return authorizedUser != null;
    }
}