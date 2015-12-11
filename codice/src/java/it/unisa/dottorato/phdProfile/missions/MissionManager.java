package it.unisa.dottorato.phdProfile.missions;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Classe della gestione delle missioni
 *
 * @author gemmacatolino
 */
public class MissionManager {
    
    /**
     * Il nome della tabella
     */
    private static final String TABLE_MISSION = "mission";
    
    //	 istanza della classe
    private static MissionManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private MissionManager() {
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
    public static synchronized MissionManager getInstance() {
        if (instance == null) {
            instance = new MissionManager();
        }
        return instance;
    }
  
    /** Metodo della classe incaricato di inserire una nuova missione
     * 
     * @param pMission la nuova missione da inserire
     * @throws SQLException 
     */
    public synchronized void insert(Mission pMission) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella mission
             */
            String tSql = "INSERT INTO "
                    + MissionManager.TABLE_MISSION
                    + " (description, startDate, endDate, reference, place, fkPhdstudent)"
                    + " VALUES ('"
                    + Utility.Replace(pMission.getDescription())
                    + "','"
                    + pMission.getStartDate()
                    + "','"
                    + pMission.getEndDate() 
                    + "','"
                    + Utility.Replace(pMission.getReference())
                    + "','"
                    + Utility.Replace(pMission.getPlace())
                    + "','"
                    + pMission.getFkPhdstudent()
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
  
    /** Metodo della classe incaricato di aggiornare una missione
     * 
     * @param oldMissionID l'id della missione da aggiornare
     * @param pMission la missione aggiornata
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void update(int oldMissionID, Mission pMission) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella mission
             */
            String tSql = "UPDATE "
                    + MissionManager.TABLE_MISSION
                    + " set description = '"
                    + Utility.Replace(pMission.getDescription())
                    + "', startDate = '"
                    + pMission.getStartDate()
                    + "', endDate = '"
                    + pMission.getEndDate()
                    + "', reference = '"
                    + Utility.Replace(pMission.getReference())
                    + "', place = '"
                    + Utility.Replace(pMission.getPlace())
                    + "' WHERE idMission = "
                    + oldMissionID + "";           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }

    /** Metodo della classe incaricato di cancellare una missione
     * 
     * @param idMission la missione da cancellare
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void delete(String idMission) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per rimuovere un record 
             * nella tabella mission
             */
            String tSql = "DELETE FROM "
                    + MissionManager.TABLE_MISSION
                    + " WHERE idMission = '"
                    + idMission + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di ricercare una missione dato il suo id
     * 
     * @param pMissionID id della missione da ricercare
     * @return restituisce la missione trovata, lancia un'eccezione altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized Mission getMissionById(int pMissionID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Mission mission = new Mission();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella mission
             */
            String tSql = "SELECT * FROM "
                    + MissionManager.TABLE_MISSION
                    + " WHERE idMission = '"
                    + pMissionID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                mission.setIdMission(result.getInt("idMission"));
                mission.setDescription(result.getString("description"));
                mission.setStartDate(result.getDate("startDate"));
                mission.setEndDate(result.getDate("endDate"));
                mission.setReference(result.getString("reference"));
                mission.setPlace(result.getString("place"));
                mission.setFkPhdstudent(result.getString("fkPhdstudent"));
            }

            return mission;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
   
    /** Metodo della classe incaricato di ricercare tutte le missioni di un dottorando
     * 
     * @param phdStudent il dottorando su cui effettuare la ricerca
     * @return restituisce una lista di missioni del dottorando, lancia un'eccezione altrimenti
     * @throws SQLException 
     */
    public synchronized List<Mission> getAllMissionsOf(PhdStudent phdStudent) throws SQLException {
        List<Mission> missions = new ArrayList<Mission>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella mission
             */
            String tSql = "SELECT * FROM "
                    + MissionManager.TABLE_MISSION
                    + " WHERE fkPhdstudent = '"
                    + phdStudent.getfkAccount()+ "'"; // da verificare

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Mission mission = new Mission();
                
                mission.setIdMission(result.getInt("idMission"));
                mission.setDescription(result.getString("description"));
                mission.setStartDate(result.getDate("startDate"));
                mission.setEndDate(result.getDate("endDate"));
                mission.setReference(result.getString("reference"));
                mission.setPlace(result.getString("place"));
                mission.setFkPhdstudent(result.getString("fkPhdstudent"));
                
                missions.add(mission);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return missions;
    }
}