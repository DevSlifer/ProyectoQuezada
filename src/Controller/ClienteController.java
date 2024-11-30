package Controller;

import java.awt.event.ActionListener;
import Model.ClienteModel;
import Model.DAOS.ClienteDAO;
import Views.PaneldeRegistros;
import Views.frmDashboard;
import Views.RegistrodeClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteController {

    private final ClienteDAO clienteDAO;
    private final ClienteModel clienteModel;
    private final PaneldeRegistros paneldeRegistros;
    private final RegistrodeClientes registrodeClientes;

    public ClienteController(PaneldeRegistros paneldeRegistros, RegistrodeClientes registroDeClientes) throws SQLException, FileNotFoundException {
        this.clienteDAO = new ClienteDAO();
        this.clienteModel = new ClienteModel();
        this.paneldeRegistros = paneldeRegistros;
        this.registrodeClientes = registroDeClientes;
        listarClientes();
        configurarListeners();
    }


    public void listarClientes() throws FileNotFoundException, SQLException {
        try {
            DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblclientes().getModel();
            modelo.setRowCount(0);
            List<ClienteModel> clientes = clienteDAO.verCliente(null);

            for (ClienteModel cliente : clientes) {
                Object[] fila = new Object[9];
                fila[0] = cliente.getNombre();
                fila[1] = cliente.getApellido();
                fila[2] = cliente.getCedula();
                fila[3] = cliente.getTelefono();
                fila[4] = cliente.getLicencia();
                fila[5] = cliente.getDirreccion().getProvincia();
                fila[6] = cliente.getDirreccion().getSector();
                fila[7] = cliente.getDirreccion().getCalle();
                fila[8] = cliente.getDirreccion().getNumeroDeCasa();
                modelo.addRow(fila);
            }
            paneldeRegistros.getTblclientes().setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(paneldeRegistros,
                    "Error al listar los clientes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregar() {
        String nombre = registrodeClientes.getTxtregistroclientesnombre().getText();
        String apellido = registrodeClientes.getTxtregistroclientesapellido().getText();
        String cedula = registrodeClientes.getTxtregistroclientescedula().getText();
        String telefono = registrodeClientes.getTxtregistroclientestelefono().getText();
        String licencia = registrodeClientes.getTxtregistroclienteslicencia().getText();
        String calle = registrodeClientes.getTxtregistroclientescalle().getText();
        String sector = registrodeClientes.getTxtregistroclientessector().getText();
        int numero = Integer.parseInt(registrodeClientes.getTxtregistroclientestelefono().getText());
        clienteModel.setNombre(nombre);
        clienteModel.setApellido(apellido);
        clienteModel.setCedula(cedula);
        clienteModel.setTelefono(telefono);
        clienteModel.setLicencia(licencia);
        clienteModel.getDirreccion().setCalle(calle);
        clienteModel.getDirreccion().setSector(sector);
        clienteModel.getDirreccion().setNumeroDeCasa(numero);
        if (validar(registrodeClientes) > 1) {
            try {
                clienteDAO.insertarCliente(clienteModel);
                JOptionPane.showMessageDialog(registrodeClientes, "Cliente agregado correctamente!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                listarClientes();
                limpiarCampos();
            } catch (FileNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(registrodeClientes, "Error al agregar el cliente!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void limpiarCampos() {
        registrodeClientes.getTxtregistroclientesnombre().setText("");
        registrodeClientes.getTxtregistroclientesapellido().setText("");
        registrodeClientes.getTxtregistroclientescedula().setText("");
        registrodeClientes.getTxtregistroclientestelefono().setText("");
        registrodeClientes.getTxtregistroclienteslicencia().setText("");
        registrodeClientes.getTxtregistroclientescalle().setText("");
        registrodeClientes.getTxtregistroclientessector().setText("");
        registrodeClientes.getTxtregistroclientestelefono().setText("");
    }

    private void configurarListeners() {
        registrodeClientes.getBtnregistroclientesagregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
    }

    public int validar(RegistrodeClientes registrodeClientes) {
        int validacion = 1;
        if (registrodeClientes.getTxtregistroclientesnombre().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de nombre no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientesnombre().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientesapellido().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de apellido no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientesapellido().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientescedula().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de cedula no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientescedula().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientestelefono().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de telefono no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientestelefono().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclienteslicencia().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de licencia no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclienteslicencia().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientescalle().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de calle no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientescalle().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientessector().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de sector no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientessector().requestFocus();
            return 0;
        }
        if (registrodeClientes.getTxtregistroclientestelefono().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeClientes, "El campo de numero no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeClientes.getTxtregistroclientestelefono().requestFocus();
            return 0;
        }
        return validacion;
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        frmDashboard dash = new frmDashboard();
        RegistrodeClientes registrodeClientes = new RegistrodeClientes();
        PaneldeRegistros paneldeRegistros1 = new PaneldeRegistros();
        ClienteController controller = new ClienteController(paneldeRegistros1, registrodeClientes);
        dash.escritorio.add(registrodeClientes);
        dash.escritorio.add(paneldeRegistros1);
        dash.setVisible(true);
        registrodeClientes.setVisible(true);
        paneldeRegistros1.setVisible(true);
    }
}
