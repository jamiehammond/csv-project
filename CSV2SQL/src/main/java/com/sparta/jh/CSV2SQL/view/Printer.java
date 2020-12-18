package com.sparta.jh.CSV2SQL.view;

public class Printer {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void blankLine() {
        System.out.println();
    }

    public static void printTimeTaken(double time, String operation) {
        System.out.printf("Time taken to %s: %s seconds%n%n", operation, time);
    }

    public static void printFolderNotFound(String folderPath) {
        System.out.printf("Folder not found at '%s'. Please specify a valid folder path.", folderPath);
    }

    public static void printNoCSVsFound(String folderPath) {
        System.out.println("No .csv files found");
        System.out.printf("Please add a .csv file to '%s' to use the program", folderPath);
    }

    public static void printCSVs(String folderPath, String[] CSVNames) {
        System.out.printf("The current .csv files in '%s' are:%n", folderPath);
        for (String fileName : CSVNames) {
            System.out.printf("%s%n", fileName);
        }
    }

    public static void printGetCSVName() {
        System.out.printf("Please enter a .csv file to scan into your SQL database%n");
        System.out.println("Enter a file name (e.g 'MyFile.csv'): ");
    }

    public static void printUsingCSVName(String CSVName) {
        System.out.printf("Using '%s'%n", CSVName);
    }

    public static void printNumberOfRecords(int amount, String type) {
        System.out.printf("%s records with %s found%n", amount, type);
    }

    public static void printNumberOfRecords(int amount) {
        System.out.printf("%s records found%n", amount);
    }

    public static void printRecordsWrittenTo(String type, String filePath) {
        System.out.printf("%s records written to '%s'%n", type, filePath);
    }

    public static void printRecordCheck(int beforeCount, int afterCount) {
        System.out.printf("Of the %s records to add, %s already exist in the database%n", beforeCount, beforeCount - afterCount);
    }

    public static void printRecordsToAdd(int amount) {
        System.out.printf("Adding %s records to the database%n", amount);
    }
}