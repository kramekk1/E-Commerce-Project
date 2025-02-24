package model;

import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double price;
    private int availableCount;

    public Product(String id, String name, double price, int availableCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableCount = availableCount;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Nazwa: " + name +
                ", Cena: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && availableCount == product.availableCount && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, availableCount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}