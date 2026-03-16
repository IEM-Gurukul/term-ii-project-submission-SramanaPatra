package com.hostel.main;

import com.hostel.model.*;
import com.hostel.service.*;
import com.hostel.util.*;

import java.util.*;

public class MainApp {
    public static void printHeader(){
    System.out.println("\n=================================");
    System.out.println(" SMART HOSTEL MANAGEMENT SYSTEM ");
    System.out.println("=================================");
}

    public static void main(String[] args){

        DatabaseSetup.initialize();

        Scanner sc = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room(101,2));
        rooms.add(new Room(102,2));

        HostelService service = new HostelService(rooms);

        FeeReminder reminder = new FeeReminder(students);
        reminder.start();

        while(true){

            System.out.println("\n=================================");
            System.out.println("   SMART HOSTEL MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. Allocate Room");
            System.out.println("3. Pay Fee");
            System.out.println("4. View Students");
            System.out.println("5. Register Complaint");
            System.out.println("6. Exit");
            System.out.println("=================================");
            System.out.print("Enter Choice: ");
            if(!sc.hasNextInt()){
               System.out.println("Invalid input! Please enter a number.");
               sc.next();
               continue;
            }

            int ch = sc.nextInt();

            switch(ch){

                case 1:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    students.add(new Student(id,name));

                    Logger.getInstance().log("Student added "+name);
                    System.out.println("✔ Student added successfully!");

                    break;

                case 2:

                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();

                    for(Student s : students){

                        if(s.getId() == sid){

                            try{

                                service.allocateRoom(s);

                                System.out.println("✔ Room allocated: " + s.getRoomNumber());

                            }
                            catch(Exception e){

                                System.out.println(e.getMessage());

                            }

                        }

                    }

                    break;

                case 3:

                    System.out.print("Student ID: ");
                    int pid = sc.nextInt();

                    for(Student s : students){

                        if(s.getId() == pid){
                            s.payFee();
                            System.out.println("✔ Fee payment successful");
                        }

                    }

                    break;

                case 4:

                    System.out.println("\n------------------------------------------------");
                    System.out.printf("%-5s %-20s %-10s %-10s\n","ID","Name","Room","Fee");
                    System.out.println("------------------------------------------------");

                    for(Student s : students){

                        System.out.printf("%-5d %-20s %-10d %-10s\n",
                                s.getId(),
                                s.getName(),
                                s.getRoomNumber(),
                                s.isFeePaid());

                    }

                    break;

                case 5:

                    System.out.println("Complaint system coming soon...");

                    break;

                case 6:

                    System.out.println("\nThank you for using Hostel Management System.");
                    System.exit(0);
                case 7:
                    System.out.print("Enter Student ID: ");
                    int searchId = sc.nextInt();

                    for(Student s : students){
                     if(s.getId() == searchId){
                    System.out.println("Student Found: "+s.getName());
                    }
                    }
                    break;

                default:

                    System.out.println("Invalid choice!");

            }

        }

    }

}