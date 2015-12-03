package it.unisa.dottorato.Curriculum;

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
public class CurriculumManager {

    /**
     * I nomi delle tabelle
     */
    private static final String TABLE_CURRICULUM = "curriculum";
    private static final String TABLE_CURRICULUMCIC = "curriculumcic";


    //	 istanza della classe
    private static CurriculumManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CurriculumManager() {
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
    public static synchronized CurriculumManager getInstance() {
        if (instance == null) {
            instance = new CurriculumManager();
        }
        return instance;
    }

    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdCurriculum del database.
     *
     * @param pCurriculum
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insert(Curriculum pCurriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCurriculum
             */
            String tSql = "INSERT INTO "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " (name, description)"
                    + " VALUES ('"
                    + Utility.Replace(pCurriculum.getName())
                    + "','"
                    + Utility.Replace(pCurriculum.getDescription())
                    + "')";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * phdCurriculum del database.
     *
     * @param oldNameCurriculum
     * @param pCurriculum
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void update(String oldNameCurriculum, Curriculum pCurriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " set name = '"
                    + Utility.Replace(pCurriculum.getName())
                    + "', description = '"
                    + Utility.Replace(pCurriculum.getDescription())
                    + "' WHERE name = '"
                    + oldNameCurriculum + "'";
            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della cancellazopme di un'entita' nella
     * tabella Curriculum del database.
     *
     * @param CurriculumName 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void delete(String CurriculumName) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " WHERE name = '"
                    + Utility.Replace(CurriculumName) + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca delle informazioni di un
     * curriculum relativo ad un ciclo.
     *
     * @param number 
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<String> getCurriculumNameByCycle(int number) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<String> curriculum = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT name FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + "  JOIN "
                    + CurriculumManager.TABLE_CURRICULUMCIC
                    + " ON "
                    + CurriculumManager.TABLE_CURRICULUM
                    + ".name = "
                    + CurriculumManager.TABLE_CURRICULUMCIC
                    + ".fkCurriculum "
                    + " WHERE fkCycle = "
                    + number;

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                curriculum.add(result.getString("name"));
            }

            return curriculum;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
   
    /**
     * Metodo della classe incaricato della ricerca di un curriculum esistente.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<String> getCurriculumNames() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<String> curriculum = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "SELECT name FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " ORDER BY name desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                curriculum.add(result.getString("name"));
            }

            return curriculum;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca delle informazioni di un
     * curriculum contenuto nella tabella Curriculum.
     *
     * @param CurriculumName 
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Curriculum getCurriculumByName(String CurriculumName) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Curriculum curriculum = new Curriculum();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "SELECT * FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " WHERE name = '"
                    + CurriculumName + "'";
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                curriculum.setDescription(result.getString("description"));
                curriculum.setName(result.getString("name"));
            }

            return curriculum;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
}
