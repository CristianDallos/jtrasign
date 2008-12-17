/*
 * JTrafficView.java
 */

package jtraffic;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class JTrafficView extends FrameView {

    public JTrafficView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = JTrafficApp.getApplication().getMainFrame();
            aboutBox = new JTrafficAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        JTrafficApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tbBarraHerr = new javax.swing.JToolBar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        spImagenOriginal = new javax.swing.JScrollPane();
        panelImagenOriginal = new javax.swing.JPanel();
        lbImagenOriginal = new javax.swing.JLabel();
        lbTextoImagenOriginal = new javax.swing.JLabel();
        spPaso1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        spPaso2 = new javax.swing.JScrollPane();
        spPaso3 = new javax.swing.JScrollPane();
        spPaso4 = new javax.swing.JScrollPane();
        spPaso5 = new javax.swing.JScrollPane();
        spPaso6 = new javax.swing.JScrollPane();
        spResultados = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        miAbrir = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        tbBarraHerr.setFloatable(false);
        tbBarraHerr.setRollover(true);
        tbBarraHerr.setName("tbBarraHerr"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        spImagenOriginal.setName("spImagenOriginal"); // NOI18N

        panelImagenOriginal.setName("panelImagenOriginal"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(jtraffic.JTrafficApp.class).getContext().getResourceMap(JTrafficView.class);
        lbImagenOriginal.setText(resourceMap.getString("lbImagenOriginal.text")); // NOI18N
        lbImagenOriginal.setName("lbImagenOriginal"); // NOI18N

        lbTextoImagenOriginal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTextoImagenOriginal.setText(resourceMap.getString("lbTextoImagenOriginal.text")); // NOI18N
        lbTextoImagenOriginal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbTextoImagenOriginal.setName("lbTextoImagenOriginal"); // NOI18N

        javax.swing.GroupLayout panelImagenOriginalLayout = new javax.swing.GroupLayout(panelImagenOriginal);
        panelImagenOriginal.setLayout(panelImagenOriginalLayout);
        panelImagenOriginalLayout.setHorizontalGroup(
            panelImagenOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenOriginalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImagenOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbImagenOriginal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
                    .addComponent(lbTextoImagenOriginal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelImagenOriginalLayout.setVerticalGroup(
            panelImagenOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenOriginalLayout.createSequentialGroup()
                .addComponent(lbImagenOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbTextoImagenOriginal)
                .addContainerGap())
        );

        spImagenOriginal.setViewportView(panelImagenOriginal);

        jTabbedPane1.addTab(resourceMap.getString("spImagenOriginal.TabConstraints.tabTitle"), spImagenOriginal); // NOI18N

        spPaso1.setName("spPaso1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        spPaso1.setViewportView(jPanel1);

        jTabbedPane1.addTab(resourceMap.getString("spPaso1.TabConstraints.tabTitle"), spPaso1); // NOI18N

        spPaso2.setName("spPaso2"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spPaso2.TabConstraints.tabTitle"), spPaso2); // NOI18N

        spPaso3.setName("spPaso3"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spPaso3.TabConstraints.tabTitle"), spPaso3); // NOI18N

        spPaso4.setName("spPaso4"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spPaso4.TabConstraints.tabTitle"), spPaso4); // NOI18N

        spPaso5.setName("spPaso5"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spPaso5.TabConstraints.tabTitle"), spPaso5); // NOI18N

        spPaso6.setName("spPaso6"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spPaso6.TabConstraints.tabTitle"), spPaso6); // NOI18N

        spResultados.setName("spResultados"); // NOI18N
        jTabbedPane1.addTab(resourceMap.getString("spResultados.TabConstraints.tabTitle"), spResultados); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbBarraHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(tbBarraHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        miAbrir.setText(resourceMap.getString("miAbrir.text")); // NOI18N
        miAbrir.setName("miAbrir"); // NOI18N
        miAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAbrirActionPerformed(evt);
            }
        });
        fileMenu.add(miAbrir);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(jtraffic.JTrafficApp.class).getContext().getActionMap(JTrafficView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 662, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void miAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAbrirActionPerformed
        JFileChooser abrirFichero = new JFileChooser();
        abrirFichero.showOpenDialog(this.getComponent());
        File fichero = abrirFichero.getSelectedFile();

        if(fichero != null){
            try{
                BufferedImage imagenOriginal = ImageIO.read(fichero);
                 ImageIcon imagen = new ImageIcon(
                        imagenOriginal.getScaledInstance(spImagenOriginal.getWidth(),
                                spImagenOriginal.getHeight(), Image.SCALE_DEFAULT));
                lbImagenOriginal.setIcon(imagen);
            } catch (IOException ex) {
                Logger.getLogger(JTrafficView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_miAbrirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbImagenOriginal;
    private javax.swing.JLabel lbTextoImagenOriginal;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miAbrir;
    private javax.swing.JPanel panelImagenOriginal;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane spImagenOriginal;
    private javax.swing.JScrollPane spPaso1;
    private javax.swing.JScrollPane spPaso2;
    private javax.swing.JScrollPane spPaso3;
    private javax.swing.JScrollPane spPaso4;
    private javax.swing.JScrollPane spPaso5;
    private javax.swing.JScrollPane spPaso6;
    private javax.swing.JScrollPane spResultados;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JToolBar tbBarraHerr;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}