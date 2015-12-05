/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;
import it.unisa.dottorato.phdCourse.Lesson;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Rembor
 */
public class PresenceManager {
  private static final String TABLE_PHDSTUDENT="phdstudent";
  private static final String TABLE_Lesson="lesson";
  private static final String TABLE_Presence="presence";
  private static final String TABLE_Course="course";
  private static PresenceManager instance;
   Presence checkPermission;
  // singleton
  private PresenceManager() {
        super();
    }
  
   public static synchronized PresenceManager getInstance() {
        if (instance == null) {
            instance = new PresenceManager();
        }
        return instance;
    }
  
  public synchronized ArrayList<Presence> getPresenceList( ) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Presence> classList = new ArrayList<Presence>();
          Presence registro;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
             */
            String tSql = "SELECT phd.fkAccount,pre.isPresent FROM phdstudent.phd present.pre "
                    + "WHERE phd.fkAccount= pre.fkAccount"
                    + " ORDER BY fkAccount ";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                 registro = new Presence();
                  registro.setFkPhdstudent(result.getString("fkPhdstudent"));
                  registro.setFkLesson(result.getString("fkLesson"));
                 registro.setIsPresent(result.getBoolean("isPresent"));

                classList.add(registro);
            }

            return classList;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
  public synchronized boolean getPermission() throws ExceptionPermissionDenied{
     this.checkPermission=new Presence();
     boolean permission= this.checkPermission.isIsPresent();
      if (permission=false){
          throw new ExceptionPermissionDenied();
          
      }
      else return true;
      }

  
  public synchronized void ChangePermission(boolean permission)  {
        
      if(permission==false){
       
       checkPermission.setSetPermission(true);
   }
       else checkPermission.setSetPermission(false);
          
       
  }
   
   //si fara con una combo box
   public synchronized ArrayList<String> getPresenceCourse() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
           ArrayList<String> corso= new ArrayList<String>();
          Presence registro;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "SELECT cou.name FROM course.cou,lesson.les "
                    + " WHERE les.fkCourse=cou.idCourse";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                 String nomeCorso=result.getString("name");
                 

                corso.add(nomeCorso);
            }

            return corso;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
   
   public synchronized ArrayList<Presence> getPresence(int lesson) throws SQLException{
       Connection connect = null;
        try {
            ArrayList<Presence> classList = new ArrayList<Presence>();
          Presence registro;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
             */
            String tSql = "SELECT phd.fkAccount,pre.isPresent FROM phdstudent.phd present.pre lesson.les "
                    + "WHERE pre.fkLesson="
                    + lesson
                    + " ORDER BY fkAccount ";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                 registro = new Presence();
                  registro.setFkPhdstudent(result.getString("fkPhdstudent"));
                 registro.setIsPresent(result.getBoolean("isPresent"));

                classList.add(registro);
            }

            return classList;

        } finally {
            DBConnection.releaseConnection(connect);
        }
   }
   public synchronized void modifyPresence(boolean signature,Presence old) throws SQLException {
       try (Connection connect = DBConnection.getConnection()) {
      
     /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella presenze
             */
            String tSql = "UPDATE"
                   +  PresenceManager.TABLE_Presence
                    +" set isPresent = "
                    + signature
                     + "WHERE fkPhdstudent='"
                    + old.getFkPhdstudent()+"',"
                    + "fkLesson ='"
                    +old.getFkLesson()+"'"; 
            
            

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
   
   }
   
}
