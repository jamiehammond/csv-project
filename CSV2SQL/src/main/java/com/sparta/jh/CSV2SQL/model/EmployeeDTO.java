package com.sparta.jh.CSV2SQL.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeDTO {
    // Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary
    String empID;
    String namePrefix;
    String firstName;
    String middleInitial;
    String lastName;
    String gender;
    String email;
    LocalDate dateOfBirth;
    LocalDate dateOfJoining;
    Integer salary;

    public EmployeeDTO(String empID, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, String salary) {
        this.empID = empID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        setDateOfBirth(dateOfBirth);
        setDateOfJoining(dateOfJoining);
        setSalary(salary);
    }

    public EmployeeDTO(String empID, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, LocalDate dateOfBirth, LocalDate dateOfJoining, int salary) {
        this.empID = empID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
//        if (dateOfBirth.matches("^\\d?\\d/\\d{2}/\\d{4}$")) {
//            this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
//        } else {
//            this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("M[M]-d[d]-yyyy"));
//        }
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
//        if (dateOfJoining.matches("^\\?d\\d/\\?d\\d/\\d{4}$")) {
//            this.dateOfJoining = LocalDate.parse(dateOfJoining, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
//        } else {
//            this.dateOfJoining = LocalDate.parse(dateOfJoining, DateTimeFormatter.ofPattern("M[M]-d[d]-yyyy"));
//        }
        this.dateOfJoining = LocalDate.parse(dateOfJoining, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = Integer.valueOf(salary);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return empID + "," +
                namePrefix + "," +
                firstName + "," +
                middleInitial + "," +
                lastName +  "," +
                gender + "," +
                email + "," +
                dateOfBirth.format(formatter) + "," +
                dateOfJoining.format(formatter) + "," +
                salary;
    }

    public boolean containsBooleanField() {
        String[] array = this.toString().split(",");
        for (String field : array) {
            String upperField = field.toUpperCase();
            if (upperField.equals("TRUE") || upperField.equals("FALSE")) {
                return true;
            }
        }
        return false;
    }
}
