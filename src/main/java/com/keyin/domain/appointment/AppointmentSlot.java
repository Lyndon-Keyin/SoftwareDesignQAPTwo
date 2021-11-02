package com.keyin.domain.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentSlot {
    private int id;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String bloodType;

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
