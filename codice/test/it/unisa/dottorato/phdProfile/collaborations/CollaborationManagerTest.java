/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdProfile.collaborations;

import it.unisa.dottorato.account.PhdStudent;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tommaso
 */
public class CollaborationManagerTest {
    private CollaborationManager instance;
    private Collaboration c;
    public CollaborationManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=CollaborationManager.getInstance();
        c=new Collaboration();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CollaborationManager.
     */
    @Test
    public void testGetInstance() {
        CollaborationManager result=CollaborationManager.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of insert method, of class CollaborationManager.
     */
    @Test
    public void testInsertOk() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        c.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertDescriptionNull() throws Exception {
        c.setDescription(null);
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertDescriptionMax() throws Exception {
        c.setDescription(testsup65536());
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateNull() throws Exception {
        c.setDescription("prova");
        c.setEndDate(null);
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateMax() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2012225,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertEndDateMin() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(225,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
     /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStartDateNull() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2015,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(null);
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStartDateMax() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2025,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(201533,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertStartDateMin() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(201,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertIstitutionNull() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution(null);
        c.setStartDate(new Date(2001,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of insert method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertIstitutionMax() throws Exception {
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution("qqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiop"
                + "qqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiop");
        c.setStartDate(new Date(2001,10,05));
        try{
            instance.insert(c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    

    /**
     * Test of update method, of class CollaborationManager.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        int oldCollaborationID = 0;
        Collaboration pCollaboration = null;
        CollaborationManager instance = null;
        instance.update(oldCollaborationID, pCollaboration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of update method, of class CollaborationManager.
     */
    @Test
    public void testUpdateOk() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
     }
    
     /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdMin() throws Exception {
        int old=-8;
        c.setDescription("prova");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
     /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdMax() throws Exception {
        int old=98989898;
        c.setDescription("prova");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
     /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIdnotExists() throws Exception {
        int old=8989;
        c.setDescription("prova");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateDescriptionNull() throws Exception {
        int old=1;
        c.setDescription(null);
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }

    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateDescriptionMax() throws Exception {
        int old=1;
        c.setDescription(testsup65536());
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEndDateNull() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(null);
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEndDateMax() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2012225,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateEndDateMin() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(225,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
     /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStartDateNull() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2015,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(null);
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStartDateMax() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2025,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(201533,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStartDateMin() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(201,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIstitutionNull() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution(null);
        c.setStartDate(new Date(2001,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }
    
    /**
     * Test of update method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateIstitutionMax() throws Exception {
        int old=1;
        c.setDescription("prova");
        c.setEndDate(new Date(2005,10,05));
        c.setIstitution("qqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiop"
                + "qqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiop");
        c.setStartDate(new Date(2001,10,05));
        try{
            instance.update(old,c);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
     }

    /**
     * Test of delete method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteOk() throws Exception {
        int idCollaboration = 1;
        try{
            instance.delete(idCollaboration);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of delete method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdMin() throws Exception {
        int idCollaboration = -1;
        try{
            instance.delete(idCollaboration);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true); 
        }
    }
    
    /**
     * Test of delete method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdMax() throws Exception {
        int idCollaboration = 89898988;
        try{
            instance.delete(idCollaboration);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true); 
        }
    }
    
    /**
     * Test of delete method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteIdNotExists() throws Exception {
        int idCollaboration = 9999;
        try{
            instance.delete(idCollaboration);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true); 
        }
    }

    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCollaborationByIdok() throws Exception {
        int id = 1;
        try{
            instance.getCollaborationById(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCollaborationByIdMin() throws Exception {
        int id = -8;
        try{
            instance.getCollaborationById(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCollaborationByIdMax() throws Exception {
        int id = 8797978;
        try{
            instance.getCollaborationById(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCollaborationByIdNotExists() throws Exception {
        int id = 4855;
        try{
            Collaboration co=instance.getCollaborationById(id);
            assertNull(co);
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCollaborationOk() throws Exception {
        String fkPhdstuent="dinucci@hotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllCollaborationOf(phdStudent);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
     /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentNull() throws Exception {
        PhdStudent phdStudent = null;
        try{
            instance.getAllCollaborationOf(phdStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
     /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentMax() throws Exception {
        String fkPhdstuent="dinucciwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerwerhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllCollaborationOf(phdStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    } 

     /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentFormatError() throws Exception {
        String fkPhdstuent="erwerwerwerhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllCollaborationOf(phdStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    } 
    
    /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentNotExists() throws Exception {
        String fkPhdstuent="erwer@werwerhotmail.it";
        PhdStudent phdStudent = new PhdStudent();
        phdStudent.setfkAccount(fkPhdstuent);
        try{
            instance.getAllCollaborationOf(phdStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    } 
    
     private String testsup65536() {
        String c="dsdffdffdf";
        for(int e=0; e<6600; e++)
            c=c.concat(c);
        return c;
    }
}
