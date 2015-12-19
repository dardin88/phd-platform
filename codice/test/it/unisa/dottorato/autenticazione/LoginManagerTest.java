/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.autenticazione;

import it.unisa.dottorato.account.Account;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class LoginManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LoginManager expResult = null;
        LoginManager result = LoginManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class LoginManager.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String pUsername = "";
        String pPassword = "";
        LoginManager instance = new LoginManager();
        Account expResult = null;
        Account result = instance.login(pUsername, pPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class LoginManager.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        HttpSession session = null;
        LoginManager instance = new LoginManager();
        instance.logout(session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class LoginManager.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        Account pAccount = null;
        LoginManager instance = new LoginManager();
        instance.register(pAccount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testAccount method, of class LoginManager.
     */
    @Test
    public void testTestAccount() throws Exception {
        System.out.println("testAccount");
        Account account = null;
        LoginManager instance = new LoginManager();
        Account expResult = null;
        Account result = instance.testAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testEmail method, of class LoginManager.
     */
    @Test
    public void testTestEmail() throws Exception {
        System.out.println("testEmail");
        String email = "";
        LoginManager instance = new LoginManager();
        String expResult = "";
        String result = instance.testEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPassword method, of class LoginManager.
     */
    @Test
    public void testTestPassword() throws Exception {
        System.out.println("testPassword");
        String pass = "";
        LoginManager instance = new LoginManager();
        String expResult = "";
        String result = instance.testPassword(pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
