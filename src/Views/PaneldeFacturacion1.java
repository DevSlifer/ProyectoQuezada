/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author 1000g
 */
public class PaneldeFacturacion1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form PaneldeFacturacion1
     */
    public PaneldeFacturacion1() {
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

        jPanel9 = new javax.swing.JPanel();
        txtfacturacioncargosadicionales = new javax.swing.JTextField();
        txtpanelfacturacioncedula = new javax.swing.JTextField();
        btnpanelfacturacionguardar = new javax.swing.JButton();
        btnpanelfacturacioneliminar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabledatos = new javax.swing.JTable();
        txtpanelfacturacionfechainicio = new javax.swing.JTextField();
        btnpanelfacturacionactualizar = new javax.swing.JButton();
        txtpanelfacturacionfechafin = new javax.swing.JTextField();
        btnpanelfacturacionbuscar = new javax.swing.JButton();
        txtpaneldefacturacionfechadepago = new javax.swing.JTextField();

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        txtfacturacioncargosadicionales.setBackground(new java.awt.Color(255, 255, 255));
        txtfacturacioncargosadicionales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargos Adicionales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 18), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtfacturacioncargosadicionales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfacturacioncargosadicionalestxtcargosadicionalesActionPerformed(evt);
            }
        });

        txtpanelfacturacioncedula.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelfacturacioncedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cedula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 18), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelfacturacioncedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelfacturacioncedulatxtnombreActionPerformed(evt);
            }
        });

        btnpanelfacturacionguardar.setBackground(new java.awt.Color(0, 0, 0));
        btnpanelfacturacionguardar.setFont(new java.awt.Font("Kalam", 0, 18)); // NOI18N
        btnpanelfacturacionguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnpanelfacturacionguardar.setText("Guardar");
        btnpanelfacturacionguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpanelfacturacionguardarbtnagregarActionPerformed(evt);
            }
        });

        btnpanelfacturacioneliminar.setBackground(new java.awt.Color(0, 0, 0));
        btnpanelfacturacioneliminar.setFont(new java.awt.Font("Kalam", 0, 18)); // NOI18N
        btnpanelfacturacioneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnpanelfacturacioneliminar.setText("Eliminar");
        btnpanelfacturacioneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpanelfacturacioneliminarbtnactualizarActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(14, 20, 155));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255)))); // NOI18N
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));

        jtabledatos.setBackground(new java.awt.Color(255, 255, 255));
        jtabledatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 0, 0))); // NOI18N
        jtabledatos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtabledatos.setForeground(new java.awt.Color(0, 0, 0));
        jtabledatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Cedula", "Monto", "Cargos Adicionales", "Fecha de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabledatos.setGridColor(new java.awt.Color(0, 0, 0));
        jtabledatos.setSelectionForeground(new java.awt.Color(255, 0, 0));
        jtabledatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabledatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtabledatos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        txtpanelfacturacionfechainicio.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelfacturacionfechainicio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de inicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 18), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelfacturacionfechainicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelfacturacionfechainicioActionPerformed(evt);
            }
        });

        btnpanelfacturacionactualizar.setBackground(new java.awt.Color(0, 0, 0));
        btnpanelfacturacionactualizar.setFont(new java.awt.Font("Kalam", 0, 18)); // NOI18N
        btnpanelfacturacionactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnpanelfacturacionactualizar.setText("Actualizar");
        btnpanelfacturacionactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpanelfacturacionactualizarbtnactualizarActionPerformed(evt);
            }
        });

        txtpanelfacturacionfechafin.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelfacturacionfechafin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Fin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 18), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtpanelfacturacionfechafin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpanelfacturacionfechafinActionPerformed(evt);
            }
        });

        btnpanelfacturacionbuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnpanelfacturacionbuscar.setFont(new java.awt.Font("Kalam", 0, 18)); // NOI18N
        btnpanelfacturacionbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnpanelfacturacionbuscar.setText("Buscar");
        btnpanelfacturacionbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpanelfacturacionbuscarbtnactualizarActionPerformed(evt);
            }
        });

        txtpaneldefacturacionfechadepago.setBackground(new java.awt.Color(255, 255, 255));
        txtpaneldefacturacionfechadepago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 18), new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        txtpaneldefacturacionfechadepago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaneldefacturacionfechadepagotxtfechadepagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpanelfacturacionfechainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpanelfacturacioncedula, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpanelfacturacionfechafin, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaneldefacturacionfechadepago, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfacturacioncargosadicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnpanelfacturacionactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpanelfacturacionguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpanelfacturacioneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpanelfacturacionbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtpanelfacturacioncedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpanelfacturacionfechainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnpanelfacturacionguardar)
                        .addGap(29, 29, 29)
                        .addComponent(btnpanelfacturacionactualizar)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnpanelfacturacionbuscar)
                        .addGap(42, 42, 42)
                        .addComponent(btnpanelfacturacioneliminar))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtpanelfacturacionfechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtfacturacioncargosadicionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpaneldefacturacionfechadepago, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfacturacioncargosadicionalestxtcargosadicionalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfacturacioncargosadicionalestxtcargosadicionalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfacturacioncargosadicionalestxtcargosadicionalesActionPerformed

    private void txtpaneldefacturacionfechadepagotxtfechadepagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaneldefacturacionfechadepagotxtfechadepagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaneldefacturacionfechadepagotxtfechadepagoActionPerformed

    private void btnpanelfacturacionguardarbtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpanelfacturacionguardarbtnagregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpanelfacturacionguardarbtnagregarActionPerformed

    private void btnpanelfacturacioneliminarbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpanelfacturacioneliminarbtnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpanelfacturacioneliminarbtnactualizarActionPerformed

    private void jtabledatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabledatosMouseClicked

    }//GEN-LAST:event_jtabledatosMouseClicked

    private void txtpanelfacturacionfechainicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelfacturacionfechainicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelfacturacionfechainicioActionPerformed

    private void btnpanelfacturacionactualizarbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpanelfacturacionactualizarbtnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpanelfacturacionactualizarbtnactualizarActionPerformed

    private void txtpanelfacturacionfechafinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelfacturacionfechafinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelfacturacionfechafinActionPerformed

    private void btnpanelfacturacionbuscarbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpanelfacturacionbuscarbtnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpanelfacturacionbuscarbtnactualizarActionPerformed

    private void txtpanelfacturacioncedulatxtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpanelfacturacioncedulatxtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpanelfacturacioncedulatxtnombreActionPerformed

    
    
    //Getter y setters de todos los componentes
    //Para que los controladores puedan acceder
    // a sus acciones y tener que poner lo componentes 
    //publicos
    public JButton getBtnpanelfacturacionactualizar() {
        return btnpanelfacturacionactualizar;
    }

    public void setBtnpanelfacturacionactualizar(JButton btnpanelfacturacionactualizar) {
        this.btnpanelfacturacionactualizar = btnpanelfacturacionactualizar;
    }

    public JButton getBtnpanelfacturacionbuscar() {
        return btnpanelfacturacionbuscar;
    }

    public void setBtnpanelfacturacionbuscar(JButton btnpanelfacturacionbuscar) {
        this.btnpanelfacturacionbuscar = btnpanelfacturacionbuscar;
    }

    public JButton getBtnpanelfacturacioneliminar() {
        return btnpanelfacturacioneliminar;
    }

    public void setBtnpanelfacturacioneliminar(JButton btnpanelfacturacioneliminar) {
        this.btnpanelfacturacioneliminar = btnpanelfacturacioneliminar;
    }

    public JButton getBtnpanelfacturacionguardar() {
        return btnpanelfacturacionguardar;
    }

    public void setBtnpanelfacturacionguardar(JButton btnpanelfacturacionguardar) {
        this.btnpanelfacturacionguardar = btnpanelfacturacionguardar;
    }

    public JTextField getTxtfacturacioncargosadicionales() {
        return txtfacturacioncargosadicionales;
    }

    public void setTxtfacturacioncargosadicionales(JTextField txtfacturacioncargosadicionales) {
        this.txtfacturacioncargosadicionales = txtfacturacioncargosadicionales;
    }

    public JTextField getTxtpaneldefacturacionfechadepago() {
        return txtpaneldefacturacionfechadepago;
    }

    public void setTxtpaneldefacturacionfechadepago(JTextField txtpaneldefacturacionfechadepago) {
        this.txtpaneldefacturacionfechadepago = txtpaneldefacturacionfechadepago;
    }

    public JTextField getTxtpanelfacturacionfechafin() {
        return txtpanelfacturacionfechafin;
    }

    public void setTxtpanelfacturacionfechafin(JTextField txtpanelfacturacionfechafin) {
        this.txtpanelfacturacionfechafin = txtpanelfacturacionfechafin;
    }

    public JTextField getTxtpanelfacturacionfechainicio() {
        return txtpanelfacturacionfechainicio;
    }

    public void setTxtpanelfacturacionfechainicio(JTextField txtpanelfacturacionfechainicio) {
        this.txtpanelfacturacionfechainicio = txtpanelfacturacionfechainicio;
    }

    public JTable getJtabledatos() {
        return jtabledatos;
    }

    public void setJtabledatos(JTable jtabledatos) {
        this.jtabledatos = jtabledatos;
    }

    public JTextField getTxtpanelfacturacioncedula() {
        return txtpanelfacturacioncedula;
    }

    public void setTxtpanelfacturacioncedula(JTextField txtpanelfacturacioncedula) {
        this.txtpanelfacturacioncedula = txtpanelfacturacioncedula;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpanelfacturacionactualizar;
    private javax.swing.JButton btnpanelfacturacionbuscar;
    private javax.swing.JButton btnpanelfacturacioneliminar;
    private javax.swing.JButton btnpanelfacturacionguardar;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtabledatos;
    private javax.swing.JTextField txtfacturacioncargosadicionales;
    private javax.swing.JTextField txtpaneldefacturacionfechadepago;
    private javax.swing.JTextField txtpanelfacturacioncedula;
    private javax.swing.JTextField txtpanelfacturacionfechafin;
    private javax.swing.JTextField txtpanelfacturacionfechainicio;
    // End of variables declaration//GEN-END:variables
}
