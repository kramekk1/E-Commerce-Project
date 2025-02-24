package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cart {
    private List<Product> productsInCart;
    public Cart() {
        productsInCart = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        Random randomIdGenerator = new Random();
        int randomId = randomIdGenerator.nextInt(1000,9999);

        if (product instanceof Computer) {
            Computer computerToAdd = new Computer(String.valueOf(randomId), product.getName(), product.getPrice(), product.getAvailableCount(),
                    ((Computer) product).getProcessorModel(), ((Computer) product).getRamType());
            System.out.println("WYBÓR PROCESORA: ");
            computerToAdd.setProcessorModel(Processor.config());
            computerToAdd.setPrice(product.getPrice() + computerToAdd.getProcessorModel().getPrice());
            System.out.println("WYBÓR RAM: ");
            computerToAdd.setRamType(Ram.config());
            computerToAdd.setPrice(product.getPrice() + computerToAdd.getRamType().getPrice());

            productsInCart.add(computerToAdd);
        }
        if (product instanceof Smartphone){
            Smartphone smartphoneToAdd = new Smartphone(String.valueOf(randomId), product.getName(), ((Smartphone) product).getColor(),
                    ((Smartphone) product).getBatteryCapacity(), ((Smartphone) product).getAddonAccessory(), product.getPrice(), product.getAvailableCount());
            System.out.println("WYBÓR KOLORU: ");
            smartphoneToAdd.setColor(Color.config());
            System.out.println("WYBÓR BATERII: ");
            smartphoneToAdd.setBatteryCapacity(BatteryCapacity.config());
            smartphoneToAdd.setPrice(product.getPrice() + ((Smartphone) product).getBatteryCapacity().getPrice());
            System.out.println("WYBÓR AKCESORII");
            smartphoneToAdd.setAddonAccessory(Accessories.config());
            smartphoneToAdd.setPrice(product.getPrice() + ((Smartphone) product).getAddonAccessory().getPrice());

            productsInCart.add(smartphoneToAdd);
        }
        if (product instanceof Electronics) {
            Electronics electronicsToAdd = new Electronics(String.valueOf(randomId), product.getName(), product.getPrice(), product.getAvailableCount());
            productsInCart.add(electronicsToAdd);
        }
    }

    public void removeProductFromCart(String idToRemove) {
        productsInCart.removeIf(prod -> idToRemove.equals(prod.getId()));
    }
    public double calculateTotalPrice() {
        return productsInCart.stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }

    public void showCartContents() {
        productsInCart.forEach(System.out::println);
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

}