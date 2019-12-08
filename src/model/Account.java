/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jerome
 */
public class Account {
    private int id;
    private int departmentId;
    private int studentNo;
    private String username;
    private String password;
    private String fullName;
    private String previleges;
    
    private static Account instance = null;
    
    public Account() {}
    public Account(String username, String password, String fullName, String previleges) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.previleges = previleges;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
  
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrevileges() {
        return this.previleges;
    }

    public void setPrevileges(String previleges) {
        this.previleges = previleges;
    }
    
    
    public static Account getInstance() {
        if(instance == null) 
            instance = new Account();
        return instance;
    }
    
    
    public static boolean isUsernameAvailable(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT username FROM account WHERE username = ?";
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            rs = statement.executeQuery();
            if(rs.first()) {
                return false;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public static boolean isStudentNoAvailable(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT username FROM account WHERE student_no = ?";
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.first()) {
                return false;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public static int insert(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        String sql = "INSERT INTO account(department_id, student_no, fullname, username, password) " + 
                    "VALUES(?, ?, ?, ? ,?)";
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, account.getDepartmentId());
            statement.setInt(2, account.getStudentNo());
            statement.setString(3, account.getFullName());
            statement.setString(4, account.getUsername());
            statement.setString(5, account.getPassword());
            row = statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    
    
    public static Account authenticate(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM account WHERE username = ?";
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            rs = statement.executeQuery();
            if(rs.first()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                
                if(username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                    Account validatedAccount = new Account();
                    validatedAccount.setId(rs.getInt("id"));
                    validatedAccount.setStudentNo(rs.getInt("student_no"));
                    validatedAccount.setDepartmentId(rs.getInt("department_id"));
                    validatedAccount.setFullName(rs.getString("fullname"));
                    validatedAccount.setPrevileges(rs.getString("previleges"));
                    return validatedAccount;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
