/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
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
    
    // metodo per aggiungere un record alla tabella course del db
    public synchronized void insert_course(Course pCourse) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella course
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_COURSE
                    + " ( idcourse, curriculum, cycle, name)"
                    + " VALUES ('"
                    + pCourse.getIdCourse()
                    + "','"
                    + Utility.Replace(pCourse.getCurriculum())
                    + "','"
                    + pCourse.getCycle()
                    + "','"
                    + Utility.Replace(pCourse.getName())
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    // metodo per aggiungere un record alla tabella lesson del db
    public synchronized void insert_lesson(Lesson pLesson) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella course
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_LESSON
                    + " ( idlesson, date, starttime, endtime, name, classroom, desription, cycle, curriculum, course)"
                    + " VALUES ('"
                    + pLesson.getIdLesson() // int
                    + "','"
                    + pLesson.getData() // Date
                    + "','"
                    + pLesson.getStartTime() // int
                    + "','"
                    + pLesson.getEndTime() // int 
                    + "','"
                    + Utility.Replace(pLesson.getName()) // String
                    + "','"
                    + Utility.Replace(pLesson.getClassroom()) // String
                      + "','"
                    + Utility.Replace(pLesson.getDescription()) // String
                      + "','"
                    + pLesson.getCycle() // int
                      + "','"
                    + Utility.Replace(pLesson.getCurriculum())
                     + "','"
                    + pLesson.getFK_course() // int
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    
    // metodo per aggiungere un record alla tabella seminar del db
    public synchronized void insert_seminar(Seminar pSeminar) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella course
             */
            String tSql = "INSERT INTO "
                    + CalendarManager.TABLE_SEMINAR
                    + " ( idseminar, date, starttime, endtime, name, namespeacker, desription, place, course)"
                    + " VALUES ('"
                    + pSeminar.getIdSeminar() // int
                    + "','"
                    + pSeminar.getData() // Date
                    + "','"
                    + pSeminar.getStartTime() // int
                    + "','"
                    + pSeminar.getEndTime() // int 
                    + "','"
                    + Utility.Replace(pSeminar.getName()) // String
                    + "','"
                    + Utility.Replace(pSeminar.getNameSpeacker()) // String
                      + "','"
                    + Utility.Replace(pSeminar.getDescription()) // String
                      + "','"
                    + Utility.Replace(pSeminar.getPlace())
                      + "','"
                    + pSeminar.getFK_course()
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    
    // metodo per modificare una lezione
     public synchronized void update_lesson(int oldLessonID, Lesson pLesson) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_LESSON
                    + " set idlesson = '"
                    + pLesson.getIdLesson()
                    + "', date = '"
                    + pLesson.getData()
                    + "', starttime = '"
                    + pLesson.getStartTime()
                    + "', endtime = '"
                    + pLesson.getEndTime()
                    + "', name = '"
                    + Utility.Replace(pLesson.getName())
                    + "', classroom = '"
                    + Utility.Replace(pLesson.getClassroom())
                    + "', description = '"
                    + Utility.Replace(pLesson.getDescription())
                    + "', cycle = '"
                    + pLesson.getCycle()
                    + "', curriculum = '"
                    + Utility.Replace(pLesson.getCurriculum())
                    + "', course = '"
                    + pLesson.getFK_course()
                    + "' WHERE idLesson = "
                    + oldLessonID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
     
     
      // metodo per modificare un seminario
     public synchronized void update_seminar(int oldSeminarID, Seminar pSeminar) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CalendarManager.TABLE_SEMINAR
                    + " set idseminar = '"
                    + pSeminar.getIdSeminar()
                    + "', date = '"
                    + pSeminar.getData()
                    + "', starttime = '"
                    + pSeminar.getStartTime()
                    + "', endtime = '"
                    + pSeminar.getEndTime()
                    + "', name = '"
                    + Utility.Replace(pSeminar.getName())
                    + "', namespeacker = '"
                    + Utility.Replace(pSeminar.getNameSpeacker())
                    + "', description = '"
                    + Utility.Replace(pSeminar.getDescription())
                    + "', place = '"
                    + Utility.Replace(pSeminar.getPlace())
                    + "', course = '"
                    + pSeminar.getFK_course()
                    + "' WHERE idLesson = "
                    + oldSeminarID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
     
     
     // metodo per eliminare un record dalla tabella lesson
     public synchronized void delete_lesson(int idLesson) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE idCollaboration = '"
                    + idLesson + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     // metodo per eliminare un record dalla tabella seminar
     public synchronized void delete_seminar(int idSeminar) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE idCollaboration = '"
                    + idSeminar + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     // metodo che restituisce una lezione in base all'Id
     public synchronized Lesson getLessonById(int pLessonID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Lesson lesson = new Lesson();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_LESSON
                    + " WHERE idCollaboration = '"
                    + pLessonID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                lesson.setIdLesson(result.getInt("idlesson"));
                lesson.setDate(result.getDate("date"));
                lesson.setStartTime(result.getInt("starttime"));
                lesson.setEndTime(result.getInt("endtime"));
                lesson.setName(result.getString("name"));
                lesson.setClassroom(result.getString("classroom"));
                lesson.setDescription(result.getString("description"));
                lesson.setCycle(result.getInt("cycle"));
                lesson.setCurriculum(result.getString("curriculum"));
                lesson.setFK_course(result.getInt("course"));
            }

            return lesson;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     // metodo che restituisce un seminario in base all'Id
     public synchronized Seminar getSeminarById(int pSeminarID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            Seminar seminar = new Seminar();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CalendarManager.TABLE_SEMINAR
                    + " WHERE idCollaboration = '"
                    + pSeminarID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                seminar.setIdSeminar(result.getInt("idseminar"));
                seminar.setDate(result.getDate("date"));
                seminar.setStartTime(result.getInt("starttime"));
                seminar.setEndTime(result.getInt("endtime"));
                seminar.setName(result.getString("name"));
                seminar.setNameSpeacker(result.getString("namespeacker"));
                seminar.setDescription(result.getString("description"));
                seminar.setPlace(result.getString("place"));
                seminar.setFK_course(result.getInt("course"));
            }

            return seminar;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
}
