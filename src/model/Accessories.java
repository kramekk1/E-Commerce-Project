package model;

public enum Accessories {
    CASE("Case"), CHARGER("Ładowarka"), HEADPHONES("Słuchawki"),
    TEMPERED_GLASS("Szkło hartowane"), CAR_HOLDER("Uchwyt do samochodu");

    private final String  description;

    Accessories(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
