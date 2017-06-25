/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vns.common.util.prefs.files;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
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
public class PropertiesExtTest {
    
    public PropertiesExtTest() {
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
     * Test of put method, of class PropertiesExt.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = "value01";
        instance.setProperty(key, "value01");
        String result = instance.getProperty(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of putAll method, of class PropertiesExt.
     */
    @Test
    public void testPutAll() {
        System.out.println("putAll");
        PropertiesExt properties = new PropertiesExt();
        String key = "key01";
        String value = "value01";
        properties.setProperty(key, value);
        
        PropertiesExt instance = new PropertiesExt();
        
        instance.putAll(properties);
        assertEquals(1,instance.size());
        assertEquals(value, instance.get(key));
        
    }

    /**
     * Test of splitValue(String) method, of class PropertiesExt.
     * PropertiesExt instance is empty
     */
    @Test
    public void testToArray() {
        System.out.println("toArray(String)");
        String sep = "\\" + System.lineSeparator();
        String str = null;
        //
        // Must return null
        //
        assertNull(PropertiesExt.splitValue(str));
        
        //
        // The string doesn't start with  "\\" + System.lineSeparator();
        //
        str = "\\";
        String[] result = PropertiesExt.splitValue(str);
        String[] expResult = new String[] {"\\"};        
        assertArrayEquals(expResult,result);
        //
        // The string starts and ends with "\\" + System.lineSeparator();
        //
        str = sep;
        expResult = new String[] {""};
        result = PropertiesExt.splitValue(str);
        assertArrayEquals(expResult, result);  
        //
        // The string starts with "\\" + System.lineSeparator() and ends with a 
        // single one more spaces. The result is an array of length 1 and an 
        // element with a single or more spaces.
        //
        str = sep + " ";
        expResult = new String[] {" "};
        result = PropertiesExt.splitValue(str);
        assertArrayEquals(expResult, result);  
        //
        // The string starts with a double string of "\\" + System.lineSeparator()
        // (sep + sep). The result is an array of length 1 and an empty element
        //
        str = sep + System.lineSeparator();;
        expResult = new String[0];
        result = PropertiesExt.splitValue(str);
        assertArrayEquals(expResult, result);  
    }
    /**
     * Test of splitValue(String) method, of class PropertiesExt.
     * PropertiesExt instance is empty
     */
    @Test
    public void testToString() {
        System.out.println("toString(String[])");
        String sep = "\\" + System.lineSeparator();
        String[] array = null;
        //
        // Must return null
        //
        assertNull(PropertiesExt.joinValues(array));
        
        //
        // The string doesn't start with  "\\" + System.lineSeparator();
        //
        array = new String[] {"\\"};        
        String result = PropertiesExt.joinValues(array);
        String expResult = sep + "\\";        
        assertEquals(expResult,result);
        //
        // The string starts and ends with "\\" + System.lineSeparator();
        //
        array = new String[] {""};        
        expResult = sep + "";
        result = PropertiesExt.joinValues(array);
        assertEquals(expResult, result);  
        //
        // The string starts with "\\" + System.lineSeparator() and ends with a 
        // single one more spaces. The result is an array of length 1 and an 
        // element with a single or more spaces.
        //
        array = new String[] {" "};   
        expResult = sep + " " ;
        result = PropertiesExt.joinValues(array);
        assertEquals(expResult, result);  
        //
        // The string starts with a double string of "\\" + System.lineSeparator()
        // (sep + sep). The result is an array of length 1 and an empty element
        //
        
        array = new String[0];
        expResult = sep + System.lineSeparator();;
        result = PropertiesExt.joinValues(array);
        assertEquals(expResult, result);  
    }
    
    /**
     * Test of get method, of class PropertiesExt.
     * PropertiesExt instance is empty
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = null;
        String result = instance.getProperty(key);
        assertEquals(expResult, result);
    }
    /**
     * Test of get method, of class PropertiesExt.
     * PropertiesExt instance is empty
     */
    @Test
    public void testGet_1() {
        System.out.println("get");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = "value01";
        instance.setProperty(key, "value01");
        String result = instance.getProperty(key);
        assertEquals(expResult, result);
    }
    /**
     * Test of get method, of class PropertiesExt.
     * Fist put headerComments.
     */
    @Test
    public void testGet_2() {
        System.out.println("get");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = null;
        //
        // put headerComments => get must return null
        //
//        instance.put(PropertiesExt.commentKey(key), "value01");
//        String result = instance.get(key);
//        assertEquals(expResult, result);
    }
    /**
     * Test of get method, of class PropertiesExt.
     * Fist put headerComments and get headerComments.
     */
    @Test
    public void testGet_3() {
        System.out.println("get");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = PropertiesExt.joinValues(new String[] {"value01"});
        //
        // put headerComments for the given key => get must return a  string view of array
        //
       // instance.setProperty(PropertiesExt.commentKey(key), "value01");
       // String result = instance.getProperty(PropertiesExt.commentKey(key));
       // assertEquals(expResult, result);
    }
    /**
     * Test of get method, of class PropertiesExt.
     * Fist put global headerComments of the PropertiesExt object and get 
 headerComments.
     */
    @Test
    public void testGet_4() {
        System.out.println("get");
        String key = "key01";
        PropertiesExt instance = new PropertiesExt();
        String expResult = PropertiesExt.joinValues(new String[] {"value01"});
        //
        // put header headerComments => get must return header headerComments as string view of array
        //
       // instance.setProperty(PropertiesExt.HEADER_COMMENT, "value01");
        //String result = instance.get(PropertiesExt.HEADER_COMMENT);
        //assertEquals(expResult, result);
    }
    /**
     * Test of defineAndPutComments method, of class PropertiesExt.
     */
    @Test
    public void testPutComments() {
        System.out.println("putComments");
        String key = "key01";
        String[] expResult = new String[] {"v1", "v2"};
        PropertiesExt instance = new PropertiesExt();
        //instance.defineAndPutComments(PropertiesExt.commentKey(key), "v1", "v2");
        //String[] result = instance.defineAndGetComments(PropertiesExt.commentKey(key));
        //assertArrayEquals(expResult, result);
        //
        // pu headerComments for the PropertiesExt object itself
        //
        //instance.putComments("key01", "v1", "v2");
        //result = instance.getComments("key01");
        //assertArrayEquals(expResult, result);

        
    }



    /**
     * Test of load method, of class PropertiesExt.
     */
    @Test
    public void testLoad() throws IOException {
        System.out.println("load");
        StringBuilder sb = new StringBuilder();
        sb.append("key01=value01");
        ByteArrayInputStream stream = new ByteArrayInputStream(sb.toString().getBytes());
        PropertiesExt instance = new PropertiesExt();
        instance.load(stream);
        assertEquals(1,instance.size());
        assertEquals("value01",instance.get("key01"));
    }

    /**
     * Test of load method, of class PropertiesExt.
     * With headerComments
     */
    @Test
    public void testLoad_1() throws IOException {
        System.out.println("load");

        String[] comments = new String[] {"","# header1",""};
        //
        // Create the corresponding string
        //
        String sep = System.lineSeparator();
        
        String text = String.join(sep, 
                "",
                "# header1",
                "",
                "key01=value01"
        );
        ByteArrayInputStream stream = new ByteArrayInputStream(text.getBytes());        
        PropertiesExt instance = new PropertiesExt();
        //
        // load
        //
        instance.load(stream);
        
        assertEquals(1,instance.size());
        assertEquals("value01",instance.get("key01"));
        
        //String[] result = instance.getHeaderComments();        
        
        //assertArrayEquals(comments, result);
    }

    /**
     * Test of load method, of class PropertiesExt.
     * With headerComments and key value headerComments
     */
    @Test
    public void testLoad_2() throws IOException {
        System.out.println("load");

        String[] comments = new String[] {"","# header1",""};
        String[] keyComments = new String[] {"","# keyvalue"};        
        //
        // Create the corresponding string
        //
        String sep = System.lineSeparator();
        
        String text = String.join(sep, 
                "",
                "# header1",
                "",
                "",
                "# keyvalue",
                "key01=value01"
        );
                
        
        ByteArrayInputStream stream = new ByteArrayInputStream(text.getBytes());
        PropertiesExt instance = new PropertiesExt();
        
        instance.load(stream);
        
        assertEquals(1,instance.size());
        assertEquals("value01",instance.get("key01"));
        
        //String[] result = instance.getHeaderComments();
        
        //assertArrayEquals(comments, result);

        //result = instance.getComments("key01");
        
        //assertArrayEquals(keyComments, result);
        
        
    }
    
    /**
     * Test of store method, of class PropertiesExt.
     */
    @Test
    public void testStore() throws IOException {
        System.out.println("store");
        String sep = System.lineSeparator();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();        
        PropertiesExt instance = new PropertiesExt();
        instance.setProperty("key01", "value01");
        //
        // load
        //
        instance.store(stream);
        String expResult = "key01=value01";
        String result = stream.toString();
        assertEquals(expResult,result); 
    }

    /**
     * Test of store method, of class PropertiesExt.
     */
    @Test
    public void testStore_1() throws IOException {
        System.out.println("store");
        String sep = System.lineSeparator();

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();        
        
        String[] headerComments = new String[] {"","# header1",""};
        String[] keyComments = new String[] {"","# keyvalue"};        
        //
        // Create the corresponding string
        //
        
        String text = String.join(sep, 
                "",
                "# header1",
                "",
                "",
                "# keyvalue",
                "key01=value01"
        );
                
        
        ByteArrayInputStream stream = new ByteArrayInputStream(text.getBytes());
        PropertiesExt inputProperties = new PropertiesExt();
        
        inputProperties.load(stream);

        //
        // load
        //
        PropertiesExt outputProperties = new PropertiesExt();
        //outputProperties.put("key01", "value01");
        //outputProperties.putComments("key01", keyComments);
        //outputProperties.putHeaderComments(headerComments);
        
        outputProperties.store(outstream);
        String outString = outstream.toString();
        assertEquals(text,outString); 
        assertEquals(inputProperties,outputProperties); 
    }
    
    
    /**
     * Test of stringPropertyNames method, of class PropertiesExt.
     */
    @Test
    public void testStringPropertyNames() {
        System.out.println("stringPropertyNames");
        PropertiesExt instance = new PropertiesExt();
        Set<String> expResult = new HashSet<>();
        Set<String> result = instance.stringPropertyNames();
        assertEquals(expResult, result);
        
        expResult = new HashSet<>();
        expResult.add("key01");
        instance.setProperty("key01", "value01");
        result = instance.stringPropertyNames();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of remove method, of class PropertiesExt.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String key = "key01";
        String value = "value01";
        PropertiesExt instance = new PropertiesExt();
        instance.setProperty(key, value);
        String com = PropertiesExt.joinValues(new String[] {"com1","com2"} );
        //instance.put(PropertiesExt.commentKey(key), com);
        //instance.put(PropertiesExt.HEADER_COMMENT, com);
        
        instance.remove(key);
        assertTrue(instance.isEmpty());
        //
        // Comments for the key must be empty
        //
        String empty = PropertiesExt.joinValues(new String[0]);
        //assertEquals(empty,instance.get(PropertiesExt.commentKey(key)));
        //
        // Comments for the properties itself is not empty
        //
        //assertTrue(instance.getProperty(PropertiesExt.HEADER_COMMENT).length() > 0);
        
        
    }
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testDecodeArray() throws IOException {
        
        String start = PropertiesExt.START;
        String end = PropertiesExt.END;
        
        String toDecode = start + "[1]" + end;
        String[] result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"1"}, result);
        
        toDecode = start + "[1][2]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"1","2"}, result);
        
        toDecode = start + "[1][2][3]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"1","2","3"}, result);

        
        toDecode = start + "[]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {""}, result);
        
        toDecode = start + "[][1]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","1"}, result);
        
        toDecode = start + "[][1][2]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","1","2"}, result);
        
        toDecode = start + "[][1][2][3]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","1","2","3"}, result);

        
        toDecode = start + "[][]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"",""}, result);

        toDecode = start + "[][][]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","",""}, result);

        toDecode = start + "[][][1][]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","","1",""}, result);
        
        toDecode = start + "[][][1][][2][]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"","","1","","2",""}, result);

        toDecode = start + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[0], result);
        
        
    }    

    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testDecodeArray_1() throws IOException {
        
        String start = PropertiesExt.START;
        String end = PropertiesExt.END;
        
        String joinSpec = PropertiesExt.joinValues(new String[] {"1\\2\\3"});
                
        String toDecode = start + "[" + joinSpec + "]" + end;
        String[] result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {joinSpec}, result);
        
        toDecode = start + "[1]" + "[" + joinSpec + "]" + end;
        
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"1",joinSpec}, result);
        
        toDecode = start + "[1]" + "[" + joinSpec + "]" + "[" + joinSpec + "]" + end;
        result = PropertiesExt.decodeArray(toDecode);
        assertArrayEquals(new String[] {"1",joinSpec,joinSpec}, result);

        
    }    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testEncodeArray() throws IOException {
        
        String start = PropertiesExt.START;
        String end = PropertiesExt.END;
        
        String[] array = new String[0];
        
        //String expResult = start + "[" + joinValues + "]" + end;
        String expResult = start+end;
        String result = PropertiesExt.encodeArray(array);
        assertEquals(expResult,result);
        
        array = null;
        
        //String expResult = start + "[" + joinValues + "]" + end;
        expResult = null;
        result = PropertiesExt.encodeArray(array);
        assertNull(result);        
        array = null;
        
        array = new String[]{""};
        expResult = start + "[]" + end;
        result = PropertiesExt.encodeArray(array);
        assertEquals(expResult,result);        

        array = new String[]{"1"};
        expResult = start + "[1]" + end;
        result = PropertiesExt.encodeArray(array);
        assertEquals(expResult,result);        
        
        array = new String[]{"[]1]]"};
        expResult = start + "[[[]]1]]]]]" + end;

        result = PropertiesExt.encodeArray(array);
        assertEquals(expResult,result);        
        
        array = new String[]{"[]]]"};
        expResult = start + "[[[]]]]]]]" + end;

        result = PropertiesExt.encodeArray(array);
        assertEquals(expResult,result);        

        
    }
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testIsEncodedArray() throws IOException {
        
        String start = PropertiesExt.START;
        String end = PropertiesExt.END;
        
        String toDecode = "[1]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));
        
        toDecode = start + "[1]";
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));        
        
        toDecode = start + end;
        PropertiesExt.decodeArray(toDecode);
        assertTrue(PropertiesExt.isEncodedArray(toDecode));        
        
        toDecode = start + "[1][2][3]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertTrue(PropertiesExt.isEncodedArray(toDecode));        
        
        toDecode = start + "1][2][3]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));        
        
        toDecode = start + "[1][2[3]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));        
        
        toDecode = start + "[1]2[3]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));                
        
        toDecode = start + "123" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));                        

        toDecode = start + "[123[45]]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertFalse(PropertiesExt.isEncodedArray(toDecode));                        
        
        toDecode = start + "[123[[45]]]" + end;
        PropertiesExt.decodeArray(toDecode);
        assertTrue(PropertiesExt.isEncodedArray(toDecode));                        
        
    }    
    /**
     * Test of createSection method, of class IniPreferences.
     */
    @Test
    public void testNext() throws IOException {
/*        String str = "[1][2][3]";
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        int pos = PropertiesExt.nextValue(chars, 0, sb);
        assertEquals(3,pos);
        
        str = "1][2][3]";
        chars = str.toCharArray();
        sb = new StringBuilder();
        pos = PropertiesExt.nextValue(chars, 4, sb);
        assertEquals(-1,pos);

        str = "[[[1][2][3]";
        chars = str.toCharArray();
        sb = new StringBuilder();
        pos = PropertiesExt.nextValue(chars, 0, sb);
        assertEquals(5,pos);
        str = "[[[1[[]]][2][3]";
        chars = str.toCharArray();
        sb = new StringBuilder();
        pos = PropertiesExt.nextValue(chars, 0, sb);
        assertEquals(9,pos);
  */      
        
    }    

    
    /**
     * Test of clear method, of class PropertiesExt.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        String key = "key01";
        String value = "value01";
        String empty = PropertiesExt.joinValues(new String[0]);        
        PropertiesExt instance = new PropertiesExt();
        instance.setProperty(key, value);
        String com = PropertiesExt.joinValues(new String[] {"com1","com2"} );
        //instance.put(PropertiesExt.commentKey(key), com);
        //instance.put(PropertiesExt.HEADER_COMMENT, com);

        //assertNotEquals(empty,instance.get(PropertiesExt.HEADER_COMMENT));
        //assertNotEquals(empty,instance.get(PropertiesExt.commentKey(key)));
        
        
        instance.clear();
        assertTrue(instance.isEmpty());
        //
        // Comments for the key must be empty
        //
        
        //assertEquals(empty,instance.get(PropertiesExt.commentKey(key)));
        //
        // Header headerComments must be empty
        //
        //assertEquals(empty,instance.get(PropertiesExt.HEADER_COMMENT));
    }
    /**
     * Test of clear method, of class PropertiesExt.
     */
    @Test
    public void testClearKeys() {
        System.out.println("clearKeys");
        String key = "key01";
        String value = "value01";
        String empty = PropertiesExt.joinValues(new String[0]);        
        PropertiesExt instance = new PropertiesExt();
        //instance.put(key, value);
        String com = PropertiesExt.joinValues(new String[] {"com1","com2"} );
        //instance.put(PropertiesExt.commentKey(key), com);
        //instance.put(PropertiesExt.HEADER_COMMENT, com);

        //assertNotEquals(empty,instance.get(PropertiesExt.HEADER_COMMENT));
        //assertNotEquals(empty,instance.get(PropertiesExt.commentKey(key)));
        
        
        instance.clearKeys();
        assertTrue(instance.isEmpty());
        //
        // Comments for the key must be empty
        //
        
        //assertEquals(empty,instance.get(PropertiesExt.commentKey(key)));
        //
        // Header headerComments must be empty
        //
        //assertNotEquals(empty,instance.get(PropertiesExt.HEADER_COMMENT));
    }
    
    
    /**
     * Test of isEmpty method, of class PropertiesExt.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PropertiesExt instance = new PropertiesExt();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class PropertiesExt.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        PropertiesExt instance = new PropertiesExt();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        instance.setProperty("key01", "value01");
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);        
    }
    
}
