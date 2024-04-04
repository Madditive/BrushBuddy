package com.brushbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void testRectangularWallArea() {
        // Given a rectangular wall with specific width and height
        RectangularWall wall = new RectangularWall(2000, 3000); // dimensions in millimeters
        // When calculating the area, we expect 6.0 square meters (since 2000mm x 3000mm = 2m x 3m)
        assertEquals(6.0, wall.calculatePaintableArea(), "The calculated area should match the expected area.");
    }

    @Test
    void testRectangularWallWithNonPaintableArea() {
        // Given a rectangular wall and a non-paintable area within it
        RectangularWall wall = new RectangularWall(2000, 3000); // dimensions in millimeters
        wall.addNonPaintableArea(new NonPaintableArea(1000, 1000)); // 1 square meter non-paintable
        // When calculating the paintable area, we expect it to subtract the non-paintable area
        assertEquals(5.0, wall.calculatePaintableArea(), "The paintable area should be reduced by the non-paintable area.");
    }

    @Test
    void testIrregularWallAreaWithMultipleSections() {
        // Given an irregular wall composed of a rectangle and a triangle
        IrregularWall irregularWall = new IrregularWall();
        irregularWall.addSection(new RectangularSection(1000, 2000)); // 2m^2
        irregularWall.addSection(new TriangularSection(1000, 2000)); // 1m^2 triangle
        // When calculating the total area
        assertEquals(3.0, irregularWall.calculateArea(), "The total area should be the sum of its sections.");
    }
}
