/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.curriculumcic;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.account.Professor;
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
public class CurriculumcicManagerTest {
    
    public CurriculumcicManagerTest() {
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
     * Test of getInstance method, of class CurriculumcicManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        CurriculumcicManager expResult = null;
        CurriculumcicManager result = CurriculumcicManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     */
    @Test
    public void testInsertProfessor() throws Exception {
        System.out.println("insertProfessor");
        Curriculumcic pCurriculumcic = null;
        String fkProfessor = "";
        CurriculumcicManager instance = null;
        instance.insertProfessor(pCurriculumcic, fkProfessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     */
    @Test
    public void testInsertCurriculumcicCoordinator() throws Exception {
        System.out.println("insertCurriculumcicCoordinator");
        Curriculumcic pCurriculumcic = null;
        String fkProfessor = "";
        CurriculumcicManager instance = null;
        instance.insertCurriculumcicCoordinator(pCurriculumcic, fkProfessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     */
    @Test
    public void testDeleteCurriculumcicCoordinator() throws Exception {
        System.out.println("deleteCurriculumcicCoordinator");
        Curriculumcic pCurriculumcic = null;
        CurriculumcicManager instance = null;
        instance.deleteCurriculumcicCoordinator(pCurriculumcic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewCurriculumcicCoordinator method, of class CurriculumcicManager.
     */
    @Test
    public void testViewCurriculumcicCoordinator() throws Exception {
        System.out.println("viewCurriculumcicCoordinator");
        Curriculumcic pCurriculumcic = null;
        CurriculumcicManager instance = null;
        Professor expResult = null;
        Professor result = instance.viewCurriculumcicCoordinator(pCurriculumcic);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DeleteProfessor method, of class CurriculumcicManager.
     */
    @Test
    public void testDeleteProfessor() throws Exception {
        System.out.println("DeleteProfessor");
        Curriculumcic pCurriculumcic = null;
        String fkProfessor = "";
        CurriculumcicManager instance = null;
        instance.DeleteProfessor(pCurriculumcic, fkProfessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     */
    @Test
    public void testInsertPhdSudent() throws Exception {
        System.out.println("insertPhdSudent");
        Curriculumcic pCurriculumcic = null;
        String fkPhdstudent = "";
        CurriculumcicManager instance = null;
        instance.insertPhdSudent(pCurriculumcic, fkPhdstudent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     */
    @Test
    public void testViewProfessorList() throws Exception {
        System.out.println("viewProfessorList");
        Curriculumcic curriculumcic = null;
        CurriculumcicManager instance = null;
        ArrayList<Professor> expResult = null;
        ArrayList<Professor> result = instance.viewProfessorList(curriculumcic);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     */
    @Test
    public void testViewPhdstudentCurriculumcic() throws Exception {
        System.out.println("viewPhdstudentCurriculumcic");
        Curriculumcic curriculumcic = null;
        CurriculumcicManager instance = null;
        ArrayList<PhdStudent> expResult = null;
        ArrayList<PhdStudent> result = instance.viewPhdstudentCurriculumcic(curriculumcic);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     */
    @Test
    public void testDeletePhdSudent() throws Exception {
        System.out.println("DeletePhdSudent");
        String fkPhdstudent = "";
        CurriculumcicManager instance = null;
        instance.DeletePhdSudent(fkPhdstudent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testCurriculucic method, of class CurriculumcicManager.
     */
    @Test
    public void testTestCurriculucic() throws Exception {
        System.out.println("testCurriculucic");
        Curriculumcic c = null;
        CurriculumcicManager instance = null;
        Curriculumcic expResult = null;
        Curriculumcic result = instance.testCurriculucic(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testFkPhdstudent method, of class CurriculumcicManager.
     */
    @Test
    public void testTestFkPhdstudent() throws Exception {
        System.out.println("testFkPhdstudent");
        String s = "";
        CurriculumcicManager instance = null;
        String expResult = "";
        String result = instance.testFkPhdstudent(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
