package com.brushbuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room("Test Room");
    }

    @Test
    void testAddRectangularWall() {
        Wall wall = new RectangularWall(2000, 3000); // 6 square meters
        room.addWall(wall);
        assertEquals(6.0, room.calculateTotalPaintableArea(), "Adding a rectangular wall should correctly update the room's total paintable area.");
    }

    @Test
    void testAddMultipleWalls() {
        room.addWall(new RectangularWall(2000, 3000)); // 6m^2
        room.addWall(new TriangularSection(3000, 4000)); // 6m^2 triangle
        assertEquals(12.0, room.calculateTotalPaintableArea(), "The room's total paintable area should sum the areas of multiple walls.");
    }

    @Test
    void testIrregularWallArea() {
        IrregularWall irregularWall = new IrregularWall();
        irregularWall.addSection(new RectangularSection(1000, 2000)); // 2m^2
        irregularWall.addSection(new TriangularSection(1000, 2000)); // 1m^2
        room.addWall(irregularWall);
        assertEquals(3.0, room.calculateTotalPaintableArea(), "Adding an irregular wall should correctly update the room's total paintable area.");
    }

    @Test
    void testRoomName() {
        assertEquals("Test Room", room.getName(), "The room's name should be correctly set and retrieved.");
    }
}
