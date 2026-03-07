[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# Smart Hostel Management System

## Project Title

Smart Hostel Management System using Java and Layered Architecture

---

## Problem Statement

Hostel administration often involves managing large amounts of information such as student records, room allocations, fee payments, and complaints. Performing these tasks manually can lead to inefficiencies, data inconsistency, and difficulty in tracking hostel activities. The Smart Hostel Management System is designed to automate hostel management operations using a structured software approach. The system allows administrators to register students, allocate hostel rooms dynamically, track fee payments, and maintain records of complaints and visitors. By implementing object-oriented programming principles and a layered architecture, the system ensures modularity, maintainability, and scalability. The goal of this project is to demonstrate the application of core Java OOP concepts along with proper system design to build a functional hostel administration tool.

---

## Target User

Hostel administrators and management staff responsible for managing hostel operations.

---

## Core Features

* Student registration and management
* Dynamic room allocation using a strategy-based approach
* Hostel fee payment tracking
* Student record viewing through a graphical dashboard
* Complaint registration and tracking
* Visitor logging system
* Activity logging for system actions
* Hostel statistics dashboard (students, rooms occupied, fees paid)

---

## OOP Concepts Used

The project demonstrates the following Object-Oriented Programming concepts:

* **Abstraction** – Service and repository layers hide implementation details
* **Encapsulation** – Data members are protected using getters and setters
* **Inheritance** – Shared structures across related classes
* **Polymorphism** – Strategy pattern used for room allocation
* **Exception Handling** – Custom exception (`NoRoomAvailableException`) for room allocation errors
* **Collections Framework** – `ArrayList` used for managing student and room data
* **Multithreading** – Fee reminder system runs in a background thread

---

## Architecture Description

The system follows a **Layered Architecture** to separate responsibilities and improve maintainability.

**Layers:**

1. **Presentation Layer (GUI)**

   * Built using Java Swing
   * Provides a dashboard interface for administrators

2. **Service Layer**

   * Contains core business logic
   * Handles room allocation, fee updates, and student management

3. **Repository Layer**

   * Manages data storage and retrieval operations

4. **Database Layer**

   * Uses **H2 embedded database** to store hostel records

**Architecture Flow**

GUI Layer → Service Layer → Repository Layer → Database

This separation ensures modular design and easy future extension.

---

## Technologies Used

* Java (Core Java & OOP)
* Java Swing (GUI)
* H2 Embedded Database
* Git & GitHub (Version Control)

---

## How to Run the Project

### Step 1 – Compile the Project

Run the following command in the project root directory:

```
javac -cp "lib/*" -d out src/com/hostel/model/*.java src/com/hostel/service/*.java src/com/hostel/util/*.java src/com/hostel/exception/*.java src/com/hostel/repository/*.java src/com/hostel/main/*.java
```

### Step 2 – Run the GUI Application

```
java -cp "out;lib/*" com.hostel.main.HostelGUI
```

The Smart Hostel Management System dashboard will launch.

---

## Project Structure

```
src/
 └ com/
    └ hostel/
       ├ model/
       ├ service/
       ├ repository/
       ├ util/
       ├ exception/
       └ main/
```

---

## Future Improvements

* Integration with a web-based interface
* Email or SMS fee reminders
* Advanced analytics dashboard
* Multi-user access with authentication

---

## Author

Sramana Patra
Computer Science Engineering
Institute of Engineering and Management (IEM)
