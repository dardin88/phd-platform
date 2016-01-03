/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.Curriculum.Curriculum;
import it.unisa.dottorato.Curriculum.CurriculumManager;
import it.unisa.dottorato.curriculumcic.Curriculumcic;
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
public class CycleManagerTest {
    private Cycle c;
    private CycleManager instance;
    
    public CycleManagerTest() {
    }
    
    @Before
    public void setUp() {
        c=new Cycle();
        instance=CycleManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CycleManager.
     */
    @Test
    public void testGetInstance() {
        CycleManager result = CycleManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of insertCycle method, of class CycleManager.
     */
    @Test
    public void testInsertCycleOk() {
        c.setYear("2000");
        c.setDescription("prova");
        try{
            instance.insertCycle(c);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of insertCycle method, of class CycleManager.
     */
    @Test
    public void testInsertCycleYearMin(){
        c.setYear("200");
        c.setDescription("prova");
        try{
            instance.insertCycle(c);
            fail("sono riuscito a fare l' op"); 
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycle method, of class CycleManager.
     */
    @Test
    public void testInsertCycleYearMax() {
        c.setYear("20000");
        c.setDescription("prova");
        try{
            instance.insertCycle(c);
            fail("sono riuscito a fare l' op"); 
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycle method, of class CycleManager.
     */
    @Test
    public void testInsertCycleYearFormatError(){
        c.setYear("2a£d");
        c.setDescription("prova");
        try{
            instance.insertCycle(c);
            fail("sono riuscito a fare l' op"); 
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleok(){
        int Number = 15;
        c.setYear("2018");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleNumberMin(){
        int Number = -1;
        c.setYear("2018");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleNumberMax(){
        int Number = 1111;
        c.setYear("2018");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleNumberNotExists(){
        int Number = 50;
        c.setYear("2018");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleYearMin(){
        int Number = 15;
        c.setYear("201");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleYearMax(){
        int Number = 15;
        c.setYear("20100");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of updateCycle method, of class CycleManager.
     */
    @Test
    public void testUpdateCycleYearFormatError(){
        int Number = 15;
        c.setYear("e$%!");
        c.setDescription("prova update ciclo 15");
        try{
            instance.updateCycle(Number, c);
            fail("sono riuscito a fare l' op");
        }catch (Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorOk(){
        int number = 15;
        String fkProfessor = "adelucia@hotmail.it";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            assertTrue(true);
        }catch (Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorNumberMin(){
        int number = -4;
        String fkProfessor = "adelucia@hotmail.it";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorNumberMax(){
        int number = 2000;
        String fkProfessor = "adelucia@hotmail.it";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorNumberNotExists(){
        int number = 50;
        String fkProfessor = "adelucia@hotmail.it";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorMin(){
        int number = 15;
        String fkProfessor = "";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorMax(){
        int number = 15;
        String fkProfessor = "dddddddddeeeddededededededededeweewedede"
                            +"dedededededededededededededweweedededede";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorFormatError(){
        int number = 15;
        String fkProfessor = "ssssssssssssssss";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorNotExists(){
        int number = 15;
        String fkProfessor = "dsdsdsds@hotmail.com";
        try{
            instance.insertCycleCoordinator(number, fkProfessor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of deleteCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleCoordinatorok(){
        int number = 15;
        try{
            instance.deleteCycleCoordinator(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of deleteCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleCoordinatorNumerMin(){
        int number = -4;
        try{
            instance.deleteCycleCoordinator(number);
            fail("sono riuscito a fare l' op");    
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleCoordinatorNumerMax(){
        int number = 1500;
        try{
            instance.deleteCycleCoordinator(number);
            fail("sono riuscito a fare l' op");    
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleCoordinatorNumerNotExists(){
        int number = 60;
        try{
            instance.deleteCycleCoordinator(number);
            fail("sono riuscito a fare l' op");    
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of viewCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testViewCycleCoordinatorOk(){
        int number = 15;
        try{
            instance.viewCycleCoordinator(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of viewCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testViewCycleCoordinatorNumberMin(){
        int number = -1;
        try{
            instance.viewCycleCoordinator(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testViewCycleCoordinatorNumberMax(){
        int number = 4444;
        try{
            instance.viewCycleCoordinator(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewCycleCoordinator method, of class CycleManager.
     */
    @Test
    public void testViewCycleCoordinatorNumberNotExists(){
        int number = 60;
        try{
            instance.viewCycleCoordinator(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of deleteCycle method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleOk(){
        int number = 16;
        try{
            instance.deleteCycle(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }
    
    /**
     * Test of deleteCycle method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleNumberMin(){
        int number = -1;
        try{
            instance.deleteCycle(number);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCycle method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleNumberMax(){
        int number = 2000;
        try{
            instance.deleteCycle(number);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCycle method, of class CycleManager.
     */
    @Test
    public void testDeleteCycleNumberNotExist(){
        int number = 150;
        try{
            instance.deleteCycle(number);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getCycleByNumber method, of class CycleManager.
     */
    @Test
    public void testGetCycleByNumberOk(){
        int number = 15;
        try{
            instance.getCycleByNumber(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getCycleByNumber method, of class CycleManager.
     */
    @Test
    public void testGetCycleByNumberMin(){
        int number = -1;
        try{
            instance.getCycleByNumber(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCycleByNumber method, of class CycleManager.
     */
    @Test
    public void testGetCycleByNumberMax(){
        int number = 2000;
        try{
            instance.getCycleByNumber(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCycleByNumber method, of class CycleManager.
     */
    @Test
    public void testGetCycleByNumberNotExists(){
        int number = 60;
        try{
            instance.getCycleByNumber(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getCycleList method, of class CycleManager.
     */
    @Test
    public void testGetCycleListOk(){
        try{
            ArrayList<Cycle> result = instance.getCycleList();
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of getCyclesListNumers method, of class CycleManager.
     */
    @Test
    public void testGetCyclesListNumersOk(){
        try{
           instance.getCyclesListNumers();
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     */
    @Test
    public void testViewCollegeCycleOk(){
        int number = 15;
        try{
            instance.viewCollegeCycle(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     */
    @Test
    public void testViewCollegeCycleNumberMin(){
        int number = -4;
        try{
            instance.viewCollegeCycle(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     */
    @Test
    public void testViewCollegeCycleNumberMax(){
        int number = 4454;
        try{
            instance.viewCollegeCycle(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     */
    @Test
    public void testViewCollegeCycleNumberNotExists(){
        int number = 60;
        try{
            instance.viewCollegeCycle(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicOk(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("Marketing e Comunicazione");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNumberMin(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("prova20");
        pCurriculumcic.setfkCycle(-4);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNumberMax(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("prova20");
        pCurriculumcic.setfkCycle(4000);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNumberCyclenotExists(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("prova20");
        pCurriculumcic.setfkCycle(60);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNameMin(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNameMax(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("seseseseseseseseseseseseseseseseseseseseseseseseseseses"
                + "seseseseseseseseseseseseseseseseseseseseseseseseseseses");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicNameNotExists(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("weweweses");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testInsertCurriculumcicExists(){
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("Informatica, Sistemi Informativi e Tecnologie del Software");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicOk(){
        int fkCycle = 15;
        String fkCurriculum = "Informatica, Sistemi Informativi e Tecnologie del Software";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNumberMin(){
        int fkCycle = -4;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNumberMax(){
        int fkCycle = 3454;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNumberNotExists(){
        int fkCycle = 60;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNameMin(){
        int fkCycle = 15;
        String fkCurriculum = "";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNameMax(){
        int fkCycle = 15;
        String fkCurriculum = "seseseseseseseseseseseseseseseseseseseseseseseseseseses"
                            + "seseseseseseseseseseseseseseseseseseseseseseseseseseses";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNameNotExists(){
        int fkCycle = 15;
        String fkCurriculum = "seseseseseseseseseseseseseseseseseseseseseseseseseseses";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     */
    @Test
    public void testDeleteCurriculumcicNotExists(){
        int fkCycle = 15;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getCurriculumcicList method, of class CycleManager.
     */
    @Test
    public void testGetCurriculumcicListOk(){
        int number = 15;
        try{
            instance.getCurriculumcicList(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getCurriculumcicList method, of class CycleManager.
     */
    @Test
    public void testGetCurriculumcicListNumberMin(){
        int number = -4;
        try{
            instance.getCurriculumcicList(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCurriculumcicList method, of class CycleManager.
     */
    @Test
    public void testGetCurriculumcicListNumberMax(){
        int number = 5000;
        try{
            instance.getCurriculumcicList(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCurriculumcicList method, of class CycleManager.
     */
    @Test
    public void testGetCurriculumcicListNumberNotExists(){
        int number = 60;
        try{
            instance.getCurriculumcicList(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

}
