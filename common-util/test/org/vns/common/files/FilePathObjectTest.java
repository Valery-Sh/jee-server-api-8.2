/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.files;

import org.vns.common.files.FilePathObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.vns.common.PathObject;

/**
 *
 * @author Valery
 */
public class FilePathObjectTest {
    
    public FilePathObjectTest() {
    }
    
    @Rule
    public TemporaryFolder TempFOLDER = new TemporaryFolder(); 
    
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
     * Test of create method, of class FilePathObject.
     * File does not exist
     */
    @Test(expected=IllegalArgumentException.class )
    public void testCreate() throws IOException {
        System.out.println("create");
        
        String relativePath = "test01";
        String ROOT_FOLDER = this.TempFOLDER.getRoot() + "/" + "TEST_ROOT";
        FilePathObject instance = new FilePathObject(ROOT_FOLDER);
        File TEST_FILE = TempFOLDER.newFolder("TEST_ROOT");
        PathObject parent = new FilePathObject(TEST_FILE.getPath());
        PathObject expResult = new FilePathObject(ROOT_FOLDER + "/test01");
        
        PathObject result = instance.create(parent, relativePath);
        //assertEquals(expResult, result);
    }
    /**
     * Test of create method, of class FilePathObject.
     * File exists
     */
    @Test
    public void testCreate_1() throws IOException {
        System.out.println("create");
        
        String relativePath = "test01";

        
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        File TEST_FOLDER = TempFOLDER.newFolder("TEST_ROOT","test01");        
        PathObject parent = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        
        PathObject expResult = new FilePathObject(TEST_FOLDER.getPath());
        
        PathObject result = instance.create(parent, relativePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of isFolder method, of class FilePathObject.
     */
    @Test
    public void testIsFolder() throws IOException {
        System.out.println("isFolder");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        File TEST_FOLDER = TempFOLDER.newFolder("TEST_ROOT","test01");         
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        boolean expResult = true;
        boolean result = instance.isFolder();
        assertEquals(expResult, result);
    }
    /**
     * Test of isFolder method, of class FilePathObject.
     */
    @Test
    public void testIsFolder_1() throws IOException {
        System.out.println("isFolder");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath() + "/testFile01");
        boolean result = instance.isFolder();
        assertFalse(result);
    }
    /**
     * Test of childrenFolderNames method, of class FilePathObject.
     */
    @Test
    public void testChildrenFileNames() throws IOException {
        System.out.println("childrenFileNames");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        List<String> expResult = new ArrayList<>();
        expResult.add( instance.create(instance, "a").getName());
                
        List<String> result = instance.childrenFolderNames(TEST_ROOT_FOLDER.toPath());
        
        assertEquals(expResult, result);
    }
    /**
     * Test of childrenFolderNames method, of class FilePathObject.
     */
    @Test
    public void testChildrenFileNames_1() throws IOException {
        System.out.println("childrenFileNames");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        PathObject po = root.create(root, "a/b");
        
        List<String> expResult = new ArrayList<>();
        expResult.add( instance.create(po, "c").getName());
        expResult.add( instance.create(po, "d").getName());
        
        List<String> result = instance.childrenFolderNames(forPath);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getFolders method, of class FilePathObject.
     */
    @Test
    public void testGetFolders() throws IOException {
        System.out.println("getFolders");
        boolean rec = false;
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        PathObject po = root.create(root, "a/b");
        
        List<PathObject> expResult = new ArrayList<>();
        expResult.add( instance.create(po, "c"));
        expResult.add( instance.create(po, "d"));
        
        List<PathObject> result = instance.getFolders(false);
        
        assertEquals(expResult, result);
    }
    /**
     * Test of getFolders(true)  method, of class FilePathObject.
     * Find recursively 
     */
    @Test
    public void testGetFolders_1() throws IOException {
        System.out.println("getFolders");
        boolean rec = true;
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        // We create the file tho check where it will not  be inclufed ti result
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        
        Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        PathObject po = root.create(root, "a/b");
        
        List<PathObject> expResult = new ArrayList<>();
        expResult.add( instance.create(root, "a"));
        expResult.add( instance.create(root, "a/b"));
        expResult.add( instance.create(root, "a/b/c"));
        expResult.add( instance.create(root, "a/b/d"));
        
        List<PathObject> result = root.getFolders(rec);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getData method, of class FilePathObject.
     */
    @Test
    public void testGetData() throws IOException {
        System.out.println("getData");
        boolean rec = true;
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        // We create the file tho check where it will not  be inclufed ti result
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        Path file02 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/d/testFile02"));
        
        //Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        PathObject po = root.create(root, "a/b");
        
        List<PathObject> expResult =  new ArrayList<>();
        expResult.add( instance.create(root, "a/b/testFile01"));
        expResult.add( instance.create(root, "a/b/d/testFile02"));
        
        List<PathObject> result = root.getData(rec);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathObject method, of class FilePathObject.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPathObject() throws IOException {
        System.out.println("getPathObject");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        String path = TEST_ROOT_FOLDER.getPath() + "a/b";
        //
        // The file with the specified path doesn't exist
        //
        PathObject instance = new FilePathObject(path);
        PathObject result = instance.getPathObject("c","d");
    }
    /**
     * Test of getPathObject method, of class FilePathObject.
     */
    @Test
    public void testGetPathObject_1() throws IOException {
        System.out.println("getPathObject");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        String path = TEST_ROOT_FOLDER.getPath() + "/a";
        Files.createDirectories(Paths.get(path,"b"));
        //
        // The file with the specified path  exists
        //
        PathObject instance = new FilePathObject(path);
        PathObject expResult = new FilePathObject(path + "/b");
        
        PathObject result = instance.getPathObject("b");
        assertEquals(expResult, result);
        
        result = instance.getPathObject("M");
        assertNull(result);
    }

    /**
     * Test of getChildren method, of class FilePathObject.
     */
    @Test
    public void testGetChildren_0args() throws IOException {
        System.out.println("getChildren");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        String filePath = TEST_ROOT_FOLDER.getPath() + "/a.txt";
        Files.createFile(Paths.get(filePath));
        
        PathObject instance = new FilePathObject(filePath);
        assertTrue(instance.getChildren().isEmpty());
    }
    /**
     * Test of getChildren method, of class FilePathObject.
     */
    @Test
    public void testGetChildren_0args_1() throws IOException {
        System.out.println("getChildren");
        //
        //  The children should be listed recursively, first all 
        //  direct children are listed; then children of direct 
        // subfolders; and so on.
        //
        boolean rec = true;
        
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        // We create the file tho check where it will not  be inclufed ti result
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        Path file02 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/d/testFile02"));
        
        //Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        PathObject po = root.create(root, "a/b");
        
        List<PathObject> expResult =  new ArrayList<>();
        expResult.add( instance.create(root, "a"));
        expResult.add( instance.create(root, "a/b"));
        expResult.add( instance.create(root, "a/b/c"));
        expResult.add( instance.create(root, "a/b/d"));
        expResult.add( instance.create(root, "a/b/testFile01"));
        expResult.add( instance.create(root, "a/b/d/testFile02"));
        
        List<PathObject> result = root.getChildren(rec);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getChildren method, of class FilePathObject.
     */
    @Test
    public void testGetChildren_0args_2() throws IOException {
        System.out.println("getChildren");
        //
        //  Only direct sub folders and files should be listed
        //
        boolean rec = false;
        
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        // We create the file tho check where it will not  be inclufed ti result
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        Path file02 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/d/testFile02"));
        
        //Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        FilePathObject root = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        FilePathObject instance = new FilePathObject(root,"a/b");
        
        List<PathObject> expResult =  new ArrayList<>();
        expResult.add( instance.create(root, "a/b/c"));
        expResult.add( instance.create(root, "a/b/d"));
        expResult.add( instance.create(root, "a/b/testFile01"));
        
        List<PathObject> result = instance.getChildren(rec);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getParent method, of class FilePathObject.
     */
    @Test
    public void testGetParent() throws IOException {
        System.out.println("getParent");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        String abPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b").toString();
        
        Path dirs01 = Files.createDirectories(path01);
        
        PathObject instance = new FilePathObject(path01.toString());
        PathObject expResult = new FilePathObject(abPath);;
        PathObject result = instance.getParent();
        assertEquals(expResult, result);
    }



    /**
     * Test of getPath method, of class FilePathObject.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        FilePathObject instance = new FilePathObject("a/b/c");

        String expResult = "a/b/c";
        String result = instance.getPath();
    }

    /**
     * Test of delete method, of class FilePathObject.
     */
    @Test
    public void testDelete() throws IOException {
        System.out.println("delete");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        Path file02 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/d/testFile02"));
        
        //Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        
        PathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        PathObject toDelete = instance.getPathObject("a/b");
        toDelete.delete();
        
        
        List<PathObject> expResult =  new ArrayList<>();
        expResult.add( new FilePathObject(instance,"a"));
/*        expResult.add( instance.create(root, "a/b"));
        expResult.add( instance.create(root, "a/b/c"));
        expResult.add( instance.create(root, "a/b/d"));
        expResult.add( instance.create(root, "a/b/testFile01"));
        expResult.add( instance.create(root, "a/b/d/testFile02"));
*/        
        List<PathObject> result = instance.getChildren(true);
        
        assertEquals(expResult, result);

    }
    /**
     * Test of delete method, of class FilePathObject.
     */
    @Test
    public void testDelete_1() throws IOException {
        System.out.println("delete");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        Path path01 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/c");
        Path path02 = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b/d");
        
        Path dirs01 = Files.createDirectories(path01);
        Path dirs02 = Files.createDirectories(path02);
        //
        //
        Path file01 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/testFile01"));
        Path file02 = Files.createFile(Paths.get(TEST_ROOT_FOLDER.getPath(),"a/b/d/testFile02"));
        
        //Path forPath = Paths.get(TEST_ROOT_FOLDER.getPath(), "a/b");
        
        
        PathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        PathObject toDelete = instance.getPathObject("a/b/d");
        toDelete.delete();
        
        
        List<PathObject> expResult =  new ArrayList<>();
        
        expResult.add( new FilePathObject(instance,"a"));
        expResult.add( new FilePathObject(instance,"a/b"));
        expResult.add( new FilePathObject(instance,"a/b/c"));
        expResult.add( new FilePathObject(instance,"a/b/testFile01"));

        List<PathObject> result = instance.getChildren(true);
        
        assertEquals(expResult, result);

    }
    /**
     * Test of createDataFile method, of class FilePathObject.
     * @throws java.io.IOException
     */
    @Test(expected = IOException.class)
    public void testCreateData_String() throws IOException {
        System.out.println("createData");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        String name = null;
        PathObject result = instance.createData(name);
    }
    /**
     * Test of createDataFile method, of class FilePathObject.
     * @throws java.io.IOException
     */
    @Test(expected = IOException.class)
    public void testCreateData_String_1() throws IOException {
        System.out.println("createData");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        //
        // Slashes are prohibited
        //
        String name = "A/testFile.txt";
        PathObject result = instance.createData(name);
    }
    /**
     * Test of createDataFile method, of class FilePathObject.
     */
    @Test(expected = IOException.class)
    public void testCreateData_String_2() throws IOException {
        System.out.println("createData");
        
        File TEST_ROOT_FOLDER = TempFOLDER.newFile("test01.txt");
        //
        // The file object cannot be a file foor this operation
        //
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        String name = "testFile.txt";
        PathObject result = instance.createData(name);
    }

    /**
     * Test of createDataFile method, of class FilePathObject.
     */
    @Test
    public void testCreateData_String_3() throws IOException {
        System.out.println("createData");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        String name = "testFile01.txt";
        
        PathObject expResult = new FilePathObject(instance,name);
        PathObject result = instance.createData(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of createFolders method, of class FilePathObject.
     */
    @Test
    public void testCreateFolders() throws IOException {
        System.out.println("createFolder");
        File TEST_ROOT_FOLDER = TempFOLDER.newFolder("TEST_ROOT");
        FilePathObject instance = new FilePathObject(TEST_ROOT_FOLDER.getPath());
        
        String relativePath = "A/B";
        
        PathObject expResult = new FilePathObject(TEST_ROOT_FOLDER.getPath() + "/" + relativePath);
        PathObject result = instance.createFolders(relativePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInputStream method, of class FilePathObject.
     */
    @Test
    public void testGetInputStream() throws Exception {
        System.out.println("getInputStream");
        FilePathObject instance = null;
        InputStream expResult = null;
//        InputStream result = instance.getInputStream();
//        assertEquals(expResult, result);
    }

    /**
     * Test of getOutputStream method, of class FilePathObject.
     */
    @Test
    public void testGetOutputStream() throws Exception {
        System.out.println("getOutputStream");
        FilePathObject instance = null;
        OutputStream expResult = null;
//        OutputStream result = instance.getOutputStream();
//        assertEquals(expResult, result);
    }
    
}
