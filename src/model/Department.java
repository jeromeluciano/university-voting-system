/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jerome
 */
public class Department {
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public static ArrayList<Department> getAll() {
        ArrayList<Department> departmentList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * from department";
        try {
            connection = DBConnection.getInstance();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            
            while(rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departmentList.add(department);
            }
            
        }catch(SQLException e ) {
            e.printStackTrace();
        }
        return departmentList;
    }
    
    public static String getName(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * from department WHERE id = "+ id;
        try {
            connection = DBConnection.getInstance();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            
            if(rs.first()) {
                return rs.getString("name");
            }
            
        }catch(SQLException e ) {
            e.printStackTrace();
        }
        return "";
    }
}
