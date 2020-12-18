package com.sparta.jh.CSV2SQL.controller;

import com.sparta.jh.CSV2SQL.model.EmployeeDAO;
import com.sparta.jh.CSV2SQL.model.EmployeeDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class JDBCWriter implements Runnable {

    private final ArrayList<EmployeeDTO> employees;
    private final Connection connection;

    public JDBCWriter(ArrayList<EmployeeDTO> employees) {
        this.employees = employees;
        this.connection = DBConnectionController.getConnection();
    }

    @Override
    public void run() {
        EmployeeDAO emp = new EmployeeDAO();
        for (EmployeeDTO employee : employees) {
            emp.insertEmployee(employee, connection);
        }
    }
}