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

/**
 *
 * @author jerome
 */
public class PartyList {
    private int id;
    private int electionId;
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElectionId() {
        return this.electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static ArrayList<PartyList> getAllPartyListUsingElectionId(int electionId) {
        ArrayList<PartyList> listOfPartylist = new ArrayList<>();
        ResultSet rs = DBConnection.query("SELECT * from partylist WHERE election_id = " + electionId);
        
        try {
            while(rs.next()){
                PartyList partylist = new PartyList();
                partylist.setId(rs.getInt("id"));
                partylist.setName(rs.getString("name"));
                partylist.setElectionId(rs.getInt("election_id"));
                listOfPartylist.add(partylist);
                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listOfPartylist;
    }
    
    public static boolean delete(int id) {
        String deleteSQL = "DELETE FROM partylist WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(deleteSQL);
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                status = true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    
    public static boolean isAvailable(String name) {
        String insertSQL = "SELECT * from partylist where name = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean status = true;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(insertSQL);
            statement.setString(1, name);
            rs = statement.executeQuery();
            if(rs.first()) {
                status = false;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public static int insert(String name, int electionId) {
        String insertSQL = "INSERT INTO partylist(name, election_id) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(insertSQL);
            statement.setString(1, name);
            statement.setInt(2, electionId);
            row = statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    
    
    public static String getStatus(int partylistId) {
        String sql = "SELECT status from partylist where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String status = "";
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, partylistId);
            rs = statement.executeQuery();
            if(rs.first()) {
                status = rs.getString("status");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    
    public static int setStatusToFull(int partylistId) {
        String sql = "UPDATE partylist SET status='FULL' where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, partylistId);
            row = statement.executeUpdate();
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
