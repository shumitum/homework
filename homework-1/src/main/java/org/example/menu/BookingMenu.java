package org.example.menu;

import java.util.Scanner;

public class BookingMenu implements Menu {

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
                    System.out.println((char) 27 + "[31mИзвините, такой команды не существует." + (char) 27 + "[0m");
                    break;
            }
        }
    }

    private void printCommonMenu() {
        System.out.println("-=Выберите действие=-");
        System.out.println("1 - Забронировать конференц-зал");
        System.out.println("2 - Забронировать рабочее место");
        System.out.println("3 - Выйти в предыдущее меню");
    }

}
