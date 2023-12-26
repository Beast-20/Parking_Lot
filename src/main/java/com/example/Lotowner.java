package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lotowner {
    private String name;
    List<ParkingLot> lots;
    List<ParkingAttendant> parkingattendants;
    private HashMap<Car,Double> account;

    public Lotowner(String name){
        this.name = name;
        this.lots = new ArrayList<>();
        this.parkingattendants = new ArrayList<>(); 
        this.account = new HashMap<>();
    }

    public void add_attendant(ParkingAttendant a){
        parkingattendants.add(a);
    }

    public void add_lot(ParkingLot p){
        lots.add(p);
        for(int i = 0;i<parkingattendants.size();i++){
            parkingattendants.get(i).allot_attendant_lots(p);
        }
    }

    public void add_to_account(Car car, double fare){
        if(account.containsKey(car)){
            double new_fare = account.get(car)+fare;
            account.put(car, new_fare);
        }
        else{
            account.put(car, fare);
        }
    }

    public HashMap<Car,Double> get_account(){
        return account;
    }
    
    public List<ParkingLot> give_data_to_police(){
        return lots;
    }

}
