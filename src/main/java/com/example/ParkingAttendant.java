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
        if(car.get_handicap_status()){
            int nearest = Integer.MAX_VALUE;
            int taget_lot_number = 0;
            for(int i = 0;i<list_of_parking_lot.size();i++){
                Car[] curr_lot = list_of_parking_lot.get(i).get_lot();
                for(int j = 0;j<curr_lot.length;j++){
                    if(curr_lot[j]==null){
                        if(j<nearest){
                            nearest = j;
                            taget_lot_number = i;
                            break;
                        }
                    }
                }
            }

            list_of_parking_lot.get(taget_lot_number).park_car(car,name);
        }
        else{
        if(car.get_type()=="Large"){
            int min_cnt = Integer.MAX_VALUE;
            int target_lot = 0;
            for(int i = 0;i<list_of_parking_lot.size();i++){
               if(list_of_parking_lot.get(i).get_cnt()<min_cnt){
                min_cnt = list_of_parking_lot.get(i).get_cnt();
                target_lot = i;
               }
            }
            list_of_parking_lot.get(target_lot).park_car(car,name);
        }
        else{
        for(int i = 0;i<list_of_parking_lot.size();i++){
            if(!list_of_parking_lot.get(i).get_full_status()){
                list_of_parking_lot.get(i).park_car(car,name);
                System.out.println("Car parked "+car.get_number());
                break;
            }
            if(i==list_of_parking_lot.size()-1){
                throw new ParkinglotFullException("Parking lot is full!");
            }
        }
    }
    }
    }

    public double unpark_car(Car car) throws CarNotPresentException{
        for(int i = 0;i<list_of_parking_lot.size();i++){
            if(list_of_parking_lot.get(i).check_car(car)){
                return list_of_parking_lot.get(i).unpark_car(car);
            }
            if(i==list_of_parking_lot.size()-1){
                throw new CarNotPresentException("Car not present in lot!");
            }
        }
        return 0.0;
    }

    public boolean check_car(Car car){
        for(int i = 0;i<list_of_parking_lot.size();i++){
            if(list_of_parking_lot.get(i).check_car(car)){
                return true;
            }
        }
        return false;
    }

    public void allot_attendant_lots(ParkingLot p){
       list_of_parking_lot.add(p);
    }

    

}
