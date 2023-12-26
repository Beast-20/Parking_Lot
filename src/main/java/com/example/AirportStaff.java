package com.example;

public class AirportStaff {
    private String name;
    private int id;

    public AirportStaff(String name, int id){
        this.id = id;
        this.name = name;
    }

    public boolean check_status(ParkingLot p){
        return p.get_full_status();
    }

}
