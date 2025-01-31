package model;

public enum BatteryCapacity {
    B_3000MAH("3000 mAh"), B_3350MAH("3350 mAh"), B_3650MAH("3650 mAh"),
    B_4350MAH("4350 mAh"), B_4850MAH("4850 mAh");
    private final String description;

    BatteryCapacity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
