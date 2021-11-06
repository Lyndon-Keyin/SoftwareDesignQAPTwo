package com.keyin.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BloodDonationAppointmentManagerTest {

    @ExtendWith(MockitoExtension.class)

    @Mock
    Database dataBaseMock;

    @ Test
    void GoodTest(){
        BloodDonor bloodDonorGoodCandidate = new BloodDonor();

        BloodDonationAppointment bloodDonationAppointment1 = new BloodDonationAppointment();
        bloodDonationAppointment1.setAppointmentId(1);
        bloodDonationAppointment1.setAppointmentDate(LocalDate.of(2021,11,12));
        bloodDonationAppointment1.setAppointmentTime(LocalTime.of(9,0));
        bloodDonationAppointment1.setAppointmentDuration(Duration.ofHours(1));//maybe more than 59mins long and int
        bloodDonationAppointment1.setLocation("1 Nice Place Portugal Cove NL");
        bloodDonationAppointment1.setBloodType("A");
        bloodDonationAppointment1.setFirstTimeDonor(false);
        bloodDonationAppointment1.setDonorId(1);

        when(dataBaseMock.getBloodDonationAppointment()).thenReturn(bloodDonationAppointment1);


        bloodDonorGoodCandidate.setFirstNAme("Lyndon");
        bloodDonorGoodCandidate.setLastName("Loveys");
        bloodDonorGoodCandidate.setBloodType("A");
        bloodDonorGoodCandidate.setDOB(LocalDate.of( 1986 , 02 , 11 ));
        bloodDonorGoodCandidate.setId(1);
        bloodDonorGoodCandidate.setLastAppointment(LocalDate.of(2021,06,07));

        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorGoodCandidate);

//        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
//
//        AppointmentSlot appointmentSlot = new AppointmentSlot();
//        appointmentSlot.setId(1);
//        appointmentSlot.setLocation("1 Nice Place Portugal Cove NL");
//        appointmentSlot.setDate(LocalDate.of(2021,11,01));
//        appointmentSlot.setStartTime(LocalTime.of(9,0));
//        appointmentSlot.setEndTime(LocalTime.of(10,0));
//        appointmentSlot.setBloodType("A");
//        appointmentSlots.add(appointmentSlot);
//
//        when(dataBaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager BDManager = new BloodDonationAppointmentManager(dataBaseMock);
        try {
            BloodDonationAppointment bloodDonationAppointment = BDManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("donor is not a good candidate"));
        }

    }

    @Test
    void TestMatchBloodType(){
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstNAme("Lyndon");
        bloodDonorTooYoung.setLastName("Loveys");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDOB(LocalDate.of( 1986 , 02 , 11 ));
        bloodDonorTooYoung.setId(1);

        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorTooYoung);

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. st. John's NL");
        appointmentSlot.setBloodType("A");
        appointmentSlots.add(appointmentSlot);

        AppointmentSlot appointmentSlotB = new AppointmentSlot();
        appointmentSlotB.setId(1);
        appointmentSlotB.setLocation("3 Pippy Place st. John's NL");
        appointmentSlotB.setBloodType("B");
        appointmentSlots.add(appointmentSlotB);

        when(dataBaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(dataBaseMock);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);

        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("invalid blood type"));
        }

    }



    @Test
    void TestForRuleUnderAge(){

        BloodDonor bloodDonorLessThan18 = new BloodDonor();
        bloodDonorLessThan18.setId(1);
        bloodDonorLessThan18.setFirstNAme("Lyndon");
        bloodDonorLessThan18.setLastName("Loveys");
        bloodDonorLessThan18.setBloodType("A+");
        bloodDonorLessThan18.setDOB(LocalDate.of(2008,02,03 ));
        //assertNotNull(databasemock);
        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorLessThan18);
        BloodDonationAppointmentManager BDManager = new BloodDonationAppointmentManager(dataBaseMock);
    //    Assert.assertNull(BDManager.bookAppointment(1));

        try {
            BloodDonationAppointment bloodDonationAppointment = BDManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("donor too young"));
        }

    }

    @Test
    void TestForRuleOverAge(){

        BloodDonor bloodDonorMoreThan80 = new BloodDonor();
        bloodDonorMoreThan80.setId(1);
        bloodDonorMoreThan80.setFirstNAme("Lyndon");
        bloodDonorMoreThan80.setLastName("Loveys");
        bloodDonorMoreThan80.setBloodType("A+");
        bloodDonorMoreThan80.setDOB(LocalDate.of(1910,02,03 ));
        //assertNotNull(databasemock);
        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorMoreThan80);
        BloodDonationAppointmentManager BDManager = new BloodDonationAppointmentManager(dataBaseMock);
//        Assert.assertNull(BDManager.bookAppointment(1));

        try {
            BloodDonationAppointment bloodDonationAppointment = BDManager.bookAppointment(1);
            //Assertions.assertNotNull(bloodDonationAppointment);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("donor too old"));
        }

    }

    @Test
    void TestNoMatchBloodType(){
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstNAme("Lyndon");
        bloodDonorTooYoung.setLastName("Loveys");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDOB(LocalDate.of( 1986 , 02 , 11 ));
        bloodDonorTooYoung.setId(1);

        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorTooYoung);

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. st. John's NL");
        appointmentSlot.setBloodType("B");
        appointmentSlots.add(appointmentSlot);

        when(dataBaseMock.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(dataBaseMock);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);

        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("invalid blood type"));
        }

    }

    @Test
    void Test56DayRule(){
        BloodDonor lastAppointment = new BloodDonor();
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();
        bloodDonationAppointment.setAppointmentId(1);
        bloodDonationAppointment.setAppointmentDate(LocalDate.of(2021,11,12));
        bloodDonationAppointment.setAppointmentTime(LocalTime.of(9,0));
        bloodDonationAppointment.setAppointmentDuration(Duration.ofHours(1));//maybe more than 59mins long and int
        bloodDonationAppointment.setLocation("1 Nice Place Portugal Cove NL");
        bloodDonationAppointment.setBloodType("A+");
        bloodDonationAppointment.setFirstTimeDonor(false);
        bloodDonationAppointment.setDonorId(1);

        when(dataBaseMock.getBloodDonationAppointment()).thenReturn(bloodDonationAppointment);

        lastAppointment.setLastAppointment(LocalDate.of(2021,9,15));

        when(dataBaseMock.getBloodDonor()).thenReturn(lastAppointment);

        BloodDonationAppointmentManager BDManager = new BloodDonationAppointmentManager(dataBaseMock);
   //     Assert.assertNull(BDManager.bookAppointment(1));
        try {
            BloodDonationAppointment bloodDonationAppointmentOne = BDManager.bookAppointment(1);
            //Assertions.assertNotNull(bloodDonationAppointment);
        } catch (InvalidDonationSchedulingException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("Appointment after 56 days"));
        }
    }

    @Test
    void TestMoreThanOneYearRule(){
        BloodDonor lastAppointmentOne = new BloodDonor();
        BloodDonationAppointment bloodDonationAppointmentTwo = new BloodDonationAppointment();
        bloodDonationAppointmentTwo.setAppointmentId(2);
        bloodDonationAppointmentTwo.setAppointmentDate(LocalDate.of(2021,11,15));
        bloodDonationAppointmentTwo.setAppointmentTime(LocalTime.of(9,0));
        bloodDonationAppointmentTwo.setAppointmentDuration(Duration.ofHours(1));//maybe more than 59mins long and int
        bloodDonationAppointmentTwo.setLocation("3 Nice Place Portugal Cove NL");
        bloodDonationAppointmentTwo.setBloodType("B+");
        bloodDonationAppointmentTwo.setFirstTimeDonor(false);
        bloodDonationAppointmentTwo.setDonorId(2);

        when(dataBaseMock.getBloodDonationAppointment()).thenReturn(bloodDonationAppointmentTwo);

        lastAppointmentOne.setLastAppointment(LocalDate.of(2020,11,30));

        when(dataBaseMock.getBloodDonor()).thenReturn(lastAppointmentOne);

        BloodDonationAppointmentManager BDManager2 = new BloodDonationAppointmentManager(dataBaseMock);

        try {
            BloodDonationAppointment bloodDonationAppointment = BDManager2.bookAppointment(2);
            //Assertions.assertNotNull(bloodDonationAppointment);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("appointment is within a year"));
        }
    }


    @Test
    void DoubleBookAppointment(){
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();
        bloodDonationAppointment.setAppointmentId(1);
        bloodDonationAppointment.setAppointmentDate(LocalDate.of(2021,11,12));
        bloodDonationAppointment.setAppointmentTime(LocalTime.of(9,0));
        bloodDonationAppointment.setAppointmentDuration(Duration.ofHours(1));
        bloodDonationAppointment.setLocation("1 Nice Place Portugal Cove NL");
        bloodDonationAppointment.setBloodType("A+");
        bloodDonationAppointment.setFirstTimeDonor(false);
        bloodDonationAppointment.setDonorId(1);

        when(dataBaseMock.getBloodDonationAppointment()).thenReturn(bloodDonationAppointment);


        BloodDonor bloodDonorSecondAppointment = new BloodDonor();
        bloodDonorSecondAppointment.setNextAppointment(LocalDate.of(2021, 12,12));
        bloodDonorSecondAppointment.setLastAppointment(LocalDate.of(2021,9,02));

        when(dataBaseMock.getBloodDonor()).thenReturn(bloodDonorSecondAppointment);

        BloodDonationAppointmentManager BDManager3 = new BloodDonationAppointmentManager(dataBaseMock);

        try{
            BloodDonationAppointment bloodDonationAppointment3 = BDManager3.bookAppointment(1);
        }catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Cannot double booking your appointment"));
        }
    }


    @Test
    void FirstTimeDonor(){
        BloodDonor firstTimer = new BloodDonor();

        firstTimer.setFirstNAme("Lyndon");
        firstTimer.setLastName("Loveys");
        firstTimer.setBloodType("A");
        firstTimer.setDOB(LocalDate.of( 1986 , 02 , 11 ));
        firstTimer.setId(1);

        when(dataBaseMock.getBloodDonor()).thenReturn(firstTimer);

        BloodDonationAppointment bloodDonationFirstTimer = new BloodDonationAppointment();

        bloodDonationFirstTimer.setFirstTimeDonor(true);

        when(dataBaseMock.getBloodDonationAppointment()).thenReturn(bloodDonationFirstTimer);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(dataBaseMock);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
            System.out.println("first Timer");
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("first timer"));
        }
    }
}
