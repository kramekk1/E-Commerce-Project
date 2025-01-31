package service;

import model.Product;

import java.awt.*;

public class Order {
    private String orderId;
    private String customerFirstName;
    private String customerLastName;
    private String destinationCity;
    private String postCode;
    private String street;
    private int homeNumber;
    private String telephoneNumber;

    public double calculateTotalPrice(Cart cart) {
        return cart.createdOrder().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
    public void prepareOrderToProcess() {

    }
}