/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.publications;

import it.unisa.dottorato.account.PhdStudent;
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
public class PublicationManagerTest {
    private PublicationManager instance;
        private Publication pub;
    
    public PublicationManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = PublicationManager.getInstance();
        pub =new Publication();
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
    public void testgetInstance() {
         PublicationManager result = PublicationManager.getInstance();
         assertNotNull(result);
                                    }
    
    
    
     @Test
    public void testInsertPublicationok(){
       
        pub.setIdPublication(1);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Prova");
        pub.setYear("2015");
        pub.setNumberPages(1200);
        pub.setLink("www.link.org");
        pub.setType("Demo");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("Abstract");
        pub.setFkPhdstudent("prova@gmail.com");
        
         try{
            instance.insert(pub);
            assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
    
     @Test
    public void testInsertMinId(){
        pub.setIdPublication(-1);
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxId(){
        pub.setIdPublication(12345678);
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     
    
    
    @Test
    public void testInsertMinTitle() {
        pub.setIdPublication(2);
        pub.setTitle("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxTitle() {
        pub.setIdPublication(2);
        pub.setTitle("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertMinPublicationIssue(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxPublicationIssue(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinYear(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxYear(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("55555");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMinNumberPages(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(-2);
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMinLink(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMaxLink(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinType(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxType(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinOtherAuthors(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMaxOtherAuthors(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinAbstract(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMinfkStudent(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("Prova");
        pub.setFkPhdstudent("");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMaxfkStudent(){
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("Prova");
        pub.setFkPhdstudent("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    ////////////////////
     @Test
    public void testUpdatePublicationok(){
        int oldId = 2;
        pub.setIdPublication(1);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Prova");
        pub.setYear("2015");
        pub.setNumberPages(1200);
        pub.setLink("www.link.org");
        pub.setType("Demo");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("Abstract");
        pub.setFkPhdstudent("prova@gmail.com");
        
         try{
        instance.update(oldId,pub);
            assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
  
    @Test
    public void testUpdateMinTitle(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("");
              
         try{
           instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxTitle(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testUpdateMinPublicationIssue(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxPublicationIssue(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
             instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinYear(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxYear(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("55555");
              
         try{
              instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testUpdateMinNumberPages(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(-2);
              
         try{
              instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testUpdateMinLink(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testUpdateMaxLink(){
         int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
           instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinType(){
         int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMaxType(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
          instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinOtherAuthors(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("");
              
         try{
               instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMaxOtherAuthors(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa");
              
         try{
               instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinAbstract(){
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("");
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    ////////////////////////
    
    
    @Test
    public void testDeleteMinIdPublication(){
        String id = null;
        try{
            instance.delete(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdPublication(){
        String id = "24699994";
        try{
            instance.delete(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    
    
    @Test
    public void testDeletePublicationok() throws Exception {
        String id = "2";
        try{
            instance.delete(id);
            assertTrue(true);
        }catch(Exception x){
             fail("sono riuscito a fare l' op");   
        }
    }
    
    @Test
    public void testGetPublicationok() throws Exception {
        int id = 2;
        try{
            instance.getPublicationById(id);
            assertTrue(true);
        }catch(Exception x){
             fail("sono riuscito a fare l' op");   
        }
    }
    
    
    @Test
    public void testGetMinIdPublication() throws Exception {
        int id = -2;
        try{
            instance.getPublicationById(id);
            fail("sono riuscito a fare l' op"); 
        }catch(Exception x){
              assertTrue(true); 
        }
    }
    
    
    
    
   
}