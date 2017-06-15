package org.netbeans.modules.jeeserver.base.embedded.project;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
import org.netbeans.modules.jeeserver.base.deployment.BaseDeploymentManager;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseUtil;
import org.netbeans.modules.jeeserver.base.deployment.utils.prefs.InstancePreferences;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteUtil;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbPreferences;

/**
 *
 * @author V. Shyshkin
 */
public class NbSuitePreferences {

    private static final Logger LOG = Logger.getLogger(NbSuitePreferences.class.getName());
    private static final String SHARED_UID = "-shared";
    public static final String SERVER_INSTANCE = "server-instance";
    
    private final String dir;
    private final String suiteUID;

    protected NbSuitePreferences(Project serverInstance) {
        dir = serverInstance.getProjectDirectory().getPath();
        Project suite = SuiteManager.getServerSuiteProject(serverInstance);
        suiteUID = SuiteUtil.getSuiteUID(suite.getProjectDirectory());
    }

    protected NbSuitePreferences(Project serverInstance, String suiteUID) {
        dir = serverInstance.getProjectDirectory().getPath();
        this.suiteUID = suiteUID;
    }

    protected NbSuitePreferences(String dir, String suiteUID) {
        this.dir = dir;
        this.suiteUID = suiteUID;
    }

    public static NbSuitePreferences newInstance(Project instanceProject) {
        NbSuitePreferences d = new NbSuitePreferences(instanceProject);
        return d;
    }

    public static NbSuitePreferences newSharedInstance(Project instanceProject) {
        NbSuitePreferences d = new NbSuitePreferences(instanceProject, "-shared");
        return d;
    }

    public static NbSuitePreferences newInstance(String dir, String suiteUID) {
        NbSuitePreferences d = new NbSuitePreferences(dir, suiteUID);
        return d;
    }

    protected Preferences rootRegistryNode() {
        Preferences prefs = rootSuiteNode().node(getNamespace());
        return prefs;
    }

    protected Preferences rootSuiteNode() {
        Preferences prefs = rootNode().node("uid-" + suiteUID);
        return prefs;
    }

    /**
     * Returns a preferences node that serves as a root node for all other
     * nodes, than this class deals with. Just invokes:
     * <pre>
     *  return NbPreferences.forModule(EmbJ2eePlatformFactory.class);
     * </pre>
     *
     * @return
     */
    protected Preferences rootNode() {
        //return NbPreferences.forModule(EmbJ2eePlatformFactory.class);
        return NbPreferences.forModule(BaseDeploymentManager.class);
    }

    public String[] childrenNames() {

        FileObject configRoot = FileUtil.getConfigRoot();;
        List<String> folderNames = new ArrayList<String>();

        if (configRoot != null) {
            for (FileObject fo : Collections.list(configRoot.getFolders(false))) {
                Enumeration<? extends FileObject> en = fo.getChildren(true);
                while (en.hasMoreElements()) {
                    FileObject ffo = en.nextElement();
                    if (ffo.hasExt("properties")) { // NOI18N
                        folderNames.add(fo.getNameExt());
                        break;
                    }
                }
            }
            for (FileObject fo : Collections.list(configRoot.getData(false))) {
                if (fo.hasExt("properties")) { // NOI18N
                    folderNames.add(fo.getName());
                }
            }
        }

        return folderNames.toArray(new String[folderNames.size()]);
    }

    protected Preferences clearSuite() throws BackingStoreException {
        synchronized (this) {
            String[] childs = rootSuiteNode().childrenNames();
            for (String c : childs) {
                rootSuiteNode().node(c).removeNode();
            }
            return rootSuiteNode();
        }
    }

    protected Preferences clearRoot() throws BackingStoreException {
        synchronized (this) {
            String[] childs = rootNode().childrenNames();
            for (String c : childs) {
                rootNode().node(c).removeNode();
            }
            return rootNode();
        }
    }

    protected Preferences clearRegistry() throws BackingStoreException {
        synchronized (this) {
            String[] childs = rootRegistryNode().childrenNames();
            for (String c : childs) {
                rootRegistryNode().node(c).removeNode();
            }
            return rootRegistryNode();
        }
    }

    /**
     * Returns a string value than represents a relative path to a node returned
     * by a method {@link #rootRegistryNode() }. In fact the method converts a
     * returned value is a project directory for a project specified as a
     * parameter in the method call {@link #newInstance(org.netbeans.api.project.Project)
     * }
     *
     *
     * @return a string value than represents a relative path to a node returned
     * by a method {@link #rootRegistryNode() }.
     */
    protected String getNamespace() {
        return getNamespace(dir);
    }

    protected String getNamespace(String forDir) {
        Path forDirPath = Paths.get(forDir);

        String root = forDirPath.getRoot().toString().replaceAll(":", "_");
        if (root.startsWith("/")) {
            root = root.substring(1);
        }
        Path targetPath = forDirPath.getRoot().relativize(forDirPath);

        Path target = Paths.get(root, targetPath.toString());

        //return "uid-" + suiteUID + "/" + target.toString().replace("\\", "/");
        return target.toString().replace("\\", "/");

    }

    /**
     * Returns an instance of type null     {@link org.netbeans.modules.jeeserver.base.deployment.utils.prefs.InstancePreferences ) 
     * for a given string properties file name.
     *
     * @return an instance of type {@literal InstancePreferences }.
     */
    public InstancePreferences getProperties() {
        return getProperties(rootRegistryNode(), SERVER_INSTANCE);
    }

    /**
     * Returns an instance of type null     {@link org.netbeans.modules.jeeserver.base.deployment.utils.prefs.InstancePreferences ) 
     * for a given string properties file name.
     *
     * @param id a string value that specifies a properties file name
     * @return an instance of type {@literal InstancePreferences }.
     */
    public InstancePreferences getProperties(String id) {
        return getProperties(rootRegistryNode(), id);
    }

    protected boolean isUIDShared() {
        return SHARED_UID.equals(suiteUID);
    }

    /**
     *
     * @throws BackingStoreException
     */
    public void removeAllProperties() throws BackingStoreException {
        synchronized (this) {
            Preferences prefs = rootRegistryNode();
            String[] childs = prefs.childrenNames();
            for (String c : childs) {
                getProperties(c).remove();
            }
        }
    }

    /**
     * Returns all existing properties created in the given {@code  namespace}.
     *
     * @param id
     * @return list of all existing properties created in the given namespace
     */
    protected InstancePreferences getProperties(Preferences prefs, String id) {
        try {

            InstancePreferences ip = null;
            synchronized (this) {
                String[] cn = prefs.childrenNames();
                Preferences child = prefs.node(id);
                child.flush();
                ip = new InstancePreferences(id, child);
            }
            return ip;
        } catch (Exception ex) {
            BaseUtil.out("SuiteRegistry getProperties() EXCEPTION = " + ex.getMessage());
            LOG.log(Level.INFO, null, ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Returns all existing properties created in the given namespace.
     *
     * @param namespace string identifying the namespace
     * @param id
     * @return list of all existing properties created in the given namespace
     */
    protected InstancePreferences getProperties(String namespace, String id) {

        Preferences prefs = rootNode();

        try {
            prefs = prefs.node(namespace);
            prefs.flush();

            InstancePreferences ip = null;
            synchronized (this) {
                String[] cn = prefs.childrenNames();
                Preferences child = prefs.node(id);
                ip = new InstancePreferences(id, child);
            }
            return ip;
        } catch (Exception ex) {
            BaseUtil.out("AbstractPreferencesManager getProperties() EXCEPTION = " + ex.getMessage());
            LOG.log(Level.INFO, null, ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Creates and returns properties in the given namespace. It is perfectly
     * legal to call this method multiple times with the same namespace as a
     * parameter - it will always create new instance of InstanceProperties.
     * Returned properties should serve for persistence of the single server
     * instance.
     *
     * @param namespace string identifying the namespace of created
     * InstanceProperties
     * @return new InstanceProperties logically placed in the given namespace
     */
    /*    protected InstancePreferences createProperties(String namespace, String id) {
        Preferences prefs = rootNode();

        try {
            prefs = prefs.node(namespace);

            synchronized (this) {
                prefs = prefs.node(id);
                prefs.flush();

                InstancePreferences created = new InstancePreferences(id, prefs);

                return created;
            }
        } catch (BackingStoreException ex) {
            LOG.log(Level.INFO, null, ex);
            throw new IllegalStateException(ex);
        }
    }
     */
}
