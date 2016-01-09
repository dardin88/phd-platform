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
    private PresenceManager instance;
    private Presence pre;
    
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

    @Test
    public void testGetCourseByProfessorOk(){
        String Professor = "adelucia@hotmail.it";
        try{
            instance.getCourseByProfessor(Professor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    @Test
    public void testGetCourseByProfessorProfessorMin(){
        String Professor = "";
        try{
            instance.getCourseByProfessor(Professor);
            fail("non sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetCourseByProfessorProfessorMax(){
        String Professor = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        try{
            instance.getCourseByProfessor(Professor);
            fail("non sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
     
    @Test
    public void testGetCourseByProfessorProfessorFormatError(){
        String Professor = "qwertyuiopqwpqwertyuiopqwertyuiop";
        try{
            instance.getCourseByProfessor(Professor);
            fail("non sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertPresenceOk(){
        pre.setFkLesson(1);
        pre.setFkPhdstudent("ballo@hotmail.it");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a creare l' op");
        }
    }
    
    @Test
    public void testInsertPresenceLessonMin(){
        pre.setFkLesson(-9);
        pre.setFkPhdstudent("ballo@hotmail.it");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            fail("sono riuscito a creare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertPresenceLessonMax(){
        pre.setFkLesson(9999999);
        pre.setFkPhdstudent("ballo@hotmail.it");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            fail("sono riuscito a creare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertPresencefkPhdstudentMin(){
        pre.setFkLesson(1);
        pre.setFkPhdstudent("");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            fail("sono riuscito a creare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertPresencefkPhdstudentMax(){
        pre.setFkLesson(1);
        pre.setFkPhdstudent("qwertyuopqwertyuopqwertyuopqwertyuopqwertyuopqwertyuop");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            fail("sono riuscito a creare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertPresencefkPhdstudentFormatError(){
        pre.setFkLesson(1);
        pre.setFkPhdstudent("qwertyuopqwertyuo@wertyuopqwertyuop");
        pre.setIsPresent(true);
        try{
            instance.insertPresence(pre);
            fail("sono riuscito a creare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetPresenceListOk(){
        int lesson = 1;
        try{
            instance.getPresenceList(lesson);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
        
    @Test
    public void testGetPresenceListLessonMin(){
        int lesson = -41;
        try{
            instance.getPresenceList(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetPresenceListLessonMax(){
        int lesson = 98989889;
        try{
            instance.getPresenceList(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetPresenceListLessonNotExists(){
        int lesson = 98989;
        try{
            instance.getPresenceList(lesson);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetPresenceDottorandiOk(){
        int idCorso = 1;
        try{
            instance.getPresenceDottorandi(idCorso);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    @Test
    public void testGetPresenceDottorandiCourseMin(){
        int idCorso = -89;
        try{
            instance.getPresenceDottorandi(idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetPresenceDottorandiCourseMax(){
        int idCorso = 9898989;
        try{
            instance.getPresenceDottorandi(idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetPresenceDottorandiCourseNotExists(){
        int idCorso = 9898;
        try{
            instance.getPresenceDottorandi(idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetPresenceToLesson(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = 1;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }    
    }
    
    @Test
    public void testGetPresenceToLessonDottorandoMin(){
        String dottorando = "";
        int idCorso = 1;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testGetPresenceToLessonDottorandoMax(){
        String dottorando = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        int idCorso = 1;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testGetPresenceToLessonDottorandoFormatError(){
        String dottorando = "qwertyuiopqpqwertyuiopqwertyuiop";
        int idCorso = 1;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testGetPresenceToLessonCorsoMin(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = -8;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testGetPresenceToLessonCorsoMax(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = 9898989;
        try{
            instance.getPresenceToLesson(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }

    @Test
    public void testModifyPresence(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = 1;
        try{
            instance.modifyPresence(dottorando, idCorso);
            assertTrue(true);
        }catch(Exception e){
            fail("sono riuscito a fare l' op");
        }    
    }
    
    
    @Test
    public void testModifyPresenceDottorandoMin(){
        String dottorando = "";
        int idCorso = 1;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testModifyPresenceDottorandoMax(){
        String dottorando = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        int idCorso = 1;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testModifyPresenceDottorandoFormatError(){
        String dottorando = "qwertyuiopqpqwertyuiopqwertyuiop";
        int idCorso = 1;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testModifyPresenceCorsoMin(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = -8;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testModifyPresenceCorsoMax(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = 9898989;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
    
    @Test
    public void testModifyPresenceNotExists(){
        String dottorando = "dinucci@hotmail.it";
        int idCorso = 878;
        try{
            instance.modifyPresence(dottorando, idCorso);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }    
    }
}
