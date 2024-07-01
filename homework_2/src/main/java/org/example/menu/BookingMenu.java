package org.example.menu;

import lombok.Setter;
import org.example.booking.controller.ConferenceHallBookingController;
import org.example.booking.controller.WorkplaceBookingController;
import org.example.context.ApplicationContext;
import org.example.in.UserInput;
import org.example.out.Output;

@Setter
public class BookingMenu implements Menu {

    private ApplicationContext context;
    private WorkplaceBookingController workplaceBookingController;
    private ConferenceHallBookingController conferenceHallBookingController;

    public BookingMenu() {
        this.context = ApplicationContext.getInstance();
        this.workplaceBookingController = context.getWorkplaceBookingController();
        this.conferenceHallBookingController = context.getConferenceHallBookingController();
    }

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            String command = UserInput.stringInput();
            switch (command.trim()) {
                case "1" -> conferenceHallBookingController.createBooking();
                case "2" -> workplaceBookingController.createBooking();
                case "3" -> conferenceHallBookingController.cancelBooking();
                case "4" -> workplaceBookingController.cancelBooking();
                case "5" -> conferenceHallBookingController.getAvailableSlotsByDate();
                case "6" -> workplaceBookingController.getAvailableSlotsByDate();
                case "7" -> conferenceHallBookingController.getBookingsByDate();
                case "8" -> workplaceBookingController.getBookingsByDate();
                case "9" -> conferenceHallBookingController.getBookingsByUserName();
                case "10" -> workplaceBookingController.getBookingsByUserName();
                case "11" -> conferenceHallBookingController.getAllBookings();
                case "12" -> workplaceBookingController.getAllBookings();
                case "0" -> {
                    return;
                }
                default ->
                        Output.printMessage((char) 27 + "[31mИзвините, такой команды не существует." + (char) 27 + "[0m");
            }
        }
    }

    private void printCommonMenu() {
        Output.printMessage("-=Выберите действие=-");
        Output.printMessage("1 - Забронировать конференц-зал");
        Output.printMessage("2 - Забронировать рабочее место");
        Output.printMessage("3 - Отменить бронь конференц-зала");
        Output.printMessage("4 - Отменить бронь рабочего места");
        Output.printMessage("5 - Просмотр доступных слотов для бронирования конференц-залов на конкретную дату");
        Output.printMessage("6 - Просмотр доступных слотов для бронирования рабочих мест на конкретную дату");
        Output.printMessage("7 - Просмотр всех бронирований конференц-залов по дате");
        Output.printMessage("8 - Просмотр всех бронирований рабочих мест по дате");
        Output.printMessage("9 - Просмотр всех бронирований конференц-залов по пользователю");
        Output.printMessage("10 - Просмотр всех бронирований рабочих мест по пользователю");
        Output.printMessage("11 - Просмотр всех бронирований конференц-залов");
        Output.printMessage("12 - Просмотр всех бронирований рабочих мест");
        Output.printMessage("0 - Выйти в предыдущее меню");
    }
}