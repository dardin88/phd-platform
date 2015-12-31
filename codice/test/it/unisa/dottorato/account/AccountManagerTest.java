/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso Minichiello
 */
public class AccountManagerTest {
    AccountManager instance;
    Account ac;
    public AccountManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=AccountManager.getInstance();
        ac=new Account();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AccountManager.
     */
    @Test
    public void testGetInstance() {
        AccountManager result=AccountManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getAccountByEmail method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccountByEmailOk() throws Exception {
        String sEmail = "dinucci@hotmail.it";
        try{
            instance.getAccountByEmail(sEmail);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }
    
    /**
     * Test of getAccountByEmail method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccountByEmailNull() throws Exception {
        String sEmail = null;
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }
    
    /**
     * Test of getAccountByEmail method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccountByEmailMax() throws Exception {
        String sEmail = "wqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiop";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }
    
    /**
     * Test of getAccountByEmail method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccountByEmailFormatError() throws Exception {
        String sEmail = "qertyuiopwqertyuiopwqertyuiop";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }
    
    /**
     * Test of getAccountByEmail method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccountByEmailNotExists() throws Exception {
        String sEmail = "erty@uiwqertyuiop.it";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }

    /**
     * Test of getAccountList method, of class AccountManager.
     */
    @Test
    public void testGetAccountListOk() {
        ArrayList<Account> result;
        try {
            result = instance.getAccountList();
            assertNotNull(result);
        } catch (Exception ex) {
            fail("non sono riuscito a fare l' op");
        }  
    }

    
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileOk() throws Exception {
        ac.setSecondaryEmail("dinucci@hotmail.it");
        ac.setTypeAccount("phdstudent");
        try{
            ArrayList<String> result = instance.viewProfile(ac);
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileEmailNull() throws Exception {
        ac.setSecondaryEmail(null);
        ac.setTypeAccount("phdstudent");
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileEmailMax() throws Exception {
        ac.setSecondaryEmail("qwertyuoi@eeqwertyuoi@eeqwertyuoi@eeqwertyuoi@eeqwertyuoi@eeqwertyuoi@ee");
        ac.setTypeAccount("phdstudent");
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileEmailFormatError() throws Exception {
        ac.setSecondaryEmail("qwertyuodsdsdee");
        ac.setTypeAccount("phdstudent");
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileEmailNotExists() throws Exception {
        ac.setSecondaryEmail("qwertyuo@dsdsdee");
        ac.setTypeAccount("phdstudent");
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileTypeNull() throws Exception {
        ac.setSecondaryEmail("dinucci@hotmail.it");
        ac.setTypeAccount(null);
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileTypeNotDominio() throws Exception {
        ac.setSecondaryEmail("dinucci@hotmail.it");
        ac.setTypeAccount("sds");
        try{
            instance.viewProfile(ac);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfileAccountNull() throws Exception {
        Account nul=null;
        try{
            instance.viewProfile(nul);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getPhdStudents method, of class AccountManager.
     */
    @Test
    public void testGetPhdStudents() {
        ArrayList<PhdStudent> result = instance.getPhdStudents();
        assertNotNull(result);
    }

    /**
     * Test of getProfessors method, of class AccountManager.
     */
    @Test
    public void testGetProfessors() {
        ArrayList<Professor> result = instance.getProfessors();
        assertNotNull(result);
    }

    /**
     * Test of searchUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testSearchUserOk() throws Exception {
        String search = "s";
        try{
            instance.searchUser(search);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscoto a fare l' op");
        }
    }
    
    /**
     * Test of searchUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testSearchUserSearchNull() throws Exception {
        String search = null;
        try{
            instance.searchUser(search);
            fail("sono riuscoto a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileOk() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setAdmin(true);
        w.setEmail("c");
        w.setPassword("provaaaa12");
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaw3w");
        w.setLink("www.prova.it");
        w.setTelephone("08245789534");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileKeyNull() throws Exception {
        String key=null;
        PhdStudent w=(PhdStudent)ac;
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileKeyMax() throws Exception {
        String key="qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        PhdStudent w=(PhdStudent)ac;
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileKeyFormatError() throws Exception {
        String key="qwertyuiopqweqwertyuiopqwertyuiop";
        PhdStudent w=(PhdStudent)ac;
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileNameMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileNameMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("qwertyuiopqweqweqwertyuiopqweqweqwertyuiopqweqwe");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileNameFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("qw123");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("cognomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSurnameMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSurnameMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("qwertyuiopoiuytrewqwertyuiopoiuyt");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSurnameFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword(key);
        w.setSurname("wwe4");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfilePasswordMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewe");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfilePasswordMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqwqwqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfilePasswordFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSecEmailMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSecEmailMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("qwertyuiopoiuytrewqwertyuiopoiuytrewqwertyuiopoiuytrrewwqqqqwrtyuuytr");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileSecEmailFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("wertyuiopiuuytttt");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileEmailInsert() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfiletelephoneMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfiletelephoneMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895998989");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfiletelephoneFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895ssd");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileLinkMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.va.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileLinkMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.vaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiop.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileDepartmentMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.provaa.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileDepartmentMax() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaqinfoProvaqinfoProvaqinfoProvaqinfoProvaqinfoProvaq");
        w.setLink("www.provaa.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileDepartmentFormatError() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaqinfoPro23infoProvaqinfoProvaq");
        w.setLink("www.provaa.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateProfileresearchInterestMin() throws Exception {
        String key="dinucci@hotmail.it";
        PhdStudent w=(PhdStudent)ac;
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaqinfoProvaq");
        w.setLink("www.provaa.it");
        w.setTelephone("082457895");
        w.setResearchInterest("");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeOk() throws Exception {
        String newType="phdstudent";
        ac.setSecondaryEmail("dracula@hotmail.it");
        try{
            instance.changeType(ac, newType);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeNewTypeNull() throws Exception {
        String newType=null;
        ac.setSecondaryEmail("dracula@hotmail.it");
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeNewTypeNotDominio() throws Exception {
        String newType="dsdd";
        ac.setSecondaryEmail("dracula@hotmail.it");
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeEmailNull() throws Exception {
        String newType="phdstudent";
        ac.setSecondaryEmail(null);
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeEmailMax() throws Exception {
        String newType="phdstudent";
        ac.setSecondaryEmail("wertwertwerwertwertwerwertwertwerwertwertwerwertwertwerwertwertwer");
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeEmailFormatError() throws Exception {
        String newType="phdstudent";
        ac.setSecondaryEmail("wertwertwertwerwertwertwer");
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of changeType method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeTypeEmailNotexists() throws Exception {
        String newType="phdstudent";
        ac.setSecondaryEmail("wertwertwe@twerwertwertwer");
        try{
            instance.changeType(ac, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of inviteUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInviteUserOk() throws Exception {
        String email = "tommaso@hotmail.it";
        try{
            instance.inviteUser(email);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of inviteUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInviteUserNull() throws Exception {
        String email = null;
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }
    
    /**
     * Test of inviteUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInviteUserMax() throws Exception {
        String email = "qwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopq";
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }
    
    /**
     * Test of inviteUser method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInviteUserFormatError() throws Exception {
        String email = "qwertyuiopqqwertyuioppqqwertyuiopq";
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorOk() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkStudentNull() throws Exception {
        String fkPhdstudent = null;
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkStudentMax() throws Exception {
        String fkPhdstudent = "dqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkStudentFormatError() throws Exception {
        String fkPhdstudent = "dqwertyui@iopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkStudentNotExists() throws Exception {
        String fkPhdstudent = "dsd@sdsdsdsp";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkTutorNull() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = null;
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkTutorMax() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkTutorMin() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkTutorFormatError() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dotmail.itdinuccihotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStudentTutorFkTutorNotExists() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "di@hotmaill.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    
    
    

    /**
     * Test of getTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTutorOk() throws Exception {
        String idStudent = "ballo@hotmail.it";
        try{
            Professor result = instance.getTutor(idStudent);
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTutorStudentNull() throws Exception {
        String idStudent = null;
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTutorStudentMax() throws Exception {
        String idStudent = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        try{
            Professor result = instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTutorStudentFormatError() throws Exception {
        String idStudent = "qwpqwertyuopqwertyuiop";
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of getTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTutorStudentNotExists() throws Exception {
        String idStudent = "quiopqwertyuio@pqwertyuiop";
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorOk() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkStudentNull() throws Exception {
        String fkPhdstudent = null;
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkStudentMax() throws Exception {
        String fkPhdstudent = "dqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkStudentFormatError() throws Exception {
        String fkPhdstudent = "dqwertyui@iopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkStudentNotExists() throws Exception {
        String fkPhdstudent = "dsd@sdsdsdsp";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkTutorNull() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = null;
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkTutorMax() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkTutorMin() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkTutorFormatError() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dotmail.itdinuccihotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStudentTutorFkTutorNotExists() throws Exception {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "di@hotmaill.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of deleteStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteStudentTutorOk() throws Exception {
        String ins="dinucci@hotmail.it";
        String prof="adelucia@hotmail.it";
        instance.insertStudentTutor(ins, prof);
        try{
            instance.deleteStudentTutor(ins);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of deleteStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteStudentTutorStudentNull() throws Exception {
        String stud= null;
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteStudentTutorStudentMax() throws Exception {
        String stud= "qwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteStudentTutorStudentùFormatError() throws Exception {
        String stud= "qpoqwertyuiopoqwertyuiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteStudentTutor method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteStudentTutorStudentùNotExists() throws Exception {
        String stud= "qpoqwertyuiopoqwerty@uiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

}
