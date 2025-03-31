# Robot Simulator – Software Testing Suite

## 📘 Overview

This repository builds upon the core **Robot Simulator** project, developed by [TwinkalChristian/robot-simulator](https://github.com/TwinkalChristian/robot-simulator). As part of our software quality assurance course, our team's assigned role was to act as the **QA team** for their implementation. This project specifically applies black-box and white-box testing techniques to validate, analyze, and improve the quality of their application.

---

## 🧠 Objective

The goal of this phase is to ensure the reliability and correctness of the Robot Simulator through:

- Comprehensive **unit test coverage**
- **Mutation testing** to evaluate the strength of test cases
- Analysis and improvement of **test suite effectiveness**
- Evaluation of **code coverage** using tooling support
- Introduction of additional test scenarios, including edge and negative cases
- Fulfillment of the following course-specific testing tasks:
  - (a) Statement coverage > 50%
  - (b) Decision coverage > 50%
  - (c) Condition coverage > 50%
  - (d) Multiple condition coverage > 50%
  - (e) Mutation testing on selected function
  - (f) Data flow testing on selected function

---

## ✅ Testing Techniques Applied

- **JUnit**: For automated unit testing  
- **Mutation Testing**: Using PIT (Pitest) to introduce faults and observe test suite behavior  
- **Coverage Tools**: JaCoCo and Eclipse built-in analyzer to measure line/branch/method coverage  
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
├── target/
│   ├── site/
│   │   └── jacoco/         # JaCoCo coverage report
│   │       └── index.html
│   └── pit-reports/        # Mutation testing reports
│       └── index.html
├── pom.xml                 # Maven build configuration
└── README.md
```

---

## 🛠️ Getting Started

1. **Clone the repository**:

    ```bash
    git clone https://github.com/your-username/robot-simulator-testing.git
    cd robot-simulator-testing
    ```

2. **Build the project** (Maven based):

    ```bash
    mvn clean install
    ```

3. **Run tests**:

    ```bash
    mvn test
    ```

4. **Run mutation testing**:

    ```bash
    mvn org.pitest:pitest-maven:mutationCoverage
    ```

---

## 🧪 Sample Test Case

```java
@Test
public void testMoveForwardWithPenDown() {
    Robot robot = new Robot(10);
    robot.penDown();
    robot.move(5);
    assertEquals(5, robot.getX());
    assertTrue(robot.isDrawing());
}
```

---

## 📟 Reports

- **Code Coverage**: `target/site/jacoco/index.html`
- **Mutation Report**: `target/pit-reports/index.html`

---

## 🔗 Related Repositories

- 👉 [Core Robot Simulator](https://github.com/TwinkalChristian/robot-simulator): Base implementation repo.