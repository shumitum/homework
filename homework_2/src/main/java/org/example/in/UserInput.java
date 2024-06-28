package org.example.in;

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
}
