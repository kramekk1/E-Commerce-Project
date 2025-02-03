package ui;

import model.Product;
import service.Cart;
import service.Order;
import service.OrderProcessor;
import service.ProductManager;


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
    public void sendOrder(Order order) {
        order.prepareOrderToProcess();
        orderProcessor.processOrder(order);
        System.out.println("Zamówienie o ID: " + order.getOrderId() + " zostało złożone");
    }
}
