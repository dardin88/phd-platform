/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

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
    
    public PresenceManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class PresenceManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PresenceManager expResult = null;
        PresenceManager result = PresenceManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceList method, of class PresenceManager.
     */
    @Test
    public void testGetPresenceList() throws Exception {
        System.out.println("getPresenceList");
        PresenceManager instance = null;
        ArrayList<Presence> expResult = null;
        ArrayList<Presence> result = instance.getPresenceList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangePermission method, of class PresenceManager.
     */
    @Test
    public void testChangePermission() {
        System.out.println("ChangePermission");
        boolean permission = false;
        PresenceManager instance = null;
        instance.ChangePermission(permission);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     */
    @Test
    public void testGetPresenceCourse() throws Exception {
        System.out.println("getPresenceCourse");
        PresenceManager instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getPresenceCourse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresence method, of class PresenceManager.
     */
    @Test
    public void testGetPresence() throws Exception {
        System.out.println("getPresence");
        int lesson = 0;
        PresenceManager instance = null;
        ArrayList<Presence> expResult = null;
        ArrayList<Presence> result = instance.getPresence(lesson);
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
        boolean signature = false;
        Presence old = null;
        PresenceManager instance = null;
        instance.modifyPresence(signature, old);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermission method, of class PresenceManager.
     */
    @Test
    public void testGetPermission() throws Exception {
        System.out.println("getPermission");
        PresenceManager instance = null;
        instance.getPermission();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
