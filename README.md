# Ubranyatra Java Console Project

A modular Java console application organized into Java packages.

## Package structure

- `com.ubranyatra.app` — application entry point
- `com.ubranyatra.model` — UserRecord, TourPackage, RideRecord
- `com.ubranyatra.service` — user/package/ride storage and fare calculation
- `com.ubranyatra.ui` — login, customer, rider and admin menus
- `com.ubranyatra.util` — console input helper

## Requirements

Java JDK 8 or later.

## Compile and run

From the project root directory:

```bash
javac -d out src/com/ubranyatra/app/Main.java src/com/ubranyatra/model/*.java src/com/ubranyatra/service/*.java src/com/ubranyatra/ui/*.java src/com/ubranyatra/util/*.java
java -cp out com.ubranyatra.app.Main
```

Or use `run.bat` on Windows / `run.sh` on macOS or Linux.

## Admin login

- Username: `admin`
- Password: `admin123`

## Features

- Customer and rider registration/login
- Customer profile updates and booking history
- Admin creates, views, updates and deletes tour packages
- Packages store a destination and an estimated distance
- Customer chooses a package and vehicle; destination is filled from the package
- Rapido-style demonstration fare: base fare + (estimated distance × vehicle rate)
- Customer sees only the total fare; individual vehicle rates are not shown
- Riders accept/reject pending rides and complete accepted rides
- Admin manages accounts and views rides/statistics
- Data is persisted in `data/users.txt`, `data/packages.txt`, and `data/rides.txt`

## Illustrative fare rates (not actual Rapido rates)

- Bike: base ₹15 + ₹6/km
- Auto: base ₹25 + ₹10/km
- Sedan: base ₹40 + ₹14/km
- SUV: base ₹60 + ₹18/km

The estimated distance is entered by the admin when creating a tour package. This console demo does not use GPS or a maps API, so it does not calculate live road distance. Connect a routing/maps service for real distance estimates.

## OOP: The Four Pillars

This version explicitly demonstrates all four pillars of object-oriented programming:

1. **Encapsulation** — `UserRecord` stores account fields as `private`. Other classes use getters and controlled setters.
2. **Inheritance** — `CustomerRecord`, `RiderRecord`, and `AdminRecord` extend the abstract `UserRecord` class and reuse its shared account data.
3. **Polymorphism** — each subclass overrides `getWelcomeMessage()`. `AuthMenu` calls `user.getWelcomeMessage()` through a `UserRecord` reference, and Java chooses the matching subclass implementation at runtime.
4. **Abstraction** — `UserRecord` is an `abstract` class and declares `getWelcomeMessage()` as an abstract method. Each account subtype must provide its own implementation.

User records are still stored in the same pipe-separated text-file format, so the file-based persistence design remains in place.
