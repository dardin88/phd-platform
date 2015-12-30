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
 * @author Tommaso Minichiello
 */
public class PresenceManagerTest {
    
    private Presence pre;
    private PresenceManager instance;
    
    public PresenceManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=PresenceManager.getInstance();
        pre= new Presence();
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
     * Test of getPresenceList method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceListOk() throws Exception {
        try{
            ArrayList<Presence> result = instance.getPresenceList();
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }


    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceCourseOk() throws Exception {
        try{
            ArrayList<Presence> result = instance.getPresenceCourse();
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of getPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceOk() throws Exception {
       
        int lesson = 1;
        try{
            ArrayList<Presence> result = instance.getPresence(lesson);
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceIdMmin() throws Exception {
       
        int lesson = -8;
        try{
            instance.getPresence(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of getPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceIdMmax() throws Exception {
       
        int lesson = 9999999;
        try{
            instance.getPresence(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceIdMNotExists() throws Exception {
       
        int lesson = 88989;
        try{
            instance.getPresence(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of modifyPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testModifyPresenceOk() throws Exception {
        boolean signature = false;
        //pre.setFkPhdstudent(fkPhdstudent);
        //pre.setFkLesson(fkLesson);
        pre.setIsPresent(signature);
        try{
            instance.modifyPresence(signature, pre);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        } 
    }
    
    /**
     * Test of modifyPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testModifyPresenceFkPhdstudentMin() throws Exception {
        boolean signature = false;
        //pre.setFkPhdstudent(fkPhdstudent);
        //pre.setFkLesson(fkLesson);
        pre.setIsPresent(signature);
        try{
            instance.modifyPresence(signature, pre);
            fail("non sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        } 
    }

    
}
