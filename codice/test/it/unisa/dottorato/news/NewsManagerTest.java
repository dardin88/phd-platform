/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

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
public class NewsManagerTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class NewsManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        NewsManager expResult = null;
        NewsManager result = NewsManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertNews method, of class NewsManager.
     */
    @Test
    public void testInsertNews() throws Exception {
        System.out.println("insertNews");
        News anews = null;
        NewsManager instance = null;
        instance.insertNews(anews);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewsById method, of class NewsManager.
     */
    @Test
    public void testGetNewsById() throws Exception {
        System.out.println("getNewsById");
        int aidnews = 0;
        NewsManager instance = null;
        News expResult = null;
        News result = instance.getNewsById(aidnews);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNews method, of class NewsManager.
     */
    @Test
    public void testDeleteNews() {
        System.out.println("deleteNews");
        int aidnews = 0;
        NewsManager instance = null;
        instance.deleteNews(aidnews);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update_news method, of class NewsManager.
     */
    @Test
    public void testUpdate_news() throws Exception {
        System.out.println("update_news");
        int oldNewsId = 0;
        News pNews = null;
        NewsManager instance = null;
        instance.update_news(oldNewsId, pNews);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewsByTypeOfTitle method, of class NewsManager.
     */
    @Test
    public void testGetNewsByTypeOfTitle() throws Exception {
        System.out.println("getNewsByTypeOfTitle");
        String title = "";
        NewsManager instance = null;
        ArrayList<News> expResult = null;
        ArrayList<News> result = instance.getNewsByTypeOfTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllNews method, of class NewsManager.
     */
    @Test
    public void testGetAllNews() throws Exception {
        System.out.println("getAllNews");
        NewsManager instance = null;
        ArrayList<News> expResult = null;
        ArrayList<News> result = instance.getAllNews();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testid method, of class NewsManager.
     */
    @Test
    public void testTestid() throws Exception {
        System.out.println("testid");
        int id = 0;
        NewsManager instance = null;
        int expResult = 0;
        int result = instance.testid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testTitle method, of class NewsManager.
     */
    @Test
    public void testTestTitle() throws Exception {
        System.out.println("testTitle");
        String title = "";
        NewsManager instance = null;
        String expResult = "";
        String result = instance.testTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testDescription method, of class NewsManager.
     */
    @Test
    public void testTestDescription() throws Exception {
        System.out.println("testDescription");
        String description = "";
        NewsManager instance = null;
        String expResult = "";
        String result = instance.testDescription(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextNumber method, of class NewsManager.
     */
    @Test
    public void testNextNumber() throws Exception {
        System.out.println("nextNumber");
        NewsManager instance = null;
        int expResult = 0;
        int result = instance.nextNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
