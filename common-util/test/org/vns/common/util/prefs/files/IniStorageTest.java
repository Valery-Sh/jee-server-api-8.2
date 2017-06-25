/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.IOException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.vns.common.util.prefs.PathPreferences;
import org.vns.common.util.prefs.Storage;

/**
 *
 * @author Valery
 */
public class IniStorageTest {
    
    public IniStorageTest() {
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
     * Test of instance method, of class IniStorage.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        PathPreferences owner = null;
        String absolutePath = "";
        Storage expResult = null;
        Object o = new String[] {"a1","a2"};
        String[] s = (String[]) o; 
        boolean b = PropertiesExt.isJoinValues(null);        
        String e0 = s[0];
        String e1 = s[1];
        
//        Storage result = IniStorage.instance(owner, absolutePath);
//        assertEquals(expResult, result);
    }

    /**
     * Test of instanceReadOnly method, of class IniStorage.
     */
    @Test
    public void testInstanceReadOnly() {
        System.out.println("instanceReadOnly");
        String absolutePath = "";
        Storage expResult = null;
        Storage result = IniStorage.instanceReadOnly(absolutePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniFile method, of class IniStorage.
     */
    @Test
    public void testIniFile() throws Exception {
        System.out.println("iniFile");
        IniStorage instance = null;
    }

    /**
     * Test of load method, of class IniStorage.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        IniStorage instance = null;
        Properties expResult = null;
        //Properties result = instance.load();
        //assertEquals(expResult, result);
    }

    /**
     * Test of save method, of class IniStorage.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Properties properties = null;
        IniStorage instance = null;
//        instance.save(properties);
    }

    /**
     * Test of sectionName method, of class IniStorage.
     */
    @Test
    public void testSectionName() {
        System.out.println("sectionName");
        IniStorage instance = null;
        String expResult = "";
//        String result = instance.sectionName();
//        assertEquals(expResult, result);
    }


    /**
     * Test of buildPropertiesPath method, of class IniStorage.
     */
    @Test
    public void testBuildPropertiesPath() {
        System.out.println("buildPropertiesPath");
        IniStorage instance = null;
        String expResult = "";
        String result = instance.buildPropertiesPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of Section.add(KeyValue)method, of class IniStorage.Section.
     */
    @Test
    public void testSection$add() throws IOException {
        System.out.println("buildPropertiesPath");
/*        FilePreferences prefs = (FilePreferences) FilePreferences.iniUserRoot("d:/0temp/Section");
        IniStorage instance = new IniStorage(prefs,"T",false);
        Section section = new Section("sec01");
        KeyValue keyValue = new KeyValue("k1","v1");
        section.add(keyValue);
        keyValue = new KeyValue("k1","v2");
        section.add(keyValue);        
        String result = section.keyValue("k1").value();
        String expResult = "\\" 
                + System.lineSeparator()
                + "v1\\" + System.lineSeparator()
                + "v2";
        assertEquals(expResult, result);

        keyValue = new KeyValue("k1","v3");
        section.add(keyValue);        
        result = section.keyValue("k1").value();
        expResult = "\\" 
                + System.lineSeparator()
                + "v1\\" + System.lineSeparator()
                + "v2\\" + System.lineSeparator()
                + "v3";
        assertEquals(expResult, result);
  */      
    }
    
}
