package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsInCart;
    private boolean activatePromotion = false;

    public Cart() {
        productsInCart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        if (product instanceof Computer) {
            System.out.println("WYBÓR PROCESORA: ");
            ((Computer) product).setProcessorModel(Processor.config());
            product.setPrice(product.getPrice() + ((Computer) product).getProcessorModel().getPrice());
            System.out.println("WYBÓR RAM: ");
            ((Computer) product).setRamType(Ram.config());
            product.setPrice(product.getPrice() + ((Computer) product).getRamType().getPrice());
        }
        if (product instanceof Smartphone){
            System.out.println("WYBÓR KOLORU: ");
            ((Smartphone) product).setColor(Color.config());
            System.out.println("WYBÓR BATERII: ");
            ((Smartphone) product).setBatteryCapacity(BatteryCapacity.config());
            product.setPrice(product.getPrice() + ((Smartphone) product).getBatteryCapacity().getPrice());
            System.out.println("WYBÓR AKCESORII");
            ((Smartphone) product).setAddonAccessory(Accessories.config());
            product.setPrice(product.getPrice() + ((Smartphone) product).getAddonAccessory().getPrice());
        }
        productsInCart.add(product);
    }

    public void removeProductFromCart(Product product) {
        productsInCart.removeIf(product::equals);
    }
    public double calculateTotalPrice() {
        double totalPrice = productsInCart.stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);

        if (activatePromotion) {
            return totalPrice * 0.8;
        }
        return totalPrice;
    }

    public void showCartContents() {
        productsInCart.forEach(System.out::println);
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public boolean isActivatePromotion() {
        return activatePromotion;
    }

    public void setActivatePromotion(boolean activatePromotion) {
        this.activatePromotion = activatePromotion;
    }
}