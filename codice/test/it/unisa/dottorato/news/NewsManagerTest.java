/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

import it.unisa.dottorato.news.News;
import java.sql.SQLException;
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
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
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
        not.setId(supint100());
           
         try{
            instance.insertNews(not);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertIdFormatNews() throws Exception {
        not.setId(testint("@[%$%EGER]"));           
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
        not.setTitle(testsup100());
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
    
    
    @Test
    public void testInsertDescriptionMaxNews() throws Exception {
        not.setId(1);          
        not.setTitle("Titolo avviso");
        not.setDescription(testsup100());
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
         int number = 15;
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
       News pnew = new News();
        pnew.setId(1);
        pnew.setTitle("Prova titolo");
        pnew.setDescription("Prova descrizione");
        instance.insertNews(pnew);
        int id = 1;
        
        try{
            instance.deleteNews(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
     @Test
    public void testDeleteNewsIdMin() throws SQLException {
       News pnew = new News();
        pnew.setId(-1);
        
        instance.insertNews(pnew);
        int id = -1;
        
        try{
            instance.deleteNews(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    
    
    @Test
    public void testDeleteNewsIdMax() throws SQLException {
       News pnew = new News();
        pnew.setId(20000);
        instance.insertNews(pnew);
        int id = 20000;
        
        try{
            instance.deleteNews(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    /**
     * Test of update_news method, of class NewsManager.
     */
    @Test
    public void testUpdate_newsok() throws Exception {
        int Number = 15;
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
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsTitleMax() throws Exception {
        int Number = 15;
        not.setTitle(testsup100());
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMin() throws Exception {
        int Number = -1;
        not.setId(Number);
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsIdMax() throws Exception {
        int Number = 2000;
        not.setId(Number);
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsDescriptionMin() throws Exception {
        int Number = 2000;
        not.setTitle("Prova");
        not.setDescription("");
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    @Test
    public void testUpdate_newsDescriptionMax() throws Exception {
        int Number = 2000;
        not.setTitle("Prova");
        not.setDescription(testsup100());
        try{
             instance.update_news(Number,not);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    

    /**
     * Test of getNewsByTypeOfTitle method, of class NewsManager.
     */
    @Test
    public void testGetNewsByTypeOfTitleok() throws Exception {
           String str = "Prova";
        try{
            instance.getNewsByTypeOfTitle(str);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    
    @Test
    public void testGetNewsByTypeOfTitleMin() throws Exception {
           String str = "";
        try{
            instance.getNewsByTypeOfTitle(str);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    
    @Test
    public void testGetNewsByTypeOfTitleMax() throws Exception {
           String str = testsup100();
        try{
            instance.getNewsByTypeOfTitle(str);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    
    // metodi di utilità
    
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
