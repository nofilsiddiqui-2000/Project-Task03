<!-- # Robot Simulator

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
java -jar robot-simulator.jar -->

# Robot Simulator – Software Testing Suite

## 📘 Overview

This repository builds upon the core **Robot Simulator** project, shifting the focus toward rigorous **software testing and validation** practices. It aims to evaluate and improve the testability, fault tolerance, and correctness of the robot simulator using automated test frameworks, mutation testing tools, and coverage metrics.

---

## 🧠 Objective

The goal of this phase is to ensure the reliability and correctness of the Robot Simulator through:

- Comprehensive **unit test coverage**
- **Mutation testing** to evaluate the strength of test cases
- Analysis and improvement of **test suite effectiveness**
- Evaluation of **code coverage** using tooling support
- Introduction of additional test scenarios, including edge and negative cases

---

## ✅ Testing Techniques Applied

- **JUnit**: For automated unit testing  
- **Mutation Testing**: Using PIT (Pitest) to introduce faults and observe test suite behavior  
- **Coverage Tools**: JaCoCo or IntelliJ's built-in analyzer to measure line/branch/method coverage  
- **Boundary and Edge Testing**: To validate robot behavior under grid limits and invalid inputs  
- **Regression Testing**: Ensuring that new tests do not break previous valid functionality

---

## 🔍 Test Coverage Focus Areas

- Robot movement logic (`move`, `turn`, `boundary enforcement`)
- Pen control logic (`pen up/down`, `drawing path`)
- Grid printing/rendering functions
- Command parsing and validation
- Replay mechanism and state initialization

---

## 📂 Project Structure

```text
robot-simulator-testing/
├── src/
│   ├── main/java/          # Original application logic
│   └── test/java/          # Test cases and testing utilities
├── pit-reports/            # Mutation testing reports
├── coverage/               # Code coverage reports
├── pom.xml / build.gradle  # Build system
└── README.md
