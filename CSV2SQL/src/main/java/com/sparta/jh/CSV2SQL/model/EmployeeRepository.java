package com.sparta.jh.CSV2SQL.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EmployeeRepository {

    private ArrayList<EmployeeDTO> employees;

    public EmployeeRepository(ArrayList<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public ArrayList<EmployeeDTO> findEmployeesByID(String id) {
        ArrayList<EmployeeDTO> employeesToReturn = new ArrayList<>();
        for (EmployeeDTO employee : employees) {
            if (employee.getEmpID().equals(id)) {
                employeesToReturn.add(employee);
            }
        }
        return employeesToReturn;
    }

    public ArrayList<EmployeeDTO> findEmployeesByEmail(String email) {
        ArrayList<EmployeeDTO> employeesToReturn = new ArrayList<>();
        for (EmployeeDTO employee : employees) {
            if (employee.getEmail().equals(email)) {
                employeesToReturn.add(employee);
            }
        }
        return employeesToReturn;
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public ArrayList<EmployeeDTO> getDuplicateIDs() {
        ArrayList<EmployeeDTO> duplicateStaff = new ArrayList<>();
        Set<String> employeeIDs = new HashSet<>();
        Set<String> duplicateIDs = new HashSet<>();
        for (EmployeeDTO employee : employees) {
            if (!employeeIDs.add(employee.getEmpID())) {
                duplicateIDs.add(employee.getEmpID());
            }
        }
        for (String id : duplicateIDs) {
            ArrayList<EmployeeDTO> tempDupes = findEmployeesByID(id);
            duplicateStaff.addAll(tempDupes);
        }
        return duplicateStaff;
    }

    public ArrayList<EmployeeDTO> getDuplicateEmails() {
        ArrayList<EmployeeDTO> duplicateStaff = new ArrayList<>();
        Set<String> employeeEmails = new HashSet<>();
        Set<String> duplicateEmails = new HashSet<>();
        for (EmployeeDTO employee : employees) {
            if (!employeeEmails.add(employee.getEmail())) {
                duplicateEmails.add(employee.getEmail());
            }
        }
        for (String email : duplicateEmails) {
            ArrayList<EmployeeDTO> tempDupes = findEmployeesByEmail(email);
            duplicateStaff.addAll(tempDupes);
        }
        return duplicateStaff;
    }

    public ArrayList<EmployeeDTO> getAllDuplicates() {
        ArrayList<EmployeeDTO> duplicateStaff = new ArrayList<>();
        duplicateStaff.addAll(getDuplicateIDs());
        duplicateStaff.addAll(getDuplicateEmails());
        return duplicateStaff;
    }

    public ArrayList<EmployeeDTO> getAllBooleans() {
        ArrayList<EmployeeDTO> invalidStaff = new ArrayList<>();
        for (EmployeeDTO employee : employees) {
            if (employee.containsBooleanField()) {
                invalidStaff.add(employee);
            }
        }
        return invalidStaff;
    }

    public void removeIfInDB(ArrayList<String> databaseIDs, ArrayList<String> databaseEmails) {
        ArrayList<EmployeeDTO> tempEmployees = new ArrayList<>();
        for (EmployeeDTO employee : employees) {
            boolean isDuplicate = false;
            for (String id : databaseIDs) {
                if (employee.getEmpID().equals(id)) {
                    isDuplicate = true;
                    break;
                }
            }
            for (String email : databaseEmails) {
                if (employee.getEmail().equals(email)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                tempEmployees.add(employee);
            }
        }
        setEmployees(tempEmployees);
    }
}