/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.vns.common.util.prefs.files.BaseProperties.CommentHelper;

/**
 *
 * @author Valery
 */
public class BasePropertiesTest {
    
    public BasePropertiesTest() {
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
     * Test of section method, of class BaseProperties.
     */
    @Test
    public void testSection_0args() {
        System.out.println("section");
        BaseProperties instance = new BaseProperties();
        BaseProperties.Section expResult = null;
        BaseProperties.Section result = instance.section();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of section method, of class BaseProperties.
     */
    @Test
    public void testSection_String() {
        System.out.println("section");
        String sectionName = "";
        BaseProperties instance = new BaseProperties();
        BaseProperties.Section expResult = null;
        BaseProperties.Section result = instance.section(sectionName);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of iniFile method, of class BaseProperties.
     */
    @Test
    public void testIniFile() {
        System.out.println("iniFile");
        BaseProperties instance = new BaseProperties();
        BaseProperties.IniFile expResult = null;
        BaseProperties.IniFile result = instance.iniFile();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setIniFile method, of class BaseProperties.
     */
    @Test
    public void testSetIniFile() {
        System.out.println("setIniFile");
        BaseProperties.IniFile iniFile = null;
        BaseProperties instance = new BaseProperties();
//        instance.setIniFile(iniFile);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of sections method, of class BaseProperties.
     */
    @Test
    public void testSections() {
        System.out.println("sections");
        BaseProperties instance = new BaseProperties();
        BaseProperties.Section[] expResult = null;
        BaseProperties.Section[] result = instance.sections();
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class BaseProperties.
     */
    @Test
    public void testLoad_InputStream() throws Exception {
        System.out.println("load");
        InputStream stream = null;
//        BaseProperties instance = new BaseProperties();
//        instance.load(stream);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class BaseProperties.
     */
    @Test
    public void testLoad_String() throws Exception {
        System.out.println("load");
        String str = "";
//        BaseProperties instance = new BaseProperties();
//        instance.load(str);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of store method, of class BaseProperties.
     */
    @Test
    public void testStore_OutputStream() throws Exception {
        System.out.println("store");
        OutputStream stream = null;
        BaseProperties instance = new BaseProperties();
//        instance.store(stream);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of store method, of class BaseProperties.
     */
    @Test
    public void testStore_0args() throws Exception {
        System.out.println("store");
        BaseProperties instance = new BaseProperties();
        String expResult = "";
        String result = instance.store();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of headerComments method, of class BaseProperties.
     */
    @Test
    public void testHeaderComments() {
        System.out.println("headerComments");
        BaseProperties instance = new BaseProperties();
        List<String> expResult = null;
//        List<String> result = instance.headerComments();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeaderComments method, of class BaseProperties.
     */
    @Test
    public void testSetHeaderComments() {
        System.out.println("setHeaderComments");
        String[] comments = null;
        BaseProperties instance = new BaseProperties();
//        instance.setHeaderComments(comments);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of bottomComments method, of class BaseProperties.
     */
    @Test
    public void testBottomComments() {
        System.out.println("bottomComments");
        BaseProperties instance = new BaseProperties();
        List<String> expResult = null;
        List<String> result = instance.bottomComments();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setBottomComments method, of class BaseProperties.
     */
    @Test
    public void testSetBottomComments() {
        System.out.println("setBottomComments");
        String[] comments = null;
        BaseProperties instance = new BaseProperties();
//        instance.setBottomComments(comments);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of decodeArray method, of class BaseProperties.
     */
    @Test
    public void testDecodeArray() {
        System.out.println("decodeArray");
        String value = "";
        String[] expResult = null;
        String[] result = BaseProperties.decodeArray(value);
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of joinValues method, of class BaseProperties.
     */
    @Test
    public void testJoinValues() {
        System.out.println("joinValues");
        String[] values = null;
        String expResult = "";
        String result = BaseProperties.joinValues(values);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of encodeArray method, of class BaseProperties.
     */
    @Test
    public void testEncodeArray() {
        System.out.println("encodeArray");
        String[] values = null;
        String expResult = "";
        String result = BaseProperties.encodeArray(values);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of splitValue method, of class BaseProperties.
     */
    @Test
    public void testSplitValue() {
        System.out.println("splitValue");
        String value = "";
        String[] expResult = null;
//        String[] result = BaseProperties.splitValue(value);
//        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isEncodedArray method, of class BaseProperties.
     */
    @Test
    public void testIsEncodedArray() {
        System.out.println("isEncodedArray");
        String value = "";
        boolean expResult = false;
        boolean result = BaseProperties.isEncodedArray(value);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    //
    // CommentHelper Internal Class
    //
    
    /**
     * Test of isEncodedArray method, of class BaseProperties.
     * One fragment
     */
    @Test
    public void testComments_Split() {
        System.out.println("split(List)");
        List<String> source = new ArrayList<>();
        //
        // Empty list
        //
        List<String[]> result = CommentHelper.split(source);
        
        List<String[]> expResult = new ArrayList<String[]>();
        assertEquals(expResult, result);
        //
        // One blank string
        //
        source.add(" ");
        result = CommentHelper.split(source);
        expResult.add(new String[] {" "});
        int l1 = result.get(0)[0].length();
        int l2 = expResult.get(0)[0].length();
        assertListEquals(expResult, result);
        //
        // Two blank strings
        //
        source.add(" ");
        result = CommentHelper.split(source);
        expResult.clear();
        expResult.add(new String[] {" ", " "});
        
        assertListEquals(expResult, result);
        source.add("# ");
        result = CommentHelper.split(source);
        expResult.clear();
        expResult.add(new String[] {" ", " ","# "});
        
        assertListEquals(expResult, result);
        
    }
    /**
     * Test of isEncodedArray method, of class BaseProperties.
     * More than one fragment
     */
    @Test
    public void testComments_Split_1() {
        System.out.println("split(List)");
        List<String> source = new ArrayList<>();
        //
        // Empty list
        //
        List<String[]> result = CommentHelper.split(source);
        
        List<String[]> expResult = new ArrayList<String[]>();
        assertEquals(expResult, result);
        //
        // 
        //
        source.add(" ");
        source.add("# ");
        source.add(" ");
        
        result = CommentHelper.split(source);
        expResult.add(new String[] {" ", "# "});
        expResult.add(new String[] {" "});
        
        assertListEquals(expResult, result);
        source.add("# ");
        source.add("# ");        
        expResult.add(new String[] {" ","# ","# "});
        result = CommentHelper.split(source);
    }

    /**
     * Test of split(List,int) method, of class BaseProperties.
     * More than one fragment
     */
    @Test
    public void testComments_Split_2() {
        System.out.println("split(List, int)");
        
        List<String> source = new ArrayList<>();
        //
        // Empty list
        //
        List<String[]> result = CommentHelper.split(source, 5);
        
        List<String[]> expResult = new ArrayList<String[]>();
        assertEquals(expResult, result);
        //
        // 
        //
        source.add("# 1");
        source.add("# 2");
        source.add("");
        source.add("#key1=value1");
        source.add("# 3");
        source.add("# 4");
        
        result = CommentHelper.split(source,3);
        
        expResult.add(new String[] {"# 1", "# 2",""});
        expResult.add(new String[] {"# 3","# 4"});
        
        assertListEquals(expResult, result);
    }

    /**
     * Test of split(List,int) method, of class BaseProperties.
     * More than one fragment
     */
    @Test
    public void testComments_Split_3() {
        System.out.println("split(List, int)");
        
        List<String> source = new ArrayList<>();
        //
        // Empty list
        //
        List<String[]> result = CommentHelper.split(source, 5);
        
        List<String[]> expResult = new ArrayList<String[]>();
        assertEquals(expResult, result);
        //
        // 
        //
        source.add("#key1=value1");
        source.add("# 1");
        source.add("# 2");
        source.add("");
        source.add("# 3");
        source.add("# 4");
        
        result = CommentHelper.split(source,0);
        expResult.add(new String[0]);
        expResult.add(new String[] {"# 1", "# 2","","# 3","# 4"});
        
        assertListEquals(expResult, result);
    }    
    
    /**
     * Test of split(List,int) method, of class BaseProperties.
     */
    @Test
    public void testComments_Split_4() {
        System.out.println("split(List, int)");
        
        List<String> source = new ArrayList<>();
        //
        // Empty list
        //
        List<String[]> result = CommentHelper.split(source, 5);
        
        List<String[]> expResult = new ArrayList<String[]>();
        assertEquals(expResult, result);
        //
        // 
        //
        source.add("# 1");
        source.add("# 2");
        source.add("");
        source.add("# 3");
        source.add("# 4");
        source.add("#key1=value1");
        
        result = CommentHelper.split(source,5);
        expResult.add(new String[] {"# 1", "# 2","","# 3","# 4"});
        expResult.add(new String[0]);
        
        
        assertListEquals(expResult, result);
    }    
    
    public void assertListEquals(List<String[]> resList, List<String[]> expList) {
        if ( resList.size() != expList.size()) {
            throw new junit.framework.AssertionFailedError("Size not equals");
        }
        for ( int i=0; i < resList.size(); i++) {
            assertArrayEquals(resList.get(i), expList.get(i));
        }
    }
}
