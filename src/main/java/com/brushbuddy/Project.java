package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

public class Project {
    // Represents a painting project that can contain multiple rooms.
    private List<Room> rooms; // a List to hold the rooms within the project

    public Project(){
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }


    public double calculateTotalPaintableArea() {
        // Method to calculate the total paintable area of all rooms in the project
        double totalArea = 0;
        for (Room room : rooms) {
            totalArea += room.calculateTotalPaintableArea();
        }
        return totalArea;
    }

    // Getter for the rooms list
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return STR."Project[rooms=\{rooms.size()}]";
    }
}
