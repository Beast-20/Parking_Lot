package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    ParkingLot p1 = new ParkingLot(2);
    Car car1 = new Car(100,"Blue","Hyundai",false,"Small");
    Car car2 = new Car(101,"White","Honda",false,"Small");
    Car car3 = new Car(150,"Black","BMW",false,"Small");
    Car car4 = new Car(161,"White","Tata",true,"Small");
    Car car5 = new Car(165,"Yellow","Suzuki",false,"Large");
    Lotowner o1 = new Lotowner("Himanshu");
    ParkingAttendant attendant1 = new ParkingAttendant("a1", 1);
    ParkingLot p2 = new ParkingLot(2);

    public void before_each_after_uc7(){
        o1.add_attendant(attendant1);
        o1.add_lot(p1);
        o1.add_lot(p2);
    }

    public void before_each_after_uc12(){
        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
            attendant1.park_car(car3);
            attendant1.park_car(car4);
        }
        catch(ParkinglotFullException e){
            System.out.println(e.getMessage());
        }
    }
    //UC1
    @Test
    public void test_parking_car()
    {
        try {
            p1.park_car(car1,null);
            assertTrue(p1.check_car(car1));
        } catch (ParkinglotFullException e) {
            fail("Exception was not expected here");
        }

    }

    //UC2
    @Test
    public void test_unparking_car(){
        //Unparking parked car
        try{
            p1.park_car(car1,null);
            p1.unpark_car(car1);
            assertFalse(p1.check_car(car1));
        }
        catch(CarNotPresentException | ParkinglotFullException e){
            fail("Exception was not expected here");
        }
        
        //Unparking unparked car
        try{
            p1.unpark_car(car2);
            fail("Exception was expected here");
        }
        catch(CarNotPresentException e){
            assertEquals("Car not present in lot!",e.getMessage());
        }
    }
    
    //UC3
    @Test
    public void check_full_status(){

        //Checking status when lot is not full
        try {
            p1.park_car(car1,null);
        } catch (ParkinglotFullException e) {
            fail("Exception was not expected here");
        }

        assertFalse(p1.get_full_status());

        //Checking status when lot is full
        try {
            p1.park_car(car2,null);
        } catch (ParkinglotFullException e) {
            fail("Exception was not expected here");
        }

        assertTrue(p1.get_full_status());
    }

    //UC4 (Sharing data with airport security personal)
    @Test
    public void check_airport_staff_action(){
        AirportStaff u1 = new AirportStaff("Officer 1", 1);
        try{
            p1.park_car(car1,null);
           p1.park_car(car2,null);
        }
        catch(ParkinglotFullException e){
            fail("Exception was not expected");
        }

        assertTrue(u1.check_status(p1));
        
    }

    //UC5(Removing Full Status)
    @Test
    public void check_remove_full_status(){
        try{
           p1.park_car(car1,null);
           p1.park_car(car2,null);
        }
        catch(ParkinglotFullException e){
            fail("Exception was not expected");
        }
        
        try{
            p1.unpark_car(car1);
        }
        catch(CarNotPresentException e){
            fail("Exception was not expected here");
        }

        assertFalse(p1.get_full_status());
    }

    //UC6 (Parking Attendant parks car)
    @Test
    public void test_parking_attendant(){
        o1.add_attendant(attendant1);
        o1.add_lot(p1);

        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        assertTrue(p1.check_car(car1));
        assertTrue(p1.check_car(car2));
    }

    //UC7 (Car owner wants to go home)
    @Test
    public void test_owner_goes_home(){
       before_each_after_uc7();
       try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        try{
            attendant1.unpark_car(car1);
        }
        catch(CarNotPresentException e){
            fail("Exception was not expected here!");
        }

        assertFalse(attendant1.check_car(car1));
    }

    //UC8(Calculate parking fare(Minimum fare of 10 minutes))
    @Test
    public void check_fair(){
        before_each_after_uc7();
        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        try{
            o1.add_to_account(car1,attendant1.unpark_car(car1));
        }
        catch(CarNotPresentException e){
            fail("Exception was not expected here!");
        }

        assertEquals(10.0,o1.get_account().get(car1),0.1);
    }

    //UC9 (Evenly distribute cars)
    @Test
    public void check_even_distribution(){
        before_each_after_uc7();
        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
            attendant1.park_car(car3);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        assertTrue(p2.check_car(car3));
    }

    //UC10 (Park Handicaped person's car)
    @Test
    public void check_parking_handicapped(){
        before_each_after_uc7();
        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
            attendant1.park_car(car3);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        try{
            attendant1.unpark_car(car2);
            attendant1.unpark_car(car3);
        }
        catch(CarNotPresentException e){
            fail("Exception was not expected here");
        }
        
        try{
            attendant1.park_car(car4);
        }
        catch(ParkinglotFullException e){
            fail("Exception was not expected here");
        }
        
       assertTrue(p2.check_car(car4));
    }

    //UC11 (Try parking large car to its desired location)
    @Test
    public void check_large_type_parking(){
        before_each_after_uc7();
        try{
            attendant1.park_car(car1);
            attendant1.park_car(car2);
            attendant1.park_car(car3);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }

        try{
            attendant1.unpark_car(car2);
            attendant1.unpark_car(car3);
        }
        catch(CarNotPresentException e){
            fail("Exception was not expected here");
        }
        
        try{
            attendant1.park_car(car5);
        }
        catch(ParkinglotFullException e){
           fail("Exception was not expected here");
        }
        assertTrue(p2.check_car(car5));
   }

   //UC12 (Get location of all white car)
   @Test
   public void check_get_white_car_location(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Parking Lot:- 0, Position:- 1");
    expected_ans.add("Parking Lot:- 1, Position:- 1");
    assertEquals(officer1.location_of_white_car(), expected_ans);
   }

   //UC13 (Get info of blue cars)
   @Test
   public void check_get_blue_car_info(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Parking Lot:- 0, Position:- 0, Car number:- 100, Attendant name:- a1");
    assertEquals(officer1.info_of_blue_cars(), expected_ans);
   }

   //UC14 (Get info of all BMW parked)
   @Test
   public void check_get_bmw_car_info(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Parking Lot:- 1, Position:- 0, Car number:- 150");
    assertEquals(officer1.info_of_bmw_cars(), expected_ans);
   }

   //UC15 (Get info of cars parked in last 30 mins)
   @Test
   public void check_get_info_based_on_time(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Parking Lot:- 0, Position:- 0, Car number:- 100");
    expected_ans.add("Parking Lot:- 0, Position:- 1, Car number:- 101");
    expected_ans.add("Parking Lot:- 1, Position:- 0, Car number:- 150");
    expected_ans.add("Parking Lot:- 1, Position:- 1, Car number:- 161");
    assertEquals(officer1.info_of_cars_based_on_time(), expected_ans);
   }

   //UC16 (Get info of small and handicapped)
   @Test
   public void check_get_info_of_small_and_handicapped(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Parking Lot:- 1, Position:- 1, Car number:- 161");
    assertEquals(officer1.info_of_small_and_handicapped_cars(), expected_ans);
   }

   //UC17 (Get info of cars at particular lot)
   @Test
   public void check_get_info_of_cars_in_lot(){
    before_each_after_uc7();
    before_each_after_uc12();
    PoliceOfficer officer1 = new PoliceOfficer(1, o1);
    List<String> expected_ans = new ArrayList<>();
    expected_ans.add("Position:- 0, Car number:- 100, Car colour:- Blue, Car company:- Hyundai");
    expected_ans.add("Position:- 1, Car number:- 101, Car colour:- White, Car company:- Honda");
    assertEquals(officer1.info_of_cars_in_lot(p1), expected_ans);
   }
}
