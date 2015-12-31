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
     */
    @Test
    public void testGetPresenceList() throws Exception {
        int idlesson=0;
        instance.getPresenceList(idlesson);
    }

    /**
     * Test of getPresenceCourse method, of class PresenceManager.
     */
    @Test
    public void testGetPresenceCourse() throws Exception {
        
    }
    /**
     * Test of getPresence method, of class PresenceManager.
     */
    @Test
    public void testGetPresence() throws Exception {
        
    }

    /**
     * Test of modifyPresence method, of class PresenceManager.
     */
    @Test
    public void testModifyPresence() throws Exception {
        
    }
}
