/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.publications;

import it.unisa.dottorato.account.PhdStudent;
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
public class PublicationManagerTest {
    
    public PublicationManagerTest() {
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
     * Test of getInstance method, of class PublicationManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PublicationManager expResult = null;
        PublicationManager result = PublicationManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class PublicationManager.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Publication pPublication = null;
        PublicationManager instance = null;
        instance.insert(pPublication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PublicationManager.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        int oldPublicationID = 0;
        Publication pPublication = null;
        PublicationManager instance = null;
        instance.update(oldPublicationID, pPublication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PublicationManager.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String idPublication = "";
        PublicationManager instance = null;
        instance.delete(idPublication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPublicationById method, of class PublicationManager.
     */
    @Test
    public void testGetPublicationById() throws Exception {
        System.out.println("getPublicationById");
        int pPublicationID = 0;
        PublicationManager instance = null;
        Publication expResult = null;
        Publication result = instance.getPublicationById(pPublicationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPublicationsOf method, of class PublicationManager.
     */
    @Test
    public void testGetAllPublicationsOf() throws Exception {
        System.out.println("getAllPublicationsOf");
        PhdStudent phdStudent = null;
        PublicationManager instance = null;
        List<Publication> expResult = null;
        List<Publication> result = instance.getAllPublicationsOf(phdStudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testId method, of class PublicationManager.
     */
    @Test
    public void testTestId() throws Exception {
        System.out.println("testId");
        int id = 0;
        PublicationManager instance = null;
        int expResult = 0;
        int result = instance.testId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testTitle method, of class PublicationManager.
     */
    @Test
    public void testTestTitle() throws Exception {
        System.out.println("testTitle");
        String title = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPubliocationIssue method, of class PublicationManager.
     */
    @Test
    public void testTestPubliocationIssue() throws Exception {
        System.out.println("testPubliocationIssue");
        String publicationIssue = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testPubliocationIssue(publicationIssue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testYear method, of class PublicationManager.
     */
    @Test
    public void testTestYear() throws Exception {
        System.out.println("testYear");
        String year = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testYear(year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testNumberPage method, of class PublicationManager.
     */
    @Test
    public void testTestNumberPage() throws Exception {
        System.out.println("testNumberPage");
        int numberPage = 0;
        PublicationManager instance = null;
        int expResult = 0;
        int result = instance.testNumberPage(numberPage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testLink method, of class PublicationManager.
     */
    @Test
    public void testTestLink() throws Exception {
        System.out.println("testLink");
        String link = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testLink(link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testType method, of class PublicationManager.
     */
    @Test
    public void testTestType() throws Exception {
        System.out.println("testType");
        String type = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testOtherAutors method, of class PublicationManager.
     */
    @Test
    public void testTestOtherAutors() throws Exception {
        System.out.println("testOtherAutors");
        String otherAutors = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testOtherAutors(otherAutors);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testAbstract method, of class PublicationManager.
     */
    @Test
    public void testTestAbstract() throws Exception {
        System.out.println("testAbstract");
        String pAbstract = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testAbstract(pAbstract);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPublication method, of class PublicationManager.
     */
    @Test
    public void testTestPublication() throws Exception {
        System.out.println("testPublication");
        Publication p = null;
        PublicationManager instance = null;
        Publication expResult = null;
        Publication result = instance.testPublication(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testfkPhdStudent method, of class PublicationManager.
     */
    @Test
    public void testTestfkPhdStudent() throws Exception {
        System.out.println("testfkPhdStudent");
        String fkPhdstudent = "";
        PublicationManager instance = null;
        String expResult = "";
        String result = instance.testfkPhdStudent(fkPhdstudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumber method, of class PublicationManager.
     */
    @Test
    public void testNextNumber() throws Exception {
        System.out.println("nextNumber");
        PublicationManager instance = null;
        int expResult = 0;
        int result = instance.nextNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
