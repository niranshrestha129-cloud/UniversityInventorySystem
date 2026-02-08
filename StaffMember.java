import java.util.Arrays;

/**
 * Represents a staff member who can borrow inventory items from the university
 * system. Each staff member has a unique identifier, a name, and can be
 * assigned up to a fixed number of items. The class provides methods to assign
 * and return items while enforcing limits and checking availability.
 */
public class StaffMember {
    private static final int MAX_ITEMS = 5;

    private String staffId;
    private String name;
    private InventoryItem[] assignedItems;
    private int itemCount;

    /**
     * Constructs a new staff member.
     *
     * @param staffId unique identifier for the staff member
     * @param name    name of the staff member
     */
    public StaffMember(String staffId, String name) {
        this.staffId = staffId;
        this.name = name;
        this.assignedItems = new InventoryItem[MAX_ITEMS];
        this.itemCount = 0;
    }

    /**
     * Returns the staff ID.
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * Returns the staff member's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns an inventory item to this staff member. If the staff member
     * already holds the maximum number of items or the item is not available,
     * a custom exception is thrown.
     *
     * @param item the inventory item to assign
     * @throws AssignmentLimitExceededException if staff has reached the limit
     * @throws ItemUnavailableException         if the item is not available
     */
    public void assignItem(InventoryItem item) throws AssignmentLimitExceededException, ItemUnavailableException {
        if (itemCount >= MAX_ITEMS) {
            throw new AssignmentLimitExceededException("Staff member has reached the maximum allowed items.");
        }
        if (!item.isAvailable()) {
            throw new ItemUnavailableException("Item is not available for assignment.");
        }
        // assign the item
        assignedItems[itemCount++] = item;
        item.setAvailable(false);
    }

    /**
     * Returns an assigned item back to the inventory. If the staff member does
     * not have the item, nothing happens.
     *
     * @param item the inventory item to return
     */
    public void returnItem(InventoryItem item) {
        for (int i = 0; i < itemCount; i++) {
            if (assignedItems[i] != null && assignedItems[i].getId().equals(item.getId())) {
                // mark item available again
                assignedItems[i].setAvailable(true);
                // remove from staff's list by shifting elements down
                for (int j = i; j < itemCount - 1; j++) {
                    assignedItems[j] = assignedItems[j + 1];
                }
                assignedItems[itemCount - 1] = null;
                itemCount--;
                break;
            }
        }
    }

    /**
     * Returns the array of assigned items. May contain null values if the
     * staff member has fewer than the maximum number of items.
     */
    public InventoryItem[] getAssignedItems() {
        return Arrays.copyOf(assignedItems, itemCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Staff ID: %s, Name: %s, Assigned Items: %d\n", staffId, name, itemCount));
        for (int i = 0; i < itemCount; i++) {
            sb.append("  -> ").append(assignedItems[i]).append("\n");
        }
        return sb.toString();
    }
}
