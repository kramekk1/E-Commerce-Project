package model;

import java.util.Scanner;
import java.util.zip.CRC32;

public enum Ram {
    KINGSTON_4GB("Kingston ValueRAM, DDR3, 4 GB, 1600MHz", 300), CORSAIR_4GB("Corsair Vengeance LPX, DDR4, 4 GB, 2400MHz", 600),
    CORSAIR_8GB("Corsair Value Select, DDR4, 8 GB, 2133MHz", 900), GIGABYTE_16GB("Gigabyte AORUS RGB, DDR4, 16 GB, 3733MHz", 1000),
    CORSAIR_16GB("Corsair Vengeance LPX, DDR4, 16 GB, 3000MHz", 1200),
    NONE("NIE WYBRANO", 0);

    private final String description;
    private final double price;

    Ram(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    public static Ram config() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Wybierz numer Ram z dostępnych: ");
        for (Ram ram : Ram.values()) {
            counter++;
            System.out.println(" [" + counter + "] " + ram.getDescription());
        }
        String userInput = scanner.nextLine();

        return switch (userInput){
            case "1" -> KINGSTON_4GB;
            case "2" ->  CORSAIR_4GB;
            case "3" -> CORSAIR_8GB;
            case "4" -> GIGABYTE_16GB;
            case "5" -> CORSAIR_16GB;
            default -> config();
        };
    }
}
