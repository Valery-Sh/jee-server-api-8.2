/**
 * This file is part of Jetty Server support in NetBeans IDE.
 *
 * Jetty Server support in NetBeans IDE is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * Jetty Server support in NetBeans IDE is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should see the GNU General Public License here:
 * <http://www.gnu.org/licenses/>.
 */
package org.netbeans.modules.jeeserver.jetty.project;


import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.modules.jeeserver.jetty.util.JettyConstants;
import org.netbeans.modules.jeeserver.jetty.util.Utils;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=ProjectFactory.class)
public class JettyProjectFactory implements ProjectFactory {
    
    //public static final String START_INI_FILE = JettyConstants.JETTYBASE_FOLDER + "/start.ini";
    //public static final String MODULES_FOLDER = JettyConstants.JETTYBASE_FOLDER + "/modules";
    //public static final String ETC_FOLDER = JettyConstants.JETTYBASE_FOLDER + "/etc-cm";

    public JettyProjectFactory() {
        
    }    
    public JettyProjectFactory(FileObject dir, ProjectState state) {
        
    }
    /**
     * Specifies when a project is a project, i.e., if the following
     * files are present in a folder:
     * <ul>
     *  <li>start.ini file;</li>
     *  <li>modules folder</li>
     *  <li>etc-cm folder</li>
     * </ul>
     * @param projectDirectory
     * @return 
     */
    @Override
    public boolean isProject(FileObject projectDirectory) {
        if ( JettyConstants.JETTYBASE_FOLDER.equals(projectDirectory.getNameExt()) ) {
            return false;
        }
        
/*        String MODULES_FOLDER = Utils.jettyBase(projectDirectory) + "/modules";
        String ETC_FOLDER = Utils.jettyBase(projectDirectory) + "/etc-cm";;
        
        return projectDirectory.getFileObject(Utils.jettyBase(projectDirectory)) != null 
                && projectDirectory.getFileObject(MODULES_FOLDER) != null
                && projectDirectory.getFileObject(ETC_FOLDER) != null;
*/        
        String startIni = Utils.jettyBase(projectDirectory) + "/start.ini";
        String httpIni = Utils.jettyBase(projectDirectory) + "/" + JettyConstants.JETTY_HTTP_INI;
        return projectDirectory.getFileObject(Utils.jettyBase(projectDirectory)) != null 
                && projectDirectory.getFileObject(startIni) != null
                && projectDirectory.getFileObject(httpIni) != null;
        
    }
    public static boolean isConfiguredForNetbeans(Project project) {
        if ( project.getProjectDirectory().getFileObject(JettyConstants.BUILD_XML) == null) {
            return false;
        }
        return true;
        
    }
    public static boolean hasProjectFiles(FileObject folder) {
        if ( folder == null ) {
            return false;
        }
        String startIni = "start.ini";
        String httpIni = JettyConstants.JETTY_HTTP_INI;
        return folder.getFileObject(startIni) != null && folder.getFileObject(httpIni) != null;
    }
    
    /**
     * Specifies when the project will be opened, i.e., if the project exists.
     * @param dir
     * @param state
     * @return
     * @throws IOException 
     */
    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        return isProject(dir) ? new JettyProject(dir, state) : null;
    }

    @Override
    public void saveProject(final Project project) throws IOException, ClassCastException {
        // leave unimplemented for the moment
    }

}