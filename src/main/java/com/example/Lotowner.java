package com.example;

import java.util.ArrayList;
import java.util.List;

public class Lotowner {
    private String name;
    List<ParkingLot> lots;
    List<ParkingAttendant> parkingattendants;

    public Lotowner(String name){
        this.name = name;
        this.lots = new ArrayList<>();
        this.parkingattendants = new ArrayList<>();
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

}
