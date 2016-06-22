/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.phdCourse.Course;
import it.unisa.dottorato.phdCourse.Lesson;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author cadav
 */
public class PresenceManagerTest {
    
    private PresenceManager result;
    
    
     @Before
    public void setUp(){
        result = PresenceManager.getInstance();
        System.out.println("@Before - setUp");
    }
    

    
   

    /**
     * Test of getInstance method, of class PresenceManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PresenceManager expResult =PresenceManager.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourseByProfessor method, of class PresenceManager.
     */
    @Test 
    public void testGetCourseByProfessor() throws Exception {
        System.out.println("getCourseByProfessor");
        String Professor = "adelucia@hotmail.it";
        ArrayList<Course> list = result.getCourseByProfessor(Professor);
        assertNotNull(list);
    }

    /**
     * Test of insertPresence method, of class PresenceManager.
     */
    @Test @Ignore
    public void testInsertPresence() throws Exception {
        System.out.println("insertPresence");
        int numbercic = 0;
        int fkCourse = 0;
        PresenceManager instance = null;
        instance.insertPresence(numbercic, fkCourse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceDottorandi method, of class PresenceManager.
     */
    @Test @Ignore
    public void testGetPresenceDottorandi() throws Exception {
        System.out.println("getPresenceDottorandi");
        int idCorso = 0;
        PresenceManager instance = null;
        ArrayList<Account> expResult = null;
        ArrayList<Account> result = instance.getPresenceDottorandi(idCorso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceToLesson method, of class PresenceManager.
     */
    @Test @Ignore
    public void testGetPresenceToLesson() throws Exception {
        System.out.println("getPresenceToLesson");
        String dottorando = "";
        int idCorso = 0;
        PresenceManager instance = null;
        ArrayList<Presence> expResult = null;
        ArrayList<Presence> result = instance.getPresenceToLesson(dottorando, idCorso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyPresence method, of class PresenceManager.
     */
    @Test @Ignore
    public void testModifyPresence() throws Exception {
        System.out.println("modifyPresence");
        String dottorando = "";
        int idLesson = 0;
        PresenceManager instance = null;
        instance.modifyPresence(dottorando, idLesson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDottorando method, of class PresenceManager.
     */
    @Test @Ignore
    public void testTestDottorando() throws Exception {
        System.out.println("testDottorando");
        String email = "";
        PresenceManager instance = null;
        String expResult = "";
        String result = instance.testDottorando(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testid method, of class PresenceManager.
     */
    @Test @Ignore
    public void testTestid() throws Exception {
        System.out.println("testid");
        int id = 0;
        PresenceManager instance = null;
        int expResult = 0;
        int result = instance.testid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorsobyDottorando method, of class PresenceManager.
     */
    @Test
    public void testGetCorsobyDottorando() throws Exception {
        System.out.println("getCorsobyDottorando");
        String idDottorando = "'ariemma@hotmail.it'";
        int ciclo = 16;
        ArrayList<Course> list = result.getCorsobyDottorando(idDottorando, ciclo);
        assertNotNull(list);
    }

    /**
     * Test of getTotalLesson method, of class PresenceManager.
     */
    @Test
    public void testGetTotalLesson() throws Exception {
        System.out.println("getTotalLesson");
        String idDottorando = "'ariemma@hotmail.it'";
        String CourseName = "'IS2'";
        int CycleNumber = 16;
        ArrayList<Presence> list = result.getTotalLesson(idDottorando, CourseName, CycleNumber);
        assertNotNull(list);
    }

    /**
     * Test of getLessonsWherePresentStudent method, of class PresenceManager.
     */
    @Test @Ignore
    public void testGetLessonsWherePresentStudent() throws Exception {
        System.out.println("getLessonsWherePresentStudent");
        String idStudent = "";
        PresenceManager instance = null;
        ArrayList<Lesson> expResult = null;
        ArrayList<Lesson> result = instance.getLessonsWherePresentStudent(idStudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfessorCourse method, of class PresenceManager.
     */
    @Test
    public void testGetProfessorCourse() throws Exception {
        System.out.println("getProfessorCourse");
        String Professor = "buonocore@hotmail.it";
        int cycle = 16;
        ArrayList<Course> list = result.getProfessorCourse(Professor, cycle);
        assertNotNull(list);
    }

    /**
     * Test of getLessonsHours method, of class PresenceManager.
     */
    @Test
    public void testGetLessonsHours() throws Exception {
        System.out.println("getLessonsHours");
        String idStudent = "'ariemma@hotmail.it'";
        int cycle = 16;
        String courseName = "'IS2'";
        ArrayList<Lesson> list = result.getLessonsHours(idStudent, cycle, courseName);
        assertNotNull(list);
    }

    /**
     * Test of getLessonsHoursTotal method, of class PresenceManager.
     */
    @Test
    public void testGetLessonsHoursTotal() throws Exception {
        System.out.println("getLessonsHoursTotal");
        String courseName = "'IS2'";
        int cycle = 16;
        ArrayList<Lesson> list = result.getLessonsHoursTotal(courseName, cycle);
        assertNotNull(list);
    }

    /**
     * Test of getPresencesLesson method, of class PresenceManager.
     */
    @Test @Ignore
    public void testGetPresencesLesson() throws Exception {
        System.out.println("getPresencesLesson");
        int fkLesson = 0;
        PresenceManager instance = null;
        ArrayList<Presence> expResult = null;
        ArrayList<Presence> list = result.getPresencesLesson(fkLesson);
        
    }

    /**
     * Test of changeAllPresencesLesson method, of class PresenceManager.
     */
    @Test @Ignore
    public void testChangeAllPresencesLesson() throws Exception {
        System.out.println("changeAllPresencesLesson");
        int fkLesson = 0;
        int isPresent = 0;
        PresenceManager instance = null;
        instance.changeAllPresencesLesson(fkLesson, isPresent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
