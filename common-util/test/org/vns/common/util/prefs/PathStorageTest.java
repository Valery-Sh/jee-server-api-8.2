/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs;

import java.util.prefs.Preferences;
import javax.swing.event.ChangeListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.vns.common.util.prefs.files.PropertiesExt;

/**
 *
 * @author Valery
 */
public class PathStorageTest {
    
    public PathStorageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of instance method, of class PathStorage.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        
        PathStorage result = PathStorage.instance(absolutePath);
        assertNotNull(result);
        
        
    }

    /**
     * Test of existsNode method, of class PathStorage.
     */
    @Test
    public void testExistsNode() {
        System.out.println("existsNode");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        assertTrue(instance.existsNode());
        instance.removeNode();
        assertFalse(instance.existsNode());
    }

    /**
     * Test of childrenNames method, of class PathStorage.
     */
    @Test
    public void testChildrenNames() {
        System.out.println("childrenNames");
        
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        
        PathStorage instance = PathStorage.instance("/A");
        String[] expResult = new String[]{"B"};
        String[] result = instance.childrenNames();
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of removeNode method, of class PathStorage.
     */
    @Test
    public void testRemoveNode() {
        System.out.println("removeNode");
        
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        instance.removeNode();
        //String result = instance.preferencesRoot().string();
        //String expResult = "Preferences";
    }

    /**
     * Test of load method, of class PathStorage.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        PropertiesExt result = instance.load();
        assertNotNull(result);
    }

    /**
     * Test of load method, of class PathStorage.
     */
    @Test
    public void testLoad_1() throws Exception {
        System.out.println("load");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        PropertiesExt props = new PropertiesExt();
        props.setProperty("test-key", "test-value");
        instance.save(props);
        PropertiesExt result = instance.load();
        assertNotNull(result);
        assertEquals("test-value",result.getProperty("test-key"));
    }
    
    /**
     * Test of save method, of class PathStorage.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        PropertiesExt props = new PropertiesExt();
        props.setProperty("test-key", "test-value");
        instance.save(props);
        PropertiesExt result = instance.load();
        assertEquals("test-value",result.getProperty("test-key"));
    }

    /**
     * Test of getProperties method, of class PathStorage.
     */
    @Test
    public void testGetProperties() throws Exception {
        System.out.println("getProperties");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        String absolutePath = "/A";
        PathStorage instance = PathStorage.instance(absolutePath);
        assertNull(instance.getProperties());
        
        PropertiesExt props = new PropertiesExt();
        props.setProperty("test-key", "test-value");
        instance.save(props);
        
        PropertiesExt result = instance.load();
        assertNotNull(result);
        assertEquals("test-value",result.getProperty("test-key"));
    }

    /**
     * Test of createProperties method, of class PathStorage.
     */
/*    @Test
    public void testCreateProperties() {
        System.out.println("createProperties");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        
        PathStorage instance = PathStorage.instance("/A");
        
        Properties expResult = new Properties();
        Properties result = instance.createProperties();
        
        assertEquals(expResult, result);
    }
*/
    /**
     * Test of createProperties method, of class PathStorage.
     */
/*    @Test
    public void testCreateProperties_1(){
        System.out.println("createProperties");
        PathPreferences user = (PathPreferences) PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        
        PathStorage instance = PathStorage.instance("/A");

        Properties result = instance.createProperties();
        Properties expResult = instance.getProperties();
        
        assertEquals(expResult, result);
    }
  */  

    /**
     * Test of attachChangeListener method, of class PathStorage.
     */
    @Test
    public void testAttachChangeListener() {
        System.out.println("attachChangeListener");
        ChangeListener changeListener = null;
        PathStorage instance = null;
    }
    
}
