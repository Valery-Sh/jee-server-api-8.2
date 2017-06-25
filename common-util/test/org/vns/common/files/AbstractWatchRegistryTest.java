package org.vns.common.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.EventListener;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.vns.common.PathObject;
import org.vns.common.RequestExecutor;
import org.vns.common.RequestExecutor.Task;
import org.vns.common.files.AbstractWatchRegistry.WatchableState;

/**
 *
 * @author Valery Shyshkin
 */
public class AbstractWatchRegistryTest {

    AbstractWatchRegistry instance = new WatchRegistryImpl();

    public AbstractWatchRegistryTest() {
    }
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

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
    public void tearDown() throws IOException {
        if (instance == null) {
            return;
        }
        if (instance.watchService() != null) {
            instance.closeService();
        }
        tempFolder.delete();
    }

    /**
     * Test of closeService method, of class AbstractWatchRegistry.
     */
    @Test
    public void testCloseService() throws Exception {
        System.out.println("closeService");
        instance.closeService();
        assertFalse(instance.isServiceAvailable());
    }

    /**
     * Test of isServiceAvailable method, of class AbstractWatchRegistry.
     */
    @Test
    public void testIsServiceAvailable() throws IOException {
        System.out.println("isServiceAvailable");
        //instance = new AbstractWatchRegistry();
        boolean expResult = false;
        boolean result = instance.isServiceAvailable();
        assertEquals(expResult, result);

        instance.closeService();
        expResult = false;
        result = instance.isServiceAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Test of watchService method, of class AbstractWatchRegistry.
     */
    @Test
    public void testWatchService() throws IOException {
        System.out.println("watchService");
        //instance = new AbstractWatchRegistry();
        WatchService result = instance.watchService();
        assertNotNull(result);
    }

    /**
     * Test of register method, of class AbstractWatchRegistry.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("registerPath");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};

        //instance = new AbstractWatchRegistry();
        AbstractWatchRegistry.WatchableState result = instance.register(pathD, kind);
        assertTrue(Arrays.equals(result.getKind(), kind));
        assertTrue(result.getPath().equals(pathD));
        assertArrayEquals(new EventListener[0], result.getListeners());
    }

    /**
     * Test of register method, of class AbstractWatchRegistry.
     */
    @Test
    public void testRegister_1() throws Exception {
        System.out.println("register");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        FileChangeListener[] listeners = new FileChangeListener[]{new FileChangeAdapter()};
        //instance = new AbstractWatchRegistry();
        AbstractWatchRegistry.WatchableState result = instance.register(pathD, kind);
        result.addListeners(listeners);

        assertArrayEquals(kind, result.getKind());
        assertTrue(result.getPath().equals(pathD));
        EventListener[] list = new EventListener[]{new FileChangeAdapter()};

        assertArrayEquals(list, result.getListeners());
    }

    /**
     * Test of register method, of class AbstractWatchRegistry.
     */
    @Test
    public void testRegister_Consumer() throws Exception {
        System.out.println("register");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE};

        //instance = new AbstractWatchRegistry();
        //
        // skip() causes the pathD is not registered
        //
        AbstractWatchRegistry.WatchableState result = instance.register(pathD, w -> {
            w.skip();
        });
        //
        // Not registered due to the effect of skip() method
        //
        assertNull(result);

        result = instance.register(pathD, w -> {
            w.setKind(kind);
            w.lock();
        });
        assertArrayEquals(result.getKind(), kind);
        assertNotNull(result.getKey());
        assertTrue(instance.isRegistered(pathD));
    }

    /**
     * Test of registerRecursively method, of class AbstractWatchRegistry.
     */
    @Test
    public void testRegisterRecursively() throws Exception {

        System.out.println("registerRecursively");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();
        Files.createDirectories(Paths.get(pathD.toString(), "d1/d2"));

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};

        //instance = new AbstractWatchRegistry();

        instance.registerRecursively(pathD, w -> {
            w.setKind(kind);
            w.lock();
        });
        assertEquals(3, instance.count());

        assertTrue(instance.isRegistered(pathD));
        assertTrue(instance.isRegistered(pathD.resolve("d1")));
        assertTrue(instance.isRegistered(pathD.resolve("d1/d2")));

    }

    /**
     * Test of onEntryCreate method, of class AbstractWatchRegistry.
     */
    @Test
    public void testOnEntryCreate() throws IOException, InterruptedException {

        System.out.println("onEntryCreate");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        Files.createDirectories(pathD);

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        FileChangeListener[] listeners = new FileChangeListener[]{createAdapter()};

        //instance = new AbstractWatchRegistry();
        //
        // Register only pathD
        //
        instance.register(pathD, w -> {
            w.addListeners(listeners);
            w.setKind(kind);
            w.lock();
        });
        //
        // Set a function to handle ENTRY_CREATE which here invokes 
        // registerRecursively
        //
        instance.onEntryCreate((w, p) -> {
            instance.registerRecursively(w.getPath().resolve(p), cw -> {
                cw.addListeners(listeners);
                cw.setKind(kind);
                cw.lock();
            });
        });

        RequestExecutor.Task task = createTask(instance);
        Thread.sleep(100);
        //
        // All the directories that are created below must be registeren
        // 
        Files.createDirectories(pathD.resolve("d1/d2"));
        Files.createDirectories(pathD.resolve("d1/t1/t2"));

        Thread.sleep(30);
        int expResult = 5;
        assertEquals(expResult, instance.count());
        assertTrue(instance.isRegistered(pathD.resolve("d1")));
        assertTrue(instance.isRegistered(pathD.resolve("d1/d2")));
        assertTrue(instance.isRegistered(pathD.resolve("d1/t1")));
        assertTrue(instance.isRegistered(pathD.resolve("d1/t1/t2")));

    }

    /**
     * Test of onEntryCreate method, of class AbstractWatchRegistry. Try with
     * listeners which created by the onEntryCreate function
     */
    @Test
    public void testOnEntryCreate_1() throws IOException, InterruptedException {

        System.out.println("onEntryCreate");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        Files.createDirectories(pathD);

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};

        FileChangeAdapter adapter = createAdapter();

        FileChangeListener[] listeners = new FileChangeListener[]{adapter};

        //instance = new AbstractWatchRegistry();
        //
        // Register only pathD
        //
        instance.register(pathD, w -> {
            w.setKind(kind);
            w.lock();
        });
        //
        // Set a function to handle ENTRY_CREATE which here invokes 
        // registerRecursively
        //
        instance.onEntryCreate((w, p) -> {
            w.addListeners(listeners);
            w.setKind(kind);
        });

        createTask(instance);

        Thread.sleep(100);
        //
        // All the directories that are created below must be registeren
        // 
        Files.createDirectories(pathD.resolve("d1"));

        Thread.sleep(30);
        int expResult = 1;
        assertEquals(expResult, instance.count());
        assertFalse(instance.isRegistered(pathD.resolve("d1")));

        assertTrue(adapter.getResult().startsWith("FOLDER_ENTRY_CREATE"));
        assertTrue(adapter.getResult().endsWith("d1"));

    }

    /**
     * Test of onEntryCreate method, of class AbstractWatchRegistry. Try with
     * with suspendListeners()
     */
    @Test
    public void testOnEntryCreate_2() throws IOException, InterruptedException {

        System.out.println("onEntryCreate");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        Files.createDirectories(pathD);

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};

        FileChangeAdapter adapter = createAdapter();

        FileChangeListener[] listeners = new FileChangeListener[]{adapter};

        FileChangeAdapter origAdapter = createAdapter();

        FileChangeListener[] origListeners = new FileChangeListener[]{origAdapter};

        //instance = new AbstractWatchRegistry();
        //
        // Register only pathD. Add listeners
        //
        instance.register(pathD, w -> {
            w.addListeners(origListeners);
            w.setKind(kind);
            w.lock();
        });
        //
        // Set a function to handle ENTRY_CREATE which here invokes 
        // registerRecursively. Add listeners on create and suspend
        // original listeners
        //
        instance.onEntryCreate((w, p) -> {
            w.addListeners(listeners);
            w.setKind(kind);
            w.suspendListeners();
        });

        RequestExecutor.Task task = createTask(instance);
        Thread.sleep(100);
        //
        // All the directories that are created below must be registeren
        // 
        Files.createDirectories(pathD.resolve("d1"));

        Thread.sleep(30);
        int expResult = 1;
        assertEquals(expResult, instance.count());
        assertFalse(instance.isRegistered(pathD.resolve("d1")));

        assertTrue(adapter.getResult().startsWith("FOLDER_ENTRY_CREATE"));
        assertTrue(adapter.getResult().endsWith("d1"));
        //
        // Original listeners are not executed => the result of the adapter is null
        //
        assertNull(origAdapter.getResult());
    }

    /**
     * Test of registerRecursively method, of class AbstractWatchRegistry. Tests
     * when a directory is skipped when registered
     */
    @Test
    public void testRegisterRecursively_1() throws IOException, InterruptedException {

        System.out.println("registerRecursively");
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        //
        // Create the directories that must be registered
        //
        Files.createDirectories(pathD.resolve("d1/d2"));
        Files.createDirectories(pathD.resolve("t1/t2"));

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        FileChangeListener[] listeners = new FileChangeListener[]{createAdapter()};

        //instance = new AbstractWatchRegistry();
        //
        // Now register all directories starting from pathD except
        // the subdirectory "t1" 
        //
        instance.registerRecursively(pathD, w -> {
            w.addListeners(listeners);
            w.setKind(kind);
            Path p = w.getPath();
            System.out.println("--- " + w.getPath());
            if (w.getPath().equals(pathD.resolve("t1"))) {
                w.skip();
            }
            w.lock();
        });

        createTask(instance);
        Thread.sleep(100);

        Thread.sleep(30);
        int expResult = 4;
        assertEquals(expResult, instance.count());
        assertFalse(instance.isRegistered(pathD.resolve("t1")));
        assertTrue(instance.isRegistered(pathD.resolve("t1/t2")));
        assertTrue(instance.isRegistered(pathD.resolve("d1")));
        assertTrue(instance.isRegistered(pathD.resolve("d1/d2")));
    }

    /**
     * Test of isRegistered method, of class AbstractWatchRegistry.
     */
    @Test
    public void testIsRegistered() throws IOException {
        System.out.println("isRegistered");

        //instance = new AbstractWatchRegistry();
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        instance.register(pathD, kind);
        boolean expResult = true;
        boolean result = instance.isRegistered(pathD);
        assertEquals(expResult, result);
    }

    /**
     * Test of unregister method, of class AbstractWatchRegistry.
     */
    @Test
    public void testUnregister() throws IOException {
        System.out.println("unregister");
        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();
        //instance = new AbstractWatchRegistry();
        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        instance.register(pathD, kind);
        int c = instance.count();
        instance.unregister(pathD);
        assertFalse(instance.isRegistered(pathD));
    }

    /**
     * Test of addListeners method, of class AbstractWatchRegistry.
     */
    @Test
    public void testAddListeners() throws IOException {
        System.out.println("addListeners");
        //instance = new AbstractWatchRegistry();
        FileChangeListener[] expResult = new FileChangeAdapter[]{
            new FileChangeAdapter(), new FileChangeAdapter()
        };

        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();
        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        instance.register(pathD, kind);
        instance.addListeners(pathD, expResult);

        EventListener[] result = instance.getListeners(pathD);
        assertArrayEquals(expResult, result);
        //
        // Now register listeners ttat already exist
        //
        instance.addListeners(pathD, expResult);
        result = instance.getListeners(pathD);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of registerRecursively method, of class AbstractWatchRegistry. Tests
     * when listenersHandler is set only for registry
     */
    @Test
    public void testAddListenerHandlers() throws IOException, InterruptedException {

        System.out.println("setListenersHandler");
        class HasResultListener implements EventListener {

            public String RESULT = null;

            public void onModify(Path path, WatchEvent ev) {
                RESULT = "EXECUTED";
            }
        }
        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();
        EventListener hasResultListener = new HasResultListener();
        EventListener[] listeners = new EventListener[]{hasResultListener};

        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        FileChangeListener[] origListeners = new FileChangeListener[]{createAdapter()};

        //instance = new AbstractWatchRegistry();
        //
        // Now register all directories starting from pathD except
        // the subdirectory "t1" 
        //
        instance.register(pathD);
        instance.addListeners(pathD, listeners);

        instance.addListenerRecognizers((state, event, listener) -> {
            ((HasResultListener) listener).onModify(state.getPath(), event);
            return true;
        });

        createTask(instance);
        Thread.sleep(100);
        //
        // Create the directories to generate an event
        //
        Files.createDirectories(pathD.resolve("d1"));
        Thread.sleep(100);

        assertEquals("EXECUTED", ((HasResultListener) hasResultListener).RESULT);
    }

    /**
     * Test of registerRecursively method, of class AbstractWatchRegistry. Two
     * types of event hasResultListener
     */
    @Test
    public void testAddListenersHandler_2() throws IOException, InterruptedException {

        System.out.println("addListenerHandlers");
        class HasResultListener implements EventListener {

            public String RESULT = null;

            public void onModify(Path path, WatchEvent ev) {
                if (RESULT == null) {
                    RESULT = "EXECUTED";
                } else {
                    RESULT += ",EXECUTED";
                }

            }

            public void onModify01(Path path, WatchEvent ev) {
                if (RESULT == null) {
                    RESULT = "EXECUTED01";
                } else {
                    RESULT += ",EXECUTED01";
                }
            }

        }
        class HasResultListener01 implements EventListener {

            public String RESULT = null;

            public void onModify(Path path, WatchEvent ev) {
                RESULT = "EXECUTED01";
            }
        }

        File folderD = tempFolder.newFolder("D");

        Path pathD = folderD.toPath();

        EventListener hasResultListener = new HasResultListener();
        EventListener hasResultListener01 = new HasResultListener01();

        //instance = new AbstractWatchRegistry();
        //
        // Now register all directories starting from pathD except
        // the subdirectory "t1" 
        //
        instance.register(pathD);
        instance.addListeners(pathD, hasResultListener, hasResultListener01);

        instance.addListenerRecognizers((state, event, listener) -> {
            if (listener instanceof HasResultListener) {
                ((HasResultListener) listener).onModify(state.getPath(), event);
                return true;
            } else {
                return false;
            }
        });
        instance.addListenerRecognizers((state, event, listener) -> {
            if (listener instanceof HasResultListener01) {
                ((HasResultListener01) listener).onModify(state.getPath(), event);
                return true;
            } else {
                return false;
            }
        });

        createTask(instance);
        Thread.sleep(100);
        //
        // Create the directories to generate an event
        //
        Files.createDirectories(pathD.resolve("d1"));
        Thread.sleep(100);

        assertEquals("EXECUTED01", ((HasResultListener01) hasResultListener01).RESULT);
        assertEquals("EXECUTED", ((HasResultListener) hasResultListener).RESULT);
    }

    /**
     * Test of removeListeners method, of class AbstractWatchRegistry.
     */
    @Test
    public void testRemoveListeners() throws IOException {
        System.out.println("removeListeners");
        //instance = new AbstractWatchRegistry();
        FileChangeAdapter l1 = new FileChangeAdapter();
        FileChangeAdapter l2 = new FileChangeAdapter();

        FileChangeListener[] expResult = new FileChangeAdapter[]{
            l1, l2
        };

        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();
        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        instance.register(pathD, kind);
        instance.addListeners(pathD, expResult);

        instance.removeListeners(pathD, l1);

        EventListener[] result = instance.getListeners(pathD);
        assertArrayEquals(new FileChangeListener[]{l2}, result);

    }

    FileChangeAdapter createAdapter() {
        FileChangeAdapter adapter = new FileChangeAdapter() {
            @Override
            public void fileCreated(FileEvent ev) {
                setResult("FILE_ENTRY_CREATE watchable=" + ev.getWatchable() + "; context=" + ev.getContext());
                System.out.println("RESULT:" + getResult());

            }

            @Override
            public void fileDeleted(FileEvent ev) {
                setResult("FILE_ENTRY_DELETE  watchable=" + ev.getWatchable() + "; context=" + ev.getContext());
                System.out.println("RESULT:" + getResult());

            }

            @Override
            public void folderCreated(FileEvent ev) {
                setResult("FOLDER_ENTRY_CREATE watchable=" + ev.getWatchable() + "; context=" + ev.getContext());
                System.out.println("RESULT:" + getResult());

            }

            @Override
            public void folderDeleted(FileEvent ev) {

                setResult("FOLDER_ENTRY_DELETE");
                System.out.println("RESULT: watchable=" + ev.getWatchable() + "; context=" + ev.getContext());
            }

        };
        return adapter;
    }

    /**
     * Test of doProcessEvents method, of class AbstractWatchRegistry.
     */
    @Test
    public void testDoProcessEvents() throws IOException, InterruptedException {
        System.out.println("doProcessEvents");
        //instance = new AbstractWatchRegistry();
        FileChangeAdapter l1 = createAdapter();
        FileChangeAdapter l2 = new FileChangeAdapter();

        FileChangeListener[] expResult = new FileChangeAdapter[]{
            l1, l2
        };

        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();
        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        instance.register(pathD, kind);
        instance.addListeners(pathD, expResult);

        RequestExecutor.Task task = RequestExecutor.createTask(() -> {
            try {
                instance.doProcessEvents();
            } catch (IOException ex) {
                Logger.getLogger(AbstractWatchRegistryTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        task.schedule(20);
        task.shutdown();
        Path pathD_t = Paths.get(pathD.toString(), "t.txt");
        PathObject fo = FilePathObject.createDataFile(pathD_t);
        Thread.sleep(100);
        assertTrue(l1.getResult().startsWith("FILE_ENTRY_CREATE"));
        fo.delete();
        Thread.sleep(100);
        assertTrue(l1.getResult().startsWith("FILE_ENTRY_DELETE"));

        task.shutdownNow(0);

        System.out.println("--- END -----");

    }

    Task createTask(AbstractWatchRegistry reg) {
        RequestExecutor.Task task = RequestExecutor.createTask(() -> {
            try {
                reg.doProcessEvents();
            } catch (IOException ex) {
                Logger.getLogger(AbstractWatchRegistryTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        task.schedule(20);
        task.shutdown();
        return task;
    }

    Path registerPath(Path path, AbstractWatchRegistry reg, FileChangeAdapter adapter) throws IOException {
        WatchEvent.Kind[] kind = new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY};
        reg.register(path, kind).addListeners(adapter);

        return path;
    }

    /**
     * Test of doProcessEvents method, of class AbstractWatchRegistry.
     */
    @Test
    public void testDoProcessEvents_1() throws IOException, InterruptedException {
        System.out.println("doProcessEvents");
        //instance = new AbstractWatchRegistry();
        FileChangeAdapter adapter = createAdapter();

        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();

        registerPath(pathD, instance, adapter);

        RequestExecutor.Task task = createTask(instance);
        //
        // FOLDERS
        //
        Path pathD_d1 = Paths.get(pathD.toString(), "d1");

        PathObject fo = FilePathObject.createDirectories(pathD_d1);
        Thread.sleep(50);
        System.out.println("R: " + adapter.getResult());
        String result = adapter.getResult();
        String expResult = "FOLDER_ENTRY_CREATE";

        assertTrue(result.startsWith(expResult));

        task.shutdownNow(0);
    }

    /**
     * Test of doProcessEvents method, of class AbstractWatchRegistry.
     */
    @Test
    public void testDoProcessEvents_2() throws IOException, InterruptedException {

        System.out.println("doProcessEvents");

        File folderD = tempFolder.newFolder("D");
        Path pathD = folderD.toPath();

        Path pathD_d1 = Paths.get(pathD.toString(), "d1");
        PathObject fo = FilePathObject.createDirectories(pathD_d1);
        Thread.sleep(100);

        //instance = new AbstractWatchRegistry();

        FileChangeAdapter adapter = createAdapter();

        registerPath(pathD, instance, adapter);

        RequestExecutor.Task task = createTask(instance);
        Thread.sleep(100);
        //
        // FOLDERS
        //
        fo.delete();
        Thread.sleep(100);
        String result = adapter.getResult();
        String expResult = "FOLDER_ENTRY_DELETE";
        System.out.println("  --- ADAPTER getResult()=" + result);
        assertEquals(expResult, result);

        task.shutdownNow(0);
    }

    @Test
    public void testTEMP_TEMP() {

        Predicate<WatchableState> p = state -> {
            boolean retval = false;
            if (state.getPath().equals(Paths.get("c://d"))) {
                retval = true;
            }
            return retval;
        };

        WatchableState wstate = new WatchableState(Paths.get("c:/D"));
        assertTrue(p.test(wstate));

    }

    public static class WatchRegistryImpl extends AbstractWatchRegistry {

        public WatchRegistryImpl() {
        }

        @Override
        protected ListenerRecognizer getDefaultListenerRecognizer() {

            return (state, event, listener) -> {

                if (!(listener instanceof FileChangeListener)) {
                    return false;
                }

                boolean retval = true;
                FileChangeListener fileListener = (FileChangeListener) listener;

                WatchEvent.Kind<?> kind = event.kind();
                FileEvent fe = new FileEvent(state.getPath(), (Path) event.context(), kind);

                Path eventSource = state.getPath().resolve((Path) event.context());

                if (event.kind() == ENTRY_CREATE) {
                    if (Files.isDirectory(eventSource)) {
                        fileListener.folderCreated(fe);
                    } else {
                        fileListener.fileCreated(fe);
                    }

                } else if (kind == ENTRY_DELETE) {
                    boolean isDirectory = state.isDirectory(eventSource);
                    if (isDirectory) {
                        fileListener.folderDeleted(fe);
                    } else {
                        fileListener.fileDeleted(fe);
                    }
                } else if (kind == ENTRY_MODIFY) {
                    boolean isDirectory = state.isDirectory(eventSource);
                    if (isDirectory) {
                        fileListener.folderModified(fe);
                    } else {
                        fileListener.fileModified(fe);
                    }
                }
                return retval;
            };
        }

    }
}
