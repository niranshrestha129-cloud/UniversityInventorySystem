import java.time.LocalDate;

/**
 * Represents specialized laboratory equipment in the inventory. These items
 * typically require more maintenance and calibration than regular equipment or
 * furniture. Examples might include microscopes, spectrometers, or other
 * scientific instruments.
 */
public class LabEquipment extends InventoryItem {
    private String labType;

    /**
     * Constructs a new lab equipment item.
     *
     * @param id           unique identifier for the lab equipment
     * @param name         name of the equipment
     * @param labType      type of lab this equipment belongs to (e.g. chemistry, physics)
     * @param purchaseDate date of purchase
     * @param price        purchase price
     * @param warrantyEnd  warranty expiration date
     */
    public LabEquipment(String id, String name, String labType, LocalDate purchaseDate, double price,
            LocalDate warrantyEnd) {
        super(id, name, purchaseDate, price, warrantyEnd);
        this.labType = labType;
    }

    /**
     * Returns the lab type for this equipment.
     */
    public String getLabType() {
        return labType;
    }

    /**
     * Provides a maintenance fee calculation specific to laboratory equipment.
     * Laboratory equipment tends to need more upkeep, so we use a higher
     * percentage.
     *
     * @return maintenance fee based on 10% of the equipment price
     */
    @Override
    public double getMaintenanceFee() {
        return getPrice() * 0.10; // 10% of the purchase price
    }

    @Override
    public String toString() {
        return String.format("[LabEquipment] %s, Lab Type: %s, Maintenance Fee: %.2f", super.toString(), labType,
                getMaintenanceFee());
    }
}
