package com.keyin.domain.appointment;

import java.time.LocalTime;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;


public class BloodDonationAppointment {
    private int AppointmentId;
    private LocalDate AppointmentDate;
    private LocalTime AppointmentTime;
    private Duration AppointmentDuration;
    private String location;
    private String bloodType;
    private boolean firstTimeDonor;
    private int DonorId;

    public int getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        AppointmentId = appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return AppointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public Duration getAppointmentDuration() {
        return AppointmentDuration;
    }

    public void setAppointmentDuration(Duration appointmentDuration) {
        AppointmentDuration = appointmentDuration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public boolean isFirstTimeDonor() {
        return firstTimeDonor;
    }

    public void setFirstTimeDonor(boolean firstTimeDonor) {
        this.firstTimeDonor = firstTimeDonor;
    }

    public int getDonorId() {
        return DonorId;
    }

    public void setDonorId(int donorId) {
        this.DonorId = donorId;
    }
}
