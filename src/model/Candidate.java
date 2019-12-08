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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jerome
 */
public class Candidate {
    private int id;
    private int partylistId;
    private int positionId;
    private int electionId;
    private int voteCount;
    
    private String name;
    private String positionName;
    private String partylistName;

    public String getPartylistName() {
        return partylistName;
    }

    public void setPartylistName(String partylistName) {
        this.partylistName = partylistName;
    }
    
    
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getElectionId() {
        return this.electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getPositionId() {
        return this.positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartylistId() {
        return this.partylistId;
    }

    public void setPartylistId(int partylistId) {
        this.partylistId = partylistId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    public static ArrayList<Candidate> getAllCandidateUsingPartylistId(int partylistId) {
        ArrayList<Candidate> listOfCandidate = new ArrayList<>();
        ResultSet rs = DBConnection.query("SELECT candidate.id as candidateId, partylist_id as partylistId, candidate.name as candidateName, eposition.id as positionId, eposition.name as positionName from candidate INNER JOIN eposition ON candidate.position_id = eposition.id WHERE partylist_id = " + partylistId);
        
        try {
            while(rs.next()){
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("candidateId"));
                candidate.setPartylistId(rs.getInt("partylistId"));
                candidate.setPositionId(rs.getInt("positionId"));
                
                candidate.setName(rs.getString("candidateName"));
                candidate.setPositionName(rs.getString("positionName"));
                listOfCandidate.add(candidate);
                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listOfCandidate;
    }
    
    public static ArrayList<Candidate> getCandidateUsingElectionIdAndPositionId(int electionId, int positionId) {
        ArrayList<Candidate> listOfCandidate = new ArrayList<>();
        String sql = "SELECT candidate.id as candidateId, candidate.partylist_id as partylistId, candidate.position_id as positionId, candidate.name as candidateName, eposition.name as positionName FROM candidate INNER JOIN eposition ON eposition.id = candidate.position_id WHERE candidate.election_id = " + electionId + " AND candidate.position_id = "+ positionId;
        
        ResultSet rs = DBConnection.query(sql);
        
        try {
            while(rs.next()){
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("candidateId"));
                candidate.setPartylistId(rs.getInt("partylistId"));
                candidate.setPositionId(rs.getInt("positionId"));
                
                candidate.setName(rs.getString("candidateName"));
                candidate.setPositionName(rs.getString("positionName"));
                listOfCandidate.add(candidate);
                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listOfCandidate;
    }
    
    public static ArrayList<Candidate> getAllCandidateUsingPositionIdAndElectionId(Candidate varCandidate) {
        ArrayList<Candidate> listOfCandidate = new ArrayList<>();
        String sql = "SELECT candidate.voteCount as voteCount, candidate.id as candidateId, candidate.partylist_id as partylistId, candidate.position_id as positionId, candidate.name as candidateName, eposition.name as positionName, partylist.name as partylistName FROM candidate INNER JOIN eposition ON eposition.id = candidate.position_id INNER JOIN partylist ON partylist.id = candidate.partylist_id WHERE candidate.position_id = " + varCandidate.getPositionId() + " AND candidate.election_id = " + varCandidate.getElectionId();
        
        
        ResultSet rs = DBConnection.query(sql);
        
        try {
            while(rs.next()){
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("candidateId"));
                candidate.setPartylistId(rs.getInt("partylistId"));
                candidate.setPositionId(rs.getInt("positionId"));
                candidate.setVoteCount(rs.getInt("voteCount"));
                
                
                candidate.setName(rs.getString("candidateName"));
                candidate.setPositionName(rs.getString("positionName"));
                candidate.setPartylistName(rs.getString("partylistName"));
                listOfCandidate.add(candidate);
                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listOfCandidate;
    }
    
    
    public static int insertCandidate(Position position) {
        int rowUpdate = 0;
        Connection connection = null;
        PreparedStatement insertCandidate = null;
        String sqlVariables = "SET @electionId = " + position.getElectionId() + ";" +
                "SET @partylistId = " + position.getPartylistId() + ";";
        String sql = "INSERT INTO candidate(election_id, position_id, partylist_id, name) "+
                "VALUES (@electionId, 1, @partylistId, ?)," + // president
                "(@electionId, 2, @partylistId, ?)," + // vice president
                "(@electionId, 3, @partylistId, ?)," + // secretary
                "(@electionId, 4, @partylistId, ?)," + // auditor
                "(@electionId, 5, @partylistId, ?)," + // treasurer
                "(@electionId, 6, @partylistId, ?)," + // pio
                "(@electionId, 7, @partylistId, ?);"; // peace officer
              
        System.out.println( sqlVariables+"\n "+sql);
        Statement createVariables = null;
        try {
            connection = DBConnection.getInstance();
            connection.setAutoCommit(false);
            
            createVariables = connection.createStatement();
            createVariables.execute(sqlVariables);
            
            insertCandidate = connection.prepareStatement(sql);
            insertCandidate.setString(1, position.getPresident());
            insertCandidate.setString(2, position.getVicePresident());
            insertCandidate.setString(3, position.getSecretary());
            insertCandidate.setString(4, position.getAuditor());
            insertCandidate.setString(5, position.getTreasurer());
            insertCandidate.setString(6, position.getPio());
            insertCandidate.setString(7, position.getPeaceOfficer());
            rowUpdate = insertCandidate.executeUpdate();
            
            connection.commit();
            
            connection.setAutoCommit(true);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }
    
    public static boolean isExist(String name, int electionId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * from candidate WHERE name = ? AND election_id = ?";
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, electionId);
            rs = statement.executeQuery();
            if(rs.first()) {
                return true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public static boolean incrementVoteCount(int candidateId) {
        String sql = "UPDATE candidate SET voteCount=voteCount+1 WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = DBConnection.getInstance();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, candidateId);
            if(statement.executeUpdate() > 0) {
                return true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
