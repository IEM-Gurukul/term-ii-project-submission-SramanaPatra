package com.hostel.util;

import com.hostel.model.Student;
import java.util.List;

public class FeeReminder extends Thread {

    List<Student> students;

    public FeeReminder(List<Student> students){
        this.students = students;
    }

    public void run(){

        int count = 0;

        while(count < 5){   // run reminder only 5 times

            for(Student s : students){

                if(!s.isFeePaid())
                    System.out.println("\n[Reminder] Fee pending for: " + s.getName());

            }

            count++;

            try{
                Thread.sleep(30000);
            }catch(Exception e){}

        }

    }
}