package com.example;

import java.time.LocalTime;

public class Car {
    private int number;
    private String colour;
    private String company;
    private LocalTime in_time;

    public Car(int number, String colour, String company){
        this.number = number;
        this.colour = colour;
        this.company = company;
    }

    public void set_in_time(LocalTime in_time){
        this.in_time = in_time;
    }

    public LocalTime get_in_time(){
        return in_time;
    }
    
}
