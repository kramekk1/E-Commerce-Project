package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class OrderProcessor {
    private List<Order> placedOrder;
    //private Map<String, String> invoiceMap;
    public Scanner userInput = new Scanner(System.in);
    private final LocalDateTime localDateTime = LocalDateTime.now();

    public OrderProcessor() {
        placedOrder = new ArrayList<>();
        //invoiceMap = new HashMap<>();
    }

    public String generateInvoice(Order order) {
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
                "Przedmioty: " + order.getCartContent().getProductsInCart() + "\n" +
                "Całkowita cena: " + order.getCartContent().calculateTotalPrice() + "\n" +
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
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            {
                try {
                    Thread.sleep(1000);
                    placedOrder.add(order);
                    savePlacedOrderToFile(order);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return " Przetwarzanie: czesc 1 ";
            }
        });

        future.thenAccept(System.out::println).join();

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Czy wygenerować fakturę do zamówienia? Podaj T/N");
                if (userInput.nextLine().equalsIgnoreCase("t")) {
                    System.out.println(generateInvoice(order));
                    //putInvoiceToMap(order);
                } else {
                    System.out.println(generateReceiptForCustomer(order));
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return " Przetwarzanie: czesc 2 ";
        });

        CompletableFuture<String> combined = future.thenCombine(future2, (result1, result2) -> result1 + result2);

        combined.thenAccept(result -> System.out.println("Przetworzono dwie czesci: " + result)).join();
    }

    public void savePlacedOrderToFile(Order order) {
        String placedOrderFilePath = "src/files/orders.txt";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(placedOrderFilePath, true))
        ) {
            bufferedWriter.write(order.toString() + " //Przedmioty: " + order.getCartContent().getProductsInCart() + " //Data złożenia: " + localDateTime + " //Całkowita cena: " + order.getCartContent().calculateTotalPrice() + "\n");
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

    public List<Order> getPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(List<Order> placedOrder) {
        this.placedOrder = placedOrder;
    }

//    public Map<String, String> getInvoiceMap() {
//        return invoiceMap;
//    }
//
//    public void setInvoiceMap(Map<String, String> invoiceMap) {
//        this.invoiceMap = invoiceMap;
//    }
}
