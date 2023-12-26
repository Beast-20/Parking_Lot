package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    ParkingLot p1 = new ParkingLot(2);
    Car car1 = new Car(100,"Red","Hyundai",false);
    Car car2 = new Car(101,"White","Honda",true);
    Lotowner o1 = new Lotowner("Himanshu");
    ParkingAttendant attendant1 = new ParkingAttendant("a1", 1);

    public void before_each_after_uc7(){
        o1.add_attendant(attendant1);
        o1.add_lot(p1);
    }
    //UC1
    @Test
    public void test_parking_car()
    {
        try {
            p1.park_car(car1);
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
            p1.park_car(car1);
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
            p1.park_car(car1);
        } catch (ParkinglotFullException e) {
            fail("Exception was not expected here");
        }

        assertFalse(p1.get_full_status());

        //Checking status when lot is full
        try {
            p1.park_car(car2);
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
            p1.park_car(car1);
           p1.park_car(car2);
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
           p1.park_car(car1);
           p1.park_car(car2);
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
}
