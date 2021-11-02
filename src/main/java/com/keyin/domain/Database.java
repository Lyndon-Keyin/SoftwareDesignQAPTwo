package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<AppointmentSlot>getAppointmentSlots(){
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("1 Nice Place Portugal Cove NL");
        appointmentSlot.setDate(LocalDate.of(2021,11,01));
        appointmentSlot.setStartTime(LocalTime.of(9,0));
        appointmentSlot.setEndTime(LocalTime.of(10,0));
        appointmentSlot.setBloodType("A");

        appointmentSlots.add(appointmentSlot);

        return appointmentSlots;
    }

    public BloodDonor getBloodDonor(){
        //ArrayList<BloodDonor> bloodDonors = new ArrayList<BloodDonor>();
        BloodDonor bloodDonor = new BloodDonor();
        bloodDonor.setId(1);
        bloodDonor.setFirstNAme("Lyndon");
        bloodDonor.setLastName("Loveys");
        bloodDonor.setBloodType("A");
        bloodDonor.setDOB(LocalDate.of(1986,02,03 ));
        bloodDonor.setLastAppointment(LocalDate.of(2021,01,01));
        bloodDonor.setNextAppointment(LocalDate.now().plusDays(56));

        return bloodDonor;
    }

    public BloodDonationAppointment getBloodDonationAppointment(){
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();
        bloodDonationAppointment.setAppointmentId(1);
        bloodDonationAppointment.setAppointmentDate(LocalDate.of(2019,11,01));
        bloodDonationAppointment.setAppointmentTime(LocalTime.of(9,0));
        bloodDonationAppointment.setAppointmentDuration(Duration.ofHours(1));//maybe more than 59mins long and int
        bloodDonationAppointment.setLocation("1 Nice Place Portugal Cove NL");
        bloodDonationAppointment.setBloodType("A+");
        bloodDonationAppointment.setFirstTimeDonor(true);
        bloodDonationAppointment.setDonorId(1);

        return bloodDonationAppointment;
    }


//    public BloodDonationAppointment getBloodDonationAppointmentTwo(){
//        BloodDonationAppointment bloodDonationAppointmentTwo = new BloodDonationAppointment();
//        bloodDonationAppointmentTwo.setAppointmentId(2);
//        bloodDonationAppointmentTwo.setAppointmentDate(LocalDate.of(2024,11,01));
//        bloodDonationAppointmentTwo.setAppointmentTime(LocalTime.of(9,0));
//        bloodDonationAppointmentTwo.setAppointmentDuration(Duration.ofHours(1));//maybe more than 59mins long and int
//        bloodDonationAppointmentTwo.setLocation("3 Nice Place Portugal Cove NL");
//        bloodDonationAppointmentTwo.setBloodType("B+");
//        bloodDonationAppointmentTwo.setFirstTimeDonor(false);
//        bloodDonationAppointmentTwo.setDonorId(2);
//
//        return bloodDonationAppointmentTwo;
//    }
}
