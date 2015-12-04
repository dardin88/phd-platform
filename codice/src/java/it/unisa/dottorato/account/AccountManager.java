package it.unisa.dottorato.account;

import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import it.unisa.integrazione.model.*;

/**
 *
 * @author ariemmov
 */
public class AccountManager {

    private static AccountManager instance;

    public static AccountManager getInstance() {

        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;

    }

    public Account login(String pUsername, String pPassword) throws SQLException, ConnectionException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String query = "select * from account where email='" + pUsername + "' and password='" + pPassword + "'";
        String queryPhd = "select * from phdstudent where fkAccount ='";
        String queryProfessor = "selecy * from professor where fkAccount ='";

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

    public void add(Account pAccount) throws SQLException {
        Connection connect = DBConnection.getConnection();

        String sql = "INSERT INTO account (email, secondaryemail, surname, name, password,typeAccount,isAdministrator) VALUES ('" + pAccount.getSecondaryEmail() + "','" + pAccount.getEmail() + "','" +
                pAccount.getTypeOfAccount() + "','" + pAccount.isAdmin() + "')";

        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    } 
    
    public Account getAccountByEmail(String pEmail) throws SQLException, ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        
        String query = "select * from account where email = '" + pEmail + "'";
        String queryPhd = "select * from phdstudent where fkAccount ='";
        String queryProfessor = "selecy * from professor where fkAccount ='";
         

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
