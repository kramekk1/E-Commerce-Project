package model;

import ui.CommandLineInterface;

public class Order {
    private String orderId;
    private String customerFirstName;
    private String customerLastName;
    private String destinationCity;
    private String postCode;
    private String street;
    private int homeNumber;
    private String telephoneNumber;
    private Cart cartContent;

    public Order(String orderId, String customerFirstName, String customerLastName, String destinationCity, String postCode, String street, int homeNumber, String telephoneNumber, Cart cartContent) {
        this.orderId = orderId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.destinationCity = destinationCity;
        this.postCode = postCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.telephoneNumber = telephoneNumber;
        this.cartContent = cartContent;
    }

    public void prepareOrderToProcess() {
        System.out.println("Przetwarzanie zawartości koszyka...");
        cartContent.showCartContents();
        System.out.println("Całkowita cena: " + CommandLineInterface.getTotalPriceForOrder());
    }

    @Override
    public String toString() {
        return "ID: " + orderId + ", Imie: " + customerFirstName +
                ", Nazwisko: " + customerLastName +
                ", Miasto: " + destinationCity +
                ", Kod pocztowy: " + postCode +
                ", Ulica: " + street +
                ", Numer domu/mieszkania: " + homeNumber +
                ", Numer telefonu: " + telephoneNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Cart getCartContent() {
        return cartContent;
    }

}