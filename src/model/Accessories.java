package model;

import java.util.Scanner;

public enum Accessories {
    CASE("Case", 200), CHARGER("Ładowarka", 150), HEADPHONES("Słuchawki", 300),
    TEMPERED_GLASS("Szkło hartowane", 50), CAR_HOLDER("Uchwyt do samochodu", 100),
    NONE("NIE WYBRANO", 0);

    private final String  description;
    private final double price;

    Accessories(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public static Accessories config() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Wybierz numer Akcesorii z dostępnych: ");
        for (Accessories accessories : Accessories.values()) {
            counter++;
            System.out.println(" [" + counter + "] " + accessories.getDescription());
        }
        String userInput = scanner.nextLine();

        return switch (userInput){
            case "1" -> CASE;
            case "2" ->  CHARGER;
            case "3" -> HEADPHONES;
            case "4" -> TEMPERED_GLASS;
            case "5" -> CAR_HOLDER;
            default -> config();
        };
    }
}
