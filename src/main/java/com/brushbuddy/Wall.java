package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

// Class to represent non-paintable areas like windows and doors
class NonPaintableArea {
    private double height; // in millimeters
    private double width; // in millimeters

    // Constructor
    public NonPaintableArea(double height, double width) {
        this.height = height;
        this.width = width;
    }

    // Method to calculate the area of a non-paintable area
    public double getArea() {
        return (height/1000) * (width/1000);
    }
}

public class Wall {
    private double height; // in millimeters
    private double width; // in millimeters
    private List<NonPaintableArea> nonPaintableAreas;

    // Constructor to initialize the wall with height and width
    public Wall(double height, double width) {
        this.height = height;
        this.width = width;
        this.nonPaintableAreas = new ArrayList<>();
    }

    public void addNonPaintableArea(NonPaintableArea area) {
        nonPaintableAreas.add(area);
    }

    // Method to calculate the area of the wall
    public double calculatePaintableArea() {
        double totalArea = (height/1000) * (width/1000);
        for (NonPaintableArea area : nonPaintableAreas) {
            totalArea -= area.getArea();
        }
        return  totalArea;
    }
}