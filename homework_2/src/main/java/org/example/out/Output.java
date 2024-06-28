package org.example.out;

public class Output {
    private Output() {
    }

    public static void printMessage(String message) {
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        }
    }
}
