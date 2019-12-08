/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import javax.swing.JFrame;
import votingsystem.Forms.CandidateForm;
import votingsystem.Forms.ControlPanel;
import votingsystem.Forms.LoginForm;
import votingsystem.Forms.PartyListForm;

/**
 *
 * @author jerome
 */
public class VotingSystem extends JFrame{
    static CandidateForm candidateForm;
    static ControlPanel controlPanel;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
    
}
