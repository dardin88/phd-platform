/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

import java.util.ArrayList;
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
        ArrayList<Account> result=instance.getAccountList();
        assertNotNull(result);
    }

    
    
    /**
     * Test of viewProfile method, of class AccountManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewProfile() throws Exception {
        System.out.println("viewProfile");
        Account pAccount = null;
        AccountManager instance = new AccountManager();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.viewProfile(pAccount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     */
    @Test
    public void testSearchUser() throws Exception {
        System.out.println("searchUser");
        String search = "";
        AccountManager instance = new AccountManager();
        ArrayList<Account> expResult = null;
        ArrayList<Account> result = instance.searchUser(search);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfile method, of class AccountManager.
     */
    @Test
    public void testUpdateProfile() throws Exception {
        System.out.println("updateProfile");
        String key = "";
        Account pAccount = null;
        AccountManager instance = new AccountManager();
        instance.updateProfile(key, pAccount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeType method, of class AccountManager.
     */
    @Test
    public void testChangeType() throws Exception {
        System.out.println("changeType");
        Account pAccount = null;
        String newType = "";
        AccountManager instance = new AccountManager();
        instance.changeType(pAccount, newType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inviteUser method, of class AccountManager.
     */
    @Test
    public void testInviteUser() throws Exception {
        System.out.println("inviteUser");
        String email = "";
        AccountManager instance = new AccountManager();
        instance.inviteUser(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertStudentTutor method, of class AccountManager.
     */
    @Test
    public void testInsertStudentTutor() throws Exception {
        System.out.println("insertStudentTutor");
        String fkPhdstudent = "";
        String fkProfessor = "";
        AccountManager instance = new AccountManager();
        instance.insertStudentTutor(fkPhdstudent, fkProfessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     */
    @Test
    public void testUpdateStudentTutor() throws Exception {
        System.out.println("updateStudentTutor");
        String fkPhdstudent = "";
        String Tutor = "";
        AccountManager instance = new AccountManager();
        instance.updateStudentTutor(fkPhdstudent, Tutor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
