package com.hostel.service;

import com.hostel.model.*;
import java.util.*;

public class HostelService {

    private List<Room> rooms;

    public HostelService(List<Room> rooms){
        this.rooms = rooms;
    }

    public void allocateRoom(Student s) throws Exception {

        if(s.getRoomNumber() != 0){
            throw new Exception("Room already allocated!");
        }

        for(Room r : rooms){

            if(r.getOccupants().size() < r.getCapacity()){

                r.addStudent(s);
                s.setRoomNumber(r.getRoomNumber());
                return;
            }
        }

        throw new Exception("All rooms are full!");
    }
}