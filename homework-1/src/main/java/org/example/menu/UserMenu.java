package org.example.menu;

import java.util.Scanner;

public class UserMenu implements Menu {

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            switch (command.trim()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Извините, такой команды не существует.");
                    break;
            }
        }
    }

    private void printCommonMenu() {
        System.out.println("-=Выберите действие=-");
        System.out.println("1 - Регистрация пользователя");
        System.out.println("2 - Авторизация пользователя");
        System.out.println("3 - Выйти в предыдущее меню");
    }

}
