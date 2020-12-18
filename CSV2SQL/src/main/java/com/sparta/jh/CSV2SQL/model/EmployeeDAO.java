package com.sparta.jh.CSV2SQL.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
    }

    // TODO: CRUD Operations

    public ResultSet queryDB(String query, Connection connection) {
        try {
            this.connection = connection;
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<EmployeeDTO> getAllEmployees(Connection connection) {
        try {
            this.connection = connection;
            ResultSet resultSet = queryDB("SELECT * FROM employees.employees", connection);
            ArrayList<EmployeeDTO> employeesDB = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String namePrefix = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String midInitial = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                String gender = resultSet.getString(6);
                String email = resultSet.getString(7);
                LocalDate dateOfBirth = resultSet.getDate(8).toLocalDate();
                LocalDate dateOfJoining = resultSet.getDate(9).toLocalDate();
                int salary = resultSet.getInt(10);
                employeesDB.add(new EmployeeDTO(id, namePrefix, firstName, midInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary));
            }
            return employeesDB;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getAllIDs(Connection connection) {
        try {
            this.connection = connection;
            ResultSet resultSet = queryDB("SELECT emp_id FROM employees.employees", connection);
            ArrayList<String> ids = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                ids.add(id);
            }
            return ids;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getAllEmails(Connection connection) {
        try {
            this.connection = connection;
            ResultSet resultSet = queryDB("SELECT email FROM employees.employees", connection);
            ArrayList<String> emails = new ArrayList<>();
            while (resultSet.next()) {
                String email = resultSet.getString(1);
                emails.add(email);
            }
            return emails;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int getCount(Connection connection) {
        int count = 0;
        try {
            this.connection = connection;
            ResultSet resultSet = queryDB("SELECT COUNT(*) FROM employees.employees", connection);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return count;
        }
    }

    private PreparedStatement prepareInsertEmployeeStatement() {
        try {
            return connection.prepareStatement("INSERT INTO `employees`.`employees` (`emp_id`, `name_prefix`, `first_name`, `middle_initial`, `last_name`, `gender`, `email`, `date_of_birth`, `date_of_joining`, `salary`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void insertEmployee(EmployeeDTO employee) {
        try {
            PreparedStatement preparedStatement = prepareInsertEmployeeStatement();
            preparedStatement.setInt(1, Integer.parseInt(employee.getEmpID()));
            preparedStatement.setString(2, employee.getNamePrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleInitial());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setInt(10, employee.getSalary());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEmployee(EmployeeDTO employee, Connection connection) {
        this.connection = connection;
        insertEmployee(employee);
    }
}