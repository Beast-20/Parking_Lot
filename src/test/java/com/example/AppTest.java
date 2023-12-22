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
    Car car1 = new Car(100,"Red","Hyundai");
    Car car2 = new Car(101,"White","Honda");
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
}
