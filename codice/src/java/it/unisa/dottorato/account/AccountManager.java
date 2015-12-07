package it.unisa.dottorato.account;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

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
    
    public ArrayList<Account> getAccountList() {
    Connection connect = null;
    
    try {
         ArrayList<Account> accounts = new ArrayList<>();
         connect = DBConnection.getConnection();
         
         String sql = "SELECT * FROM account"
                 +    "ORDER BY name desc";
         
         ResultSet result = Utility.queryOperation(connect, sql);
         Account temp = new Account();
         while(result.next()){
             temp.setName(result.getString("name"));
             temp.setEmail(result.getString("email"));
             temp.setSecondaryEmail(result.getString("secondaryEmail"));
             temp.setTypeOfAccount(result.getString("typeAccount"));
             temp.setPassword(result.getString("password"));
             temp.setAdmin(result.getBoolean("isAdministrator"));
             accounts.add(temp);
         }
          return accounts;

         
    }   catch (SQLException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        DBConnection.releaseConnection(connect);
    }
    return null;
  }
    
    public void updateProfile(String key, Account pAccount) throws SQLException, ConnectionException,
            MissingDataException {
        try (Connection connect = DBConnection.getConnection()) {
      
            String sql = "UPDATE account"
                + "set name = '"
                + Utility.Replace(pAccount.getName())
                + "', surname = '"
                + Utility.Replace(pAccount.getSurname())
                + "', password = '"
                + pAccount.getPassword()
                + "', secondaryEmail = '"
                + pAccount.getSecondaryEmail()
                + "WHERE email = '"
                + key + "'";
        
        String sql2 = "UPDATE "
                + pAccount.getTypeOfAccount();
        
        if(pAccount instanceof PhdStudent) {
            sql2 += " set telephone = '"
                    + ((PhdStudent) pAccount).getTelephone()
                    + "', link =  '"
                    + ((PhdStudent) pAccount).getLink()
                    + "', deparment = '"
                    + ((PhdStudent) pAccount).getDepartment()
                    + "', researchInterest = '"
                    + ((PhdStudent) pAccount).getResearchInterest()
                    + "' WHERE fkAccount = "
                    + ((PhdStudent)pAccount).getSecondaryEmail();
        }
        
        if(pAccount instanceof Professor) {
            sql2 += " set link = '"
                    + ((Professor) pAccount).getLink()
                    +"', set department = '"
                    + ((Professor) pAccount).getDepartment()
                    +"' WHERE fkAccount = '"
                    + ((Professor) pAccount).getSecondaryEmail()
                    +"'";
        }
                
       if(pAccount.getTypeOfAccount().equals("basic")) //aggiorna solo info base
               Utility.executeOperation(connect, sql);
       else {
           Utility.executeOperation(connect, sql);
           Utility.executeOperation(connect, sql2);
        }
        
        connect.commit();

    }
  }
    
    
    
    public void changeType(Account pAccount, String newType)
            throws SQLException, ConnectionException {
        String demotionSql = "DELETE FROM " // cancella vecchie info
                 + pAccount.getTypeOfAccount()
                 + "WHERE fkAccount = '"
                 + pAccount.getSecondaryEmail() + "'";
        
        String toProfessorSql = "INSERT INTO professor " //se nuovo professor
                +"(fkAccount,link,department)"
                + "VALUES ('"
                + pAccount.getSecondaryEmail() + "',"
                +"'" + "null" + "',"
                +"'" + "null" + "'";
        
        String toPhdSql = "INSERT INTO phdstudent "
                + "(fkAccount,telephone,link,deparment,researchInterest,fkCycle"
                + "fkCurriculum, fkProfessor )" //nuovo dottorando
                + "VALUES ('"
                + pAccount.getSecondaryEmail() + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "'";
        
        Connection connect = null;
        try {
            connect = DBConnection.getConnection();
            
            if(newType.equals("phdstudent") && pAccount.getTypeOfAccount().equals("basic"))
                Utility.executeOperation(connect, toPhdSql); //diventa un dottorando
            else if(newType.equals("phdstudent") && pAccount.getTypeOfAccount().equals("professor")) {
                Utility.executeOperation(connect, demotionSql); //perde info phd
                Utility.executeOperation(connect, toPhdSql); //nuove info prof
            }
            else if(newType.equals("professor") && pAccount.getTypeOfAccount().equals("basic"))
                Utility.executeOperation(connect, toProfessorSql);
            else if(newType.equals("professor") && pAccount.getTypeOfAccount().equals("phdstudent")) {
                Utility.executeOperation(connect, demotionSql);
                Utility.executeOperation(connect, toProfessorSql);
            }
            else if(newType.equals("basic"))
                Utility.executeOperation(connect, demotionSql);
            
            
        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        }
     
    
    //Dovrebbe inviare una mail, not tested
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
            message.setText("localhost:8080/phd-platform/register.jsp"); //test
            Transport.send(message);
        } catch(MessagingException ex) {
            ex.printStackTrace();
      }
    }
  }


 

   
    
   
