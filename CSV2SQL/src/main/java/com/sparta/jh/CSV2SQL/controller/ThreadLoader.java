package com.sparta.jh.CSV2SQL.controller;

import com.sparta.jh.CSV2SQL.model.EmployeeDTO;

import java.util.ArrayList;

public class ThreadLoader {

    private static final int SUBARRAY_SIZE = 1000;
    private static ArrayList<Thread> threads = new ArrayList<>();

    public static void loadThreads(ArrayList<EmployeeDTO> employees) {
        divideArray(employees);
        startAll();
        joinAll();
    }

    private static void startAll() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void joinAll() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<EmployeeDTO> divideArray(ArrayList<EmployeeDTO> employeeArray) {
        if (employeeArray.size() < SUBARRAY_SIZE) {
            return employeeArray;
        }

        int midIndex = employeeArray.size() / 2;
        ArrayList<EmployeeDTO> leftArray = new ArrayList<EmployeeDTO>();
        ArrayList<EmployeeDTO> rightArray = new ArrayList<EmployeeDTO>();

        for (int i = 0; i < midIndex; i++) {
            leftArray.add(i, employeeArray.get(i));
        }
        for (int i = midIndex; i < employeeArray.size(); i++) {
            rightArray.add(i - midIndex, employeeArray.get(i));
        }

        leftArray = divideArray(leftArray);
        rightArray = divideArray(rightArray);

        if (leftArray != null) {
            createThread(leftArray);
        }
        if (rightArray != null) {
            createThread(rightArray);
        }

        return null;
    }

    private static void createThread(ArrayList<EmployeeDTO> employeeArray) {
        threads.add(new Thread(new JDBCWriter(employeeArray)));
    }
}
