/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.phdCourse.Seminar;
import java.util.ArrayList;
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
    
    @Before
    public void setUp() {
        instance = ActivityRegisterManager.getInstance();
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
        Activity activity = new Activity();
        activity.setName("Test");
        activity.setDescription("UnitTest");
        activity.setStartDateTime("2016-06-28 09:00:00");
        activity.setEndDateTime("2016-06-28 11:00:00");
        activity.setTypology("Test");
        activity.setFkPhdStudent("dinucci@hotmail.it");
        String idSeminar = "N/A";
        instance.insertActivity(activity, idSeminar);
    }

    /**
     * Test of getActivityRegisterOf method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetActivityRegisterOf() throws Exception {
        System.out.println("getActivityRegisterOf");
        String idStudent = "dinucci@hotmail.it";
        String annoInizio = "2015";
        ArrayList<Activity> result = instance.getActivityRegisterOf(idStudent, annoInizio);
        assertNotNull(result);
    }

    /**
     * Test of updateActivity method, of class ActivityRegisterManager.
     */
    @Test @Ignore
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        int oldActivityID = 0;
        Activity newActivity = null;
        String fkPhdStudent = "";
        instance.updateActivity(oldActivityID, newActivity, fkPhdStudent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String fkPhdStudent = "dinucci@hotmail.it";
        String annoInizio = "2015";
        ArrayList<Seminar> result = instance.getSeminarActivitiesByStudent(fkPhdStudent, annoInizio);
        assertNotNull(result);
     }

    /**
     * Test of getSeminarCoursesOfStudent method, of class ActivityRegisterManager.
     */
    @Test
    public void testGetSeminarCoursesOfStudent() {
        System.out.println("getSeminarCoursesOfStudent");
        String fkPhdStudent = "dinucci@hotmail.it";
        String annoInizio = "2015";
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
