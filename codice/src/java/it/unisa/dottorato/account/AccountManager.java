package it.unisa.dottorato.account;

import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.dottorato.autenticazione.PasswordException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import it.unisa.integrazione.database.exception.MissingDataException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Classe per la gestione degli account
 *
 * @author ariemmov
 */
public class AccountManager {

    private static final String TABLE_PROFESSOR = "professor";
    private static final String TABLE_ACCOUNT = "account";
    private static final String TABLE_STUDENT = "phdstudent";
    //istanza della classe
    private static AccountManager instance;

    /**
     * Metodo della classe incaricato della produzione degli oggetti, tale
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static AccountManager getInstance() {

        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;

    }
    private Object account;

    /**
     * Metodo della classe incaricato di ricercare un account data l'email
     *
     * @param sEmail email dell'account da ricercare
     * @return restituisce l'account se trovato, lancia un'eccezione altrimenti
     * @throws SQLException
     * @throws ConnectionException
     * @throws ClassNotFoundException
     * @throws it.unisa.dottorato.autenticazione.EmailException
     */
    public Account getAccountByEmail(String sEmail) throws SQLException, ConnectionException,
            ClassNotFoundException, EmailException {
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rt = null;
        Connection connection = null;
        try {
            //connessione al database
            connection = DBConnection.getConnection();
            /*
             *stringa SQL per effettuare la ricerca nella 
             * tabella account
             */
            String sql = "SELECT * From account"
                    + " WHERE email = '"
                    + testEmail(sEmail) + "'";

            String queryPhd = "select * from phdstudent where fkAccount ='";
            String queryProfessor = "select * from professor where fkAccount ='";

            if (connection == null) {
                throw new ConnectionException();
            }
            //esecuzione query
            rs = Utility.queryOperation(connection, sql);

            if (rs.next()) {
                switch (rs.getString("typeAccount")) {
                    case "phdstudent":
                        queryPhd += rs.getString("secondaryEmail") + "'";
                        stmt2 = connection.createStatement();
                        rt = stmt2.executeQuery(queryPhd);
                        if (rt.next()) {
                            PhdStudent phd = new PhdStudent();
                            phd.setName(rs.getString("name"));
                            phd.setSurname(rs.getString("surname"));
                            phd.setEmail(rs.getString("email"));
                            phd.setSecondaryEmail(rs.getString("secondaryEMail"));
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
                        if (rt.next()) {
                            Professor professor = new Professor();
                            professor.setEmail(rs.getString("email"));
                            professor.setName(rs.getString("name"));
                            professor.setSurname(rs.getString("surname"));
                            professor.setSecondaryEmail(rs.getString("secondaryEmail"));
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

            }
        } finally {

            DBConnection.releaseConnection(connection);
        }

        return null;
    }

    /**
     * Metodo della classe incaricato di ricercare tutti gli account presenti
     * nella piattaforma
     *
     * @return restituisce un array list di account di tutti gli account
     * presenti nella piattaforma
     * @throws java.sql.SQLException
     */
    public ArrayList<Account> getAccountList() throws SQLException {
        Connection connection = null;
        ResultSet rs;

        try {
            ArrayList<Account> accounts = new ArrayList<>();
            //Connessione al database
            connection = DBConnection.getConnection();
            /*
             * stringa SQL per selezionare piu record 
             * nella tabella account
             */
            String sql = "SELECT * FROM account ";

            //esecuzione della query
            rs = Utility.queryOperation(connection, sql);
            while (rs.next()) {

                Account account = new Account();
                account.setName(rs.getString("name"));
                account.setSurname(rs.getString("surname"));
                account.setEmail(rs.getString("email"));
                account.setSecondaryEmail(rs.getString("secondaryEmail"));
                account.setAdmin(rs.getBoolean("isAdministrator"));
                account.setTypeAccount(rs.getString("typeAccount"));
                accounts.add(account);
            }
            return accounts;
        } finally {
            DBConnection.releaseConnection(connection);
        }
    }

    /**
     * Metodo della classe incaricato di ricercare tutti gli account dei
     * phdStudent presenti nella piattaforma
     *
     * @return restituisce un array list di account di tutti gli account dei
     * phdStudent presenti nella piattaforma
     * @throws java.sql.SQLException
     */
    public ArrayList<PhdStudent> getPhdStudents() throws SQLException {
        Connection connect = null;

        try {
            ArrayList<PhdStudent> students = new ArrayList<>();
            //Connessione al database
            connect = DBConnection.getConnection();
            /*
             * stringa SQL per selezionare piu record 
             * nella tabella account
             */
            String sql = "SELECT * FROM account  INNER JOIN phdstudent ON account.secondaryEmail=phdstudent.fkAccount   WHERE typeAccount = 'phdstudent'";

            //esecuzione della query
            ResultSet result = Utility.queryOperation(connect, sql);
            while (result.next()) {
                PhdStudent temp = new PhdStudent();
                temp.setName(result.getString("name"));
                temp.setSurname(result.getString("surname"));
                temp.setEmail(result.getString("email"));
                temp.setLink(result.getString("link"));
                temp.setDepartment(result.getString("department"));
                temp.setResearchInterest(result.getString("researchInterest"));
                temp.setfkAccount(result.getString("fkAccount"));
                temp.setfkCycle(result.getInt("fkCycle"));
                temp.setfkCurriculum(result.getString("fkCurriculum"));
                temp.setfkProfessor(result.getString("fkProfessor"));
                temp.setSecondaryEmail(result.getString("secondaryEmail"));
                temp.setTypeAccount(result.getString("typeAccount"));
                temp.setPassword(result.getString("password"));
                temp.setAdmin(result.getBoolean("isAdministrator"));
                students.add(temp);
                System.out.println(temp);
            }
            return students;
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    
    
    /**
     * Metodo della classe incaricato di ricercare tutti gli account dei
     * professori presenti nella piattaforma
     *
     * @return restituisce un array list di account di tutti gli account dei
     * professori presenti nella piattaforma
     * @throws java.sql.SQLException
     */
    public ArrayList<Professor> getProfessors() throws SQLException {
        Connection connect = null;

        try {
            ArrayList<Professor> professors = new ArrayList<>();
            //Connessione al database
            connect = DBConnection.getConnection();
            /*
             * stringa SQL per selezionare piu record 
             * nella tabella account
             */
            String sql = "SELECT * FROM account  INNER JOIN professor ON account.secondaryEmail=professor.fkAccount   WHERE typeAccount = 'professor'";

            //esecuzione della query
            ResultSet result = Utility.queryOperation(connect, sql);
            while (result.next()) {
                Professor temp = new Professor();
                temp.setName(result.getString("name"));
                temp.setSurname(result.getString("surname"));
                temp.setEmail(result.getString("email"));
                temp.setLink(result.getString("link"));
                temp.setDepartment(result.getString("department"));
                temp.setSecondaryEmail(result.getString("secondaryEmail"));
                temp.setTypeAccount(result.getString("typeAccount"));
                temp.setPassword(result.getString("password"));
                temp.setAdmin(result.getBoolean("isAdministrator"));
                professors.add(temp);
            }

            return professors;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    public void updateIsAdmin(String secondaryEmail, boolean var) throws SQLException, EmailException {
        Connection conn = null;
        Statement stmt = null;
        try {
            String sql = "UPDATE account "
                    + "set isAdministrator = " + var
                    + " WHERE secondaryEmail = '" + testSecondaryEmail(secondaryEmail) + "'";

            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            System.out.println(sql);

            stmt.execute(sql);
            conn.commit();
        } finally {
            DBConnection.releaseConnection(conn);
        }
    }

    /**
     * Metodo della classe incaricato alla ricerca di un utente dato il suo nome
     *
     *
     * @param search il nome dell'utente da ricercare
     * @return restituisce un array list di account di tutti gli utenti trovati,
     * lancia un'eccezione altrimenti
     * @throws SQLException
     */
    public ArrayList<Account> searchUser(String search) throws SQLException, ProfileException {
        Connection connect = null;
        ArrayList<Account> accounts;
        /*
             * stringa SQL per selezionare piu record 
             * nella tabella account
         */
        if(search==null)
            throw new ProfileException();
        String sql = "SELECT * from account WHERE "
                + "name LIKE '%" + search + "%' or surname LIKE '%" + search + "%' or email LIKE '%" + search + "%'";
        try {
            //connesione al database
            connect = DBConnection.getConnection();
            accounts = new ArrayList<>();
            ResultSet result = Utility.queryOperation(connect, sql);
            while (result.next()) {
                Account temp = new Account();
                temp.setName(result.getString("name"));
                temp.setSurname(result.getString("surname"));
                temp.setEmail(result.getString("email"));
                temp.setSecondaryEmail(result.getString("secondaryEmail"));
                temp.setTypeAccount(result.getString("typeAccount"));
                temp.setPassword(result.getString("password"));
                temp.setAdmin(result.getBoolean("isAdministrator"));
                accounts.add(temp);

            }
        } finally {
            DBConnection.releaseConnection(connect);
        }
        return accounts;
    }

    private static String addSlashes(String s) {
        s = s.replaceAll("\\\\", "\\\\\\\\");
        s = s.replaceAll("\\n", "\\\\n");
        s = s.replaceAll("\\r", "\\\\r");
        s = s.replaceAll("\\00", "\\\\0");
        s = s.replaceAll("'", "\\\\'");
        return s;
    }

    /**
     * Metodo della classe incaricato dell'aggiornamento di un progilo
     *
     * @param key email dell'account da modificare
     * @param pAccount le modifiche da apportare
     * @throws SQLException
     * @throws ConnectionException
     * @throws MissingDataException
     * @throws NullAccountException
     * @throws ProfileException
     * @throws PasswordException
     * @throws EmailException
     */
    public void updateProfile(String key, Account pAccount) throws SQLException, ConnectionException,
            MissingDataException, NullAccountException, ProfileException, PasswordException, EmailException, TelephoneException, NameException {
        try (Connection connect = DBConnection.getConnection()) {
            pAccount = testAccount(pAccount);
            key = testEmail(key);

            /*
             * stringa SQL per aggiornare un record 
             * nella tabella account
             */
            String sql = "UPDATE account "
                    + "set name = '"
                    + testname(pAccount.getName())
                    + "', surname = '"
                    + testname(addSlashes(pAccount.getSurname()))
                    + "', password = '"
                    + testPassword(addSlashes(pAccount.getPassword()))
                    + "', secondaryEmail = '"
                    + testSecondaryEmail(pAccount.getSecondaryEmail())
                    + "' WHERE email = '"
                    + testEmail(key) + "'";

            String sql2 = "UPDATE "
                    + pAccount.getTypeAccount() + " ";

            if (pAccount instanceof PhdStudent) {
                sql2 += " set telephone = '"
                        + testTelephone(((PhdStudent) pAccount).getTelephone())
                        + "', link =  '"
                        + testLink(((PhdStudent) pAccount).getLink())
                        + "', department = '"
                        + testDepartment(addSlashes(((PhdStudent) pAccount).getDepartment()))
                        + "', researchInterest = '"
                        + testResearchInterest(addSlashes(((PhdStudent) pAccount).getResearchInterest()))
                        + "' WHERE fkAccount = '"
                        + testSecondaryEmail(((PhdStudent) pAccount).getSecondaryEmail()) + "'";
            }

            if (pAccount instanceof Professor) {
                sql2 += " set link = '"
                        + testLink(((Professor) pAccount).getLink())
                        + "', department = '"
                        + testDepartment(addSlashes(((Professor) pAccount).getDepartment()))
                        + "' WHERE fkAccount = '"
                        + testSecondaryEmail(((Professor) pAccount).getSecondaryEmail())
                        + "'";
            }

            if (pAccount.getTypeAccount().equals("basic")) //aggiorna solo info base
            //esecuzione della query
            {
                Utility.executeOperation(connect, sql);
            } else {
                //esecuzione delle query
                Utility.executeOperation(connect, sql);
                Utility.executeOperation(connect, sql2);
            }

            connect.commit();

        }
    }

    /**
     * Metodo della classe incaricato dell'aggiornamento di un tipo di un
     * account
     *
     * @param email
     * @param newType il nuovo tipo da inserire
     * @throws SQLException
     * @throws ConnectionException
     * @throws NullAccountException
     * @throws EmailException
     * @throws java.lang.ClassNotFoundException
     */
    public void changeType(String email, String newType)
            throws SQLException, ConnectionException, NullAccountException, EmailException, ClassNotFoundException, ProfileException, NameException {

        Connection connect = null;
        try {
            email = testEmail(email);
            newType = testType(newType);
            //connessione al database
            connect = DBConnection.getConnection();
            Account pAccount = testAccount(this.getAccountByEmail(email));

            /*
             * stringghe SQL per inserire/rimuovere piu record 
             * nella tabella account
             */
            String hack = "SET FOREIGN_KEY_CHECKS = 0";
            String hackDisable = "SET FOREIGN_KEY_CHECKS=1";

            String demotionSql = "DELETE FROM " // cancella vecchie info
                    + pAccount.getTypeAccount()
                    + " WHERE fkAccount = '"
                    + testSecondaryEmail(pAccount.getSecondaryEmail()) + "'";

            String toProfessorSql = "INSERT INTO professor " //se nuovo professor
                    + "(fkAccount,link,department)"
                    + "VALUES ('"
                    + testSecondaryEmail(pAccount.getSecondaryEmail()) + "',"
                    + "'',"
                    + "''"
                    + ")";

            String toPhdSql = "INSERT INTO phdstudent "
                    + "(fkAccount,telephone,link,department,researchInterest,fkCycle,"
                    + "fkCurriculum, fkProfessor )" //nuovo dottorando
                    + "VALUES ('"
                    + testSecondaryEmail(pAccount.getSecondaryEmail()) + "',"
                    + "'',"
                    + "'',"
                    + "'',"
                    + "'',"
                    + "NULL,"
                    + "'',"
                    + "'')";

            String changeTypeSql = "UPDATE account " //aggiorna il tipo
                    + "set typeAccount = '" + newType
                    + "' WHERE email = '" + pAccount.getEmail() + "'";

            Utility.executeOperation(connect, hack);

            if (newType.equals("phdstudent") && pAccount.getTypeAccount().equals("basic")) {
                //esecuzione delle query

                System.out.println(toPhdSql);
                Utility.executeOperation(connect, toPhdSql); //diventa un dottorando
                Utility.executeOperation(connect, changeTypeSql); //cambia tipo in account

            } else if (newType.equals("phdstudent") && pAccount.getTypeAccount().equals("professor")) {
                //esecuzione delle query
                Utility.executeOperation(connect, demotionSql); //perde info professor
                Utility.executeOperation(connect, toPhdSql); //nuove info phd
                Utility.executeOperation(connect, changeTypeSql);
            } else if (newType.equals("professor") && pAccount.getTypeAccount().equals("basic")) {
                //esecuzione delle query
                Utility.executeOperation(connect, toProfessorSql);
                Utility.executeOperation(connect, changeTypeSql);
            } else if (newType.equals("professor") && pAccount.getTypeAccount().equals("phdstudent")) {
                //esecuzione delle query
                Utility.executeOperation(connect, demotionSql);
                Utility.executeOperation(connect, toProfessorSql);
                Utility.executeOperation(connect, changeTypeSql);
            } else if (newType.equals("basic")) {
                System.out.println(demotionSql + "\n" + changeTypeSql);
                //esecuzione delle query
                Utility.executeOperation(connect, demotionSql);
                Utility.executeOperation(connect, changeTypeSql);
            }

            Utility.executeOperation(connect, hackDisable);

            connect.commit();

        } finally {
            DBConnection.releaseConnection(connect);
        }

    }

    /**
     * Metodo della classe incaricato dell'invito degli utenti via email
     *
     * @param email email a cui inviare l'invito
     * @throws SQLException
     * @throws EmailException
     */
    //Dovrebbe inviare una mail, not tested
    public void inviteUser(String email) throws SQLException, EmailException, Exception {
 Properties props = System.getProperties();

        props.setProperty("mail.smtp.user", "phdplatformunisa@gmail.com");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        try {
            testSecondaryEmail(email);
            Session session = Session.getInstance(props, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(
                            "phdplatformunisa@gmail.com",
                            "unisaunisa");

                }
            });

            Message message = new MimeMessage(session);
            Address fromAdd = new InternetAddress(
                   "phdplatformunisa@gmail.com");
            Address toAdd = new InternetAddress(email);
            message.setFrom(fromAdd);
            message.setRecipient(Message.RecipientType.TO, toAdd);
            message.setSubject("Conferma la registrazione");

            MimeBodyPart messagePart = new MimeBodyPart();
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);  // adding message part    

            //Setting the Email Encoding
            messagePart.setText("http://localhost:8080/codice/register.jsp?email=" + email, "utf-8");
            messagePart.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
            messagePart.setHeader("Content-Transfer-Encoding", "quoted-printable");
            message.setContent(multipart);
            message.setSentDate(new Date());
            
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", 465, "phdplatformunisa@gmail.com","unisaunisa");
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
            
        } finally{
        }        
    }

    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella professor_student del database.
     *
     * @param fkPhdstudent email del dottorando
     * @param fkProfessor email del professore
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertStudentTutor(String fkPhdstudent, String fkProfessor) throws ClassNotFoundException, SQLException, IOException, EmailException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella professor_student
             */
            String tSql = "update  "
                    + AccountManager.TABLE_STUDENT
                    + " set fkProfessor ='"
                    + testSecondaryEmail(fkProfessor)
                    + "' where fkAccount = '"
                    + testSecondaryEmail(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql) == 0) {
                throw new Exception();
            }

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca delle informazioni del tutor
     * relativo a uno studente.
     *
     * @param idStudent email dello studente da ricercare
     * @return restituisce il tutor dello studente
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Professor getTutor(String idStudent) throws ClassNotFoundException, SQLException, IOException, EmailException {
        Connection connect = null;
        Professor cord = null;
        try {

            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca di un record 
             * nella tabella professor_Student
             */
            String tSql = "SELECT account.email, account.secondaryEmail, account.surname, account.name, professor.link, professor.department FROM "
                    + AccountManager.TABLE_PROFESSOR
                    + ","
                    + AccountManager.TABLE_STUDENT
                    + ","
                    + AccountManager.TABLE_ACCOUNT
                    + " WHERE phdstudent.fkAccount = '"
                    + testSecondaryEmail(idStudent)
                    + "' AND fkProfessor = professor.fkAccount AND professor.fkAccount = secondaryEmail";

            connect.commit();
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                cord = new Professor();
                cord.setEmail(result.getString("email"));
                cord.setSecondaryEmail(result.getString("secondaryEmail"));
                cord.setSurname(result.getString("surname"));
                cord.setName(result.getString("name"));
                cord.setLink(result.getString("link"));
                cord.setDepartment(result.getString("department"));
                cord.setPassword(null);
                cord.setAdmin(false);
                cord.setTypeAccount(null);
                cord.setfkAccount(cord.getSecondaryEmail());
            }

            //Inviamo la Query al DataBase
            return cord;

        } finally {
            DBConnection.releaseConnection(connect);
        }

    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * professor_student del database.
     *
     * @param fkPhdstudent email del dottorando
     * @param Tutor email del professore
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void updateStudentTutor(String fkPhdstudent, String Tutor) throws ClassNotFoundException, SQLException, IOException, EmailException, Exception {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella professor_student
             */
            String tSql = "update  "
                    + AccountManager.TABLE_STUDENT
                    + " set fkProfessor ='"
                    + testSecondaryEmail(Tutor)
                    + "' where fkAccount = '"
                    + testSecondaryEmail(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql) == 0) {
                throw new Exception();
            }

            connect.commit();
        }
    }

    /**
     * Metodo della classe incaricato della cancellazopme di un'entita' nella
     * tabella professor_student del database.
     *
     * @param idStudent
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.autenticazione.EmailException
     */
    public synchronized void deleteStudentTutor(String idStudent) throws ClassNotFoundException, SQLException, IOException, EmailException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la cancellazione di un record 
             * nella tabella professor_student
             */
            String tSql = "update  "
                    + AccountManager.TABLE_STUDENT
                    + " set fkProfessor =null where fkAccount = '"
                    + testSecondaryEmail(idStudent)
                    + "'";

            System.out.println("la query di deleteStudentTutor è  " + tSql);
            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql) == 0) {
                throw new Exception();
            }

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe per il testing dell'account; verifica che l'account
     * non sia <code>null</code>
     *
     * @param account account da testare
     * @return restituisce l'account se non è nullo, lancia un'eccezione
     * altrimenti
     * @throws NullAccountException
     */
    public Account testAccount(Account account) throws NullAccountException {
        if (account == null) {
            throw new NullAccountException();
        }
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
    public String testSecondaryEmail(String email) throws EmailException {
        if ((email.length() > 50) || email.isEmpty() || email==null || (email.length() < 10) || !email.contains("@")) {
            throw new EmailException();
        }
        return email;
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
        int l=pass.length();
        int contL=0;
        int contN=0;
        for(int i=0;i<l;i++){
            if(Character.isLetter(pass.charAt(i)))
                    contL++;
            if(Character.isDigit(pass.charAt(i)))
                    contN++;
        }
        if(contN==0 || contL==0)
            throw new PasswordException();
        return pass;
    }

    /**
     * Metodo della classe per il testing di una stringa; verifica se è vuota
     *
     * @param data stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ProfileException
     */
    public String testProfileData(String data) throws ProfileException {
        if (data.isEmpty()) {
            throw new ProfileException();
        }
        return data;
    }

    /**
     * Metodo della classe per il testing di una stringa; verifica se è vuota
     *
     * @param tele
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ProfileException
     * @throws it.unisa.dottorato.account.TelephoneException
     */
    public String testTelephone(String tele) throws ProfileException, TelephoneException {
        int n = tele.length();
        if (n > 15 || n < 10) {
            throw new TelephoneException();
        }
        int c;
        for (int i = 0; i < n; i++) {
            c = Integer.parseInt(tele.substring(i, i + 1));
            if (c >= 0 && c <= 9) {

            } else {
                throw new TelephoneException();
            }
        }
        return tele;
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
    public String testname(String name) throws NameException {
        if (name.isEmpty() || (name.length() < 1) || (name.length() > 25)) {
            throw new NameException();
        }
        int n = name.length();
        char c;
        for (int i = 0; i < n; i++) {
            c = name.substring(i, i + 1).charAt(0);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                throw new NameException();
            }
        }
        return name;
    }

    public String testLink(String link) throws NameException {
        if ((link.length() > 150)) {
            throw new NameException();
        }
        return link;
    }

    public String testDepartment(String link) throws NameException {
        if ((link.length() > 50) || (link.isEmpty()) || link.equals("")) {
            throw new NameException();
        }
        return link;
    }

    public String testType(String type) throws NameException {
        if (!type.equals("basic") && !type.equals("phdstudent") && !type.equals("professor") && !type.equals("phd")) {
            throw new NameException();
        }
        return type;
    }

    public String testResearchInterest(String link) throws NameException {
        if ((link.length() > 65536)) {
            throw new NameException();
        }
        return link;
    }
}
