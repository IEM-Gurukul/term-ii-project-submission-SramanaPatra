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

        // 🔥 Dynamic Room Creation
        System.out.print("Enter number of rooms: ");
        int roomCount = sc.nextInt();

        for(int i = 0; i < roomCount; i++){
            rooms.add(new Room(101 + i, 2));
        }

        HostelService service = new HostelService(rooms);

        // Fee Reminder Thread
        FeeReminder reminder = new FeeReminder(students);
        reminder.start();

        // Logger
        Logger logger = Logger.getInstance();

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
                logger.log("Invalid menu input");
                sc.next();
                continue;
            }

            int ch = sc.nextInt();

            switch(ch){

                // 🔹 Add Student
                case 1:

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    students.add(new Student(id,name));

                    logger.log("Student added: " + name);
                    System.out.println("✔ Student added successfully!");

                    break;

                // 🔹 Allocate Room
                case 2:

                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    boolean found = false;

                    for(Student s : students){

                        if(s.getId() == sid){

                            found = true;

                            if(s.getRoomNumber() != 0){
                                System.out.println("⚠ Room already allocated.");
                                logger.log("Duplicate allocation attempt for student " + sid);
                                break;
                            }

                            try{
                                service.allocateRoom(s);

                                logger.log("Room allocated to student ID: " + sid);
                                System.out.println("✔ Room allocated: " + s.getRoomNumber());

                            } catch(Exception e){

                                logger.log("Room allocation failed: " + e.getMessage());
                                System.out.println("❌ " + e.getMessage());
                            }
                        }
                    }

                    if(!found){
                        logger.log("Student not found for allocation: " + sid);
                        System.out.println("Student not found.");
                    }

                    break;

                // 🔹 Pay Fee
                case 3:

                    System.out.print("Enter Student ID: ");
                    int pid = sc.nextInt();

                    boolean paid = false;

                    for(Student s : students){

                        if(s.getId() == pid){

                            if(s.getRoomNumber() == 0){
                                System.out.println("❌ Cannot pay fee. Room not allocated.");
                                logger.log("Fee payment failed: No room for student " + pid);
                                paid = true;
                                break;
                            }

                            if(s.isFeePaid()){
                                System.out.println("⚠ Fee already paid.");
                                logger.log("Fee already paid by student " + pid);
                                paid = true;
                                break;
                            }

                            s.payFee();
                            paid = true;

                            logger.log("Fee paid by student ID: " + pid);
                            System.out.println("✔ Fee payment successful");
                        }
                    }

                    if(!paid){
                        logger.log("Fee payment failed: Student not found " + pid);
                        System.out.println("Student not found.");
                    }

                    break;

                // 🔹 View Students
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

                    logger.log("Viewed student list");

                    break;

                // 🔹 Register Complaint
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
                        logger.log("Complaint failed: Student not found " + cid);
                        System.out.println("❌ Student not found.");
                        break;
                    }

                    System.out.print("Enter Complaint: ");
                    String msg = sc.nextLine();

                    complaints.add(new Complaint(cid, msg));

                    logger.log("Complaint registered by student ID: " + cid);
                    System.out.println("✔ Complaint registered successfully!");

                    break;

                // 🔹 View Complaints
                case 6:

                    if(complaints.isEmpty()){
                        System.out.println("No complaints found.");
                        logger.log("Viewed complaints: none found");
                        break;
                    }

                    System.out.println("\n------ Complaint List ------");

                    for(Complaint c : complaints){
                        System.out.println("Student ID: " + c.getStudentId());
                        System.out.println("Complaint: " + c.getMessage());
                        System.out.println("----------------------------");
                    }

                    logger.log("Viewed all complaints");

                    break;

                // 🔹 Search Student
                case 7:

                    System.out.print("Enter Student ID: ");
                    int searchId = sc.nextInt();

                    boolean studentFound = false;

                    for(Student s : students){

                        if(s.getId() == searchId){

                            studentFound = true;

                            System.out.println("\n--------- Student Details ---------");
                            System.out.println("ID: " + s.getId());
                            System.out.println("Name: " + s.getName());
                            System.out.println("Room: " + s.getRoomNumber());
                            System.out.println("Fee Status: " + (s.isFeePaid() ? "Paid" : "Pending"));
                            System.out.println("-----------------------------------");

                            logger.log("Student searched: " + searchId);
                            break;
                        }
                    }

                    if(!studentFound){
                        logger.log("Search failed: Student not found " + searchId);
                        System.out.println("❌ Student not found.");
                    }

                    break;

                // 🔹 Exit
                case 8:

                    logger.log("System exited");
                    System.out.println("\nThank you for using Hostel Management System.");
                    System.exit(0);

                default:

                    logger.log("Invalid menu choice: " + ch);
                    System.out.println("Invalid choice!");
            }
        }
    }
}