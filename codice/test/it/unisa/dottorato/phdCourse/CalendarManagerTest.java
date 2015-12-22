/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private CalendarManager instance;
        private Course cur;
        private Lesson les;
        private Seminar sem;
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
         instance = CalendarManager.getInstance();
        cur=new Course();
        les=new Lesson();
        sem=new Seminar();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CalendarManager.
     */
    @Test
    public void testgetInstance() {
         CalendarManager result = CalendarManager.getInstance();
         assertNotNull(result);
         
    }

    @Test
    public void testInsertCurseok() throws Exception {
       
        cur.setIdCourse(1);
        cur.setFK_curriculum("Il curriculum è di prova");
        cur.setFK_cycle(2);
        cur.setName("Basi di dati");
        cur.setDescription("Il corso di basi di dati verterà su argomenti avanzati di SQL");
        cur.setStartDate(new Date(2015,12,12));
        cur.setEndDate(new Date(2016,12,12));
        
         try{
            instance.insert_course(cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertMinId() throws Exception {
        cur.setIdCourse(-1);
              
         try{
            instance.insert_course(cur);
            fail("il numero non raggiunge la lunghezza minima");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxId() throws Exception {
        cur.setIdCourse(777777777);
              
         try{
            instance.insert_course(cur);
            fail("il numero supera la lunghezza massima");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatId() throws Exception {
        cur.setIdCourse(testint("777777#@7"));              
         try{
            instance.insert_course(cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
 
    @Test
    public void testInsertMinCurriculum() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("");
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCurriculum() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum(testsup100());
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
      
    @Test
    public void testInsertMinCycle() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(-1);
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
   
@Test
    public void testInsertMaxCycle() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(1234567);
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatCycle() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(testint("IPIçI&/$GT"));
         try{
            instance.insert_course(cur);
            fail("formato ciclo errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertMinDescription() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription(testsup100());
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxDescription() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription(testsup100());
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrDate() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(new Date(null));
         try{
            instance.insert_course(cur);
            fail("data errata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertErr2Date() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(new Date(null));
        cur.setEndDate(new Date(null));
         try{
            instance.insert_course(cur);
            fail("data errata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertMinName() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(new Date(2015,12,12));
        cur.setEndDate(new Date(2016,12,12));
        cur.setName("");
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxName() throws Exception {
        cur.setIdCourse(1); 
        cur.setFK_curriculum("Curriculum di prova");
        cur.setFK_cycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(new Date(2015,12,12));
        cur.setEndDate(new Date(2016,12,12));
        cur.setName(testsup100());
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    //////////////////////////////
    
     @Test
    public void testInsertMinIdLesson() throws Exception {
        les.setIdLesson(-1);         
         try{
            instance.insert_lesson(les);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxIdLesson() throws Exception {
        les.setIdLesson(7777777);         
         try{
            instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatIdLesson() throws Exception {
        les.setIdLesson(testint("777777#@7"));         
         try{
             instance.insert_lesson(les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertErrDateLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(null));
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrTimeLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(-1);
        les.setEndTime(-2);
         try{
             instance.insert_lesson(les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxNameLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName(testsup100());
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinDescriptionLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription(null);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxDescriptionLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription(testsup100());
         try{
            instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinClassroomLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom(null);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testInsertMaxClassroomLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom(testsup100());
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinCycleLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(-1);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCycleLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(1234567);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatCycleLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(testint("777777#@7"));
         try{
            instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinCurriculumLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxCurriculumLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum(testsup100());
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinCourseLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(-1);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCourseLesson() throws Exception {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(88888888);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatCourseLesson() throws Exception {
        les.setIdLesson(1);    
        //les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(testint("777777#@7"));
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertLessonok() throws Exception {
        les.setIdLesson(1);    
       // les.setDate(new Date(2000,12,12)) ;       
        les.setStartTime(12);
        les.setEndTime(13);
        les.setName("Basi di dati");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(6);
         try{
            instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    
    
    
    /////////////////
    
    
  
    
    
    
    
     @Test
    public void testUpdateMinIdLesson() throws Exception {
         int oldId = 5;
        
        les.setIdLesson(-1);         
         try{
            instance.update_lesson(oldId,les);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
     @Test
    public void testUpdateMaxIdLesson() throws Exception {
        int oldId = 5;
        les.setIdLesson(7777777);         
         try{
            instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateFormatIdLesson() throws Exception {
        int oldId = 5;
        les.setIdLesson(testint("777777#@7"));         
         try{
             instance.update_lesson(oldId,les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testUpdateErrDateLesson() throws Exception {
        int oldId = 5;
        
        les.setIdLesson(1);    
        les.setDate(new Date(null));
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateErrTimeLesson() throws Exception {
         int oldId = 5;
         
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(-1);
        les.setEndTime(-2);
         try{
              instance.update_lesson(oldId,les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameLesson() throws Exception {
        int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("");
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxNameLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName(testsup100());
         try{
            instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinDescriptionLesson() throws Exception {
            int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription(null);
         try{
                instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxDescriptionLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription(testsup100());
         try{
           instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinClassroomLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom(null);
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testUpdateMaxClassroomLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom(testsup100());
         try{
        instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinCycleLesson() throws Exception {
           int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(-1);
         try{
            instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxCycleLesson() throws Exception {
           int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(1234567);
         try{
           instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateFormatCycleLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(testint("777777#@7"));
         try{
         instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinCurriculumLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("");
         try{
              instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxCurriculumLesson() throws Exception {
          int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum(testsup100());
         try{
          instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinCourseLesson() throws Exception {
          int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(-1);
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxCourseLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(88888888);
         try{
              instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateFormatCourseLesson() throws Exception {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime(1222);
        les.setEndTime(1300);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(testint("777777#@7"));
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateLessonok() throws Exception {
           int oldId = 5;
        les.setIdLesson(1);    
       // les.setDate(new Date(2015-12-12));
        les.setStartTime(12);
        les.setEndTime(13);
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setCycle(3);
        les.setCurriculum("Curriculum di prova");
        les.setFK_course(6);
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    //////////////////
    
    @Test
    public void testDeleteMinIdLesson() throws Exception {
        int oldId = -1;
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdLesson() throws Exception {
        int oldId = supint100();
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteFormatIdLesson() throws Exception {
        int oldId = testint("wfreg*TG&@");
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteLessonok() throws Exception {
        int oldId = 11;
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    /////////////////
    @Test
    public void testInsertSeminarok() throws Exception {
       
        sem.setIdSeminar(1);
      //  sem.setDate(new Date(2015,15,12));
        sem.setStartTime(12);
        sem.setEndTime(15);
        sem.setName("Algoritmi");
        sem.setNameSpeacker("Antonio Verdi");
        sem.setDescription("Seminario su algoritmi avanzati");
        sem.setPlace("aula p2");
        sem.setFK_course(2);
        
         try{
            instance.insert_seminar(sem);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    
     @Test
    public void testInsertMinIdSeminar() throws Exception {
        sem.setIdSeminar(-1);         
         try{
            instance.insert_seminar(sem);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxIdSeminar() throws Exception {
        sem.setIdSeminar(7777777);         
         try{
            instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertFormatIdSeminar() throws Exception {
        sem.setIdSeminar(testint("777777#@7"));         
         try{
              instance.insert_seminar(sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertErrDateSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(null));
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrTimeSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(-1);
        sem.setEndTime(-2);
         try{
             instance.insert_seminar(sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxNameSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName(testsup100());
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameSpeackerSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker(null);
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxNameSpeackerSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker(testsup100());
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinDescriptionSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription(null);
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testInsertMaxDescriptionSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription(testsup100());
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinPlaceSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("prove");
        sem.setPlace("");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxPlace() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("prove");
        sem.setPlace(testsup100());
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
    
    @Test
    public void testInsertMinCourseSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Prove");
        sem.setPlace("P3");
        sem.setFK_course(-1);
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCourseSeminar() throws Exception {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Prove");
        sem.setPlace("P3");
        sem.setFK_course(supint100());
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    //////////////
    
     @Test
    public void testUpdateSeminarok() throws Exception {
       int oldId = 2;
        sem.setIdSeminar(1);
      //  sem.setDate(new Date(2015,15,12));
        sem.setStartTime(12);
        sem.setEndTime(15);
        sem.setName("Algoritmi");
        sem.setNameSpeacker("Antonio Verdi");
        sem.setDescription("Seminario su algoritmi avanzati");
        sem.setPlace("aula p2");
        sem.setFK_course(2);
        
         try{
            instance.update_seminar(oldId,sem);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
     @Test
    public void testUpdateMinIdSeminar() throws Exception {
         int oldId = 5;
        
        sem.setIdSeminar(-1);         
         try{
            instance.update_seminar(oldId,sem);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
     @Test
    public void testUpdateMaxIdSeminar() throws Exception {
        int oldId = 5;
        sem.setIdSeminar(7777777);         
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateFormatIdSeminar() throws Exception {
        int oldId = 5;
        sem.setIdSeminar(testint("777777#@7"));         
         try{
             instance.update_seminar(oldId,sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testUpdateErrDateSeminar() throws Exception {
        int oldId = 5;
        
        sem.setIdSeminar(1);    
        sem.setDate(new Date(null));
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateErrTimeSeminar() throws Exception {
         int oldId = 5;
         
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(-1);
        sem.setEndTime(-2);
         try{
              instance.update_seminar(oldId,sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameSeminar() throws Exception {
        int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("");
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxNameSeminar() throws Exception {
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName(testsup100());
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameSpeackerSeminar() throws Exception {
            int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker(null);
         try{
                instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxNameSpeackerSeminar() throws Exception {
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker(testsup100());
         try{
           instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinDescriptionSeminar() throws Exception {
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription(null);
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testUpdateMaxDescriptionSeminar() throws Exception {
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription(testsup100());
         try{
        instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinPlaceSeminar() throws Exception {
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("");
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxPlaceSeminar() throws Exception {
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace(testsup100());
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinCourseSeminar() throws Exception {
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("p2");
        sem.setFK_course(-1);
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
   @Test
    public void testUpdateMaxCourseSeminar() throws Exception {
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("p2");
        sem.setFK_course(supint100());
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateFormatCourseSeminar() throws Exception {
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime(1222);
        sem.setEndTime(1300);
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("p2");
        sem.setFK_course(testint("777777#@7"));
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
 
   
    
     //////////////////
    
    @Test
    public void testDeleteMinIdSeminar() throws Exception {
        int oldId = -1;
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdSeminar() throws Exception {
        int oldId = supint100();
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteFormatIdSeminar() throws Exception {
        int oldId = testint("777777#@7");
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteSeminarok() throws Exception {
        int oldId = 2;
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
   
    
    
    /////////
    
    
    public Date convertStringToDate(String dateString) throws ParseException
{
    DateFormat df = new SimpleDateFormat ("dd/M/yyyy");
    df.setLenient (false);
    Date d = df.parse (dateString);
    return d;
}
    private String testsup100() {
        String c="dsdff";
        for(int e=0; e<200; e++)
            c=c.concat(c);
        return c;
    }
    
    private int testint(String str){
    int num;
    num = Integer.parseInt(str);
    return num;
    }
    
    private int supint100(){
        int c=444;
        
        return c^36435356;
    }
}

