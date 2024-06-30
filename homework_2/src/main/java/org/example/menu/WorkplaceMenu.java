package org.example.menu;

import lombok.Setter;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;
import org.example.workplace.WorkPlaceService;

@Setter
public class WorkplaceMenu implements Menu {
    private WorkPlaceService workPlaceService;

    public WorkplaceMenu() {
        this.workPlaceService = ApplicationContext.getInstance().getWorkPlaceService();
    }

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            String command = UserInput.stringInput();
            switch (command.trim()) {
                case "1" -> workPlaceService.createWorkplace();
                case "2" -> workPlaceService.updateWorkplace();
                case "3" -> workPlaceService.deleteWorkplace();
                case "4" -> Output.printMessage(workPlaceService.findAllWorkplaces().toString());
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
        System.out.println("1 - Создать рабочее место");
        System.out.println("2 - Обновить данные рабочего места");
        System.out.println("3 - Удалить рабочее место");
        System.out.println("4 - Посмотреть все доступные рабочие места");
        System.out.println("0 - Выйти в предыдущее меню");
    }
}