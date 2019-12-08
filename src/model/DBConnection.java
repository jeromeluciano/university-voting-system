/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jerome
 */
public class DBConnection {
    public static String url = "jdbc:mysql://localhost/votingsystem?allowMultiQueries=true";
    public static String user = "root";
    public static String pass = "";
    public static Connection connection = null;
    
    
    public static Connection getInstance() {
        try{
            if(connection == null)
                connection = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public static ResultSet query(String query) {
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connect = DBConnection.getInstance();
            statement = connect.prepareStatement(query);
            rs = statement.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public static int updateQuery(String query) {
        Connection connect = null;
        PreparedStatement statement = null;
        int rs = 0;
        try {
            connect = DBConnection.getInstance();
            statement = connect.prepareStatement(query);
            rs = statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
}
