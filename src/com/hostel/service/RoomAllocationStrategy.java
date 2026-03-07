package com.hostel.service;

import com.hostel.model.Room;
import java.util.List;

public interface RoomAllocationStrategy {

    Room allocate(List<Room> rooms);

}
