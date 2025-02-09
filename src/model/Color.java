package model;

import java.util.Scanner;

public enum Color {
    BLACK("Czarny"), WHITE("Biały"), GOLD("Złoty"), SILVER("Srebrny"), RED("Czerwony"),
    GREEN("Zielony"), BLUE("Niebieski"), ORANGE("Pomarańczowy"), YELLOW("Żółty"), PURPLE("Fioletowy"),
    NONE("NIE WYBRANO");

    private final String description;

    Color(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public static Color config() {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        System.out.println("Wybierz numer Koloru z dostępnych: ");
        for (Color color : Color.values()) {
            counter++;
            System.out.println(" [" + counter + "] " + color.getDescription());
        }
        String userInput = scanner.nextLine();

        return switch (userInput){
            case "1" -> BLACK;
            case "2" ->  WHITE;
            case "3" -> GOLD;
            case "4" -> SILVER;
            case "5" -> RED;
            case "6" -> GREEN;
            case "7" -> BLUE;
            case "8" -> ORANGE;
            case "9" -> YELLOW;
            case "10" -> PURPLE;
            default -> config();
        };
    }
}
