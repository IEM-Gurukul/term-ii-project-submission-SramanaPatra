package com.hostel.service;

import com.hostel.model.Room;
import java.util.List;

public class FirstAvailableStrategy implements RoomAllocationStrategy {

    public Room allocate(List<Room> rooms){

        for(Room r:rooms){

            if(r.hasSpace())
                return r;

        }

        return null;

    }
}
