package ui;

import model.Product;
import service.Cart;
import service.Order;
import service.OrderProcessor;
import service.ProductManager;

import java.time.LocalDateTime;

public class CommandLineInterface {
    private final LocalDateTime localDateTime = LocalDateTime.now();
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
    public void sendOrder(Order order) {
        orderProcessor.processOrder(order);
        System.out.println("Zamówienie o ID: " + order.getOrderId() + " zostało złożone");
        System.out.println("Czas: " + localDateTime);
    }
}
