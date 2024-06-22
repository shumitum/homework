package org.example;

import org.example.menu.PrimaryMenu;

public class App {
    public static void main(String[] args) {
        PrimaryMenu primaryMenu = new PrimaryMenu();
        primaryMenu.handleUserAction();
    }
}