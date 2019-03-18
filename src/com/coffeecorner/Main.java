package com.coffeecorner;

import com.coffeecorner.dao.DbAdapter;

public class Main {

    public static void main(String[] args) {

        DbAdapter dbAdapter = new DbAdapter();

        // Connect
        dbAdapter.connect();

        // Disconnect
        dbAdapter.disconnect();
    }
}
