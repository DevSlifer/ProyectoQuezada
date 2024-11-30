package Controller;

import Model.EmpleadoModel;
import Model.DAOS.EmpleadoDAO;
import Views.PaneldeRegistros;
import Views.RegistrodeEmpleados;
import Views.frmDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadoController implements ActionListener {

    private final EmpleadoDAO empleadoDAO;
    private final EmpleadoModel empleadoModel;
    private final RegistrodeEmpleados registrodeEmpleados;
    private final PaneldeRegistros paneldeRegistros;

    public EmpleadoController(RegistrodeEmpleados registrodeEmpleados, PaneldeRegistros paneldeRegistros) throws SQLException, FileNotFoundException {
        this.empleadoDAO = new EmpleadoDAO();
        this.empleadoModel = new EmpleadoModel();
        this.registrodeEmpleados = registrodeEmpleados;
        this.paneldeRegistros = paneldeRegistros;
        listarEmpleados();
        configurarListeners();
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        frmDashboard dash = new frmDashboard();
        RegistrodeEmpleados registrodeEmpleados = new RegistrodeEmpleados();
        PaneldeRegistros paneldeRegistros1 = new PaneldeRegistros();
        EmpleadoController controller = new EmpleadoController(registrodeEmpleados, paneldeRegistros1);
        dash.escritorio.add(registrodeEmpleados);
        dash.escritorio.add(paneldeRegistros1);
        dash.setVisible(true);
        registrodeEmpleados.setVisible(true);
        paneldeRegistros1.setVisible(true);
    }

    private void configurarListeners() {
        this.registrodeEmpleados.getBtnregistroempleadosagregar().addActionListener(this);
    }

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
            } catch (SQLException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(registrodeEmpleados, "Error al agregar el empleado: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrodeEmpleados.getBtnregistroempleadosagregar()) {
            agregar();
        }
    }
}
