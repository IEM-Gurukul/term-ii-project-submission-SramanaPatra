package com.hostel.model;

public class Room {

    private int roomNumber;
    private int capacity;
    private int occupied;

    public Room(int roomNumber,int capacity){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.occupied = 0;
    }

    public boolean hasSpace(){
        return occupied < capacity;
    }

    public void allocate(){
        occupied++;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
}