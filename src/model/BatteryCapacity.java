package model;

import java.util.Scanner;

public enum BatteryCapacity {
    B_3000MAH("3000 mAh", 50), B_3350MAH("3350 mAh", 100), B_3650MAH("3650 mAh", 150),
    B_4350MAH("4350 mAh", 200), B_4850MAH("4850 mAh", 250),
    NONE("NIE WYBRANO", 0);
    private final String description;
    private final double price;

    BatteryCapacity(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public static BatteryCapacity config() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Wybierz numer Pojemnośći Baterii z dostępnych: ");
        for (BatteryCapacity battCap : BatteryCapacity.values()) {
            counter++;
            System.out.println(" [" + counter + "] " + battCap.getDescription());
        }
        String userInput = scanner.nextLine();

        return switch (userInput){
            case "1" -> B_3000MAH;
            case "2" ->  B_3350MAH;
            case "3" -> B_3650MAH;
            case "4" -> B_4350MAH;
            case "5" -> B_4850MAH;
            default -> config();
        };
    }

}
