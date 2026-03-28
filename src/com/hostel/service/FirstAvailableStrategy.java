package com.hostel.service;

import com.hostel.model.Room;
import java.util.List;

public class FirstAvailableStrategy implements RoomAllocationStrategy {

    @Override
    public Room allocate(List<Room> rooms) {

        for(Room r : rooms){

            if(r.hasSpace()){
                return r;   // return first available room
            }
        }

        return null; // no room available
    }
}
