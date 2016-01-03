/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

import java.sql.SQLException;
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
public class NewsManagerTest {
    private NewsManager instance;
    private News not;
    
    public NewsManagerTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         instance = NewsManager.getInstance();
        not =new News();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class NewsManager.
     */
    @Test
    public void testGetInstance() {
        NewsManager result = NewsManager.getInstance();
         assertNotNull(result);
    }

    /**
     * Test of insertNews method, of class NewsManager.
     */
    @Test
    public void testInsertNewsok() throws Exception {
        not.setId(1);
        not.setTitle("Prova di avviso");
        not.setDescription("Descrizione avviso");
        try{
            instance.insertNews(not);
            assertTrue(true);
        }catch(Exception x){
            fail(" non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testInsertIdMinNews() throws Exception {
        not.setId(-1);
           
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertIdMaxNews() throws Exception {
        not.setId(1000000);
           
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertTitleMinNews() throws Exception {
        not.setId(1);          
        not.setTitle("");
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertTitleMaxNews() throws Exception {
        not.setId(1);          
        not.setTitle("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertDescriptionMinNews() throws Exception {
        not.setId(1);          
        not.setTitle("Titolo avviso");
        not.setDescription("");
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }

    /**
     * Test of getNewsById method, of class NewsManager.
     */
    @Test
    public void testGetNewsByIdok() throws Exception {
        int number = 1;
        try{
            instance.getNewsById(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    @Test
    public void testGetNewsByIdMin() throws Exception {
        int number = -1;
        try{
            instance.getNewsById(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testGetNewsByIdMax() throws Exception {
        int number = 2000;
        try{
            instance.getNewsById(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of deleteNews method, of class NewsManager.
     */
    @Test
    public void testDeleteNewsok() throws SQLException {
       int id=2;      
        try{
            instance.deleteNews(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
     @Test
    public void testDeleteNewsIdMin() throws SQLException {
        int id = -1;
        try{
            instance.deleteNews(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    
    
    @Test
    public void testDeleteNewsIdMax() throws SQLException {
        int id = 20000;
        try{
            instance.deleteNews(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of update_news method, of class NewsManager.
     */
    @Test
    public void testUpdate_newsok() throws Exception {
        int Number = 1;
        not.setTitle("Prova");
        not.setDescription("Prova descrizione");
        try{
            instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsTitleMin() throws Exception {
        int Number = 15;
        not.setTitle("");
        try{
             instance.update_news(Number,not);
             fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsTitleMax() throws Exception {
        int Number = 15;
        not.setTitle("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMin() throws Exception {
        int Number = -1;
        not.setId(Number);
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMax() throws Exception {
        int Number = 2000000;
        not.setId(Number);
        try{
            instance.update_news(Number,not);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdate_newsDescriptionMin() throws Exception {
        int Number = 2000;
        not.setTitle("Prova");
        not.setDescription(null);
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }   

    @Test
    public void testGetAllNewsOk()throws Exception{
        try{
            ArrayList<News> result=instance.getAllNews();
            assertNotNull(not);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
}
