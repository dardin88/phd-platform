/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.phdCourse.Seminar;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private static final String TABLE_TYPOLOGY = "typology";
 
    /**
    * Il costruttore della classe e' dichiarato privato, per evitare
    * l'istanziazione di oggetti della classe.
    */
    private ActivityRegisterManager(){
        super();
    }
    
    /**
     * Metodo viene chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     * @return L'istanza della classe
     */
    public static synchronized ActivityRegisterManager getInstance() {
        if (instance == null) {
            instance = new ActivityRegisterManager();
        }
        return instance;
    }
    /**
     * Inserimento di un'attività nel registro
     * @param activity attività da inserire
     * @param idSeminar
     * @throws SQLException
     * @throws IOException 
     */
    public void insertActivity(Activity activity,String idSeminar) throws SQLException, IOException {
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
            
            //esegue query
            Utility.executeOperation(connect, stingSQL);

            if(!idSeminar.equalsIgnoreCase("N/A")){
                stingSQL = "INSERT INTO seminar_activity(fkActivity,fkSeminar) "
                        + "SELECT MAX(idActivity),"+idSeminar+" FROM "+ ActivityRegisterManager.TABLE_ACTIVITY;
            
            //esegue query
            Utility.executeOperation(connect, stingSQL);
          
            }
            connect.commit();
        }finally {
           DBConnection.releaseConnection(connect);
        }
   }
/**
 * Recupera il registro delle attività di uno studente
 * @param idStudent id dello studente 
     * @param annoInizio 
 * @return lista delle attività del registro
 * @throws SQLException
 * @throws IOException 
 */
    public ArrayList<Activity> getActivityRegisterOf(String idStudent,String annoInizio) throws SQLException, IOException {
       
       Connection connect = null;
       ArrayList<Activity> activityList = new ArrayList<Activity>();
       
       try{
              connect = DBConnection.getConnection();
              //Preparazione query per recupero della lista delle attività di un utente
              
              String stringSQL = "SELECT * FROM " 
                    + ActivityRegisterManager.TABLE_ACTIVITY 
                    + " WHERE fkPhdStudent='"+idStudent+"'"
                    + "AND "
                    + "startDateTime >= '"+datetimeFormat(annoInizio)+"' " 
                    + "AND " 
                    + "endDateTime <= '" +datetimeFormat(endYear(annoInizio))+ "' ";            
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
                    + " AND isPresent = 1 AND fkLesson = lesson.idLesson "
                    + "AND "
                    + "lesson.date >= '"+dateFormat(annoInizio)+"' " 
                    + "AND " 
                    + "lesson.date <= '" +dateFormat(endYear(annoInizio))+ "' ";                                 
            System.out.println(stringSQL);

            //esegue query
            result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Activity lesson = new Activity();
                lesson.setIdActivity(Integer.parseInt(result.getString("idLesson")));
                lesson.setName(result.getString("name"));
                lesson.setDescription(result.getString("desription"));
                
                String startDateTime = result.getString("date") +" "+convertTo24(result.getString("startTime"));
                String endDateTime = result.getString("date") +" "+convertTo24(result.getString("endTime"));
                
                lesson.setStartDateTime(startDateTime);
                lesson.setEndDateTime(endDateTime);
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
     * Aggiorna le attività del registro
     * @param oldActivityID id dell'attività da modificare
     * @param newActivity attività con i campi modificati
     * @param fkPhdStudent id dello studente
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

                if(Utility.executeOperation(connect, stringSQL) == 0)
                    throw new Exception();
               
                connect.commit();
            }finally {
                DBConnection.releaseConnection(connect);
        }
    }
    /**
     * recupera tutti seminari seguiti da uno studente e che quindi appartengono al registro delle attività
     * @param fkPhdStudent
     * @param annoInizio
     * @return 
     */
    public ArrayList<Seminar> getSeminarActivitiesByStudent(String fkPhdStudent, String annoInizio){
          
       Connection connect = null;
       ArrayList<Seminar> seminarList = new ArrayList<Seminar>();
       
       try{
              connect = DBConnection.getConnection();
              //Preparazione query per recupero della lista delle attività di un utente
              String stringSQL ="SELECT seminar.idSeminar, seminar.name ,seminar.date,seminar.startTime,seminar.endTime,seminar.desription,seminar.place, seminar.namespeacker, seminar.fkCourse "
                                + "FROM activity, seminar, seminar_activity " 
                                + "where seminar.idSeminar=seminar_activity.fkSeminar " 
                                + "AND " 
                                + "seminar_activity.fkActivity = activity.idActivity " 
                                + "AND "
                                + "activity.fkPhdStudent='"+fkPhdStudent+"'"
                                + "AND "
                                + "seminar.date >= '"+dateFormat(annoInizio)+"' " 
                                + "AND " 
                                + "seminar.date <= '" +dateFormat(endYear(annoInizio))+ "' ";            
           System.out.println(stringSQL);
            //esegue query
            ResultSet result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Seminar seminar = new Seminar();
                seminar.setIdSeminar(result.getInt("idSeminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(result.getString("startTime"));
                seminar.setEndTime(result.getString("endTime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("namespeacker"));
                seminar.setDescription(result.getString("desription"));
                seminar.setPlace(result.getString("place"));
                seminar.setFK_course(result.getInt("fkCourse"));

                
                seminarList.add(seminar);    
            }
          } catch (SQLException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
          }finally {
           DBConnection.releaseConnection(connect);
        } 
       return seminarList;
    }
    
    
/**
 * recupera tutti i corsi relativi ai seminari seguiti da uno studente e che quindi appartengono al registro delle attività
 * @param fkPhdStudent
 * @param annoInizio
 * @return 
 */    
    public ArrayList<Course> getSeminarCoursesOfStudent(String fkPhdStudent,String annoInizio){
          
       Connection connect = null;
       ArrayList<Course> courseList = new ArrayList<Course>();
       
       try{
              connect = DBConnection.getConnection();
              //Preparazione query per recupero della lista delle attività di un utente
              String stringSQL ="SELECT course.name, course.idCourse, course.fkCurriculum, course.fkCycle,course.description, course.startDate, course.endDate "
                       + "FROM activity, seminar, seminar_activity,course, lesson " 
                       + "WHERE " 
                       + "activity.fkPhdStudent='"+fkPhdStudent+"' " 
                       + "AND " 
                       + "seminar.idSeminar=seminar_activity.fkSeminar " 
                       + "AND " 
                       + "seminar_activity.fkActivity = activity.idActivity " 
                       + "AND " 
                       + "lesson.idLesson=seminar_activity.fkSeminar " 
                       + "AND " 
                       + "lesson.fkCourse=course.idCourse " 
                       + "AND "
                       + "course.startDate >= '"+dateFormat(annoInizio)+"' " 
                       + "AND " 
                       + "course.endDate   <= '" +dateFormat(endYear(annoInizio))+ "' "
                       + "group by course.idCourse\n";
           System.out.println(stringSQL);
            //esegue query
            ResultSet result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                
                Course course = new Course();
                course.setIdCourse(result.getInt("idCourse"));
                course.setName(result.getString("name"));
                course.setFkCurriculum(result.getString("fkCurriculum"));
                course.setFkCycle(result.getInt("fkCycle"));
                course.setDescription(result.getString("description"));
                course.setStartDate(result.getDate("startDate"));
                course.setEndDate(result.getDate("endDate"));
               
                courseList.add(course);    
            }
          } catch (SQLException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
          }finally {
           DBConnection.releaseConnection(connect);
        } 
       return courseList;
    } 
     /**
     * Calcolo dei minuti dedicati ad una attivita'
     * @param startDateTime 
     * @param endDateTime 
     * @return durate del'attività
     */
    private float calculateTotTime(String startDateTime, String endDateTime) {
        try {
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
     * Converte una data dal formato AM-PM in formato 24 ore
     * @param date in formato AM-PM
     * @return stringa della data in formato 24 ore
     */
    private String convertTo24(String date) {
         if(date.contains("AM")){
            date = date.replace("AM", "");
            String[] timeArr = date.split(":");
            if(timeArr[0].equals("12")){
                timeArr[0] = "00";
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
 
    /**
     * Recupera dal database le diverse tipologie delle attività
     * @return insieme di tipologie
     * @throws SQLException 
     */
    public ArrayList<Typology> getTypology() throws SQLException{
        Connection connect = null;
        ArrayList<Typology> typologyList = new ArrayList<Typology>();
       
        try{
            connect = DBConnection.getConnection();
            //Preparazione query per recupero della lista delle tipologie
            String stringSQL = "SELECT idTypology, name FROM " 
                    + ActivityRegisterManager.TABLE_TYPOLOGY;            
           
            //esegue query
            ResultSet result = Utility.queryOperation(connect, stringSQL);
           
            while (result.next()) {
                Typology typology = new Typology();
                typology.setId(Integer.parseInt(result.getString("idTypology")));
                typology.setName(result.getString("name"));
                
                typologyList.add(typology);
            }
        }finally {
           DBConnection.releaseConnection(connect);
        } 
       return typologyList;
    }
    
    private Date convertStringToDate(String annoInizio){
        try {
            Date startDateParse = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(annoInizio+"-09-01 00:00");
            return startDateParse;
        } catch (ParseException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * aggiunge un anno all'anno dato come parametro
     * @param dataInizio
     * @return 
     */
    private String endYear(String dataInizio){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(dataInizio));
            cal.add( Calendar.YEAR, 1 );
            String convertedDate=dateFormat.format(cal.getTime());
            return convertedDate;
        } catch (ParseException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
/**
 * converte in formato datetime una stringa
 * l'anno viene modificato come inizio anno del dottorato
 * 
 * @param dataString
 * @return 
 */
    private String datetimeFormat(String dataString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=null;
        try {
            date = formatter.parse(dataString+"-09-01 00:00");
        } catch (ParseException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatter.format(date);
    }
    
    /**
     * converte in formato yyyy-MM-dd l'anno dato in input 
     * l'anno viene modificato come inizio anno del dottorato
     * 
     * @param dataString
     * @return 
     */
     private String dateFormat(String dataString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = formatter.parse(dataString+"-09-01");
        } catch (ParseException ex) {
            Logger.getLogger(ActivityRegisterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatter.format(date);
    }
} 