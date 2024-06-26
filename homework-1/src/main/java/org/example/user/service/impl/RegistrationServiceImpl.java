package org.example.user.service.impl;

import lombok.Setter;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.user.repository.UserRepository;
import org.example.user.service.RegistrationService;

@Setter
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private UserInput userInput;

    public RegistrationServiceImpl() {
        this.userRepository = ApplicationContext.getInstance().getUserRepository();
        this.userInput = ApplicationContext.getInstance().getUserInput();
    }

    @Override
    public void registerUser() {
        String userName = userInput.stringInput("Введите имя пользователя:");
        String password = userInput.stringInput("Введите пароль:");
        userRepository.saveUser(userName, password);
    }
}
