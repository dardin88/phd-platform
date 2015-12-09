package it.unisa.dottorato.account;

import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.dottorato.autenticazione.PasswordException;
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
             temp.setTypeAccount(result.getString("typeAccount"));
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
    
  public ArrayList<Account> searchUser(String search, String type) throws SQLException {
      Connection connect = null;
      ArrayList<Account> accounts;
      
      String sql = "SELECT * from account WHERE "
              + "name LIKE '%" + search + "%'" +
               "AND typeAccount = '" + type + "'";
      String sql2 = "SELECT * from account WHERE " 
              + "typeAccount ='" + type + "'";
      try {
          connect = DBConnection.getConnection();
          accounts = new ArrayList<>();
          if(search.isEmpty()) {
            ResultSet result =  Utility.queryOperation(connect, sql2);
            Account temp = new Account();
            while(result.next()) {
             temp.setName(result.getString("name"));
             temp.setEmail(result.getString("email"));
             temp.setSecondaryEmail(result.getString("secondaryEmail"));
             temp.setTypeAccount(result.getString("typeAccount"));
             temp.setPassword(result.getString("password"));
             temp.setAdmin(result.getBoolean("isAdministrator"));
             accounts.add(temp);

          }
       }
          else {
            ResultSet result =  Utility.queryOperation(connect, sql);
            Account temp = new Account();
            while(result.next()) {
             temp.setName(result.getString("name"));
             temp.setEmail(result.getString("email"));
             temp.setSecondaryEmail(result.getString("secondaryEmail"));
             temp.setTypeAccount(result.getString("typeAccount"));
             temp.setPassword(result.getString("password"));
             temp.setAdmin(result.getBoolean("isAdministrator"));
             accounts.add(temp);
            }
          }
          
      } finally {
          DBConnection.releaseConnection(connect);
      }
      return accounts;
  }
      
      
      

    
    public void updateProfile(String key, Account pAccount) throws SQLException, ConnectionException,
            MissingDataException, NullAccountException, ProfileException, PasswordException, EmailException {
        try (Connection connect = DBConnection.getConnection()) {
            pAccount = testAccount(pAccount);

            
            String sql = "UPDATE account"
                + "set name = '"
                + Utility.Replace(testProfileData(pAccount.getName()))
                + "', surname = '"
                + Utility.Replace(testProfileData(pAccount.getSurname()))
                + "', password = '"
                + testPassword(pAccount.getPassword())
                + "', secondaryEmail = '"
                + testEmail(pAccount.getSecondaryEmail())
                + "WHERE email = '"
                + key + "'";
        
        String sql2 = "UPDATE "
                + pAccount.getTypeAccount();
        
        if(pAccount instanceof PhdStudent) {
            sql2 += " set telephone = '"
                    + testProfileData(((PhdStudent) pAccount).getTelephone())
                    + "', link =  '"
                    + testProfileData(((PhdStudent) pAccount).getLink())
                    + "', deparment = '"
                    + testProfileData(((PhdStudent) pAccount).getDepartment())
                    + "', researchInterest = '"
                    + testProfileData(((PhdStudent) pAccount).getResearchInterest())
                    + "' WHERE fkAccount = '"
                    + testProfileData(((PhdStudent) pAccount).getSecondaryEmail());
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
                
       if(pAccount.getTypeAccount().equals("basic")) //aggiorna solo info base
               Utility.executeOperation(connect, sql);
       else {
           Utility.executeOperation(connect, sql);
           Utility.executeOperation(connect, sql2);
        }
        
        connect.commit();

    }
  }
    
    
    
    public void changeType(Account pAccount, String newType)
            throws SQLException, ConnectionException, NullAccountException, EmailException {
        String demotionSql = "DELETE FROM " // cancella vecchie info
                 + pAccount.getTypeAccount()
                 + "WHERE fkAccount = '"
                 + testEmail(pAccount.getSecondaryEmail()) + "'";
        
        String toProfessorSql = "INSERT INTO professor " //se nuovo professor
                +"(fkAccount,link,department)"
                + "VALUES ('"
                + testEmail(pAccount.getSecondaryEmail()) + "',"
                +"'" + "null" + "',"
                +"'" + "null" + "'";
        
        String toPhdSql = "INSERT INTO phdstudent "
                + "(fkAccount,telephone,link,deparment,researchInterest,fkCycle"
                + "fkCurriculum, fkProfessor )" //nuovo dottorando
                + "VALUES ('"
                + testEmail(pAccount.getSecondaryEmail()) + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "',"
                + "'" + "null" + "'";
        
        String changeTypeSql = "UPDATE account" //aggiorna il tipo
                +"set typeAccount = '" + newType
                + "' WHERE email = '" + pAccount.getEmail();
        
        Connection connect = null;
        try {
            connect = DBConnection.getConnection();
            pAccount = testAccount(pAccount);
            
            if(newType.equals("phdstudent") && pAccount.getTypeAccount().equals("basic")) {
                Utility.executeOperation(connect, toPhdSql); //diventa un dottorando
                Utility.executeOperation(connect, changeTypeSql); //cambia tipo in account
            }
            else if(newType.equals("phdstudent") && pAccount.getTypeAccount().equals("professor")) {
                Utility.executeOperation(connect, demotionSql); //perde info phd
                Utility.executeOperation(connect, toPhdSql); //nuove info prof
                Utility.executeOperation(connect, changeTypeSql);
            }
            else if(newType.equals("professor") && pAccount.getTypeAccount().equals("basic")) {
                Utility.executeOperation(connect, toProfessorSql);
                Utility.executeOperation(connect, changeTypeSql);
            }
            else if(newType.equals("professor") && pAccount.getTypeAccount().equals("phdstudent")) {
                Utility.executeOperation(connect, demotionSql);
                Utility.executeOperation(connect, toProfessorSql);
                Utility.executeOperation(connect, changeTypeSql);
            }
            else if(newType.equals("basic")) {
                Utility.executeOperation(connect, demotionSql);
                Utility.executeOperation(connect, changeTypeSql);
            }
            
            
            
        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        }
    
    //Dovrebbe inviare una mail, not tested
    public void inviteUser(String email) throws SQLException, EmailException {
        String to;
        to = testEmail(email);
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
    
    public Account testAccount(Account account) throws NullAccountException {
        if(account == null)
            throw new NullAccountException();
        return account;
    }
    
    public String testEmail(String email) throws EmailException {
        if(email.isEmpty() || email.length() > 50) 
            throw new EmailException();
        return email;
    }
    
    public String testPassword(String pass) throws PasswordException {
        if(pass.isEmpty() || pass.length() > 20)
            throw new PasswordException();
        return pass;
    }
    
 
    public String testProfileData(String data) throws ProfileException {
        if(data.isEmpty())
            throw new ProfileException();
        return data;
    }


  }


 

   
    
   
