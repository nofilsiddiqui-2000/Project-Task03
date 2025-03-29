package com.robot.robot_simulator;

public class Robot {
    private int x, y;
    private boolean penDown;
    private String direction; // NORTH, EAST, SOUTH, WEST

    public Robot() {
        this.x = 0;
        this.y = 0;
        this.penDown = false;
        this.direction = "NORTH";
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public boolean isPenDown() {  // Added method
        return penDown;
    }

    public void turnRight() {
        switch (direction) {
            case "NORTH": direction = "EAST"; break;
            case "EAST": direction = "SOUTH"; break;
            case "SOUTH": direction = "WEST"; break;
            case "WEST": direction = "NORTH"; break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case "NORTH": direction = "WEST"; break;
            case "WEST": direction = "SOUTH"; break;
            case "SOUTH": direction = "EAST"; break;
            case "EAST": direction = "NORTH"; break;
        }
    }

    public void move(int steps, Grid grid) {
        for (int i = 0; i < steps; i++) {
            switch (direction) {
                case "NORTH": y = Math.min(grid.getSize() - 1, y + 1); break;
                case "SOUTH": y = Math.max(0, y - 1); break;
                case "EAST":  x = Math.min(grid.getSize() - 1, x + 1); break;
                case "WEST":  x = Math.max(0, x - 1); break;
            }
            if (penDown) {
                grid.mark(x, y);
            }
        }
    }

    public String getStatus() {
        return "Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: " + direction;
    }
}
