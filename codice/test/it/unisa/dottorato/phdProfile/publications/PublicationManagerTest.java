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
    public void testInsertPublicationok() throws Exception {
       
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
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
     @Test
    public void testInsertMinId() throws Exception {
        pub.setIdPublication(-1);
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMaxId() throws Exception {
        pub.setIdPublication(supint100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertFormatId() throws Exception {
        pub.setIdPublication(testint("@[[à%Y%"));
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testInsertMinTitle() throws Exception {
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
    public void testInsertMaxTitle() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testInsertMinPublicationIssue() throws Exception {
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
    public void testInsertMaxPublicationIssue() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testInsertMinYear() throws Exception {
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
    public void testInsertMaxYear() throws Exception {
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
    public void testInsertMinNumberPages() throws Exception {
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
    public void testInsertMinLink() throws Exception {
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
    public void testInsertMaxLink() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinType() throws Exception {
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
    public void testInsertMaxType() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinOtherAuthors() throws Exception {
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
    public void testInsertMaxOtherAuthors() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testInsertMinAbstract() throws Exception {
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
    public void testInsertMinfkStudent() throws Exception {
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
    public void testInsertMaxfkStudent() throws Exception {
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors("Marco Orsi");
        pub.setAbstract("Prova");
        pub.setFkPhdstudent(testsup100());
              
         try{
            instance.insert(pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    ////////////////////
     @Test
    public void testUpdatePublicationok() throws Exception {
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
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    
  
    
    
    @Test
    public void testUpdateFormatId() throws Exception {
        int oldId = 2;
        pub.setIdPublication(testint("@[[à%Y%"));
              
         try{
           instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
    @Test
    public void testUpdateMinTitle() throws Exception {
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
    public void testUpdateMaxTitle() throws Exception {
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle(testsup100());
              
         try{
            instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
     @Test
    public void testUpdateMinPublicationIssue() throws Exception {
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
    public void testUpdateMaxPublicationIssue() throws Exception {
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue(testsup100());
              
         try{
             instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateMinYear() throws Exception {
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
    public void testUpdateMaxYear() throws Exception {
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
    public void testUpdateMinNumberPages() throws Exception {
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
    public void testUpdateMinLink() throws Exception {
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
    public void testUpdateMaxLink() throws Exception {
         int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink(testsup100());
              
         try{
           instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinType() throws Exception {
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
    public void testUpdateMaxType() throws Exception {
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType(testsup100());
              
         try{
          instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinOtherAuthors() throws Exception {
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
    public void testUpdateMaxOtherAuthors() throws Exception {
        int oldId = 2;
        pub.setIdPublication(2);
        pub.setTitle("Prova di pubblicazione");
        pub.setPublicationIssue("Demo");
        pub.setYear("2015");
        pub.setNumberPages(240);
        pub.setLink("www.unisa.org");
        pub.setType("Prova");
        pub.setAuthors(testsup100());
              
         try{
               instance.update(oldId,pub);
            fail("fail");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testUpdateMinAbstract() throws Exception {
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
    public void testDeleteMinIdPublication() throws Exception {
        String id = testsup100();
        try{
            instance.delete(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
    
    @Test
    public void testDeleteMaxIdPublication() throws Exception {
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
        String id = "";
        try{
            instance.delete(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);    
        }
    }
   
    
    /////////////////////
    
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