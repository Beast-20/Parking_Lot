package com.example;

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
}
