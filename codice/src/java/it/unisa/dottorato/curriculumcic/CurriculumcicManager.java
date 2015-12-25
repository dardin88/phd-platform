package it.unisa.dottorato.curriculumcic;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.Curriculum.CurriculumManager;
import it.unisa.dottorato.Cycle.CycleManager;
import it.unisa.dottorato.exception.ReferenceException;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.NameException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe per la gestione delle coppie curriculum-ciclo
 *
 * @author Tommaso Minichiello
 */
public class CurriculumcicManager {
    private static final String TABLE_TEACH="teach";
    private static final String TABLE_CURRICULUMCIC = "curriculumcic";
    private static final String TABLE_PHDSTUDENT="phdstudent";
    private static final String TABLE_PROFESSOR="professor";
    private static final String TABLE_ACCOUNT="account";

//	 istanza della classe
    private static CurriculumcicManager instance;

    
    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CurriculumcicManager() {
        super();
    }
    
      /**
     * Metodo della classe incaricato della produzione degli oggetti, tale
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static synchronized CurriculumcicManager getInstance() {
        if (instance == null) {
            instance = new CurriculumcicManager();
        }
        return instance;
    }

    
   /**
     * Metodo della classe incaricato dell'inserimento di un record
     * nella tabella teach
     * @param pCurriculumcic il curriculum-ciclo da aggiornare
     * @param fkProfessor il nuovo professore da inserire
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * 
     */
    public synchronized void insertProfessor(Curriculumcic pCurriculumcic, String fkProfessor) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella teach
             */
           String tSql = "INSERT INTO "
                    + CurriculumcicManager.TABLE_TEACH
                    + " (fkCurriculum, fkCycle, fkProfessor)"
                    + " VALUES ('"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "',"
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle())
                    + ",'"
                    + CycleManager.getInstance().testFkProfessor(fkProfessor)
                    + "')";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReferenceException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Metodo della classe incaricato dell'inserimento di un record
     * nella tabella curriculumcic
     * @param pCurriculumcic il curriculum-ciclo da aggiornare
     * @param fkProfessor il nuovo coordinatore da inserire
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertCurriculumcicCoordinator(Curriculumcic pCurriculumcic, String fkProfessor) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "UPDATE "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " SET fkProfessor = '"
                    + CycleManager.getInstance().testFkProfessor(fkProfessor)
                    + "' WHERE fkCurriculum= '"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "' AND fkCycle ="
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle());

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReferenceException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Metodo della classe incaricato della cancellazione di un record
     * nella tabella curriculumcic
     * @param pCurriculumcic il curriculum-ciclo da eliminare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void deleteCurriculumcicCoordinator(Curriculumcic pCurriculumcic) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "UPDATE "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " SET fkProfessor = null WHERE fkCurriculum= '"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "' AND fkCycle ="
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle());

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     * Metodo della classe incaricato della visualizzazione del coordinatore
     * di un curriculum-ciclo
     * @param pCurriculumcic il curriculum-ciclo da selezionare
     * @return restituisce il profilo del coordinatore se trovato, lancia un'eccezione altrimenti
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Professor viewCurriculumcicCoordinator(Curriculumcic pCurriculumcic) throws
            ClassNotFoundException, SQLException, IOException {
        Professor cord=new Professor();
        
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "SELECT email, secondaryEmail, surname, name, link, department FROM "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC+ ","
                    + CurriculumcicManager.TABLE_PROFESSOR+ ","
                    + CurriculumcicManager.TABLE_ACCOUNT 
                    +" WHERE fkCurriculum= '"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "' AND fkCycle ="
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle())
                    +" AND secondaryEmail=fkAccount AND fkAccount=fkProfessor";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            if (result.next()) {
                cord.setEmail(result.getString("email"));
                cord.setSecondaryEmail(result.getString("secondaryEmail"));
                cord.setSurname(result.getString("surname"));
                cord.setName(result.getString("name"));
                cord.setLink(result.getString("link"));
                cord.setDepartment(result.getString("department"));
                cord.setfkAccount(cord.getSecondaryEmail());
            }

            connect.commit();
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cord;
    } 
    
    /**
     * Metodo della classe incaricato della cancellazione di un record
     * nella tabella teach
     * @param pCurriculumcic il curriculum-ciclo da selezionare
     * @param fkProfessor il professore da cancellare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void DeleteProfessor(Curriculumcic pCurriculumcic, String fkProfessor) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella teach
             */
           String tSql = "DELETE * FROM "
                    + CurriculumcicManager.TABLE_TEACH
                    + " WHERE fkCurriculum = '"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "' AND fkCycle = "
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle())
                    + " AND fkProfessor ='"
                    + CycleManager.getInstance().testFkProfessor(fkProfessor)
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (ReferenceException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo della classe incaricato dell'inserimento in un record della
     * tabella pdhstudent dei campi fkCurriculum e fkCycle
     *
     * @param pCurriculumcic il curriculum-ciclo da selezionare
     * @param fkPhdstudent il nuovo studente da iscrivere
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertPhdSudent(Curriculumcic pCurriculumcic, String fkPhdstudent) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella phdstudent
             */
            String tSql = "UPDATE phdstudent SET"
                    + "fkCurriculum = '"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "',fkCycle = "
                    + CycleManager.getInstance().testNumber(pCurriculumcic.getfkCycle())
                    + " WHERE fkAccount = '"
                    + testFkPhdstudent(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReferenceException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo della classe incaricato della ricerca e visualizzazione
     * dei professori di un curriculum-ciclo.
     *@param curriculumcic il curriculum-ciclo da selezionare 
     * @return restituisce un array list contenente i professori associati al
     * curriculum-ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Professor> viewProfessorList(Curriculumcic curriculumcic) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        ArrayList<Professor> prof=null;
        try {
            testCurriculucic(curriculumcic);
             prof= new ArrayList<>();
            Professor cord=new Professor();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella teach
             */
            String tSql = "SELECT email, secondaryEmail, surname, name, link, department FROM "
                    + CurriculumcicManager.TABLE_TEACH+","+CurriculumcicManager.TABLE_PROFESSOR
                    +","+CurriculumcicManager.TABLE_ACCOUNT
                    + " WHERE fkCycle = "
                    + CycleManager.getInstance().testNumber(curriculumcic.getfkCycle())
                    +"' AND fkCurriculum = '"
                    + CurriculumManager.getInstance().testName(curriculumcic.getfkCurriculum())
                    +"' AND secondaryEmail=fkAccount AND fkAccount=fkProfessor ";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
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
                prof.add(cord);
            }

            return prof;

        } catch (CurriculumcicException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(connect);
        }
        return prof;
    }
    
    /**
     * Metodo della classe incaricato della ricerca e visualizzazione dei 
     * dottorandi iscritti al curriculum-ciclo
     *@param curriculumcic  il curriculum-ciclo da selezionare
     * @return restituisce un array list contenente i dottorandi iscritti
     * al curriculum-ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<PhdStudent> viewPhdstudentCurriculumcic(Curriculumcic curriculumcic) 
            throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        ArrayList<PhdStudent> stud=null;
        try {
            stud = new ArrayList<>();
            PhdStudent cord=new PhdStudent();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdstudent
             */
             String tSql = "SELECT email, secondaryEmail, surname, name, telephone, researchInterest, link, department, fkProfessor FROM "
                    + CurriculumcicManager.TABLE_PHDSTUDENT +","+ CurriculumcicManager.TABLE_ACCOUNT
                    + " WHERE fkCycle = "
                    + CycleManager.getInstance().testNumber(curriculumcic.getfkCycle())
                    +" AND fkCurriculum = '"
                    + CurriculumManager.getInstance().testName(curriculumcic.getfkCurriculum())
                    +"' AND secondaryEmail=fkAccount";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
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
                cord.setfkCurriculum(curriculumcic.getfkCurriculum());
                cord.setfkCycle(curriculumcic.getfkCycle());
                cord.setfkProfessor("fkProfessor");
                stud.add(cord);
            }

            return stud;

        } catch (IdException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(connect);
        }
        return stud;
    }
    
    /**
     * Metodo della classe incaricato della cancellazione di un record della
     * tabella pdhstudent dei campi fkCurriculum e fkCycle
     *
     * @param fkPhdstudent il dottorando da cancellare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void DeletePhdSudent(String fkPhdstudent) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella phdstudent
             */
            String tSql = "UPDATE phdstudent SET"
                    + " fkCurriculum = null, fkCycle = null"
                    + " WHERE fkAccount = '"
                    + testFkPhdstudent(fkPhdstudent)
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (ReferenceException ex) {
            Logger.getLogger(CurriculumcicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Metodo della classe per il testing dell'oggetto curriculumcic; verifica
     * che non sia <code>null</code>
     * 
     * @param c il curriculumcic da testare
     * @return restituisce l'oggetto curriculumcic se valido, lancia un'eccezione altrimenti
     * @throws CurriculumcicException 
     */
    public Curriculumcic testCurriculucic(Curriculumcic c) throws CurriculumcicException{
        if(c==null)
            throw new CurriculumcicException();
        return c;
    }
    
    /** Metodo della classe per il testing dell'email del dottorando; verifica che 
     * non sia di lunghezza minore di 10 e maggiore di 50 caratteri, e che ci sia il
     * carattere '@'
     * @param s stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ReferenceException 
     */
    public String testFkPhdstudent(String s) throws ReferenceException{
        if(s.length()<10 || s.length()>50 || s.indexOf("@")==-1)
            throw new ReferenceException();
        return s;
    }

}