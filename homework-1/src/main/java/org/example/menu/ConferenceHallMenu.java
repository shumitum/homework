package org.example.menu;

import org.example.conferencehall.ConferenceHallService;
import org.example.context.ApplicationContext;

import java.util.Scanner;

public class ConferenceHallMenu implements Menu {
    private final ConferenceHallService conferenceHallService = ApplicationContext.getInstance().getConferenceHallService();

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            switch (command.trim()) {
                case "1":
                    conferenceHallService.createConferenceHall();
                    break;
                case "2":
                    conferenceHallService.updateConferenceHall();
                    break;
                case "3":
                    conferenceHallService.deleteConferenceHall();
                    break;
                case "4":
                    System.out.println(conferenceHallService.findAllConferenceHalls());
                    break;
                case "0":
                    return;
                default:
                    System.out.println((char) 27 + "[31mИзвините, такой команды не существует." + (char) 27 + "[0m");
                    break;
            }
        }
    }

    private void printCommonMenu() {
        System.out.println("-=Выберите действие=-");
        System.out.println("1 - Создать конференц-зал");
        System.out.println("2 - Обновить данные конференц-зала");
        System.out.println("3 - Удалить конференц-зал");
        System.out.println("4 - Посмотреть все доступные конференц-залы");
        System.out.println("0 - Выйти в предыдущее меню");
    }

}
