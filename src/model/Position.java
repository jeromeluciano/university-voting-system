/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jerome
 */
public class Position {
    private String president;
    private String vicePresident;
    private String secretary;
    private String auditor;
    private String treasurer;
    private String pio;
    private String peaceOfficer;
    
    private int electionId;
    private int positionId;
    private int partylistId;

    public String getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(String treasurer) {
        this.treasurer = treasurer;
    }

       
    public int getPartylistId() {
        return partylistId;
    }

    public void setPartylistId(int partylistId) {
        this.partylistId = partylistId;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(String vicePresident) {
        this.vicePresident = vicePresident;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPio() {
        return pio;
    }

    public void setPio(String pio) {
        this.pio = pio;
    }

    public String getPeaceOfficer() {
        return peaceOfficer;
    }

    public void setPeaceOfficer(String peaceOfficer) {
        this.peaceOfficer = peaceOfficer;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
    
    
}
