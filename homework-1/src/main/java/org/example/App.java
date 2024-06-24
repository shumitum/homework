package org.example;

import org.example.menu.Menu;
import org.example.menu.PrimaryMenu;

public class App {
    public static void main(String[] args) {
        Menu primaryMenu = new PrimaryMenu();
        primaryMenu.handleUserAction();
    }
}