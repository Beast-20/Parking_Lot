package com.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class PoliceOfficer {
    private int id;
    private List<ParkingLot> lots;

    public PoliceOfficer(int id, Lotowner o){
        this.id = id;
        lots = o.give_data_to_police();
    }

    public List<String> location_of_white_car(){
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<lots.size();i++){
            Car[] curr_lot = lots.get(i).get_lot();
            for(int j = 0;j<curr_lot.length;j++){
               if(curr_lot[j].get_colour()=="White"){
                 ans.add("Parking Lot:- "+i+", Position:- "+j);
               }
            }
        }
        return ans;
    }

    public List<String> info_of_blue_cars(){
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<lots.size();i++){
            Car[] curr_lot = lots.get(i).get_lot();
            for(int j = 0;j<curr_lot.length;j++){
               if(curr_lot[j].get_colour()=="Blue"){
                 ans.add("Parking Lot:- "+i+", Position:- "+j+", "+"Car number:- "+curr_lot[j].get_number_string()+", Attendant name:- "+curr_lot[j].get_attendant_name());
               }
            }
        }
        return ans;
    }

    public List<String> info_of_bmw_cars(){
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<lots.size();i++){
            Car[] curr_lot = lots.get(i).get_lot();
            for(int j = 0;j<curr_lot.length;j++){
               if(curr_lot[j].get_company()=="BMW"){
                 ans.add("Parking Lot:- "+i+", Position:- "+j+", "+"Car number:- "+curr_lot[j].get_number_string());
               }
            }
        }
        return ans;
    }

    public List<String> info_of_cars_based_on_time(){
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<lots.size();i++){
            Car[] curr_lot = lots.get(i).get_lot();
            for(int j = 0;j<curr_lot.length;j++){
            Duration duration = Duration.between(LocalTime.now(),curr_lot[j].get_in_time());
            long minutes = duration.toMinutes();
               if(minutes<=30){
                 ans.add("Parking Lot:- "+i+", Position:- "+j+", "+"Car number:- "+curr_lot[j].get_number_string());
               }
            }
        }
        return ans;
    }

    public List<String> info_of_small_and_handicapped_cars(){
        List<String> ans = new ArrayList<>();
        for(int i = 0;i<lots.size();i++){
            Car[] curr_lot = lots.get(i).get_lot();
            for(int j = 0;j<curr_lot.length;j++){
               if(curr_lot[j].get_type()=="Small" && curr_lot[j].get_handicap_status()==true){
                 ans.add("Parking Lot:- "+i+", Position:- "+j+", "+"Car number:- "+curr_lot[j].get_number_string());
               }
            }
        }
        return ans;
    }
}
