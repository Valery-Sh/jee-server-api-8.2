/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
public class IniPropertiesTest {
    
    public IniPropertiesTest() {
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
     * Test of section method, of class IniProperties.
     */
    @Test
    public void testSection_0args() {
        System.out.println("section");
        IniProperties instance = new IniProperties();
        IniProperties.Section expResult = null;
        IniProperties.Section result = instance.section();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of section method, of class IniProperties.
     */
    @Test
    public void testSection_String() {
        System.out.println("section");
        String sectionName = "";
        IniProperties instance = new IniProperties();
        IniProperties.Section expResult = null;
        IniProperties.Section result = instance.section(sectionName);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of iniFile method, of class IniProperties.
     */
    @Test
    public void testIniFile() throws Exception {
        System.out.println("iniFile");
        IniProperties instance = new IniProperties();
        IniProperties.IniFile expResult = null;
        IniProperties.IniFile result = instance.iniFile();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of sections method, of class IniProperties.
     */
    @Test
    public void testSections() {
        System.out.println("sections");
        IniProperties instance = new IniProperties();
        IniProperties.Section[] expResult = null;
        IniProperties.Section[] result = instance.sections();
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class IniProperties.
     */
    @Test
    public void testLoad_InputStream() throws Exception {
        System.out.println("load");
        InputStream stream = Files.newInputStream(Paths.get("d:/0temp/iniProps/start.ini"));
        IniProperties instance = new IniProperties();
        instance.load(stream);
        String[] s  = instance.section().get("--module");
        
        //instance.section().setProperty("--module", "new-module01");
        instance.section().put("--module", s);
//        Files.createFile(Paths.get("d:/0temp/iniProps/start-copy.ini"));
        OutputStream ostream = Files.newOutputStream(Paths.get("d:/0temp/iniProps/start-copy.ini"));
        instance.store(ostream);
        
    }

    /**
     * Test of load method, of class IniProperties.
     */
    @Test
    public void testLoad_String() throws Exception {
        System.out.println("load");
        String str = "";
        IniProperties instance = new IniProperties();
        instance.load(str);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of store method, of class IniProperties.
     */
    @Test
    public void testStore_OutputStream() throws Exception {
        System.out.println("store");
        OutputStream stream = null;
        IniProperties instance = new IniProperties();
//        instance.store(stream);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of store method, of class IniProperties.
     */
    @Test
    public void testStore_0args() throws Exception {
        System.out.println("store");
        IniProperties instance = new IniProperties();
        String expResult = "";
//        String result = instance.store();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of headerComments method, of class IniProperties.
     */
    @Test
    public void testHeaderComments() {
        System.out.println("headerComments");
        IniProperties instance = new IniProperties();
        List<String> expResult = null;
//        List<String> result = instance.headerComments();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeaderComments method, of class IniProperties.
     */
    @Test
    public void testSetHeaderComments() {
        System.out.println("setHeaderComments");
        String[] comments = null;
//        IniProperties instance = new IniProperties();
//        instance.setHeaderComments(comments);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of bottomComments method, of class IniProperties.
     */
    @Test
    public void testBottomComments() {
        System.out.println("bottomComments");
        IniProperties instance = new IniProperties();
        List<String> expResult = null;
        List<String> result = instance.bottomComments();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setBottomComments method, of class IniProperties.
     */
    @Test
    public void testSetBottomComments() {
        System.out.println("setBottomComments");
        String[] comments = null;
        IniProperties instance = new IniProperties();
        instance.setBottomComments(comments);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of decodeArray method, of class IniProperties.
     */
    @Test
    public void testDecodeArray() {
        System.out.println("decodeArray");
        String value = "";
        String[] expResult = null;
        String[] result = IniProperties.decodeArray(value);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of joinValues method, of class IniProperties.
     */
    @Test
    public void testJoinValues() {
        System.out.println("joinValues");
        String[] values = null;
        String expResult = "";
        String result = IniProperties.joinValues(values);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of encodeArray method, of class IniProperties.
     */
    @Test
    public void testEncodeArray() {
        System.out.println("encodeArray");
        String[] values = null;
        String expResult = "";
        String result = IniProperties.encodeArray(values);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of splitValue method, of class IniProperties.
     */
    @Test
    public void testSplitValue() {
        System.out.println("splitValue");
        String value = "";
        String[] expResult = null;
        String[] result = IniProperties.splitValue(value);
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isEncodedArray method, of class IniProperties.
     */
    @Test
    public void testIsEncodedArray() {
        System.out.println("isEncodedArray");
        String value = "";
        boolean expResult = false;
        boolean result = IniProperties.isEncodedArray(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
