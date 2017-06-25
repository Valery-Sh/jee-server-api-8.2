/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs;

import java.nio.file.Paths;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.event.ChangeEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Valery
 */
public class PathPreferencesTest {
    
    public PathPreferencesTest() {
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
    public void tearDown() throws BackingStoreException {
        PathPreferences instance = PathPreferences.pathUserRoot();
        String[] names = instance.childrenNames();
        for ( String name : names) {
            instance.node(name).removeNode();
        }
    }
    @Test
    public void testPathIEMP_TEMP() {
        Paths.get("");
        int i = 0;
    }
    /**
     * Test of pathUserRoot method, of class PathPreferences.
     */
    @Test
    public void testPathUserRoot() {
        System.out.println("pathUserRoot");
        
        Preferences user = PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        
        String result = p01.get("key", null);
        assertNull(result);
    }
    /**
     * Test of childrenNamesSpi method, of class PathPreferences.
     */
    @Test
    public void testChildrenNamesSpi() throws Exception {
        System.out.println("childrenNamesSpi");
        PathPreferences instance = PathPreferences.pathUserRoot();
        instance.node("a/b");
        String[] expResult = new String[]{"a"};
        String[] result = instance.childrenNamesSpi();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of keysSpi method, of class PathPreferences.
     */
    @Test
    public void testKeysSpi() throws Exception {
        System.out.println("keysSpi");
        PathPreferences instance = PathPreferences.pathUserRoot();
        instance = (PathPreferences) instance.node("a/b");
        instance.put("test-key", "test-value");
        
        String[] expResult = new String[] {"test-key"};
        String[] result = instance.keysSpi();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getSpi method, of class PathPreferences.
     */
    @Test
    public void testGetSpi() {
        System.out.println("getSpi");
        String key = "";
        Preferences user = PathPreferences.pathUserRoot();
        Preferences p01 = user.node("A");
        Preferences p02 = p01.node("B");
        
        PathPreferences instance = (PathPreferences) p02;
        String expResult = null;
        String result = instance.getSpi(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of putSpi method, of class PathPreferences.
     */
    @Test
    public void testPutSpi() throws BackingStoreException {
        System.out.println("putSpi");
        
        String key = "test-key";
        String value = "test-value";
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        System.out.println("INSTANCE cnames.length=" + instance.childrenNames().length);
        for ( String nm : instance.childrenNames() ) {
            System.out.println("  --- name = " + nm);
        }
        instance = (PathPreferences) instance.node("a/b");
        instance.put(key, value);
        
        String expResult = "test-value";
        String result = instance.get(key, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSpi method, of class PathPreferences.
     */
    @Test
    public void testRemoveNodeSpi() throws BackingStoreException {
        System.out.println("removeNodeSpi");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        instance = (PathPreferences) instance.node("a/b");
        
        instance.removeNode();
        assertFalse(instance.storage().existsNode());
        
    }
    /**
     * Test of removeSpi method, of class PathPreferences.
     */
    @Test
    public void testRemoveNodeSpi_1() throws BackingStoreException {
        System.out.println("removeNodeSpi");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences root = (PathPreferences) instance.node("root");
        
        PathPreferences ab = (PathPreferences) root.node("a/b");
        PathPreferences cd = (PathPreferences) root.node("a/c");
        PathPreferences a = (PathPreferences) root.node("a");
        ab.removeNode();
        boolean b = ab.nodeExists("");

        String[] rcn1 = root.childrenNames();
        String[] acn1 = a.childrenNames();        
        
        assertTrue(root.nodeExists("a"));
        assertTrue(root.nodeExists("a/c"));
        assertFalse(a.nodeExists("b"));
        
        assertFalse(root.nodeExists("a/b"));
    }
    
    /**
     * Test of removeSpi method, of class PathPreferences.
     */
    @Test
    public void testRemoveSpi() throws BackingStoreException {
        System.out.println("removeNodeSpi");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences root = (PathPreferences) instance.node("root");
        root.put("key-test", "value-test");
        assertEquals("value-test", root.get("key-test"));
        
        root.remove("key-test");
        
        assertEquals(null, root.get("key-test"));

        
        
        
        
    }
    

    /**
     * Test of flushSpi method, of class PathPreferences.
     */
    @Test
    public void testFlushSpi() throws Exception {
        System.out.println("flushSpi");
        PathPreferences instance = null;
        //instance.flushSpi();
    }

    /**
     * Test of syncSpi method, of class PathPreferences.
     */
    @Test
    public void testSyncSpi() throws Exception {
        System.out.println("syncSpi");
        PathPreferences instance = null;
//        instance.syncSpi();
    }

    /**
     * Test of stateChanged method, of class PathPreferences.
     */
    @Test
    public void testStateChanged() {
        System.out.println("stateChanged");
        ChangeEvent e = null;
        PathPreferences instance = null;
//        instance.stateChanged(e);
    }
    /**
     * Test of setProperty(String, String...) method, of class PathPreferences.
     * An array with one element
     */    
    @Test
    public void testSetProperty_Array() {
        System.out.println("setProperty");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        node.setProperty("k01", "a");
        String[] expResult = new String[]{"a"};
        String[] result = node.getAsArray("k01");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of setProperty(String, String...) method, of class PathPreferences.
     * An array with two elements
     */    
    @Test
    public void testSetProperty_Array_1() {
        System.out.println("setProperty");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        node.setProperty("k01", "a","b");
        String[] expResult = new String[]{"a","b"};
        String[] result = node.getAsArray("k01");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of setProperty(String, String...) method, of class PathPreferences.
     * An empty array.
     */    
    @Test
    public void testSetProperty_Array_2() {
        System.out.println("setProperty");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        node.setProperty("k01");
        //
        //Must return an array with a single element with an empty string
        //
        String[] expResult = new String[]{""};
        String[] result = node.getAsArray("k01");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of join(String, String) method, of class PathPreferences.
     * Usual case.
     */    
    @Test
    public void testJoin() {
        System.out.println("join");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        
        node.put("k01", "value01");
        //
        //Must return an array with a single element with an empty string
        //
        String expResult = "value01";
        String result = node.join("k01", "???");
        assertEquals(expResult, result);
    }
    /**
     * Test of get(String, String) method, of class PathPreferences.
     * When setProperty as array with a single element.
     */    
    @Test
    public void testJoin_1() {
        System.out.println("join");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        
        node.setProperty("k01", "value01");
        //
        //Must return an array with a single element
        //
        String expResult = "value01";
        String result = node.join("k01", "???");
        assertEquals(expResult, result);
    }
    /**
     * Test of get(String, String) method, of class PathPreferences.
     * When setProperty as array with two elements.
     */    
    @Test
    public void testJoin_2() {
        System.out.println("join");
        PathPreferences instance = PathPreferences.pathUserRoot();
        PathPreferences node = (PathPreferences) instance.node("A");
        
        node.setProperty("k01", "value01","value02");
        //
        //Must return an array with a single element
        //
        String expResult = "value01value02";
        String result = node.join("k01", "???");
        assertEquals(expResult, result);
    }
    /**
     * Test of addAll(String[], String...) method, of class PathPreferences.
     * An adding parameter is an array of a single element.
     */    
    @Test
    public void testAddTo() {
        System.out.println("addTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array with a single element
        //
        String[] expResult = new String[] {"value01","value02","value03"};
        String[] result = Storage.addAll(array,"value03");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of addAll(String[], String...) method, of class PathPreferences.
     * An adding parameter is an array of two elements.
     */    
    @Test
    public void testAddTo_1() {
        System.out.println("addTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array with a single element
        //
        String[] expResult = new String[] {"value01","value02","value03","value04"};
        String[] result = Storage.addAll(array,"value03","value04");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of addAll(String[], String...) method, of class PathPreferences.
     * An adding parameter is an empty array.
     */    
    @Test
    public void testAddTo_2() {
        System.out.println("addTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array with a single element
        //
        String[] result = Storage.addAll(array);
        assertTrue(array == result);
    }

    /**
     * Test of insertTo(String[], int, String...) method, of class PathPreferences.
     * An inserting parameter is an array of a single element.
     */    
    @Test
    public void testInsertTo() {
        System.out.println("inserTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array where first element is the inserted element
        //
        String[] expResult = new String[] {"value03","value01","value02"};
        String[] result = Storage.insertTo(array,0, "value03");
        assertArrayEquals(expResult, result);
        //
        // Must return an array where tht second element is the inserted element
        //
        expResult = new String[] {"value01","value03","value02"};
        result = Storage.insertTo(array,1, "value03");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of insertTo(String[], int, String...) method, of class PathPreferences.
     * An inserting parameter is an array of two elements.
     */    
    @Test
    public void testInsertTo_1() {
        System.out.println("inserTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array where first element is the inserted element
        //
        String[] expResult = new String[] {"value03","value04","value01","value02"};
        String[] result = Storage.insertTo(array,0, "value03","value04");
        assertArrayEquals(expResult, result);
        //
        // Must return an array where the second element is the inserted element
        //
        expResult = new String[] {"value01","value03","value04","value02"};
        result = Storage.insertTo(array,1, "value03","value04");
        assertArrayEquals(expResult, result);
    }
    /**
     * Test of insertTo(String[], int, String...) method, of class PathPreferences.
     * An inserting parameter is an empty array.
     */    
    @Test
    public void testInsertTo_2() {
        System.out.println("inserTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return the same array
        //
        String[] result = Storage.insertTo(array,0);
        assertTrue(array == result);
    }
    /**
     * Test of insertTo(String[], int, String...) method, of class PathPreferences.
     * An index to insert is equals to source array length
     */    
    @Test
    public void testInsertTo_3() {
        System.out.println("inserTo");
        String[] array = new String[] {"value01","value02"};
        //
        // Must return an array with aa added element
        //
        String[] result = Storage.insertTo(array,2,"value03");
        String[] expResult = new String[] {"value01","value02","value03"};
        assertArrayEquals(expResult,result);
    }

    /**
     * Test of addToArray(String, String...) method, of class PathPreferences.
     * 
     */    
    @Test
    public void testAdd() {
        System.out.println("add");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        String sep = System.lineSeparator();
        PathPreferences node = (PathPreferences) instance.node("Node01");
        //
        // Add a single value if the property for the key="k1" does not exist 
        // The metod get("k1") must return a simple value "v1"
        //
        node.addToArray("k1", "v1");
        assertEquals("v1", node.get("k1"));
        //
        // Now addToArray another value with the same key 
        // The metod get("k1") must return a string representation of the array
        //
        node.addToArray("k1", "v2");
        assertEquals("\\" + sep + "v1\\" + sep + "v2", node.get("k1"));
    }
    /**
     * Test of addToArray(String, String...) method, of class PathPreferences.
     */    
    @Test
    public void testAdd_1() {
        System.out.println("add");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        String sep = System.lineSeparator();
        PathPreferences node = (PathPreferences) instance.node("Node01");
        //
        // Add an array of values if the property for the key="k1" does not exist 
        // The method get("k1") must return a string representation of the array        //
        //
        node.addToArray("k1", "v1","v2");
        assertEquals("\\" + sep + "v1\\" + sep + "v2", node.get("k1"));        
        //
        // Now addToArray another value with the same key 
        // The metod get("k1") must return a string representation of the array
        //
        node.addToArray("k1", "v3");
        assertEquals("\\" + sep + "v1\\" + sep + "v2\\" + sep + "v3", node.get("k1"));
    }
    /**
     * Test of addToArray(String, String...) method, of class PathPreferences.
     */    
    @Test
    public void testAdd_2() {
        System.out.println("add");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        String sep = System.lineSeparator();
        PathPreferences node = (PathPreferences) instance.node("Node01");
        //
        // Add an empty array of values if the property for the key="k1" does not exist 
        // The method get("k1") must return null        //
        //
        
        node.addToArray("k1");
        assertNull(node.get("k1"));
    }
    /**
     * Test of addToArray(String, String...) method, of class PathPreferences.
     */    
    @Test
    public void testAdd_3() {
        System.out.println("add");
        
        PathPreferences instance = PathPreferences.pathUserRoot();
        String sep = System.lineSeparator();
        PathPreferences node = (PathPreferences) instance.node("Node01");
        //
        // Add a single value if the property for the key="k1" does not exist 
        // The method get("k1") must return null        //
        //
        node.addToArray("k1","v1");
        //
        // Add an empty array of values if the property for the key="k1" already exists 
        // The method get("k1") must return a value "v1" 
        //
        node.addToArray("k1");
        
        assertEquals("v1",node.get("k1"));
    }
    
    
}//class PathPrefrencesTest
