/*
 * ImageDialog.java
 *
 * Created on 12 de noviembre de 2008, 13:03
 */

package jtraffic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author  juanmasp
 * @date 17/11/2008
 */
public class ImageDialog extends javax.swing.JDialog {
    
    private Image imagen;

    /** Creates new form ImageDialog */
    public ImageDialog(Image imagen, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.imagen = imagen;
        
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(jtraffic.JTrafficApp.class).getContext().getResourceMap(ImageDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    this.asignar_imagen();
}//GEN-LAST:event_formComponentResized

    public void show(){
        asignar_imagen();
        super.show();
    }
    
    private void asignar_imagen(){
        int width = this.getWidth() - 20;
        int height = this.getHeight() - 20;
        
        this.jLabel1.setIcon(
                new ImageIcon(
                    this.imagen.getScaledInstance(
                        width, 
                        height, 
                        Image.SCALE_DEFAULT)));
        /*
        this.setTitle(this.getTitle()
                + " Dimensiones: " + width + "x" + height);
         */
    }

    public void setTitle(String texto){
        BufferedImage aux = (BufferedImage) imagen;
        super.setTitle(texto + " - " + aux.getWidth() + "x" + aux.getHeight());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
