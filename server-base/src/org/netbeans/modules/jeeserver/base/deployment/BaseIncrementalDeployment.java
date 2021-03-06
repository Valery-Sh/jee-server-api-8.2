/**
 * This file is part of Base JEE Server support in NetBeans IDE.
 *
 * Base JEE Server support in NetBeans IDE is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * Base JEE Server support in NetBeans IDE is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should see the GNU General Public License here:
 * <http://www.gnu.org/licenses/>.
 */
package org.netbeans.modules.jeeserver.base.deployment;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.enterprise.deploy.spi.DeploymentManager;
import javax.enterprise.deploy.spi.Target;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.status.ProgressObject;
import org.netbeans.modules.jeeserver.base.deployment.progress.BaseDeployProgressObject;
import org.netbeans.modules.jeeserver.base.deployment.progress.BaseIncrementalProgressObject;
import org.netbeans.modules.j2ee.deployment.devmodules.api.InstanceRemovedException;
import org.netbeans.modules.j2ee.deployment.devmodules.api.J2eeModule;
import org.netbeans.modules.j2ee.deployment.plugins.api.AppChangeDescriptor;
import org.netbeans.modules.j2ee.deployment.plugins.api.DeploymentChangeDescriptor;
import org.netbeans.modules.j2ee.deployment.plugins.spi.DeploymentContext;
import org.netbeans.modules.j2ee.deployment.plugins.spi.IncrementalDeployment;
import org.netbeans.modules.j2ee.deployment.plugins.spi.IncrementalDeployment2;
import org.netbeans.modules.j2ee.deployment.plugins.spi.config.ModuleConfiguration;
import org.netbeans.modules.jeeserver.base.deployment.utils.BaseUtil;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author V. Shyshkin
 */
public class BaseIncrementalDeployment extends IncrementalDeployment implements IncrementalDeployment2 {

    private final BaseDeploymentManager manager;
    private final boolean deployOnSaveSupported;

    /**
     * Creates a new instance of BaseIncrementalDeployment
     *
     * @param manager
     */
    public BaseIncrementalDeployment(DeploymentManager manager) {
        this.manager = (BaseDeploymentManager) manager;
        deployOnSaveSupported = true;
//        BaseUtil.out("BaseIncrementalDeployment CONSTRUCTOR");
    }

    @Override
    public boolean canFileDeploy(Target target, J2eeModule j2eeModule) {
//        BaseUtil.out("BaseIncrementalDeployment canFileDeploy j2eeModule = " + j2eeModule.getUrl() + "; TYPE= " + j2eeModule.getType().equals(J2eeModule.Type.WAR));
        
        return j2eeModule.getType().equals(J2eeModule.Type.WAR);
    }

    protected String execServerCommand(String cmd) {
        if (!manager.pingServer()) {
//        BaseUtil.out(" BaseIncrementalDeployment execServerCommand cmd=" + cmd + "; snot ping server ");
            
            return null;
        }

//        BaseUtil.out("BaseIncrementalDeployment execServerCommand cmd=" + cmd);
        return manager.getSpecifics().execCommand(manager, cmd);
    }

    @Override
    public synchronized File getDirectoryForModule(TargetModuleID module) {
//        BaseUtil.out(" BaseIncrementalDeployment getDirectoryForModule module=" + module);
        //if ( true ) return null;
        if (module == null) {
            return null;
        }
        if (module instanceof BaseTargetModuleID) {
            String cmd = BaseUtil.createCommand((BaseTargetModuleID) module, "getcopydir");
            String dir = execServerCommand(cmd);
//BaseUtils.out(" getDirectoryForModule dir=" + dir);                            
            if (dir != null && !dir.equals("inplace")) {
//BaseUtils.out(" getDirectoryForModule dir=" + dir);                
                return new File(dir);
            }
        }
        return null;
    }

    @Override
    public File getDirectoryForNewApplication(Target target, J2eeModule module, ModuleConfiguration configuration) {

        if (module != null && module.getType().equals(J2eeModule.Type.WAR)) {
            return null;
        }
        throw new IllegalArgumentException("ModuleType:" + module == null ? null : module.getModuleType() + " Configuration:" + configuration); //NOI18N
    }

    @Override
    public java.io.File getDirectoryForNewModule(java.io.File appDir, String uri, J2eeModule module, ModuleConfiguration configuration) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProgressObject initialDeploy(Target target, DeploymentContext context) {
//        BaseUtil.out(" initialDeploy");
//        BaseUtil.out(" BaseIncrementalDeployment initialDeploy dcontext" + context);
        
        BaseIncrementalProgressObject deployer = new BaseIncrementalProgressObject(manager);
//        BaseUtil.out(" BaseIncrementalDeployment before deployer.initialDeploy dcontext" + context);
        
        return deployer.initialDeploy(target, context);
    }

    /**
     * Do I need it ?
     *
     * @param module
     */
    @Override
    public void notifyDeployment(TargetModuleID module) {
    }

    protected String getContextPath(File file) {
        String cp = null;
        Path filePath = file.toPath();

        String[] paths = manager.getSpecifics().getSupportedContextPaths();
        for (String p : paths) {
            if (filePath.endsWith(Paths.get(p))) {
                FileObject fo = FileUtil.toFileObject(file);
                manager.getSpecifics().getContextProperties(fo);
                cp = manager.getSpecifics().getContextProperties(fo)
                        .getProperty("contextPath");
                break;
            }
        }
        return cp;
    }

    @Override
    public ProgressObject deployOnSave(TargetModuleID module, DeploymentChangeDescriptor desc) {
        BaseIncrementalProgressObject deployer = new BaseIncrementalProgressObject(manager);

        AppChangeDescriptor changes = desc;
        FileObject projDir = FileUtil.toFileObject(new File(((BaseTargetModuleID) module).getProjectDir()));
        //J2eeModule m = BaseUtils.getJ2eeModule(manager.getServerProject());

        File[] files = changes.getChangedFiles();
        String cp = null;
        for (File f : files) {
            cp = getContextPath(f);
            if (cp != null) {
                break;
            }
        }

        if (changes.descriptorChanged() || changes.serverDescriptorChanged() || changes.classesChanged()) {
            if (changes.serverDescriptorChanged()) {
                BaseUtil.out("1 BaseIncrementalDeployment: incrementalDeploy 3 cp=" + ((BaseTargetModuleID) module).getContextPath() + "module.projDir=" + ((BaseTargetModuleID) module).getProjectDir());
                BaseUtil.out("----- usually when contexPath changed and Save button clicked");
                BaseUtil.out("----- we must undeploy old web app and deploy it again");
                BaseUtil.out("-------------------------------------------------------");

                new BaseDeployProgressObject(manager).undeploy((BaseTargetModuleID) module, projDir);
                deployer.deploy((BaseTargetModuleID) module);
                ((BaseTargetModuleID) module).getContextPath();
            } else if (cp != null) {
                BaseUtil.out("BaseIncrementalDeployment: incrementalDeploy");
                BaseUtil.out("----- usually when contextpath changed and Save button clicked");
                BaseUtil.out("----- we must stop web and then start it again");
                BaseUtil.out("----- Old cp = " + ((BaseTargetModuleID) module).getContextPath() + ". New cp =" + cp);
                BaseUtil.out("-------------------------------------------------------");
                // Так было до 7.8.15
                BaseTargetModuleID newModule = createTargetModuleId(projDir, cp);
                new BaseDeployProgressObject(manager).redeploy((BaseTargetModuleID) module, newModule);
                //deployer.start((BaseTargetModuleID) module);                
                //new BaseIncrementalProgressObject(manager).stop((BaseTargetModuleID) module);
                //deployer.stop((BaseTargetModuleID) module);
                deployer.start(newModule);
            } else if (changes.descriptorChanged()) {
                BaseUtil.out("BaseIncrementalDeployment: incrementalDeploy");
                BaseUtil.out("----- usually when web.xml changed and Save button clicked");
                BaseUtil.out("----- we must stop web and then start it again");
                BaseUtil.out("-------------------------------------------------------");
                // Так было до 7.8.15
                new BaseDeployProgressObject(manager).redeploy((BaseTargetModuleID) module);
                //deployer.start((BaseTargetModuleID) module);                
                //new BaseIncrementalProgressObject(manager).stop((BaseTargetModuleID) module);
                //deployer.stop((BaseTargetModuleID) module);
                deployer.start((BaseTargetModuleID) module);
                //new BaseDeployProgressObject(manager).undeploy((BaseTargetModuleID) module, projDir);
            } else {
                BaseUtil.out("ESIncrementalDeployment: incrementalDeploy 5 module.projDir=" + ((BaseTargetModuleID) module).getProjectDir());
                BaseUtil.out("----- usually when .java source code changed and Save button clicked");
                BaseUtil.out("----- we must redeploy web. For jetty it's not time consuming ");
                BaseUtil.out("-------------------------------------------------------");
                BaseUtil.out("SOURCE FILES CHANGES -------------------------------------------------------");
                //new BaseDeployProgressObject(manager).undeploy((BaseTargetModuleID) module, projDir);
                //new BaseDeployProgressObject(manager).deploy((BaseTargetModuleID) module);                
                new BaseDeployProgressObject(manager).redeploy((BaseTargetModuleID) module);
                BaseUtil.out("BaseIncrementalDeployment BaseIncrementalDeployment.REDEPLOY");
                deployer.start((BaseTargetModuleID) module);

//                deployer.redeploy((BaseTargetModuleID) module);
            }
            return deployer;
        } else {
            BaseUtil.out("*********** ESIncrementalDeployment: incrementalDeploy 6 module.class=" + module.getClass().getName());
            BaseUtil.out("----- usually when html or jsp content changed");
            BaseUtil.out("----- we just make DUMMY deploy (no server call)");
            return deployer.deploy((BaseTargetModuleID) module, true);
        }
    }

    protected BaseTargetModuleID createTargetModuleId(FileObject projDir, String contextPath) {
        BaseTarget target = manager.getDefaultTarget();
        return BaseTargetModuleID.getInstance(manager, target, contextPath, projDir.getPath());

    }

    public ProgressObject deployOnSave_OLD(TargetModuleID module, DeploymentChangeDescriptor desc) {
        BaseIncrementalProgressObject deployer = new BaseIncrementalProgressObject(manager);

        AppChangeDescriptor changes = desc;
        FileObject projDir = FileUtil.toFileObject(new File(((BaseTargetModuleID) module).getProjectDir()));
        File[] files = changes.getChangedFiles();
        for (File f : files) {
            BaseUtil.out("BaseIncrementalDeployment: fileChanged=" + ((BaseTargetModuleID) module).getContextPath() + "module.projDir=" + f.getPath());
        }
        if (changes.descriptorChanged() || changes.serverDescriptorChanged() || changes.classesChanged()) {
            if (changes.serverDescriptorChanged()) {
                BaseUtil.out("1 ESIncrementalDeployment: incrementalDeploy 3 cp=" + ((BaseTargetModuleID) module).getContextPath() + "module.projDir=" + ((BaseTargetModuleID) module).getProjectDir());
                BaseUtil.out("----- usually when contexPath changed and Save button clicked");
                BaseUtil.out("----- we must undeploy old web app and deploy it again");
                BaseUtil.out("-------------------------------------------------------");

                new BaseDeployProgressObject(manager).undeploy((BaseTargetModuleID) module, projDir);
                deployer.deploy((BaseTargetModuleID) module);
            } else if (changes.descriptorChanged()) {
                BaseUtil.out("2 ESIncrementalDeployment: incrementalDeploy 4");
                BaseUtil.out("----- usually when web.xml changed and Save button clicked");
                BaseUtil.out("----- we must stop web and then start it again");
                BaseUtil.out("-------------------------------------------------------");
                // Так было до 7.8.15
                new BaseIncrementalProgressObject(manager).stop((BaseTargetModuleID) module);
                deployer.start((BaseTargetModuleID) module);
            } else {
                BaseUtil.out("ESIncrementalDeployment: incrementalDeploy 5 module.projDir=" + ((BaseTargetModuleID) module).getProjectDir());
                BaseUtil.out("----- usually when .java source code changed and Save button clicked");
                BaseUtil.out("----- we must redeploy web. For jetty it's not time consuming ");
                BaseUtil.out("-------------------------------------------------------");
                BaseUtil.out("SOURCE FILES CHANGES -------------------------------------------------------");
                //new BaseDeployProgressObject(manager).undeploy((BaseTargetModuleID) module, projDir);
                //new BaseDeployProgressObject(manager).deploy((BaseTargetModuleID) module);                
                new BaseDeployProgressObject(manager).redeploy((BaseTargetModuleID) module);
                deployer.start((BaseTargetModuleID) module);

//                deployer.redeploy((BaseTargetModuleID) module);
            }
            return deployer;
        } else {
            BaseUtil.out("*********** ESIncrementalDeployment: incrementalDeploy 6 module.class=" + module.getClass().getName());
            BaseUtil.out("----- usually when html or jsp content changed");
            BaseUtil.out("----- we just make DUMMY deploy (no server call)");
            return deployer.deploy((BaseTargetModuleID) module, true);
        }
    }

    /**
     * Returns <code>true</code> when the deploy on save is supported by the
     * server for the given module.
     *
     * @param module module representing the application
     * @return <code>true</code> when the deploy on save is supported by the
     * server for the given module
     * @throws InstanceRemovedException if the instance is not available anymore
     * @since 1.49
     */
    public boolean isDeployOnSaveSupported(J2eeModule module) throws InstanceRemovedException {
        return deployOnSaveSupported;
    }

    @Override
    public boolean isDeployOnSaveSupported() {
        return deployOnSaveSupported;
    }

    @Override
    public ProgressObject initialDeploy(Target target, J2eeModule app, ModuleConfiguration configuration, File dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProgressObject incrementalDeploy(TargetModuleID module, AppChangeDescriptor changes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProgressObject incrementalDeploy(TargetModuleID module, DeploymentContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//class
