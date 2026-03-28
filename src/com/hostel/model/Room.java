package com.hostel.model;

import java.util.*;

public class Room {

    private int roomNumber;
    private int capacity;
    private List<Student> occupants;

    public Room(int roomNumber, int capacity){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.occupants = new ArrayList<>();
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public int getCapacity(){
        return capacity;
    }

    public List<Student> getOccupants(){
        return occupants;
    }

    public boolean hasSpace(){
        return occupants.size() < capacity;
    }

    public void addStudent(Student s){
        if(hasSpace()){
            occupants.add(s);
        } else {
            throw new RuntimeException("Room is full");
        }
    }
}