package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<Room> rooms;

    public Project(){
        this.rooms = new ArrayList<>();
    }

    // Method to add a wall to the project
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Getter for the rooms list
    public List<Room> getRooms() {
        return rooms;
    }

    // Method to calculate the total paintable area of all rooms in the project
    public double calculateTotalPaintable() {
        double totalArea = 0;
        for (Room room : rooms) {
            totalArea += room.calculateTotalPaintableArea();
        }
        return totalArea;
    }
}
