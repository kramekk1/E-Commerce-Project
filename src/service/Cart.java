package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsInCart;

    public Cart() {
        productsInCart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        productsInCart.add(product);
    }

    public void removeProductFromCart(Product product) {
        productsInCart.removeIf(product::equals);
    }

    public void clearCart() {
        productsInCart.clear();
    }

    public void showCartContents() {
        productsInCart.forEach(System.out::println);
    }

    public List<Product> createdOrder() {
        List<Product> orderedItems;
        orderedItems = productsInCart;
        clearCart();
        return orderedItems;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

}