# Robot Simulator

## Project Description
The **Robot Simulator** is a program that simulates the movement of a robot in a grid. The robot can move around, trace out shapes, and control its pen (up or down). Users can issue commands to control the robot's movement and behavior in the grid.

## Features
- Pen up/down to control drawing
- Move robot in different directions (North, South, East, West)
- Print the grid and robot status
- Replay previous commands
- Initialize/reset the robot

## Commands
- `[U|u]`: Pen up
- `[D|d]`: Pen down
- `[R|r]`: Turn right
- `[L|l]`: Turn left
- `[M s|m s]`: Move forward s spaces (s is a non-negative integer)
- `[P|p]`: Print the grid and display indices
- `[C|c]`: Print the current position of the pen and its facing direction
- `[Q|q]`: Stop the program
- `[I n|i n]`: Initialize the system with an N by N grid
- `[H|h]`: Replay all steps from the last system start

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/TwinkalChristian/robot-simulator.git
    ```

2. Navigate into the project directory:
    ```bash
    cd robot-simulator
    ```

3. Ensure you have Java installed and configured.

## Usage
To run the program, execute the following command:
```bash
java -jar robot-simulator.jar
