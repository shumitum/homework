package org.example.menu;

import lombok.Setter;
import org.example.booking.BookingService;
import org.example.context.ApplicationContext;

import java.util.Scanner;

@Setter
public class BookingMenu implements Menu {

    private ApplicationContext context;
    private BookingService workplaceBookingService;
    private BookingService conferenceHallBookingService;

    public BookingMenu() {
        this.context = ApplicationContext.getInstance();
        this.workplaceBookingService = context.getWorkplaceBookingService();
        this.conferenceHallBookingService = context.getConferenceHallBookingService();
    }

    @Override
    public void handleUserAction() {
        while (true) {
            printCommonMenu();
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            switch (command.trim()) {
                case "1":
                    conferenceHallBookingService.createBooking();
                    break;
                case "2":
                    workplaceBookingService.createBooking();
                    break;
                case "3":
                    conferenceHallBookingService.cancelBooking();
                    break;
                case "4":
                    workplaceBookingService.cancelBooking();
                    break;
                case "5":
                    conferenceHallBookingService.getAvailableSlotsByDate();
                    break;
                case "6":
                    workplaceBookingService.getAvailableSlotsByDate();
                    break;
                case "7":
                    conferenceHallBookingService.getBookingsByDate();
                    break;
                case "8":
                    workplaceBookingService.getBookingsByDate();
                    break;
                case "9":
                    conferenceHallBookingService.getBookingsByUserName();
                    break;
                case "10":
                    workplaceBookingService.getBookingsByUserName();
                    break;
                case "11":
                    System.out.println("Все брони конференц-залов: " + conferenceHallBookingService.getAllBookings());
                    break;
                case "12":
                    System.out.println("Все брони рабочих мест: " + workplaceBookingService.getAllBookings());
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
        System.out.println("1 - Забронировать конференц-зал");
        System.out.println("2 - Забронировать рабочее место");
        System.out.println("3 - Отменить бронь конференц-зала");
        System.out.println("4 - Отменить бронь рабочего места");
        System.out.println("5 - Просмотр доступных слотов для бронирования конференц-залов на конкретную дату");
        System.out.println("6 - Просмотр доступных слотов для бронирования рабочих мест на конкретную дату");
        System.out.println("7 - Просмотр всех бронирований конференц-залов по дате");
        System.out.println("8 - Просмотр всех бронирований рабочих мест по дате");
        System.out.println("9 - Просмотр всех бронирований конференц-залов по пользователю");
        System.out.println("10 - Просмотр всех бронирований рабочих мест по пользователю");
        System.out.println("11 - Просмотр всех бронирований конференц-залов");
        System.out.println("12 - Просмотр всех бронирований рабочих мест");
        System.out.println("0 - Выйти в предыдущее меню");
    }
}