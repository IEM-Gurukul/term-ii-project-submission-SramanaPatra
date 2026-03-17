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
        List<Complaint> complaints = new ArrayList<>();

        rooms.add(new Room(101,2));
        rooms.add(new Room(102,2));

        HostelService service = new HostelService(rooms);

        FeeReminder reminder = new FeeReminder(students);
        reminder.start();

        while(true){

            printHeader();

           System.out.println("1. Add Student");
            System.out.println("2. Allocate Room");
           System.out.println("3. Pay Fee");
           System.out.println("4. View Students");
           System.out.println("5. Register Complaint");
           System.out.println("6. View Complaints");
           System.out.println("7. Search Student");
            System.out.println("8. Exit");
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

                    Logger.getInstance().log("Student added " + name);
                    System.out.println("✔ Student added successfully!");

                    break;

                case 2:

                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();

                    boolean found = false;

                    for(Student s : students){

                        if(s.getId() == sid){

                            found = true;

                            try{

                                service.allocateRoom(s);

                                System.out.println("✔ Room allocated: " + s.getRoomNumber());

                            }
                            catch(Exception e){

                                System.out.println("❌ " + e.getMessage());

                            }

                        }

                    }

                    if(!found){
                        System.out.println("Student not found.");
                    }

                    break;

                case 3:

                    System.out.print("Student ID: ");
                    int pid = sc.nextInt();

                    boolean paid = false;

                    for(Student s : students){

                        if(s.getId() == pid){
                            s.payFee();
                            paid = true;
                            System.out.println("✔ Fee payment successful");
                        }

                    }

                    if(!paid){
                        System.out.println("Student not found.");
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
                                s.isFeePaid() ? "Paid" : "Pending");

                    }

                    break;

                case 5:

    System.out.print("Enter Student ID: ");
    int cid = sc.nextInt();
    sc.nextLine();

    boolean exists = false;

    for(Student s : students){
        if(s.getId() == cid){
            exists = true;
            break;
        }
    }

    if(!exists){
        System.out.println("❌ Student not found. Cannot register complaint.");
        break;
    }

    System.out.print("Enter complaint: ");
    String msg = sc.nextLine();

    complaints.add(new Complaint(cid, msg));

    Logger.getInstance().log("Complaint added by Student ID: " + cid);

    System.out.println("✔ Complaint registered successfully!");

    break;

     
                case 6:

    if(complaints.isEmpty()){
        System.out.println("No complaints found.");
        break;
    }

    System.out.println("\n------ Complaint List ------");

    for(Complaint c : complaints){
        System.out.println("Student ID: " + c.getStudentId());
        System.out.println("Complaint: " + c.getMessage());
        System.out.println("----------------------------");
    }

    break;
    case 7:

    System.out.print("Enter Student ID: ");
    int searchId = sc.nextInt();

    Optional<Student> result = students.stream()
            .filter(s -> s.getId() == searchId)
            .findFirst();

    if(result.isPresent()){
        Student s = result.get();

        System.out.println("\n--------- Student Details ---------");
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Room: " + s.getRoomNumber());
        System.out.println("Fee Status: " + (s.isFeePaid() ? "Paid" : "Pending"));
        System.out.println("-----------------------------------");

    } else {
        System.out.println("❌ Student not found.");
    }

    break;

                case 8:

                    System.out.println("\nThank you for using Hostel Management System.");
                    System.exit(0);

                default:

                    System.out.println("Invalid choice!");

            }

        }

    }

}