package com.robot.robot_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is designed specifically to cover all 
 * definition-use pairs (data-flow coverage) in 
 * CommandHandler.processCommand(String).
 */
public class DataFlowTest {

    private CommandHandler handler;

    @BeforeEach
    void setUp() {
        // Set up a CommandHandler with a reasonable grid size
        handler = new CommandHandler(5);
    }

    @Test
    void testDataFlowCoverage() {
        // 1) "M 4"
        // Covers normal parse of 'steps', p-use steps<0=false, c-use steps in robot.move(...)
        // Also covers definitions/uses of 'command', 'parts', 'cmd'
        handler.processCommand("M 4");

        // 2) "M -1"
        // Covers p-use steps<0=true branch
        handler.processCommand("M -1");

        // 3) "M"
        // Covers the p-use of parts (parts.length < 2) leading to "Invalid format" return
        handler.processCommand("M");

        // 4) "XYZ"
        // Covers the default branch in the switch (unmatched cmd)
        handler.processCommand("XYZ");

        // 5) "D"
        // Covers a single-letter command that sets pen down (just to touch that branch)
        handler.processCommand("D");

        // Optional: You could add "R", "L", "U", "P", "C", "H" too,
        // if you want to ensure the code for those commands is executed.
        // But from a data-flow standpoint for local variables, 
        // the key paths revolve around 'M' because that's where
        // steps is defined and used.

        // If you want to confirm final states, add asserts below. 
        // For instance, was pen down after "D"? Did "M 4" move the robot?
        // (But the main goal here is data-flow coverage.)
    }
}
