package org.netbeans.modules.jeeserver.base.deployment.xml.pom;

import org.netbeans.modules.jeeserver.base.deployment.xml.AbstractCompoundXmlElement;
import org.w3c.dom.Element;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlCompoundElement;
import org.netbeans.modules.jeeserver.base.deployment.xml.XmlTagMap;

/**
 * The class corresponds to the  tag named  "properties" of the {@code pom } 
 * document. 
 * The element is used as a child element of the pom root {@code project} element.
 *
 * @see Property
 * @see PomRoot
 * @author Valery Shyshkin
 */
public class PomProperties extends AbstractCompoundXmlElement {

    /**
     * Creates a new instance of the class with a {@code tagName} property value
     * equals to {@code "properties"}. 
     * Puts a single element to the{@code tagMap} with a 
     * key {@code "property"} and value {@code Property.class.getName()}
     *
     */
    public PomProperties() {
        super("properties", null, null);
        init();
    }

/*    public PomProperties(String tagName) {
        super("properties", null, null);
        init();
    }
*/
    protected PomProperties(Element element, XmlCompoundElement parent) {
        super("properties", element, parent);
        init();
    }

    protected PomProperties(XmlCompoundElement parent) {
        super("properties", null, parent);
        init();
    }

    private void init() {
        //XmlTagMap map = new XmlTagMap();
        //map.put("property", Property.class.getName());
        //setTagMap(map);
        getTagMap().setDefaultClass(Property.class.getName());
    }
    
    public Property getProperty(String propertyTagName) {
        Property p = (Property) findFirstElementByPath(propertyTagName);
        return p;
    }
}
