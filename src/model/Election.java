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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerome
 */
public class Election {
    private int id;
    private int departmentId;
    private String name;
    private String status;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public static ArrayList<Election> getAllElection() {
        ArrayList<Election> electionList = new ArrayList<Election>();
        ResultSet rs = DBConnection.query("SELECT * from election");
        
        try {
            while(rs.next()){
                Election election = new Election();
                election.setId(rs.getInt("id"));
                election.setName(rs.getString("name"));
                election.setStatus(rs.getString("status"));
                
                electionList.add(election);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return electionList;
    }
    
    public static ArrayList<Election> getActiveElectionUsingDepartmentId(int departmentId) {
        ArrayList<Election> electionList = new ArrayList<Election>();   
        ResultSet rs = DBConnection.query("SELECT * FROM election WHERE department_id = " + departmentId + " AND status = 'ACTIVE' ");
        
        try {
            while(rs.next()){
                Election election = new Election();
                election.setId(rs.getInt("id"));
                election.setName(rs.getString("name"));
                election.setStatus(rs.getString("status"));
                election.setDepartmentId(rs.getInt("department_id"));
                electionList.add(election);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return electionList;
    }
    
    public static ArrayList<Election> getElectionUsingDepartmentId(int departmentId) {
        ArrayList<Election> electionList = new ArrayList<Election>();
        ResultSet rs = DBConnection.query("SELECT * FROM election WHERE department_id = " + departmentId);
        
        try {
            while(rs.next()){
                Election election = new Election();
                election.setId(rs.getInt("id"));
                election.setName(rs.getString("name"));
                election.setStatus(rs.getString("status"));
                election.setDepartmentId(rs.getInt("department_id"));
                electionList.add(election);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return electionList;
    }
    
    public static int insert(Election election) {
        String insertSQL = "INSERT INTO election(department_id, name,status) VALUES(?, ?, 'ACTIVE')";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(insertSQL);
            statement.setInt(1, election.getDepartmentId());
            statement.setString(2, election.getName());
            row = statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    
    public static boolean isAvailable(String name, int departmentId) {
        String insertSQL = "SELECT * from election where name = ? and department_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean status = true;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(insertSQL);
            statement.setString(1, name);
            statement.setInt(2, departmentId);
            rs = statement.executeQuery();
            if(rs.first()) {
                status = false;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean delete(int id) {
        String insertSQL = "DELETE FROM election WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(insertSQL);
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                status = true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    
    
    public String toString() {
        return this.getName();
    }
    
}
