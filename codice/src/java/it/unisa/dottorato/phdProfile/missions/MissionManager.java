package it.unisa.dottorato.phdProfile.missions;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.MissionException;
import it.unisa.dottorato.exception.PlaceException;
import it.unisa.dottorato.exception.ReferenceAttributeException;
import it.unisa.dottorato.exception.ReferenceException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
     * @throws it.unisa.dottorato.exception.MissionException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.exception.ReferenceException 
     * @throws it.unisa.dottorato.exception.PlaceException 
     * @throws it.unisa.dottorato.exception.ReferenceAttributeException 
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws java.io.IOException 
     */
    public synchronized void insert(Mission pMission) throws SQLException,MissionException , DescriptionException , DateException , ReferenceException , PlaceException , ReferenceAttributeException , IdException , IOException {
        try (Connection connect = DBConnection.getConnection()) {

            testMission(pMission);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella mission
             */
            String tSql = "INSERT INTO "
                    + MissionManager.TABLE_MISSION
                    + " (idMission,description, startDate, endDate, reference, place, fkPhdstudent)"
                    + " VALUES ("
                    + testId(nextNumber())
                    + ",'"
                    + Utility.Replace(MissionManager.getInstance().testDescription(pMission.getDescription()))
                    + "','"
                    + MissionManager.getInstance().testStartDate(pMission.getStartDate())
                    + "','"
                    + MissionManager.getInstance().testEndDate(pMission.getEndDate())
                    + "','"
                    + Utility.Replace(MissionManager.getInstance().testReference(pMission.getReference()))
                    + "','"
                    + Utility.Replace(MissionManager.getInstance().testPlace(pMission.getPlace()))
                    + "','"
                    + MissionManager.getInstance().testfkPhdStudent(pMission.getFkPhdstudent())
                    + "')";

          
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
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.MissionException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.exception.ReferenceAttributeException 
     * @throws it.unisa.dottorato.exception.PlaceException 
     */
    public synchronized void update(int oldMissionID, Mission pMission) throws ClassNotFoundException, SQLException, IOException,IdException , MissionException , DescriptionException , DateException , ReferenceAttributeException , PlaceException, Exception {
        try (Connection connect = DBConnection.getConnection()) {

            testId(oldMissionID);
            testMission(pMission);
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella mission
             */
            String tSql = "UPDATE "
                    + MissionManager.TABLE_MISSION
                    + " set description = '"
                    + Utility.Replace(MissionManager.getInstance().testDescription(pMission.getDescription()))
                    + "', startDate = '"
                    + MissionManager.getInstance().testStartDate(pMission.getStartDate())
                    + "', endDate = '"
                    + MissionManager.getInstance().testEndDate(pMission.getEndDate())
                    + "', reference = '"
                    + Utility.Replace(MissionManager.getInstance().testReference(pMission.getReference()))
                    + "', place = '"
                    + Utility.Replace(MissionManager.getInstance().testPlace(pMission.getPlace()))
                    + "' WHERE idMission = "
                    + oldMissionID;           

            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();

            connect.commit();
        } 
    }

    /** Metodo della classe incaricato di cancellare una missione
     * 
     * @param idMission la missione da cancellare
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     * @throws it.unisa.dottorato.exception.IdException 
     */
    public synchronized void delete(int idMission) throws ClassNotFoundException, SQLException, IOException, IdException, Exception {
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
                    + " WHERE idMission = "
                    + testId(idMission);

            //Inviamo la Query al DataBase
            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();

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
     * @throws it.unisa.dottorato.exception.IdException 
     */
    public synchronized Mission getMissionById(int pMissionID) throws ClassNotFoundException, SQLException, IOException,IdException, Exception {
        Connection connect = null;
        Mission mission = new Mission();
        try {
           
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            testId(pMissionID);
            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella mission
             */
            String tSql = "SELECT * FROM "
                    + MissionManager.TABLE_MISSION
                    + " WHERE idMission = "
                    + pMissionID;

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
            }else{
                throw new Exception();
            }

        }  finally {
            DBConnection.releaseConnection(connect);
        }
        return mission;
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
    
    /** Metodo della classe per il testing dell'id; non può essere minore di 0
     * 
     * @param id id da testare
     * @return restituisce l'id se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
    public int testId(int id) throws IdException {
        if(id<0 ||id >999999){
            throw new IdException("L'id non puo' essere minore di 0 o maggiore di 6");
        }
        return id;
    } 
    
    /**Metodo della classe per il testing della descrizione; non puo' essere nulla
     * 
     * @param description descrizione da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException 
     */
    public String testDescription(String description) throws DescriptionException{
         if(description.isEmpty() || description.length()>250){
            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
    
    /**Metodo della classe per il testing della data di inizio; non puo' essere nulla
     * 
     * @param startDate data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testStartDate(Date startDate) throws DateException{
         if(startDate == null){
            
            throw new DateException("la data e' nulla"); 
        }
         return startDate;
    }
    
    /**Metodo della classe per il testing della data di inizio; non puo' essere nulla
     * 
     * @param endDate data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testEndDate(Date endDate) throws DateException{
         if(endDate == null){
            
            throw new DateException("la data e' nulla"); 
        }
         return endDate;
    }
    
    /**Metodo della classe per il testing del campo reference; non puo' avere una lunghezza maggiore di 255 caratteri
     * 
     * @param reference stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ReferenceAttributeException 
     */
    public String testReference(String reference) throws ReferenceAttributeException {
        if(reference.length()>255){
            
            throw new ReferenceAttributeException("il campo degli altri autori e' sbagliato"); 
        }
        return reference;
    }
    
    /**Metodo della classe per il testing del campo place; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 70 caratteri
     * 
     * @param place stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws PlaceException 
     */
    public String testPlace(String place) throws PlaceException {
        if(place.isEmpty() || place.length()>70){
            
            throw new PlaceException("il posto e' sbagliato"); 
        }
        return place;
    }
    
     /**Metodo della classe per il testing dell'attributo missione; non puo' essere nulla
     * 
     * @param m missione da testare
     * @return restituisce la missione se valida, lancia un'eccezione altrimenti
     * @throws MissionException 
     */
    public Mission testMission(Mission m) throws MissionException{
        if(m==null)
            throw new MissionException("errore oggetto Missione");
        return m;
    }
    
    
    /**Metodo della classe per il testing della chiave esterna per la tabella PhdStudent; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 49 caratteri
     * 
     * @param fkPhdstudent stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ReferenceException 
     */
    public String testfkPhdStudent(String fkPhdstudent) throws ReferenceException {
        if(fkPhdstudent.isEmpty() || fkPhdstudent.length()>50){
            
            throw new ReferenceException("il campo per il riferimento al PhdStudent e' sbagliato"); 
        }
        return fkPhdstudent;
    }   
    
    /**
     * Metodo della classe incaricato di calcolare il numero della prossima missione da inserire
     * nella tabella Cycle del database.
     * @return restituisce il prossimo numero
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * 
     */
    public synchronized int nextNumber() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idMission FROM "
                    + MissionManager.TABLE_MISSION
                    + " ORDER BY idMission DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idMission")+1;
            }else{
                return 1;
            }
            connect.commit();
            return c;
        } 
    }
    
}