package com.sparta.jh.CSV2SQL;

import com.sparta.jh.CSV2SQL.controller.*;
import com.sparta.jh.CSV2SQL.model.EmployeeDAO;
import com.sparta.jh.CSV2SQL.model.EmployeeDTO;
import com.sparta.jh.CSV2SQL.model.EmployeeRepository;
import com.sparta.jh.CSV2SQL.utility.Timer;
import com.sparta.jh.CSV2SQL.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class App {
    static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        final String FOLDER_PATH = "src/main/resources";
        Timer readCSVTimer = new Timer();
        Timer programTimer = new Timer();
        Timer cleanCSVTimer = new Timer();
        Timer retrieveRecordsTimer = new Timer();
        Timer populateDBTimer = new Timer();
        Connection connection = DBConnectionController.getConnection();

        Printer.print("Welcome to CSV2SQL!");
        Printer.blankLine();
        CSVReader csvReader = new CSVReader(FOLDER_PATH);
        if (!csvReader.setFolder()) {
            Printer.printFolderNotFound(csvReader.getFolderPath());
            System.exit(-1);
        }
        if (csvReader.getNoOfCSVs() == 0) {
            Printer.printNoCSVsFound(csvReader.getFolderPath());
            System.exit(-1);
        }
        Printer.printCSVs(csvReader.getFolderPath(), csvReader.getCSVNames());
        Printer.blankLine();
        Printer.printGetCSVName();
        String CSVName = InputController.chooseCSVName(csvReader.getCSVNames());

        programTimer.start();

        Printer.print("Reading CSV file...");
        readCSVTimer.start();
        if (!csvReader.setFile(CSVName)) {
            logger.error("File at " + csvReader.getFilePath() + "not found.");
            System.exit(-1);
        }
        EmployeeRepository employees = new EmployeeRepository(csvReader.readEmployees());
        Printer.printNumberOfRecords(employees.getEmployees().size());
        readCSVTimer.stop();
        Printer.printTimeTaken(readCSVTimer.getTimeSeconds(), "read CSV file");

        Printer.print("Cleaning CSV file...");
        cleanCSVTimer.start();
        EmployeeRepository duplicateIDs = new EmployeeRepository(employees.getDuplicateIDs());
        Printer.printNumberOfRecords(duplicateIDs.getEmployees().size(), "duplicate IDs");
        EmployeeFileWriter fileWriter = new EmployeeFileWriter(FOLDER_PATH + "/" + "DuplicateIDs.csv");
        fileWriter.writeEmployeesToFile(duplicateIDs.getEmployees());
        Printer.printRecordsWrittenTo("Duplicate ID", fileWriter.getFilePath());

        EmployeeRepository duplicateEmails = new EmployeeRepository(employees.getDuplicateEmails());
        Printer.printNumberOfRecords(duplicateEmails.getEmployees().size(), "duplicate email addresses");
        fileWriter.setFilePath(FOLDER_PATH + "/" + "DuplicateEmails.csv");
        fileWriter.writeEmployeesToFile(duplicateEmails.getEmployees());
        Printer.printRecordsWrittenTo("Duplicate emails", fileWriter.getFilePath());

        EmployeeRepository invalidFields = new EmployeeRepository(employees.getAllBooleans());
        Printer.printNumberOfRecords(invalidFields.getEmployees().size(), "invalid fields");
        fileWriter.setFilePath(FOLDER_PATH + "/" + "InvalidFields.csv");
        fileWriter.writeEmployeesToFile(duplicateEmails.getEmployees());
        Printer.printRecordsWrittenTo("Invalid fields", fileWriter.getFilePath());

        ArrayList<EmployeeDTO> employeeArray = employees.getEmployees();
        employeeArray.removeAll(duplicateIDs.getEmployees());
        employeeArray.removeAll(duplicateEmails.getEmployees());
        employeeArray.removeAll(invalidFields.getEmployees());
        employees.setEmployees(employeeArray);
        cleanCSVTimer.stop();
        Printer.printTimeTaken(cleanCSVTimer.getTimeSeconds(), "clean CSV file");

        Printer.print("Checking records to add against existing records from database...");
        retrieveRecordsTimer.start();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        int employeesToAddCountBefore = employees.getEmployeeCount();
        if (employeeDAO.getCount(connection) > 0) {
            ArrayList<String> databaseIDs = employeeDAO.getAllIDs(connection);
            ArrayList<String> databaseEmails = employeeDAO.getAllEmails(connection);
            employees.removeIfInDB(databaseIDs, databaseEmails);
        }
        int employeesToAddCount = employees.getEmployeeCount();
        Printer.printRecordCheck(employeesToAddCountBefore, employeesToAddCount);
        retrieveRecordsTimer.stop();
        Printer.printTimeTaken(retrieveRecordsTimer.getTimeSeconds(), "check records against database");

        Printer.print("Populating database...");
        Printer.printRecordsToAdd(employeesToAddCount);
        populateDBTimer.start();
        ThreadLoader.loadThreads(employees.getEmployees());
        populateDBTimer.stop();
        Printer.printTimeTaken(populateDBTimer.getTimeSeconds(), "populate database");

        programTimer.stop();
        Printer.printTimeTaken(programTimer.getTimeSeconds(), "complete all operations");
    }
}