//Gerald Manuel Gomera (20240044)
package Views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import Controller.AdminController;
import Model.CarroModel;
import Model.ClienteModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1000g
 */
public class frmPanelAdmin extends javax.swing.JInternalFrame {

    private AdminController adminController;
    private static final Logger LOGGER = Logger.getLogger(frmPanelAdmin.class.getName());

    public frmPanelAdmin() throws InstantiationException, ClassNotFoundException {
        super("Panel de Administración", true, true, true, true);
        initComponents();
        configurarTablaCarros();
        configurarTablaClientes();
        this.adminController = new AdminController(new frmPanelCarros());
        configurarEventos();
        cargarDatosIniciales();
    }

    private void configurarTablaCarros() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Matrícula", "Marca", "Modelo", "Placa", "Año", "Kilometraje"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_matricula.setModel(modelo);
    }

    private void configurarTablaClientes() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Nombre", "Cédula", "Provincia", "Sector", "Calle", "N° Casa", "Teléfono"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_clientes.setModel(modelo);
    }

    private void configurarEventos() {
        // Evento de búsqueda
        btn_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtcedula.getText().trim();
                String matricula = txtmatricula1.getText().trim();
                try {
                    if (!cedula.isEmpty()) {
                        buscarCliente(cedula);
                    } else if (!matricula.isEmpty()) {
                        adminController.buscarCarroEnTabla(tbl_matricula, matricula);
                    } else {
                        JOptionPane.showMessageDialog(frmPanelAdmin.this, "Ingrese una cédula o matrícula para buscar");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(frmPanelAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPanelAdmin.this, "Error en la búsqueda: " + ex.getMessage());
                }
            }
        });

        // Evento de eliminación
        btn_eliminar.addActionListener(e -> {
            try {
                String matricula = txtmatricula1.getText().trim();
                if (!matricula.isEmpty()) {
                    adminController.eliminarCarro(matricula, tbl_matricula);
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un carro para eliminar");
                }
            } catch (Exception ex) {
                Logger.getLogger(frmPanelAdmin.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        });

        // Evento de actualización
        btn_actualizar.addActionListener(e -> {
            cargarDatosIniciales();
        });

        // Evento de eliminación
        btn_eliminar.addActionListener(e -> {
            try {
                String matricula = txtmatricula1.getText().trim();
                if (!matricula.isEmpty()) {
                    adminController.eliminarCarro(matricula, tbl_matricula);
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un carro para eliminar");
                }
            } catch (Exception ex) {
                Logger.getLogger(frmPanelAdmin.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        });

        // Evento de doble clic en tabla de carros
        tbl_matricula.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    mostrarDialogoEdicionCarro();
                }
            }
        });
    }

    private void cargarDatosIniciales() {
        adminController.cargarDatosCarrosEnTabla(tbl_matricula);
        cargarTablaClientes();
    }

    private void mostrarDialogoEdicionCarro() {
        int filaSeleccionada = tbl_matricula.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String matricula = tbl_matricula.getValueAt(filaSeleccionada, 1).toString();
            CarroModel carro = adminController.buscarCarroPorMatricula(matricula);
            if (carro != null) {
                // Crear y mostrar diálogo de edición
                JDialog dialogo = new JDialog((Frame) SwingUtilities.windowForComponent(this), "Editar Vehículo", true);
                // ... configuración del diálogo y campos de edición ...
                // Agregar campos para editar el carro
            }
        }
    }

    private void cargarTablaClientes() {
        DefaultTableModel modelo = (DefaultTableModel) tbl_clientes.getModel();
        modelo.setRowCount(0);

        List<ClienteModel> clientes = adminController.obtenerClientes();
        for (ClienteModel cliente : clientes) {
            modelo.addRow(new Object[]{
                cliente.getNombre(),
                cliente.getCedula(),
                cliente.getProvincia(),
                cliente.getSector(),
                cliente.getCalle(),
                cliente.getNumeroCasa(),
                cliente.getTelefono()
            });
        }
    }

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, FileNotFoundException, FileNotFoundException {
        String cedula = txtcedula.getText();
        ClienteModel cliente = null;
        cliente = adminController.buscarCliente(cedula);

        if (cliente != null) {
            DefaultTableModel model = (DefaultTableModel) tbl_clientes.getModel();
            model.setRowCount(0); // Limpia la tabla
            model.addRow(new Object[]{
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCedula(),
                cliente.getLicencia(),
                cliente.getTelefono(),
                cliente.getProvincia(),
                cliente.getSector(),
                cliente.getCalle(),
                cliente.getNumeroCasa()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        btn_buscar = new javax.swing.JButton();
        txtcedula = new javax.swing.JTextField();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_matricula = new javax.swing.JTable();
        txtmatricula1 = new javax.swing.JTextField();

        jPopupMenu2.setForeground(new java.awt.Color(20, 20, 20));

        jMenuItem2.setBackground(new java.awt.Color(20, 20, 20));
        jMenuItem2.setForeground(new java.awt.Color(50, 50, 50));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Imagenes/7347206 (2).png"))); // NOI18N
        jMenuItem2.setText("ELIMINAR");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Panel Admin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Panel de Registros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255)))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        tbl_clientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        tbl_clientes.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbl_clientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "Nombre", "Cedula", "Provincia", "Sector", "Calle", "Número de Casa", "Teléfonos"
                }
        ));
        tbl_clientes.setComponentPopupMenu(jPopupMenu2);
        tbl_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_clientes);

        btn_buscar.setForeground(new java.awt.Color(0, 0, 0));
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        txtcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Ingrese la Cedula", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcedulaActionPerformed(evt);
            }
        });

        btn_actualizar.setForeground(new java.awt.Color(0, 0, 0));
        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        btn_eliminar.setForeground(new java.awt.Color(0, 0, 0));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        tbl_matricula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        tbl_matricula.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbl_matricula.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "Matricula", "Marca", "Modelo", "N.º de Placa", "Año"
                }
        ));
        tbl_matricula.setComponentPopupMenu(jPopupMenu2);
        tbl_matricula.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_matriculaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_matricula);

        txtmatricula1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Ingrese la Matricula", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 0)))); // NOI18N
        txtmatricula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatricula1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtmatricula1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 660, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtmatricula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_clientesMouseClicked
        btn_buscar.setEnabled(false);
        btn_actualizar.setEnabled(true);
        int fila = this.tbl_clientes.getSelectedRow();

    }//GEN-LAST:event_tbl_clientesMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

        try {
            cargarTablaClientes();
            cargarTablaCarros();
            JOptionPane.showMessageDialog(this, "Tablas actualizadas exitosamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las tablas: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try {
            String cedula = txtcedula.getText().trim();
            String matricula = txtmatricula1.getText().trim();

            if (!cedula.isEmpty()) {
                buscarCliente(cedula);
            } else if (!matricula.isEmpty()) {
                buscarCarro(matricula);
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una cédula o matrícula para buscar");
            }
        } catch (Exception ex) {
            Logger.getLogger(frmPanelAdmin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        try {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este registro?");
            if (confirmar == JOptionPane.YES_OPTION) {
                String cedula = txtcedula.getText().trim();
                String matricula = txtmatricula1.getText().trim();

                if (!cedula.isEmpty()) {
                    adminController.eliminarCliente(cedula);
                    cargarTablaClientes();
                    JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente");
                } else if (!matricula.isEmpty()) {
                    adminController.eliminarCarro(matricula, tbl_matricula);
                    cargarTablaCarros();
                    JOptionPane.showMessageDialog(this, "Carro eliminado exitosamente");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txtmatricula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatricula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmatricula1ActionPerformed

    private void tbl_matriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_matriculaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_matriculaMouseClicked

    private void txtmatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedula1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new frmPanelAdmin().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_clientes;
    private javax.swing.JTable tbl_matricula;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtmatricula1;
    // End of variables declaration//GEN-END:variables

    private Component crearGestionClientesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Gestión de Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        btnAgregarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnAgregarCliente);

        return panel;
    }

    private void cargarTablaCarros() {
        DefaultTableModel modelo = (DefaultTableModel) tbl_matricula.getModel();
        modelo.setRowCount(0);

        List<CarroModel> carros = adminController.obtenerCarros();
        for (CarroModel carro : carros) {
            modelo.addRow(new Object[]{
                carro.getMatricula(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getNumPlaca(),
                carro.getAnio(),
                carro.getKilometraje()
            });
        }
    }

    private void buscarCarro(String matricula) throws SQLException, FileNotFoundException {
        adminController.buscarCarroEnTabla(matricula, tbl_matricula);
    }

    private void buscarCliente(String cedula) throws SQLException, FileNotFoundException {
        ClienteModel cliente = adminController.buscarCliente(cedula);
        if (cliente != null) {
            DefaultTableModel model = (DefaultTableModel) tbl_clientes.getModel();
            model.setRowCount(0);
            model.addRow(new Object[]{
                cliente.getNombre(),
                cliente.getCedula(),
                cliente.getProvincia(),
                cliente.getSector(),
                cliente.getCalle(),
                cliente.getNumeroCasa(),
                cliente.getTelefono()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }
    }
}
