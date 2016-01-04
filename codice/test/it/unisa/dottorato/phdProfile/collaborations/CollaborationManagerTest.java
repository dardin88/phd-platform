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
    public void testInsertOk(){
        c.setDescription("provainserimento");
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
     */
    @Test
    public void testInsertDescriptionNull(){
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
     */
    @Test
    public void testInsertEndDateNull(){
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
     */
    @Test
    public void testInsertEndDateMax(){
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
     */
    @Test
    public void testInsertEndDateMin(){
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
     */
    @Test
    public void testInsertStartDateNull(){
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
     */
    @Test
    public void testInsertStartDateMax(){
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
     */
    @Test
    public void testInsertStartDateMin(){
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
     */
    @Test
    public void testInsertIstitutionNull(){
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
     */
    @Test
    public void testInsertIstitutionMax(){
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
    public void testUpdateOk(){
        int old=2;
        c.setDescription("provamodifica");
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
     */
    @Test
    public void testUpdateIdMin(){
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
     */
    @Test
    public void testUpdateIdMax(){
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
     */
    @Test
    public void testUpdateIdnotExists(){
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
     */
    @Test
    public void testUpdateDescriptionNull(){
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
     */
    @Test
    public void testUpdateEndDateNull(){
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
     */
    @Test
    public void testUpdateEndDateMax(){
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
     */
    @Test
    public void testUpdateEndDateMin(){
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
     */
    @Test
    public void testUpdateStartDateNull(){
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
     */
    @Test
    public void testUpdateStartDateMax(){
        int old=2;
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
     */
    @Test
    public void testUpdateStartDateMin(){
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
     */
    @Test
    public void testUpdateIstitutionNull(){
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
     */
    @Test
    public void testUpdateIstitutionMax(){
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
     */
    @Test
    public void testDeleteOk(){
        c.setDescription("provaicav");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        c.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(c);
        }catch(Exception e){   
        }
        int n=0;
        try{
            n=instance.nextNumber()-1;
        }catch(Exception e){   
        }
        int idCollaboration = n;
        try{
            instance.delete(idCollaboration);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of delete method, of class CollaborationManager.
     */
    @Test
    public void testDeleteIdMin(){
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
     */
    @Test
    public void testDeleteIdMax(){
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
     */
    @Test
    public void testDeleteIdNotExists(){
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
     */
    @Test
    public void testGetCollaborationByIdok(){
        c.setDescription("provaicavdd");
        c.setEndDate(new Date(2016,10,05));
        c.setIstitution("Dipartimento di Informatica");
        c.setStartDate(new Date(2015,10,05));
        c.setFkPhdstudent("dinucci@hotmail.it");
        try{
            instance.insert(c);
        }catch(Exception e){   
        }
        int n=0;
        try{
            n=instance.nextNumber()-1;
        }catch(Exception e){   
        }
        int id = n;
        try{
            instance.getCollaborationById(id);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    /**
     * Test of getCollaborationById method, of class CollaborationManager.
     */
    @Test
    public void testGetCollaborationByIdMin(){
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
     */
    @Test
    public void testGetCollaborationByIdMax(){
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
     */
    @Test
    public void testGetCollaborationByIdNotExists(){
        int id = 4855;
        try{
            instance.getCollaborationById(id);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    /**
     * Test of getAllCollaborationOf method, of class CollaborationManager.
     */
    @Test
    public void testGetAllCollaborationOk(){
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
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentNull(){
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
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentMax(){
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
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentFormatError(){
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
     */
    @Test
    public void testGetAllCollaborationFkPhdStudentNotExists(){
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
}
