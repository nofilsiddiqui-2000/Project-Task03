//package com.robot.robot_simulator;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class CommandHandlerTest {
//    private Robot robot;
//    private Grid grid;
//    private CommandHandler commandHandler;
//
//    @Before
//    public void setUp() {
//        robot = new Robot();
//        grid = new Grid(5);
//        commandHandler = new CommandHandler(robot, grid);
//    }
//
//    @Test
//    public void testProcessCommand_TurnRight() {
//        commandHandler.processCommand("R");
//        assertEquals("Position: 0, 0 - Pen: up - Facing: EAST", robot.getStatus());
//    }
//
//    @Test
//    public void testProcessCommand_Move() {
//        commandHandler.processCommand("M 2");
//        assertEquals("Position: 2, 0 - Pen: up - Facing: NORTH", robot.getStatus());
//    }
//
//    @Test
//    public void testProcessCommand_PenDown() {
//        commandHandler.processCommand("D");
//        assertTrue(robot.isPenDown());
//    }
//
//    @Test
//    public void testProcessCommand_CheckStatus() {
//        commandHandler.processCommand("C");
//        assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH", robot.getStatus());
//    }
//
//    @Test
//    public void testProcessCommand_PenUp() {
//        commandHandler.processCommand("U");
//        assertFalse(robot.isPenDown());
//    }
//
//    @Test
//    public void testProcessCommand_TurnLeft() {
//        commandHandler.processCommand("L");
//        assertEquals("Position: 0, 0 - Pen: up - Facing: WEST", robot.getStatus());
//    }
//
//    @Test
//    public void testProcessCommand_PrintGrid() {
//        commandHandler.processCommand("P");
//        // You will need to capture the output here in your actual test framework to assert
//    }
//
//    @Test
//    public void testMoveNorth() {
//        robot.move(1, grid);
//        assertEquals("Position: 0, 1 - Pen: up - Facing: NORTH", robot.getStatus());
//    }
//
////    @Test
////    public void testMark() {
////        robot.setPenDown(true);
////        robot.move(2, grid);
////        assertTrue(grid.isCellMarked(0, 1));
////        assertTrue(grid.isCellMarked(0, 2));
////    }
//}


package com.robot.robot_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandHandlerTest {

    private CommandHandler handlerWithInjectedObjects;
    private CommandHandler handlerWithGridSize;
    private Robot robot;
    private Grid grid;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Used for testing overloaded constructor
        robot = new Robot();
        grid = new Grid(10);
        handlerWithInjectedObjects = new CommandHandler(robot, grid);

        // Used for testing default constructor
        handlerWithGridSize = new CommandHandler(10);

        // Redirect System.out to capture command outputs
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    // === Tests using CommandHandler(Robot, Grid) ===

    @Test
    void testMoveCommandInjectedConstructor() {
        handlerWithInjectedObjects.processCommand("D");
        handlerWithInjectedObjects.processCommand("M 3");
        handlerWithInjectedObjects.processCommand("C");
        assertEquals("Position: 0, 3 - Pen: down - Facing: NORTH", robot.getStatus());
    }

    @Test
    void testGridMarkingWithInjectedConstructor() {
        handlerWithInjectedObjects.processCommand("D");
        handlerWithInjectedObjects.processCommand("M 2");
        assertTrue(grid.isCellMarked(0, 1));
        assertTrue(grid.isCellMarked(0, 2));
    }

    @Test
    void testTurnRightWithInjectedConstructor() {
        handlerWithInjectedObjects.processCommand("R");
        assertTrue(robot.getStatus().contains("EAST"));
    }

    // === Tests using CommandHandler(int gridSize) ===

    @Test
    void testPenDownCommand() {
        handlerWithGridSize.processCommand("D");
        handlerWithGridSize.processCommand("C");
        Robot internalRobot = getInternalRobot(handlerWithGridSize);
        assertTrue(internalRobot.isPenDown());
    }

    @Test
    void testPenUpCommand() {
        handlerWithGridSize.processCommand("D");
        handlerWithGridSize.processCommand("U");
        handlerWithGridSize.processCommand("C");
        assertTrue(outContent.toString().contains("Pen: up"));
    }

    @Test
    void testMoveCommandWithSteps() {
        handlerWithGridSize.processCommand("D");
        handlerWithGridSize.processCommand("M 3");
        handlerWithGridSize.processCommand("C");
        assertTrue(outContent.toString().contains("Position: 0, 3"));
    }

    @Test
    void testMoveCommandWithoutSteps() {
        handlerWithGridSize.processCommand("M");
        assertTrue(outContent.toString().contains("Invalid format"));
    }

    @Test
    void testMoveCommandWithNegativeSteps() {
        handlerWithGridSize.processCommand("M -3");
        assertTrue(outContent.toString().contains("Steps must be non-negative"));
    }

    @Test
    void testMoveCommandWithInvalidSteps() {
        handlerWithGridSize.processCommand("M abc");
        assertTrue(outContent.toString().contains("Invalid number format"));
    }

    @Test
    void testTurnLeftCommand() {
        handlerWithGridSize.processCommand("L");
        handlerWithGridSize.processCommand("C");
        assertTrue(outContent.toString().contains("Facing: WEST"));
    }

    @Test
    void testPrintGridCommand() {
        handlerWithGridSize.processCommand("P");
        assertTrue(outContent.toString().contains("Grid:"));
    }

    @Test
    void testPrintStatusCommand() {
        handlerWithGridSize.processCommand("C");
        assertTrue(outContent.toString().contains("Position:"));
    }

    @Test
    void testInvalidCommand() {
        handlerWithGridSize.processCommand("XYZ");
        assertTrue(outContent.toString().contains("Invalid command"));
    }

    @Test
    void testHistoryReplayCommand() {
        handlerWithGridSize.processCommand("D");
        handlerWithGridSize.processCommand("M 2");
        handlerWithGridSize.processCommand("R");
        handlerWithGridSize.processCommand("H");
        assertTrue(outContent.toString().contains("Replaying history..."));
        assertTrue(outContent.toString().contains("> D"));
        assertTrue(outContent.toString().contains("> M 2"));
        assertTrue(outContent.toString().contains("> R"));
    }
    
 // === Helper ===

    private Robot getInternalRobot(CommandHandler handler) {
        try {
            java.lang.reflect.Field robotField = CommandHandler.class.getDeclaredField("robot");
            robotField.setAccessible(true);
            return (Robot) robotField.get(handler);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access internal Robot", e);
        }
    }
}
