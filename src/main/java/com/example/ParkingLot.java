package com.example;

import java.time.Duration;
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

    public void park_car(Car car,String attendant_name) throws ParkinglotFullException{
        if(cnt==capacity){
            throw new ParkinglotFullException("Parking lot is full!");
        }
        else{
            LocalTime in_time = LocalTime.now();
            car.set_in_time(in_time);
            car.set_attendant_name(attendant_name);
            for(int i = 0;i<capacity;i++){
                if(lot[i]==null){
                    lot[i] = car;
                    break;
                }
            }
            cnt++;

        }
    }

    public boolean check_car(Car car){
          for(int i = 0;i<lot.length;i++){
            if(lot[i]==car){
                return true;
            }
          }
          return false;
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
            Duration duration = Duration.between(in_time, out_time);
            long minutes = duration.toMinutes();
            if(minutes<10){
                minutes = 10;
            }
            double fare = 1.0*minutes;
            return fare;
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

    public Car[] get_lot(){
        return lot;
    }

    public int get_cnt(){
        return cnt;
    }

}
