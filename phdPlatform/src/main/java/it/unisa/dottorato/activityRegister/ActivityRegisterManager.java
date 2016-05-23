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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Liliana Annunziata
 */
public class ActivityRegisterManager {
    private static ActivityRegisterManager instance;
    private static final String TABLE_ACTIVITY = "activity";
    private static final String TABLE_LESSON="lesson";
    private static final String TABLE_PRESENCE="presence";
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
            System.out.println(stringSQL);
           
            //esegue query
            ResultSet result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Activity activity = new Activity();
                activity.setIdActivity(result.getInt("idActivity"));
                activity.setName(result.getString("name"));
                activity.setDescription(result.getString("description"));
                activity.setStartDateTime(result.getString("startDateTime"));
                activity.setEndDateTime(result.getString("endDateTime"));
                activity.setTotalTime(result.getFloat("totalTime"));
                activity.setTypology(result.getString("typology"));
                activity.setFkPhdStudent(result.getString("fkPhdStudent"));

                activityList.add(activity);
            }
           
            stringSQL = "SELECT lesson.idLesson, lesson.name, lesson.desription, lesson.date, lesson.startTime, lesson.endTime, presence.fkPhdStudent"
                      + " FROM "+ ActivityRegisterManager.TABLE_LESSON+ "," + ActivityRegisterManager.TABLE_PRESENCE
                      + " WHERE fkPhdStudent='"+idStudent+"'"
                      + " AND isPresent = 1 AND fkLesson = lesson.idLesson" ;                                
            System.out.println(stringSQL);

            //esegue query
            result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Activity lesson = new Activity();
                lesson.setIdActivity(Integer.parseInt(result.getString("idLesson")));
                lesson.setName(result.getString("name"));
                lesson.setDescription(result.getString("desription"));
                
               // checkTime(startTime, endTime);
               // Date startDateTime =convertStringToDate(result.getDate("date"),result.getString("startTime"));
              //  String tmp = "12AM";
                //String startTime = result.getString("startTime").contains(tmp.replace(": ","")  ? "12:00 AM" : convertTo24(result.getString("startTime"));
         //       String endTime = result.getString("endTime").contains(tmp)  ? tmp: convertTo24(result.getString("endTime"));
                String startDateTime = result.getString("date") +" "+convertTo24(result.getString("startTime"));
                String endDateTime = result.getString("date") +" "+convertTo24(result.getString("endTime"));
                lesson.setStartDateTime(startDateTime);
                lesson.setEndDateTime(endDateTime);
               System.out.println("get"+startDateTime +" "+ endDateTime+" "+", "+result.getString("endTime")+' '+calculateTotTime(startDateTime,endDateTime));
                lesson.setTotalTime(calculateTotTime(startDateTime,endDateTime));
               
                lesson.setTypology("Lezione");
                lesson.setFkPhdStudent(result.getString("fkPhdStudent"));
               
                activityList.add(lesson);    
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
     * @param fkPhdStudent
     * @throws SQLException
     * @throws IOException 
     */
    public void updateActivity(int oldActivityID, Activity newActivity, String fkPhdStudent) throws SQLException, IOException{
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
                         + "WHERE idActivity = " + oldActivityID + " AND fkPhdStudent='"+fkPhdStudent+"'";
            System.out.println(stringSQL);

            Utility.executeOperation(connect, stringSQL);

            connect.commit();
            }finally {
                DBConnection.releaseConnection(connect);
        }
    }
    /**
     * Cancella un'attvità dal registro dell'utente
     * @param idActivity id dell'attività da modificare
     * @param fkPhdStudent
     * @throws SQLException
     * @throws Exception lancia un'eccezione se non viene cancellata nessuna attività
     */
    public void deleteActivity(int idActivity, String fkPhdStudent) throws SQLException, Exception{
        Connection connect = null;
            try{
                connect = DBConnection.getConnection();

                //Preparazione query per la cancellazione
                String stringSQL = "DELETE FROM " 
                        + ActivityRegisterManager.TABLE_ACTIVITY 
                        + " WHERE idActivity = " + idActivity + " AND fkPhdStudent='"+fkPhdStudent+"'";
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
    private float calculateTotTime(String startDateTime, String endDateTime) {
        try {
           // System.out.println(startDateTime);
            Date startDateParse = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDateTime);
            Date endDateParse = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDateTime);
            long diffInMillies = endDateParse.getTime() - startDateParse.getTime();
            return TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
        } catch (ParseException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    } 

    /**
     * data una date e le ore restituisce il Date
     * @param date
     * @param time
     * @return Date
    */
    private String convertTo24(String date) {
         if(date.contains("AM")){
            date = date.replace("AM", "");
            String[] timeArr = date.split(":");
            if(timeArr[0].equals("12")){
                timeArr[0] = "12";
            }
            date = timeArr[0]+":"+timeArr[1];
        }
        else if(date.contains("PM")){
            date = date.replace("PM", "");
            String[] timeArr = date.split(":");
            if(!timeArr[0].equals("12")){
                timeArr[0] = Integer.toString(Integer.parseInt(timeArr[0])+12);
            }
            date = timeArr[0]+":"+timeArr[1];
        }   
         return date;
   }
 
}