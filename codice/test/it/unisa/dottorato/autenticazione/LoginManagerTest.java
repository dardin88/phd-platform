/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Account;
import it.unisa.dottorato.account.AccountManager;
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
    public void testMinUserLogin(){
       String user = "";
       String pass = "";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }

    
    @Test
    public void testMaxUserLogin(){
       String user = "Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,";
       String pass = "";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testMinPassLogin(){
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
    public void testMaxPassLogin(){
       String user = "adelucia@hotmail.it";
       String pass = "Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,";
        
        
         try{
            instance.login(user,pass);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testLoginok(){
       String user = "adelucia@unisa.it";
       String pass = "testtest5";
       try{
            instance.login(user,pass);
            assertTrue(true);
        }catch(Exception x){
            fail("sono riuscito a fare l' op");
        }
    }
    
    /////////////////////
    @Test
    public void testIscrizioneok(){
       acc.setEmail("pippo2@unisa.it");
       acc.setSecondaryEmail("plut4o@gmail.com");
       acc.setSurname("Verdi");
       acc.setName("Antonio");
       acc.setPassword("ziookwrfo");
        
         try{
            instance.register(acc);
           assertTrue(true);
        }catch(Exception x){
             fail("sono riuscito a fare l' op");
        }
    }
    
     @Test
    public void testMinEmailIscrizione(){
       acc.setEmail("");
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
    
    
     @Test
    public void testMaxEmailIscrizione(){
       acc.setEmail("Prova stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massimaProva stringa massima");
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
    
    @Test
    public void testMinSurnameIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("");
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
    
    @Test
    public void testMaxSurnameIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("provaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprova");
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
    
     @Test
    public void testMinPasswordIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("prova");
       acc.setName("Antonio");
       acc.setPassword("");
       acc.setTypeAccount("Phdstudent");  
        
         try{
            instance.register(acc);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    @Test
    public void testMaxPasswordIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("prova");
       acc.setName("Antonio");
       acc.setPassword("provaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprovaprova");
       acc.setTypeAccount("phdstudent");  
        
         try{
            instance.register(acc);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    @Test
    public void testMinTypeIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("prova");
       acc.setName("Antonio");
       acc.setPassword("prova");
       acc.setTypeAccount("");  
        
         try{
            instance.register(acc);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
    
     @Test
    public void testMaxTypeIscrizione(){
       acc.setEmail("Prova@unisa.it");
       acc.setSecondaryEmail("pluto@gmail.com");
       acc.setSurname("prova");
       acc.setName("Antonio");
       acc.setPassword("prova");
       acc.setTypeAccount("prova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringaprova stringa");  
        
         try{
            instance.register(acc);
            fail("sono riuscito a fare l' op");
        }catch(Exception x){
            assertTrue(true);
        }
    }
    
    
}