package com.coffeecorner.dao;

import java.sql.*;

public class DbAdapter {

    /* Variables */
    String jdbUrl = "jdbc:postgresql://localhost:5432/coffee_corner"; // jdbc:postgresql://adres hosta/nazwa_bazy_danych
    String username = "postgres";
    String password = "postgre";

    /* Data Base Variables */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;


    /* Constructor */
    public DbAdapter() {
    }

    /**
     * Connect to Database
     */
    public void connect(){
        //Connecting to the database
        try {
            connection = DriverManager.getConnection(jdbUrl, username, password);
            System.out.println("Connected to the Database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect from Database
     */
    public void disconnect(){
        //Disconnecting from the database

        try{
            if(statement != null) statement.close();
            if(resultSet != null) resultSet.close();
            if (connection != null) connection.close();

            System.out.println("Disconnected from the Database");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
