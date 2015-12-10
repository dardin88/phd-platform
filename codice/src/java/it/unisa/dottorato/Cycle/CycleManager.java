package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.curriculumcic.Curriculumcic;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Tommaso Minichiello
 */
public class CycleManager {

    /**
     * Il nome della tabella
     */
    private static final String TABLE_CYCLE = "cycle";
    private static final String TABLE_CURRICULUMCIC = "curriculumcic";
    private static final String TABLE_TEACH = "teach";
    private static final String TABLE_PROFESSOR = "professor";
    private static final String TABLE_ACCOUNT = "account";
    
    //	 istanza della classe
    private static CycleManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CycleManager() {
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
    public static synchronized CycleManager getInstance() {
        if (instance == null) {
            instance = new CycleManager();
        }
        return instance;
    }

    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdCycle del database.
     *
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertCycle(Cycle pCycle) throws 
            ClassNotFoundException, SQLException, IOException, DescriptionException, IdException, DateException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCycle
             */
            String tSql = "INSERT INTO "
                    + CycleManager.TABLE_CYCLE
                    + " (number, description, year, fkProfessor)"
                    + " VALUES ("
                    + testNumber(pCycle.getNumber())
                    + ",'"
                    + testDescription(pCycle.getDescription())
                    + "','"
                    + testYear(pCycle.getYear())
                    + "',"
                    + Utility.emptyValue(pCycle.getFkProfessor())
                    + ")";

            
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * phdCycle del database.
     *
     * @param oldNumber 
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void updateCycle(int oldNumber, Cycle pCycle) 
            throws ClassNotFoundException, SQLException, IOException, DescriptionException, IdException, DateException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set description = '"
                    + testDescription(pCycle.getDescription())
                    + "', year = '"
                    + testYear(pCycle.getYear())
                    + "', idPhdCycle = '"
                    + testNumber(pCycle.getNumber())
                    + "', fkProfessor = "
                    + Utility.emptyValue(pCycle.getFkProfessor())
                    + " WHERE number = "
                    + testNumber(oldNumber);           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param cycle  
     * @param fkProfessor 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertCycleCoordinator(Cycle cycle, String fkProfessor) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set fkProfessor = "
                    + Utility.emptyValue(fkProfessor)
                    + " WHERE number = "
                    + cycle.getNumber();           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
      /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param cycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void deleteCycleCoordinator(Cycle cycle) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set fkProfessor = null WHERE number = "
                    + cycle.getNumber();           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
     /**
     * Metodo della classe incaricato di cercare il coordinatore di un ciclo
     *
     * @param cycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Account viewCycleCoordinator(Cycle cycle) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
           Account cord=new Account();
           connect = DBConnection.getConnection();
           /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
             String tSql = "SELECT secondaryEmail, email, surname, name, password, typeAccount, isAdministrator From "
                    + CycleManager.TABLE_CYCLE
                    +","
                    + CycleManager.TABLE_PROFESSOR
                    + ","
                    + CycleManager.TABLE_ACCOUNT
                    + " WHERE number = "
                    + cycle.getNumber()
                    +" AND fkProfessor = fkAccount AND fkAccount = secondaryEmail";   

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                cord.setSecondaryEmail(result.getString("secondaryEmail"));
                cord.setEmail(result.getString("email"));
                cord.setSurname(result.getString("surname"));
                cord.setName(result.getString("name"));
                cord.setPassword(result.getString("password"));
                cord.setTypeAccount(result.getString("typeAccount"));
                cord.setAdmin(result.getBoolean("isAdministrator"));
            }
            return cord;
        } finally {
            DBConnection.releaseConnection(connect);
        }
   
    }
    
    /**
     * Metodo della classe incaricato della cancellazopme di un'entita' nella
     * tabella phdCycle del database.
     *
     * @param number 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void deleteCycle(int number) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + CycleManager.TABLE_CYCLE
                    + " WHERE number = "
                    + number;

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca delle informazioni di un
     * ciclo contenuto nella tabella phdCycle.
     *
     * @param number 
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Cycle getCycleByNumber(int number) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Cycle cycle = new Cycle();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CYCLE
                    + " WHERE number = "
                    + number;

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                cycle.setNumber(result.getInt("idPhdCycle"));
                cycle.setDescription(result.getString("description"));
                cycle.setYear(result.getString("year"));
                cycle.setFkProfessor(result.getString("fkProfessor"));
            }

            return cycle;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Cycle> getCycleList() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Cycle> cycles = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CYCLE
                    + " ORDER BY number desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            Cycle cycle= new Cycle();
            while (result.next()) {
                cycle.setNumber(result.getInt("number"));
                cycle.setDescription(result.getString("description"));
                cycle.setYear(result.getString("year"));
                cycle.setFkProfessor(result.getString("fkProfessor"));
                cycles.add(cycle);
            }

            return cycles;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Integer> getCyclesListNumers() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Integer> cycles = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT number FROM "
                    + CycleManager.TABLE_CYCLE
                    + " ORDER BY number desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
                cycles.add(result.getInt("number"));
            }

            return cycles;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
     /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *@param number 
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<String> viewCollegeCycle(int number) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<String> prof = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
             String tSql = "SELECT fkAccount FROM "
                    + CycleManager.TABLE_TEACH
                    + " WHERE fkCycle = "
                    + number;

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
                prof.add(result.getString("fkAccount"));
            }

            return prof;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
       
    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdlass del database.
     *
     * @param pCurriculumcic
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertCurriculumcic(Curriculumcic pCurriculumcic) throws 
            ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella curriculumcic
             */
            String tSql = "INSERT INTO "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + " (fkCurriculum, fkCycle, fkProfessor)"
                    + " VALUES ('"
                    + pCurriculumcic.getfkCurriculum()
                    + "',"
                    + pCurriculumcic.getfkCycle()
                    + ",'"
                    + pCurriculumcic.getfkProfessor()
                    + "')";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

    
    /**
     * Metodo della classe incaricato della cancellazione di un'entita' nella
     * tabella curriculumcic del database.
     *
     * @param fkCurriculum
     * @param fkCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void deleteCurriculumcic(int fkCycle, String fkCurriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdClass
             */
            String tSql = "DELETE FROM "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + " WHERE fkCycle = '"
                    + fkCycle + "' AND "
                    +" fkCurriculum = '"
                    + Utility.Replace(fkCurriculum) + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

   
    /**
     * Metodo della classe incaricato della ricerca delle classi esistenti.
     *@param number 
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Curriculumcic> getCurriculumcicList(int number) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Curriculumcic> classList = new ArrayList<>();
            Curriculumcic aCurriculumcic;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella curriculumcic
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + "WHERE fkCycle ="
                    + number
                    +" ORDER BY fkCycle desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                aCurriculumcic = new Curriculumcic();
                aCurriculumcic.setfkCycle(result.getInt("fkCycle"));
                aCurriculumcic.setfkCurriculum(result.getString("fkCurriculum"));
                aCurriculumcic.setfkProfessor(result.getString("fkProfessor"));

                classList.add(aCurriculumcic);
            }

            return classList;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

     public Integer testNumber(int number) throws IdException {
        if(number <=0 || number > 999) 
            throw new IdException("il numero del ciclo è sbagliato");
        return number;
    }
    
    public String testDescription(String description) throws DescriptionException {
        if(description.isEmpty() || description.length() > 65536) 
            throw new DescriptionException("Descrizione ciclo errata.");
        return description;
    }
    
    public String testYear(String year) throws DateException {
        if(year.isEmpty() || year.length() > 4) 
            throw new DateException("Anno ciclo errato.");
        return year;
    }
}