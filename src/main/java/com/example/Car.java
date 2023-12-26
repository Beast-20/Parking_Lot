package com.example;

import java.time.LocalTime;

public class Car {
    private int number;
    private String colour;
    private String company;
    private LocalTime in_time;
    private boolean is_handicap;
    private String type;

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

    public String get_colour(){
        return colour;
    }
    
}
