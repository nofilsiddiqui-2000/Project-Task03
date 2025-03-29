package com.robot.robot_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    private Robot robot;
    private Grid grid;

    @BeforeEach
    public void setUp() {
        robot = new Robot();
        grid = new Grid(5);
    }

    @Test
    public void testInitialPosition() {
        assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH", robot.getStatus());
    }

    @Test
    public void testMoveWithPenDown() {
        robot.setPenDown(true);
        robot.move(2, grid);
        assertTrue(grid.isCellMarked(0, 1));
        assertTrue(grid.isCellMarked(0, 2));
    }

    @Test
    public void testMark() {
        robot.setPenDown(true);
        robot.move(2, grid);
        assertTrue(grid.isCellMarked(0, 1));
        assertTrue(grid.isCellMarked(0, 2));
    }

    @Test
    public void testMarkOutOfBoundsDoesNothing() {
        grid.mark(-1, 0);  // Left of grid
        grid.mark(0, -1);  // Below grid
        grid.mark(5, 0);   // Right of grid
        grid.mark(0, 5);   // Above grid

        // No cells should be marked
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                assertFalse(grid.isCellMarked(x, y));
            }
        }
    }

    @Test
    public void testIsCellMarkedOutOfBounds() {
        assertFalse(grid.isCellMarked(-1, 0));
        assertFalse(grid.isCellMarked(0, -1));
        assertFalse(grid.isCellMarked(5, 0));
        assertFalse(grid.isCellMarked(0, 5));
    }

    @Test
    public void testGetSize() {
        assertEquals(5, grid.getSize());
    }

    @Test
    public void testPrintGridOutput() {
        grid.mark(0, 0);
        grid.mark(2, 4);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        grid.printGrid();
        String output = out.toString();

        assertTrue(output.contains("Grid:"));
        assertTrue(output.contains("*")); // At least one marked cell
    }
}
