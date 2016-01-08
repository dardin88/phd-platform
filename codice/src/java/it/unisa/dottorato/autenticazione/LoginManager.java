package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.NameException;
import it.unisa.dottorato.account.NullAccountException;
import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**Classe per la gestione dei log-in
 *
 * @author Armando
 */
public class LoginManager  {
    //istanza della classe
    private static LoginManager instance;

    /**
     * Metodo della classe incaricato della produzione degli oggetti, tale
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static LoginManager getInstance() {

        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;

    }
    
    /**Mentodo della classe incaricato di effettuare il login dato un nome utente
     * e una password
     * 
     * @param pUsername la username dell'utente
     * @param pPassword la password dell'utente
     * @return restituisce l'account se l'accesso va a buon fine, lancia 
     * un'eccezione altrimenti
     * @throws SQLException
     * @throws ConnectionException
     * @throws EmailException
     * @throws PasswordException 
     */
    public Account login (String pUsername, String pPassword) throws SQLException,
            ConnectionException, EmailException,PasswordException, Exception {
        Connection connection = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rt = null;
        
        /*
             * stringhe SQL per selezionare piu record 
             * nella tabella account, phdstudent e professor
             */
        String query = "select * from account where email='" + addSlashes(pUsername) + "' and password='" + addSlashes(pPassword) + "'";
        String queryPhd = "select * from phdstudent where fkAccount ='";
        String queryProfessor = "select * from professor where fkAccount ='";

        try {
            //connessione al database
            connection = DBConnection.getConnection();


            if (connection == null) {
                throw new ConnectionException();
            }
            //esecuzione query
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                switch(rs.getString("typeAccount")) {
                    case "phdstudent":
                       queryPhd += rs.getString("secondaryEmail") + "'";
                       stmt2 = connection.createStatement();
                       rt = stmt2.executeQuery(queryPhd);
                       if(rt.next()) {
                           PhdStudent phd = new PhdStudent();
                           phd.setName(rs.getString("name"));
                           phd.setSurname(rs.getString("surname"));
                           phd.setPassword(rs.getString("password"));
                           phd.setEmail(rs.getString("email"));
                           phd.setSecondaryEmail(rs.getString("secondaryEmail"));
                           phd.setTypeAccount(rs.getString("typeAccount"));
                           phd.setAdmin(rs.getBoolean("isAdministrator"));
                           phd.setfkAccount(rs.getString("secondaryEmail"));
                           phd.setTelephone(rt.getString("telephone"));
                           phd.setDepartment(rt.getString("department"));
                           phd.setResearchInterest(rt.getString("researchInterest"));
                           phd.setLink(rt.getString("link"));
                           phd.setfkCycle(rt.getInt("fkCycle"));
                           phd.setfkProfessor(rt.getString("fkProfessor"));
                           phd.setfkCurriculum(rt.getString("fkCurriculum"));
                           phd.setAdmin(rs.getBoolean("isAdministrator"));
                           return phd;                      
                   }
                    
                    case "professor":
                        queryProfessor += rs.getString("secondaryEmail") + "'";
                        stmt2 = connection.createStatement();
                        rt = stmt2.executeQuery(queryProfessor);
                        if(rt.next()) {
                            Professor professor = new Professor();
                            professor.setEmail(rs.getString("email"));
                            professor.setName(rs.getString("name"));
                            professor.setSurname(rs.getString("surname"));
                            professor.setSecondaryEmail(rs.getString("secondaryEmail"));
                            professor.setPassword(rs.getString("password"));
                            professor.setTypeAccount(rs.getString("typeAccount"));
                            professor.setAdmin(rs.getBoolean("isAdministrator"));
                            professor.setfkAccount(rs.getString("secondaryEmail"));
                            professor.setDepartment(rt.getString("department"));
                            professor.setLink(rt.getString("link"));
                            professor.setAdmin(rs.getBoolean("isAdministrator"));
                            return professor;
                        }
                        
                    case "basic":
                        Account account = new Account();
                        account.setName(rs.getString("name"));
                        account.setSurname(rs.getString("surname"));
                        account.setEmail(rs.getString("email"));
                        account.setSecondaryEmail(rs.getString("secondaryEmail"));
                        account.setAdmin(rs.getBoolean("isAdministrator"));
                        return account;
                }
            }else{
                throw new Exception();
            }
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return null;
  }
    
       private static String addSlashes(String s) {
        s = s.replaceAll("\\\\", "\\\\\\\\");
        s = s.replaceAll("\\n", "\\\\n");
        s = s.replaceAll("\\r", "\\\\r");
        s = s.replaceAll("\\00", "\\\\0");
        s = s.replaceAll("'", "\\\\'");
        return s;
    }
    
    /** Metodo della classe incaricato di effettuare il log-out
     * 
     * @param session la sessione corrente
     */
  public void logout(HttpSession session) {
      session.removeAttribute("account");
  }
  
  /** Metodo della classe incaricato di effettuare la registrazione dato 
   * un account
   * 
   * @param pAccount account da registrare
   * @throws SQLException
   * @throws NullAccountException 
   */
  public void register(Account pAccount) throws SQLException, NullAccountException, EmailException, PasswordException, NameException, Exception {
      //connessione al database  
      Connection connect = DBConnection.getConnection();
      pAccount = testAccount(pAccount);


      /*
             * stringa SQL per inserire un record 
             * nella tabella account
             */
        String sql = "INSERT INTO account"
                + "(email, secondaryemail, surname, name, password,typeAccount,isAdministrator)"
                + " VALUES ('"
                + testEmail(pAccount.getEmail()) + "','" 
                + testSecondaryEmail(pAccount.getSecondaryEmail()) + "','"
                + addSlashes(testName(pAccount.getSurname())) + "','"
                + addSlashes(testName(pAccount.getName())) + "','"
                + testPassword(pAccount.getPassword()) +"','basic',false)";

        try {
            //esecuzione query
            if(Utility.executeOperation(connect, sql)==0)
                throw new Exception();
            connect.commit();
           
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
  
    /** Metodo della classe per il testing dell'account; l'oggetto account
     * non deve essere <code>null</code>
     * 
     * @param account account da testare
     * @return restituisce l'account se valido, lancia un'eccezione altrimenti
     * @throws NullAccountException 
     */
    public Account testAccount(Account account) throws NullAccountException {
        if(account == null)
            throw new NullAccountException();
        return account;
    }
  
    /**
     * Metodo della classe per il testing dell'email; verifica che non sia una
     * stringa vuota o piu' lunga di 50 caratteri
     *
     * @param email stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws EmailException
     */
    public String testEmail(String email) throws EmailException {
        if (email.isEmpty() || (email.length() < 10) || (email.length() > 50) || !email.contains("@unisa")) {
            throw new EmailException();
        }
        return email;
    }

    /**
     * Metodo della classe per il testing dell'email; verifica che non sia una
     * stringa vuota o piu' lunga di 50 caratteri
     *
     * @param name stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws NameException
     */
    public String testName(String name) throws NameException {
        if (name.isEmpty() || (name.length() < 1) || (name.length() > 25)) {
            throw new NameException();
        }
        return name;
    }
     /**
     * Metodo della classe per il testing della password; verifica che non sia
     * una stringa vuota o piu' lunga di 20 caratteri o piu' corta di 8
     *
     * @param pass stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws PasswordException
     */
      public String testPassword(String pass) throws PasswordException {
        if ((pass.length() > 16) || (pass.length() < 8)) {
            throw new PasswordException();
        }
        return pass;
    }
      
      /**
     * Metodo della classe per il testing dell'email; verifica che non sia una
     * stringa vuota o piu' lunga di 50 caratteri
     *
     * @param email stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws EmailException
     */
    public String testSecondaryEmail(String email) throws EmailException {
        if (email.isEmpty() || (email.length() < 10) || (email.length() > 50) || !email.contains("@")) {
            throw new EmailException();
        }
        return email;
    }

  }

