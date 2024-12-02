package Controller;

import Model.EmpleadoModel;
import Model.DAOS.EmpleadoDAO;
import Views.PaneldeRegistros;
import Views.RegistrodeEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadoController implements ActionListener {

    private final EmpleadoDAO empleadoDAO;
    private final EmpleadoModel empleadoModel;
    private final RegistrodeEmpleados registrodeEmpleados;
    private final PaneldeRegistros paneldeRegistros;

    //Inicializacion de los componentes
    public EmpleadoController(RegistrodeEmpleados registrodeEmpleados, PaneldeRegistros paneldeRegistros) throws SQLException, FileNotFoundException {
        this.empleadoDAO = new EmpleadoDAO();
        this.empleadoModel = new EmpleadoModel();
        this.registrodeEmpleados = registrodeEmpleados;
        this.paneldeRegistros = paneldeRegistros;
        registrodeEmpleados.getBtnregistroempleadosagregar().addActionListener(this);
        paneldeRegistros.getBtnregistroeliminar().addActionListener(this);
        paneldeRegistros.getBtnregistroactualizar().addActionListener(this);
        paneldeRegistros.getBtnregistrobuscar().addActionListener(this);
        listarEmpleados();
    }

    //Agregar empleados con todos sus botones
    public void agregar() {
        if (validarCampos(registrodeEmpleados) > 1) {
            try {
                empleadoModel.setNombre(registrodeEmpleados.getTxtregistroempleadosnombre().getText());
                empleadoModel.setApellido(registrodeEmpleados.getTxtregistroempleadosapellido().getText());
                empleadoModel.setCedula(registrodeEmpleados.getTxtregistroempleadoscedula().getText());
                empleadoModel.setTelefono(registrodeEmpleados.getTxtregistroempleadostelefono().getText());
                empleadoModel.setSalario(Double.parseDouble(registrodeEmpleados.getTxtregistroempleadossalario().getText()));

                empleadoDAO.insertarEmpleado(empleadoModel);
                JOptionPane.showMessageDialog(registrodeEmpleados, "Empleado agregado correctamente!", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
                listarEmpleados();
                limpiarCampos();
            } catch (SQLException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(registrodeEmpleados, "Error al agregar el empleado: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Ver todos los empleados
    public void listarEmpleados() throws SQLException, FileNotFoundException {
        DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblEmpleados().getModel();
        modelo.setRowCount(0);
        List<EmpleadoModel> empleados;
        empleados = empleadoDAO.verEmpleado(null);
        Object[] fila = new Object[5];
        for (EmpleadoModel empleado : empleados) {
            fila[0] = empleado.getNombre();
            fila[1] = empleado.getApellido();
            fila[2] = empleado.getCedula();
            fila[3] = empleado.getSalario();
            fila[4] = empleado.getTelefono();
            modelo.addRow(fila);
        }
        paneldeRegistros.getTblEmpleados().setModel(modelo);
    }

    //Eliminar empleado por cedula
    public void eliminarEmpleado(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese la cedula del empleado",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirmacion = JOptionPane.showConfirmDialog(paneldeRegistros,
                    "¿Está seguro que desea eliminar el empleado con cédula " + cedula + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                empleadoDAO.eliminarEmpleado(cedula);
                JOptionPane.showMessageDialog(paneldeRegistros, "Empleado eliminado correctamente!", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
                listarEmpleados();
            } else {
                JOptionPane.showMessageDialog(paneldeRegistros, "Operación cancelada por el usuario", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al eliminar el empleado: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Actualizar empleado por su cedula
    public void actualizarEmpleado(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese la cedula del empleado",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<EmpleadoModel> empleados = empleadoDAO.verEmpleado(cedula);
            if (!empleados.isEmpty()) {
                EmpleadoModel empleado = empleados.get(0);
                String nuevoNombre = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo nombre del empleado", empleado.getNombre());
                String nuevoApellido = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo apellido del empleado", empleado.getApellido());
                String nuevoTelefono = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo teléfono del empleado", empleado.getTelefono());
                String nuevoSalario = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo salario del empleado", empleado.getSalario());
                if (nuevoNombre != null && nuevoApellido != null && nuevoTelefono != null && nuevoSalario != null) {
                    empleado.setNombre(nuevoNombre);
                    empleado.setApellido(nuevoApellido);
                    empleado.setTelefono(nuevoTelefono);
                    empleado.setSalario(Double.parseDouble(nuevoSalario));
                    empleadoDAO.actualizarEmpleado(empleado);
                    JOptionPane.showMessageDialog(paneldeRegistros, "Empleado actualizado correctamente!", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
                    listarEmpleados();
                }

            }
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al actualizar el empleado: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Buscar empleado por su cedula
    public void buscarPorCedula(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese la cedula del empleado",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblEmpleados().getModel();
            modelo.setRowCount(0);
            List<EmpleadoModel> empleados = empleadoDAO.verEmpleado(cedula);
            Object[] fila = new Object[5];
            for (EmpleadoModel empleado : empleados) {
                fila[0] = empleado.getNombre();
                fila[1] = empleado.getApellido();
                fila[2] = empleado.getCedula();
                fila[3] = empleado.getSalario();
                fila[4] = empleado.getTelefono();
                modelo.addRow(fila);
            }
            paneldeRegistros.getTblEmpleados().setModel(modelo);
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al buscar el empleado: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            listarEmpleados();
        }
    }

    //validacion de campos
    private int validarCampos(RegistrodeEmpleados registrodeEmpleados) {
        int validacion = 1;
        if (registrodeEmpleados.getTxtregistroempleadosnombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeEmpleados, "El campo nombre no debe estar vacío!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeEmpleados.getTxtregistroempleadosnombre().requestFocus();
            validacion = 0;
        } else if (registrodeEmpleados.getTxtregistroempleadosapellido().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeEmpleados, "El campo apellido no debe estar vacío!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeEmpleados.getTxtregistroempleadosapellido().requestFocus();
            validacion = 0;
        } else if (registrodeEmpleados.getTxtregistroempleadoscedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeEmpleados, "El campo cédula no debe estar vacío!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeEmpleados.getTxtregistroempleadoscedula().requestFocus();
            validacion = 0;
        } else if (registrodeEmpleados.getTxtregistroempleadostelefono().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeEmpleados, "El campo teléfono no debe estar vacío!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeEmpleados.getTxtregistroempleadostelefono().requestFocus();
            validacion = 0;
        } else if (registrodeEmpleados.getTxtregistroempleadossalario().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeEmpleados, "El campo salario no debe estar vacío!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeEmpleados.getTxtregistroempleadossalario().requestFocus();
            validacion = 0;
        }
        return validacion;
    }
    //Limpieza de campos 
    private void limpiarCampos(){
        registrodeEmpleados.getTxtregistroempleadosnombre().setText("");
        registrodeEmpleados.getTxtregistroempleadosapellido().setText("");
        registrodeEmpleados.getTxtregistroempleadoscedula().setText("");
        registrodeEmpleados.getTxtregistroempleadostelefono().setText("");
        registrodeEmpleados.getTxtregistroempleadossalario().setText("");
        registrodeEmpleados.getTxtregistroempleadosnombre().requestFocus();
    }

    //Accion de cada boton con sus metodos
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrodeEmpleados.getBtnregistroempleadosagregar()) {
            agregar();
        } else if (e.getSource() == paneldeRegistros.getBtnregistroeliminar()) {
            String cedula = paneldeRegistros.getTxtpanelregistroempleado().getText();
            try {
                eliminarEmpleado(cedula);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == paneldeRegistros.getBtnregistroactualizar()) {
            String cedula = paneldeRegistros.getTxtpanelregistroempleado().getText();
            try {
                actualizarEmpleado(cedula);
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == paneldeRegistros.getBtnregistrobuscar()) {
            String cedula = paneldeRegistros.getTxtpanelregistroempleado().getText();
            try {
                buscarPorCedula(cedula);
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
