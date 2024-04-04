package com.brushbuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
    }

    @Test
    void testAddRoom() {
        Room room = new Room("Living Room");
        project.addRoom(room);
        assertEquals(1, project.getRooms().size(), "The project should correctly add a room.");
    }

    @Test
    void testCalculateTotalPaintableArea() {
        Room livingRoom = new Room("Living Room");
        livingRoom.addWall(new RectangularWall(5000, 2500)); // 12.5 square meters
        livingRoom.addWall(new RectangularWall(5000, 2500)); // Another 12.5 square meters

        Room kitchen = new Room("Kitchen");
        kitchen.addWall(new RectangularWall(4000, 3000)); // 12 square meters

        project.addRoom(livingRoom);
        project.addRoom(kitchen);

        // Total expected paintable area = 12.5 + 12.5 + 12 = 37 square meters
        assertEquals(37.0, project.calculateTotalPaintableArea(), "The project should correctly calculate the total paintable area.");
    }

    @Test
    void testCalculateTotalPaintableAreaWithNonPaintableAreas() {
        Room room = new Room("Bedroom");
        RectangularWall wallWithWindow = new RectangularWall(4000, 3000); // 12 square meters
        wallWithWindow.addNonPaintableArea(new NonPaintableArea(2000, 1500)); // 3 square meters non-paintable
        room.addWall(wallWithWindow);

        project.addRoom(room);

        // Expected paintable area = 12 - 3 = 9 square meters
        assertEquals(9.0, project.calculateTotalPaintableArea(), "The project should correctly calculate the total paintable area considering non-paintable areas.");
    }

    @Test
    void testProjectWithNoRooms() {
        assertEquals(0, project.calculateTotalPaintableArea(), "A project with no rooms should have a total paintable area of 0.");
    }
}
