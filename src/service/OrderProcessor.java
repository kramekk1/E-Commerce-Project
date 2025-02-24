package service;

import model.Order;
import ui.CommandLineInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;

public class OrderProcessor {
    private final ZonedDateTime zonedDateTime = ZonedDateTime.now();

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
    public CompletableFuture<Void> processOrder(Order order) {
        return CompletableFuture.runAsync(() -> {
            {
                savePlacedOrderToFile(order);
                System.out.println("Generuję fakturę...");
                System.out.println(generateInvoice(order));
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
}
