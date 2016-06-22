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
import org.junit.Ignore;

/**
 *
 * @author cadav
 */
public class AccountManagerTest {
    private AccountManager account;
    
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
      account= AccountManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AccountManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
    public void testGetAccountList() throws Exception {
        System.out.println("getAccountList");
        AccountManager instance = new AccountManager();
        ArrayList<Account> expResult = null;
        ArrayList<Account> result = instance.getAccountList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhdStudents method, of class AccountManager.
     */
    @Test @Ignore
    public void testGetPhdStudents() throws Exception {
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
    @Test @Ignore
    public void testGetProfessors() throws Exception {
        System.out.println("getProfessors");
        AccountManager instance = new AccountManager();
        ArrayList<Professor> expResult = null;
        ArrayList<Professor> result = instance.getProfessors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateIsAdmin method, of class AccountManager.
     */
    @Test @Ignore
    public void testUpdateIsAdmin() throws Exception {
        System.out.println("updateIsAdmin");
        String secondaryEmail = "";
        boolean var = false;
        AccountManager instance = new AccountManager();
        instance.updateIsAdmin(secondaryEmail, var);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUser method, of class AccountManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
    public void testChangeType() throws Exception {
        System.out.println("changeType");
        String email = "";
        String newType = "";
        AccountManager instance = new AccountManager();
        instance.changeType(email, newType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inviteUser method, of class AccountManager.
     */
    @Test @Ignore
    public void testInviteUser() throws Exception {
        System.out.println("inviteUser");
        String email = "";
        AccountManager instance = new AccountManager();
        instance.inviteUser(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMail method, of class AccountManager.
     */
    @Test @Ignore
    public void testSendMail() throws Exception {
        System.out.println("sendMail");
        String email = "";
        String oggetto = "";
        String testo = "";
        AccountManager instance = new AccountManager();
        instance.sendMail(email, oggetto, testo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertStudentTutor method, of class AccountManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
    @Test @Ignore
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
     * Test of testSecondaryEmail method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestSecondaryEmail() throws Exception {
        System.out.println("testSecondaryEmail");
        String email = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testSecondaryEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPassword method, of class AccountManager.
     */
    @Test @Ignore
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
    @Test @Ignore
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

    /**
     * Test of testTelephone method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestTelephone() throws Exception {
        System.out.println("testTelephone");
        String tele = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testTelephone(tele);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testEmail method, of class AccountManager.
     */
    @Test @Ignore
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
     * Test of testname method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestname() throws Exception {
        System.out.println("testname");
        String name = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testname(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testLink method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestLink() throws Exception {
        System.out.println("testLink");
        String link = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testLink(link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDepartment method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestDepartment() throws Exception {
        System.out.println("testDepartment");
        String dep = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testDepartment(dep);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testType method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestType() throws Exception {
        System.out.println("testType");
        String type = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testResearchInterest method, of class AccountManager.
     */
    @Test @Ignore
    public void testTestResearchInterest() throws Exception {
        System.out.println("testResearchInterest");
        String resInt = "";
        AccountManager instance = new AccountManager();
        String expResult = "";
        String result = instance.testResearchInterest(resInt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhdStudentsCoursebyProfessor method, of class AccountManager.
     */
    @Test 
    public void testGetPhdStudentsCoursebyProfessor() throws Exception {
        System.out.println("getPhdStudentsCoursebyProfessor");
        String professor = "adelucia@hotmail.it";
        int ciclo = 16;
        
        ArrayList<PhdStudent> result =account.getPhdStudentsCoursebyProfessor(professor, ciclo);
        assertNotNull(result);
   
    }
    
}
