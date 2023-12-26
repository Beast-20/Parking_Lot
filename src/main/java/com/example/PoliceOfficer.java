package com.example;

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
}
