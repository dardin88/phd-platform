package it.unisa.dottorato.account;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
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
    
    public void update(Account pAccount) throws SQLException, ConnectionException,
            MissingDataException {
                 Connection connect = null;
        
        try {
                connect = DBConnection.getConnection();
                
                String sql = "UPDATE account "
                        + "set secondaryEmail = '"
                        + pAccount.getSecondaryEmail()
                        + "', email = '"
                        + pAccount.getEmail()
                        + "', surname = '"
                        + pAccount.getSurname()
                        + "', name = '"
                        + pAccount.getName()
                        + "', password = '"
                        + pAccount.getPassword()
                        + "', typeAccount = '"
                        + pAccount.getTypeOfAccount()
                        + "', isAdministrator = '"
                        + pAccount.isAdmin()
                        + "'";
                
                
                if (pAccount instanceof PhdStudent) {
                    sql += ", fkAccount = '"
                            + ((PhdStudent) pAccount).getfkAccount()
                            + "', telephone = '"
                            + ((PhdStudent) pAccount).getTelephone()
                            + ",  link = '"
                            + ((PhdStudent) pAccount).getLink()
                            + "', deparment = '"
                            + ((PhdStudent) pAccount).getDepartment()
                            + "', resarchInterest ='"
                            + ((PhdStudent) pAccount).getResearchInterest()
                            + "', fkCycle = '"
                            + ((PhdStudent) pAccount).getfkCycle()
                            + "', fkCurriculum = '"
                            + ((PhdStudent) pAccount).getfkCurriculum()
                            + "', fkProfessor = '"
                            + ((PhdStudent) pAccount).getfkProfessor()
                            + "'";
                }
                
                else if(pAccount instanceof Professor) {
                    sql += ", fkAccount = '"
                            + ((Professor) pAccount).getfkAccount()
                            + "', link = '"
                            + ((Professor) pAccount).getLink()
                            + "', department = '"
                            + ((Professor) pAccount).getDepartment()
                            + "'";
                }
                
                Utility.executeOperation(connect, sql);
                connect.commit();
        }  finally {
            DBConnection.releaseConnection(connect);
        }

    }
    
    public void changeType(Account pAccount, String newType)
            throws SQLException, ConnectionException {
        
           //Work in Progress
        
        }
     
    
    public void add(Account pAccount) throws SQLException {
        Connection connect = DBConnection.getConnection();

        String sql = "INSERT INTO account"
                + "(email, secondaryemail, surname, name, password,typeAccount,isAdministrator)"
                + " VALUES ('"
                + pAccount.getSecondaryEmail() + "','" 
                + pAccount.getEmail() + "','"
                + pAccount.getSurname() + "','"
                + pAccount.getName() + "','"
                + pAccount.getTypeOfAccount() + "','"
                + pAccount.isAdmin() + "')";

        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    } 
    
    //Per testare come inviare mail
    public void inviteUser(String email) throws SQLException {
        String to = email;
        String from = "phdplatform@unisa.it";
        
        String host = "localhost"; //testing
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host",host);
        
        Session mailSession = Session.getDefaultInstance(properties);
        
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Sei stato invitato ad iscriverti a"
                    + " Phd-platform.");
            message.setText("bla bla bla testing 123"); //test
            Transport.send(message);
        } catch(MessagingException ex) {
            ex.printStackTrace();
        }
    }
  }


 

   
    
   
