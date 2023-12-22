package com.example;

import java.time.LocalTime;
import java.util.HashSet;

public class ParkingLot {
    private int capacity;
    HashSet<Car> lot;
    int cnt;

    public ParkingLot(int capacity){
        this.cnt = 0;
        this.capacity = capacity;
        this.lot = new HashSet<>();
    }

    public void park_car(Car car) throws ParkinglotFullException{
        if(cnt==capacity){
            throw new ParkinglotFullException("Parking lot is full!");
        }
        else{
            LocalTime in_time = LocalTime.now();
            car.set_in_time(in_time);
            lot.add(car);
            cnt++;

        }
    }

    public boolean check_car(Car car){
          return lot.contains(car);
    }

    public double unpark_car(Car car) throws CarNotPresentException{
          if(!lot.contains(car)){
            throw new CarNotPresentException("Car not present in lot!");
          }
          else{
            LocalTime in_time = car.get_in_time();
            LocalTime out_time = LocalTime.now();
            lot.remove(car);
            return 0.0;
          }
    }

}
