/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author 1000g
 */
public class PaneldeReservaciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form PaneldeReservaciones
     */
    public PaneldeReservaciones() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtpanelreservacionesmarca = new javax.swing.JTextField();
        txtpanelreservacionesfechadevolucion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpanelreservacionesaño = new javax.swing.JTextField();
        btnpanelreservacionesguardar = new javax.swing.JButton();
        txtpanelreservacionesapellido = new javax.swing.JTextField();
        txtpanelreservacionesfechaentrega = new javax.swing.JTextField();
        txtpanelreservacionesnombre = new javax.swing.JTextField();
        txtpanelreservacionescedula = new javax.swing.JTextField();
        txtpanelreservacionesmodelo = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255)), "", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255)), "", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N

        txtpanelreservacionesmarca.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesmarca.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Marca del vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesmarcaActionPerformed(evt);
            }
        });

        txtpanelreservacionesfechadevolucion.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesfechadevolucion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de Devolucion", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        txtpanelreservacionesfechadevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesfechadevolucionActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Kalam", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ingrese los datos de la reserva");

        txtpanelreservacionesaño.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesaño.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Año", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesañoActionPerformed(evt);
            }
        });

        btnpanelreservacionesguardar.setBackground(new java.awt.Color(0, 0, 0));
        btnpanelreservacionesguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnpanelreservacionesguardar.setText("Aceptar");
        btnpanelreservacionesguardar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnpanelreservacionesguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpanelreservacionesguardarActionPerformed(evt);
            }
        });

        txtpanelreservacionesapellido.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesapellido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesapellidoActionPerformed(evt);
            }
        });

        txtpanelreservacionesfechaentrega.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesfechaentrega.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de Entrega", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesfechaentrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesfechaentregaActionPerformed(evt);
            }
        });

        txtpanelreservacionesnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesnombre.setFont(new java.awt.Font("Kalam", 3, 14)); // NOI18N
        txtpanelreservacionesnombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesnombreActionPerformed(evt);
            }
        });

        txtpanelreservacionescedula.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionescedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cedula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionescedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionescedulaActionPerformed(evt);
            }
        });

        txtpanelreservacionesmodelo.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelreservacionesmodelo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modelo ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelreservacionesmodelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelreservacionesmodeloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpanelreservacionesapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpanelreservacionesnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpanelreservacionescedula, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpanelreservacionesmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtpanelreservacionesaño, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(txtpanelreservacionesfechaentrega)
                                .addComponent(txtpanelreservacionesmodelo))
                            .addComponent(txtpanelreservacionesfechadevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(btnpanelreservacionesguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpanelreservacionesnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpanelreservacionesmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpanelreservacionesapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpanelreservacionesfechaentrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpanelreservacionescedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpanelreservacionesfechadevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpanelreservacionesmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpanelreservacionesaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnpanelreservacionesguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpanelreservacionesmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesmarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesmarcaActionPerformed

    private void txtpanelreservacionesfechadevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesfechadevolucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesfechadevolucionActionPerformed

    private void btnpanelreservacionesguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpanelreservacionesguardarActionPerformed

    }//GEN-LAST:event_btnpanelreservacionesguardarActionPerformed

    private void txtpanelreservacionesañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesañoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesañoActionPerformed

    private void txtpanelreservacionesapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesapellidoActionPerformed

    private void txtpanelreservacionesfechaentregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesfechaentregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesfechaentregaActionPerformed

    private void txtpanelreservacionesmodeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesmodeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesmodeloActionPerformed

    private void txtpanelreservacionesnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionesnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionesnombreActionPerformed

    private void txtpanelreservacionescedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelreservacionescedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelreservacionescedulaActionPerformed

    public JButton getBtnpanelreservacionesguardar() {
        return btnpanelreservacionesguardar;
    }

    public void setBtnpanelreservacionesguardar(JButton btnpanelreservacionesguardar) {
        this.btnpanelreservacionesguardar = btnpanelreservacionesguardar;
    }

    public JTextField getTxtpanelreservacionesapellido() {
        return txtpanelreservacionesapellido;
    }

    public void setTxtpanelreservacionesapellido(JTextField txtpanelreservacionesapellido) {
        this.txtpanelreservacionesapellido = txtpanelreservacionesapellido;
    }

    public JTextField getTxtpanelreservacionesaño() {
        return txtpanelreservacionesaño;
    }

    public void setTxtpanelreservacionesaño(JTextField txtpanelreservacionesaño) {
        this.txtpanelreservacionesaño = txtpanelreservacionesaño;
    }

    public JTextField getTxtpanelreservacionescedula() {
        return txtpanelreservacionescedula;
    }

    public void setTxtpanelreservacionescedula(JTextField txtpanelreservacionescedula) {
        this.txtpanelreservacionescedula = txtpanelreservacionescedula;
    }

    public JTextField getTxtpanelreservacionesfechadevolucion() {
        return txtpanelreservacionesfechadevolucion;
    }

    public void setTxtpanelreservacionesfechadevolucion(JTextField txtpanelreservacionesfechadevolucion) {
        this.txtpanelreservacionesfechadevolucion = txtpanelreservacionesfechadevolucion;
    }

    public JTextField getTxtpanelreservacionesfechaentrega() {
        return txtpanelreservacionesfechaentrega;
    }

    public void setTxtpanelreservacionesfechaentrega(JTextField txtpanelreservacionesfechaentrega) {
        this.txtpanelreservacionesfechaentrega = txtpanelreservacionesfechaentrega;
    }

    public JTextField getTxtpanelreservacionesmarca() {
        return txtpanelreservacionesmarca;
    }

    public void setTxtpanelreservacionesmarca(JTextField txtpanelreservacionesmarca) {
        this.txtpanelreservacionesmarca = txtpanelreservacionesmarca;
    }

    public JTextField getTxtpanelreservacionesmodelo() {
        return txtpanelreservacionesmodelo;
    }

    public void setTxtpanelreservacionesmodelo(JTextField txtpanelreservacionesmodelo) {
        this.txtpanelreservacionesmodelo = txtpanelreservacionesmodelo;
    }

    public JTextField getTxtpanelreservacionesnombre() {
        return txtpanelreservacionesnombre;
    }

    public void setTxtpanelreservacionesnombre(JTextField txtpanelreservacionesnombre) {
        this.txtpanelreservacionesnombre = txtpanelreservacionesnombre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpanelreservacionesguardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtpanelreservacionesapellido;
    private javax.swing.JTextField txtpanelreservacionesaño;
    private javax.swing.JTextField txtpanelreservacionescedula;
    private javax.swing.JTextField txtpanelreservacionesfechadevolucion;
    private javax.swing.JTextField txtpanelreservacionesfechaentrega;
    private javax.swing.JTextField txtpanelreservacionesmarca;
    private javax.swing.JTextField txtpanelreservacionesmodelo;
    private javax.swing.JTextField txtpanelreservacionesnombre;
    // End of variables declaration//GEN-END:variables
}
