/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.vns.common.util.prefs.files.BaseProperties.CommentHelper;
import org.vns.common.util.prefs.files.BaseProperties.Section;

/**
 *
 * @author Valery
 */
public class IniPreferencesTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder(); 
    
    public IniPreferencesTest() {
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
     * Test of name method, of class IniPreferences.
     */
    @Test
    public void testName() throws IOException {
        System.out.println("node");
        tempFolder.newFolder("a","b");
        File rootFile = tempFolder.getRoot();
        //
        // Has an empty name
        //
        IniPreferences instance = new IniPreferences(rootFile.getAbsolutePath());
        assertEquals("", instance.name());
        //
        // The preferencesNode to be create has not empty name
        //
        IniPreferences result  = instance.node("a/b");
        assertEquals("b",result.name());
        //
        // Create an IniSection and tests the name of it's owner 
        //
        IniSection sec  = result.section("sec01");
        assertEquals("sec01",sec.owner().name());
    }
    
    /**
     * Test of preferencesNode method, of class IniPreferences.
     */
    @Test
    public void testNode_1() throws IOException {
        System.out.println("node");
        tempFolder.newFolder("a","b");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getAbsolutePath());
        FilePreferences result = instance.preferencesNode();
        assertNotNull(result);
    }
    /**
     * Test of preferencesNode method, of class IniPreferences.
     */
    @Test
    public void testNode_2() throws IOException {
        System.out.println("node");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath());
        IniPreferences result = instance.node(path);
        assertNotNull(result);
        assertEquals(Paths.get(instance.userRoot()), Paths.get(result.userRoot()));
        assertEquals(Paths.get(result.nodePath()),Paths.get(path));
    }
    /**
     * Test of preferencesNode method, of class IniPreferences.
     */
    @Test
    public void testNode_3() throws IOException {
        System.out.println("node");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath());
        IniPreferences result = instance.node(path);
        
        String s = Paths.get(result.preferencesNode().absolutePath()).normalize().toString().replace("\\", "/");        
        
        assertNotNull(result.parent());
        assertEquals("a",result.parent().nodePath());
        assertNotNull(result.parent());
        result.parent().children();
        assertNotNull(result.parent().child("b"));
        assertEquals("",result.parent().parent().nodePath());
        
        
        
        
        
    }    
    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testTEMP_TEMP() throws IOException {
        //Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
        Pattern p1 = Pattern.compile("[\\#]{1}[\\s]{1}[a-zA-Z\\-_][a-zA-Z\\-_0-9.]*");
        Matcher m = p1.matcher("# --1module");
        boolean r = m.matches();
        List<String> l = new ArrayList<>();
        //l.get(-1);
        Set<Integer> set = new HashSet<>();
        set.add(5);
        set.add(3);
        set.add(4);
        set.add(0);
        set.add(2);
        set.add(1);
        set.stream().sorted().forEach(i -> {
            System.out.println("i=" + i);
        });
        
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add("s1");
        l1.add("s2");
        l2.add("s1");
        l2.add("s2");
        assertTrue(Objects.equals(l1, l2));
        String[] a1 = new String[]{"s1","s2"};
        String[] a2 = new String[]{"s1","s2"};
        assertTrue(Objects.deepEquals(a1,a2));

        Iterator<Path> it = Paths.get("a/b").iterator();
        Path p;
        while (it.hasNext()) {
            p = it.next();
            int i = 0;
        }        
        
        String start = PropertiesExt.START;
        String end = PropertiesExt.END;
        
        String s1 = start + "[1]" + end;
        String[] r1 = PropertiesExt.decodeArray(s1);
        int i = 0;
        
        s1 = start + "[1][2]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;       
        
        s1 = start + "[1][2][3]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;        

        
        s1 = start + "[]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
        s1 = start + "[][1]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
        s1 = start + "[][1][2]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
        s1 = start + "[][1][2][3]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
        s1 = start + "[][]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  

        s1 = start + "[][][]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  

        s1 = start + "[][][1][]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
        s1 = start + "[][][1][][2][]" + end;
        r1 = PropertiesExt.decodeArray(s1);
        i = 0;  
        
    }    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testNode_4() throws IOException {
        System.out.println("node");
        String path = "a/b";
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        //IniSection dsec = result.section();
        //String s = dsec.getProperty("--module");
        
        //sec.removeValues("ss");
        //sec.removeValues("--module", "my-module");
        //sec.comment("--module", "my-module");
        //sec.uncomment("--module", "my-module");
        //String[] a = new String[] {"my-module1111111111111","my-module1",  "mm2","mm3"};
        //sec.setProperty("--module1", "my-module1111111111111");
        //sec.setProperty("--module1", PropertiesExt.encodeArray(a));
        //sec.comment("--module1","my-module1");
        //sec.setPropertyComments("--module", 0, "# 1", "# 2");
        //List<String> dcom = CommentHelper.list(dsec.comments());
        //dcom.add("# 111");
        //sec.setComments(dcom.toArray(new String[0]));
        
        //sec.uncomment("--module1","my-module1");        
        //dsec.uncomment("--module");
        //sec.uncomment("--module");
        //sec.uncomment("--module2","my-module2");        
        //sec.uncomment("--module1");
        
        IniSection defsec = result.section();
        defsec.getProperty("a");
        
        IniSection sec = result.section("section01");
        sec.setProperty("sec01-key01", "sec01-val01");
        sec.setProperty("sec01-key02", "sec01-val02");
        sec.setProperty("sec01-key01", "sec01-val01111111111111");
        sec.addProperty("sec01-key01", "sec01-val0122222222222");
        
        //IniSection defsec = result.section();
        defsec.setProperty("def01-key01", "def01-val01");
        
        defsec.setProperty("def01-key03", "sec01-val03");
        defsec.setPropertyComments("def01-key03", 0, new String[]{"defsec.setProperty(\"def01-key03\", \"def01-val03\");"});
        defsec.setProperty("def01-key02", "def01-val02");
        defsec.setProperty("def01-key03", "def01-val03");
        defsec.setProperty("def01-key04", "sec01-val04");
        defsec.setProperty("def01-key04", "def01-val04");
        defsec.setProperty("def01-key05", "sec01-val05");
        
        sec.setComments("111","2222","3333");
        

    }
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testNode_5() throws IOException {
        System.out.println("node");
        String path = "a/b";
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        IniSection sec = result.section();
        sec.addProperties("--module", "my-new-module");
        sec.get("--module");
        //sec.addProperties("--module", "my-new-module-01");
        
        //sec.removeValues("--module", "my-new-module");
    }
    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testNode_8() throws IOException {
        System.out.println("node");
        String path = "a/b";
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        //result.onSaveValue(IniPreferences.SaveValueOption.UNIQUE_KEY_VALUE);
        IniSection sec = result.section();
        //sec.onSaveValue(UNIQUE_KEY);
        
        sec.addProperties("--module", "my-new-module");
        //sec.addProperties("--module", "my-new-module-01");
        sec.addProperties("http_port", "8080");
        
    }
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testNode_9() throws IOException {
        System.out.println("node");
        String path = "a/b";
        IniPreferences instance = new IniPreferences("d:/0temp/iniprefs");
        IniPreferences result = instance.node(path);
        //result.onSaveValue(IniPreferences.SaveValueOption.UNIQUE_KEY_VALUE);
        IniSection sec = result.section();
        sec.get("--module");
        //sec.onSaveValue(UNIQUE_KEY);
        
        sec.addProperties("--module", "my-new-module");
        //sec.addProperties("--module", "my-new-module-01");
        sec.addProperties("http_port", "8080");
        
/*        dsec.modifyValue("--module", po -> {
            List<String> list = po.list();
            list.add("https");
            po.removeDublicateValues();
            
        });
*/        
    }
    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testSection() throws IOException {
        System.out.println("node");
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
        defsec.setProperty("def01-key04", "def01-val04-double");        
        sec.setProperty("sec01-key05", "sec01-val05");
        
        List<Section> list = result.listSections();
        assertEquals(2,list.size());
        assertTrue(list.get(0).name().equals("[]"));
        assertTrue(list.get(1).name().equals("section01"));        
        
        String[] defValues = new String[] {"def01-val01","def01-val02","def01-val03","def01-val04-double"};
        assertEquals("def01-val01",defsec.get("def01-key01"));
        assertEquals("def01-val02",defsec.get("def01-key02"));        
        assertEquals("def01-val03",defsec.get("def01-key03"));        
        assertEquals("def01-val04-double",defsec.get("def01-key04"));        

        assertEquals("sec01-val01",sec.get("sec01-key01"));
        assertEquals("sec01-val02",sec.get("sec01-key02"));        
        assertEquals("sec01-val03",sec.get("sec01-key03"));        
        assertEquals("sec01-val04",sec.get("sec01-key04"));        
        assertEquals("sec01-val05",sec.get("sec01-key05")); 

    }
    
    
    /**
     * Test of nodePath method, of class IniPreferences.
     */
    @Test
    public void testNodePath() throws IOException {
        System.out.println("nodePath");
        String path = "";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        IniPreferences instance = new IniPreferences(rootFile.getPath());
        assertEquals(Paths.get(instance.nodePath()),Paths.get(path));
    }
    /**
     * Test of nodePath method, of class IniPreferences.
     */
    @Test
    public void testNodePath_1() throws IOException {
        System.out.println("nodePath");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node("a/b");;
        assertEquals(Paths.get(instance.nodePath()),Paths.get(path));
    }
    /**
     * Test of section(String) method, of class IniPreferences.
     */
    @Test
    public void testSection_String() throws IOException {
        System.out.println("section(String)");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node("a/b");
        IniSection result = instance.section("sec01");
        assertNotNull(result);
        //
        // Method createSection() returns a Preferebces object of the instance of IniPreferences 
        //
        assertEquals("sec01",result.node().sectionName());
        //
        assertEquals("a/b/sec01",result.nodePath());
        
    }
    /**
     * Test of section() method, of class IniPreferences.
     * Tests the result of building of the default section
     */
    @Test
    public void testSection_1() throws IOException {
        System.out.println("section()");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node("a/b");
        IniSection result = instance.section();
        assertNotNull(result);
        
        assertEquals("[]",result.node().sectionName());
        assertEquals("a/b/[]",result.nodePath());
        
    }
    /**
     * Test of iniSection() method, of class IniPreferences.
     * Tests the result of building of the default section
     */
    @Test
    public void testIniSection() throws IOException {
        System.out.println("iniSection()");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node("a/b");
        //
        // No section created
        //
        assertNull(instance.iniSection());
        
        IniSection result = instance.section();
        //
        // Now the instance has not null IniSection reference
        //        
        IniPreferences sectionOwner = instance.node("a/b/[]");
        assertNotNull(result.owner().iniSection());
        assertEquals("a/b/[]",result.owner().nodePath());
        
        assertEquals("[]",result.node().sectionName());
        assertEquals("a/b/[]",result.nodePath());
    }

    /**
     * Test of children() method, of class IniPreferences.
     * Tests the result of building of the default section
     */
    @Test
    public void testChildren() throws IOException {
        System.out.println("section()");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node(path);
        IniSection section = instance.section();
        
        IniPreferences[] result = instance.children();
        assertEquals(1, result.length);

        assertEquals("[]",result[0].section.name());
        
    }
    /**
     * Test of children() method, of class IniPreferences.
     * Tests the result of building of two sections
     */
    @Test
    public void testChildren_1() throws IOException {
        System.out.println("children()");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node(path);
        IniSection section = instance.section();
        IniSection section01 = instance.section("sec01");
        
        IniPreferences[] result = instance.children();
        assertEquals(2, result.length);

        assertEquals("[]",result[0].section.name());
        assertEquals("sec01",result[1].section.name());
    }
    
    /**
     * Test of children() method, of class IniPreferences.
     * Tests the result of building of two sections
     */
    @Test
    public void testChild() throws IOException {
        System.out.println("child(String)");
        String path = "a/b";
        tempFolder.newFolder("A");
        File rootFile = tempFolder.getRoot();
        
        IniPreferences instance = new IniPreferences(rootFile.getPath()).node("");
        instance = new IniPreferences(rootFile.getPath()).node(path);
        
        instance.node("a");
        
        IniSection section = instance.section();
        IniSection section01 = instance.section("sec01");
        
        IniPreferences result = instance.child("[]" );
        assertNotNull(result);
        assertEquals(path + "/[]", result.nodePath());
        assertEquals(section,result.iniSection());
        assertEquals(instance,result.parent());
        
        result = instance.child("sec01" );
        assertNotNull(result);
        assertEquals(path + "/sec01", result.nodePath());
        assertEquals(section01,result.iniSection());
        
        assertEquals(instance,result.parent());
    }
    
}
