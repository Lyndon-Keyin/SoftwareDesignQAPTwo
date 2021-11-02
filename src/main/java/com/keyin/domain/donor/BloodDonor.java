package com.keyin.domain.donor;

import java.time.LocalDate;
import java.util.Date;

public class BloodDonor {

    private int id;
    private String firstNAme;
    private String LastName;
    private LocalDate DOB;
    private String bloodType;
    private LocalDate nextAppointment;
    private LocalDate lastAppointment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstNAme;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDate nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public LocalDate getLastAppointment() {
        return lastAppointment;
    }

    public void setLastAppointment(LocalDate lastAppointment) {
        this.lastAppointment = lastAppointment;
    }
}
