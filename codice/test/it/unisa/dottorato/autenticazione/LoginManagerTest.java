/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.AccountManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
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
public class LoginManagerTest {
     private LoginManager instance;
     private AccountManager instances;
     private Account acc;
     
    public LoginManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = LoginManager.getInstance();
        instances = AccountManager.getInstance();
        acc=new Account();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class LoginManager.
     */
   
    
    @Test
    public void testgetInstance() {
         LoginManager result = LoginManager.getInstance();
         assertNotNull(result);
         
    }
    
@Test
    public void testMinUserLogin() throws Exception {
       String user = "null";
       String pass = "";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }

    
    @Test
    public void testMaxUserLogin() throws Exception {
       String user = testsup100();
       String pass = "";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testMinPassLogin() throws Exception {
       String user = "adelucia@hotmail.it";
       String pass = "";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testMaxPassLogin() throws Exception {
       String user = "adelucia@hotmail.it";
       String pass = testsup100();
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testLoginok() throws Exception {
       String user = "adelucia@hotmail.it";
       String pass = null;
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    /////////////////////
    @Test
    public void testIscrizioneok() throws Exception {
       acc.setEmail("pippo@unisa.com");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("Verdi");
       acc.setName("Antonio");
       acc.setPassword("zio");
       acc.setTypeAccount("phdstudent");    
        
         try{
            instance.register(acc);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
///////// metodi di utilità
    
    
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
