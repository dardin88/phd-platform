package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.Professor;
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
    private static final String TABLE_STUDENT = "phdstudent";
    private static final String TABLE_PRESENCE = "presence";
    
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
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.NameException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.phdCourse.CourseException 
     * @throws java.io.IOException 
     */
    public synchronized void insert_course(Course pCourse) throws SQLException ,IdException , DescriptionException , NameException , DateException,CourseException, IOException{
       
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
                    + " (idCourse, fkCurriculum, fkCycle, name,description, startDate,endDate)"
                    + " VALUES ("
                    + testid(nextNumberCourse())
                    + ",'"
                    + testName(pCourse.getFkCurriculum())
                    + "',"
                    + testid(pCourse.getFkCycle())
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
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.exception.NameException 
     * @throws java.io.IOException 
     * @throws it.unisa.dottorato.phdCourse.ClassroomException 
     */
    public synchronized void insert_lesson(Lesson pLesson,Professor professor) throws SQLException,IdException , DescriptionException , DateException, NameException , ClassroomException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella lesson
             */
            int idLesson= testid(nextNumberLesson());
            int idCourse= testid(pLesson.getFK_course());
            
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_LESSON
                    + " ( idLesson, date, startTime, endTime, name, classroom, desription, fkCourse)"
                    + " VALUES ("
                    + idLesson // int
                    + ",'"
                    + testStartData(pLesson.getData()) // Date
                    + "','"
                    + pLesson.getStartTime() // int
                    + "','"
                    + pLesson.getEndTime() // int 
                    + "','"
                    + Utility.Replace(testNomeLesson(pLesson.getName())) // String
                    + "','"
                    + Utility.Replace(testClassroom(pLesson.getClassroom())) // String
                      + "','"
                    + Utility.Replace(testDescriptionLesson(pLesson.getDescription())) // String
                      + "',"
                    + idCourse // int
                    + ")";

           
            Utility.executeOperation(connect, tSql);
            
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella keep
             */
            tSql = "INSERT INTO keep"
                    + " ( fkProfessor, fkLesson)"
                    + " VALUES ('"
                    + professor.getSecondaryEmail()
                    + "',"
                    + idLesson // int
                    + ")";
           
            Utility.executeOperation(connect, tSql);
            
            int cycle=this.getCycle(idCourse, connect);
            
            ResultSet student = this.getStudent(cycle, connect);
            
            this.setPresence(idLesson, connect, student);
            
            connect.commit();
        } 
    }
    
    
   /** Metodo della classe incaricato di inserire un nuovo seminario
    * 
    * @param pSeminar il nuovo seminario da inserire
    * @throws SQLException 
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.phdCourse.PlaceException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.phdCourse.SpeakerException 
     * @throws it.unisa.dottorato.exception.NameException 
     * @throws java.io.IOException 
    */
    public synchronized void insert_seminar(Seminar pSeminar) throws SQLException,IdException , NameException , PlaceException , DescriptionException , SpeakerException , DateException, IOException{
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella seminar
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_SEMINAR
                    + " ( idSeminar, date, startTime, endTime, name, namespeacker, desription, place, fkCourse)"
                    + " VALUES ("
                    + testid(nextNumberSeminar()) // int
                    + ",'"
                    + testStartData(pSeminar.getData()) // Date
                    + "','"
                    + pSeminar.getStartTime() // int
                    + "','"
                    + pSeminar.getEndTime() // int 
                    + "','"
                    + Utility.Replace(testNomeSeminar(pSeminar.getName())) // String
                    + "','"
                    + Utility.Replace(testSpeakerSeminar(pSeminar.getNameSpeacker())) // String
                      + "','"
                    + Utility.Replace(seminarTestDescription(pSeminar.getDescription())) // String
                      + "','"
                    + Utility.Replace(testPlaceSeminar(pSeminar.getPlace()))
                      + "',"
                    + testid(pSeminar.getFK_course())
                    + ")";

            
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
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.NameException 
     * @throws it.unisa.dottorato.phdCourse.ClassroomException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.DateException 
     */
     public synchronized void update_lesson(int oldLessonID, Lesson pLesson) throws 
             ClassNotFoundException, SQLException, IOException,IdException , NameException , ClassroomException , DescriptionException , DateException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella lesson
             */
            
            int newCourseID=testid(pLesson.getFK_course());
            
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_LESSON
                    + " set date = '"
                    + testStartData(pLesson.getData())
                    + "', startTime = '"
                    + pLesson.getStartTime()
                    + "', endTime = '"
                    + pLesson.getEndTime()
                    + "', name = '"
                    + Utility.Replace(testNomeLesson(pLesson.getName()))
                    + "', classroom = '"
                    + Utility.Replace(testClassroom(pLesson.getClassroom()))
                    + "', desription = '"
                    + Utility.Replace(testDescriptionLesson(pLesson.getDescription()))
                    + "', fkCourse = "
                    + newCourseID
                    + " WHERE idLesson = "
                    + oldLessonID;           
            
           
            /*
             * Prepariamo la stringa SQL per ricavare il ciclo
             * del corso inserito
             */
            String oldCourse = "SELECT fkCourse FROM " 
                   + CalendarManager.TABLE_LESSON
                   + " WHERE idLesson = " 
                   + oldLessonID;
            
            ResultSet rs = Utility.queryOperation(connect, oldCourse); 
            rs.next();
            int oldCourseID=rs.getInt(1);
            
            if(Utility.executeOperation(connect, tSql)==0)
                throw new NameException();
            
            if(oldCourseID != newCourseID){
                        
                tSql = "DELETE FROM "
                    + CalendarManager.TABLE_PRESENCE
                    + " WHERE fkLesson = "
                    + oldLessonID;

                //Inviamo la Query al DataBase
                Utility.executeOperation(connect, tSql);
                
                int cycle=this.getCycle(newCourseID, connect);
                
                ResultSet student = this.getStudent(cycle, connect);
            
                this.setPresence(oldLessonID, connect, student);
                
            }
            connect.commit();
        } 
    }
     
    private int getCycle(int idCourse, Connection con) throws SQLException{
        
            /* Prepariamo la stringa SQL per ricavare il ciclo
             * del corso inserito
             */
            
            String cycle = "SELECT fkCycle FROM " 
                   + CalendarManager.TABLE_COURSE
                   + " WHERE idCourse = " 
                   + idCourse;
            
            ResultSet rs = Utility.queryOperation(con, cycle); 
            rs.next();    
            return(rs.getInt(1));
    }
    
    private ResultSet getStudent(int cycle, Connection con) throws SQLException{
        
            /*
             * Prepariamo la stringa SQL per ricavare gli studenti
             * che fanno parte del ciclo in esame
             */
            
            String student =  "SELECT fkAccount FROM "  
                             + CalendarManager.TABLE_STUDENT
                             + " WHERE fkCycle = " 
                             + cycle;
            
            ResultSet rs = Utility.queryOperation(con, student);
            return(rs);        
    }
    
    private void setPresence(int idLesson, Connection con, ResultSet rs) throws SQLException{
            /*
             * Prepariamo le stringhe SQL per riempire
             * lassociazione presence ed eseguiamo
             */
             
            while(rs.next()){
                String tSql = "INSERT INTO "
                             + CalendarManager.TABLE_PRESENCE
                             + " ( fkPhdstudent, fkLesson)"
                             + " VALUES ('"
                             + rs.getString(1)
                             + "',"
                             + idLesson
                             + ")";
                
                Utility.executeOperation(con, tSql);
            }        
    }
     
     /** Metodo della classe incaricato di modificare un seminario
      * 
      * @param oldSeminarID l'id del seminario da modificare
      * @param pSeminar il nuovo seminario aggiornato
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.NameException 
     * @throws it.unisa.dottorato.phdCourse.SpeakerException 
     * @throws it.unisa.dottorato.phdCourse.PlaceException 
     * @throws it.unisa.dottorato.exception.DateException 
      */
     public synchronized void update_seminar(int oldSeminarID, Seminar pSeminar) throws 
             ClassNotFoundException, SQLException, IOException,IdException , 
             DescriptionException , NameException , SpeakerException , PlaceException , DateException {
         Connection connect=null;
        try{
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella seminar
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_SEMINAR
                    + " set date = '"
                    + testStartData(pSeminar.getData())
                    + "', startTime = '"
                    + pSeminar.getStartTime()
                    + "', endTime = '"
                    + pSeminar.getEndTime()
                    + "', name = '"
                    + Utility.Replace(testNomeSeminar(pSeminar.getName()))
                    + "', namespeacker = '"
                    + Utility.Replace(testSpeakerSeminar(pSeminar.getNameSpeacker()))
                    + "', desription = '"
                    + Utility.Replace(testDescriptionLesson(pSeminar.getDescription()))
                    + "', place = '"
                    + Utility.Replace(testPlaceSeminar(pSeminar.getPlace()))
                    + "', fkCourse = "
                    + testid(pSeminar.getFK_course())
                    + " WHERE idSeminar = "
                    + oldSeminarID;           

            Utility.executeOperation(connect, tSql);

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        } 
    }
     
     
     /** Metodo della classe incaricato di cancellare una lezione
      * 
      * @param idLesson l'id della lezione da cancellare
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
     * @throws it.unisa.dottorato.exception.IdException 
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
                    + " WHERE idLesson = "
                    + testid(id);

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
     * @throws it.unisa.dottorato.exception.IdException 
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
                    + " WHERE idSeminar = "
                    + testid(id);

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
                    + " WHERE idLesson = "
                    + testid(pLessonID);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getString("startTime"));
                lesson.setEndTime(result.getString("endTime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("desription"));
                lesson.setFK_course(result.getInt("fkCourse"));
                lesson.setStatus(result.getString("status"));
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
     public synchronized Seminar getSeminarById(int pSeminarID) throws 
             ClassNotFoundException, SQLException, IOException, IdException {
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
                    + " WHERE idSeminar ="
                    + testid(pSeminarID);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                seminar.setIdSeminar(result.getInt("idSeminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(("startTime"));
                seminar.setEndTime(("endTime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("namespeacker"));
                seminar.setDescription(result.getString("desription"));
                seminar.setPlace(result.getString("place"));
                seminar.setFK_course(result.getInt("fkCourse"));
            }else{
                throw new IdException();
            }

            return seminar;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    /** Metodo della classe che ritorna i corsi
     * 
     * @return ritorna l' elenco dei corsi
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws IdException 
     */   
     public synchronized ArrayList<Course> getAllCourse() throws 
             ClassNotFoundException, SQLException, IOException, IdException {
      
    
         Statement stmt = null;
        ResultSet result = null;
        Connection connection = null;
       
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

            
            //esecuzione query
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
               while (result.next()) {
                Course course = new Course();
                course.setIdCourse(result.getInt("idCourse"));
                course.setName(result.getString("name"));
                course.setFkCurriculum(result.getString("fkCurriculum"));
                course.setFkCycle(result.getInt("fkCycle"));
                course.setDescription(result.getString("description"));
                course.setStartDate(result.getDate("startDate"));
                course.setEndDate(result.getDate("endDate"));
                               
               listAvviso.add(course);

            }
               return listAvviso;
        } finally {
            DBConnection.releaseConnection(connection);
        }
     }
     /** Metodo della classe che ritorna la lista degli id dei corsi
      * 
      * @param cycle 
      * @param curriculum
      * @return
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
      */
     public synchronized ArrayList<Integer> getCourseListId(int cycle, String curriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        ArrayList<Integer> courses = new ArrayList<>();
        try {
            
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM course "
                    + " WHERE fkCycle ="+cycle+" AND fkCurriculum='"+curriculum+"'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            connect.commit();
            while (result.next()) {
                courses.add(result.getInt("idCourse"));
            }

           

        } finally {
            DBConnection.releaseConnection(connect);
        }
         return courses;
    }
    /** Metodo della classe che ritorna i seminari
     * 
     * @return ritorna l' elenco dei seminari
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws IdException 
     */   
     public synchronized ArrayList<Seminar> getAllSeminar() throws ClassNotFoundException, SQLException, IOException, IdException {
      
    
         Statement stmt = null;
        ResultSet result = null;
        Connection connection = null;
       
        ArrayList<Seminar> listSeminar = new ArrayList<Seminar>();
        
        try {
            //connessione al database
            connection = DBConnection.getConnection();
            /*
             *stringa SQL per effettuare la ricerca nella 
             * tabella news
             */
        String query = "select * from seminar";
       //  String query = "select * from course where curdate()<course.startDate";  quando lo ripopolo meglio

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }
            //esecuzione query
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
               while (result.next()) {
                Seminar seminar = new Seminar();
                seminar.setIdSeminar(result.getInt("idSeminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(result.getString("startTime"));
                seminar.setEndTime(result.getString("endTime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("nameSpeacker"));
                seminar.setDescription(result.getString("desription"));
                seminar.setPlace(result.getString("place"));
                seminar.setFK_course(result.getInt("fkCourse"));
              
                
                               
               listSeminar.add(seminar);

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

        return listSeminar;
     
     
     
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
     public synchronized Course getCourseById(int pCourseID) throws 
             ClassNotFoundException, SQLException, IOException, IdException {
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
                    + " WHERE idCourse = "
                    + testid(pCourseID);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                course.setIdCourse(result.getInt("idCourse"));
                course.setName(result.getString("name"));
                course.setFkCurriculum(result.getString("fkCurriculum"));
                course.setFkCycle(result.getInt("fkCycle"));
                course.setDescription(result.getString("description"));
                course.setStartDate(result.getDate("startDate"));
                course.setEndDate(result.getDate("endDate"));
            }else{
                throw new IdException();
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
       
          ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        
        Connection connect = null;
        Connection connect2 = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            connect2 = DBConnection.getConnection();
            String t="select * from course where idCourse="+idcourse;
            ResultSet re=Utility.queryOperation(connect2, t);
                if(!re.next())
                    throw new IdException();
            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE fkCourse = "
                    + testid(idcourse)
                    +" order by date, idLesson";
                         //da modificare ancora

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                
                Lesson lesson = new Lesson();
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getString("startTime"));
                lesson.setEndTime(result.getString("endTime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("desription"));                             
                lesson.setFK_course(result.getInt("fkCourse"));
                lesson.setStatus(result.getString("status"));
                lessons.add(lesson);
            }

        } finally {
            DBConnection.releaseConnection(connect);
            DBConnection.releaseConnection(connect2);
        }
        
        
        return lessons;
    }
      /** Metodo della classe che ritorna i seminari di un corso
     * 
     * @return ritorna l' elenco dei corsi
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws IdException 
     */   
      
    /**
     * Metodo della classe che ritorna i seminari di un corso
     * @param idcourse l' id del corso di cui mi voglio fa restituire tutti i seminari
     * @return ritorna l' elenco dei corsi
     * @throws SQLException
     * @throws IdException
     */
    public synchronized ArrayList<Seminar> getAllSeminarOf(int idcourse) throws SQLException, IdException { //da modificare dato Person
          ArrayList<Seminar> seminars = new ArrayList<Seminar>();
        
        Connection connect = null;
        Connection connect2 = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            connect2 = DBConnection.getConnection();
            String t="select * from course where idCourse="+idcourse;
            ResultSet re=Utility.queryOperation(connect2, t);
                if(!re.next())
                    throw new IdException();
            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_SEMINAR
                    + " WHERE fkCourse = "
                    + testid(idcourse);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                
                Seminar seminar = new Seminar();

                seminar.setIdSeminar(result.getInt("idSeminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(("startTime"));
                seminar.setEndTime(("endTime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("namespeacker"));
                seminar.setPlace(result.getString("place"));
                seminar.setDescription(result.getString("desription"));                             
                seminar.setFK_course(result.getInt("fkCourse"));
                
                seminars.add(seminar);
            }

        } finally {
            DBConnection.releaseConnection(connect);
            DBConnection.releaseConnection(connect2);
        }
        
        
        return seminars;
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
        if(id<0 || id>999999){
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
        if(description.isEmpty()|| description.length()>65536){
            
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
         if(data == null){
            
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
         if(data == null){
            
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
        if(name.isEmpty() || name.length() > 100){ 
            throw new NameException();
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
         if(data == null){
            
            throw new DateException("la data e' sbagliata"); 
        }
         return data;   
    }
     public void testCourse (Course c) throws CourseException {
        if (c == null) {
            throw new CourseException();
        }
    }
    
     public synchronized int nextNumberCourse() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idCourse FROM "
                    + CalendarManager.TABLE_COURSE
                    + " ORDER BY idCourse DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idCourse")+1;
            }else{
                return 1;
            }
            connect.commit();
            return c;
        } 
     }
     
     public synchronized int nextNumberLesson() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idLesson FROM "
                    + CalendarManager.TABLE_LESSON
                    + " ORDER BY idLesson DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idLesson")+1;
            }else{
                return 1;
            }
            connect.commit();
            return c;
        } 
     }
     
     public synchronized int nextNumberSeminar() throws SQLException, IOException {
        int c=1;
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idSeminar FROM "
                    + CalendarManager.TABLE_SEMINAR
                    + " ORDER BY idSeminar DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idSeminar")+1;
            }else{
                return 1;
            }
            connect.commit();
            return c;
        } 
     }

    public synchronized  ArrayList<Lesson> getLessonsOfProfessor(int fkCourse, Professor loggedPerson) throws SQLException, IOException {
        
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        
        try (Connection connect = DBConnection.getConnection()){
           String fkProfessor=loggedPerson.getSecondaryEmail();
           
            /*
             * Prepariamo la stringa SQL per ricercare uno o piu' record 
             * nella tabella lesson
             */
           
           
            String tSql = "SELECT * FROM "
                          + CalendarManager.TABLE_LESSON
                          + " JOIN keep ON "
                          + CalendarManager.TABLE_LESSON
                          + ".idLesson = keep.fkLesson WHERE keep.fkProfessor = '"
                          + fkProfessor
                          + "' and " 
                          + CalendarManager.TABLE_LESSON + ".fkCourse = "
                          + fkCourse
                          + " ORDER BY "
                          + CalendarManager.TABLE_LESSON + ".date, " 
                          + CalendarManager.TABLE_LESSON + ".name";
           
            ResultSet result = Utility.queryOperation(connect, tSql);
           
            while (result.next()) {
                
                Lesson lesson = new Lesson();
                lesson.setIdLesson(result.getInt("idLesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getString("startTime"));
                lesson.setEndTime(result.getString("endTime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("desription"));                             
                lesson.setFK_course(result.getInt("fkCourse"));
                lesson.setStatus(result.getString("status"));
                
                lessons.add(lesson);
            }
           
            return lessons;
        }
    }
     /** Metodo della classe che ritorna la lista dei nomi dei corsi di un dato ciclo e curriculum
      * 
      * @param cycle 
      * @param curriculum
      * @return
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws IOException 
      */
     public synchronized ArrayList<String> getCourseListName(int cycle, String curriculum) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        ArrayList<String> courses = new ArrayList<>();
        try {
            
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM course "
                    + " WHERE fkCycle ="+cycle+" AND fkCurriculum='"+curriculum+"'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            connect.commit();
            while (result.next()) {
                courses.add(result.getString("name"));
            }

           

        } finally {
            DBConnection.releaseConnection(connect);
        }
         return courses;
    }
     
    public synchronized void setStatusLesson(int idLesson,String status) throws SQLException {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella lesson
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_LESSON
                    + " set status = '" 
                    + status
                    + "' WHERE idLesson = "
                    + idLesson;           

            Utility.executeOperation(connect, tSql);
            connect.commit();
            
        }finally {
            DBConnection.releaseConnection(connect);
        } 
    }
}

