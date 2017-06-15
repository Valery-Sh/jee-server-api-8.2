/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.jeeserver.base.deployment.maven;

import javax.swing.JButton;
import javax.swing.JLabel;
import org.netbeans.api.project.Project;

/**
 *
 * @author Valery
 */
public class MainClassChooserPanelVisual2 extends javax.swing.JPanel {
    
    private static final String NO_MAIN_CLASS_FOUND = "No Main Class Found";
    
    private final JButton customizeButton;
    private final JButton cancelButton;
    private final JButton acceptButton;
    
    private Project serverProject;
    /**
     * Creates new form MainClassChooserPanelVisual2
     * @param customizeButton to customize
     * @param cancelButton to cancel
     * @param acceptButton to accept
     */
    public MainClassChooserPanelVisual2(JButton customizeButton,JButton cancelButton, JButton acceptButton) {
        initComponents();
        
        this.customizeButton = customizeButton;
        this.cancelButton = cancelButton;
        this.acceptButton = acceptButton;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }


    public JButton getAcceptButton() {
        return acceptButton;
    }

    public Project getServerProject() {
        return serverProject;
    }

    public void setServerProject(Project serverProject) {
        this.serverProject = serverProject;
    }

    public JButton getCustomizeButton() {
        return customizeButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        errorLabel = new javax.swing.JLabel();

        infoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/jeeserver/base/deployment/resources/info.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(infoLabel, " Could not find or load main class"); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(MainClassChooserPanelVisual2.class, "MainClassChooserPanelVisual2.jTextArea1.text")); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        errorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/netbeans/modules/jeeserver/base/deployment/resources/error.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, "  The class that you  specified doesn't exist                    "); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLabel)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables


}
