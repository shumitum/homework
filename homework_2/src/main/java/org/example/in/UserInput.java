package org.example.in;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public Integer digitInput(String consoleMessage) throws InputMismatchException {
        if (consoleMessage != null) {
            System.out.println(consoleMessage);
        }
        return scanner.nextInt();
    }

    public String stringInput(String consoleMessage) {
        if (consoleMessage != null) {
            System.out.println(consoleMessage);
        }
        return scanner.next();
    }
}
