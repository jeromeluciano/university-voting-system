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
public class Vote {
    private int id;
    private int accountId;
    private int electionId;
    private int isVoted;
    
    public Vote() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(int isVoted) {
        this.isVoted = isVoted;
    }

    public static boolean already(int accountId, int electionId) {
        String sql = "SELECT * FROM vote WHERE account_id = ? AND election_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, electionId);
            rs = statement.executeQuery();
            if(rs.first()) {
                if(rs.getInt("is_voted") == 1) {
                    return true;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static int markAccountAsVoted(int accountId, int electionId) {
        String sql = "INSERT INTO vote(election_id, account_id, is_voted) VALUES (?, ?, 1)";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, electionId);
            statement.setInt(2, accountId);
            
            row = statement.executeUpdate();
           
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    
}
