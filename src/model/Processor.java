package model;

import java.util.Scanner;

public enum Processor {
    AMD_RYZEN5600("AMD Ryzen 5 5600", 1000), INTEL_I5("Intel Core i5-12400F", 1500), INTEL_I7("Intel Core i7-14700K", 2000),
    INTEL_I9("Intel Core i9-14900K", 2300), AMD_RYZEN7900X("AMD Ryzen 9 7900X", 2600),
    NONE("NIE WYBRANO", 0);
    private final String description;
    private final double price;

    Processor(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static Processor config() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Wybierz numer procesora z dostępnych: ");
        for (Processor proc : Processor.values()) {
            counter++;
            System.out.println(" [" + counter + "] " + proc.getDescription());
        }
        String userInput = scanner.nextLine();

        return switch (userInput){
            case "1" -> AMD_RYZEN5600;
            case "2" ->  INTEL_I5;
            case "3" -> INTEL_I7;
            case "4" -> INTEL_I9;
            case "5" -> AMD_RYZEN7900X;
            default -> config();
        };
    }
}
