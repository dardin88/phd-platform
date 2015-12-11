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


/**Classe per la gestione dei cicli
 *
 * @author Tommaso Minichiello
 */
public class CycleManager {

    /**
     * I nomi delle tabelle
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
     * @param pCycle il nuovo ciclo da inserire
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.DescriptionException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.DateException
     * 
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
     * @param oldNumber numero del ciclo da selezionare
     * @param pCycle il nuovo ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.DescriptionException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.DateException
     */
    public synchronized void updateCycle(int Number, Cycle pCycle) 
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
                    + "' WHERE number = "
                    + testNumber(Number);           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param number numero del il ciclo da selezionare
     * @param fkProfessor il coordinatore da inserire nel ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insertCycleCoordinator(int number, String fkProfessor) throws ClassNotFoundException, SQLException, IOException {
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
                    + number;           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
      /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param number numero del ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void deleteCycleCoordinator(int number) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set fkProfessor = null WHERE number = "
                    + number;           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
     /**
     * Metodo della classe incaricato di cercare il coordinatore di un ciclo
     *
     * @param cycle il ciclo da selezionare
     * @return l'account del coordinatore se esistente
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Account viewCycleCoordinator(int number) throws 
            ClassNotFoundException, SQLException, IOException, IdException {
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
                    + testNumber(number)
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
     * Metodo della classe incaricato della cancellazione di un'entita' nella
     * tabella phdCycle del database.
     *
     * @param number il numero del ciclo da cancellare
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
     * @param number il numero del ciclo da selezionare
     * @return ritorna il ciclo <code>number</code> se esiste, lancia un'eccezione
     * altrimenti
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
     * @return restituisce un array list di cicli
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
     * @return restituisce un array list dei numeri dei cicli
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
     *@param number il numero del ciclo da ricercare
     * @return restituisce un array list di professori che formano il collegio
     * dei docenti del ciclo
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
             * nella tabella teach
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
     * @param pCurriculumcic il nuovo curriculum da inserire
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
     * @param fkCurriculum il curriculum
     * @param fkCycle il ciclo
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
             * nella tabella curriculumcic
             */
            String tSql = "DELETE FROM "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + " WHERE fkCycle = "
                    + fkCycle + " AND "
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
     *@param number il numero del ciclo da selezionare
     * @return restituisce un array list dei curriculum del ciclo <code>number</code>
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
    
    /**
     * Metodo della classe incaricato di calcolare il numero del prossimo ciclo da inserire
     * nella tabella Cycle del database.
     * @return restituisce il prossimo numero
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.DescriptionException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.DateException
     * 
     */
    public synchronized int nextNumber() throws 
            ClassNotFoundException, SQLException, IOException, DescriptionException, IdException, DateException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT number FROM "
                    + CycleManager.TABLE_CYCLE
                    + "ORDER BY number DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("number")+1;
            }
            connect.commit();
            return c;
        } 
    }
    
    /** Metodo per il testing del numero di un ciclo; non può essere minore o uguale
     * di 0 e maggiore di 999
     * 
     * @param number il numero da testare
     * @return restituisce il numero se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
     public Integer testNumber(int number) throws IdException {
        if(number <=0 || number > 999) 
            throw new IdException("il numero del ciclo è sbagliato");
        return number;
    }
    
       /**Metodo per il testing della descrizione del ciclo; verifica che la stringa
     * <code>description</code> non sia vuota o non abbia una lunghezza superiore
     * ai 65535 caratteri
     * 
     * @param description descrizione del ciclo
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException 
     */
    public String testDescription(String description) throws DescriptionException {
        if(description.isEmpty() || description.length() > 65536) 
            throw new DescriptionException("Descrizione ciclo errata.");
        return description;
    }
    
    /** Metodo per il testing dell'anno di un ciclo; non può essere una stringa
     * vuota o maggiore di 4 caratteri
     * 
     * @param year la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public String testYear(String year) throws DateException {
        if(year.isEmpty() || year.length() > 4) 
            throw new DateException("Anno ciclo errato.");
        return year;
    }
}