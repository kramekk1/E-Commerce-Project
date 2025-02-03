package ui;

import model.Product;
import service.Cart;
import service.Order;
import service.OrderProcessor;
import service.ProductManager;

import java.util.Random;
import java.util.Scanner;


public class CommandLineInterface {
    public ProductManager productManager = new ProductManager();
    public Cart cartManager = new Cart();
    public OrderProcessor orderProcessor = new OrderProcessor();
    public void showShopContent() {
        productManager.showProductsInShop();
    }
    public void addItemToCart(Product product) {
        cartManager.addProductToCart(product);
    }
    public void removeItemFromCart(Product product) {
        cartManager.removeProductFromCart(product);
    }
    public void clearCart() {
        cartManager.getProductsInCart().clear();
    }
    public void showItemsInCart() {
        cartManager.getProductsInCart().forEach(System.out::println);
    }
    public void orderPaymentInfo(Order order) {
        order.showOrderPaymentInfo();
    }
    public Order createOrder() {
        Random orderIdGenerator = new Random();
        String generatedId = String.valueOf(orderIdGenerator.nextInt(1000,9999));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj dane do zamówienia");
        System.out.println("Podaj imię");
        String name = scanner.nextLine();
        System.out.println("Podaj nazwisko");
        String lastName = scanner.nextLine();
        System.out.println("Podaj miasto do dostawy");
        String city = scanner.nextLine();
        System.out.println("Podaj kod pocztowy");
        String postCode = scanner.nextLine();
        System.out.println("Podaj ulicę");
        String street = scanner.nextLine();
        System.out.println("Podaj numer domu/mieszkania");
        int homeNumber = scanner.nextInt();
        System.out.println("Podaj numer telefonu");
        String telNumber = scanner.nextLine();
        return new Order(generatedId, name, lastName, city, postCode, street, homeNumber, telNumber, cartManager);
    }
    public void sendOrder() {
        Order createdOrder = createOrder();
        createdOrder.prepareOrderToProcess();
        orderProcessor.processOrder(createdOrder);
        System.out.println("Zamówienie o ID: " + createdOrder.getOrderId() + " zostało złożone");
    }
}
