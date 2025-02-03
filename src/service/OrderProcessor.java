package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class OrderProcessor {
    private List<Order> placedOrder;
    private Map<String, String> invoiceMap;
    public Scanner userInput = new Scanner(System.in);
    private final LocalDateTime localDateTime = LocalDateTime.now();

    public OrderProcessor() {
        placedOrder = new ArrayList<>();
        invoiceMap = new HashMap<>();
    }

    public String generateInvoice(Order order) {
//        System.out.println("=======================================");
//        System.out.println("Numer zamówienia: " + order.getOrderId());
//        System.out.println("---------------------------------------");
//        System.out.println("Sprzedawca:" + "\n" + "E-Commerce sklep");
//        System.out.println("---------------------------------------");
//        System.out.println("Nabywca: " + order.getCustomerFirstName() + " " + order.getCustomerLastName());
//        System.out.println("Adres: " + order.getPostCode() + " " + order.getDestinationCity());
//        System.out.println("      " + order.getStreet() + " " + order.getHomeNumber());
//        System.out.println("Numer telefonu: " + order.getTelephoneNumber());
//        System.out.println("---------------------------------------");
//        System.out.println("Przedmioty: " + order.getCartContent());
//        System.out.println("=======================================");
        return "=======================================" + "\n" +
                "Numer zamówienia: " + order.getOrderId() + "\n" +
                "Data: " + localDateTime + "\n" +
                "---------------------------------------" + "\n" +
                "Sprzedawca:" + "\n" + "E-Commerce sklep" + "\n" +
                "---------------------------------------" + "\n" +
                "Nabywca: " + order.getCustomerFirstName() + " " + order.getCustomerLastName() + "\n" +
                "Adres: " + order.getPostCode() + " " + order.getDestinationCity() + "\n" +
                "      " + order.getStreet() + " " + order.getHomeNumber() + "\n" +
                "Numer telefonu: " + order.getTelephoneNumber() + "\n" +
                "---------------------------------------" + "\n" +
                "Przedmioty: " + order.getCartContent() + "\n" +
                "=======================================";
    }

    public String generateReceiptForCustomer(Order order) {
        return "===============PARAGON===============" + "\n" +
                "Numer zamówienia: " + order.getOrderId() + "\n" +
                "Data: " + localDateTime + "\n" +
                "---------------------------------------" + "\n" +
                "Przedmioty: " + order.getCartContent();
    }

    public void processOrder(Order order) {
        placedOrder.add(order);
        savePlacedOrderToFile(order);

        System.out.println("Czy wygenerować fakturę do zamówienia? Podaj T/N");
        if (userInput.nextLine().equalsIgnoreCase("t")) {
            System.out.println(generateInvoice(order));
            putInvoiceToMap(order);
        } else {
            System.out.println(generateReceiptForCustomer(order));
        }
    }

    public void savePlacedOrderToFile(Order order) {
        String placedOrderFilePath = "src/orders.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(placedOrderFilePath, true))
        ) {
            bufferedWriter.write(order.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void putInvoiceToMap(Order order) {
        invoiceMap.put(order.getOrderId(), generateInvoice(order));
    }

    public void searchInvoiceById(String id) {
        for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
            if (id.equalsIgnoreCase(entry.getKey())) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void removeInvoiceFromMapById(String id) {
        for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
            if (id.equalsIgnoreCase(entry.getKey())) {
                invoiceMap.remove(entry.getKey());
                break;
            }
        }
    }

    public List<Order> getPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(List<Order> placedOrder) {
        this.placedOrder = placedOrder;
    }

    public Map<String, String> getInvoiceMap() {
        return invoiceMap;
    }

    public void setInvoiceMap(Map<String, String> invoiceMap) {
        this.invoiceMap = invoiceMap;
    }
}
