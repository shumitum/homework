package org.example.menu;

import org.example.context.ApplicationContext;
import org.example.user.service.impl.AuthenticationServiceImpl;
import org.example.user.service.impl.RegistrationServiceImpl;

import java.util.Scanner;

public class PrimaryMenu {

    private final ApplicationContext context = ApplicationContext.getInstance();
    private final AuthenticationServiceImpl authenticationServiceImpl = context.getAuthenticationManager();
    private final RegistrationServiceImpl registrationServiceImpl = context.getRegistrationManager();
    private final Menu conferenceHallMenu = context.getConferenceHallMenu();
    private final Menu workplaceMenu = context.getWorkplaceMenu();
    private final Menu bookingMenu = context.getBookingMenu();

    public void handleUserAction() {
        while (true) {
            printAuthMenu();
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            switch (command.trim()) {
                case "1":
                    registrationServiceImpl.registerUser();
                    break;
                case "2":
                    authenticationServiceImpl.authenticateUser();
                    break;
                case "3":
                    if (authenticationServiceImpl.getAuthorizedUser() != null) {
                        conferenceHallMenu.handleUserAction();
                    } else {
                        return;
                    }
                    break;
                case "4":
                    if (authenticationServiceImpl.getAuthorizedUser() != null) {
                        workplaceMenu.handleUserAction();
                    } else {
                        printNoSuchCommand();
                    }
                    break;
                case "5":
                    if (authenticationServiceImpl.getAuthorizedUser() != null) {
                        bookingMenu.handleUserAction();
                    } else {
                        printNoSuchCommand();
                    }
                    break;
                case "6":
                    if (authenticationServiceImpl.getAuthorizedUser() != null) {
                        return;
                    } else {
                        printNoSuchCommand();
                    }
                    break;
                default:
                    printNoSuchCommand();
                    break;
            }
        }
    }

    private void printAuthMenu() {
        System.out.println("-=Выберите действие=-");
        System.out.printf("Текущий пользователь: %s%n", authenticationServiceImpl.getAuthorizedUserName());
        System.out.println("1 - Регистрация пользователя");
        System.out.println("2 - Авторизация пользователя");
        showAdditionalMenu();
    }

    private void showAdditionalMenu() {
        if (authenticationServiceImpl.getAuthorizedUser() != null) {
            System.out.println("3 - Меню управления конференц-залами");
            System.out.println("4 - Меню управления рабочими местами");
            System.out.println("5 - Меню управления бронью");
            System.out.println("6 - Выйти из приложения");
        } else {
            System.out.println("3 - Выйти из приложения");
        }
    }

    private void printNoSuchCommand() {
        System.out.println((char) 27 + "[31mИзвините, такой команды не существует." + (char) 27 + "[0m");
    }
}
