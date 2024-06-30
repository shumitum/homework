package org.example.menu;

import lombok.Setter;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.user.service.AuthenticationService;
import org.example.user.service.RegistrationService;

@Setter
public class PrimaryMenu implements Menu {

    private ApplicationContext context;
    private AuthenticationService authenticationService;
    private RegistrationService registrationService;
    private Menu conferenceHallMenu;
    private Menu workplaceMenu;
    private Menu bookingMenu;

    public PrimaryMenu() {
        this.context = ApplicationContext.getInstance();
        this.authenticationService = context.getAuthenticationService();
        this.registrationService = context.getRegistrationService();
        this.conferenceHallMenu = context.getConferenceHallMenu();
        this.workplaceMenu = context.getWorkplaceMenu();
        this.bookingMenu = context.getBookingMenu();
    }

    @Override
    public void handleUserAction() {
        while (true) {
            printAuthMenu();
            String command = UserInput.stringInput();
            switch (command.trim()) {
                case "1" -> registrationService.registerUser();
                case "2" -> authenticationService.authenticateUser();
                case "3" -> {
                    if (authenticationService.checkAuthorizedUserExistence()) {
                        conferenceHallMenu.handleUserAction();
                    } else {
                        return;
                    }
                }
                case "4" -> {
                    if (authenticationService.checkAuthorizedUserExistence()) {
                        workplaceMenu.handleUserAction();
                    } else {
                        printNoSuchCommand();
                    }
                }
                case "5" -> {
                    if (authenticationService.checkAuthorizedUserExistence()) {
                        bookingMenu.handleUserAction();
                    } else {
                        printNoSuchCommand();
                    }
                }
                case "6" -> {
                    if (authenticationService.checkAuthorizedUserExistence()) {
                        return;
                    } else {
                        printNoSuchCommand();
                    }
                }
                default -> printNoSuchCommand();
            }
        }
    }

    private void printAuthMenu() {
        System.out.println("-=Выберите действие=-");
        System.out.printf("Текущий пользователь: %s%n", authenticationService.getAuthorizedUserName());
        System.out.println("1 - Регистрация пользователя");
        System.out.println("2 - Авторизация пользователя");
        showAdditionalMenu();
    }

    private void showAdditionalMenu() {
        if (authenticationService.checkAuthorizedUserExistence()) {
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
