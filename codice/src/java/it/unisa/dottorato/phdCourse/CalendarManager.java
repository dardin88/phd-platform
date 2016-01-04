package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.Curriculum.CurriculumManager;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.NameException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe della gestione del calendario
 *
 * @author Giuseppe Picciocchi
 */
public class CalendarManager {
     /**
     *  macro per indicare il nome delle tabelle
     */
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_LESSON = "lesson";
    private static final String TABLE_SEMINAR = "seminar";
    
     //	 istanza della classe
    private static CalendarManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CalendarManager() {
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
    public static synchronized CalendarManager getInstance() {
        if (instance == null) {
            instance = new CalendarManager();
        }
        return instance;
    }
    
    /** Metodo della classe incaricato di inserire un nuovo corso
     * 
     * @param pCourse il nuovo corso da inserire
     * @throws SQLException 
     */
    public synchronized void insert_course(Course pCourse) throws SQLException ,IdException , DescriptionException , NameException , DateException,CourseException{
       
         Connection connect = null;
        try  {
                    connect = DBConnection.getConnection();
                    testCourse(pCourse);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella course
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_COURSE
                    + " (idCourse, fkCurriculum, fkCycle, name,desription, startDate,endDate)"
                    + " VALUES ("
                    + testid(pCourse.getIdCourse())
                    + ",'"
                    + testName(pCourse.getFK_curriculum())
                    + "',"
                    + testid(pCourse.getFK_cycle())
                    + ",'"
                    +testName(pCourse.getName())
                     + "','"
                    +(testDescription(pCourse.getDescription()))
                    + "','"
                    + testStartData(pCourse.getStartDate())
                    + "','"
                    + testEndData(pCourse.getEndDate()) 
                    + "')";

             //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di inserire una nuova lezione
     * 
     * @param pLesson la nuova lezione da inserire
     * @throws SQLException 
     */
    public synchronized void insert_lesson(Lesson pLesson) throws SQLException,IdException , DescriptionException , DateException, NameException , ClassroomException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella lesson
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_LESSON
                    + " ( idLesson, date, startTime, endTime, name, classroom, desription, cycle, curriculum, fkCourse)"
                    + " VALUES ('"
                    + testid(pLesson.getIdLesson()) // int
                    + "','"
                    + testStartData(pLesson.getData()) // Date
                    + "','"
                    + testid(pLesson.getStartTime()) // int
                    + "','"
                    + testid(pLesson.getEndTime()) // int 
                    + "','"
                    + Utility.Replace(testNomeLesson(pLesson.getName())) // String
                    + "','"
                    + Utility.Replace(testClassroom(pLesson.getClassroom())) // String
                      + "','"
                    + Utility.Replace(testDescriptionLesson(pLesson.getDescription())) // String
                      + "','"
                    + testid(pLesson.getCycle()) // int
                      + "','"
                    + Utility.Replace(testName(pLesson.getCurriculum()))
                     + "','"
                    + testid(pLesson.getFK_course()) // int
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } 
    }
    
    
   /** Metodo della classe incaricato di inserire un nuovo seminario
    * 
    * @param pSeminar il nuovo seminario da inserire
    * @throws SQLException 
    */
    public synchronized void insert_seminar(Seminar pSeminar) throws SQLException,IdException , NameException , PlaceException , DescriptionException , SpeakerException , DateException{
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella seminar
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_SEMINAR
                    + " ( idSeminar, date, startTime, endTime, name, namespeacker, desription, place, fkCourse)"
                    + " VALUES ('"
                    + testid(pSeminar.getIdSeminar()) // int
                    + "','"
                    + testStartData(pSeminar.getData()) // Date
                    + "','"
                    + testid(pSeminar.getStartTime()) // int
                    + "','"
                    + testid(pSeminar.getEndTime()) // int 
                    + "','"
                    + Utility.Replace(testNomeSeminar(pSeminar.getName())) // String
                    + "','"
                    + Utility.Replace(testSpeakerSeminar(pSeminar.getNameSpeacker())) // String
                      + "','"
                    + Utility.Replace(seminarTestDescription(pSeminar.getDescription())) // String
                      + "','"
                    + Utility.Replace(testPlaceSeminar(pSeminar.getPlace()))
                      + "','"
                    + testid(pSeminar.getFK_course())
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    
    /** Metodo della classe incaricato di modificare una lezione
     * 
     * @param oldLessonID l'id della lezione da modificare
     * @param pLesson la nuova lezione aggiornata
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
     public synchronized void update_lesson(int oldLessonID, Lesson pLesson) throws ClassNotFoundException, SQLException, IOException,IdException , NameException , ClassroomException , DescriptionException , DateException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella lesson
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_LESSON
                    + " set idLesson = '"
                    + testid(pLesson.getIdLesson())
                    + "', date = '"
                    + testStartData(pLesson.getData())
                    + "', startTime = '"
                    + testid(pLesson.getStartTime())
                    + "', endTime = '"
                    + testid(pLesson.getEndTime())
                    + "', name = '"
                    + Utility.Replace(testNomeLesson(pLesson.getName()))
                    + "', classroom = '"
                    + Utility.Replace(testClassroom(pLesson.getClassroom()))
                    + "', description = '"
                    + Utility.Replace(testDescriptionLesson(pLesson.getDescription()))
                    + "', cycle = '"
                    + testid(pLesson.getCycle())
                    + "', curriculum = '"
                    + Utility.Replace(testNomeLesson(pLesson.getCurriculum()))
                    + "', fkCourse = '"
                    + testid(pLesson.getFK_course())
                    + "' WHERE idLesson = "
                    + oldLessonID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } 
    }
     
     
     /** Metodo della classe incaricato di modificare un seminario
      * 
      * @param oldSeminarID l'id del seminario da modificare
      * @param pSeminar il nuovo seminario aggiornato
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
      */
     public synchronized void update_seminar(int oldSeminarID, Seminar pSeminar) throws ClassNotFoundException, SQLException, IOException,IdException , DescriptionException , NameException , SpeakerException , PlaceException , DateException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella seminar
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_SEMINAR
                    + " set idSeminar = '"
                    + testid(pSeminar.getIdSeminar())
                    + "', date = '"
                    + testStartData(pSeminar.getData())
                    + "', startTime = '"
                    + testid(pSeminar.getStartTime())
                    + "', endTime = '"
                    + testid(pSeminar.getEndTime())
                    + "', name = '"
                    + Utility.Replace(testNomeSeminar(pSeminar.getName()))
                    + "', namespeacker = '"
                    + Utility.Replace(testSpeakerSeminar(pSeminar.getNameSpeacker()))
                    + "', description = '"
                    + Utility.Replace(testDescriptionLesson(pSeminar.getDescription()))
                    + "', place = '"
                    + Utility.Replace(testPlaceSeminar(pSeminar.getPlace()))
                    + "', fkCourse = '"
                    + testid(pSeminar.getFK_course())
                    + "' WHERE idLesson = "
                    + oldSeminarID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } 
    }
     
     
     /** Metodo della classe incaricato di cancellare una lezione
      * 
      * @param idLesson l'id della lezione da cancellare
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
      */
     public synchronized void delete_lesson(String idLesson) throws ClassNotFoundException, SQLException, IOException,IdException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
                int id = Integer.parseInt(idLesson);
            /*
             * Prepariamo la stringa SQL per cancellare un record 
             * nella tabella lesson
             */
            String tSql = "DELETE FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE idLesson = '"
                    + testid(id) + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     /** Metodo della classe incaricato di cancellare un seminario
      * 
      * @param idSeminar l'id del seminario da cancellare
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
      */
     public synchronized void delete_seminar(String idSeminar) throws ClassNotFoundException, SQLException, IOException,IdException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            int id = Integer.parseInt(idSeminar);
            /*
             * Prepariamo la stringa SQL per cancellare un record 
             * nella tabella seminar
             */
            String tSql = "DELETE FROM "
                    + CalendarManager.TABLE_SEMINAR
                    + " WHERE idSeminar = '"
                    + testid(id) + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }  finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     /** Metodo della classe incaricato di ricercare una lezione dato l'id
      * 
      * @param pLessonID l'id della lezione da ricercare
      * @return restituisce la lezione se trovata, lancia un'eccezione altrimenti
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException
      * @throws IdException 
      */
     public synchronized Lesson getLessonById(int pLessonID) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        
        try {
            Lesson lesson = new Lesson();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella lesson
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE idLesson = '"
                    + testid(pLessonID) + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getInt("startTime"));
                lesson.setEndTime(result.getInt("endTime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("description"));
                lesson.setCycle(result.getInt("cycle"));
                lesson.setCurriculum(result.getString("curriculum"));
                lesson.setFK_course(result.getInt("fkCourse"));
            }

            return lesson;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     /** Metodo della classe incaricato di ricercare un seminario dato l'id
      * 
      * @param pSeminarID l'id del seminario da ricercare
      * @return restituisce il seminario se trovato, lancia un'eccezione altrimenti
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException
      * @throws IdException 
      */
     public synchronized Seminar getSeminarById(int pSeminarID) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        try {
            Seminar seminar = new Seminar();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella seminar
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_SEMINAR
                    + " WHERE idSeminar = '"
                    + testid(pSeminarID) + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                seminar.setIdSeminar(result.getInt("idSeminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(result.getInt("startTime"));
                seminar.setEndTime(result.getInt("endTime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("namespeacker"));
                seminar.setDescription(result.getString("description"));
                seminar.setPlace(result.getString("place"));
                seminar.setFK_course(result.getInt("fkCourse"));
            }

            return seminar;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
       
     public synchronized ArrayList<Course> getAllCourse() throws ClassNotFoundException, SQLException, IOException, IdException {
      
    
         Statement stmt = null;
        ResultSet result = null;
        Connection connection = null;
       Course course = new Course();
        ArrayList<Course> listAvviso = new ArrayList<Course>();
        
        try {
            //connessione al database
            connection = DBConnection.getConnection();
            /*
             *stringa SQL per effettuare la ricerca nella 
             * tabella news
             */
        String query = "select * from course";
       //  String query = "select * from course where curdate()<course.startDate";  quando lo ripopolo meglio

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }
            //esecuzione query
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
               while (result.next()) {
                course.setIdCourse(result.getInt("idCourse"));
                course.setName(result.getString("name"));
                course.setFK_curriculum(result.getString("fkCurriculum"));
                course.setFK_cycle(result.getInt("fkCycle"));
                course.setDescription(result.getString("description"));
                course.setStartDate(result.getDate("startDate"));
                course.setEndDate(result.getDate("endDate"));
                               
               listAvviso.add(course);

            }
        } catch (ConnectionException ex) {
            Logger.getLogger(CalendarManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (result != null) {
                result.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return listAvviso;
     
     
     
     }
     /** Metodo della classe incaricato di ricercare un corso dato l'id
      * 
      * @param pCourseID l'id del corso da ricercare
      * @return restituisce il corso se trovato, lancia un'eccezione altrimenti
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException
      * @throws IdException 
      */
     public synchronized Course getCourseById(int pCourseID) throws ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        try {
            Course course = new Course();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella course
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_COURSE
                    + " WHERE idCourse = '"
                    + testid(pCourseID) + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                course.setIdCourse(result.getInt("idCourse"));
                course.setName(result.getString("name"));
                course.setFK_curriculum(result.getString("fkCurriculum"));
                course.setFK_cycle(result.getInt("fkCycle"));
                course.setDescription(result.getString("description"));
                course.setStartDate(result.getDate("startDate"));
                course.setEndDate(result.getDate("endDate"));
            }

            return course;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     /** Metodo della classe incaricato di ritornare tutte le lezioni di un corso
      * dato il corso
      * 
      * @param idcourse il corso da selezionare
      * @return restituisce una lista di lezioni presenti nel corso
      * @throws SQLException
      * @throws IdException 
      */
      public synchronized ArrayList<Lesson> getAllLessonOf(int idcourse) throws SQLException, IdException { //da modificare dato Person
       Lesson lesson = new Lesson();
          ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE fkCourse = '"
                    + testid(idcourse) + "'"; //da modificare ancora

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                
                
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
      
      
      
       public synchronized ArrayList<Lesson> getAllLessonOfProfessor() throws SQLException, IdException { //da modificare dato Person
        ArrayList<Lesson> lessons = new ArrayList<>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT DISTINCT  lesson.name, lesson.idLesson , lesson.fkCourse FROM lesson group by lesson.name "
                    ; //da modificare ancora

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Lesson lesson = new Lesson();
                
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setName(result.getString("name"));
                lesson.setFK_course(result.getInt("fkCourse"));
                
                lessons.add(lesson);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return lessons;
    }
       public synchronized int getAllLessonOfCourse() throws SQLException, IdException { //da modificare dato Person
       int numerLezio=0;
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT COUNT(idLesson) from lesson,Course where  FkCourse=idCourse" ;//da modificare ancora

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
 if(result.next()){
     numerLezio=result.getInt("COUNT(idLesson)");
 }
            

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return numerLezio;
    }
       
      /** Metodo della classe per il testing dell'id; non puo' essere minore di 0
       * 
       * @param id
       * @return restituisce l'id se valido, lancia un'eccezione altrimenti
       * @throws IdException 
       */
      public int testid(int id) throws IdException {
        if(id<0||id>6){
            throw new IdException("l'id non puo' essere minore di 0");
        }
       else return id;
    }  
      /** Metodo della classe per il testing della descrizione; non puo' essere
       * nulla o maggiore di 249 caratteri
       * 
       * @param description stringa da testare
       * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
       * @throws DescriptionException 
       */
       public String testDescription(String description) throws DescriptionException{
         if(description.isEmpty()|| description.length()>250){            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }

       /** Metodo della classe per il testing della descrizione della lezione;
        * non puo' essere nulla
        * 
        * @param description stringa da testare
        * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
        * @throws DescriptionException 
        */
    private String testDescriptionLesson(String description) throws DescriptionException {
        if(description.isEmpty()){
            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
    
   /** Metodo della classe per il testing della descrizione del seminario;
        * non puo' essere nulla
        * 
        * @param description stringa da testare
        * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
        * @throws DescriptionException 
        */
    public String seminarTestDescription(String description) throws DescriptionException{
         if(description.isEmpty()){
            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
    
    /**Metodo della classe per il testing della data di inzio; non puo' essere nulla
     * 
     * @param data data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testStartData(Date data) throws DateException{
         if(data.equals(null)){
            
            throw new DateException("la data di inizio e' sbagliata"); 
        }
         return data;
    }
   
    /**Metodo della classe per il testing della data di fine; non puo' essere nulla
     * 
     * @param data data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testEndData(Date data) throws DateException{
         if(data.equals(null)){
            
            throw new DateException("la data di fine e' sbagliata"); 
        }
         return data;
    }
    
    /** Metodo della classe per il testing della stringa name; non puo' essere
     * nulla o maggiore di 49 caratteri
     * 
     * @param name la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws NameException 
     */
      public String testName(String name) throws NameException {
        if(name.isEmpty() || name.length() > 100) 
            throw new NameException();
        return name;
    }
      
      /** Metodo della classe per il testing della stringa name; non puo' essere
     * nulla o maggiore di 69 caratteri
     * 
     * @param name la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws NameException 
     */
      public String testNomeLesson(String name) throws NameException{
         if(name.isEmpty()||name.length()>70){
            
            throw new NameException("il nome  e' sbagliato"); 
        }
         return name;
    }

       /** Metodo della classe per il testing della stringa name; non puo' essere
     * nulla o maggiore di 69 caratteri
     * 
     * @param name la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws NameException 
     */
    private String testNomeSeminar(String name)throws NameException{
         if(name.isEmpty()||name.length()>70){
            
            throw new NameException("il nome  e' sbagliato"); 
        }
         return name;
    }
    
     /** Metodo della classe per il testing della stringa name; non puo' essere
     * nulla o maggiore di 49 caratteri
     * 
     * @param name la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws PlaceException 
     */
    private String testPlaceSeminar(String name)throws PlaceException{
         if(name.isEmpty()|| name.length()>50){
            
            throw new PlaceException("il posto e' sbagliato"); 
        }
         return name;
    }

     /** Metodo della classe per il testing del nome dello speaker; non puo' essere
     * nulla o maggiore di 49 caratteri
     * 
     * @param nameSpeacker la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws SpeakerException 
     */
    private String testSpeakerSeminar(String nameSpeacker) throws SpeakerException{
        if(nameSpeacker.isEmpty() || nameSpeacker.length()>50){
            
            throw new  SpeakerException("il posto e' sbagliato"); 
        }
         return nameSpeacker;
    }

     /** Metodo della classe per il testing della stringa classroom; non puo' essere
     * nulla o maggiore di 49 caratteri
     * 
     * @param classroom la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ClassroomException 
     */
    private String testClassroom(String classroom) throws ClassroomException {
      if(classroom.isEmpty() || classroom.length()>50){
            
            throw new  ClassroomException ("la classe e' sbagliato"); 
        }
         return classroom;
    }

    /**Metodo della classe per il testing della data; non puo' essere nulla
     * 
     * @param data data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    private Date testData(Date data)  throws DateException{
         if(data.equals(null)){
            
            throw new DateException("la data e' sbagliata"); 
        }
         return data;   
    }
     public void testCourse (Course c) throws CourseException {
        if (c == null) {
            throw new CourseException();
        }
    }
    
}

