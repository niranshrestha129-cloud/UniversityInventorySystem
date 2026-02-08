import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main class for the University Inventory Management System. This class
 * provides a console-based interface through which users can add inventory
 * items, register staff, assign and return items, search the inventory and
 * generate various reports. It demonstrates fundamental programming concepts
 * such as arrays, loops, conditional logic, inheritance, polymorphism and
 * exception handling.
 */
public class UniversityInventorySystem {

    // Maximum number of inventory items and staff members that can be stored
    private static final int MAX_INVENTORY = 100;
    private static final int MAX_STAFF = 50;

    // Arrays to hold inventory items and staff members
    private static InventoryItem[] inventory = new InventoryItem[MAX_INVENTORY];
    private static int inventoryCount = 0;
    private static StaffMember[] staffMembers = new StaffMember[MAX_STAFF];
    private static int staffCount = 0;

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("Welcome to the University Inventory Management System!");
        boolean quit;
        do {
            displayMenu();
            int choice = readInt("Enter your choice: ");
            quit = handleChoice(choice);
        } while (!quit);
        System.out.println("Thank you for using the inventory system. Goodbye!");
        // close the scanner before exiting
        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Add Inventory Item");
        System.out.println("2. Register Staff Member");
        System.out.println("3. Assign Item to Staff");
        System.out.println("4. Return Item from Staff");
        System.out.println("5. Search Inventory");
        System.out.println("6. Generate Reports");
        System.out.println("7. Exit");
    }

    /**
     * Processes the user's menu selection and triggers appropriate actions. Returns
     * true if the user chooses to quit the program.
     */
    private static boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                addInventoryItem();
                break;
            case 2:
                registerStaffMember();
                break;
            case 3:
                assignItemToStaff();
                break;
            case 4:
                returnItemFromStaff();
                break;
            case 5:
                searchInventoryMenu();
                break;
            case 6:
                generateReportsMenu();
                break;
            case 7:
                return true;
            default:
                System.out.println("Invalid choice. Please select an option from 1 to 7.");
        }
        return false;
    }

    /**
     * Reads an integer from the user, prompting until a valid integer is
     * provided. If the input is invalid, the user is warned and asked again.
     */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads a double from the user with validation.
     */
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a date from the user in yyyy-MM-dd format, prompting until valid.
     */
    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (yyyy-MM-dd): ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input.trim(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    /**
     * Adds a new inventory item by prompting the user for type and details.
     */
    private static void addInventoryItem() {
        if (inventoryCount >= MAX_INVENTORY) {
            System.out.println("Inventory is full; cannot add more items.");
            return;
        }
        System.out.println("\nSelect item type to add:");
        System.out.println("1. Equipment");
        System.out.println("2. Furniture");
        System.out.println("3. Lab Equipment");
        int typeChoice = readInt("Choice: ");

        String id = promptNonEmpty("Enter item ID: ");
        String name = promptNonEmpty("Enter item name: ");
        LocalDate purchaseDate = readDate("Enter purchase date");
        double price = readDouble("Enter purchase price: ");
        LocalDate warrantyEnd = readDate("Enter warranty end date");

        InventoryItem item;
        switch (typeChoice) {
            case 1: {
                String brand = promptNonEmpty("Enter equipment brand: ");
                item = new Equipment(id, name, brand, purchaseDate, price, warrantyEnd);
                break;
            }
            case 2: {
                String material = promptNonEmpty("Enter furniture material: ");
                item = new Furniture(id, name, material, purchaseDate, price, warrantyEnd);
                break;
            }
            case 3: {
                String labType = promptNonEmpty("Enter lab type: ");
                item = new LabEquipment(id, name, labType, purchaseDate, price, warrantyEnd);
                break;
            }
            default:
                System.out.println("Invalid type selection. Returning to main menu.");
                return;
        }
        inventory[inventoryCount++] = item;
        System.out.println("Item added successfully!");
    }

    /**
     * Prompts the user for a non-empty string. If the user enters a blank
     * response, they are asked again.
     */
    private static String promptNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    /**
     * Registers a new staff member by requesting a unique staff ID and name.
     */
    private static void registerStaffMember() {
        if (staffCount >= MAX_STAFF) {
            System.out.println("Cannot register more staff members.");
            return;
        }
        String id = promptNonEmpty("Enter staff ID: ");
        // check if staff ID already exists
        if (findStaffById(id) != null) {
            System.out.println("A staff member with this ID already exists.");
            return;
        }
        String name = promptNonEmpty("Enter staff name: ");
        staffMembers[staffCount++] = new StaffMember(id, name);
        System.out.println("Staff member registered successfully!");
    }

    /**
     * Assigns an existing inventory item to an existing staff member. This
     * operation will prompt for staff ID and item ID and handle exceptions if
     * conditions are violated.
     */
    private static void assignItemToStaff() {
        if (staffCount == 0) {
            System.out.println("No staff registered yet. Please register staff first.");
            return;
        }
        if (inventoryCount == 0) {
            System.out.println("No inventory items available. Please add items first.");
            return;
        }
        String staffId = promptNonEmpty("Enter staff ID: ");
        StaffMember staff = findStaffById(staffId);
        if (staff == null) {
            System.out.println("Staff member not found.");
            return;
        }
        String itemId = promptNonEmpty("Enter item ID to assign: ");
        InventoryItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        try {
            staff.assignItem(item);
            System.out.println("Item assigned successfully to staff member.");
        } catch (AssignmentLimitExceededException | ItemUnavailableException e) {
            System.out.println("Assignment failed: " + e.getMessage());
        }
    }

    /**
     * Returns an item from a staff member back to inventory.
     */
    private static void returnItemFromStaff() {
        if (staffCount == 0) {
            System.out.println("No staff registered.");
            return;
        }
        String staffId = promptNonEmpty("Enter staff ID: ");
        StaffMember staff = findStaffById(staffId);
        if (staff == null) {
            System.out.println("Staff member not found.");
            return;
        }
        String itemId = promptNonEmpty("Enter item ID to return: ");
        InventoryItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        // before returning, ensure staff actually has this item assigned
        InventoryItem[] assigned = staff.getAssignedItems();
        boolean hasItem = false;
        for (InventoryItem it : assigned) {
            if (it.getId().equals(itemId)) {
                hasItem = true;
                break;
            }
        }
        if (!hasItem) {
            System.out.println("This staff member does not hold this item.");
            return;
        }
        // perform return
        staff.returnItem(item);
        System.out.println("Item returned successfully.");
    }

    /**
     * Presents a menu for searching inventory by ID or by name.
     */
    private static void searchInventoryMenu() {
        if (inventoryCount == 0) {
            System.out.println("No items in inventory to search.");
            return;
        }
        System.out.println("\nSearch by:");
        System.out.println("1. Item ID");
        System.out.println("2. Item Name");
        int choice = readInt("Choice: ");
        switch (choice) {
            case 1: {
                String id = promptNonEmpty("Enter item ID: ");
                InventoryItem item = findItemById(id);
                if (item != null) {
                    System.out.println("Item found:\n" + item);
                } else {
                    System.out.println("No item found with the given ID.");
                }
                break;
            }
            case 2: {
                String name = promptNonEmpty("Enter item name: ");
                InventoryItem[] items = findItemsByName(name);
                if (items.length > 0) {
                    System.out.println("Items found:");
                    for (InventoryItem item : items) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("No items found with the given name.");
                }
                break;
            }
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Presents a submenu for generating various reports.
     */
    private static void generateReportsMenu() {
        System.out.println("\nSelect report to generate:");
        System.out.println("1. Inventory List");
        System.out.println("2. Expired Warranties");
        System.out.println("3. Assignment Summary");
        int choice = readInt("Choice: ");
        switch (choice) {
            case 1:
                reportInventoryList();
                break;
            case 2:
                reportExpiredWarranties();
                break;
            case 3:
                reportAssignmentSummary();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Generates a report listing all inventory items along with their details.
     * Demonstrates use of a basic for loop.
     */
    private static void reportInventoryList() {
        if (inventoryCount == 0) {
            System.out.println("No items in inventory.");
            return;
        }
        System.out.println("\n=== Inventory List ===");
        for (int i = 0; i < inventoryCount; i++) {
            System.out.println(inventory[i]);
        }
    }

    /**
     * Generates a report of items whose warranty has expired. Demonstrates the use
     * of an enhanced for-each loop.
     */
    private static void reportExpiredWarranties() {
        if (inventoryCount == 0) {
            System.out.println("No items in inventory.");
            return;
        }
        LocalDate today = LocalDate.now();
        System.out.println("\n=== Items with Expired Warranties ===");
        boolean found = false;
        for (InventoryItem item : inventory) {
            if (item == null) {
                continue;
            }
            LocalDate warrantyEnd = item.getWarrantyEndDate();
            if (warrantyEnd != null && warrantyEnd.isBefore(today)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items with expired warranties.");
        }
    }

    /**
     * Generates a summary of staff assignments. Demonstrates a do-while loop.
     */
    private static void reportAssignmentSummary() {
        if (staffCount == 0) {
            System.out.println("No staff registered.");
            return;
        }
        System.out.println("\n=== Assignment Summary ===");
        int index = 0;
        // using do-while to ensure at least one iteration if staff exist
        do {
            StaffMember staff = staffMembers[index];
            if (staff != null) {
                System.out.println(staff);
            }
            index++;
        } while (index < staffCount);
    }

    /**
     * Finds a staff member by ID using a simple for loop.
     */
    private static StaffMember findStaffById(String id) {
        for (int i = 0; i < staffCount; i++) {
            if (staffMembers[i] != null && staffMembers[i].getStaffId().equalsIgnoreCase(id)) {
                return staffMembers[i];
            }
        }
        return null;
    }

    /**
     * Finds an inventory item by its ID.
     */
    private static InventoryItem findItemById(String id) {
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] != null && inventory[i].getId().equalsIgnoreCase(id)) {
                return inventory[i];
            }
        }
        return null;
    }

    /**
     * Finds items by name (case insensitive). Demonstrates method overloading.
     * Returns an array of matches.
     */
    private static InventoryItem[] findItemsByName(String name) {
        // first determine how many match to size the array properly
        int count = 0;
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] != null && inventory[i].getName().equalsIgnoreCase(name)) {
                count++;
            }
        }
        InventoryItem[] result = new InventoryItem[count];
        int idx = 0;
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] != null && inventory[i].getName().equalsIgnoreCase(name)) {
                result[idx++] = inventory[i];
            }
        }
        return result;
    }
}
