DungeonMaster

DungeonMaster is an Android application designed for creating and managing basic Dungeons & Dragons (D&D) characters. The application allows users to keep track of character statistics, attributes, and essential information through a simple and structured interface.

The project also includes a REST API developed in Java, responsible for handling character data management and communication between the mobile client and the backend service.

Features
Create and manage D&D characters
Track character statistics and attributes
Store and update character information
RESTful API integration
Client-server architecture
Clean and modular project structure
Tech Stack
Mobile Application
Java
Android Studio
Android SDK
Backend API
Java
REST API
Tools
Git
GitHub
Architecture

The project follows a client-server architecture:

Android Application → Handles the user interface and character management features
REST API → Processes requests, manages character data, and handles backend communication
Project Structure
DungeonMaster/
│
├── app/                # Android application source code
├── api/                # Java REST API source code
├── assets/             # Images and resources
├── docs/               # Documentation
└── README.md
Installation
Clone the Repository
git clone https://github.com/Mabadcortes/DungeonMaster.git
Android Application Setup
Open the project in Android Studio
Sync Gradle dependencies
Build and run the application on an emulator or Android device
API Setup
Navigate to the API project directory
Configure the server environment
Run the Java REST API

Example:

cd api
./run-server
Future Improvements
User authentication system
Cloud database integration
Expanded D&D mechanics
Multiplayer/session support
Improved UI/UX
Character export/import functionality
Learning Goals

This project was developed to improve skills in:

Android development
REST API development
Java backend architecture
Client-server communication
Object-oriented programming
Mobile application design
Author

Developed by Mabadcortes
