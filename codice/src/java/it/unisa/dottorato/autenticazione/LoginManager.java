/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Armando
 */
public class LoginManager  {
    
    private static LoginManager instance;

    public static LoginManager getInstance() {

        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;

    }
    
    public Account login (String pUsername, String pPassword) throws SQLException, ConnectionException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String query = "select * from account where email='" + pUsername + "' and password='" + pPassword + "'";
        String queryPhd = "select * from phdstudent where fkAccount ='";
        String queryProfessor = "select * from professor where fkAccount ='";

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                switch(rs.getString("typeAccount")) {
                    case "phd":
                       queryPhd += rs.getString("secondaryEmail") +"'";
                       ResultSet rt = stmt.executeQuery(queryPhd);
                       if(rt.next()) {
                           PhdStudent phd = new PhdStudent();
                           phd.setName(rs.getString("name"));
                           phd.setSurname(rs.getString("surname"));
                           phd.setPassword(rs.getString("password"));
                           phd.setTypeOfAccount(rs.getString("typeAccount"));
                           phd.setAdmin(rs.getBoolean("isAdministrator"));
                           phd.setfkAccount(rs.getString("secondaryEmail"));
                           phd.setTelephone(rt.getString("telephone"));
                           phd.setDepartment(rt.getString("deparment"));
                           phd.setResearchInterest(rt.getString("researchInterest"));
                           phd.setLink(rt.getString("link"));
                           phd.setfkCycle(rt.getInt("fkCycle"));
                           phd.setfkProfessor(rt.getString("fkProfessor"));
                           phd.setfkCurriculum(rt.getString("fkCurriculum"));
                           phd.setAdmin(false);
                           return phd;                      
                   }
                    
                    case "docente":
                        queryProfessor += rs.getString("secondaryEmail") + "'";
                        rt = stmt.executeQuery(queryProfessor);
                        if(rt.next()) {
                            Professor professor = new Professor();
                            professor.setName(rs.getString("name"));
                            professor.setSurname(rs.getString("surname"));
                            professor.setPassword(rs.getString("password"));
                            professor.setTypeOfAccount(rs.getString("typeAccount"));
                            professor.setAdmin(rs.getBoolean("isAdministrator"));
                            professor.setfkAccount(rs.getString("secondaryEmail"));
                            professor.setDepartment(rt.getString("department"));
                            professor.setLink(rt.getString("link"));
                            professor.setAdmin(false);
                            return professor;
                        }
                        
                    case "basic":
                        Account account = new Account();
                        account.setName(rs.getString("name"));
                        account.setSurname(rs.getString("surname"));
                        account.setEmail(rs.getString("email"));
                        account.setSecondaryEmail(rs.getString("secondaryEmail"));
                        account.setAdmin(false);
                        return account;
                }
            }
        } finally {
            DBConnection.releaseConnection(connection);
        }
        
        return null;
    }
    
    
     
     
     public Account getAccountByEmail(String pEmail) throws SQLException, ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        
        String query = "select * from account where email = '" + pEmail + "'";
        String queryPhd = "select * from phdstudent where fkAccount ='";
        String queryProfessor = "select * from professor where fkAccount ='";
         

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                switch(rs.getString("typeAccount")) {
                    case "dottorando":
                       queryPhd += rs.getString("secondaryEmail") +"'";
                       ResultSet rt = stmt.executeQuery(queryPhd);
                       if(rt.next()) {
                           PhdStudent phd = new PhdStudent();
                           phd.setName(rs.getString("name"));
                           phd.setSurname(rs.getString("surname"));
                           phd.setPassword(rs.getString("password"));
                           phd.setTypeOfAccount(rs.getString("typeAccount"));
                           phd.setAdmin(rs.getBoolean("isAdministrator"));
                           phd.setfkAccount(rs.getString("secondaryEmail"));
                           phd.setTelephone(rt.getString("telephone"));
                           phd.setDepartment(rt.getString("deparment"));
                           phd.setResearchInterest(rt.getString("researchInterest"));
                           phd.setLink(rt.getString("link"));
                           phd.setfkCycle(rt.getInt("fkCycle"));
                           phd.setfkProfessor(rt.getString("fkProfessor"));
                           phd.setfkCurriculum(rt.getString("fkCurriculum"));
                           return phd;                      
                   }
                    
                    case "docente":
                        queryProfessor += rs.getString("secondaryEmail") + "'";
                        rt = stmt.executeQuery(queryProfessor);
                        if(rt.next()) {
                            Professor professor = new Professor();
                            professor.setName(rs.getString("name"));
                            professor.setSurname(rs.getString("surname"));
                            professor.setPassword(rs.getString("password"));
                            professor.setTypeOfAccount(rs.getString("typeAccount"));
                            professor.setAdmin(rs.getBoolean("isAdministrator"));
                            professor.setfkAccount(rs.getString("secondaryEmail"));
                            professor.setDepartment(rt.getString("department"));
                            professor.setLink(rt.getString("link"));
                            return professor;
                        }
                }
            }
        } finally {

         DBConnection.releaseConnection(connection);
        }

        return null;
    }
    
}
