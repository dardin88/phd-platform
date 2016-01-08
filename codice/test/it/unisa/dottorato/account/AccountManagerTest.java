/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

import it.unisa.dottorato.autenticazione.EmailException;
import it.unisa.integrazione.database.exception.ConnectionException;
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
public class AccountManagerTest {
    AccountManager instance;
    Account ac;
    public AccountManagerTest() {
    }
    
    @Before
    public void setUp() {
        instance=AccountManager.getInstance();
        ac=new Account();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetInstance() {
        AccountManager result=AccountManager.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testGetAccountByEmailOk(){
        String sEmail = "dinucci@unisa.it";
        try{
            instance.getAccountByEmail(sEmail);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito ad effettuare l' op");
        }
    }

    @Test
    public void testGetAccountByEmailNull(){
        String sEmail = null;
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }

    @Test
    public void testGetAccountByEmailMax(){
        String sEmail = "wqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiopwqertyuiop";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }

    @Test
    public void testGetAccountByEmailFormatError() {
        String sEmail = "qertyuiopwqertyuiopwqertyuiop";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }

    @Test
    public void testGetAccountByEmailNotExists() {
        String sEmail = "erty@uiwqertyuiop.it";
        try{
            instance.getAccountByEmail(sEmail);
            fail("sono riuscito ad effettuare l' op");
        }catch(Exception e){
            assertTrue(true);
            }
    }

    @Test
    public void testGetAccountListOk() {
        ArrayList<Account> result;
        try {
            result = instance.getAccountList();
            assertNotNull(result);
        } catch (Exception ex) {
            fail("non sono riuscito a fare l' op");
        }  
    }

    @Test
    public void testGetPhdStudents() {
        ArrayList<PhdStudent> result;
        try {
            result = instance.getPhdStudents();
            assertNotNull(result);
        } catch (SQLException ex) {
            fail("non sono riuscito a fare l' op");
        }   
    }

    @Test
    public void testGetProfessors() {
        ArrayList<Professor> result;
        try {
            result = instance.getProfessors();
            assertNotNull(result);
        } catch (SQLException ex) {
            fail("non sono riuscito a fare l' op");
        } 
    }

    @Test
    public void testSearchUserOk() {
        String search = "s";
        try{
            instance.searchUser(search);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscoto a fare l' op");
        }
    }

    @Test
    public void testUpdateProfileOk() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setAdmin(true);
        w.setPassword("provaaaa12");
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("okemaildiprova@hormail.it");
        w.setDepartment("infoProvaw3w");
        w.setLink("www.prova.it");
        w.setTelephone("08245789534");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testUpdateProfileKeyNull() {
        String key=null;
        PhdStudent w=new PhdStudent();
        w.setAdmin(true);
        w.setPassword("ciaoooooooo3");
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245789533");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileKeyMax() {
        String key="qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        PhdStudent w=new PhdStudent();
        w.setAdmin(true);
        w.setPassword("weewew3rwerwre");
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245733895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileKeyFormatError() {
        String key="qwertyuiopqweqwertyuiopqwertyuiop";
        PhdStudent w=new PhdStudent();
        w.setAdmin(true);
        w.setPassword("sdwewewerwe3");
        w.setSurname("cognomeProva");
        w.setName("NomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245733895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileNameMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("qwerasdfre4");
        w.setSurname("cognomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245783395");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileNameMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("qwertyuiopqweqweqwertyuiopqweqweqwertyuiopqweqwe");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("qwertrewq3");
        w.setSurname("cognomeProva");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245783395");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileSurnameMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("qwertrewq3");
        w.setSurname("");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457893335");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileSurnameMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("qwertyuiop5");
        w.setSurname("qwertyuiopoiuytrewqwertyuiopoiuyt");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245007895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfilePasswordMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setEmail(key);
        w.setAdmin(true);
        w.setPassword("wewe");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245007895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfilePasswordMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqwqwqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245007895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfilePasswordFormatError() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setEmail(key);
        w.setAdmin(true);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245007895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
 
    @Test
    public void testUpdateProfileSecEmailMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileSecEmailMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("qwertyuiopoiuytrewqwertyuiopoiuytrewqwertyuiopoiuytrrewwqqqqwrtyuuytr");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileSecEmailFormatError() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw20");
        w.setSurname("freddo");
        w.setSecondaryEmail("wertyuiopiuuytttt");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateProfiletelephoneMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw1");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082955");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfiletelephoneMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw2");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("08245789599898955");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfiletelephoneFormatError() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.prova.it");
        w.setTelephone("082457895ssd");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileLinkMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw4");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProva");
        w.setLink("www.vaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyui"
                + "opvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopvaqwertyuiopv"
                + "aqwertyuiopvaqwertyuiopvaqwertyuiop.it");
        w.setTelephone("082457895333");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileDepartmentMin() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw2");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("");
        w.setLink("www.provaa.it");
        w.setTelephone("08245447895");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileDepartmentMax() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw2");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaqinfoProvaqinfoProvaqinfoProvaqinfoProvaqinfoProvaq");
        w.setLink("www.provaa.it");
        w.setTelephone("08245789544");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProfileDepartmentFormatError() {
        String key="dinucci@unisa.it";
        PhdStudent w=new PhdStudent();
        w.setName("prova");
        w.setAdmin(true);
        w.setEmail(key);
        w.setPassword("wewqqwqwqw3");
        w.setSurname("freddo");
        w.setSecondaryEmail("emaildiprova@hormail.it");
        w.setDepartment("infoProvaqinfoPro23infoProvaqinfoProvaq");
        w.setLink("www.provaa.it");
        w.setTelephone("08245789335");
        w.setResearchInterest("Ricerca prova");
        try{
            instance.updateProfile(key, w);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeOk() {
        String newType="phdstudent";
        String em="test@hotmail.it";
        try{
            instance.changeType(em, newType);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testChangeTypeNewTypeNull() {
        String newType=null;
       String em="dracula@hotmail.it";
        try{
            instance.changeType(em, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeNewTypeNotDominio() {
        String newType="dsdd";
        String em="dracula@hotmail.it";
        try{
            instance.changeType(em, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeEmailNull() {
        String newType="phdstudent";
        try{
            instance.changeType(null, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeEmailMax() {
        String newType="phdstudent";
        String em="wertwertwerwertwertwerwertwertwerwertwertwerwertwertwerwertwertwer";
        try{
            instance.changeType(em, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeEmailFormatError() {
        String newType="phdstudent";
        String em="wertwertwertwerwertwertwer";
        try{
            instance.changeType(em, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testChangeTypeEmailNotexists() {
        String newType="phdstudent";
        String em="wertwertwe@twerwertwertwer";
        try{
            instance.changeType(em, newType);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInviteUserOk() {
        String email = "tommaso@hotmail.it";
        try{
            instance.inviteUser(email);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testInviteUserNull() {
        String email = null;
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }

    @Test
    public void testInviteUserMax() {
        String email = "qwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopqqwertyuiopq";
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }

    @Test
    public void testInviteUserFormatError() {
        String email = "qwertyuiopqqwertyuioppqqwertyuiopq";
        try{
            instance.inviteUser(email);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);  
        }
    }

    @Test
    public void testInsertStudentTutorOk() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testInsertStudentTutorFkStudentNull() {
        String fkPhdstudent = null;
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkStudentMax() {
        String fkPhdstudent = "dqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkStudentFormatError() {
        String fkPhdstudent = "dqwertyui@iopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkStudentNotExists() {
        String fkPhdstudent = "dsd@sdsdsdsp";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkTutorNull() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = null;
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkTutorMax() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkTutorMin() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    public void testInsertStudentTutorFkTutorFormatError() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dotmail.itdinuccihotmail.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInsertStudentTutorFkTutorNotExists() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "di@hotmaill.it";
        try{
            instance.insertStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetTutorOk() {
        String idStudent = "ballo@hotmail.it";
        try{
            Professor result = instance.getTutor(idStudent);
            assertNotNull(result);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testGetTutorStudentNull() {
        String idStudent = null;
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetTutorStudentMax() {
        String idStudent = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
        try{
            Professor result = instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetTutorStudentFormatError() {
        String idStudent = "qwpqwertyuopqwertyuiop";
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetTutorStudentNotExists() {
        String idStudent = "quiopqwertyuio@pqwertyuiop";
        try{
            instance.getTutor(idStudent);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorOk() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }

    @Test
    public void testUpdateStudentTutorFkStudentNull() {
        String fkPhdstudent = null;
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkStudentMax() {
        String fkPhdstudent = "dqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkStudentFormatError() {
        String fkPhdstudent = "dqwertyui@iopdqwertyuiop";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkStudentNotExists() {
        String fkPhdstudent = "dsd@sdsdsdsp";
        String Tutor = "adelucia@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkTutorNull() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = null;
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkTutorMax() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.itdinucci@hotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkTutorMin() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dinail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkTutorFormatError() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "dotmail.itdinuccihotmail.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateStudentTutorFkTutorNotExists() {
        String fkPhdstudent = "dinucci@hotmail.it";
        String Tutor = "di@hotmaill.it";
        try{
            instance.updateStudentTutor(fkPhdstudent, Tutor);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteStudentTutorOk() {
        String ins="ballo@hotmail.it";
        try{
            instance.deleteStudentTutor(ins);
            assertTrue(true);
        }catch(Exception e){
            fail("non sono riuscito a fare l' op");
        }
    }
    
    @Test
    public void testDeleteStudentTutorStudentNull() {
        String stud= null;
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testDeleteStudentTutorStudentMax() {
        String stud= "qwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopoqwertyuiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteStudentTutorStudentFormatError() {
        String stud= "qpoqwertyuiopoqwertyuiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
    
    @Test
    public void testDeleteStudentTutorStudentùNotExists() {
        String stud= "qpoqwertyuiopoqwerty@uiopo";
        try{
            instance.deleteStudentTutor(stud);
            fail("sono riuscito a fare l' op");
        }catch(Exception e){
            assertTrue(true);
        }
    }
}