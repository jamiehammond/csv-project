package com.sparta.jh.CSV2SQL.utility;

public class Timer {

    private long startTime;
    private long endTime;

    public void start() {
        setStartTime(System.currentTimeMillis());
    }

    public void stop() {
        setEndTime(System.currentTimeMillis());
    }

    public double getTimeSeconds() {
        return ((double)endTime - (double)startTime) / 1000;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
