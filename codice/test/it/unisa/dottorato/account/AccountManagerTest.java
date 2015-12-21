/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

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
public class AccountManagerTest {
    
    public AccountManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AccountManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AccountManager expResult = null;
        AccountManager result = AccountManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountByEmail method, of class AccountManager.
     */
    @Test
    public void testGetAccountByEmail() throws Exception {
        System.out.println("getAccountByEmail");
        String sEmail = "";
        AccountManager instance = new AccountManager();
        Account expResult = null;
        Account result = instance.getAccountByEmail(sEmail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountList method, of class AccountManager.
     */
    @Test
    public void testGetAccountList() {
        System.out.println("getAccountList");
        AccountManager instance = new AccountManager();
        ArrayList<Account> expResult = null;
        ArrayList<Account> result = instance.getAccountList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewProfile method, of class AccountManager.
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
        System.out.println("getPhdStudents");
        AccountManager instance = new AccountManager();
        ArrayList<PhdStudent> expResult = null;
        ArrayList<PhdStudent> result = instance.getPhdStudents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfessors method, of class AccountManager.
     */
    @Test
    public void testGetProfessors() {
        System.out.println("getProfessors");
        AccountManager instance = new AccountManager();
        ArrayList<Professor> expResult = null;
        ArrayList<Professor> result = instance.getProfessors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     */
    @Test
    public void testGetTutor() throws Exception {
        System.out.println("getTutor");
        String idStudent = "";
        AccountManager instance = new AccountManager();
        Professor expResult = null;
        Professor result = instance.getTutor(idStudent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     */
    @Test
    public void testDeleteStudentTutor() throws Exception {
        System.out.println("deleteStudentTutor");
        String idStudent = "";
        AccountManager instance = new AccountManager();
        instance.deleteStudentTutor(idStudent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testAccount method, of class AccountManager.
     */
    @Test
    public void testTestAccount() throws Exception {
        System.out.println("testAccount");
        Account account = null;
        AccountManager instance = new AccountManager();
        Account expResult = null;
        Account result = instance.testAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testEmail method, of class AccountManager.
     */
    @Test
    public void testTestEmail() throws Exception {
        System.out.println("testEmail");
        String email = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPassword method, of class AccountManager.
     */
    @Test
    public void testTestPassword() throws Exception {
        System.out.println("testPassword");
        String pass = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testPassword(pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testProfileData method, of class AccountManager.
     */
    @Test
    public void testTestProfileData() throws Exception {
        System.out.println("testProfileData");
        String data = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testProfileData(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
