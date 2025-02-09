package ui;

import model.*;
import service.DuplicateIdException;

import java.util.Scanner;

public class ShopApplication {
    private static final Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) throws DuplicateIdException {

        CommandLineInterface commandLineInterface = new CommandLineInterface();

        //////////////////////ALREADY EXISTED PRODUCTS////////////////////////////////

        Computer computer1 = new Computer("1", "Komputer stacjonarny", 2000, 50, Processor.NONE, Ram.NONE);
        commandLineInterface.getProductManager().adminAdd(computer1);
        Computer computer2 = new Computer("2", "Komputer stacjonarny", 4000, 50, Processor.NONE, Ram.NONE);
        commandLineInterface.getProductManager().adminAdd(computer2);
        Computer computer3 = new Computer("3", "Laptop Acer", 4500, 40, Processor.NONE, Ram.NONE);
        commandLineInterface.getProductManager().adminAdd(computer3);
        Computer computer4 = new Computer("4", "Laptop Lenovo", 6000, 40, Processor.NONE, Ram.NONE);
        commandLineInterface.getProductManager().adminAdd(computer4);
        Smartphone smartphone1 = new Smartphone("5", "iPhone 15", Color.NONE, BatteryCapacity.NONE, Accessories.NONE, 2000, 50);
        commandLineInterface.getProductManager().adminAdd(smartphone1);
        Smartphone smartphone2 = new Smartphone("6", "Samsung", Color.NONE, BatteryCapacity.NONE, Accessories.NONE, 3300, 40);
        commandLineInterface.getProductManager().adminAdd(smartphone2);
        Electronics electronics1 = new Electronics("7", "Blender Philips", 400, 40);
        commandLineInterface.getProductManager().adminAdd(electronics1);

        /////////////////////////////////////////////////////////////////////////////
        boolean appRunning = true;

        while (appRunning) {
            welcomeInTheShopText();
            if (commandLineInterface.isPromotion()) {
                System.out.println();
                System.out.println("AKTYWNY RABAT 20%");
            }
            String option = userInput.nextLine();

            switch (option) {
                case "1" -> commandLineInterface.showShopContent();
                case "2" -> commandLineInterface.addItemToCart();
                case "3" -> commandLineInterface.removeItemFromCart();
                case "4" -> commandLineInterface.clearCart();
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
