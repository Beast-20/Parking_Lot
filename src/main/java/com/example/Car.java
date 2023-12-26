package com.example;

import java.time.LocalTime;

public class Car {
    private int number;
    private String colour;
    private String company;
    private LocalTime in_time;
    private boolean is_handicap;
    private String type;
    private String attendant_name;

    public Car(int number, String colour, String company, boolean is_handicap, String type){
        this.number = number;
        this.colour = colour;
        this.company = company;
        this.is_handicap = is_handicap;
        this.type = type;
    }

    public void set_in_time(LocalTime in_time){
        this.in_time = in_time;
    }

    public LocalTime get_in_time(){
        return in_time;
    }

    public boolean get_handicap_status(){
        return is_handicap;
    }
    
    public String get_type(){
        return type;
    }

    public int get_number(){
        return number;
    }

    public String get_number_string(){
        return number+"";
    }

    public String get_colour(){
        return colour;
    }

    public void set_attendant_name(String name){
        this.attendant_name = name;
    }

    public String get_attendant_name(){
        return attendant_name;
    }
    
}
