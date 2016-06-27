/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import it.unisa.dottorato.account.Professor;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Raff
 */
public class CalendarManagerTest {
    
    private CalendarManager instance;
     
    @Before
    public void setUp() {
        instance = CalendarManager.getInstance();
        System.out.println("@Before - setUp");
    }
    
    /**
     * Test of getInstance method, of class CalendarManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        CalendarManager expResult = CalendarManager.getInstance();        
        assertEquals(expResult, instance);
    }

    /**
     * Test of insert_course method, of class CalendarManager.
     */
    @Test @Ignore
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
    @Test @Ignore
    public void testInsert_lesson() throws Exception {
        System.out.println("insert_lesson");
        Lesson pLesson = null;
        Professor professor = null;
        CalendarManager instance = null;
        instance.insert_lesson(pLesson, professor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert_seminar method, of class CalendarManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
    @Test  @Ignore
    public void testDelete_lesson() throws Exception {
        System.out.println("delete_lesson");
        String idLesson = "";
        CalendarManager instance = null;
        instance.delete_lesson(idLesson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete_seminar method, of class CalendarManager.
     */
    @Test @Ignore
    public void testDelete_seminar() throws Exception {
        System.out.println("delete_seminar");
        String idSeminar = "";
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
        int idLesson = 1;
        Lesson result = instance.getLessonById(idLesson);
        assertEquals(idLesson, result.getIdLesson());
    }

    /**
     * Test of getSeminarById method, of class CalendarManager.
     */
    @Test @Ignore
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
     * Test of getAllCourse method, of class CalendarManager.
     */
    @Test
    public void testGetAllCourse() throws Exception {
        System.out.println("getAllCourse");
        ArrayList<Course> result = instance.getAllCourse();
        assertNotNull(result);        
    }

    /**
     * Test of getCourseListId method, of class CalendarManager.
     */
    @Test
    public void testGetCourseListId() throws Exception {
        System.out.println("getCourseListId");
        int cycle = 15;
        String curriculum = "Informatica, Sistemi Informativi e Tecnologie del Software";
        ArrayList<Integer> result = instance.getCourseListId(cycle, curriculum);
        assertNotNull(result);
    }

    /**
     * Test of getAllSeminar method, of class CalendarManager.
     */
    @Test @Ignore
    public void testGetAllSeminar() throws Exception {
        System.out.println("getAllSeminar");
        CalendarManager instance = null;
        ArrayList<Seminar> expResult = null;
        ArrayList<Seminar> result = instance.getAllSeminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourseById method, of class CalendarManager.
     */
    @Test @Ignore
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
        int idcourse = 1;
        ArrayList<Lesson> list = instance.getAllLessonOf(idcourse);
        assertNotNull(list);
    }

    /**
     * Test of getAllSeminarOf method, of class CalendarManager.
     */
    @Test @Ignore
    public void testGetAllSeminarOf() throws Exception {
        System.out.println("getAllSeminarOf");
        int idcourse = 0;
        CalendarManager instance = null;
        ArrayList<Seminar> expResult = null;
        ArrayList<Seminar> result = instance.getAllSeminarOf(idcourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLessonOfProfessor method, of class CalendarManager.
     */
    @Test @Ignore
    public void testGetAllLessonOfProfessor() throws Exception {
        System.out.println("getAllLessonOfProfessor");
        CalendarManager instance = null;
        ArrayList<Lesson> expResult = null;
        ArrayList<Lesson> result = instance.getAllLessonOfProfessor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLessonOfCourse method, of class CalendarManager.
     */
    @Test @Ignore
    public void testGetAllLessonOfCourse() throws Exception {
        System.out.println("getAllLessonOfCourse");
        CalendarManager instance = null;
        int expResult = 0;
        int result = instance.getAllLessonOfCourse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testid method, of class CalendarManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
     * Test of testName method, of class CalendarManager.
     */
    @Test @Ignore
    public void testTestName() throws Exception {
        System.out.println("testName");
        String name = "";
        CalendarManager instance = null;
        String expResult = "";
        String result = instance.testName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testNomeLesson method, of class CalendarManager.
     */
    @Test @Ignore
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

    /**
     * Test of testCourse method, of class CalendarManager.
     */
    @Test @Ignore
    public void testTestCourse() throws Exception {
        System.out.println("testCourse");
        Course c = null;
        CalendarManager instance = null;
        instance.testCourse(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumberCourse method, of class CalendarManager.
     */
    @Test @Ignore
    public void testNextNumberCourse() throws Exception {
        System.out.println("nextNumberCourse");
        CalendarManager instance = null;
        int expResult = 0;
        int result = instance.nextNumberCourse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumberLesson method, of class CalendarManager.
     */
    @Test @Ignore
    public void testNextNumberLesson() throws Exception {
        System.out.println("nextNumberLesson");
        CalendarManager instance = null;
        int expResult = 0;
        int result = instance.nextNumberLesson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumberSeminar method, of class CalendarManager.
     */
    @Test @Ignore
    public void testNextNumberSeminar() throws Exception {
        System.out.println("nextNumberSeminar");
        CalendarManager instance = null;
        int expResult = 0;
        int result = instance.nextNumberSeminar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLessonsOfProfessor method, of class CalendarManager.
     */
    @Test
    public void testGetLessonsOfProfessor() throws Exception {
        System.out.println("getLessonsOfProfessor");
        int idcourse = 1;
        Professor loggedPerson = new Professor();
        loggedPerson.setSecondaryEmail("adelucia@hotmail.it");        
        ArrayList<Lesson> list = instance.getLessonsOfProfessor(idcourse,loggedPerson);
        assertNotNull(list);
    }

    /**
     * Test of getCourseListName method, of class CalendarManager.
     */
    @Test @Ignore
    public void testGetCourseListName() throws Exception {
        System.out.println("getCourseListName");
        int cycle = 0;
        String curriculum = "";
        CalendarManager instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCourseListName(cycle, curriculum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatusLesson method, of class CalendarManager.
     */
    @Test 
    public void testSetStatusLesson() throws Exception {
        System.out.println("setStatusLesson");
        int idLesson = 1;
        String status = "chiusa";
        instance.setStatusLesson(idLesson, status);
        Lesson lesson = instance.getLessonById(idLesson);
        String s = lesson.getStatus();
        assertEquals(status, s);       
    }
    
}
