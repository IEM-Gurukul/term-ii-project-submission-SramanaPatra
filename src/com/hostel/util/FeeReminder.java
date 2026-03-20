package com.hostel.util;

import com.hostel.model.Student;
import java.util.List;

public class FeeReminder extends Thread {

    private List<Student> students;

    public FeeReminder(List<Student> students){
        this.students = students;
    }

    @Override
    public void run(){

        while(true){

            try{
                Thread.sleep(10000); // check every 10 seconds
            } catch(InterruptedException e){
                System.out.println("Reminder thread interrupted");
            }

            for(Student s : students){

                if(!s.isFeePaid()){

                    System.out.println("\n🔔 Reminder: Fee pending for student ID "
                            + s.getId() + " (" + s.getName() + ")");
                }
            }
        }
    }
}