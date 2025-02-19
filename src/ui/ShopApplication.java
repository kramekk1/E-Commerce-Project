package ui;

import service.DuplicateIdException;

import java.util.Scanner;

public class ShopApplication {
    private static final Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) throws DuplicateIdException {

        CommandLineInterface commandLineInterface = new CommandLineInterface();
        commandLineInterface.readExistedProductsFromCsvFile();
        commandLineInterface.readCartStatusFromCsvFile();
        commandLineInterface.readPromoStatusFromFile();

        boolean appRunning = true;

        while (appRunning) {
            welcomeInTheShopText();
            if (commandLineInterface.isPromotion()) {
                if (commandLineInterface.getPromotionType().equals("1")) {
                    System.out.println();
                    System.out.println("$$ AKTYWNY RABAT " + commandLineInterface.getPromotionPercentValue() + " % $$");
                } if (commandLineInterface.getPromotionType().equals("2")) {
                    System.out.println();
                    System.out.println("$$ AKTYWNY RABAT " + commandLineInterface.getPromotionPercentValue() + " % OD " + commandLineInterface.getPromotionProductCountInCart() + " PRODUKTÓW W KOSZYKU $$");
                }
            }
            String option = userInput.nextLine();

            switch (option) {
                case "1" -> commandLineInterface.showShopContent();
                case "2" -> commandLineInterface.addItemToCart();
                case "3" -> commandLineInterface.removeItemFromCart();
                case "4" -> commandLineInterface.clearCartByUserRequest();
                case "5" -> commandLineInterface.showItemsInCart();
                case "6" -> commandLineInterface.orderPaymentInfo();
                case "7" -> commandLineInterface.sendOrder();
                case "9" -> appRunning = false;
                case "8" -> {
                    boolean adminMenu = false;
                    System.out.println("Podaj hasło: ");
                    String password = userInput.nextLine();
                    if (password.equals("admin")) {
                        adminMenu = true;
                    } else {
                        System.out.println("Nieprawidłowe hasło");
                    }

                    while (adminMenu) {
                        adminMenu();
                        String optionInAdminMenu = userInput.nextLine();
                        switch (optionInAdminMenu) {
                            case "1" -> commandLineInterface.adminAddProductToShop();
                            case "2" -> commandLineInterface.adminRemoveProductFromShop();
                            case "3" -> commandLineInterface.adminChangeProductName();
                            case "4" -> commandLineInterface.adminChangeProductPrice();
                            case "5" -> commandLineInterface.adminChangeProductAvailableCount();
                            case "6" -> commandLineInterface.adminActivatePromotion();
                            case "7" -> adminMenu = false;
                        }
                    }
                }
            }
        }
    }
    public static void welcomeInTheShopText() {
        System.out.println("#################################################");
        System.out.println("############    WITAJ W SKLEPIE     #############");
        System.out.println("#################################################");
        System.out.println();
        System.out.println(">>>> WYBIERZ [ 1 ] ABY WYŚWIETLIĆ PRODUKTY W SKLEPIE");
        System.out.println(">>>> WYBIERZ [ 2 ] ABY DODAĆ PRODUKT DO KOSZYKA");
        System.out.println(">>>> WYBIERZ [ 3 ] ABY USUNĄĆ PRODUKT Z KOSZYKA");
        System.out.println(">>>> WYBIERZ [ 4 ] ABY WYCZYŚCIĆ CAŁA ZAWARTOŚĆ KOSZYKA");
        System.out.println(">>>> WYBIERZ [ 5 ] ABY WYŚWIETLIĆ CAŁA ZAWARTOŚĆ KOSZYKA");
        System.out.println(">>>> WYBIERZ [ 6 ] ABY WYŚWIETLIĆ BIĘŻĄCĄ KWOTE DO ZAPŁATY ZA KOSZYK");
        System.out.println(">>>> WYBIERZ [ 7 ] ABY ZŁOŻYĆ ZAMÓWIENIE");
        System.out.println();
        System.out.println(">>>> WYBIERZ [ 8 ] ABY WEJŚĆ W OPCJE ADMINA");
        System.out.println(">>>> WYBIERZ [ 9 ] ABY WYJŚĆ");
    }

    public static void adminMenu() {
        System.out.println("#################################################");
        System.out.println("###############     ADMIN MENU     ##############");
        System.out.println("#################################################");
        System.out.println();
        System.out.println(">>>> WYBIERZ [ 1 ] ABY DODAĆ PRODUKT DO SKLEPU");
        System.out.println(">>>> WYBIERZ [ 2 ] ABY USUNĄĆ PRODUKT PO ID");
        System.out.println(">>>> WYBIERZ [ 3 ] ZMIANA NAZWY ISTNIEJACEGO PRODUKTU");
        System.out.println(">>>> WYBIERZ [ 4 ] ZMIANA CENY ISTNIEJACEGO PRODUKTU");
        System.out.println(">>>> WYBIERZ [ 5 ] ZMIANA DOSTEPNEJ ILOSCI PRODUKTU");
        System.out.println(">>>> WYBIERZ [ 6 ] ABY AKTYWOWAĆ PROMOCJĘ");
        System.out.println();
        System.out.println(">>>> WYBIERZ [ 7 ] ABY WRÓCIĆ");
    }
}
