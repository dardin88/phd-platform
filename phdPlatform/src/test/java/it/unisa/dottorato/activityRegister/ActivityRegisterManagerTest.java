/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.phdCourse.Seminar;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Liliana
 */
public class ActivityRegisterManagerTest {
    
    private ActivityRegisterManager instance;
    private String fkPhdStudent,annoInizio;
    private Activity activity;
    private int idActivity;
    
    @Before
    public void setUp() {
        try {
            
            fkPhdStudent = "dinucci@hotmail.it";
            annoInizio = "2015";
            
            //inserimento attività nel DB
            instance = ActivityRegisterManager.getInstance();
            
            activity = new Activity();
            activity.setName("Test");
            activity.setDescription("UnitTest");
            activity.setStartDateTime("2016-06-28 09:00:00");
            activity.setEndDateTime("2016-06-28 11:00:00");
            activity.setTypology("Test");
            activity.setFkPhdStudent("dinucci@hotmail.it");
            
            
            instance.insertActivity(activity, "N/A");
            
            //Recupero ultima attività inserita
            ArrayList<Activity> result = instance.getActivityRegisterOf(fkPhdStudent,annoInizio);
            int max = 0;
            for(int i=0; i< result.size();i++){
                if(max < result.get(i).getIdActivity() && !result.get(i).getTypology().equalsIgnoreCase("Lezione"))
                    max = result.get(i).getIdActivity();
            }
            System.out.println("max:"+max);
            idActivity = max;
            
        } catch (Exception ex) {
            Logger.getLogger(ActivityRegisterManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @After
     public void tearDown(  ) {
        try {   
            instance.deleteActivity(idActivity, fkPhdStudent);
        } catch (Exception ex) {
            Logger.getLogger(ActivityRegisterManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test of getInstance method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ActivityRegisterManager result = ActivityRegisterManager.getInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of insertActivity method, of class ActivityRegisterManager.
     */
    @Test @Ignore 
    public void testInsertActivity() throws Exception {
        System.out.println("insertActivity");
        instance.insertActivity(activity, "N/A");
    }

    /**
     * Test of getActivityRegisterOf method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetActivityRegisterOf() throws Exception {
        System.out.println("getActivityRegisterOf");      
        ArrayList<Activity> result = instance.getActivityRegisterOf(fkPhdStudent, annoInizio);
        assertNotNull(result);
    }

    /**
     * Test of updateActivity method, of class ActivityRegisterManager.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        
        Activity newactivity = new Activity();
            newactivity.setName("NameChangeByTest");
            newactivity.setDescription("UnitTest");
            newactivity.setStartDateTime("2016-06-28 09:00:00");
            newactivity.setEndDateTime("2016-06-28 11:00:00");
            newactivity.setTypology("Test");
            newactivity.setFkPhdStudent("dinucci@hotmail.it");
        instance.updateActivity(idActivity, newactivity, fkPhdStudent);
        
        ArrayList<Activity> result = instance.getActivityRegisterOf(fkPhdStudent,annoInizio);
            for(int i=0; i< result.size();i++){
                if(idActivity == result.get(i).getIdActivity()){
                    System.out.println(result.get(i).getName()+" "+result.get(i).getIdActivity());
                    assertEquals(result.get(i).getName(),"NameChangeByTest");}
            }
    }

    /**
     * Test of deleteActivity method, of class ActivityRegisterManager.
     */
    @Test @Ignore
    public void testDeleteActivity() throws Exception {
        System.out.println("deleteActivity");
        int idActivity = 0;
        String fkPhdStudent = "";
        instance.deleteActivity(idActivity, fkPhdStudent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeminarActivitiesByStudent method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetSeminarActivitiesByStudent() {
        System.out.println("getSeminarActivitiesByStudent");
        ArrayList<Seminar> result = instance.getSeminarActivitiesByStudent(fkPhdStudent, annoInizio);
        assertNotNull(result);
    }

    /**
     * Test of getSeminarCoursesOfStudent method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetSeminarCoursesOfStudent() {
        System.out.println("getSeminarCoursesOfStudent");
        ArrayList<Course> result = instance.getSeminarCoursesOfStudent(fkPhdStudent, annoInizio);
        assertNotNull(result);
    }

    /**
     * Test of getTypology method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetTypology() throws Exception {
        System.out.println("getTypology");
        ArrayList<Typology> result = instance.getTypology();
        assertNotNull(result);
    }
    
}
