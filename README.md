# DungeonMaster

DungeonMaster is an Android application designed for creating and managing basic Dungeons & Dragons (D&D) characters. The application allows users to track character statistics, attributes, and essential information through a simple and structured interface.

The project also includes a REST API developed in Java, responsible for handling character data management and communication between the mobile client and backend service.

---

## Features

- Create and manage D&D characters
- Track character statistics and attributes
- Store and update character information
- RESTful API integration
- Client-server architecture
- Clean and modular project structure

---

## Tech Stack

### Mobile Application
- Java
- Android Studio
- Android SDK

### Backend API
- Java
- REST API

### Tools
- Git
- GitHub

---

## Architecture

The project follows a client-server architecture:

```text
Android Application ↔ REST API ↔ Data Management
```

- **Android Application** → Handles the user interface and character management
- **REST API** → Processes requests and manages backend communication

---

## Project Structure

```text
DungeonMaster/
│
├── app/        # Android application source code
├── api/        # Java REST API source code
├── assets/     # Images and resources
├── docs/       # Documentation
└── README.md
```

---

## Installation

### Clone the Repository

```bash
git clone https://github.com/Mabadcortes/DungeonMaster.git
```

---

## Android Application Setup

1. Open the project in Android Studio
2. Sync Gradle dependencies
3. Build and run the application on an emulator or Android device

---

## API Setup

1. Navigate to the API directory

```bash
cd api
```

2. Run the Java REST API server

```bash
./run-server
```

---

## Future Improvements

- User authentication system
- Cloud database integration
- Expanded D&D mechanics
- Multiplayer/session support
- Improved UI/UX
- Character export/import functionality

---

## Learning Goals

This project was developed to improve skills in:

- Android development
- REST API development
- Java backend architecture
- Client-server communication
- Object-oriented programming
- Mobile application design

---

## Author

Developed by [Mabadcortes](https://github.com/Mabadcortes)
