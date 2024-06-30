package org.example.menu;

import lombok.Setter;
import org.example.conferencehall.ConferenceHallService;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;

@Setter
public class ConferenceHallMenu implements Menu {
    private ConferenceHallService conferenceHallService;

    public ConferenceHallMenu() {
        this.conferenceHallService = ApplicationContext.getInstance().getConferenceHallService();
    }

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            String command = UserInput.stringInput();
            switch (command.trim()) {
                case "1" -> conferenceHallService.createConferenceHall();
                case "2" -> conferenceHallService.updateConferenceHall();
                case "3" -> conferenceHallService.deleteConferenceHall();
                case "4" -> Output.printMessage(conferenceHallService.findAllConferenceHalls().toString());
                case "0" -> {
                    return;
                }
                default ->
                        Output.printMessage((char) 27 + "[31mИзвините, такой команды не существует." + (char) 27 + "[0m");
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
