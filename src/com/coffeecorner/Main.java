package com.coffeecorner;

import com.coffeecorner.dao.DatabaseImpl;
import com.coffeecorner.domain.Product;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DatabaseImpl dbAdapter = new DatabaseImpl();

        // Connect
        dbAdapter.connect();

        // Create table
        dbAdapter.createTables();

        //Fill the table
        dbAdapter.insertProduct("Espresso", 0.40);
        dbAdapter.insertProduct("Americano", 0.65);
        dbAdapter.insertProduct("Latte", 2.00);
        dbAdapter.insertProduct("Cappuccino", 2.40);
        dbAdapter.insertProduct("Almond Cappuccino", 1.90);
        dbAdapter.insertProduct("Mint Latte", 4.90);
        dbAdapter.insertProduct("Coffee Frappe", 0.60);
        dbAdapter.insertProduct("Ice Coffee", 2.10);


        int numberOfRows = dbAdapter.countRows();
        System.out.println("Rows" + numberOfRows);

        // Get all products
        List<Product> allProducts = dbAdapter.queryProducts();

        allProducts.stream().forEach(
                e ->
                {
                    System.out.print("" + e.getProductId() + "\t\t");
                    System.out.print("" + e.getProductName() + "\t\t");
                    System.out.print("" + e.getProductPrice() + "\n");
                }
        );

        // Disconnect
        dbAdapter.disconnect();
    }
}
