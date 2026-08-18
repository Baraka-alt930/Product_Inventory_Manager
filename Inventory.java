/* Product class */
class Product {

    // Instance fields
    String name;
    String category;
    double price;
    int quantity;

    // Static field shared by all objects
    static int totalProductsCreated = 0;

    // Constructor with name and category only
    Product(String name, String category) {
        this.name = name;
        this.category = category;
        this.price = 0.0;
        this.quantity = 0;

        // Count every product created
        totalProductsCreated++;
    }

    // Constructor with all details
    Product(String name, String category, double price, int quantity) {

        // Call the first constructor
        this(name, category);

        this.price = price;
        this.quantity = quantity;
    }

    // Calculate total value of the product
    double getTotalValue() {
        return price * quantity;
    }

    // Apply discount to price
    void applyDiscount(double percent) {
        price = Math.max(0.0,
                price - (price * percent / 100));
    }

    // Check if product is in stock
    boolean isInStock() {
        return quantity > 0;
    }

    // Return formatted product information
    String getLabel() {
        return String.format(
            "%-14s %-22s %9.2f %4d %12.2f    %s",
            category.toUpperCase(),
            name,
            price,
            quantity,
            getTotalValue(),
            isInStock() ? "IN STOCK" : "OUT OF STOCK"
        );
    }

    // Return total products created
    static int getTotalProductsCreated() {
        return totalProductsCreated;
    }
}

/* Main class */
public class Inventory {

    public static void main(String[] args) {

        // Create required products
        Product laptop =
                new Product("Laptop", "Electronics", 1200.00, 5);

        Product tshirt =
                new Product("T-Shirt", "Clothing", 15.00, 20);

        Product book =
                new Product("Java Programming", "Books", 45.00, 0);

        Product rice =
                new Product("Rice", "Food", 32.50, 10);

        // Apply 10% discount to laptop
        laptop.applyDiscount(10);

        // Store products in array
        Product[] products = { laptop, tshirt, book, rice };

        // Variables for summary
        double totalInventoryValue = 0;

        // Assume first product is most valuable
        Product mostValuable = products[0];

        // Header
        System.out.println("============================================================");
        System.out.println("           CS 234 — PRODUCT INVENTORY REPORT");
        System.out.println("============================================================");

        // Display all products
        for (Product p : products) {

            System.out.println(p.getLabel());

            // Add total value
            totalInventoryValue += p.getTotalValue();

            // Find most valuable product
            if (p.getTotalValue() >
                    mostValuable.getTotalValue()) {

                mostValuable = p;
            }
        }

        // Footer and summary
        System.out.println("============================================================");

        System.out.println(
                "Total Products Created : "
                + Product.getTotalProductsCreated());

        System.out.printf(
                "Total Inventory Value  : %.2f%n",
                totalInventoryValue);

        System.out.printf(
                "Most Valuable Product  : %s (%.2f)%n",
                mostValuable.name,
                mostValuable.getTotalValue());

        System.out.println("============================================================");
    }
}