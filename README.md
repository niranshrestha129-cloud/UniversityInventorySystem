University Inventory System

This Java application manages the inventory of a university, including equipment, furniture, and lab items. It allows staff members to assign, return, and track inventory items, ensuring efficient resource management and preventing over-assignment or double-booking.

Features

Add and manage inventory items (equipment, furniture, lab equipment)
Assign items to staff members with assignment limits
Handle unavailable items and assignment exceptions
Return items and update inventory status
Exception handling for assignment limits and unavailable items
Project Structure

UniversityInventorySystem.java – Main application logic and user interface
InventoryItem.java – Base class for inventory items
Equipment.java, Furniture.java, LabEquipment.java – Item subclasses
StaffMember.java – Staff member management and item assignment
AssignmentLimitExceededException.java – Exception for assignment limits
ItemUnavailableException.java – Exception for unavailable items
How to Run

Compile all Java files:
javac *.java
Run the main application:
java UniversityInventorySystem
Usage

Follow the on-screen prompts to add items, assign them to staff, and manage inventory.
The system will notify you if an assignment exceeds limits or if an item is unavailable.
License

This project is for educational purposes.
