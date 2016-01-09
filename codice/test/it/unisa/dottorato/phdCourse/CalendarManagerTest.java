/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

import java.util.Date;
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
    public void testInsertCurseok(){
        cur.setIdCourse(1);
        cur.setFkCurriculum("Marketing e Comunicazione");
        cur.setFkCycle(15);
        cur.setName("Basi di dati");
        cur.setDescription("Il corso di basi di dati verterà su argomenti avanzati di SQL");
        cur.setStartDate(new java.sql.Date(2015,10,12));
        cur.setEndDate(new java.sql.Date(2016,10,12));
        
         try{
            instance.insert_course(cur);
             assertTrue(true);
        }catch(Exception x){
           fail("non sono riuscito a fare l' op");
        }
    }
    
    
     @Test
    public void testInsertMinId(){
        cur.setIdCourse(-1);
              
         try{
            instance.insert_course(cur);
            fail("il numero non raggiunge la lunghezza minima");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxId(){
        cur.setIdCourse(777777777);
              
         try{
            instance.insert_course(cur);
            fail("il numero supera la lunghezza massima");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
 
    @Test
    public void testInsertMinCurriculum() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("");
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCurriculum(){
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
      
    @Test
    public void testInsertMinCycle(){
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(-1);
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
   
@Test
    public void testInsertMaxCycle() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(123456789);
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
   
    
     @Test
    public void testInsertMinDescription() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
        cur.setDescription("");
         try{
            instance.insert_course(cur);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxDescription() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
        cur.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrDate() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(null);
         try{
            instance.insert_course(cur);
            fail("data errata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertErr2Date() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(null);
        cur.setEndDate(null);
         try{
            instance.insert_course(cur);
            fail("data errata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertMinName() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
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
    public void testInsertMaxName() {
        cur.setIdCourse(1); 
        cur.setFkCurriculum("Curriculum di prova");
        cur.setFkCycle(3);
        cur.setDescription("Basi di dati");
        cur.setStartDate(new Date(2015,12,12));
        cur.setEndDate(new Date(2016,12,12));
        cur.setName("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.insert_course(cur);
            fail("lunghezza massima superata");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    //////////////////////////////
    
     @Test
    public void testInsertMinIdLesson() {
        les.setIdLesson(-1);         
         try{
            instance.insert_lesson(les);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxIdLesson(){
        les.setIdLesson(7777777);         
         try{
            instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
     @Test
    public void testInsertErrDateLesson() {
        les.setIdLesson(1);    
        les.setDate(null);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrTimeLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("-1");
        les.setEndTime("-2");
         try{
             instance.insert_lesson(les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxNameLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinDescriptionLesson(){
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinClassroomLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testInsertMaxClassroomLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }

    
    @Test
    public void testInsertMinCourseLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(-1);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxCourseLesson() {
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(88888888);
         try{
             instance.insert_lesson(les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
    @Test
    public void testInsertLessonok() throws Exception {
      
        les.setIdLesson(5);    
        les.setDate(new java.sql.Date(2015,10,12));       
        les.setStartTime("12:00");
        les.setEndTime("13:22");
        les.setName("Basi di dati");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(1);
         try{
            instance.insert_lesson(les);
            assertTrue(true);
        }catch(Exception x){
             fail("fail");
        }
    }
    
    
    
    
    
    
    /////////////////
    
    
  
    
    
    
    
     @Test
    public void testUpdateMinIdLesson(){
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
    public void testUpdateMaxIdLesson() {
        int oldId = 5;
        les.setIdLesson(77777777);         
         try{
            instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
     @Test
    public void testUpdateErrDateLesson() {
        int oldId = 5;
        
        les.setIdLesson(1);    
        les.setDate(null);
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateErrTimeLesson() {
         int oldId = 5;
         
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("-1");
        les.setEndTime("-2");
         try{
              instance.update_lesson(oldId,les);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameLesson() {
        int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("");
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxNameLesson() {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinDescriptionLesson(){
            int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("");
         try{
                instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxDescriptionLesson() {
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
           instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinClassroomLesson(){
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("");
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testUpdateMaxClassroomLesson(){
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
        instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinCourseLesson(){
          int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(-1);
         try{
             instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxCourseLesson(){
         int oldId = 5;
        les.setIdLesson(1);    
        les.setDate(new Date(2015,12,12));
        les.setStartTime("12:22");
        les.setEndTime("13:00");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(88888888);
         try{
              instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testUpdateLessonok(){
           int oldId = 1;
        les.setIdLesson(1);    
       // les.setDate(new Date(2015-12-12));
        les.setStartTime("12:00");
        les.setEndTime("13:22");
        les.setName("BD");
        les.setDescription("Lezione Basi di dati");
        les.setClassroom("P1");
        les.setFK_course(6);
         try{
             instance.update_lesson(oldId,les);
             assertTrue(true);
        }catch(Exception x){
           fail("fail");
        }
    }
    
    
    //////////////////
    
    @Test
    public void testDeleteMinIdLesson() {
        String oldId = "-1";
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdLesson(){
        String oldId = "1234567";
        try{
            instance.delete_lesson(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    
    
    @Test
    public void testDeleteLessonok() {
        String oldId = "11";
        try{
            instance.delete_lesson(oldId);
            assertTrue(true);
        }catch(Exception x){
              fail("sono riuscito a fare l' op");  
        }
    }
    /////////////////
    @Test
    public void testInsertSeminarok() {
       
        sem.setIdSeminar(1);
        sem.setDate(new java.sql.Date(2015,10,12));
        sem.setStartTime("12:00");
        sem.setEndTime("15:00");
        sem.setName("Algoritmi");
        sem.setNameSpeacker("Antonio Verdi");
        sem.setDescription("Seminario su algoritmi avanzati");
        sem.setPlace("aula p2");
        sem.setFK_course(1);
        
         try{
            instance.insert_seminar(sem);
            assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
    
    
    
     @Test
    public void testInsertMinIdSeminar(){
        sem.setIdSeminar(-1);         
         try{
            instance.insert_seminar(sem);
            fail("lunghezza minima non raggiunta");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxIdSeminar() {
        sem.setIdSeminar(7777777);         
         try{
            instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
     @Test
    public void testInsertErrDateSeminar() {
        sem.setIdSeminar(1);    
        sem.setDate(null);
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertErrTimeSeminar() {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("-1");
        sem.setEndTime("-2");
         try{
             instance.insert_seminar(sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameSeminar() {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxNameSeminar() {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinNameSpeackerSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxNameSpeackerSeminar() {
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
             instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinDescriptionSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testInsertMaxDescriptionSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinPlaceSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
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
    public void testInsertMaxPlace(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("prove");
        sem.setPlace("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
              instance.insert_seminar(sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
    
    @Test
    public void testInsertMinCourseSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
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
    public void testInsertMaxCourseSeminar(){
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Prove");
        sem.setPlace("P3");
        sem.setFK_course(1234567);
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
        sem.setStartTime("12:00");
        sem.setEndTime("15:00");
        sem.setName("Algoritmi");
        sem.setNameSpeacker("Antonio Verdi");
        sem.setDescription("Seminario su algoritmi avanzati");
        sem.setPlace("aula p2");
        sem.setFK_course(2);
        
         try{
            instance.update_seminar(oldId,sem);
            assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
    
    
     @Test
    public void testUpdateMinIdSeminar(){
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
    public void testUpdateMaxIdSeminar() {
        int oldId = 5;
        sem.setIdSeminar(77777777);         
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
     @Test
    public void testUpdateErrDateSeminar(){
        int oldId = 5;
        
        sem.setIdSeminar(1);    
        sem.setDate(null);
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateErrTimeSeminar(){
         int oldId = 5;
         
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("-1");
        sem.setEndTime("-2");
         try{
              instance.update_seminar(oldId,sem);
            fail("formato errato");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameSeminar(){
        int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("");
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxNameSeminar(){
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinNameSpeackerSeminar(){
            int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("");
         try{
                instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxNameSpeackerSeminar(){
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
           instance.update_lesson(oldId,les);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinDescriptionSeminar(){
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("");
         try{
             instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    @Test
    public void testUpdateMaxDescriptionSeminar(){
         int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
        instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinPlaceSeminar(){
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
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
    public void testUpdateMaxPlaceSeminar(){
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringaProva superamento stringa,,");
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinCourseSeminar(){
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
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
    public void testUpdateMaxCourseSeminar(){
           int oldId = 5;
        sem.setIdSeminar(1);    
        sem.setDate(new Date(2015,12,12));
        sem.setStartTime("12:22");
        sem.setEndTime("13:00");
        sem.setName("BD");
        sem.setNameSpeacker("Olmo");
        sem.setDescription("Seminario sugli algoritmi");
        sem.setPlace("p2");
        sem.setFK_course(12345678);
         try{
            instance.update_seminar(oldId,sem);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
   
    
 
   
    
     //////////////////
    
    @Test
    public void testDeleteMinIdSeminar() {
        String oldId = "-1";
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdSeminar(){
        String oldId = "12345678";
        try{
            instance.delete_seminar(oldId);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
   
    
    @Test
    public void testDeleteSeminarok() throws Exception {
        String oldId = "2";
        sem.setIdSeminar(2);
        try{
            instance.insert_seminar(sem);
            instance.delete_seminar(oldId);
            assertTrue(true);
        }catch(Exception x){
               fail("sono riuscito a fare l' op"); 
        }
    }
   
}