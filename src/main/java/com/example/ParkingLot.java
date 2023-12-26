package com.example;

import java.time.LocalTime;
import java.util.Arrays;

public class ParkingLot {
    private int capacity;
    private Car[] lot;
    private int cnt;
    private boolean is_full;



    public ParkingLot(int capacity){
        this.cnt = 0;
        this.capacity = capacity;
        this.lot = new Car[capacity];
    }

    public void park_car(Car car) throws ParkinglotFullException{
        if(cnt==capacity){
            throw new ParkinglotFullException("Parking lot is full!");
        }
        else{
            LocalTime in_time = LocalTime.now();
            car.set_in_time(in_time);
            for(int i = capacity-1;i>=0;i--){
                if(lot[i]==null){
                    lot[i] = car;
                    break;
                }
            }
            cnt++;

        }
    }

    public boolean check_car(Car car){
          return Arrays.asList(lot).contains(car);
    }

    public double unpark_car(Car car) throws CarNotPresentException{
          if(!Arrays.asList(lot).contains(car)){
            throw new CarNotPresentException("Car not present in lot!");
          }
          else{
            LocalTime in_time = car.get_in_time();
            LocalTime out_time = LocalTime.now();
            for(int i = 0;i<capacity;i++){
                if(lot[i]==car){
                    lot[i] = null;
                    break;
                }
            }
            cnt-=1;
            return 0.0;
          }
    }

    public boolean get_full_status(){
        if(cnt==capacity){
            is_full = true;
        }
        else{
            is_full = false;
        }

        return is_full;
    }

}
