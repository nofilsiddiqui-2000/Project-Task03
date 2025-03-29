package com.robot.robot_simulator;

public class Grid {
    private int size;
    private boolean[][] cells;

    public Grid(int size) {
        this.size = size;
        this.cells = new boolean[size][size];
    }

    public int getSize() {
        return size;
    }

    public void mark(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            cells[y][x] = true; // Fixed marking to correct coordinates
        }
    }

    public void printGrid() {
        System.out.println("Grid:");
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }

    public boolean isCellMarked(int x, int y) {  // Added method
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return cells[y][x];
        }
        return false;
    }
}
