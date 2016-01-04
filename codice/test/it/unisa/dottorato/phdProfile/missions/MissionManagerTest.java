/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.missions;

import it.unisa.dottorato.account.PhdStudent;
import java.sql.SQLException;
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
    public void testInsertOk(){
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
    public void testInsertDescriptionMax(){
        m.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
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
    public void testInsertPlaceNull(){
        m.setDescription("prova");
        m.setPlace("");
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
    public void testInsertPlaceMax(){
        m.setDescription("prova");
        m.setPlace("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
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
    public void testInsertReferenceMax(){
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
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
    public void testInsertStartDateNull(){
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
        m.setStartDate(null);
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
    public void testInsertStartDateFormatError(){
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
        m.setStartDate(new Date(204, -9, 15));
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
    public void testInsertEndDateNull() {
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
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
    public void testInsertEndDateFormatError(){
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
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
    public void testInsertEndDateFkPhdstudentNull(){
      m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("");
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
    public void testInsertEndDateFkPhdstudentMax(){
      m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
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
    
    
     /**
     * Test of insert method, of class MissionManager.
     * @throws java.lang.Exception
     */
   
    /**
     * Test of update method, of class MissionManager.
     */
    @Test
    public void testUpdateOk(){
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
    public void testUpdateIdNotExists(){
        int oldMissionID = -2;
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
    public void testUpdateIdMax(){
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
    public void testUpdateIdMin(){
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
    public void testUpdateDescriptionMax(){
        int oldMissionID = 1;
        m.setDescription("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
        m.setPlace("unisaProva");
        m.setReference("io");
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
    public void testUpdatePlaceNull(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("");
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
    public void testUpdatePlaceMax(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
        m.setReference("io");
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
    public void testUpdateReferenceMax(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("aulap1");
        m.setReference("Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,");
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
    public void testUpdateStartDateNull(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
        m.setStartDate(null);
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
    public void testUpdateStartDateFormatError(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
        m.setReference("Prova");
        m.setStartDate(new Date(204, -9, 15));
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
    public void testUpdateEndDateNull(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("yuiopqqwertyuiopq");
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
    public void testUpdateEndDateFormatError(){
        int oldMissionID = 1;
        m.setDescription("prova");
        m.setPlace("aulap2");
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
    public void testDeleteIdOk(){
        m.setIdMission(2);
        m.setDescription("prova");
        m.setPlace("unisaProva");
        m.setReference("io");
        m.setStartDate(new Date(2014, 02, 15));
        m.setEndDate(new Date(2015, 02, 05));
        m.setFkPhdstudent("dinucci@hotmail.it");
       
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
    public void testDeleteIdMin(){
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
    public void testDeleteIdMax(){
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
   

    /**
     * Test of getMissionById method, of class MissionManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMissionByIdOk(){
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
    public void testGetMissionByIdMin(){
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
    public void testGetMissionByIdMax(){
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
    public void testGetMissionByIdNotExists(){
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
    public void testGetAllMissionsOfOk(){
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
    public void testGetAllMissionsFkPhdStudentNull(){
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
    public void testGetAllMissionsFkPhdStudentMax(){
        String fkPhdstuent="Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,Prova superamento stringa,.it";
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
    public void testGetAllMissionsFkPhdStudentFormatError(){
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
    
}
