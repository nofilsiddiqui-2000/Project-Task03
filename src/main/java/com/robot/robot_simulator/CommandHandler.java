package com.robot.robot_simulator;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private final Robot robot;
    private final Grid grid;
    private final List<String> commandHistory;

    public CommandHandler(int gridSize) {
        this.robot = new Robot();
        this.grid = new Grid(gridSize);
        this.commandHistory = new ArrayList<>();
    }

    public CommandHandler(Robot robot, Grid grid) {  // Added constructor
        this.robot = robot;
        this.grid = grid;
        this.commandHistory = new ArrayList<>();
    }

    public void processCommand(String command) {
        commandHistory.add(command); // Store command history for replay
        String[] parts = command.split(" ");
        String cmd = parts[0].toUpperCase();

        try {
            switch (cmd) {
                case "M":
                    if (parts.length < 2) {
                        System.out.println("Invalid format. Use: M <steps>");
                        return;
                    }
                    int steps = Integer.parseInt(parts[1]);
                    if (steps < 0) {
                        System.out.println("Steps must be non-negative.");
                        return;
                    }
                    robot.move(steps, grid);
                    break;
                case "L":
                    robot.turnLeft();
                    break;
                case "R":
                    robot.turnRight();
                    break;
                case "D":
                    robot.setPenDown(true);
                    break;
                case "U":
                    robot.setPenDown(false);
                    break;
                case "P":
                    grid.printGrid();
                    break;
                case "C":
                    System.out.println(robot.getStatus());
                    break;
                case "H":
                    replayHistory();
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

    private void replayHistory() {
        System.out.println("Replaying history...");
        grid.printGrid();
        for (String command : commandHistory) {
            System.out.println("> " + command);
        }
    }
}
