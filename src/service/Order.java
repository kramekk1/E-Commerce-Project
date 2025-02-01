package service;

import model.Product;

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

    public double calculateTotalPrice(Cart cart) {
        return cart.getProductsInCart().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
    public void prepareOrderToProcess() {
        System.out.println("Przetwarzanie zawartości koszyka...");
        cartContent.showCartContents();
        System.out.println("Całkowita cena: " + calculateTotalPrice(cartContent));
    }
    public void showOrderInfo() {
        System.out.println("Zawartość koszyka: ");
        cartContent.showCartContents();
        System.out.println("Całkowita cena: " + calculateTotalPrice(cartContent));
        System.out.println("Dane do zamówienia: " + Order.this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber=" + homeNumber +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Cart getCartContent() {
        return cartContent;
    }

    public void setCartContent(Cart cartContent) {
        this.cartContent = cartContent;
    }
}