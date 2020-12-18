package com.sparta.jh.CSV2SQL;

import com.sparta.jh.CSV2SQL.controller.CSVReader;
import com.sparta.jh.CSV2SQL.model.EmployeeDTO;
import com.sparta.jh.CSV2SQL.model.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class CSVReaderTest {

    @Test
    void canSeeCSVFiles() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        Assertions.assertEquals(6, csvReader.getNoOfCSVs());
    }

    @Test
    void canSeeNoCSVFiles() {
        CSVReader csvReader = new CSVReader("src/test/testEmptyDirectory");
        Assertions.assertEquals(0, csvReader.getNoOfCSVs());
    }

    @Test
    void canReadCSVNames() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        Assertions.assertArrayEquals(new String[]{"file1.csv", "file2.csv", "file3.csv", "TestDupeEmails.csv", "TestDupeIDs.csv", "TestInvalidFields.csv"}, csvReader.getCSVNames());
    }

    @Test
    void returnsFalseIfFolderDoesNotExist() {
        CSVReader csvReader = new CSVReader("src/test/nonExistentFolder");
        Assertions.assertFalse(csvReader.setFolder());
    }

    @Test
    void returnsTrueIfFolderExists() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        Assertions.assertTrue(csvReader.setFolder());
    }

    @Test
    void returnsFalseIfFileDoesNotExist() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        Assertions.assertFalse(csvReader.setFile("nonExistentFile.csv"));
    }

    @Test
    void returnsTrueIfFileExists() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        Assertions.assertTrue(csvReader.setFile("file1.csv"));
    }

    @Test
    void readsAllLines() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file1.csv");
        ArrayList<EmployeeDTO> testArray = csvReader.readEmployees();
        Assertions.assertEquals(5, testArray.size());
    }

    @Test
    void findsInvalidRecords() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        Assertions.assertEquals(3, empRepo.getAllBooleans().size());
    }

    @Test
    void findsDuplicateIDs() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        Assertions.assertEquals(6, empRepo.getDuplicateIDs().size());
    }

    @Test
    void findsDuplicateEmails() {
        CSVReader csvReader = new CSVReader("src/test/testDirectory");
        csvReader.setFile("file2.csv");
        EmployeeRepository empRepo = new EmployeeRepository(csvReader.readEmployees());
        Assertions.assertEquals(4, empRepo.getDuplicateEmails().size());
    }
}