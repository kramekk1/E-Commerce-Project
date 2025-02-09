package ui;

import model.Product;
import service.*;

import java.util.Random;
import java.util.Scanner;


public class CommandLineInterface {
    private ProductManager productManager = new ProductManager();
    private Cart cartManager = new Cart();
    private OrderProcessor orderProcessor = new OrderProcessor();
    private final Scanner scanner = new Scanner(System.in);
    private boolean promotion;

    public CommandLineInterface() {
    }

    public void showShopContent() {
        productManager.showProductsInShop();
    }
    public void addItemToCart() {
        System.out.println("Podaj ID produktu do dodania");
        String idToAdd = scanner.nextLine();
        Product product = findProductMatchingById(idToAdd);

        if (product != null) {
            cartManager.addProductToCart(product);
            product.setAvailableCount(product.getAvailableCount() - 1);
        } else {
            System.out.println("Podane ID nieistnieje");
        }
    }
    public void removeItemFromCart() {
        System.out.println("Podaj ID produktu do usunięcia");
        String idToRemove = scanner.nextLine();
        Product product = findProductMatchingById(idToRemove);

        if (product != null) {
            cartManager.removeProductFromCart(product);
            product.setAvailableCount(product.getAvailableCount() + 1);
        } else {
            System.out.println("Podane ID nieistnieje");
        }

    }
    public void clearCart() {
        cartManager.getProductsInCart().clear();
    }
    public void showItemsInCart() {
        cartManager.getProductsInCart().forEach(System.out::println);
    }
    public void orderPaymentInfo() {
        System.out.println("Aktualna kwota do zapłaty");
        System.out.println(cartManager.calculateTotalPrice());
    }
    public Order createOrder() {
        Random orderIdGenerator = new Random();
        String generatedId = String.valueOf(orderIdGenerator.nextInt(1000,9999));

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
        scanner.nextLine();
        String telNumber = scanner.nextLine();
        return new Order(generatedId, name, lastName, city, postCode, street, homeNumber, telNumber, cartManager);
    }
    public void sendOrder() {
        Order createdOrder = createOrder();
        createdOrder.prepareOrderToProcess();
        orderProcessor.processOrder(createdOrder);
        System.out.println("Zamówienie o ID: " + createdOrder.getOrderId() + " zostało złożone");
    }

    public Product findProductMatchingById(String id) {
        return productManager.getProductsInShop().stream()
                .filter(prod -> id.equalsIgnoreCase(prod.getId()))
                .findFirst()
                .orElse(null);
    }
    //////////////////ADMIN OPTIONS////////////////////

    public void adminAddProductToShop() throws DuplicateIdException {
        productManager.addProductToShop();
    }
    public void adminRemoveProductFromShop() {
        System.out.println("Podaj ID do usunięcia: ");
        String idToRemove = scanner.nextLine();
        if (findProductMatchingById(idToRemove) != null) {
            productManager.removeProductFromShopById(idToRemove);
        } else {
            System.out.println("Niepoprawne ID");
        }
    }
    public void adminChangeProductName() {
        System.out.println("Podaj ID do zmiany nazwy");
        String id = scanner.nextLine();

        if (findProductMatchingById(id) != null) {
            System.out.println("Podaj nową nazwę dla: " + findProductMatchingById(id));
            String newName = scanner.nextLine();
            productManager.updateProductNameById(id, newName);
        } else {
            System.out.println("Niepoprawne ID");
        }
    }
    public void adminChangeProductPrice() {
        System.out.println("Podaj ID do zmiany ceny");
        String id = scanner.nextLine();

        if (findProductMatchingById(id) != null) {
            System.out.println("Podaj nową cenę dla: " + findProductMatchingById(id));
            double newPrice = scanner.nextDouble();
            productManager.updateProductPriceById(id, newPrice);
        } else {
            System.out.println("Niepoprawne ID");
        }
    }
    public void adminChangeProductAvailableCount() {
        System.out.println("Podaj ID do zmiany dostępnej ilośći");
        String id = scanner.nextLine();

        if (findProductMatchingById(id) != null) {
            System.out.println("Podaj nową dostępną ilość dla: " + findProductMatchingById(id));
            int newAvailableCount = scanner.nextInt();
            productManager.updateProductAvailableCountById(id, newAvailableCount);
        } else {
            System.out.println("Niepoprawne ID");
        }
    }
    public void adminActivatePromotion() {
        System.out.println("Czy chcesz włączyć rabat 20% na cały koszyk? T/N");
        String option = scanner.nextLine();
        if (option.equalsIgnoreCase("t")) {
            cartManager.setActivatePromotion(true);
            promotion = true;
        } else {
         cartManager.setActivatePromotion(false);
         promotion = false;
        }
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}
