package it.unisa.dottorato.curriculumcic;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     */
    public synchronized void insertProfessor(Curriculumcic pCurriculumcic, String fkProfessor) throws
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella teach
             */
           String tSql = "INSERT INTO "
                    + CurriculumcicManager.TABLE_TEACH
                    + " (fkCurriculum, fkCycle, fkProfessor)"
                    + " VALUES ('"
                    + pCurriculumcic.getfkCurriculum()
                    + "',"
                    + pCurriculumcic.getfkCycle()
                    + ",'"
                    + fkProfessor
                    + "')";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
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

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "UPDATE "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " SET fkProfessor = '"
                    + fkProfessor
                    + "' WHERE fkCurriculum= '"
                    + pCurriculumcic.getfkCurriculum()
                    + "' AND fkCycle ="
                    + pCurriculumcic.getfkCycle();

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
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

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "UPDATE "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " SET fkProfessor = null WHERE fkCurriculum= '"
                    + pCurriculumcic.getfkCurriculum()
                    + "' AND fkCycle ="
                    + pCurriculumcic.getfkCycle();

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    } 
    
    /**
     * Metodo della classe incaricato della visualizzazione di tutti i coordinatori
     * di un curriculum-ciclo
     * @param pCurriculumcic il curriculum-ciclo da selezionare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Professor viewCurriculumcicCoordinator(Curriculumcic pCurriculumcic) throws
            ClassNotFoundException, SQLException, IOException {
        Professor cord=new Professor();
        
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella curriculumcic
             */
           String tSql = "SELECT email, secondaryEmail, surname, name, link, department FROM "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + ","
                    + CurriculumcicManager.TABLE_PROFESSOR+","
                    + CurriculumcicManager.TABLE_ACCOUNT 
                    +"WHERE fkCurriculum= '"
                    + pCurriculumcic.getfkCurriculum()
                    + "' AND fkCycle ="
                    + pCurriculumcic.getfkCycle()
                    +"AND secondaryEmail=fkAccount AND fkAccount=fkProfessor";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            if (result.next()) {
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

            connect.commit();
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

            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella teach
             */
           String tSql = "DELETE * FROM "
                    + CurriculumcicManager.TABLE_TEACH
                    + " WHERE fkCurriculum = '"
                    + pCurriculumcic.getfkCurriculum()
                    + "' AND fkCycle = "
                    + pCurriculumcic.getfkCycle()
                    + " AND fkProfessor ='"
                    + fkProfessor
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
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
            /*
             * Prepariamo la stringa SQL per effettuare la modifica alla 
             * tabella phdstudent
             */
            String tSql = "UPDATE phdstudent SET"
                    + "fkCurriculum = '"
                    + pCurriculumcic.getfkCurriculum()
                    + "',fkCycle = "
                    + pCurriculumcic.getfkCycle()
                    + " WHERE fkAccount = '"
                    + fkPhdstudent
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
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
        try {
            ArrayList<Professor> prof = new ArrayList<>();
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
                    + curriculumcic.getfkCycle()
                    +"' AND fkCurriculum = '"
                    + curriculumcic.getfkCurriculum()
                    +"'AND secondaryEmail=fkAccount AND fkAccount=fkProfessor ";

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

        } finally {
            DBConnection.releaseConnection(connect);
        }
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
        try {
            ArrayList<PhdStudent> stud = new ArrayList<>();
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
                    + curriculumcic.getfkCycle()
                    +" AND fkCurriculum = '"
                    + curriculumcic.getfkCurriculum()
                    +"' AND secondaryEmail=fkAccount";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
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
            }

            return stud;

        } finally {
            DBConnection.releaseConnection(connect);
        }
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
                    + "fkCurriculum = null, fkCycle = null"
                    + " WHERE fkAccount = '"
                    + fkPhdstudent
                    + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

}