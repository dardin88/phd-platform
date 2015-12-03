package it.unisa.dottorato.phdCycle;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Elisa D'Eugenio
 */
public class CycleManager {

    /**
     * Il nome della tabella
     */
    private static final String TABLE_CYCLE = "cycle";
    
    

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
    public synchronized void insert(Cycle pCycle) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCycle
             */
            String tSql = "INSERT INTO "
                    + CycleManager.TABLE_CYCLE
                    + " (number, description, year, fkProfessor)"
                    + " VALUES ('"
                    + pCycle.getNumber()
                    + "','"
                    + Utility.Replace(pCycle.getDescription())
                    + "','"
                    + pCycle.getYear()
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
     * @param oldIdPhdCycle
     * @param pCycle
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void update(int oldIdPhdCycle, Cycle pCycle) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set description = '"
                    + Utility.Replace(pCycle.getDescription())
                    + "', year = '"
                    + pCycle.getYear()
                    + "', idPhdCycle = '"
                    + pCycle.getNumber()
                    + "', fkProfessor = "
                    + Utility.emptyValue(pCycle.getFkProfessor())
                    + " WHERE number = "
                    + oldIdPhdCycle;           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
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
    public synchronized void delete(int number) throws ClassNotFoundException, SQLException, IOException {
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
     * @param idPhdCycle
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
    public synchronized ArrayList<Integer> getCycleList() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Integer> cycles = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT IdPhdCycle FROM "
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

}