package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductManager {
    private List<Product> productsInShop;

    public ProductManager() {
        productsInShop = new ArrayList<>();
    }

    public void adminAdd(Product product) {
        productsInShop.add(product);
    }
    public void addProductToShop() throws DuplicateIdException {
        Product product = configurateProduct();
        if (isIdAlreadyExist(product)) {
            throw new DuplicateIdException("ID produktu, który próbujesz dodać już istnieje!");
        } else {
            productsInShop.add(product);
            System.out.println("Pomyślnie dodano produkt ID: " + product.getId() + " // " + product.getName() + " // ");
        }
    }
    public Product configurateProduct() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Podaj ID: ");
        String id = userInput.nextLine();
        System.out.println("Podaj nazwe: ");
        String name = userInput.nextLine();
        System.out.println("Podaj cenę: ");
        double price = userInput.nextDouble();
        System.out.println("Podaj liczbę sztuk: ");
        int availableCount = userInput.nextInt();

        System.out.println("Komputer, Smartfon czy Elektronika?");
        userInput.nextLine();
        String choice = userInput.nextLine().toLowerCase();

        return switch (choice) {
            case "komputer" -> new Computer(id, name, price, availableCount, Processor.NONE, Ram.NONE);
            case "smartfon" -> new Smartphone(id, name, Color.NONE, BatteryCapacity.NONE, Accessories.NONE, price, availableCount);
            case "elektronika" -> new Electronics(id, name, price, availableCount);
            default -> new Product(id, name, price, availableCount);
        };
    }
    public boolean isIdAlreadyExist(Product product) {
        return productsInShop.stream()
                .anyMatch(p -> p.getId().equals(product.getId()));
    }

    public void removeProductFromShopById(String id) {
        System.out.println("Usuwanie produktu o ID: " + id);
        productsInShop.removeIf(value -> id.equals(value.getId()));
    }

    public void showProductsInShop() {
        System.out.println("Wszystkie produkty w sklepie: ");
        productsInShop.forEach(product -> System.out.println(product + " ///Dostępna ilość sztuk: " + product.getAvailableCount()));
    }

    public Optional<Product> findedProductById(String id) {
        return productsInShop.stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst();
    }

    public void updateProductNameById(String id, String newName) {
        Optional<Product> findedProduct = findedProductById(id);

        findedProduct.ifPresentOrElse(
                product -> {
                    product.setName(newName);
                    System.out.println("Zaktualizowano NAZWĘ produktu o ID: " + id);
                },
                () -> System.out.println("Produkt o podanym ID: " + id + " nie został odnaleziony")
        );
    }

    public void updateProductPriceById(String id, double newPrice) {
        Optional<Product> findedProduct = findedProductById(id);

        findedProduct.ifPresentOrElse(
                product -> {
                    product.setPrice(newPrice);
                    System.out.println("Zaktualizowano CENĘ produktu o ID: " + id);
                },
                () -> System.out.println("Produkt o ID: " + id + " nie został znaleziony.")
        );
    }

    public void updateProductAvailableCountById(String id, int newAvailableCount) {
        Optional<Product> findedProduct = findedProductById(id);

        findedProduct.ifPresentOrElse(
                product -> {
                    product.setAvailableCount(newAvailableCount);
                    System.out.println("Zaktualizowano DOSTĘPNĄ ILOŚĆ produktu o ID: " + id);
                },
                () -> System.out.println("Produkt o ID: " + id + " nie został znaleziony.")
        );
    }

    public List<Product> getProductsInShop() {
        return productsInShop;
    }

    public void setProductsInShop(List<Product> productsInShop) {
        this.productsInShop = productsInShop;
    }
}
