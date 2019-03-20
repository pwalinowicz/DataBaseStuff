package com.coffeecorner.dao;

import com.coffeecorner.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl extends DbAdapter {

    public void createTables() {
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

    public void insertProduct(String name, Double price) {
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

    public int countRows() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(*) FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Double queryProductPriceForProductName(String productName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT product_price FROM products WHERE product_name=?");
            preparedStatement.setString(1, productName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getDouble(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1.0;
    }

    public void updateProductPrice(String productName, Double newProductPrice){
        System.out.println("UpdateProductPrice " + productName +
                " - new price :$" + newProductPrice);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE products SET product_price=?" +
                            "WHERE product_name=?");

            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, newProductPrice);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String productName){
        System.out.println("DeleteProduct " + productName);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM products " +
                            "WHERE product_name=?");

            preparedStatement.setString(1, productName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Double> queryProductPrice(){

        List<Double> pricesList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT product_price FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                double price = resultSet.getDouble(1);
                pricesList.add(price);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pricesList;
    }

    public List<Product> queryProducts(){

        List<Product> productList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT product_id, product_name, product_price FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                Double productPrice = resultSet.getDouble(3);

                Product productTemp = new Product(productId, productName, productPrice);

                productList.add(productTemp);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
