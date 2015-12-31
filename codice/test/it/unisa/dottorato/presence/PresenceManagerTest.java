/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso
 */
public class PresenceManagerTest {
    private Presence pre;
    private PresenceManager instance;
    
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
     * Test of getPresenceList method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceListOk() throws Exception {
        int idlesson=1;
        try{
            instance.getPresenceList(idlesson);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getPresenceList method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceListIdMin() throws Exception {
        int idlesson=-9;
        try{
            instance.getPresenceList(idlesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getPresenceList method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceListIdMax() throws Exception {
        int idlesson=9999999;
        try{
            instance.getPresenceList(idlesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceCourseOk() throws Exception {
        String studente="dinucci@hotmail.it";
        try{
            instance.getPresenceCourse(studente);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceCourseFkStudentNull() throws Exception {
        String studente=null;
        try{
            instance.getPresenceCourse(studente);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceCourseFkStudentMax() throws Exception {
        String studente="qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        try{
            instance.getPresenceCourse(studente);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceCourseFkStudentFormatError() throws Exception {
        String studente="qwertyuiopqwertyopqwertyuiopqwertyuiop";
        try{
            instance.getPresenceCourse(studente);
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
    public void testGetPresenceOk() throws Exception {
        int idlesson=1;
        try{
            instance.getPresence(idlesson);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getPresence method, of class PresenceManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPresenceIdlessonMin() throws Exception {
        int idlesson=-7;
        try{
            instance.getPresence(idlesson);
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
    public void testGetPresenceIdlessonMax() throws Exception {
        int idlesson=899898989;
        try{
            instance.getPresence(idlesson);
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
        String dottorando="dinucci@hotmail.it";
        try{
            instance.modifyPresence(dottorando);
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
    public void testModifyPresenceFkStuddentNull() throws Exception {
        String dottorando=null;
        try{
            instance.modifyPresence(dottorando);
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
    public void testModifyPresenceFkStuddentMax() throws Exception {
        String dottorando="qwertyuioplkqwertyuioplkqwertyuioplkqwertyuioplkqwertyuioplk";
        try{
            instance.modifyPresence(dottorando);
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
    public void testModifyPresenceFkStuddentFormatError() throws Exception {
        String dottorando="qwertyuioplkqwetyuioplkqwertyuioplk";
        try{
            instance.modifyPresence(dottorando);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
}
