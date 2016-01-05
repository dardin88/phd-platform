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
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Metodo della classe incaricato di effettuare la visualizzazione di un
     * profilo dato il suo account
     *
     * @param pAccount account da ricercare
     * @return restituisce un array list di tutti gli attributi dell'account
     * <code>pAccount</code>
     * @throws SQLException
     */
    public ArrayList<String> viewProfile(Account pAccount) throws SQLException, NullAccountException {
        Connection connect = null;
        /**
         * Stringa sql per selezionare un record dalla tabella phdstudent o
         * professor
         *
         */
        String sql = "SELECT * from " + pAccount.getTypeAccount()
                + "WHERE fkAccount  = '" + pAccount.getSecondaryEmail() + "'";

        try {
            pAccount = testAccount(pAccount);
            ArrayList<String> profile = new ArrayList<>();
            connect = DBConnection.getConnection();
            //esecuzione della query
            ResultSet rs = Utility.queryOperation(connect, sql);

            while (rs.next()) {
                if (pAccount.getTypeAccount().equals("phdstudent")) {
                    profile.add(rs.getString("telephone"));
                    profile.add(rs.getString("link"));
                    profile.add(rs.getString("department"));
                    profile.add(rs.getString("researchInterest"));
                    profile.add(rs.getString("fkCurriculum"));
                    profile.add(rs.getString("fkCycle"));
                    profile.add(rs.getString("fkProfessor"));
                    profile.add(rs.getString("fkAccount"));
                }

                if (pAccount.getTypeAccount().equals("professor")) {
                    profile.add(rs.getString("link"));
                    profile.add(rs.getString("department"));
                    profile.add(rs.getString("fkAccount"));
                }
            }

            return profile;

        }finally {
            DBConnection.releaseConnection(connect);
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

    public ArrayList<PhdStudent> getPhdStudents() throws SQLException{
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
        }finally {
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

        }finally {
            DBConnection.releaseConnection(connect);
        }
    }

    public void updateIsAdmin(String secondaryEmail, boolean var) throws SQLException, EmailException {
        Connection conn = null;
        Statement stmt = null;
        int bool;
        if (var) {
            bool = 1;
        } else {
            bool = 0;
        }

        String sql = "UPDATE account "
                + "set isAdministrator = " + bool
                + " WHERE secondaryEmail = '" + testEmail(secondaryEmail) + "'";

        try {
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
        String sql = "SELECT * from account WHERE "
                + "name LIKE '%" + search + "%' or surname LIKE '%" + search + "%' or email LIKE '%" + search + "%'";
        try {
            search = testProfileData(search);
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
            MissingDataException, NullAccountException, ProfileException, PasswordException, EmailException {
        try (Connection connect = DBConnection.getConnection()) {
            pAccount = testAccount(pAccount);
            key = testEmail(key);

            /*
             * stringa SQL per aggiornare un record 
             * nella tabella account
             */
            String sql = "UPDATE account "
                    + "set name = '"
                    + testProfileData(pAccount.getName())
                    + "', surname = '"
                    + testProfileData(addSlashes(pAccount.getSurname()))
                    + "', password = '"
                    + testPassword(addSlashes(pAccount.getPassword()))
                    + "', secondaryEmail = '"
                    + testEmail(pAccount.getSecondaryEmail())
                    + "' WHERE email = '"
                    + key + "'";

            String sql2 = "UPDATE "
                    + pAccount.getTypeAccount() + " ";

            if (pAccount instanceof PhdStudent) {
                sql2 += " set telephone = '"
                        + testProfileData(((PhdStudent) pAccount).getTelephone())
                        + "', link =  '"
                        + testProfileData(((PhdStudent) pAccount).getLink())
                        + "', department = '"
                        + testProfileData(addSlashes(((PhdStudent) pAccount).getDepartment()))
                        + "', researchInterest = '"
                        + testProfileData(addSlashes(((PhdStudent) pAccount).getResearchInterest()))
                        + "' WHERE fkAccount = '"
                        + testProfileData(((PhdStudent) pAccount).getSecondaryEmail()) + "'";
            }

            if (pAccount instanceof Professor) {
                sql2 += " set link = '"
                        + testProfileData(((Professor) pAccount).getLink())
                        + "', department = '"
                        + testProfileData(addSlashes(((Professor) pAccount).getDepartment()))
                        + "' WHERE fkAccount = '"
                        + testProfileData(((Professor) pAccount).getSecondaryEmail())
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
     * @param email
     * @param newType il nuovo tipo da inserire
     * @throws SQLException
     * @throws ConnectionException
     * @throws NullAccountException
     * @throws EmailException
     */
    public void changeType(String email, String newType)
            throws SQLException, ConnectionException, NullAccountException, EmailException, ClassNotFoundException, ProfileException {

        Connection connect = null;
        try {
            email = testEmail(email);
            newType = testProfileData(newType);
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
                    + testEmail(pAccount.getSecondaryEmail()) + "'";

            String toProfessorSql = "INSERT INTO professor " //se nuovo professor
                    + "(fkAccount,link,department)"
                    + "VALUES ('"
                    + testEmail(pAccount.getSecondaryEmail()) + "',"
                    + "'',"
                    + "''"
                    + ")";

            String toPhdSql = "INSERT INTO phdstudent "
                    + "(fkAccount,telephone,link,department,researchInterest,fkCycle,"
                    + "fkCurriculum, fkProfessor )" //nuovo dottorando
                    + "VALUES ('"
                    + testEmail(pAccount.getSecondaryEmail()) + "',"
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
    public void inviteUser(String email) throws SQLException, EmailException {
        String to;
        to = testEmail(email);
        String from = "phdplatform@unisa.it";

        String host = "localhost"; //testing
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session mailSession = Session.getDefaultInstance(properties);

        try {

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Sei stato invitato ad iscriverti a"
                    + " Phd-platform.");
            message.setText("localhost:8080/phd-platform/codice/register.jsp"); //test
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
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
    public synchronized void insertStudentTutor(String fkPhdstudent, String fkProfessor) throws ClassNotFoundException, SQLException, IOException, EmailException {
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
                    + testEmail(fkProfessor)
                    + "' where fkAccount = '"
                    + testEmail(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

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
                    + testEmail(idStudent)
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
    public synchronized void updateStudentTutor(String fkPhdstudent, String Tutor) throws ClassNotFoundException, SQLException, IOException, EmailException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella professor_student
             */
            String tSql = "update  "
                    + AccountManager.TABLE_STUDENT
                    + " set fkProfessor ='"
                    + testEmail(Tutor)
                    + "' where fkAccount = '"
                    + testEmail(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

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
     */
    public synchronized void deleteStudentTutor(String idStudent) throws ClassNotFoundException, SQLException, IOException, EmailException {
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
                    + testEmail(idStudent)
                    + "'";

            System.out.println("la query di deleteStudentTutor è  " + tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

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
    public String testEmail(String email) throws EmailException {
        if (email.isEmpty() || (email.length() > 50) || !email.contains("@")) {
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

}
