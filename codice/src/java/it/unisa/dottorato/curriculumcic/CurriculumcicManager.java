package it.unisa.dottorato.curriculumcic;

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
public class CurriculumcicManager {

    private static final String TABLE_CURRICULUMCIC = "curriculumcic";

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
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdlass del database.
     *
     * @param pCurriculumcic
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized void insert(Curriculumcic pCurriculumcic) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella curriculumcic
             */
            String tSql = "INSERT INTO "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " (fkCurriculum, fkCycle, fkProfessor)"
                    + " VALUES ('"
                    + pCurriculumcic.getfkCurriculum()
                    + "','"
                    + pCurriculumcic.getfkCycle()
                    + "','"
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
    public synchronized void delete(int fkCycle, String fkCurriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdClass
             */
            String tSql = "DELETE FROM "
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
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
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Curriculumcic> getCurriculumcicList() throws ClassNotFoundException, SQLException, IOException {
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
                    + CurriculumcicManager.TABLE_CURRICULUMCIC
                    + " ORDER BY fkCycle desc";

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
}