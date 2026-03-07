package com.hostel.service;

import com.hostel.model.*;
import com.hostel.exception.NoRoomAvailableException;
import java.util.*;

public class HostelService {

    private List<Room> rooms;
    private RoomAllocationStrategy strategy;

    public HostelService(List<Room> rooms){

        this.rooms = rooms;
        strategy = new FirstAvailableStrategy();

    }

    public void allocateRoom(Student s) throws NoRoomAvailableException {

        Room room = strategy.allocate(rooms);

        if(room == null)
            throw new NoRoomAvailableException("No room available");

        room.allocate();
        s.setRoomNumber(room.getRoomNumber());
    }

    // VIEW STUDENTS METHOD (separate method)
    public void viewStudents(List<Student> students){

        System.out.println("\n----- Student List -----");

        for(Student s : students){

            System.out.println(
                "ID: " + s.getId() +
                " | Name: " + s.getName() +
                " | Room: " + s.getRoomNumber() +
                " | Fee Paid: " + s.isFeePaid()
            );

        }
    }
}