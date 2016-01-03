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
     */
    @Test
    public void testInsertProfessorOk(){
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
     */
    @Test
    public void testInsertProfessorNameMin(){
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
     */
    @Test
    public void testInsertProfessorNameMax(){
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
     */
    @Test
    public void testInsertProfessorNameNotExists(){
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
     */
    @Test
    public void testInsertProfessorNumberMin(){
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
     */
    @Test
    public void testInsertProfessorNumberMax(){
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
     */
    @Test
    public void testInsertProfessorNumberNotExists(){
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
     */
    @Test
    public void testInsertProfessorfkProfessorMin(){
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
     */
    @Test
    public void testInsertProfessorfkProfessorMax(){
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
     */
    @Test
    public void testInsertProfessorfkProfessorFormatError(){
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
     */
    @Test
    public void testInsertProfessorfkProfessorNotExists(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorOk(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameMin(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameMax(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNameNotExists(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberMin(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberMax(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorNumberNotExists(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorMin(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorMax(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorFormatError(){
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
     */
    @Test
    public void testInsertCurriculumcicCoordinatorFkProfessorNotExists(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorOk(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameMin(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameMax(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNameNotExists(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberMin(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberMax(){
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
     */
    @Test
    public void testDeleteCurriculumcicCoordinatorNumberNotExists(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorOk(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameMin(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameMax(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNameNotExists(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberMin(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberMax(){
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
     */
    @Test
    public void testViewCurriculumcicCoordinatorNumberNotExists(){
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
     */
    @Test
    public void testDeleteProfessorOk(){
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
     */
    @Test
    public void testDeleteProfessorNameMin(){
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
     */
    @Test
    public void testDeleteProfessorNameMax(){
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
     */
    @Test
    public void testDeleteProfessorNameNotExists(){
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
     */
    @Test
    public void testDeleteProfessorNumberMin(){
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
     */
    @Test
    public void testDeleteProfessorNumberMax(){
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
     */
    @Test
    public void testDeleteProfessorNumberNotExists(){
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
     */
    @Test
    public void testDeleteProfessorFkProfessorMin(){
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
     */
    @Test
    public void testDeleteProfessorFkProfessorMax(){
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
     */
    @Test
    public void testDeleteProfessorFkProfessorFormatError(){
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
     */
    @Test
    public void testDeleteProfessorFkProfessorNotExists(){
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
     */
    @Test 
    public void testInsertPhdSudentOk(){
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
     */
    @Test
    public void testInsertPhdSudentNameMin(){
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
     */
    @Test
    public void testInsertPhdSudentNameMax(){
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
     */
    @Test
    public void testInsertPhdSudentNameNotExists(){
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
     */
    @Test
    public void testInsertPhdSudentNumberMin(){
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
     */
    @Test
    public void testInsertPhdSudentNumberMax(){
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
     */
    @Test
    public void testInsertPhdSudentNumberNotExists(){
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
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentMin(){
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
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentMax(){
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
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentFormatError(){
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
     */
    @Test
    public void testInsertPhdSudentFkPhdstudentNotExists(){
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
     */
    @Test
    public void testviewProfessorListOk(){
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
     */
    @Test
    public void testviewProfessorListNameMin(){
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
     */
    @Test
    public void testviewProfessorListNameMax(){
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
     */
    @Test
    public void testviewProfessorListNameNotExists(){
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
     */
    @Test
    public void testviewProfessorListNumberMin(){
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
     */
    @Test
    public void testviewProfessorListNumberMax(){
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
     */
    @Test
    public void testviewProfessorListNumberNotExists(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicOk(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameMin(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameMax(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNameNotExists(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberMin(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberMax(){
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
     */
    @Test
    public void testviewPhdstudentCurriculumcicNumberNotExists(){
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
     */
    @Test
    public void testDeletePhdSudentOk(){
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
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentMin(){
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
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentMax(){
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
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentFormatError(){
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
     */
    @Test
    public void testDeletePhdSudentFKPhdsudentNotExists(){
        String fkPhdstudent = "qwertyuiopqwertyuiop@gmail.com";
        try{
            instance.DeletePhdSudent(fkPhdstudent);
             fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
}
