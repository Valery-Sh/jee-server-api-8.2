/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Valery
 */
public class IniSectionTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder(); 
    
    public IniSectionTest() {
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
     * Test of name method, of class IniSection.
     */
    @Test
    public void testName() {
        System.out.println("name");
        IniSection instance = null;
        String expResult = "";
        String result = instance.name();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of node method, of class IniSection.
     */
    @Test
    public void testNode() {
        System.out.println("node");
        IniSection instance = null;
        FilePreferences expResult = null;
        FilePreferences result = instance.node();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of owner method, of class IniSection.
     */
    @Test
    public void testOwner() {
        System.out.println("owner");
        IniSection instance = null;
        IniPreferences expResult = null;
        IniPreferences result = instance.owner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nodePath method, of class IniSection.
     */
    @Test
    public void testNodePath() {
        System.out.println("nodePath");
        IniSection instance = null;
        String expResult = "";
        String result = instance.nodePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comments method, of class IniSection.
     */
    @Test
    public void testComments_0() throws IOException {
        System.out.println("comments");
        String path = "a/b";
        
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        IniSection sec = result.section("section01");
        sec.setProperty("sec01-key01", "sec01-val01");
        sec.setProperty("sec01-key02", "sec01-val02");
        IniSection defsec = result.section();
        defsec.setProperty("def01-key01", "def01-val01");
        sec.setProperty("sec01-key03", "sec01-val03");
        defsec.setProperty("def01-key02", "def01-val02");
        defsec.setProperty("def01-key03", "def01-val03");
        sec.setProperty("sec01-key04", "sec01-val04");
        defsec.setProperty("def01-key04", "def01-val04");
        sec.setProperty("sec01-key05", "sec01-val05");

/*        String[] resultCom = sec.headerComments();
        String[] defcom = defsec.comments();
        String[] seccom = sec.comments();
        String[] bottomcom = sec.bottomComments();
        
        String[] headerExp = new String[] {"# header1","# header2","","# def01-key01"};
        
        assertArrayEquals(headerExp, resultCom);
*/        
        
    }
    
    /**
     * Test of comments method, of class IniSection.
     */
    @Test
    public void testComments() throws IOException {
        System.out.println("comments");
        String path = "a/c";
        
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        IniSection sec = result.section("section01");
        
        sec.setComments(new String[]{
            "",
            "# sec_01_1",
            "#"
        });
        
        //sec.setProperty("sec01-key01", "sec01-val01");
        //sec.setProperty("sec01-key02", "sec01-val02");
        
/*        IniSection defsec = result.section();

        defsec.setComments(new String[]{
            "",
            "# default_sec",
            ""
        });
        
        defsec.setProperty("def01-key01", "def01-val01");
        
        
        
        sec.setProperty("sec01-key03", "sec01-val03");
        defsec.setProperty("def01-key02", "def01-val02");
        defsec.setProperty("def01-key03", "def01-val03");
        sec.setProperty("sec01-key04", "sec01-val04");
        defsec.setProperty("def01-key04", "def01-val04");
        sec.setProperty("sec01-key05", "sec01-val05");

        String[] defcom = defsec.comments();
        String[] seccom = sec.comments();
        
        String[] expResult = new String[]{
            "",
            "# sec_01",
            ""
        };
        
        assertArrayEquals(expResult, seccom);
        
        expResult = new String[]{
            "",
            "# default_sec",
            ""
        };        

        assertArrayEquals(expResult, defcom);

        
        expResult = new String[] {"# header1","# header2","","# def01-key01"};
        sec.setHeaderComments(expResult);
        String[] resultCom = sec.headerComments();        
        
        assertArrayEquals(expResult, resultCom);

        defsec.setHeaderComments(expResult);
        resultCom = defsec.headerComments();        
        assertArrayEquals(expResult, resultCom);

        expResult = new String[] {"# bottom1","# bottom2","","# def01-key01"};
        sec.setBottomComments(expResult);
        resultCom = sec.bottomComments();
        assertArrayEquals(expResult, resultCom);

        defsec.setBottomComments(expResult);
        resultCom = defsec.bottomComments();
        assertArrayEquals(expResult, resultCom);
  */      
        
    }

    /**
     * Test of setComments method, of class IniSection.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        String[] comments = null;
        IniSection instance = null;
        instance.setComments(comments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propertyComments method, of class IniSection.
     */
    @Test
    public void testPropertyComments() {
        System.out.println("propertyComments");
        String key = "";
        String value = "";
        IniSection instance = null;
        String[] expResult = null;
        ///String[] result = instance.propertyComments(key, value);
        //assertArrayEquals(expResult, result);
    }

    /**
     * Test of setPropertyComments method, of class IniSection.
     */
    @Test
    public void testSetPropertyComments() {
        System.out.println("setPropertyComments");
        String key = "";
        String value = "";
        String[] comments = null;
        IniSection instance = null;
        //instance.setPropertyComments(key, value, comments);
    }

    /**
     * Test of put method, of class IniSection.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        String key = "";
        String value = "";
        IniSection instance = null;
        instance.setProperty(key, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class IniSection.
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String key = "";
        IniSection instance = null;
        String expResult = "";
        String result = instance.getProperty(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class IniSection.
     */
    @Test
    public void testGet_String_String() {
        System.out.println("get");
        String key = "";
        String def = "";
        IniSection instance = null;
        String expResult = "";
        String result = instance.get(key, def);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProperty method, of class IniSection.
     */
    @Test
    public void testSetProperty() {
        System.out.println("setProperty");
        String key = "";
        String[] values = null;
        IniSection instance = null;
        String expResult = "";
        String result = instance.setProperty(key, values);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of join method, of class IniSection.
     */
    @Test
    public void testJoin() {
        System.out.println("join");
        String key = "";
        String def = "";
        IniSection instance = null;
        String expResult = "";
        String result = instance.join(key, def);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class IniSection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        IniSection instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class IniSection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        IniSection instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
