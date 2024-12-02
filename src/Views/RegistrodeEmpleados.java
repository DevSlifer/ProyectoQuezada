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
public class RegistrodeEmpleados extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistrodeEmpleados
     */
    public RegistrodeEmpleados() {
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
        txtregistroempleadoscedula = new javax.swing.JTextField();
        txtregistroempleadosnombre = new javax.swing.JTextField();
        txtregistroempleadossalario = new javax.swing.JTextField();
        btnregistroempleadosagregar = new javax.swing.JButton();
        txtregistroempleadostelefono = new javax.swing.JTextField();
        txtregistroempleadosapellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255)), "", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N

        txtregistroempleadoscedula.setBackground(new java.awt.Color(255, 255, 255));
        txtregistroempleadoscedula.setFont(new java.awt.Font("Kalam", 0, 14)); // NOI18N
        txtregistroempleadoscedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cedula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtregistroempleadoscedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistroempleadoscedulaActionPerformed(evt);
            }
        });

        txtregistroempleadosnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtregistroempleadosnombre.setFont(new java.awt.Font("Kalam", 0, 14)); // NOI18N
        txtregistroempleadosnombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        txtregistroempleadosnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistroempleadosnombreActionPerformed(evt);
            }
        });

        txtregistroempleadossalario.setBackground(new java.awt.Color(255, 255, 255));
        txtregistroempleadossalario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtregistroempleadossalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistroempleadossalarioActionPerformed(evt);
            }
        });

        btnregistroempleadosagregar.setBackground(new java.awt.Color(0, 0, 0));
        btnregistroempleadosagregar.setFont(new java.awt.Font("Kalam", 0, 14)); // NOI18N
        btnregistroempleadosagregar.setForeground(new java.awt.Color(255, 255, 255));
        btnregistroempleadosagregar.setText("Aceptar");
        btnregistroempleadosagregar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btnregistroempleadosagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistroempleadosagregarActionPerformed(evt);
            }
        });

        txtregistroempleadostelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtregistroempleadostelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefono", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtregistroempleadostelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistroempleadostelefonoActionPerformed(evt);
            }
        });

        txtregistroempleadosapellido.setBackground(new java.awt.Color(255, 255, 255));
        txtregistroempleadosapellido.setFont(new java.awt.Font("Kalam", 0, 14)); // NOI18N
        txtregistroempleadosapellido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Apellidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kalam", 3, 14), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtregistroempleadosapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistroempleadosapellidoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Kalam", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("    Ingrese los datos del empleado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtregistroempleadoscedula, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(txtregistroempleadosapellido)
                            .addComponent(txtregistroempleadosnombre))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtregistroempleadostelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtregistroempleadossalario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnregistroempleadosagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtregistroempleadossalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtregistroempleadosnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtregistroempleadostelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtregistroempleadosapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtregistroempleadoscedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregistroempleadosagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtregistroempleadoscedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistroempleadoscedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroempleadoscedulaActionPerformed

    private void txtregistroempleadosnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistroempleadosnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroempleadosnombreActionPerformed

    private void txtregistroempleadossalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistroempleadossalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroempleadossalarioActionPerformed

    private void btnregistroempleadosagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistroempleadosagregarActionPerformed

    }//GEN-LAST:event_btnregistroempleadosagregarActionPerformed

    private void txtregistroempleadostelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistroempleadostelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroempleadostelefonoActionPerformed

    private void txtregistroempleadosapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistroempleadosapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroempleadosapellidoActionPerformed

    //Getter y setters de todos los componentes
    //Para que los controladores puedan acceder
    // a sus acciones y tener que poner lo componentes 
    //publicos
    public JButton getBtnregistroempleadosagregar() {
        return btnregistroempleadosagregar;
    }

    public void setBtnregistroempleadosagregar(JButton btnregistroempleadosagregar) {
        this.btnregistroempleadosagregar = btnregistroempleadosagregar;
    }

    public JTextField getTxtregistroempleadosapellido() {
        return txtregistroempleadosapellido;
    }

    public void setTxtregistroempleadosapellido(JTextField txtregistroempleadosapellido) {
        this.txtregistroempleadosapellido = txtregistroempleadosapellido;
    }

    public JTextField getTxtregistroempleadoscedula() {
        return txtregistroempleadoscedula;
    }

    public void setTxtregistroempleadoscedula(JTextField txtregistroempleadoscedula) {
        this.txtregistroempleadoscedula = txtregistroempleadoscedula;
    }

    public JTextField getTxtregistroempleadosnombre() {
        return txtregistroempleadosnombre;
    }

    public void setTxtregistroempleadosnombre(JTextField txtregistroempleadosnombre) {
        this.txtregistroempleadosnombre = txtregistroempleadosnombre;
    }

    public JTextField getTxtregistroempleadossalario() {
        return txtregistroempleadossalario;
    }

    public void setTxtregistroempleadossalario(JTextField txtregistroempleadossalario) {
        this.txtregistroempleadossalario = txtregistroempleadossalario;
    }

    public JTextField getTxtregistroempleadostelefono() {
        return txtregistroempleadostelefono;
    }

    public void setTxtregistroempleadostelefono(JTextField txtregistroempleadostelefono) {
        this.txtregistroempleadostelefono = txtregistroempleadostelefono;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnregistroempleadosagregar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtregistroempleadosapellido;
    private javax.swing.JTextField txtregistroempleadoscedula;
    private javax.swing.JTextField txtregistroempleadosnombre;
    private javax.swing.JTextField txtregistroempleadossalario;
    private javax.swing.JTextField txtregistroempleadostelefono;
    // End of variables declaration//GEN-END:variables
}
