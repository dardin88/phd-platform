package it.unisa.dottorato.presence;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
  public synchronized ArrayList<Presence> getPresenceList() throws ClassNotFoundException, SQLException, IOException {
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
    

   
 /**  Metodo della classe incaricato di ritornare una lista delle presenze di un corso
  * 
  * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
  * @throws ClassNotFoundException
  * @throws SQLException
  * @throws IOException 
  */ 
   public synchronized ArrayList<Presence> getPresenceCourse() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
           ArrayList<Presence> corso= new ArrayList<Presence>();
          Presence registro;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "SELECT * from presence";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                 registro = new Presence();
                 
                  registro.setFkPhdstudent(result.getString("fkPhdstudent"));
                 registro.setIsPresent(result.getBoolean("isPresent"));
                 corso.add(registro);
            }

            return corso;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
  
   /**  Metodo della classe incaricato di ritornare la lista delle presenze di una lezione
    * dato il suo id
    * 
    * @param lesson l'id della lezione
    * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
    * @throws SQLException 
    */
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
             String tSql = "SELECT distinct presence.fkPhdstudent, presence.isPresent "
       + "from presence,lesson  where "
+ "  lesson.idLesson= "+ lesson + " group by presence.fkPhdstudent";
           
             /*String tSql = "SELECT account.name, account.surname, presence.isPresent "
       + "from presence,account,lesson  where "
+ " presence.fkPhdstudent=account.secondaryEmail and lesson.idLesson= "
                    + lesson
       + " ORDER BY account.surname"*/
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
    * 
    * @param signature valore booleano
    * @param old  presenza da modificare
    * @throws SQLException
    * @throws ExceptionPermissionDenied 
    */
   public synchronized void modifyPresence(boolean signature,Presence old) throws SQLException, ExceptionPermissionDenied {
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
