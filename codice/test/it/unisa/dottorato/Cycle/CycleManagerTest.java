/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.Curriculum.Curriculum;
import it.unisa.dottorato.Curriculum.CurriculumManager;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.curriculumcic.Curriculumcic;
import it.unisa.dottorato.exception.NameException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleOk() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleYearMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleYearMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleYearFormatError() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleok() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleYearMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleYearMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateCycleYearFormatError() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorOk() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorFormatError() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCycleCoordinatorfkProfessorNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleCoordinatorok() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleCoordinatorNumerMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleCoordinatorNumerMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleCoordinatorNumerNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCycleCoordinatorOk() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCycleCoordinatorNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCycleCoordinatorNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCycleCoordinatorNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleOk() throws Exception {
        int number = 17;
        try{
            instance.deleteCycle(number);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }
    
    /**
     * Test of deleteCycle method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCycleNumberNotExist() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCycleByNumberOk() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCycleByNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCycleByNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCycleByNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCycleListOk() throws Exception {
        try{
            ArrayList<Cycle> result = instance.getCycleList();
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of getCyclesListNumers method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCyclesListNumersOk() throws Exception {
        try{
           ArrayList<Integer> result = instance.getCyclesListNumers();
           assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCollegeCycleOk() throws Exception {
        int number = 15;
        try{
            ArrayList<Professor> result = instance.viewCollegeCycle(number);
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of viewCollegeCycle method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCollegeCycleNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCollegeCycleNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testViewCollegeCycleNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicOk() throws Exception {
        CurriculumManager curr=CurriculumManager.getInstance();
        Curriculum x=new Curriculum();
        x.setName("prova20");
        x.setDescription("descrprova");
        curr.insert(x);
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("prova20");
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNumberCyclenotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNameMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNameMax() throws Exception {
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("seseseseseseseseseseseseseseseseseseseseseseseseseseses"
                + "seseseseseseseseseseseseseseseseseseseseseseseseseseses");
        pCurriculumcic.setfkCycle(15);
        try{
            instance.insertCurriculumcic(pCurriculumcic);
            fail("sono riuscito ad effettuare l' op");
        }catch(ClassNotFoundException | SQLException | IOException | NameException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of insertCurriculumcic method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicNameNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertCurriculumcicCurriculumcicExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicOk() throws Exception {
        Curriculumcic pCurriculumcic = new Curriculumcic();
        pCurriculumcic.setfkCurriculum("Marketing e Comunicazione");
        pCurriculumcic.setfkCycle(15);
        instance.insertCurriculumcic(pCurriculumcic);
        int fkCycle = 15;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNumberMax() throws Exception {
        int fkCycle = 3454;
        String fkCurriculum = "Marketing e Comunicazione";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(ClassNotFoundException | SQLException | IOException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNumberNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNameMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNameMax() throws Exception {
        int fkCycle = 15;
        String fkCurriculum = "seseseseseseseseseseseseseseseseseseseseseseseseseseses"
                            + "seseseseseseseseseseseseseseseseseseseseseseseseseseses";
        try{
            instance.deleteCurriculumcic(fkCycle, fkCurriculum);
            fail("sono riuscito a fare l' op");
        }catch(ClassNotFoundException | SQLException | IOException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of deleteCurriculumcic method, of class CycleManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNameNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteCurriculumcicNotExists() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumcicListOk() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumcicListNumberMin() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumcicListNumberMax() throws Exception {
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurriculumcicListNumberNotExists() throws Exception {
        int number = 60;
        try{
            instance.getCurriculumcicList(number);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

}
