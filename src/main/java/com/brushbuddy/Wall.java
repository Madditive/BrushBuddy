package com.brushbuddy;

import java.util.ArrayList;
import java.util.List;

public abstract class Wall {
    // Abstract base class for all wall types
    private List<NonPaintableArea> nonPaintableAreas;

    public Wall() {
        this.nonPaintableAreas = new ArrayList<>();
    }

    public abstract double calculateArea();

    public void addNonPaintableArea(NonPaintableArea npArea) {
        nonPaintableAreas.add(npArea);
    }

    public double calculateTotalNonPaintableArea() {
        double totalNonPaintableArea = 0;
        for (NonPaintableArea npArea : nonPaintableAreas){
            totalNonPaintableArea += npArea.getArea();
        }
        return  totalNonPaintableArea;
    }

    public double calculatePaintableArea() {
        return calculateArea() - calculateTotalNonPaintableArea();
    }
}

class NonPaintableArea {
    // Represents an area within a wall that does not require painting (e.g., windows, doors)
    private double width; // Width in millimeters
    private double height; // Height in millimeters

    public NonPaintableArea(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        // returns the area in square meters
        return (width/1000) * (height/1000);
    }
}

class RectangularWall extends Wall {
    private double width; // Width in millimeters
    private double height; // Height in millimeters

    public RectangularWall(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return (width/1000) * (height/1000);
    }
}

class TriangularSection extends Wall {
    // Represents a triangular section of an irregular wall.
    private double base; // Base in millimeters
    private double height; // Height in millimeters

    public TriangularSection(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * (base/1000) * (height/1000);
    }
}

class RectangularSection extends Wall {
    // Represents a rectangular section of an irregular wall.
    private double width; // Width in millimeters
    private double height; // Width in millimeters

    public RectangularSection(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return (width/1000) * (height/1000);
    }
}

class IrregularWall extends Wall {
    // Represents an irregular wall composed of multiple sections, which may be triangular or rectangular
    private List<Wall> sections = new ArrayList<>();

    public void addSection(Wall section) {
        // Adds a section to the irregular wall.
        sections.add(section);
    }

    @Override
    public double calculateArea() {
        // Calculates the total area of the irregular wall by summing the areas of its sections
        double totalArea = 0;
        for (Wall section : sections) {
            totalArea += section.calculateArea();
        }
        return totalArea;
    }
}
