package com.coffeecorner.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseImpl extends DbAdapter{

    public void createTables(){
        try {
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS products(" +
                    "product_id SERIAL PRIMARY KEY NOT NULL, " +
                    "product_name VARCHAR(100) NOT NULL, " +
                    "product_price REAL)";

            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String name, Double price){
        System.out.println("InsertProduct " + name + " $" + price);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO products " +
                            "(product_name, product_price) " +
                            "VALUES(?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
