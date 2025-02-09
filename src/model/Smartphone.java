package model;

public class Smartphone extends Product{
    private Color color;
    private BatteryCapacity batteryCapacity;
    private Accessories addonAccessory;

    public Smartphone(String id, String name, Color color, BatteryCapacity batteryCapacity, Accessories addonAccessory, double price, int availableCount) {
        super(id, name, price, availableCount);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.addonAccessory = addonAccessory;
    }

    @Override
    public String toString() {
        return super.toString() + " Kolor: " + color.getDescription() + " Pojemność baterii: " + batteryCapacity.getDescription() + " Akcesoria w zestawie: " + addonAccessory.getDescription();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BatteryCapacity getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(BatteryCapacity batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Accessories getAddonAccessory() {
        return addonAccessory;
    }

    public void setAddonAccessory(Accessories addonAccessory) {
        this.addonAccessory = addonAccessory;
    }
}
