package com.sparta.jh.CSV2SQL.controller;

import com.sparta.jh.CSV2SQL.view.Printer;

import java.util.Scanner;

public class InputController {

    public static String chooseCSVName(String[] CSVNames) {
        Scanner scanner = new Scanner(System.in);
        String CSVName;
        boolean validName;
        do {
            validName = false;
            while (!scanner.hasNext()) {
                scanner.next();
            }
            CSVName = scanner.nextLine();
            for (String name : CSVNames) {
                if (CSVName.equals(name)) {
                    validName = true;
                    break;
                }
            }
            if (!validName) {
                Printer.print("Please enter a valid file name.");
            }
        } while (!validName);
        Printer.printUsingCSVName(CSVName);
        Printer.blankLine();
        return CSVName;
    }
}