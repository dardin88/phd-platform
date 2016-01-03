/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Curriculum;

import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.NameException;
import java.io.IOException;
import java.sql.SQLException;
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
public class CurriculumManagerTest {

    private CurriculumManager instance;
    private Curriculum cur;

    public CurriculumManagerTest() {
    }

    @Before
    public void setUp() {
        instance = CurriculumManager.getInstance();
        cur = new Curriculum();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testgetInstance() {
        CurriculumManager result = CurriculumManager.getInstance();
        assertNotNull(result);

    }

    /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInsertok() {
        cur.setName("Curriculmtest");
        cur.setDescription("il Curriculum è di prova");
        try {
            instance.insert(cur);
            assertTrue(true);
        } catch (Exception x) {
            fail("sono riuscito a fare l' op");
        }
    }

    /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInsertEmptyName() {
        cur.setName("");
        cur.setDescription("il Curriculum è di prova");
        try {
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }

    }

    /**
     * Test of insert method, of class CurriculumManager.
     */
    public void testInsertMaxName() {
        cur.setName("ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum");
        cur.setDescription("il Curriculum è di prova");
        try {
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of insert method, of class CurriculumManager.
     */
    @Test
    public void testInsertPresent() {
        cur.setName("Marketing e Comunicazione");
        cur.setDescription("il Curriculum è di prova");
        try {
            instance.insert(cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateok() {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("curriculum prova update2");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            assertTrue(true);
        } catch (Exception x) {
            fail("non sono riuscito a fare l' op");
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateoldnotexist() {
        String oldNameCurriculum = "ciao";
        cur.setName("curriculum prova update2");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateoldnull() {
        String oldNameCurriculum = "";
        cur.setName("curriculum prova update3");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateoldmax() {
        String oldNameCurriculum = "ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum";
        cur.setName("curriculum prova update");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateEmptyName() {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of update method, of class CurriculumManager.
     */
    @Test
    public void testUpdateNamemax() {
        String oldNameCurriculum = "Marketing e Comunicazione";
        cur.setName("ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum");
        cur.setDescription("descrizione prova update");
        try {
            instance.update(oldNameCurriculum, cur);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of delete method, of class CurriculumManager.
     */
    @Test
    public void testDeleteEmptyName() {
        String CurriculumName = null;
        try {
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of delete method, of class CurriculumManager.
     */
    @Test
    public void testDeleteNameMax() {
        String CurriculumName = "ciaosonomario,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum";
        try {
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }

    }

    /**
     * Test of delete method, of class CurriculumManager.
     */
    @Test
    public void testDeletenotExist() {
        String CurriculumName = "ciaooooooooo";
        try {
            instance.delete(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of delete method, of class CurriculumManager.
     */
    @Test
    public void testDeleteok() {
        String CurriculumName = "Curriculmtest";
        try {
            instance.delete(CurriculumName);
            assertTrue(true);
        } catch (Exception x) {
            fail("sono riuscito a fare l' op");
        }
    }

    /**
     * Test of getCurriculumList method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumList() {
        ArrayList<Curriculum> result;
        try {
            result = instance.getCurriculumList();
            assertTrue(true);
        } catch (Exception ex) {
            fail("sono riuscito a fare l' op");
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumByNameEmptyName() {
        String CurriculumName = "";
        try {
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumByNameMaxName() {
        String CurriculumName = "ciaosofdffrio,ho sbagliato ad inserire il nome del curriculum"
                + "ciaosonomario,ho sbagliato ad inserire il nome del curriculum";
        try {
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumByNameNamenotExist() {
        String CurriculumName = "ciaooooooooo";
        try {
            instance.getCurriculumByName(CurriculumName);
            fail("sono riuscito a fare l' op");
        } catch (Exception x) {
            assertTrue(true);
        }
    }

    /**
     * Test of getCurriculumByName method, of class CurriculumManager.
     */
    @Test
    public void testGetCurriculumByNameok() {
        String CurriculumName = "Marketing e Comunicazione";
        try {
            instance.getCurriculumByName(CurriculumName);
            assertTrue(true);
        } catch (Exception x) {
            fail("non sono riuscito a fare l' op");
        }

    }
}
