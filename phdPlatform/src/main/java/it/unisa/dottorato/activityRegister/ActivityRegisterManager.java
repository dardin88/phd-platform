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
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Liliana
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
    public void insertActvity(Activity activity) throws 
           SQLException, IOException {
       Connection connect = null;
            try{
                connect = DBConnection.getConnection();
           
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
    private float calculateTotTime(java.util.Date startDateTime, java.util.Date endDateTime) {
        long diffInMillies = startDateTime.getTime() - endDateTime.getTime();
        return TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    

   
 }
