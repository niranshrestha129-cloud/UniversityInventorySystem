/**
 * Thrown when an attempt is made to assign an inventory item that is
 * currently unavailable. An item may be unavailable because it is already
 * assigned to another staff member or has been removed from inventory.
 */
public class ItemUnavailableException extends Exception {
    public ItemUnavailableException(String message) {
        super(message);
    }
}
