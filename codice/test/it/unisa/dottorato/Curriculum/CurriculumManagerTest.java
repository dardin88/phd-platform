/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Curriculum;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso Minichiello
 */
public class CurriculumManagerTest {
        private CurriculumManager instance;
        private Curriculum cur;
    
    public CurriculumManagerTest() {
    }
 
    @Before
    public void setUp() {
        instance = CurriculumManager.getInstance();
        cur=new Curriculum();
    }
    
    @After
    public void tearDown() {
    }
     
    @Test
    public void testgetInstance() {
         CurriculumManager result = CurriculumManager.getInstance();
         assertNotNull(result);
         
    }

    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertok() throws Exception {
        cur.setName("Curriculmtest");
        cur.setDescription("il Curriculum è di prova");
         try{
           instance.insert(cur);
           assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEmptyName() throws Exception {
        cur.setName("");        
        cur.setDescription("il Curriculum è di prova"); 
        try{
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
        
    }
    
    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertMaxName() throws Exception {
        cur.setName("ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum");
        cur.setDescription("il Curriculum è di prova");  
        try{
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPresent() throws Exception {
        cur.setName("Marketing e Comunicazione");
        cur.setDescription("il Curriculum è di prova"); 
        try{
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
   
    
    /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateok() throws Exception {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("curriculum prova update");
        cur.setDescription("descrizione prova update");
        try{
            instance.update(oldNameCurriculum, cur);
            assertTrue(true);
        }catch(Exception x){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateoldnotexist() throws Exception {
        String oldNameCurriculum = "ciao";
        cur.setName("curriculum prova update2");
        cur.setDescription("descrizione prova update");
        try{
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateoldnull() throws Exception {
        String oldNameCurriculum = "";
        cur.setName("curriculum prova update3");
        cur.setDescription("descrizione prova update");
        try{
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateoldmax() throws Exception {
        String oldNameCurriculum = "ciaosonomario,ho sbagliato ad inserire il nome del curriculum" 
                                   + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum";
        cur.setName("curriculum prova update");
        cur.setDescription("descrizione prova update");
        try{
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEmptyName() throws Exception {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("");
        cur.setDescription("descrizione prova update");
         try{
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
     /**
     * Test of update method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateNamemax() throws Exception {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum");
        cur.setDescription("descrizione prova update");
        try{
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    
    /**
     * Test of delete method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteEmptyName() throws Exception {
        String CurriculumName = null;
        try{
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    /**
     * Test of delete method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteNameMax() throws Exception {
        String CurriculumName = "ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                              + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum";
        try{
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
  
    }

    /**
     * Test of delete method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletenotExist() throws Exception {
        String CurriculumName = "ciaooooooooo";
        try{
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    /**
     * Test of delete method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteok() throws Exception {
        String CurriculumName = "Curriculmtest";
       try{
            instance.delete(CurriculumName);
            assertTrue(true); 
        }catch(Exception x){
            fail("sono riuscito a fare l' op");  
        }
    }
    
    /**
     * Test of getCurriculumList method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumList() throws Exception {
        System.out.println("getCurriculumList");
        ArrayList<Curriculum> result = instance.getCurriculumList();
        assertNotNull(result);
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumByNameEmptyName() throws Exception {
        String CurriculumName = "";
        try{
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumByNameMaxName() throws Exception {
        String CurriculumName =  "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum"
                              + "ciaosonomario,ho sbagliato ad inserire il nome del curriculum";
        try{
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumByNameNamenotExist() throws Exception {
        String CurriculumName = "ciaooooooooo";
        try{
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }

     /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumByNameok() throws Exception {
        String CurriculumName = "Marketing e Comunicazione";
        try{
            instance.getCurriculumByName(CurriculumName);
            assertTrue(true);  
        }catch(Exception x){
            fail("non sono riuscito a fare l' op");
        }
        
    }
    private String testsup65536() {
        String c="dsdffdffdf";
        for(int e=0; e<6600; e++)
            c=c.concat(c);
        return c;
    }


}
