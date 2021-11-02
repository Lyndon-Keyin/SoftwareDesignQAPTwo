package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;

import com.keyin.domain.donor.BloodDonor;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;

import net.bytebuddy.implementation.bytecode.Throw;
import org.joda.time.Years;
import java.time.LocalDate;
import java.util.*;



public class BloodDonationAppointmentManager {
    private Database database;

    //private int age;
    public BloodDonationAppointmentManager(Database database) {
        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) throws InvalidDonationSchedulingException {
        // database.bloodDonorId = blood donorId
        //then utilise that database object
        BloodDonationAppointment bloodDonationAppointment = database.getBloodDonationAppointment();
        BloodDonor bloodDonor = database.getBloodDonor();
//        BloodDonationAppointment bloodDonationAppointmentOne = database.getBloodDonationAppointment();
        //BloodDonationAppointment bloodDonationAppointmentTwo = database.getBloodDonationAppointmentTwo();


        if (bloodDonor.getId() == bloodDonorId) {
            LocalDate birthday = bloodDonor.getDOB();
            LocalDate now = LocalDate.now();
            Period year = Period.between(birthday, now);
            int duration = year.getYears();

            if (duration <= 18) {
                throw new InvalidDonationSchedulingException("donor too young");
            } else if (duration >= 80) {
                throw new InvalidDonationSchedulingException("donor too old");
            }
        }



        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();

        for (AppointmentSlot appointmentSlot : appointmentSlotList) {
            if (appointmentSlot.getBloodType().equalsIgnoreCase(bloodDonor.getBloodType())) {
            }
            else {
                throw new InvalidDonationSchedulingException("invalid blood type");
            }
        }
//        if (bloodDonationAppointment.getDonorId() == bloodDonorId) {
//            LocalDate appointmentDate = bloodDonationAppointment.getAppointmentDate();
//            LocalDate nextAppointmentDate = bloodDonor.getLastAppointment().plusDays(56);
//            if (appointmentDate.isBefore(nextAppointmentDate)) {
//                throw new InvalidDonationSchedulingException("too soon for another visit");
//            }
//        }
//
//        if (bloodDonationAppointment.getDonorId() == bloodDonorId) {
//            LocalDate appointmentDate2 = bloodDonationAppointment.getAppointmentDate();
//            LocalDate yearAppointmentDate2 = bloodDonor.getLastAppointment().plusDays(365);
//            if (appointmentDate2.isAfter(yearAppointmentDate2)) {
//                throw new InvalidDonationSchedulingException("you must re-book within a year.");
//            }
//        }

            if(bloodDonor.getLastAppointment() == null){
                BloodDonationAppointment bloodDonationAppointment1 = new BloodDonationAppointment();
                BloodDonor bloodDonor1 = new BloodDonor();

                bloodDonationAppointment1.setFirstTimeDonor(false);
                bloodDonor1.setLastAppointment(LocalDate.now().minusDays(57));
                System.out.println("It's a first time donor!!");
            }

            if(bloodDonor.getNextAppointment() != null){
                throw new InvalidDonationSchedulingException("you must re-book within a year.");
//                System.exit(1);
            }


//        if (bloodDonationAppointmentOne.getDonorId() == bloodDonorId){
//            if(bloodDonor.bloodDonationAppointment)
//            //check to see if lastappointment date is null
//        }


//iuf blood doner is firsttime, false if statement...

        return bloodDonationAppointment;
    }
}
