package model;

public enum Color {
    BLACK("Czarny"), WHITE("Biały"), GOLD("Złoty"), SILVER("Srebrny"), RED("Czerwony"),
    GREEN("Zielony"), BLUE("Niebieski"), ORANGE("Pomarańczowy"), YELLOW("Żółty"), PURPLE("Fioletowy");


    private final String description;

    Color(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
