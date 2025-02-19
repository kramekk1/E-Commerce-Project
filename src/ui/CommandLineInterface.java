package ui;

import model.Product;
import service.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CommandLineInterface {
    private ProductManager productManager = new ProductManager();
    private Cart cartManager = new Cart();
    private OrderProcessor orderProcessor = new OrderProcessor();
    private final Scanner scanner = new Scanner(System.in);
    private boolean promotion;
    private int promotionPercentValue;
    private int promotionProductCountInCart;
    private String promotionType;
    private static double totalPriceForOrder;

    public CommandLineInterface() {
    }

    public void readExistedProductsFromCsvFile() {
        productManager.readExistedProductsFromFile(productManager.getProductsInShop(), "existedProducts.csv");
    }

    public void readCartStatusFromCsvFile() {
        productManager.readCartStatusFromFile(cartManager.getProductsInCart(), "cart.csv");
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
            productManager.saveProductsStatusToFileByList(productManager.getProductsInShop(), "existedProducts.csv");
            productManager.saveProductsStatusToFileByList(cartManager.getProductsInCart(), "cart.csv");
         } else {
            System.out.println("Podane ID nieistnieje");
         }
    }

    public void removeItemFromCart() {
        System.out.println("Podaj ID produktu do usunięcia");
        String idToRemove = scanner.nextLine();

        Product product = findProductMatchingById(idToRemove);
        long productsWithTheSameIdInCartCount = cartManager.getProductsInCart().stream()
                .filter(prod -> prod.getId().equals(product.getId()))
                .count();

        if (product != null) {
            cartManager.removeProductFromCart(idToRemove);
            product.setAvailableCount((int) (product.getAvailableCount() + productsWithTheSameIdInCartCount));
            productManager.saveProductsStatusToFileByList(productManager.getProductsInShop(), "existedProducts.csv");
            productManager.saveProductsStatusToFileByList(cartManager.getProductsInCart(), "cart.csv");
        } else {
            System.out.println("Podane ID nieistnieje");
        }
    }
    public void clearCartByUserRequest() {
        Map<String, Integer> idAndCountMap = new HashMap<>();
        for (Product prod : cartManager.getProductsInCart()) {
            idAndCountMap.merge(prod.getId(), 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry : idAndCountMap.entrySet()) {
            productManager.getProductsInShop().stream()
                    .filter(product -> entry.getKey().equals(product.getId()))
                    .forEach(product -> product.setAvailableCount(product.getAvailableCount() + entry.getValue()));
        }
        productManager.saveProductsStatusToFileByList(productManager.getProductsInShop(), "existedProducts.csv");
        clearCart();
    }

    public void clearCart() {
        cartManager.getProductsInCart().clear();
        productManager.saveProductsStatusToFileByList(cartManager.getProductsInCart(), "cart.csv");
    }

    public void showItemsInCart() {
        cartManager.getProductsInCart().forEach(System.out::println);
    }

    public void orderPaymentInfo() {
        double promotionCalc = cartManager.calculateTotalPrice() * (1 - (promotionPercentValue * 0.01));
        System.out.println("Aktualna kwota do zapłaty");

        if (promotion) {
            if (promotionType.equals("1")) {
                System.out.println(promotionCalc);
                totalPriceForOrder = promotionCalc;
            }
            if (promotionType.equals("2")) {
                if (cartManager.getProductsInCart().size() >= promotionProductCountInCart) {
                    System.out.println(promotionCalc);
                    totalPriceForOrder = promotionCalc;
                } else {
                    System.out.println(cartManager.calculateTotalPrice());
                    totalPriceForOrder = cartManager.calculateTotalPrice();
                }
            }
        } else {
            System.out.println(cartManager.calculateTotalPrice());
            totalPriceForOrder = cartManager.calculateTotalPrice();
        }
    }

    public Order createOrder() {
        Random orderIdGenerator = new Random();
        String generatedId = String.valueOf(orderIdGenerator.nextInt(1000, 9999));

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
        int homeNumber;
        while (!scanner.hasNextInt()) {
            System.out.println("Numer domu powinien być cyfrą");
            scanner.next();
        }
        homeNumber = scanner.nextInt();
        System.out.println("Podaj numer telefonu");
        scanner.nextLine();
        String telNumber = scanner.nextLine();

        System.out.println("Potwierdzasz składanie zamówienie z podaniem prawidłowych danych czy chcesz anulować?");
        System.out.println("WYBIERZ [ 1 ] ABY POTWIERDZIĆ");
        System.out.println("WYBIERZ [ 2 ] ABY ANULOWAĆ");
        System.out.println("WYBIERZ [ 3 ] ABY ZACZĄĆ OD POCZĄTKU");
        String option = scanner.nextLine();
        return switch (option){
            case "1" -> new Order(generatedId, name, lastName, city, postCode, street, homeNumber, telNumber, cartManager);
            case "2" -> null;
            default -> createOrder();
        };
    }

    public void sendOrder() {
        Order createdOrder = createOrder();
        createdOrder.prepareOrderToProcess();
        orderProcessor.processOrder(createdOrder)
                        .thenRun(this::clearCart);
        System.out.println("Zamówienie o ID: " + createdOrder.getOrderId() + " zostało złożone");
        productManager.saveProductsStatusToFileByList(productManager.getProductsInShop(), "existedProducts.csv");
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
            double newPrice;
            while (!scanner.hasNextDouble()) {
                System.out.println("To nie jest cyfra, jeszcze raz");
                scanner.next();
            }
            newPrice = scanner.nextDouble();
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
            int newAvailableCount;
            while (!scanner.hasNextInt()) {
                System.out.println("To nie jest cyfra, jeszcze raz");
                scanner.next();
            }
            newAvailableCount = scanner.nextInt();
            productManager.updateProductAvailableCountById(id, newAvailableCount);
        } else {
            System.out.println("Niepoprawne ID");
        }
    }

    public void adminActivatePromotion() {
        System.out.println("Jaką promocję chcesz włączyć?");
        System.out.println(">>>> WYBIERZ [ 1 ] RABAT [X] % NA CAŁY KOSZYK");
        System.out.println(">>>> WYBIERZ [ 2 ] RABAT [X] % NA KOSZYK AKTYWOWANY OD [Y] SZT. PRODUKTÓW W KOSZYKU");
        System.out.println(">>>> WYBIERZ [ 3 ] ABY WYŁĄCZYĆ CAŁKOWICIE");
        String option = scanner.nextLine();

        switch (option) {
            case "1" -> {
                System.out.println("Podaj ile % chcesz włączyć");
                while (!scanner.hasNextInt()) {
                    System.out.println("To nie jest cyfra, jeszcze raz");
                    scanner.next();
                }
                promotionPercentValue = scanner.nextInt();
                promotion = true;
                promotionType = "1";
                productManager.savePromotionToFile(promotion, promotionPercentValue, 0, promotionType);
            }
            case "2" -> {
                System.out.println("Podaj ile % chcesz włączyć");
                while (!scanner.hasNextInt()) {
                    System.out.println("To nie jest cyfra, jeszcze raz");
                    scanner.next();
                }
                promotionPercentValue = scanner.nextInt();

                System.out.println("Od ilu produktów w koszyku?");
                while (!scanner.hasNextInt()) {
                    System.out.println("To nie jest cyfra, jeszcze raz");
                    scanner.next();
                }
                promotionProductCountInCart = scanner.nextInt();
                promotion = true;
                promotionType = "2";
                productManager.savePromotionToFile(promotion, promotionPercentValue, promotionProductCountInCart, promotionType);
            }
            case "3" -> {
                promotion = false;
                productManager.savePromotionToFile(promotion, 0, 0, "0");
            }
            default -> {
                System.out.println("Podano niepoprawny znak");
                adminActivatePromotion();
            }
        }
    }
    public void readPromoStatusFromFile() {
        String promoFilePath = "src/files/promotion.csv";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(promoFilePath))){
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                setPromotion(Boolean.parseBoolean(values[0]));
                setPromotionPercentValue(Integer.parseInt(values[1]));
                setPromotionProductCountInCart(Integer.parseInt(values[2]));
                setPromotionType(values[3]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////////////////////////////////////////////////////////
    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public int getPromotionPercentValue() {
        return promotionPercentValue;
    }

    public void setPromotionPercentValue(int promotionPercentValue) {
        this.promotionPercentValue = promotionPercentValue;
    }

    public int getPromotionProductCountInCart() {
        return promotionProductCountInCart;
    }

    public void setPromotionProductCountInCart(int promotionProductCountInCart) {
        this.promotionProductCountInCart = promotionProductCountInCart;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public static double getTotalPriceForOrder() {
        return totalPriceForOrder;
    }
}
