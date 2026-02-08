import java.time.LocalDate;

/**
 * Abstract base class representing a generic inventory item in the university system.
 *
 * Each inventory item has a unique identifier, name, purchase date, price and
 * warranty information. Derived classes should implement {@code getMaintenanceFee()}
 * to calculate the appropriate maintenance cost for that item type. This class
 * also tracks whether the item is currently available for assignment.
 */
public abstract class InventoryItem {
    private String id;
    private String name;
    private LocalDate purchaseDate;
    private double price;
    private LocalDate warrantyEndDate;
    private boolean available;

    /**
     * Constructs a new inventory item.
     *
     * @param id            unique identifier for the item
     * @param name          descriptive name of the item
     * @param purchaseDate  date the item was purchased
     * @param price         purchase price of the item
     * @param warrantyEnd   date the warranty expires
     */
    public InventoryItem(String id, String name, LocalDate purchaseDate, double price, LocalDate warrantyEnd) {
        this.id = id;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.warrantyEndDate = warrantyEnd;
        this.available = true; // items are available by default when added
    }

    /**
     * Returns the unique identifier of the item.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the descriptive name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the purchase date of the item.
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Returns the price at which the item was purchased.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the end date of the warranty.
     */
    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    /**
     * Checks whether the item is available for assignment.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability status of the item.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns a string representation of the item, including key details.
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Price: %.2f, Purchased: %s, Warranty End: %s, Available: %s", id, name, price,
                purchaseDate, warrantyEndDate, available);
    }

    /**
     * Calculates the maintenance fee for the item. Subclasses must provide
     * their own implementation.
     *
     * @return calculated maintenance fee
     */
    public abstract double getMaintenanceFee();
}
