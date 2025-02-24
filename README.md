# E-Commerce-Project

## 📌 Opis projektu
Projekt zawiera aplikację konsolową imitującą działanie sklepu internetowego z elektroniką taką jak komputery, smartfony ale także z elektroniką ogólną.

---

## 📂 Struktura pakietu `model`
Pakiet zawiera następujące klasy i enumeracje:

### **1. Klasa bazowa:**
- **`Product`** – klasa bazowa dla wszystkich produktów, zawiera pola:
    - `id` (String) – unikalny identyfikator produktu
    - `name` (String) – nazwa produktu
    - `price` (double) – cena produktu
    - `availableCount` (int) – dostępna liczba sztuk

- **`Electronics`** – klasa dziedzicząca po `Product`, reprezentuje ogólną elektronikę.

### **2. Klasy produktów:**
- **`Smartphone`** – rozszerza `Product`, dodaje:
    - `color` (Color) – kolor urządzenia
    - `batteryCapacity` (BatteryCapacity) – pojemność baterii
    - `addonAccessory` (Accessories) – dodatkowe akcesoria

- **`Computer`** – rozszerza `Product`, zawiera:
    - `processorModel` (Processor) – model procesora
    - `ramType` (Ram) – typ pamięci RAM

### **3. Enumeracje (wyliczenia):**
- **`Accessories`** – dostępne akcesoria dla urządzeń (np. etui, ładowarka, słuchawki)
- **`BatteryCapacity`** – różne warianty pojemności baterii
- **`Color`** – dostępne kolory dla urządzeń
- **`Processor`** – modele procesorów dla komputerów
- **`Ram`** – dostępne konfiguracje pamięci RAM

### **4. Klasa Cart:**
- `productsInCart` (List<Product>) – lista produktów, które są umiejscowione w koszyku.

### **5. Klasa Order:**
- `orderId` (String) – numer ID zamówienia.
- `customerFirstName` (String) – Imię zamawiającego.
- `customerLastName` (String) – Nazwisko zamawiającego.
- `destinationCity` (String) – miasto docelowe.
- `postCode` (String) – kod pocztowy.
- `street` (String) – ulica docelowa.
- `homeNumber` (int) – numer domu.
- `telephoneNumber` (String) – numer telefonu.
- `cartContent` (Cart) – zmienna typu Cart, określająca 'koszyk' czyli zawartość przedmiotów przypisaną do zamówienia (Order).


---

## ⚙️ Funkcjonalność
- Wszystkie enumeracje posiadają metodę `config()`, umożliwiającą użytkownikowi wybór opcji za pomocą terminala.
- Klasy produktów implementują metodę `toString()`, aby ułatwić wyświetlanie ich opisu.
- `Product` zawiera metody `equals()` i `hashCode()` do porównywania obiektów.
- Klasa Cart zawiera metody i konstruktor obiektu typu Cart, który reprezentuje produkty zawarte w koszyku użytkownika
- Klasa OrderProcessor zawiera metody procesujące zamówienie takie jak generowanie faktury, zapis złożonego zamówienia do pliku.

---

## 📂 Struktura pakietu `service`
Pakiet zawiera klasy procesujące zamówienie oraz zarządzanie produktami w sklepie:


### **1. Klasa OrderProcessor:**
- `zonedDateTime` (ZonedDateTime) – zmienna typu ZonedDateTime, określająca czas złożenia zamówienia w zależności od strefy czasowej.

### **2. Klasa ProductManager:**
- `productsInShop` (List<Product>) – lista produktów obecnie istniejących w sklepie.


- Metody w klasach z pakietu 'service' umożliwiają obsługę modelu danych z pakietu 'model'

---

## 📂 Struktura pakietu `exception`
Pakiet zawiera klasy procesujące zamówienie oraz zarządzanie produktami w sklepie:

### **1. DuplicateIdException:**
- Klasa wyjątku wywoływana w momencie, gdy próbujemy dodać do sklepu produkt o już zajętym ID.


---


## 🚀Metody w klasie Cart:

-  `addProductToCart(Product product)` - metoda ogólna, umożliwia dodanie produktu do koszyka za pomocą przekazanego parametru typu Product, spośród już istniejących w sklepie. W zależności czy dodawanym produktem będzie obiekt instancji typu Computer lub Smartphone, uruchamiana jest konfiguracja produktu, za pomocą metody config(), określonej w klasach Enum w pakiecie 'model'.
-  `removeProductFromCart(String idToRemove)` - metoda ogólna, umożliwia usunięcie produktu z koszyka, jeśli id produktu przekazane w parametrze, będzie równy id produktu w koszyku.
-  `calculateTotalPrice()` - metoda typu double, zwracająca całkowitą cenę za produktu umieszczone w koszyku(Cart). Uwzględnia włączoną/wyłączoną promocję
-  `showCartContents()` - wyświetla całą zawartość koszyka.
-  `gettery`.

## 🚀Metody w klasie Order:

-  `prepareOrderToProcess()` - metoda wstępnie przetwarzająca zawartość koszyka. Wypisuje informację o przetwarzaniu, zawartość koszyka oraz całkowitą cenę za zamówienie.
-  `toString()` - nadpisana metoda do prawidłowego wyświetlania obiektów.
-  `gettery`.

## 🚀Metody w klasie OrderProcessor:

-  `generateInvoice(Order order)` - metoda zwracająca wartość typu String i przyjmująca parametr typu Order, która przedstawia wygenerowaną fakturę do zamówienia z podstawowymi danymi nabywcy, sprzedającego, przedmiotami, czasie utworzenia oraz cenie zamówienia.
-  `processOrder(Order order)` - metoda procesująca zamówienie działająca asynchronicznie. Dodaje złożone zamówienie do pliku invoices.txt, generuje fakturę.
-  `savePlacedOrderToFile(Order order)` - zapisuje złożone zamówienia do pliku ze ścieżką "src/files/orders.txt". Zawiera wszystkie niezbędne dane

## 🚀Metody w klasie ProductManager:

-  `readExistedProductsFromFile(List<Product> products, String fileName)` - metoda odczytująca istniejące i wcześniej dodane przez użytkownika produkty z pliku w sesji programu.
-  `readCartStatusFromFile(List<Product> products, String fileName)` - metoda do odczytu z pliku, aktualnej zawartości koszyka, służy do zapisywania zawartości koszyka po zatrzymaniu programu
-  `savePromotionToFile(boolean promo, int promoPercent, int promoCount, String promoType)` - metoda do zapisu konfiguracji promocji do pliku, aby mogła działać po zamknieciu i ponownym uruchomieniu programu.
-  `saveProductsStatusToFileByList(List<Product> products, String fileName)` - zapis aktualnego statusu produktów do pliku w celu późniejszego odczytania. Wywoływana w momencie np. składania zamówienia gdy zmniejszana jest liczba dostępnych sztuk produktu, aby tę zmianę uwzględnić.
-  `detailedCartStatusSave(List<Product> products, String fileName)` - metoda działająca z metodą saveProductStatusToFile(), jeśli fileName wskazuje na cart.csv, zapisuje aktualny stan koszyka do pliku.
-  `addProductToShop()` - metoda przystosowana do dodania produktu do sklepu z Admin Menu. Rzuca utworzony wyjątek 'DuplicateIdException' jeśli próbujemy dodać produkt o ID, które już istnieje.
-  `configurateProduct()` - metoda pomocnicza typu Product, konfigurująca i pobierająca informację od użytkownika o dodawanym produkcie. Wywoływana w metodzie addProductToShop().
-  `isIdAlreadyExist(Product product)` - metoda pomocnicza typu boolean, zwracająca odpowiednią wartość true/false w zależoności czy product, przekazany w parametrze sygnatury metody znajduję się na liście 'productsInShop', po jego ID. Wywoływana w metodzie addProductToShop().
-  `removeProductFromShopById(String id)` - metoda usuwająca produkt z listy istniejących produktów w sklepie 'productsInShop' po jego ID.
-  `showProdutsInShop()` - iteruje po wszystkich produktach istniejących w sklepie, określa liczbą dostępnych sztuk.
-  `findedProductById(String id)` - metoda pomocnicza zwracająca wartość typu Optional. Jeśli 'product' o podanym 'id' w parametrze sygnatury istnieję, zwraca taką wartość typu Optional.
-  `updateProductNameById(String id, String newName)` - aktualizuję nazwę produktu w AdminMenu, za pomocą ID istniejącego produktu i przekazanej nowej nazwy w parametrze sygnatury metody.
-  `updateProductPriceById(String id, double newPrice)` - aktualizuję cenę produktu w AdminMenu, za pomocą ID istniejącego produktu i przekazanej nowej ceny w parametrze sygnatury metody.
-  `updateProductAvailableCountById(String id, int newAvailableCount)` - aktualizuję liczbę dostępnych sztuk produktu w AdminMenu, za pomocą ID istniejącego produktu i przekazanej nowej liczby dostępnych sztuk w parametrze sygnatury metody.
-  `getter`

## 📂 Struktura pakietu `ui`
Pakiet zawiera następujące klasy:

### **1. Klasa CommandLineInterface:**
- CommandLineInterface to interfejs użytkownika do zarządzania sklepem internetowym poprzez konsolę. Umożliwia dodawanie i usuwanie produktów z koszyka, składanie zamówień, a także oferuje opcje administracyjne, takie jak edycja produktów i aktywowanie promocji.

- `productManager` (ProductManager) – instancja odwołująca się do klasy ProductManager.
- `cartManager` (Cart) – instancja odwołująca się do klasy Cart.
- `orderProcessor` (OrderProcessor) – instancja odwołująca się do klasy OrderProcessor.
- `scanner` (Scanner) – referencja na obiekt typu Scanner, służący do pobierania informacji od użytkownika i interakcji.
- `promotion` (boolean) - zmienna określająca czy promocja jest włączona/wyłączona.
- `promotionPercentValue` (int) - wartość int określająca wartość rabatu procentowego na produkty.
- `promotionProductCountInCart` (int) - wartość int określająca od jakiej liczby produktów w koszyku, promocja jest uruchamiana (typ promocji = 2).
- `promotionType` (String) - określana cyfrą zapisaną poprzez String, jakiego typu jest promocja (1 albo 2), w zależności czy chcemy włączyć rabat x % na cały koszyk lub w sytuacji gdy chcemy włączyć x % na x szt produktów w koszyku.
- `totalPriceForOrder` (static double) - zmienna statyczna przechowywująca zsumowaną wartość do zapłaty za koszyk z uwzględnieniem promocji (jeśli jest włączona).

### **2. Klasa ShopApplication(main):**
- Klasa uruchamiająca aplikację z metodą main, pętlą w której program działa oraz tekstami opisującymi opcje w Menu.


## 🚀Metody w klasie CommandLineInterface:

-  `readExistedProductsFromCsvFile` - metoda odczytująca aktualny stan produktów z pliku w interfejsie użytkownika.
-  `readCartStatusFromCsvFile` - metoda odczytująca stan koszyka, jeśli jakaś zawartość istniała we wcześniejszej sesji programu.
-  `showShopContent()` - metoda odwołująca się do metody z klasy ProductManager, wyświetlająca wszystkie produkty w sklepie jeśli użytkownik o to poprosi.
-  `addItemToCart()` - metoda dodająca produkt do koszyka, i pobierająca wszystkie niezbędne informajcję od użytkownika aby ten produkt określić i zmniejszyć ilość dostępnych jego sztuk, jeśli zostanie dodany.
-  `removeItemFromCart()` - metoda usuwająca przedmiot z koszyka po pobranym od użytkowinka ID.
-  `clearCartByUserRequest` - metoda czyszcząca koszyk gdy zdecyduje użytkownik. Uwzględnia zwiekszenie dostepnej ilosci produktow po usunieciu gdy ID w koszyku przypadkowo sie zdubluje.
-  `clearCart()` - metoda czyszcząca całą zawartość koszyka.
-  `showItemsInCart()` - metoda iterująca po zawartości koszyka.
-  `orderPaymentInfo()` - metoda informująca o bieżącej kwocie do zapłaty za przedmioty zawarte w koszyku.
-  `createOrder()` - zwraca nowy obiekt typu Order, w zależności od pobranych od użytkownika danych o zamówieniu.
-  `sendOrder()` - wysyła zamówienie do przetworzenia, wyświetla cenę, zapisuje do pliku i kończy tekstem o powodzeniu operacji.
-  `findProductMatchingById(String id)` - metoda pomocnicza typu Product, zwracająca Product, jeśli id podane w parametrze, będzie zgadzać się z, którymkolwiek id, produktów już istniejących

### ADMIN MENU ###

-  `adminAddProductToShop()` - tworzenie całkiem nowego produktu i dodawanie go do sklepu.
-  `adminRemoveProductFromShop()` - usuwa istniejący produkt ze sklepu po jego ID.
-  `adminChangeProductName()` - zmienia nazwę istniejącego produktu po jego ID.
-  `adminChangeProductPrice()` - zmienia cenę istniejącego produktu po jego ID.
-  `adminChangeProductAvailableCount()` - zmienia liczbę dostępnych sztuk istniejącego produktu po jego ID.
-  `adminActivatePromotion()` - aktywuję wybraną promocję z dwóch dostępnych: -rabat x % na cały koszyk- , -rabat x % od x podanej ilości produktów w koszyku-
-  `readPromoStatusFromFile` - odczytuje zapamietany status promocji z pliku i zawiera w programie.
-  `gettery i settery`.

## 🚀Metody w klasie ShopApplication:

-  `main` - główna metoda uruchamiająca aplikację, z dwiema pętlami while(po jednej na każde menu), instrukcją switch pozwalającą na wybranie opcji/polecenia.
-  `welcomeInTheShopText()` - statyczna metoda wyświetlająca tekst powitalny oraz opcje w menu dla użytkownika
-  `adminMenu()` - statyczna metoda wyświetlająca tekst powitalny oraz opcje w AdminMenu.


## 🚀Katalog files:

-  `cart.csv` - aktualny i zapamiętany status koszyka w pliku
-  `existedProducts.csv` - istniejące produkty w sklepie w pliku.
-  `invoices.txt` - faktury do złożonych zamówień też w pliku.
-  `promotion.csv` - plik ze statusem i parametrami promocji.


## 🚀HASŁO:

## HASŁO DO ADMIN MENU:
			'admin' 

## 👨‍💻 Autor
Projekt stworzony dla systemu zarządzania sklepem z elektroniką. Możliwe rozszerzenie o kolejne modele.

JAKUB KRAMKOWSKI
