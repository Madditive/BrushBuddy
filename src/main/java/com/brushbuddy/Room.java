package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Wall> walls;
    private String name; // Optional, but useful for identifying the room

    public Room(String name) {
        this.walls = new ArrayList<>();
        this.name = name;
    }

    // Adds a Wall object to the room
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    // Calculates the total paintable area for all walls in the room
    public double calculateTotalPaintableArea() {
        double totalArea = 0;
        for (Wall wall : walls) {
            totalArea += wall.calculatePaintableArea();
        }
        return totalArea;
    }

    // Optional getter for room name
    public String getName() {
        return name;
    }

}

