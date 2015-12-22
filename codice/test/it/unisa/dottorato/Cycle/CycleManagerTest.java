/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.Curriculum.Curriculum;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.curriculumcic.Curriculumcic;
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
public class CycleManagerTest {
    
    public CycleManagerTest() {
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
     * Test of getInstance method, of class CycleManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        CycleManager expResult = null;
        CycleManager result = CycleManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCycle method, of class CycleManager.
     */
    @Test
    public void testInsertCycle() throws Exception {
        System.out.println("insertCycle");
        Cycle pCycle = null;
        CycleManager instance = null;
        instance.insertCycle(pCycle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycle() throws Exception {
        System.out.println("updateCycle");
        int Number = 0;
        Cycle pCycle = null;
        CycleManager instance = null;
        instance.updateCycle(Number, pCycle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinator() throws Exception {
        System.out.println("insertCycleCoordinator");
        int number = 0;
        String fkProfessor = "";
        CycleManager instance = null;
        instance.insertCycleCoordinator(number, fkProfessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleCoordinator() throws Exception {
        System.out.println("deleteCycleCoordinator");
        int number = 0;
        CycleManager instance = null;
        instance.deleteCycleCoordinator(number);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testViewCycleCoordinator() throws Exception {
        System.out.println("viewCycleCoordinator");
        int number = 0;
        CycleManager instance = null;
        Professor expResult = null;
        Professor result = instance.viewCycleCoordinator(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCycle method, of class CycleManager.
     */
    @Test
    public void testDeleteCycle() throws Exception {
        System.out.println("deleteCycle");
        int number = 0;
        CycleManager instance = null;
        instance.deleteCycle(number);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCycleByNumber method, of class CycleManager.
     */
    @Test
    public void testGetCycleByNumber() throws Exception {
        System.out.println("getCycleByNumber");
        int number = 0;
        CycleManager instance = null;
        Cycle expResult = null;
        Cycle result = instance.getCycleByNumber(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCycleList method, of class CycleManager.
     */
    @Test
    public void testGetCycleList() throws Exception {
        System.out.println("getCycleList");
        CycleManager instance = null;
        ArrayList<Cycle> expResult = null;
        ArrayList<Cycle> result = instance.getCycleList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCyclesListNumers method, of class CycleManager.
     */
    @Test
    public void testGetCyclesListNumers() throws Exception {
        System.out.println("getCyclesListNumers");
        CycleManager instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getCyclesListNumers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     */
    @Test
    public void testViewCollegeCycle() throws Exception {
        System.out.println("viewCollegeCycle");
        int number = 0;
        CycleManager instance = null;
        ArrayList<Professor> expResult = null;
        ArrayList<Professor> result = instance.viewCollegeCycle(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcic() throws Exception {
        System.out.println("insertCurriculumcic");
        Curriculumcic pCurriculumcic = null;
        CycleManager instance = null;
        instance.insertCurriculumcic(pCurriculumcic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcic() throws Exception {
        System.out.println("deleteCurriculumcic");
        int fkCycle = 0;
        String fkCurriculum = "";
        CycleManager instance = null;
        instance.deleteCurriculumcic(fkCycle, fkCurriculum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurriculumcicList method, of class CycleManager.
     */
    @Test
    public void testGetCurriculumcicList() throws Exception {
        System.out.println("getCurriculumcicList");
        int number = 0;
        CycleManager instance = null;
        ArrayList<Curriculum> expResult = null;
        ArrayList<Curriculum> result = instance.getCurriculumcicList(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumber method, of class CycleManager.
     */
    @Test
    public void testNextNumber() throws Exception {
        System.out.println("nextNumber");
        CycleManager instance = null;
        int expResult = 0;
        int result = instance.nextNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testNumber method, of class CycleManager.
     */
    @Test
    public void testTestNumber() throws Exception {
        System.out.println("testNumber");
        int number = 0;
        CycleManager instance = null;
        Integer expResult = null;
        Integer result = instance.testNumber(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDescription method, of class CycleManager.
     */
    @Test
    public void testTestDescription() throws Exception {
        System.out.println("testDescription");
        String description = "";
        CycleManager instance = null;
        String expResult = "";
        String result = instance.testDescription(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testYear method, of class CycleManager.
     */
    @Test
    public void testTestYear() throws Exception {
        System.out.println("testYear");
        String year = "";
        CycleManager instance = null;
        String expResult = "";
        String result = instance.testYear(year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existCycle method, of class CycleManager.
     */
    @Test
    public void testExistCycle() throws Exception {
        System.out.println("existCycle");
        Cycle c = null;
        CycleManager instance = null;
        boolean expResult = false;
        boolean result = instance.existCycle(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testCycle method, of class CycleManager.
     */
    @Test
    public void testTestCycle() throws Exception {
        System.out.println("testCycle");
        Cycle c = null;
        CycleManager instance = null;
        instance.testCycle(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testFkProfessor method, of class CycleManager.
     */
    @Test
    public void testTestFkProfessor() throws Exception {
        System.out.println("testFkProfessor");
        String s = "";
        CycleManager instance = null;
        String expResult = "";
        String result = instance.testFkProfessor(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
