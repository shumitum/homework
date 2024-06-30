package org.example.in;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    public static Integer digitInput(String message) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        }
        return scanner.nextInt();
    }

    public static String stringInput(String message) {
        Scanner scanner = new Scanner(System.in);
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        }
        return scanner.next();
    }

    public static String stringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static LocalDate dateInput() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату бронирования в формате гггг-мм-дд:");
        String inputDate = scanner.next();
        return LocalDate.parse(inputDate);
    }
}
