package com.robot.robot_simulator;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testAppFlowWithInitializationAndCommands() {
        String simulatedInput = String.join("\n",
                "I 5",
                "D",
                "M 2",
                "R",
                "C",
                "Q"
        );

        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printOut = new PrintStream(outContent);

        System.setIn(in);              // Simulate user input
        System.setOut(printOut);       // Capture console output

        App.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Grid initialized to size 5"));
        assertTrue(output.contains("Pen: down"));
        assertTrue(output.contains("Facing: EAST"));
        assertTrue(output.contains("Exiting..."));
    }

    @Test
    void testGridSizeZeroShowsError() {
        String input = "I 0\nQ\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App.main(new String[]{});

        assertTrue(out.toString().contains("Grid size must be greater than zero."));
    }

    @Test
    void testInvalidGridSizeFormat() {
        String input = "I five\nQ\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App.main(new String[]{});

        assertTrue(out.toString().contains("Invalid grid size."));
    }

    @Test
    void testCommandBeforeInitialization() {
        String input = "D\nQ\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App.main(new String[]{});

        assertTrue(out.toString().contains("Initialize the grid first"));
    }

    @Test
    void testNoInputEndsGracefully() {
        InputStream in = new ByteArrayInputStream(new byte[0]); // No input
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App.main(new String[]{});  // Should exit gracefully

        assertTrue(true); // If it doesn't hang or crash, we're good
    }
}
