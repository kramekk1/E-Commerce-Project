package model;

public enum Ram {
    KINGSTON_4GB("Kingston ValueRAM, DDR3, 4 GB, 1600MHz"), CORSAIR_4GB("Corsair Vengeance LPX, DDR4, 4 GB, 2400MHz"),
    CORSAIR_8GB("Corsair Value Select, DDR4, 8 GB, 2133MHz"), GIGABYTE_16GB("Gigabyte AORUS RGB, DDR4, 16 GB, 3733MHz"),
    CORSAIR_16GB("Corsair Vengeance LPX, DDR4, 16 GB, 3000MHz");

    private final String description;

    Ram(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
