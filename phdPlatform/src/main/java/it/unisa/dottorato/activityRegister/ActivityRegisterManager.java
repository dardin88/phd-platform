/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Liliana Annunziata
 */
public class ActivityRegisterManager {
    private static ActivityRegisterManager instance;
    private static final String TABLE_ACTIVITY = "activity";
    /**
    * Il costruttore della classe e' dichiarato privato, per evitare
    * l'istanziazione di oggetti della classe .
    */
    private ActivityRegisterManager(){
        super();
    }
    /**
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static synchronized ActivityRegisterManager getInstance() {

        if (instance == null) {
            instance = new ActivityRegisterManager();
        }
        return instance;

    }
    /**
     * Metodo che inserisce un'attività nel regitro
     * @param activity attività da inserire
     * @throws SQLException
     * @throws IOException 
     */
    public void insertActivity(Activity activity) throws SQLException, IOException {
       Connection connect = null;
       try{
              connect = DBConnection.getConnection();
           //preparazione query per inserimento
               String stingSQL = "INSERT INTO "
                    + ActivityRegisterManager.TABLE_ACTIVITY 
                    + "(name, description, startDateTime, endDateTime, totalTime, typology, fkPhdStudent)"
                    + "VALUES ("
                    + "'"+activity.getName()+"'," 
                    + "'"+activity.getDescription()+"'," 
                    + "'"+activity.getStartDateTime()+"'," 
                    + "'"+activity.getEndDateTime()+"'," 
                    + "'"+calculateTotTime(activity.getStartDateTime(),activity.getEndDateTime())+"'," 
                    + "'"+activity.getTypology()+"'," 
                    + "'"+activity.getFkPhdStudent()+"'" 
                    + ")";
            
            //System.out.println(stingSQL);
            //esegue query
            Utility.executeOperation(connect, stingSQL);

            connect.commit();
        }finally {
           DBConnection.releaseConnection(connect);
        }
   }
/**
 * metodo che restituisce il registro delle attività di uno studente
 * @param idStudent id dello studente 
 * @return lista delle attività del registro
 * @throws SQLException
 * @throws IOException 
 */
    public ArrayList<Activity> getActivityRegisterOf(String idStudent) throws SQLException, IOException {
       
       Connection connect = null;
       ArrayList<Activity> activityList = new ArrayList<Activity>();
       
       try{
              connect = DBConnection.getConnection();
              //Preparazione query per recupero della lista delle attività di un utente
              String stringSQL = "SELECT * FROM " 
                    + ActivityRegisterManager.TABLE_ACTIVITY 
                    + " WHERE fkPhdStudent='"+idStudent+"'";            
            //System.out.println(stringSQL);
           
            //esegue query
            ResultSet result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Activity activity = new Activity();
                activity.setIdActivity(result.getInt("idActivity"));
                activity.setName(result.getString("name"));
                activity.setDescription(result.getString("description"));
                activity.setStartDateTime(result.getTimestamp("startDateTime"));
                activity.setEndDateTime(result.getTimestamp("endDateTime"));
                activity.setTotalTime(result.getFloat("totalTime"));
                activity.setTypology(result.getString("typology"));
                activity.setFkPhdStudent(result.getString("fkPhdStudent"));

                activityList.add(activity);
            }
           
          }finally {
           DBConnection.releaseConnection(connect);
        } 
       return activityList;
    }

    /**
     * aggiorna i campi dell'attività che l'utente desidera modificato
     * @param oldActivityID id dell'attività da modificare
     * @param newActivity attività con i campi modificati
     * @throws SQLException
     * @throws IOException 
     */
    public void updateActivity(int oldActivityID, Activity newActivity) throws SQLException, IOException{
        Connection connect = null;
            try{
              connect = DBConnection.getConnection();
           
            //preparazione query per aggiornamento
            String stringSQL = "UPDATE " 
                    + ActivityRegisterManager.TABLE_ACTIVITY 
                    + " SET "
                     + "name ='" + newActivity.getName() +"',"
                      + "description ='" + newActivity.getDescription() +"',"
                       + "startDateTime ='" + newActivity.getStartDateTime() +"',"
                        + "endDateTime ='" + newActivity.getEndDateTime() +"'," 
                        + "totalTime ='" + calculateTotTime(newActivity.getStartDateTime(),newActivity.getEndDateTime())+"',"
                         + "typology ='" + newActivity.getTypology() +"' "
                         + "WHERE idActivity = " + oldActivityID;
            //System.out.println(stringSQL);

            Utility.executeOperation(connect, stringSQL);

            connect.commit();
            }finally {
                DBConnection.releaseConnection(connect);
        }
    }
    /**
     * Cancella un'attvità dal registro dell'utente
     * @param idActivity id dell'attività da modificare
     * @throws SQLException
     * @throws Exception lancia un'eccezione se non viene cancellata nessuna attività
     */
    public void deleteActivity(int idActivity) throws SQLException, Exception{
        Connection connect = null;
            try{
                connect = DBConnection.getConnection();

                //Preparazione query per la cancellazione
                String stringSQL = "DELETE FROM " 
                        + ActivityRegisterManager.TABLE_ACTIVITY 
                        + " WHERE idActivity = " + idActivity;
               //System.out.println(stringSQL);

                if(Utility.executeOperation(connect, stringSQL) == 0)
                    throw new Exception();
               
                connect.commit();
            }finally {
                DBConnection.releaseConnection(connect);
        }
    }
    /**
     * Calcolo delle ore dedicate ad una attivita'
     * @param startDateTime 
     * @param endDateTime 
     * @return durate del'attività
     */
    private float calculateTotTime(Timestamp startDateTime, Timestamp endDateTime) {
        long diffInMillies = endDateTime.getTime() - startDateTime.getTime();
        return TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }  
 }
