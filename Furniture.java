import java.time.LocalDate;

/**
 * Represents pieces of furniture in the university inventory. Examples include
 * desks, chairs, cabinets, etc. Furniture has a material property and
 * overrides the maintenance calculation from {@link InventoryItem}.
 */
public class Furniture extends InventoryItem {
    private String material;

    /**
     * Constructs a new furniture item.
     *
     * @param id           unique identifier for the furniture
     * @param name         name of the furniture
     * @param material     material from which the furniture is made (e.g. wood, metal)
     * @param purchaseDate date of purchase
     * @param price        purchase price
     * @param warrantyEnd  warranty expiration date
     */
    public Furniture(String id, String name, String material, LocalDate purchaseDate, double price,
            LocalDate warrantyEnd) {
        super(id, name, purchaseDate, price, warrantyEnd);
        this.material = material;
    }

    /**
     * Returns the material of the furniture.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Provides a maintenance fee calculation specific to furniture. Furniture
     * typically requires less maintenance, so we use a lower percentage of the
     * price.
     *
     * @return maintenance fee based on 2% of the furniture price
     */
    @Override
    public double getMaintenanceFee() {
        return getPrice() * 0.02; // 2% of the purchase price
    }

    @Override
    public String toString() {
        return String.format("[Furniture] %s, Material: %s, Maintenance Fee: %.2f", super.toString(), material,
                getMaintenanceFee());
    }
}
