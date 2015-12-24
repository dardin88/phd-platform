/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.missions;

import it.unisa.dottorato.account.PhdStudent;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso Minichiello
 */
public class MissionManagerTest {
    private MissionManager instance;
    private Mission m;
    
    public MissionManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=MissionManager.getInstance();
        m=new Mission();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class MissionManager.
     */
    @Test
    public void testGetInstance() {
        MissionManager result=MissionManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertOk() throws Exception {
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertDescriptionMax() throws Exception {
        m.setDescription(testsup65536());
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPlaceNull() throws Exception {
        m.setDescription("prova");
        m.setPlace(null);
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertPlaceMax() throws Exception {
        m.setDescription("prova");
        m.setPlace("qwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopq");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertReferenceMax() throws Exception {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStartDateNull() throws Exception {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStartDateFormatError() throws Exception {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(204, -9, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateNull() throws Exception {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014,02, 15));
        m.setEndDate(null);
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
           fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateFormatError() throws Exception {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014,02, 15));
        m.setEndDate(new Date(204,02, -5));
        m.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateFkPhdstudentNull() throws Exception {
      m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent(null);
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateFkPhdstudentMax() throws Exception {
      m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("qwertyoiuoqwertyoiuoqwertyoiuoqwertyoiuoqwertyoiuoqwertyoiuo");
        try{
            instance.insert(m);
           fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateFkPhdstudentFormatError() throws Exception {
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("qwertyoiuuoqwertyoiuo");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateFkPhdstudentNotExists() throws Exception {
      m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("qwertyoiu@uoqwertyoiuo");
        try{
            instance.insert(m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class MissionManager.
     */
    @Test
    public void testUpdateOk() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }
    
    /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdNotExists() throws Exception {
        int oldMissionID = 5655;
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdMax() throws Exception {
        int oldMissionID = 9879879;
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdMin() throws Exception {
        int oldMissionID = -8;
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateDescriptionMax() throws Exception {
        int oldMissionID = 1;
        m.setDescription(testsup65536());
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatePlaceNull() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace(null);
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatePlaceMax() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("qwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopq");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateReferenceMax() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStartDateNull() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStartDateFormatError() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(204, -9, 15));
        m.setEndDate(new Date(2015, 02, 05));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEndDateNull() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014,02, 15));
        m.setEndDate(null);
        try{
            instance.update(oldMissionID, m);
           fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of update method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEndDateFormatError() throws Exception {
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference(testsup255());
        m.setStartDate(new Date(2014,02, 15));
        m.setEndDate(new Date(204,02, -5));
        try{
            instance.update(oldMissionID, m);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    /**
     * Test of delete method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdOk() throws Exception {
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
        instance.insert(m);
        int pMissionID = 2;
        try{
            instance.delete(pMissionID);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }                   
    }
    
    /**
     * Test of delete method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdMin() throws Exception {
        int pMissionID = -1;
        try{
            instance.delete(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }
    
    /**
     * Test of delete method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdMax() throws Exception {
        int pMissionID = 12345678;
        try{
            instance.delete(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }
    
    /**
     * Test of delete method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdNotExists() throws Exception {
        int pMissionID = 9999;
        try{
            instance.delete(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }

    /**
     * Test of getMissionById method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMissionByIdOk() throws Exception {
        int pMissionID = 1;
        try{
            instance.getMissionById(pMissionID);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }                   
    }
    
    /**
     * Test of getMissionById method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMissionByIdMin() throws Exception {
        int pMissionID = -1;
        try{
            instance.getMissionById(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }
    
    /**
     * Test of getMissionById method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMissionByIdMax() throws Exception {
        int pMissionID = 12345678;
        try{
            instance.getMissionById(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }
    
    /**
     * Test of getMissionById method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMissionByIdNotExists() throws Exception {
        int pMissionID = 9999;
        try{
            instance.getMissionById(pMissionID);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }                   
    }

    /**
     * Test of getAllMissionsOf method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMissionsOfOk() throws Exception {
        String fkPhdstuent="dinucci@hotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllMissionsOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     /**
     * Test of getAllMissionsOf method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMissionsFkPhdStudentNull() throws Exception {
        PhdStudent phdStudent = new PhdStudent();
        try{
            instance.getAllMissionsOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     /**
     * Test of getAllMissionsOf method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMissionsFkPhdStudentMax() throws Exception {
        String fkPhdstuent="dinucciwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerhotmailhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllMissionsOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     /**
     * Test of getAllMissionsOf method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMissionsFkPhdStudentFormatError() throws Exception {
        String fkPhdstuent="rwerwerwerwerwerwerwerwerwerwerhotmailhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllMissionsOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     /**
     * Test of getAllMissionsOf method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMissionsFkPhdStudentNotExists() throws Exception {
        String fkPhdstuent="derwerwerwerwe@werwerwerhotmailhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllMissionsOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     private String testsup65536() {
        String c="dsdffdffdf";
        for(int e=0; e<6600; e++)
            c=c.concat(c);
        return c;
    }
     
     private String testsup255() {
        String c="dsdffdffdf";
        for(int e=0; e<30; e++)
            c=c.concat(c);
        return c;
    }
}
