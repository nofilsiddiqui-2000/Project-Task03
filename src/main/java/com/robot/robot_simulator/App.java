package com.robot.robot_simulator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler handler = null;

        try {
            while (true) {
                System.out.print("Enter command: ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("Q")) {
                    System.out.println("Exiting...");
                    break;
                }

                if (command.toUpperCase().startsWith("I ")) {
                    try {
                        int gridSize = Integer.parseInt(command.substring(2).trim());
                        if (gridSize <= 0) {
                            System.out.println("Grid size must be greater than zero.");
                            continue;
                        }
                        handler = new CommandHandler(gridSize);
                        System.out.println("Grid initialized to size " + gridSize);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grid size.");
                    }
                } else if (handler != null) {
                    handler.processCommand(command);
                } else {
                    System.out.println("Initialize the grid first using the 'I n' command.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
