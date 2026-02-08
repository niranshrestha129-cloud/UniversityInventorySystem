import java.time.LocalDate;

/**
 * Represents general equipment in the university inventory. This could include
 * items like laptops, projectors, tablets, etc. Equipment carries brand
 * information and inherits common inventory properties from {@link InventoryItem}.
 */
public class Equipment extends InventoryItem {
    private String brand;

    /**
     * Constructs a new equipment item.
     *
     * @param id           unique identifier for the equipment
     * @param name         name of the equipment
     * @param brand        manufacturer or brand of the equipment
     * @param purchaseDate date of purchase
     * @param price        purchase price
     * @param warrantyEnd  warranty expiration date
     */
    public Equipment(String id, String name, String brand, LocalDate purchaseDate, double price, LocalDate warrantyEnd) {
        super(id, name, purchaseDate, price, warrantyEnd);
        this.brand = brand;
    }

    /**
     * Returns the brand of the equipment.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Provides a maintenance fee calculation specific to general equipment.
     * For example, we consider a fixed percentage of the price as a basic fee.
     *
     * @return maintenance fee based on 5% of the equipment price
     */
    @Override
    public double getMaintenanceFee() {
        return getPrice() * 0.05; // 5% of the purchase price
    }

    @Override
    public String toString() {
        return String.format("[Equipment] %s, Brand: %s, Maintenance Fee: %.2f", super.toString(), brand,
                getMaintenanceFee());
    }
}
