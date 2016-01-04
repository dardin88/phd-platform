package it.unisa.dottorato.presence;
import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.phdCourse.Lesson;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
   /**
    * Metodo della classe incaricato di inserire la presenza  di un dottorando  una lezione
    * 
    * @param dottorando
    * @throws SQLException 
    */
   public void insertPresence(Presence dottorando) throws SQLException{
        //connessione al database
        Connection connect = DBConnection.getConnection();
     try {
            /*
             *stringa SQL per effettuare l'inserimento nella 
             * tabella news
             */
             String tSql = "INSERT INTO "
                    + PresenceManager.TABLE_Presence
                    + "(fkPhdstudent,fkLesson,isPresent)"
                    + " VALUES ('"
                    + Utility.Replace(dottorando.getFkPhdstudent())
                    + "',"
                    + testid(dottorando.getFkLesson())
                    + ","
                    + " false "
                    + ")";      
            
            System.out.println(tSql);
            //esecuzione query
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(tSql);
            connect.commit();
        }
      catch (IdException ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
      }          finally {
            DBConnection.releaseConnection(connect);
        }
    }

   
   /** Metodo della classe incaricato di ritornare tutte le lezioni di un corso
      * dato il corso
      * 
      * @param pCourse il corso da selezionare
      * @return restituisce una lista di lezioni presenti nel corso
      * @throws SQLException
      * @throws IdException 
      */
      public synchronized List<Lesson> getAllLessonOf(int idcourse) throws SQLException, IdException { //da modificare dato Person
        List<Lesson> lessons = new ArrayList<>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT * FROM "
                    + PresenceManager.TABLE_Lesson
                    + " WHERE fkCourse = "
                    + testid(idcourse) 
                    + " order by date"; 

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Lesson lesson = new Lesson();
                
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getInt("startTime"));
                lesson.setEndTime(result.getInt("endTime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("desription"));                             
                lesson.setFK_course(result.getInt("fkCourse"));
                
                lessons.add(lesson);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return lessons;
    }
      
  
   
   /** Metodo della classe incaricato di ritornare la lista delle presenze di una lezione
    * 
     * @param lesson
    * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
    * @throws ClassNotFoundException
    * @throws SQLException
    * @throws IOException 
    */
  
   
   public synchronized ArrayList<Presence> getPresenceList(int lesson) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        try {
            ArrayList<Presence> classList = new ArrayList <Presence>();
          Presence registro;
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
             */
            String tSql = "SELECT name, surname ,isPresent FROM account,presence "
                    + " WHERE secondaryEmail = fkPhdstudent and fkLesson= "+testid(lesson);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                /*String  nome =result.getString("name");
                String surname= result.getString("surname");
                boolean isPresent=result.getBoolean("isPresent");
                */
                 registro = new Presence();
                  registro.setFkPhdstudent(result.getString("fkPhdstudent"));
                  registro.setFkLesson(result.getInt("fkLesson"));
                 registro.setIsPresent(result.getBoolean("isPresent"));

                classList.add(registro);
            }

            return classList;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    

      
   
 /**  Metodo della classe incaricato di ritornare il numero  delle presenze di un dottorando a un corso
  *     @param idCourse
     * @param studente
  * @return restituisce un array list di presenze, lancia un'eccezione altrimenti
  * @throws ClassNotFoundException
  * @throws SQLException
  * @throws IOException 
  */ 
   public synchronized int getPresenceCourse(String dottorando,int idCourse) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        Presence corso = null ;
       int presence=0;
        try {
         
          
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "SELECT COUNT(presence.isPresent) FROM presence,lesson  "
                    + " where isPresent=true and presence.fkLesson = lesson.idLesson" +
                   " and lesson.fkCourse ="+ testid(idCourse)+ 
                    " and presence.fkPhdstudent ='"+testDottorando(dottorando) +"'" ;
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

         if(result.next()) {
                 
           presence=result.getInt(1);
            }

        

        } catch (PhdStudentexception ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
      }  finally {
            DBConnection.releaseConnection(connect);
        }
        return presence;
     
    }
   /**metodo che dato un idCorso restituisce tutti i dottorandi che seguono quel corso
    * 
    * 
    * @param idCorso
    * @return
    * @throws ClassNotFoundException
    * @throws SQLException
    * @throws IOException
    * @throws IdException 
    */
   
   public synchronized ArrayList<Account> getPresenceDottorandi(int idCorso) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
       Account corso = null ;
       ArrayList<Account> classList = new ArrayList <Account>();
    
        try {
        // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "SELECT  account.name, account.surname, account.secondaryEmail" +
        " FROM presence, account, lesson " +
        " where presence.fkPhdstudent = account.secondaryEmail " +
         " and presence.fkLesson = lesson.idLesson " +
          " and lesson.fkCourse="+testid(idCorso)+
          " group by account.secondaryEmail" ;
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

       while (result.next()) {
                
              corso = new Account();
              
                   corso.setName(result.getString("name"));
                  corso.setSurname(result.getString("surname"));
                 corso.setSecondaryEmail(result.getString("secondaryEmail"));

                classList.add(corso);
            }

           
        }  finally {
            DBConnection.releaseConnection(connect);
        }
        return classList;
        //return corso;
    }
  /** metodo che passando il dottorando e il id corso restituisce 
   * le lezioni e le presenze a queste lezioni
   * 
   * @param dottorando
   * @param idCorso
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   * @throws IOException
   * @throws IdException 
   */
   public synchronized ArrayList<Presence> getPresenceToLesson(String dottorando,int idCorso) throws ClassNotFoundException, SQLException, IOException, IdException {
         Connection connect = null;
       Lesson corso = null ;
       Presence presente= null;
      ArrayList<Presence> classList = new ArrayList <Presence>();
        ArrayList<Lesson> classLesson= new ArrayList <Lesson>();
         Map< ArrayList<Lesson>,ArrayList<Presence>> map =new HashMap();
       
         try {
         
          
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella presence
           */
            String tSql = "SELECT  presence.isPresent, presence.fkLesson, presence.fkPhdstudent " +
               " FROM presence, lesson " +
           " where presence.fkLesson = lesson.idLesson " +
            "and lesson.fkCourse ="+testid(idCorso) +" and presence.fkPhdstudent = '"
                    +testDottorando(dottorando) 
             +"' order by date" ;
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

       while (result.next()) {
                presente =new Presence();
                presente.setIsPresent(result.getBoolean("isPresent"));
                presente.setFkLesson(result.getInt("fkLesson"));
                presente.setFkPhdstudent(result.getString("fkPhdstudent"));
               
                
             classList.add(presente);
                  /*  contiene.setClassLesson(corso);
              contiene.setClassList(presente);

                contenitore.add(contiene);*/
            }

           
        } catch (PhdStudentexception ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
      }  finally {
            DBConnection.releaseConnection(connect);
        }
        return classList;
        
    }
  
   /**  Metodo della classe incaricato di modificare una presenza
    *  @param dottorando
    *  @param  idLesson
    * @throws IdException
    */
   public void modifyPresence(String dottorando,int idLesson ) throws SQLException, PhdStudentexception {
       try (Connection connect = DBConnection.getConnection()) {
          
     /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella presenze
             */
            String tSql = "UPDATE "
                   +  PresenceManager.TABLE_Presence
                    +" set isPresent = "
                    + changeSignatura(dottorando,idLesson)
                     + " WHERE fkPhdstudent = '"
                    + testDottorando(dottorando)+"' "
                    + " and fkLesson = "
                    + testid(idLesson); 
            
            

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IdException ex) {
          Logger.getLogger(PresenceManager.class.getName()).log(Level.SEVERE, null, ex);
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
/** metodo incaricato di cambiare la presenza di un  dottorando  ad una lezione
 * 
 *
 */
    private  boolean changeSignatura(String dottorando,int idLesson)  throws SQLException, IOException, PhdStudentexception, IdException {
          boolean controllo=false;
        try (Connection connect = DBConnection.getConnection()) {
           
             String tSql = "SELECT isPresent FROM  "
                    + PresenceManager.TABLE_Presence
                    + " where fkPhdstudent = '"
                    + testDottorando(dottorando)+"' and fkLesson = "
                     + testid(idLesson);
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            
            if (result.next()){
             controllo=result.getBoolean("isPresent");
               if(controllo!=true){  
                 
             controllo=true;}
            
             else controllo=false;
            }
            connect.commit();
            return controllo;
        }
    }

    
   
}
