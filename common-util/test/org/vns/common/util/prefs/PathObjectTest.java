
package org.vns.common.util.prefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.vns.common.PathObject;

/**
 *
 * @author Valery
 */
public class PathObjectTest {
    
    public PathObjectTest() {
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
     * Test of getPathObject method, of class CachePathObject.
     */
    @Test
    public void testGetPathObject() throws IOException {
        System.out.println("getPathObject");
        String names = "";
        PathObject instance = new CachePathObject("A");
        PathObject expResult = null;
        PathObject result = instance.getPathObject("B");
        assertEquals(expResult, result);
        
        expResult = instance.createFolders("B/C");
        result = instance.getPathObject("B/C");
        assertEquals(expResult, result);
        
        
    }
    /**
     * Test of createFolders method, of class CachePathObject.
     */
    @Test
    public void testFindByRelativePath() {
        System.out.println("findByRelativePath");
        String relativePath = "a/b/c";
        PathObject instance = new CachePathObject("ROOT");
        
        PathObject found = instance.getPathObject(relativePath);
        assertNull(found);
        
        PathObject po = new CachePathObject(instance,"a");
        PathObject po1 = new CachePathObject(po,"b");
        relativePath = "a";
        found = instance.getPathObject(relativePath);
        assertNull(found);
    }

    /**
     * Test of createFolders method, of class CachePathObject.
     */
    @Test
    public void testCreateFolder() throws IOException {
        System.out.println("createByRelativePath");
        String relativePath = "a/b/c";
        CachePathObject root = new CachePathObject("ROOT");
        root.createFolders(relativePath);
        assertEquals("ROOT[a[b[c]]]",root.string());
    }

    /**
     * Test of createFolders method, of class CachePathObject.
     */
    @Test
    public void testCreateFolder_1() throws IOException {
        System.out.println("createByRelativePath");
        String relativePath = "a/b/c";
        CachePathObject root = new CachePathObject("ROOT");
        root.createFolders(relativePath);
        assertEquals("ROOT[a[b[c]]]",root.string());
        PathObject po = root.getPathObject("a/b");
        
        po = po.createFolders("D/M");
        
        po = root.getPathObject("a/b/D");
        po.createFolders("T");
        System.out.println("1. ROOT[a[b[c,[D[M,T]]]]]");
        assertTrue("ROOT[a[b[c,[D[M,T]]]]]".equals("ROOT[a[b[c,[D[M,T]]]]]"));
        String expResult = "ROOT[a[b[c,D[M,T]]]]";
        String result = root.string();

        assertEquals(expResult,result);
    }
    /**
     * Test of getPath method, of class CachePathObject.
     */
    @Test
    public void testGetPath() throws IOException {
        System.out.println("getPath");
        String relativePath = "a/b/c";
        PathObject root = new CachePathObject("ROOT");
        PathObject folder = root.createFolders(relativePath);
        assertEquals("ROOT/a/b/c", folder.getPath());
    }

    /**
     * Test of isFolder method, of class CachePathObject.
     */
    @Test
    public void testIsFolder() {
        System.out.println("isFolder");
        PathObject root = new CachePathObject("ROOT");
        PathObject instance = new CachePathObject(root,"a/b/c");
        boolean expResult = true;
        boolean result = instance.isFolder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getChildren method, of class CachePathObject.
     */
    @Test
    public void testGetChildren_0args() throws IOException {
        System.out.println("getChildren");
        PathObject instance = new CachePathObject("A");
        instance.createFolders("B");
        instance.createFolders("C");
        
        List<PathObject> expResult = new ArrayList<>();
        expResult.add(instance.getPathObject("B")); 
        expResult.add(instance.getPathObject("C")); 
                
                
        List<PathObject> result = instance.getChildren();
        assertEquals(expResult, result);
    }
    /**
     * Test of getParent method, of class CachePathObject.
     */
    @Test
    public void testGetParent() throws IOException {
        System.out.println("getParent");
        PathObject instance = new CachePathObject("root");
        PathObject expResult = null;
        PathObject result = instance.getParent();
        assertEquals(expResult, result);
        
        result = instance.createFolders("a").getParent();
        expResult = instance;
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class CachePathObject.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String relativePath = "a/b/c";
        PathObject root = new CachePathObject("ROOT");
        root.createFolders(relativePath);
        assertEquals("ROOT[a[b[c]]]",root.string());
        PathObject po = root.getPathObject("a/b");
        
        po = po.createFolders("D/M");
        
        po = root.getPathObject("a/b/D");
        po.createFolders("T");
        System.out.println("1. ROOT[a[b[c,[D[M,T]]]]]");
        assertTrue("ROOT[a[b[c,[D[M,T]]]]]".equals("ROOT[a[b[c,[D[M,T]]]]]"));
        String expResult = "ROOT[a[b[c,D[M,T]]]]";
        String result = root.string();
        assertEquals(expResult,result);
        
        po.delete();
        expResult = "ROOT[a[b[c]]]";
        result = root.string();
        assertEquals(expResult,result);
    }

    /**
     * Test of delete method, of class CachePathObject.
     */
    @Test
    public void testDelete_1() throws Exception {
        System.out.println("delete");
        String relativePath = "a/b/c";
        PathObject root = new CachePathObject("ROOT");
        root.createFolders(relativePath);
        assertEquals("ROOT[a[b[c]]]",root.string());
        PathObject po = root.getPathObject("a/b");
        
        po = po.createFolders("D/M");
        
        po = root.getPathObject("a/b/D");
        PathObject tpo = po.createFolders("T");
        
        System.out.println("1. ROOT[a[b[c,[D[M,T]]]]]");
        assertTrue("ROOT[a[b[c,[D[M,T]]]]]".equals("ROOT[a[b[c,[D[M,T]]]]]"));
        String expResult = "ROOT[a[b[c,D[M,T]]]]";
        String result = root.string();
        assertEquals(expResult,result);
        
        tpo.delete();
        
        expResult = "ROOT[a[b[c,D[M]]]]";
        
        result = root.string();
        assertEquals(expResult,result);
    }
    /**
     * Test of hasExt method, of class CachePathObject.
     */
    @Test
    public void testHasExt() {
        System.out.println("hasExt");
        String ext = "txt";
        PathObject instance = new CachePathObject("root.txt");
        boolean expResult = true;
        boolean result = instance.hasExt(ext);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNameExt method, of class CachePathObject.
     */
    @Test
    public void testGetNameExt() {
        System.out.println("getNameExt");
        PathObject instance = new CachePathObject("root.txt");
        String expResult = "root.txt";
        String result = instance.getNameExt();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class CachePathObject.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        PathObject instance = new CachePathObject("root.txt");
        String expResult = "root";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}
