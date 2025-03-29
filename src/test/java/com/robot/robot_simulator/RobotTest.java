package com.robot.robot_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {
    private Robot robot;
    private Grid grid;

    @BeforeEach
    public void setUp() {
        robot = new Robot();
        grid = new Grid(5);
    }

    @Test
    public void testInitialStatus() {
        assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH", robot.getStatus());
    }

    @Test
    public void testSetAndGetPenDown() {
        robot.setPenDown(true);
        assertTrue(robot.isPenDown());
        robot.setPenDown(false);
        assertFalse(robot.isPenDown());
    }

    @Test
    public void testTurnRightAllDirections() {
        robot.turnRight(); // EAST
        assertTrue(robot.getStatus().contains("EAST"));
        robot.turnRight(); // SOUTH
        assertTrue(robot.getStatus().contains("SOUTH"));
        robot.turnRight(); // WEST
        assertTrue(robot.getStatus().contains("WEST"));
        robot.turnRight(); // NORTH
        assertTrue(robot.getStatus().contains("NORTH"));
    }

    @Test
    public void testTurnLeftAllDirections() {
        robot.turnLeft(); // WEST
        assertTrue(robot.getStatus().contains("WEST"));
        robot.turnLeft(); // SOUTH
        assertTrue(robot.getStatus().contains("SOUTH"));
        robot.turnLeft(); // EAST
        assertTrue(robot.getStatus().contains("EAST"));
        robot.turnLeft(); // NORTH
        assertTrue(robot.getStatus().contains("NORTH"));
    }

    @Test
    public void testMoveNorth() {
        robot.move(1, grid);
        assertEquals("Position: 0, 1 - Pen: up - Facing: NORTH", robot.getStatus());
    }

    @Test
    public void testMoveEast() {
        robot.turnRight(); // EAST
        robot.move(2, grid);
        assertEquals("Position: 2, 0 - Pen: up - Facing: EAST", robot.getStatus());
    }

    @Test
    public void testMoveSouthBoundary() {
        robot.turnRight(); // EAST
        robot.turnRight(); // SOUTH
        robot.move(1, grid); // should stay at (0,0)
        assertEquals("Position: 0, 0 - Pen: up - Facing: SOUTH", robot.getStatus());
    }

    @Test
    public void testMoveWestBoundary() {
        robot.turnLeft(); // WEST
        robot.move(1, grid); // should stay at (0,0)
        assertEquals("Position: 0, 0 - Pen: up - Facing: WEST", robot.getStatus());
    }

    @Test
    public void testBoundaryNorthLimit() {
        robot.move(10, grid); // should stop at y=4
        assertEquals("Position: 0, 4 - Pen: up - Facing: NORTH", robot.getStatus());
    }

    @Test
    public void testBoundaryEastLimit() {
        robot.turnRight(); // EAST
        robot.move(10, grid); // should stop at x=4
        assertEquals("Position: 4, 0 - Pen: up - Facing: EAST", robot.getStatus());
    }

    @Test
    public void testPenDownMarksGrid() {
        robot.setPenDown(true);
        robot.move(2, grid);
        assertTrue(grid.isCellMarked(0, 1));
        assertTrue(grid.isCellMarked(0, 2));
    }

    @Test
    public void testPenUpDoesNotMarkGrid() {
        robot.setPenDown(false);
        robot.move(2, grid);
        assertFalse(grid.isCellMarked(0, 1));
        assertFalse(grid.isCellMarked(0, 2));
    }

    @Test
    public void testComplexMoveAndTurnSequence() {
        robot.setPenDown(true);
        robot.move(1, grid); // (0,1)
        robot.turnRight();   // EAST
        robot.move(1, grid); // (1,1)
        robot.turnRight();   // SOUTH
        robot.move(1, grid); // (1,0)
        robot.turnLeft();    // EAST
        robot.move(1, grid); // (2,0)

        assertEquals("Position: 2, 0 - Pen: down - Facing: EAST", robot.getStatus());

        // Check that all positions along path were marked
        assertTrue(grid.isCellMarked(0, 1));
        assertTrue(grid.isCellMarked(1, 1));
        assertTrue(grid.isCellMarked(1, 0));
        assertTrue(grid.isCellMarked(2, 0));
    }
}
