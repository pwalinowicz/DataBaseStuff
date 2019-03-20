package com.coffeecorner;

import com.coffeecorner.dao.DatabaseImpl;

public class Main {

    public static void main(String[] args) {

        DatabaseImpl dbAdapter = new DatabaseImpl();

        // Connect
        dbAdapter.connect();

        // Create table
        dbAdapter.createTables();

        dbAdapter.insertProduct("Espresso", 0.40);
        dbAdapter.insertProduct("Americano", 0.65);
        dbAdapter.insertProduct("Latte", 2.00);
        dbAdapter.insertProduct("Cappuccino", 2.40);
        dbAdapter.insertProduct("Almond Cappuccino", 1.90);
        dbAdapter.insertProduct("Mint Latte", 4.90);
        dbAdapter.insertProduct("Coffee Frappe", 0.60);
        dbAdapter.insertProduct("Ice Coffee", 2.10);

        // Disconnect
        dbAdapter.disconnect();
    }
}
