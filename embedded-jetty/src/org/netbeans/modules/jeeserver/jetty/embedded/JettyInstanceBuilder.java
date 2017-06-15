package org.netbeans.modules.jeeserver.jetty.embedded;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.modules.jeeserver.base.deployment.specifics.InstanceBuilder;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseConstants;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseUtil;
import org.netbeans.modules.jeeserver.base.embedded.project.wizard.ServerInstanceAntBuildExtender;
import org.netbeans.modules.jeeserver.base.embedded.EmbeddedInstanceBuilder;
import org.netbeans.modules.jeeserver.base.embedded.utils.SuiteConstants;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

/**
 *
 * @author V. Shyshkin
 */
public class JettyInstanceBuilder extends EmbeddedInstanceBuilder {

    private static final Logger LOG = Logger.getLogger(JettyInstanceBuilder.class.getName());

    @StaticResource
    public static final String ZIP_ANT_TEMPLATE_PATH = "org/netbeans/modules/jeeserver/jetty/embedded/resources/JettyEmbeddedAntTemplate.zip";//JettyServerInstanceProject.zip";    

    public JettyInstanceBuilder(Properties configProps, InstanceBuilder.Options options) {
        super(configProps, options);
    }

    @Override
    public Set instantiate() {
        Set set = new HashSet();
//BaseUtil.out("JettInstanceBuilder instantiate " + getOptions());                        
        try {
            if (getOptions().equals(InstanceBuilder.Options.NEW)) {
//BaseUtil.out("JettInstanceBuilder nvoke instantiateProjectDir");                
                
                instantiateProjectDir(set);
            } else {
                File dirF = FileUtil.normalizeFile((File) getWizardDescriptor().getProperty("projdir"));
//                String name = (String) getWizardDescriptor().getProperty("name");                
//                dirF = new File( dirF.getPath() + "/" + name);
                FileObject dir = FileUtil.toFileObject(dirF);
                Project p = BaseUtil.findOwnerProject(dir);
                set.add(p);
//BaseUtil.out("JettInstanceBuilder nvoke updateWithTemplates");                
                updateWithTemplates(set);
            }
            instantiateServerProperties(set);
            instantiateServerInstanceDir(set);

            if (!isMavenbased()) {
                ServerInstanceAntBuildExtender extender = new ServerInstanceAntBuildExtender(findProject(set));
                extender.enableExtender();
            }

            //modifyClasspath(set);

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return set;
    }

    @Override
    public InputStream getZipTemplateInputStream() {
        return getClass().getClassLoader().getResourceAsStream("/"
                + ZIP_ANT_TEMPLATE_PATH);
    }

    protected FileObject getSrcDir(Project p) {
//        BaseUtil.out("JettyInstanceBuilder.getSrcDir project = " + p);
//        if ( p != null ) {
//            BaseUtil.out("JettyInstanceBuilder.getSrcDir project.getDir = " + p.getProjectDirectory());
            
//        }
        FileObject fo = p.getProjectDirectory().getFileObject("src");
//        BaseUtil.out("JettyInstanceBuilder.getSrcDir fo = " + fo);
        
        return fo;
    }

/*    @Override
    protected FileObject getLibDir(Project p) {
        FileObject fo;
        fo = p.getProjectDirectory().getFileObject(SuiteConstants.ANT_LIB_PATH + "/ext");
        return fo;
    }
*/
    @Override
    public void updateWithTemplates(Set result) {
        Project proj = findProject(result);

        String classpackage = (String) getWizardDescriptor()
                .getProperty("package");

/*        FileObject libFolder = getLibDir(proj);

        if (libFolder == null) {
            createLib(proj);
        }
*/
        updateServerInstanceProperties(proj);
        
        if ( ! getOptions().equals(InstanceBuilder.Options.NEW) ) {
            // We create main method from template only for a new project
            return;
        }
        //
        // Plugin jar => we can create a class from template
        //
        DataObject template;
        DataFolder outputFolder;

        Map<String, Object> templateParams = new HashMap<>(1);
        try {

            FileObject srcFo = getSrcDir(proj);

            FileObject toDelete = srcFo.getFileObject("javaapplication0");
            if (toDelete != null) {
                toDelete.delete();
            }
            FileObject targetFo = srcFo;

            if (classpackage != null) {
                String path = classpackage.replace(".", "/");
                targetFo = FileUtil.createFolder(targetFo, path);

            } else {
                classpackage = "org.embedded.server";
                targetFo = srcFo.createFolder("org")
                        .createFolder("embedded")
                        .createFolder("server");
            }

            outputFolder = DataFolder.findFolder(targetFo);
//BaseUtil.out("JettInstanceBuilder outputFolder=" + outputFolder);
            template = DataObject.find(
                    FileUtil.getConfigFile("Templates/jetty9/JettyEmbeddedServer"));
//BaseUtil.out("JettInstanceBuilder template isNull=" + (template == null));
            
            templateParams.put("port", getWizardDescriptor().getProperty(BaseConstants.HTTP_PORT_PROP));
//BaseUtil.out("JettInstanceBuilder template port=" + getWizardDescriptor().getProperty(BaseConstants.HTTP_PORT_PROP));
            
            //templateParams.put("comStart", "");
            //templateParams.put("comEnd", "");
            templateParams.put("classpackage", classpackage);
//BaseUtil.out("JettInstanceBuilder classpackage=" + classpackage);
            
//            templateParams.put("command.manager.param", getCommandManagerJarTemplateName());

            template.createFromTemplate(
                    outputFolder,
                    "JettyEmbeddedServer.java",
                    templateParams);
            //setMainClass(projectDir);
        } catch (IOException ex) {
            BaseUtil.out("JettInstanceBuilder EXCEPTION " + ex.getMessage());
            LOG.log(Level.INFO, ex.getMessage()); //NOI18N
        }
    }

    public void createLib(Project project) {
        FileObject libFo = null;

        File libFolder;
        FileObject fo;
        libFolder = new File(project.getProjectDirectory().getPath() + "/lib/ext");
        fo = project.getProjectDirectory().getFileObject("lib/ext");

        if (fo == null) {
            try {
                libFo = FileUtil.createFolder(libFolder);
            } catch (IOException ex) {
                LOG.log(Level.INFO, ex.getMessage()); //NOI18N
            }
        }
        return;
    }
    
}