package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private List<Product> productsInShop;

    public ProductManager() {
        productsInShop = new ArrayList<>();
    }

    public void addProductToShop(Product product) {
        System.out.println("Pomyślnie dodano produkt ID: " + product.getId() + " // " + product.getName() + " // ");
        productsInShop.add(product);
    }

    public void removeProductFromShopById(String id) {
        System.out.println("Usuwanie produktu o ID: " + id);
        productsInShop.removeIf(value -> id.equals(value.getId()));
    }

    public void showProductsInShop() {
        System.out.println("Wszystkie produkty w sklepie: ");
        productsInShop.forEach(System.out::println);
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
