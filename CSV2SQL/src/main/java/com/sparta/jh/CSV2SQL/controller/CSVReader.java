package com.sparta.jh.CSV2SQL.controller;

import com.sparta.jh.CSV2SQL.model.EmployeeDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    private File folder;
    private File file;
    private String folderPath;
    private String filePath;

    public CSVReader(String folderPath) {
        this.folderPath = folderPath;
        setFolder();
    }

    public String getFolderPath() {
        return folderPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean setFolder() {
        File temp = new File(folderPath);
        if (temp.exists()) {
            folder = temp;
            return true;
        } else {
            return false;
        }
    }

    public boolean setFile(String fileName) {
        filePath = folderPath + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            this.file = file;
            return true;
        } else {
            return false;
        }
    }

    public int getNoOfCSVs() {
        return getCSVNames().length;
    }

    public ArrayList<EmployeeDTO> readEmployees() {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            bufferedReader.readLine(); // Reads the first line without outputting it, essentially skipping over the first unnecessary entry.
            while ((line = bufferedReader.readLine()) != null) {
                String[] empString = line.split(",");
                // Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary
                EmployeeDTO currentEmployee = new EmployeeDTO(empString[0], empString[1], empString[2], empString[3], empString[4], empString[5], empString[6], empString[7], empString[8], empString[9]);
                employees.add(currentEmployee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex);
    }

    public String[] getCSVNames() {
        File[] fileList = folder.listFiles();
        ArrayList<String> csvList = new ArrayList<>();
        if (fileList != null) {
            for (File file : fileList) {
                if (getFileExtension(file).equals(".csv")) {
                    csvList.add(file.getName());
                }
            }
        }
        return csvList.toArray(new String[0]);
    }
}