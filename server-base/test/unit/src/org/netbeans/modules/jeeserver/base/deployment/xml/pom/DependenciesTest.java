
package org.netbeans.modules.jeeserver.base.deployment.xml.pom;

import java.io.InputStream;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlDocument;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlElement;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlRoot;

/**
 *
 * @author Valery
 */
public class DependenciesTest {
    
    public DependenciesTest() {
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
     * Test of isChildTagNameSupported method, of class Dependencies.
     * The only {@literal dependency } DOM Element is supported as a child. 
     *
     */
    @Test
    public void testIsChildSupported() {
        System.out.println("isChildSupported");
        PomDocument pomDocument = new PomDocument(getClass().getResourceAsStream("resources/pom01_1.xml"));
        XmlRoot root = pomDocument.getRoot();
        String c = root.getTagMap().getDefaultClass();
        
        XmlElement build = root.findFirstElementByPath("build");
        
        Dependencies dependencies = new Dependencies();
//        assertTrue(dependencies.isChildTagNameSupported(el.getTagName())); 
        
    }

    /**
     * Test of getChildElements method, of class Dependencies.
     */
    @Test
    public void testGetChilds() {
        System.out.println("getChilds");
        PomDocument pomDocument = new PomDocument();
        PomRoot pomRoot = pomDocument.getRoot();
        Dependencies dependencies = new Dependencies();
        assertTrue(dependencies.getChilds().isEmpty()); 
        
        pomRoot.addChild(dependencies);
        Dependency dependency = new Dependency();
        
        dependencies.addChild(dependency);
        assertTrue(dependencies.getChilds().size() == 1); 
        assertTrue(dependencies.getChilds().get(0) instanceof Dependency); 
    }

    /**
     * Test of testCloneXmlElementInstance method, of class Dependencies.
     * Creates a PomCocument using a {@literal  pom.xml} resource file.
     * Apply the method to be tested to an object of type {@link Dependencies }.
     * The clone must contain all child nodes of the original {@literal Dependencies}.
     */
    @Test
    public void testCloneXmlElementInstance() {
        System.out.println("cloneXmlElementInstance");
        InputStream is = this.getClass().getResourceAsStream("resources/pom01_1.xml");
        PomDocument pomDocument = new PomDocument(is);
        PomRoot pomRoot = pomDocument.getRoot();
        pomDocument.getDomDependencyList();
        
        
        Dependencies dependencies = pomRoot.getDependencies();
        //
        // Clone Dependencies object
        //
        Dependencies dependenciesClone = (Dependencies) dependencies.getClone();
        assertNotNull(dependenciesClone);
        //
        // Check whether all child elements are cloned
        //
        Dependency dep = dependenciesClone.findDependency("a.b.c", "a1.b1.c1", "jar");
        DependencyArtifact groupId = dep.findByTagName("groupId");
        assertEquals(groupId.getTextContent(), "a.b.c");
        DependencyArtifact artifactId = dep.findByTagName("artifactId");
        assertEquals(artifactId.getTextContent(), "a1.b1.c1");
        
        //c = art.getElement().getTextContent();
        //boolean b = art.getElement().hasChildNodes();
        //int i = 0;
      
    }
    /**
     * Test of mergeApi method, of class Dependencies.
     */
    @Test
    public void testMergeAPI() {
        System.out.println("mergeAPI");
        InputStream is = this.getClass().getResourceAsStream("resources/test-jetty-pom.xml");
        PomDocument pomDocument = new PomDocument(is);
        PomRoot pomRoot = pomDocument.getRoot();
        
        InputStream isApi = this.getClass().getResourceAsStream("resources/jetty-api-pom.xml");
        XmlDocument apiDocument = new XmlDocument(isApi);
        XmlRoot rootApi = apiDocument.getRoot();
        rootApi.getTagMap().put("dependencies", Dependencies.class.getName());

        pomRoot.getDependencies().mergeAPI("standard", rootApi);
        pomRoot.commitUpdates();        
        assertEquals(2, pomRoot.getDependencies().getChilds().size() );
        pomDocument.save(Paths.get("d:/0temp"), "jetty-with-api.xml");
    }

}
