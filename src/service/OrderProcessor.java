package service;

import ui.CommandLineInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class OrderProcessor {
    //private Map<String, String> invoiceMap;
    public Scanner userInput = new Scanner(System.in);
    private final ZonedDateTime zonedDateTime = ZonedDateTime.now();

    public OrderProcessor() {
        //invoiceMap = new HashMap<>();
    }

    public String generateInvoice(Order order) {
        return "=======================================" + "\n" +
                "Numer zamówienia: " + order.getOrderId() + "\n" +
                "Data: " + zonedDateTime + "\n" +
                "---------------------------------------" + "\n" +
                "Sprzedawca:" + "\n" + "E-Commerce sklep" + "\n" +
                "---------------------------------------" + "\n" +
                "Nabywca: " + order.getCustomerFirstName() + " " + order.getCustomerLastName() + "\n" +
                "Adres: " + order.getPostCode() + " " + order.getDestinationCity() + "\n" +
                "      " + order.getStreet() + " " + order.getHomeNumber() + "\n" +
                "Numer telefonu: " + order.getTelephoneNumber() + "\n" +
                "---------------------------------------" + "\n" +
                "Przedmioty: " + order.getCartContent().getProductsInCart() + "\n" +
                "Całkowita cena: " + CommandLineInterface.getTotalPriceForOrder() + "\n" +
                "=======================================";
    }

//    public String generateReceiptForCustomer(Order order) {
//        return "===============PARAGON===============" + "\n" +
//                "Numer zamówienia: " + order.getOrderId() + "\n" +
//                "Data: " + zonedDateTime + "\n" +
//                "---------------------------------------" + "\n" +
//                "Przedmioty: " + order.getCartContent();
//    }

    public CompletableFuture<Void> processOrder(Order order) {
        return CompletableFuture.runAsync(() -> {
            {
                savePlacedOrderToFile(order);
                System.out.println("Generuję fakturę...");
//                if (userInput.nextLine().equalsIgnoreCase("t")) {
                System.out.println(generateInvoice(order));
//                    putInvoiceToMap(order);
//                } else {
//                    System.out.println(generateReceiptForCustomer(order));
//                }
            }
        });
    }

    public void savePlacedOrderToFile(Order order) {
        String placedOrderFilePath = "src/files/invoices.txt";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(placedOrderFilePath, true))
        ) {
            bufferedWriter.write(order.toString() + " //Przedmioty: " + order.getCartContent().getProductsInCart() + " //Data złożenia: " + zonedDateTime + " //Całkowita cena: " + CommandLineInterface.getTotalPriceForOrder() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

////////////NOT ADDED////////////

//    public void putInvoiceToMap(Order order) {
//        invoiceMap.put(order.getOrderId(), generateInvoice(order));
//    }
//
//    public void searchInvoiceById(String id) {
//        for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
//            if (id.equalsIgnoreCase(entry.getKey())) {
//                System.out.println(entry.getValue());
//            }
//        }
//    }
//
//    public void removeInvoiceFromMapById(String id) {
//        for (Map.Entry<String, String> entry : invoiceMap.entrySet()) {
//            if (id.equalsIgnoreCase(entry.getKey())) {
//                invoiceMap.remove(entry.getKey());
//                break;
//            }
//        }
//    }
//    public Map<String, String> getInvoiceMap() {
//        return invoiceMap;
//    }
//
//    public void setInvoiceMap(Map<String, String> invoiceMap) {
//        this.invoiceMap = invoiceMap;
//    }
}
