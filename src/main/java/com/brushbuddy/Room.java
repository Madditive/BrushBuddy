package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

public class Room {
    // Represents a room that can contain multiple walls.
    private List<Wall> walls; // A list to hold the walls of the room
    private String name; // The name of the room for identification

    public Room(String name) {
        this.walls = new ArrayList<>();
        this.name = name;
    }

    public void addWall(Wall wall) {
        // Adds a Wall object to the room
        walls.add(wall);
    }

    public double calculateTotalPaintableArea() {
        // Calculates the total paintable area for all walls in the room
        double totalPaintableArea = 0;
        for (Wall wall : walls) {
            totalPaintableArea += wall.calculatePaintableArea();
        }
        return totalPaintableArea;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return STR."Room[name=\{name}, walls=\{walls.size()}]";
    }

}

