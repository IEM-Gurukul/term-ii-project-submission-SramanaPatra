package com.hostel.model;

public class Student extends User {

    private int roomNumber;
    private boolean feePaid;

    public Student(int id,String name){
        super(id,name);
        feePaid = false;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(int r){
        roomNumber = r;
    }

    public boolean isFeePaid(){
        return feePaid;
    }

    public void payFee(){
        feePaid = true;
    }

    @Override
    public void showMenu(){
        System.out.println("Student Menu");
    }
}