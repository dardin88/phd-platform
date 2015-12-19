/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.collaborations;

import it.unisa.dottorato.account.PhdStudent;
import java.util.Date;
import java.util.List;
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
public class CollaborationManagerTest {
    
    public CollaborationManagerTest() {
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
     * Test of getInstance method, of class CollaborationManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        CollaborationManager expResult = null;
        CollaborationManager result = CollaborationManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class CollaborationManager.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Collaboration pCollaboration = null;
        CollaborationManager instance = null;
        instance.insert(pCollaboration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class CollaborationManager.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        int oldCollaborationID = 0;
        Collaboration pCollaboration = null;
        CollaborationManager instance = null;
        instance.update(oldCollaborationID, pCollaboration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class CollaborationManager.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String idCollaboration = "";
        CollaborationManager instance = null;
        instance.delete(idCollaboration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     */
    @Test
    public void testGetCollaborationById() throws Exception {
        System.out.println("getCollaborationById");
        int pCollaborationID = 0;
        CollaborationManager instance = null;
        Collaboration expResult = null;
        Collaboration result = instance.getCollaborationById(pCollaborationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     */
    @Test
    public void testGetAllCollaborationOf() throws Exception {
        System.out.println("getAllCollaborationOf");
        PhdStudent phdStudent = null;
        CollaborationManager instance = null;
        List<Collaboration> expResult = null;
        List<Collaboration> result = instance.getAllCollaborationOf(phdStudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testId method, of class CollaborationManager.
     */
    @Test
    public void testTestId() throws Exception {
        System.out.println("testId");
        int id = 0;
        CollaborationManager instance = null;
        int expResult = 0;
        int result = instance.testId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDescription method, of class CollaborationManager.
     */
    @Test
    public void testTestDescription() throws Exception {
        System.out.println("testDescription");
        String description = "";
        CollaborationManager instance = null;
        String expResult = "";
        String result = instance.testDescription(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testStartDate method, of class CollaborationManager.
     */
    @Test
    public void testTestStartDate() throws Exception {
        System.out.println("testStartDate");
        Date startDate = null;
        CollaborationManager instance = null;
        Date expResult = null;
        Date result = instance.testStartDate(startDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testEndDate method, of class CollaborationManager.
     */
    @Test
    public void testTestEndDate() throws Exception {
        System.out.println("testEndDate");
        Date endDate = null;
        CollaborationManager instance = null;
        Date expResult = null;
        Date result = instance.testEndDate(endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testIstitution method, of class CollaborationManager.
     */
    @Test
    public void testTestIstitution() throws Exception {
        System.out.println("testIstitution");
        String istitution = "";
        CollaborationManager instance = null;
        String expResult = "";
        String result = instance.testIstitution(istitution);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testCollaboration method, of class CollaborationManager.
     */
    @Test
    public void testTestCollaboration() throws Exception {
        System.out.println("testCollaboration");
        Collaboration c = null;
        CollaborationManager instance = null;
        Collaboration expResult = null;
        Collaboration result = instance.testCollaboration(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testfkPhdStudent method, of class CollaborationManager.
     */
    @Test
    public void testTestfkPhdStudent() throws Exception {
        System.out.println("testfkPhdStudent");
        String fkPhdstudent = "";
        CollaborationManager instance = null;
        String expResult = "";
        String result = instance.testfkPhdStudent(fkPhdstudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumber method, of class CollaborationManager.
     */
    @Test
    public void testNextNumber() throws Exception {
        System.out.println("nextNumber");
        CollaborationManager instance = null;
        int expResult = 0;
        int result = instance.nextNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
