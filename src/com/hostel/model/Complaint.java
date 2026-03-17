package com.hostel.model;

public class Complaint {

    private int studentId;
    private String message;

    public Complaint(int studentId, String message){
        this.studentId = studentId;
        this.message = message;
    }

    public int getStudentId(){
        return studentId;
    }

    public String getMessage(){
        return message;
    }
}
