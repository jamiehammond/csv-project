package com.sparta.jh.CSV2SQL;

import com.sparta.jh.CSV2SQL.controller.CSVReader;
import com.sparta.jh.CSV2SQL.controller.EmployeeFileWriter;
import com.sparta.jh.CSV2SQL.model.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeFileWriterTest {

    @Test
    void writesInvalidRecords() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        EmployeeFileWriter fw = new EmployeeFileWriter("src/test/testDirectory/TestInvalidFields.csv");
        fw.writeEmployeesToFile(empRepo.getAllBooleans());
        csvReader.setFile("TestInvalidFields.csv");
        Assertions.assertEquals(csvReader.readEmployees().toString(), empRepo.getAllBooleans().toString());
    }

    @Test
    void writesDuplicateIDs() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        EmployeeFileWriter fw = new EmployeeFileWriter("src/test/testDirectory/TestDupeIDs.csv");
        fw.writeEmployeesToFile(empRepo.getDuplicateIDs());
        csvReader.setFile("TestDupeIDs.csv");
        Assertions.assertEquals(csvReader.readEmployees().toString(), empRepo.getDuplicateIDs().toString());
    }

    @Test
    void writesDuplicateEmails() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        EmployeeFileWriter fw = new EmployeeFileWriter("src/test/testDirectory/TestDupeEmails.csv");
        fw.writeEmployeesToFile(empRepo.getDuplicateEmails());
        csvReader.setFile("TestDupeEmails.csv");
        Assertions.assertEquals(csvReader.readEmployees().toString(), empRepo.getDuplicateEmails().toString());
    }
}
