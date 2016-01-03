package it.unisa.dottorato.Curriculum;

import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.NameException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe per la gestione dei curriculum
 *
 * @author Tommaso Minichiello
 */
public class CurriculumManager {

    /**
     * I nomi delle tabelle
     */
    private static final String TABLE_CURRICULUM = "curriculum";

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
     * @param pCurriculum il nuovo curriculum
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.DescriptionException
     * @throws it.unisa.dottorato.exception.NameException
     * @throws it.unisa.dottorato.Curriculum.CurriculumException
     */
    public synchronized void insert(Curriculum pCurriculum)
            throws ClassNotFoundException, SQLException, IOException, DescriptionException, NameException, CurriculumException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            testCurriculum(pCurriculum);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCurriculum
             */
            String tSql = "INSERT INTO "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " (name, description)"
                    + " VALUES ('"
                    + testName(pCurriculum.getName())
                    + "','"
                    + testDescription(pCurriculum.getDescription())
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
     * @param oldNameCurriculum nome del curriculum da modificare
     * @param pCurriculum il nuovo curriculum
     * @throws java.sql.SQLException
     * @throws it.unisa.dottorato.Curriculum.CurriculumException
     * @throws it.unisa.dottorato.exception.NameException
     * @throws it.unisa.dottorato.exception.DescriptionException
     */
    public synchronized void update(String oldNameCurriculum, Curriculum pCurriculum) throws SQLException, CurriculumException, NameException, DescriptionException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            testCurriculum(pCurriculum);
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "UPDATE "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " set name = '"
                    + testName(pCurriculum.getName())
                    + "', description = '"
                    + testDescription(pCurriculum.getDescription())
                    + "' WHERE name = '"
                    + testName(oldNameCurriculum) + "'";
            System.out.println(tSql);
            //Inviamo la Query al DataBase
            int res = Utility.executeOperation(connect, tSql);
            if (res==0){
                throw new Exception();
            }

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della cancellazione di un'entita' nella
     * tabella Curriculum del database.
     *
     * @param CurriculumName nome del curriculum da cancellare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.NameException
     */
    public synchronized void delete(String CurriculumName)
            throws ClassNotFoundException, SQLException, IOException, NameException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "DELETE FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " WHERE name = '"
                    + testName(CurriculumName) + "'";

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql)==0){
                throw new Exception();
            }

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato di ottenere un array list di nomi dei
     * curriculum esistenti.
     *
     * @return lista dei nomi dei curriculum esistenti
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Curriculum> getCurriculumList() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Curriculum> curriculum = new ArrayList<>();

            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "SELECT * FROM "
                    + CurriculumManager.TABLE_CURRICULUM;

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Curriculum c = new Curriculum();
                c.setName(result.getString("name"));
                c.setDescription(result.getString("description"));
                curriculum.add(c);
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
     * @param CurriculumName il nome del curriculum da ricercare
     * @return restituisce il curriculum se trovato, altrimenti lancia un
     * eccezione
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized Curriculum getCurriculumByName(String CurriculumName)
            throws ClassNotFoundException, SQLException, IOException, Exception {
        Connection connect = null;
        Curriculum curriculum = new Curriculum();
        try {

            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCurriculum
             */
            String tSql = "SELECT * FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " WHERE name = '"
                    + testName(CurriculumName) + "'";
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                curriculum.setDescription(result.getString("description"));
                curriculum.setName(result.getString("name"));
            }
            
            if (curriculum.getName().equals("") && curriculum.getDescription().equals("")){
                throw new Exception();
            }

            return curriculum;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    public void testCurriculum(Curriculum c) throws CurriculumException {
        if (c == null) {
            throw new CurriculumException();
        }
    }

    /**
     * Metodo per il testing del nome del curriculum; verifica che la stringa
     * <code>name</code> non sia vuota o non abbia una lunghezza superiore ai 99
     * caratteri
     *
     * @param name nome del curriculum
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws NameException
     */
    public String testName(String name) throws NameException {
        if (name.isEmpty() || (name.length() > 100)) {
            throw new NameException();
        }
        return name;
    }

    /**
     * Metodo per il testing della descrizione del curriculum; verifica che la
     * stringa <code>description</code> non sia vuota o non abbia una lunghezza
     * superiore ai 65535 caratteri
     *
     * @param description descrizione del curriculum
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException
     */
    public String testDescription(String description) throws DescriptionException {
        if (description.isEmpty() || description.length() > 65536) {
            throw new DescriptionException("Descrizione Curriculum errata.");
        }
        return description;
    }

    /**
     * Metodo della classe incaricato di verificare l'esistenza di un curriculum
     *
     *
     * @param c il curriculum da ricercare
     * @return restituisce true se il curriculum esiste, false altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public boolean existCurriculum(Curriculum c) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per ricercare il curriculum c
             * nella tabella Curriculum
             */
            String tSql = "SELECT * FROM "
                    + CurriculumManager.TABLE_CURRICULUM
                    + " WHERE name = '"
                    + c.getName() + "'";
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            return result.next();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
}
