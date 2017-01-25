package com.dasha.equipment.dao.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Даша on 16.12.2016.
 */
public class DBConnection {
    private static final DBConnection dbconnection=new DBConnection();
    private static final String URL="jdbc:mysql://localhost:3306/sport_equipment";
    private static final String USER="root";
    private static final String PASSWORD="5559708";
    private static Connection connection;

    private DBConnection()
    {
    }
    private static void init(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        init();
        return connection;
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
