package com.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant {
    private String name;
    private int id;
    List<ParkingLot> list_of_parking_lot;

    public ParkingAttendant(String name, int id){
        this.name = name;
        this.id = id;
        this.list_of_parking_lot = new ArrayList<>();
    }

    public void park_car(Car car) throws ParkinglotFullException{
        for(int i = 0;i<list_of_parking_lot.size();i++){
            if(!list_of_parking_lot.get(i).get_full_status()){
                list_of_parking_lot.get(i).park_car(car);
                break;
            }
        }
    }

    public void allot_attendant_lots(ParkingLot p){
       list_of_parking_lot.add(p);
    }
    

}
