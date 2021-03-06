package org.netbeans.modules.jeeserver.base.deployment.xml.pom;

import org.netbeans.modules.jeeserver.base.deployment.xml.AbstractXmlTextElement;
import org.w3c.dom.Element;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlCompoundElement;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlElement;

/**
 *
 * @author Valery Shyshkin
 */
public class DependencyArtifact extends AbstractXmlTextElement {
    
    public DependencyArtifact(String tagName) {
        super(tagName, null, null);
    }

    protected DependencyArtifact(Element element, XmlCompoundElement parent) {
        super(element.getTagName(), element, parent);
    }

    protected DependencyArtifact(String tagName, XmlCompoundElement parent) {
        super(tagName, null, parent);
    }
    @Override
    public boolean weakEquals(Object other) {
        if ( ! super.weakEquals(other)) {
            return false;
        }
        String s1 = getTextContent();
        String s2 = "jar";
        if ( "type".equals(getTagName() )) {
            s2 = ((DependencyArtifact)other).getTextContent();
        } 
        return XmlElement.equals(s1, s2);
    }
    
    public String resolveByProperties() {
        String r = getTextContent();
        PomRoot root = (PomRoot) PomRoot.findXmlRoot(this);
        if ( root == null) {
            return r;
        }
        PomProperties props = root.getProperties();
        if ( props == null ) {
            return r;
        }
        
        return r;
    }
     private String resolveVersionByProperties() {
         String r = getTextContent();
         return r;
     }
    
}
