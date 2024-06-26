package org.example.in;

import java.util.Scanner;

public class UserInput {

    public Integer digitInput(String consoleMessage) {
        System.out.println(consoleMessage);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String stringInput(String consoleMessage) {
        System.out.println(consoleMessage);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
