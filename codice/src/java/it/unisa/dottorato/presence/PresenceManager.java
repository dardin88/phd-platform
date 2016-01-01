package it.unisa.dottorato.presence;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe per la gestione delle presenze
 *
 * @author Rembor
 */
public class PresenceManager {
    /**
     * I nomi delle tabelle
     */
  private static final String TABLE_PHDSTUDENT="phdstudent";
  private static final String TABLE_Lesson="lesson";
  private static final String TABLE_Presence="presence";
  private static final String TABLE_Course="course";
  private static PresenceManager instance;
   Presence checkPermission;
  /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
  private PresenceManager() {
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
   public static synchronized PresenceManager getInstance() {
        if (instance == null) {
            instance = new PresenceManager();
        }
        return instance;
    }
  
   /** Metodo della classe incaricato di ritornare la lista delle presenze di una lezione
    * 
    * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
    * @throws ClassNotFoundException
    * @throws SQLException
    * @throws IOException 
    */
  public synchronized ArrayList<Presence> getPresenceList(int lesson) throws ClassNotFoundException, SQLException, IOException, IdException {
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
            String tSql = "SELECT name, surname ,isPresent FROM account,presence "
                    + " WHERE secondaryEmail = fkPhdstudent and fkLesson= "+ testid(lesson);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                 registro = new Presence();
                  registro.setFkPhdstudent(result.getString("name"));
                  registro.setFkLesson(result.getInt("fkLesson"));
                 registro.setIsPresent(result.getBoolean("isPresent"));

                classList.add(registro);
            }

            return classList;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    

   
 /**  Metodo della classe incaricato di ritornare una lista delle presenze di un corso
  * 
  * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
  * @throws ClassNotFoundException
  * @throws SQLException
  * @throws IOException 
  */ 
   public synchronized Presence getPresenceCourse(String studente) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        Presence corso = null ;
        try {
         
          
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "select isPresent from "+PresenceManager.TABLE_Presence+
                    " where fkPhdstudent='"+testDottorando(studente)+"'";
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

         if(result.next()) {
                 corso = new Presence();                             
                 corso.setIsPresent(result.getBoolean("isPresent"));
           
            }

            return corso;

        } catch (PhdStudentexception ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
      }  finally {
            DBConnection.releaseConnection(connect);
        }
        return corso;
    }
  
   /**  Metodo della classe incaricato di ritornare la lista delle presenze di una lezione
    * dato il suo id
    * 
    * @param lesson l'id della lezione
    * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
    * @throws SQLException 
    */
   public synchronized ArrayList<Presence> getPresence(int lesson) throws SQLException, IdException{
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
             String tSql = "SELECT  presence.fkPhdstudent, presence.isPresent "
       + "from presence where "
+ "  presence.fkLesson= "+ testid(lesson) + " group by presence.fkPhdstudent";
           
             
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
   
   /**  Metodo della classe incaricato di modificare una presenza
    *  @param dottorando 
    * @throws ExceptionPermissionDenied 
    */
   public void modifyPresence(String dottorando ) throws SQLException, ExceptionPermissionDenied, PhdStudentexception {
       try (Connection connect = DBConnection.getConnection()) {
          
     /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella presenze
             */
            String tSql = "UPDATE "
                   +  PresenceManager.TABLE_Presence
                    +" set isPresent = "
                    + changeSignatura(dottorando)
                     + " WHERE fkPhdstudent = '"
                    + testDottorando(dottorando)+"'"; 
            
            

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IOException ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   
   }
   public String testDottorando(String title) throws PhdStudentexception{
        if((title.length()<1) || title.equals("") ||(title.length()>50)|| (title.indexOf("@")==-1)){
            
            throw new PhdStudentexception("l'email del dottorando e' sbagliata "); 
        }
        return title;
    }
  
   public int testid(int id) throws IdException {
        if(id<0 || id>999999){
            throw new IdException("l'id non puo' essere minore di 0");
        }
        return id;
    }  

    private  boolean changeSignatura(String dottorando)  throws SQLException, IOException, PhdStudentexception {
         try (Connection connect = DBConnection.getConnection()) {
           
             String tSql = "SELECT isPresent FROM  "
                    + PresenceManager.TABLE_Presence
                    + " where fkPhdstudent = '"
                    + testDottorando(dottorando)+" '";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            boolean controllo;
              if(controllo=result.next()){
                 System.out.println(controllo);
             if(controllo!=true){  
                  System.out.println(controllo);
               controllo=true;
            }
             else controllo=false;
              }
            connect.commit();
            return controllo;
        } 
    }
   
}
