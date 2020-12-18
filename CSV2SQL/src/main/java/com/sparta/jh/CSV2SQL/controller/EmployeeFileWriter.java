package com.sparta.jh.CSV2SQL.controller;

import com.sparta.jh.CSV2SQL.model.EmployeeDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeFileWriter {

    private File file;
    private String filePath;

    public EmployeeFileWriter(String filePath) {
        this.filePath = filePath;
        setFile();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean setFile() {
        File temp = new File(filePath);
        if (temp.exists()) {
            file = temp;
            return true;
        } else {
            return false;
        }
    }

    public void writeEmployeesToFile(ArrayList<EmployeeDTO> employees) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write("Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary");
            bufferedWriter.newLine();
            for (EmployeeDTO employee : employees) {
                bufferedWriter.write(employee.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}