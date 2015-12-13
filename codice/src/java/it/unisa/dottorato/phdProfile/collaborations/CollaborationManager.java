
package it.unisa.dottorato.phdProfile.collaborations;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Classe per la gestione delle collaborazioni
 *
 * @author gemmacatolino
 */
public class CollaborationManager {
    
    /**
     * Il nome della tabella
     */
    private static final String TABLE_COLLABORATION = "collaboration";
    
    //	 istanza della classe
    private static CollaborationManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CollaborationManager() {
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
    public static synchronized CollaborationManager getInstance() {
        if (instance == null) {
            instance = new CollaborationManager();
        }
        return instance;
    }
   
    /** Metodo della classe incaricato di inserire una nuova collaborazione
     * 
     * @param pCollaboration la nuova collaborazione
     * @throws SQLException 
     */
    public synchronized void insert(Collaboration pCollaboration) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella collaboration
             */
            String tSql = "INSERT INTO "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " (description, startDate, endDate, istitution, fkPhdstudent)"
                    + " VALUES ('"
                    + Utility.Replace(pCollaboration.getDescription())
                    + "','"
                    + pCollaboration.getStartDate()
                    + "','"
                    + pCollaboration.getEndDate() 
                    + "','"
                    + Utility.Replace(pCollaboration.getIstitution())
                    + "','"
                    + pCollaboration.getFkPhdstudent()
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
   
    /** Metodo della classe incaricato di aggiornare una collaborazione
     * 
     * @param oldCollaborationID l'id della collaborazione da aggiornare
     * @param pCollaboration la collaborazione aggiornata
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void update(int oldCollaborationID, Collaboration pCollaboration) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella collaboration
             */
            String tSql = "UPDATE "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " set description = '"
                    + Utility.Replace(pCollaboration.getDescription())
                    + "', startDate = '"
                    + pCollaboration.getStartDate()
                    + "', endDate = '"
                    + pCollaboration.getEndDate()
                    + "', istitution = '"
                    + Utility.Replace(pCollaboration.getIstitution())
                    + "' WHERE idCollaboration = "
                    + oldCollaborationID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    /** Metodo della classe incaricato di cancellare una collaborazione
     * 
     * @param idCollaboration l'id della collaborazione da cancellare
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void delete(String idCollaboration) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per cancellare un record 
             * nella tabella collaboration
             */
            String tSql = "DELETE FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE idCollaboration = '"
                    + idCollaboration + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di ricercare una collaborazione dato
     * il suo id
     * 
     * @param pCollaborationID l'id della collaborazione da ricercare
     * @return restituisce la collaborazione se trovata, lancia un'eccezione altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized Collaboration getCollaborationById(int pCollaborationID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Collaboration collaboration = new Collaboration();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella collaboration
             */
            String tSql = "SELECT * FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE idCollaboration = '"
                    + pCollaborationID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                collaboration.setIdCollaboration(result.getInt("idCollaboration"));
                collaboration.setDescription(result.getString("description"));
                collaboration.setStartDate(result.getDate("startDate"));
                collaboration.setEndDate(result.getDate("endDate"));
                collaboration.setIstitution(result.getString("istitution"));
                collaboration.setFkPhdstudent(result.getString("fkPhdstudent"));
            }

            return collaboration;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di ricercare tutte le collaborazioni
     * dato un dottorando
     * 
     * @param phdStudent dottorando di cui si vogliono conoscere le collaborazioni
     * @return restituisce una lista di tutte le collaborazioni del dottorando,
     * lancia un'eccezione altrimenti
     * @throws SQLException 
     */
    public synchronized List<Collaboration> getAllCollaborationOf(PhdStudent phdStudent) throws SQLException { //da verificare
        List<Collaboration> collaborations = new ArrayList<Collaboration>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella collaboration
             */
            String tSql = "SELECT * FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE fkPhdstudent = '"
                    + phdStudent.getfkAccount()+ "'"; //da verificare

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Collaboration collaboration = new Collaboration();
                
                collaboration.setIdCollaboration(result.getInt("idCollaboration"));
                collaboration.setDescription(result.getString("description"));
                collaboration.setStartDate(result.getDate("startDate"));
                collaboration.setEndDate(result.getDate("endDate"));
                collaboration.setIstitution(result.getString("istitution"));
                collaboration.setFkPhdstudent(result.getString("fkPhdstudent"));
                
                collaborations.add(collaboration);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return collaborations;
    }
}