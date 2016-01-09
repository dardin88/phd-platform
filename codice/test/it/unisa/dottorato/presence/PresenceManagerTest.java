/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.phdCourse.Course;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso
 */
public class PresenceManagerTest {
    private PresenceManager instance;
    private Presence pre;
    
    public PresenceManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=PresenceManager.getInstance();
        pre=new Presence();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class PresenceManager.
     */
    @Test
    public void testGetInstance() {
        PresenceManager result=PresenceManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getCourseByProfessor method, of class PresenceManager.
     */
    @Test
    public void testGetCourseByProfessor() throws Exception {
        System.out.println("getCourseByProfessor");
        String Professor = "";
        PresenceManager instance = null;
        ArrayList<Course> expResult = null;
        ArrayList<Course> result = instance.getCourseByProfessor(Professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPresence method, of class PresenceManager.
     */
    @Test
    public void testInsertPresence() throws Exception {
        System.out.println("insertPresence");
        Presence dottorando = null;
        PresenceManager instance = null;
        instance.insertPresence(dottorando);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceList method, of class PresenceManager.
     */
    @Test
    public void testGetPresenceList() throws Exception {
        System.out.println("getPresenceList");
        int lesson = 0;
        PresenceManager instance = null;
        ArrayList<Presence> expResult = null;
        ArrayList<Presence> result = instance.getPresenceList(lesson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceDottorandi method, of class PresenceManager.
     */
    @Test
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
    @Test
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
    @Test
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
    @Test
    public void testTestDottorando() throws Exception {
        System.out.println("testDottorando");
        String title = "";
        PresenceManager instance = null;
        String expResult = "";
        String result = instance.testDottorando(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testid method, of class PresenceManager.
     */
    @Test
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
    
}
