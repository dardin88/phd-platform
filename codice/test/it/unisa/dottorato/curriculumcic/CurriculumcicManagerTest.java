/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.curriculumcic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso Minichiello
 */
public class CurriculumcicManagerTest {
    private CurriculumcicManager instance;
    private Curriculumcic c;
    
    public CurriculumcicManagerTest() {
    }
    
    @Before
    public void setUp() {
    instance=CurriculumcicManager.getInstance();
    c=new Curriculumcic();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CurriculumcicManager.
     */
    @Test
    public void testGetInstance() {
        CurriculumcicManager result=CurriculumcicManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorOk() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
            
        
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNameMin() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
     
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNameMax() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("asqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqw"
                         + "sasqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNameNotExists() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("prova");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNumberMin() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-8);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNumberMax() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(8989);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorNumberNotExists() throws Exception {
        String fkProfessor = "wrestler@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(50);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorfkProfessorMin() throws Exception {
        String fkProfessor = "";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorfkProfessorMax() throws Exception {
        String fkProfessor = "sdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdssdsdsdsdsdsdsdsdsds";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }

    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorfkProfessorFormatError() throws Exception {
        String fkProfessor = "sdsdsdsdssdsdsdsdsdsdsdsdsds";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertProfessorfkProfessorNotExists() throws Exception {
        String fkProfessor = "dsdsdsdsdsds@hotmail.com";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertProfessor(c, fkProfessor);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
           assertTrue(true);
        } 
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorOk() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameMin() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameMax() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("asqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqw"
                         + "sasqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameNotExists() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("aqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberMin() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-8);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberMax() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(8989);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberNotExists() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorMin() throws Exception {
        String fkProfessor = "";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorMax() throws Exception {
        String fkProfessor = "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnm";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorFormatError() throws Exception {
        String fkProfessor = "cvbnmqwertyuioplkjhgfdsazxcvbnm";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorNotExists() throws Exception {
        String fkProfessor = "tyuioplkjhgf@hotmail.com";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertCurriculumcicCoordinator(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
 
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorOk() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l'op");
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameMin() throws Exception {
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameNotExists() throws Exception {
        c.setfkCurriculum("nologie del Software");
        c.setfkCycle(15);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberMin() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-9);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(4554);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberNotExists() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
            instance.deleteCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

     /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorOk() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewCurriculumcicCoordinator(c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l'op");
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameMin() throws Exception {
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameNotExists() throws Exception {
        c.setfkCurriculum("nologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberMin() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-9);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(4554);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testViewCurriculumcicCoordinator method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberNotExists() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
            instance.viewCurriculumcicCoordinator(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorOk() throws Exception {
        String fkProfessor = "adelucia@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNameMin() throws Exception {
        String fkProfessor = "adelucia@hotmail.it";
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNameMax() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("asqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqw"
                         + "sasqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNameNotExists() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
        c.setfkCurriculum("aqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
          instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNumberMin() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-8);
        try{
           instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNumberMax() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(8989);
        try{
           instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorNumberNotExists() throws Exception {
        String fkProfessor = "dracula@hotmail.it";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
           instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorFkProfessorMin() throws Exception {
        String fkProfessor = "";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorFkProfessorMax() throws Exception {
        String fkProfessor = "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnm";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorFkProfessorFormatError() throws Exception {
        String fkProfessor = "cvbnmqwertyuioplkjhgfdsazxcvbnm";
                c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of testDeleteProfessor method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteProfessorFkProfessorNotExists() throws Exception {
        String fkProfessor = "tyuioplkjhgf@hotmail.com";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
           instance.DeleteProfessor(c, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }   

    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test 
    public void testInsertPhdSudentOk() throws Exception {
        String fkPhdstudent = "ballo@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNameMin() throws Exception {
        String fkPhdstudent = "adelucia@hotmail.it";
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNameMax() throws Exception {
        String fkPhdstudent = "dracula@hotmail.it";
        c.setfkCurriculum("asqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqw"
                         + "sasqwasqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNameNotExists() throws Exception {
        String fkPhdstudent = "dracula@hotmail.it";
        c.setfkCurriculum("aqwsaqwsaqwsaqws");
        c.setfkCycle(15);
        try{
          instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNumberMin() throws Exception {
        String fkPhdstudent = "dracula@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-8);
        try{
           instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNumberMax() throws Exception {
        String fkPhdstudent = "dracula@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(8989);
        try{
           instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentNumberNotExists() throws Exception {
        String fkPhdstudent = "dracula@hotmail.it";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
           instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentMin() throws Exception {
        String fkPhdstudent = "";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentMax() throws Exception {
        String fkPhdstudent = "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhgfdsazxcvbnm";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentFormatError() throws Exception {
        String fkPhdstudent = "cvbnmqwertyuioplkjhgfdsazxcvbnm";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertPhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentNotExists() throws Exception {
        String fkPhdstudent = "tyuioplkjhgf@hotmail.com";
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
           instance.insertPhdSudent(c, fkPhdstudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }   
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListOk() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewProfessorList(c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l'op");
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNameMin() throws Exception {
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNameMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNameNotExists() throws Exception {
        c.setfkCurriculum("nologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNumberMin() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-9);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNumberMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(4554);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewProfessorList method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewProfessorListNumberNotExists() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
            instance.viewProfessorList(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }  
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicOk() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l'op");
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameMin() throws Exception {
        c.setfkCurriculum("");
        c.setfkCycle(15);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software"
                + "Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameNotExists() throws Exception {
        c.setfkCurriculum("nologie del Software");
        c.setfkCycle(15);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberMin() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(-9);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberMax() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(4554);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewPhdstudentCurriculumcic method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberNotExists() throws Exception {
        c.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        c.setfkCycle(60);
        try{
            instance.viewPhdstudentCurriculumcic(c);
            fail("non sono riuscito a fare l'op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePhdSudentOk() throws Exception {
        String fkPhdstudent = "ballo@hotmail.it";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentMin() throws Exception {
        String fkPhdstudent = "";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
             fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentMax() throws Exception {
        String fkPhdstudent = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
             fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentFormatError() throws Exception {
        String fkPhdstudent = "wertyuiopqwertyuiopqwertyuiop";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
             fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of DeletePhdSudent method, of class CurriculumcicManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentNotExists() throws Exception {
        String fkPhdstudent = "qwertyuiopqwertyuiop@gmail.com";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
             fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
}
