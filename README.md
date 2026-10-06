
# 🏙️ UrbanYatra

### City Tour Package & Ride Management System

UrbanYatra is a **console-based Java application** designed to provide a convenient platform for exploring and booking city tour packages. The system combines **tour package management, vehicle selection, dynamic pricing, customer booking, rider assignment, ride tracking, and administrative management** in one application.

---

## 📌 Project Overview

Customers can register and log in to the system, explore cities and available tour packages, view destinations included in each package, select the number of passengers and vehicle type, view the dynamically calculated price, and book a tour package.

After a booking is confirmed, an available rider can be assigned to the booking. Riders can view their assigned rides, accept or reject rides, reach the customer's pickup point, mark the customer as picked up, start the ride, and complete each destination or package section.

Once all sections are completed, the rider can complete the ride and immediately view the **ride duration, earnings, and completion details**.

Administrators manage the complete system, including **customers, riders, cities, tour packages, destinations, vehicles, pricing rules, bookings, rider assignments, and reports**.

---

## 🎯 Project Objective

The main objective of **UrbanYatra** is to create a simple but well-structured city tour and ride management system while demonstrating how **Java Object-Oriented Programming concepts** can be applied to a practical real-world application.

---

## 🚀 Main Features

* 👤 User Registration & Authentication
* 🔐 Role-Based Login
* 👥 Customer, Rider & Admin Roles
* 🏙️ City Management
* 📦 Tour Package Management
* 📍 Destination Management
* 🧭 Package Section Management
* 🚗 Vehicle Management
* 💰 Dynamic Pricing
* 📝 Customer Booking
* 🧑‍✈️ Rider Assignment
* 📍 Ride Progress Tracking
* ✅ Package Section Completion
* 💵 Rider Earnings
* 📊 Reports
* ⚠️ Exception Handling
* 🕒 Date & Time Management
* 💾 Array-Based In-Memory Data Storage
* 🖥️ Console-Based Menus

---

## 👥 Target Users

### 👤 Customer

Customers can:

* Register an account
* Log in
* View cities
* View tour packages
* View package destinations
* Select number of passengers
* Select vehicle type
* View dynamic pricing
* Book a package
* View booking details
* Track ride status
* View ride history
* Cancel bookings

### 🧑‍✈️ Rider

Riders can:

* Log in
* View assigned rides
* Accept or reject rides
* Mark customer as reached
* Mark customer as picked up
* Start the ride
* Complete package sections
* Complete the ride
* View ride duration
* View earnings
* View ride history

### 👨‍💼 Admin

Administrators can:

* Manage customers
* Manage riders
* Manage cities
* Create and update tour packages
* Manage destinations
* Manage vehicles
* Configure pricing
* Assign riders
* Manage bookings
* Monitor rides
* View reports

---

## 💰 Dynamic Pricing

UrbanYatra calculates the final price dynamically based on package, passengers, and vehicle selection.

### Formula

```text
Final Price =
Base Package Price
+ Passenger Charge
+ Vehicle Charge
+ Additional Charges
```

### Example

```text
Base Package Price = ₹1000
Passenger Charge    = ₹200
Vehicle Charge      = ₹300

Final Price         = ₹1500
```

The pricing rules can be managed by the administrator.

---

## 🚗 Vehicle Types

UrbanYatra supports different vehicle types:

* 🏍️ Bike
* 🛺 Auto
* 🚗 Car

Each vehicle type can have its own pricing and capacity rules.

---

## 🧭 Ride Flow

The rider follows a controlled ride process:

```text
Assigned
   ↓
Accept Ride
   ↓
Reached Customer
   ↓
Picked Up Customer
   ↓
Start Ride
   ↓
Complete Package Sections
   ↓
Complete Ride
   ↓
Generate Earnings
   ↓
Display Ride Duration & Completion Details
```

Example package progress:

```text
[✓] Charminar
[✓] Chowmahalla Palace
[ ] Salar Jung Museum
[ ] Golconda Fort
```

A ride can only be completed after all required package sections are completed.

---

## 🧱 OOP Concepts Demonstrated

This project is designed specifically to demonstrate important Java OOP concepts.

### Classes & Objects

The system contains classes such as:

```text
User
Customer
Rider
Admin
City
TourPackage
Destination
Vehicle
Booking
Ride
Earning
```

### Encapsulation

Important data members are kept private and accessed through controlled methods.

### Abstraction

Abstract classes are used for common concepts:

```text
User
Vehicle
```

### Inheritance

Examples:

```text
User
 ├── Customer
 ├── Rider
 └── Admin
```

```text
Vehicle
 ├── Bike
 ├── Auto
 └── Car
```

### Interface

The `Authenticatable` interface provides common authentication behavior for users.

### Polymorphism

Different vehicle classes override vehicle pricing behavior.

```text
Bike → calculateVehicleCharge()
Auto → calculateVehicleCharge()
Car  → calculateVehicleCharge()
```

### Association

Examples:

```text
Customer → Booking
Booking → TourPackage
Booking → Vehicle
Rider → Ride
```

### Aggregation

```text
City → TourPackage
```

A city can contain multiple tour packages.

### Composition

```text
TourPackage → PackageSection
Ride → RideSection
```

These sections belong to their respective parent objects.

### Static & Non-Static Members

Static members are used for shared system data, constants, and counters, while non-static members represent individual object data.

### Arrays

Arrays are used for in-memory storage instead of a database.

### Exception Handling

Custom exceptions are used for invalid operations such as:

```text
InvalidLoginException
InvalidBookingException
PackageNotFoundException
RiderNotAvailableException
InvalidRideStateException
InvalidInputException
```

### Java Packages

The project is divided into logical Java packages to improve organization and maintainability.

### Date & Time

`LocalDateTime` is used for:

* Booking time
* Ride start time
* Pickup time
* Completion time
* Earning time

---

## 📁 Project Structure

```text
UrbanYatra/
│
└── src/
    │
    ├── main/
    │   └── Main.java
    │
    ├── users/
    │   ├── User.java
    │   ├── Customer.j
```
