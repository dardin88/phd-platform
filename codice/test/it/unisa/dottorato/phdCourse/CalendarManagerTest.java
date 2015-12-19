/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

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
public class CalendarManagerTest {
    
    public CalendarManagerTest() {
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
     * Test of getInstance method, of class CalendarManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        CalendarManager expResult = null;
        CalendarManager result = CalendarManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert_course method, of class CalendarManager.
     */
    @Test
    public void testInsert_course() throws Exception {
        System.out.println("insert_course");
        Course pCourse = null;
        CalendarManager instance = null;
        instance.insert_course(pCourse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert_lesson method, of class CalendarManager.
     */
    @Test
    public void testInsert_lesson() throws Exception {
        System.out.println("insert_lesson");
        Lesson pLesson = null;
        CalendarManager instance = null;
        instance.insert_lesson(pLesson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert_seminar method, of class CalendarManager.
     */
    @Test
    public void testInsert_seminar() throws Exception {
        System.out.println("insert_seminar");
        Seminar pSeminar = null;
        CalendarManager instance = null;
        instance.insert_seminar(pSeminar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update_lesson method, of class CalendarManager.
     */
    @Test
    public void testUpdate_lesson() throws Exception {
        System.out.println("update_lesson");
        int oldLessonID = 0;
        Lesson pLesson = null;
        CalendarManager instance = null;
        instance.update_lesson(oldLessonID, pLesson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update_seminar method, of class CalendarManager.
     */
    @Test
    public void testUpdate_seminar() throws Exception {
        System.out.println("update_seminar");
        int oldSeminarID = 0;
        Seminar pSeminar = null;
        CalendarManager instance = null;
        instance.update_seminar(oldSeminarID, pSeminar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete_lesson method, of class CalendarManager.
     */
    @Test
    public void testDelete_lesson() throws Exception {
        System.out.println("delete_lesson");
        int idLesson = 0;
        CalendarManager instance = null;
        instance.delete_lesson(idLesson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete_seminar method, of class CalendarManager.
     */
    @Test
    public void testDelete_seminar() throws Exception {
        System.out.println("delete_seminar");
        int idSeminar = 0;
        CalendarManager instance = null;
        instance.delete_seminar(idSeminar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLessonById method, of class CalendarManager.
     */
    @Test
    public void testGetLessonById() throws Exception {
        System.out.println("getLessonById");
        int pLessonID = 0;
        CalendarManager instance = null;
        Lesson expResult = null;
        Lesson result = instance.getLessonById(pLessonID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeminarById method, of class CalendarManager.
     */
    @Test
    public void testGetSeminarById() throws Exception {
        System.out.println("getSeminarById");
        int pSeminarID = 0;
        CalendarManager instance = null;
        Seminar expResult = null;
        Seminar result = instance.getSeminarById(pSeminarID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseById method, of class CalendarManager.
     */
    @Test
    public void testGetCourseById() throws Exception {
        System.out.println("getCourseById");
        int pCourseID = 0;
        CalendarManager instance = null;
        Course expResult = null;
        Course result = instance.getCourseById(pCourseID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLessonOf method, of class CalendarManager.
     */
    @Test
    public void testGetAllLessonOf() throws Exception {
        System.out.println("getAllLessonOf");
        Course pCourse = null;
        CalendarManager instance = null;
        List<Lesson> expResult = null;
        List<Lesson> result = instance.getAllLessonOf(pCourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testid method, of class CalendarManager.
     */
    @Test
    public void testTestid() throws Exception {
        System.out.println("testid");
        int id = 0;
        CalendarManager instance = null;
        int expResult = 0;
        int result = instance.testid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDescription method, of class CalendarManager.
     */
    @Test
    public void testTestDescription() throws Exception {
        System.out.println("testDescription");
        String description = "";
        CalendarManager instance = null;
        String expResult = "";
        String result = instance.testDescription(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of seminarTestDescription method, of class CalendarManager.
     */
    @Test
    public void testSeminarTestDescription() throws Exception {
        System.out.println("seminarTestDescription");
        String description = "";
        CalendarManager instance = null;
        String expResult = "";
        String result = instance.seminarTestDescription(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testStartData method, of class CalendarManager.
     */
    @Test
    public void testTestStartData() throws Exception {
        System.out.println("testStartData");
        Date data = null;
        CalendarManager instance = null;
        Date expResult = null;
        Date result = instance.testStartData(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testEndData method, of class CalendarManager.
     */
    @Test
    public void testTestEndData() throws Exception {
        System.out.println("testEndData");
        Date data = null;
        CalendarManager instance = null;
        Date expResult = null;
        Date result = instance.testEndData(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testNome method, of class CalendarManager.
     */
    @Test
    public void testTestNome() throws Exception {
        System.out.println("testNome");
        String name = "";
        CalendarManager instance = null;
        String expResult = "";
        String result = instance.testNome(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testNomeLesson method, of class CalendarManager.
     */
    @Test
    public void testTestNomeLesson() throws Exception {
        System.out.println("testNomeLesson");
        String name = "";
        CalendarManager instance = null;
        String expResult = "";
        String result = instance.testNomeLesson(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
