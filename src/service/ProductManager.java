package service;

import model.*;
import ui.CommandLineInterface;

import java.io.*;
import java.util.*;

public class ProductManager {
    private List<Product> productsInShop;

    public ProductManager() {
        productsInShop = new ArrayList<>();
    }

    public void readExistedProductsFromFile(List<Product> products, String fileName) {
        String filePath = "src/files/" + fileName;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                double priceFromStringToDouble = Double.parseDouble(values[3]);
                int availableCountFromStringToInt = Integer.parseInt(values[4]);

                if (Objects.equals(values[0], "Computer")) {
                    products.add(new Computer(values[1], values[2], priceFromStringToDouble, availableCountFromStringToInt, Processor.NONE, Ram.NONE));
                } else if (Objects.equals(values[0], "Smartphone")) {
                    products.add(new Smartphone(values[1], values[2], Color.NONE, BatteryCapacity.NONE, Accessories.NONE, priceFromStringToDouble, availableCountFromStringToInt));
                } else if (Objects.equals(values[0], "Electronics")) {
                    products.add(new Electronics(values[1], values[2], priceFromStringToDouble, availableCountFromStringToInt));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readCartStatusFromFile(List<Product> products, String fileName) {
        String filePath = "src/files/" + fileName;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                double priceFromStringToDouble = Double.parseDouble(values[3]);
                int availableCountFromStringToInt = Integer.parseInt(values[4]);

                if (Objects.equals(values[0], "Computer")) {
                    products.add(new Computer(values[1], values[2], priceFromStringToDouble, availableCountFromStringToInt, Processor.valueOf(values[5]), Ram.valueOf(values[6])));
                } else if (Objects.equals(values[0], "Smartphone")) {
                    products.add(new Smartphone(values[1], values[2], Color.valueOf(values[7]), BatteryCapacity.valueOf(values[8]), Accessories.valueOf(values[9]), priceFromStringToDouble, availableCountFromStringToInt));
                } else if (Objects.equals(values[0], "Electronics")) {
                    products.add(new Electronics(values[1], values[2], priceFromStringToDouble, availableCountFromStringToInt));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePromotionToFile(boolean promo, int promoPercent, int promoCount, String promoType) {
        String promoFilePath = "src/files/promotion.csv";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(promoFilePath))) {
            bufferedWriter.write("promotion,promotionPercentValue,promotionProductCountInCart,promotionType");
            bufferedWriter.newLine();
            bufferedWriter.write(String.join(",",
                    String.valueOf(promo),
                    String.valueOf(promoPercent),
                    String.valueOf(promoCount),
                    promoType));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveProductsStatusToFileByList(List<Product> products, String fileName) {
        if (fileName.equals("existedProducts.csv")) {
            String filePath = "src/files/" + fileName;

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))
            ) {
                bufferedWriter.write("type,id,name,price,availableCount");
                bufferedWriter.newLine();

                for (Product prod : products) {
                    if (prod instanceof Computer) {
                        bufferedWriter.write(String.join(",",
                                "Computer",
                                prod.getId(),
                                prod.getName(),
                                String.valueOf(prod.getPrice()),
                                String.valueOf(prod.getAvailableCount())));
                        bufferedWriter.newLine();
                    }
                    if (prod instanceof Smartphone) {
                        bufferedWriter.write(String.join(",",
                                "Smartphone",
                                prod.getId(),
                                prod.getName(),
                                String.valueOf(prod.getPrice()),
                                String.valueOf(prod.getAvailableCount())));
                        bufferedWriter.newLine();
                    }
                    if (prod instanceof Electronics) {
                        bufferedWriter.write(String.join(",",
                                "Electronics",
                                prod.getId(),
                                prod.getName(),
                                String.valueOf(prod.getPrice()),
                                String.valueOf(prod.getAvailableCount())));
                        bufferedWriter.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (fileName.equals("cart.csv")) {
            detailedCartStatusSave(products, fileName);
        }
    }

    public void detailedCartStatusSave(List<Product> products, String fileName) {
        String filePath = "src/files/" + fileName;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))
        ) {
            bufferedWriter.write("type,id,name,price,availableCount,processor,ramType,color,batteryCapacity,accessories");
            bufferedWriter.newLine();

            for (Product prod : products) {
                if (prod instanceof Computer) {
                    bufferedWriter.write(String.join(",",
                            "Computer",
                            prod.getId(),
                            prod.getName(),
                            String.valueOf(prod.getPrice()),
                            String.valueOf(prod.getAvailableCount()),
                            String.valueOf(((Computer) prod).getProcessorModel()),
                            String.valueOf(((Computer) prod).getRamType()),
                            "",
                            "",
                            ""));
                    bufferedWriter.newLine();
                }
                if (prod instanceof Smartphone) {
                    bufferedWriter.write(String.join(",",
                            "Smartphone",
                            prod.getId(),
                            prod.getName(),
                            String.valueOf(prod.getPrice()),
                            String.valueOf(prod.getAvailableCount()),
                            "",
                            "",
                            String.valueOf(((Smartphone) prod).getColor()),
                            String.valueOf(((Smartphone) prod).getBatteryCapacity()),
                            String.valueOf(((Smartphone) prod).getAddonAccessory())));
                    bufferedWriter.newLine();
                }
                if (prod instanceof Electronics) {
                    bufferedWriter.write(String.join(",",
                            "Electronics",
                            prod.getId(),
                            prod.getName(),
                            String.valueOf(prod.getPrice()),
                            String.valueOf(prod.getAvailableCount())));
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProductToShop() throws DuplicateIdException {
        Product product = configurateProduct();
        if (isIdAlreadyExist(product)) {
            throw new DuplicateIdException("ID produktu, który próbujesz dodać już istnieje!");
        } else {
            productsInShop.add(product);
            System.out.println("Pomyślnie dodano produkt ID: " + product.getId() + " // " + product.getName() + " // ");
            saveProductsStatusToFileByList(productsInShop, "existedProducts.csv");
        }
    }

    public Product configurateProduct() {
        Scanner userInput = new Scanner(System.in);
        Random randomIdGenerator = new Random();

        int idIntValue = randomIdGenerator.nextInt(1000, 9999);
        String id = String.valueOf(idIntValue);
        System.out.println("Podaj nazwe: ");
        String name = userInput.nextLine();

        System.out.println("Podaj cenę: ");
        double price;
        while (!userInput.hasNextDouble()) {
            System.out.println("To nie jest cyfra, jeszcze raz");
            userInput.next();
        }
        price = userInput.nextDouble();

        System.out.println("Podaj liczbę sztuk: ");
        int availableCount;
        while (!userInput.hasNextInt()) {
            System.out.println("To nie jest cyfra, jeszcze raz");
            userInput.next();
        }
        availableCount = userInput.nextInt();

        System.out.println("Komputer, Smartfon czy Elektronika?");
        userInput.nextLine();
        String choice = userInput.nextLine().toLowerCase();

        return switch (choice) {
            case "komputer" -> new Computer(id, name, price, availableCount, Processor.NONE, Ram.NONE);
            case "smartfon" ->
                    new Smartphone(id, name, Color.NONE, BatteryCapacity.NONE, Accessories.NONE, price, availableCount);
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
        saveProductsStatusToFileByList(productsInShop, "existedProducts.csv");
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
        saveProductsStatusToFileByList(productsInShop, "existedProducts.csv");
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
        saveProductsStatusToFileByList(productsInShop, "existedProducts.csv");
    }

    public List<Product> getProductsInShop() {
        return productsInShop;
    }

    public void setProductsInShop(List<Product> productsInShop) {
        this.productsInShop = productsInShop;
    }
}
