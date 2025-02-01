package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderProcessor {
    private List<Order> placedOrder;
    public Scanner userInput = new Scanner(System.in);

    public OrderProcessor() {
        placedOrder = new ArrayList<>();
    }
    public void generateInvoice(Order order) {
        System.out.println("=======================================");
        System.out.println("Numer zamówienia: " + order.getOrderId());
        System.out.println("---------------------------------------");
        System.out.println("Sprzedawca:" + "\n" + "E-Commerce sklep");
        System.out.println("---------------------------------------");
        System.out.println("Nabywca: " + order.getCustomerFirstName() + " " + order.getCustomerLastName());
        System.out.println("Adres: " + order.getPostCode() + " " + order.getDestinationCity());
        System.out.println("      " + order.getStreet() + " " + order.getHomeNumber());
        System.out.println("Numer telefonu: " + order.getTelephoneNumber());
        System.out.println("---------------------------------------");
        System.out.println("Przedmioty: " + order.getCartContent());
        System.out.println("=======================================");
    }

    public void processOrder(Order order) {
        placedOrder.add(order);
        System.out.println("Czy wygenerować fakturę do zamówienia? Podaj T/N");
        if (userInput.nextLine().equalsIgnoreCase("t")) {
            generateInvoice(order);
        }
    }

    public List<Order> getPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(List<Order> placedOrder) {
        this.placedOrder = placedOrder;
    }
}
