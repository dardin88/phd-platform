/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Curriculum;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CurriculumManagerTest {
    
    public CurriculumManagerTest() {
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
         try {
            CurriculumManager.getInstance().delete("Curriculmtest");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(CurriculumManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @Test
    public void testgetInstance() {
         System.out.println("getInstance");
         CurriculumManager result = CurriculumManager.getInstance();
         assertNotNull(result);
         
    }

    /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInsertok() throws Exception {
        
        System.out.println("insert");
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum cur=new Curriculum();
        cur.setName("Curriculmtest");
        cur.setDescription("il Curriculum è di prova");  
    }
    
    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEmptyName() throws Exception {
        
        System.out.println("insert");
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum c=new Curriculum();
        c.setName("");        
        c.setDescription("il Curriculum è di prova");  
        instance.insert(c);
    }
    
    /**
     * Test of insert method, of class CurriculumManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertMaxName() throws Exception {
        
        System.out.println("insert");
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum c=new Curriculum();
        c.setName("ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum");
        c.setDescription("il Curriculum è di prova");  
        instance.insert(c);
    }
    
    
    /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInserPresent() throws Exception {
        
        System.out.println("insert");
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum c=new Curriculum();
        c.setName("Marketing e Comunicazione");
        c.setDescription("il Curriculum è di prova"); 
        instance.insert(c);
        
    }
    
     /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInsertMaxDecription() throws Exception {
        
        System.out.println("insert");
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum c=new Curriculum();
        c.setDescription(testsup65536());
        instance.insert(c);
        Curriculum result=instance.getCurriculumByName(c.getName());
        assertNotNull(result);
        tearDown();
    }
    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        String oldNameCurriculum = "";
        Curriculum pCurriculum = null;
        CurriculumManager instance = null;
        instance.update(oldNameCurriculum, pCurriculum);
        
    }

    /**
     * Test of delete method, of class CurriculumManager.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String CurriculumName = "";
        CurriculumManager instance = null;
        instance.delete(CurriculumName);
        
    }

    /**
     * Test of getCurriculumList method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumList() throws Exception {
        System.out.println("getCurriculumList");
        CurriculumManager instance = null;
        ArrayList<Curriculum> expResult = null;
        ArrayList<Curriculum> result = instance.getCurriculumList();
        assertNotNull(result);
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumByName() throws Exception {
        System.out.println("getCurriculumByName");
        String CurriculumName = "";
        CurriculumManager instance = CurriculumManager.getInstance();
        Curriculum result = instance.getCurriculumByName(CurriculumName);
        assertNotNull(result);
    }

    private String testsup65536() {
        String c="dsdffdffdf";
        for(int e=0; e<6600; e++)
            c=c.concat(c);
        return c;
    }


}
