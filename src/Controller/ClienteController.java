package Controller;

import Model.ClienteModel;
import Model.DAOS.ClienteDAO;
import Model.DireccionClienteModel;
import Views.PaneldeRegistros;
import Views.RegistrodeClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteController implements ActionListener {

    private final ClienteDAO clienteDAO;
    private final ClienteModel clienteModel;
    private final PaneldeRegistros paneldeRegistros;
    private final RegistrodeClientes registrodeClientes;

    public ClienteController(PaneldeRegistros paneldeRegistros, RegistrodeClientes registroDeClientes) throws SQLException, FileNotFoundException {
        this.clienteDAO = new ClienteDAO();
        this.clienteModel = new ClienteModel();
        this.paneldeRegistros = paneldeRegistros;
        this.registrodeClientes = registroDeClientes;
        this.clienteModel.setDirreccion(new DireccionClienteModel());
        this.registrodeClientes.getBtnregistroclientesagregar().addActionListener(this);
        this.paneldeRegistros.getBtnregistrobuscar().addActionListener(this);
        this.paneldeRegistros.getBtnregistroeliminar().addActionListener(this);
        this.paneldeRegistros.getBtnregistroactualizar().addActionListener(this);
        listarClientes();
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
        String provincia = registrodeClientes.getTxtregistroclientesprovincia().getText().trim();
        String calle = registrodeClientes.getTxtregistroclientescalle().getText();
        String sector = registrodeClientes.getTxtregistroclientessector().getText();
        String numeroStr = registrodeClientes.getTxtregistroclientesnumdelacasa().getText().trim();
        int numero;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(registrodeClientes,
                    "El número de casa debe ser un valor numérico válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        clienteModel.setNombre(nombre);
        clienteModel.setApellido(apellido);
        clienteModel.setCedula(cedula);
        clienteModel.setTelefono(telefono);
        clienteModel.setLicencia(licencia);
        clienteModel.getDirreccion().setCalle(calle);
        clienteModel.getDirreccion().setSector(sector);
        clienteModel.getDirreccion().setProvincia(provincia);
        clienteModel.getDirreccion().setNumeroDeCasa(numero);
        if (validar(registrodeClientes) > 0) {
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

    public void eliminarCliente(String cedula) throws FileNotFoundException, SQLException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese una cedula", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirmacion = JOptionPane.showConfirmDialog(paneldeRegistros, "¿Está seguro que desea eliminar el cliente con cedula: " + cedula + "?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                clienteDAO.eliminarCliente(cedula);
                JOptionPane.showMessageDialog(paneldeRegistros, "Cliente eliminado correctamente!", "Exito!",
                        JOptionPane.INFORMATION_MESSAGE);
                listarClientes();
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al eliminar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarCliente(String cedula) throws FileNotFoundException, SQLException, SQLException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese una cedula", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<ClienteModel> clientes = clienteDAO.verCliente(cedula);
            if (!clientes.isEmpty()) {
                ClienteModel cliente = clientes.get(0);
                String nuevoNombre = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo nombre", cliente.getNombre());
                String nuevoApellido = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo apellido", cliente.getApellido());
                String nuevoTelefono = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo telefono", cliente.getTelefono());
                String nuevaLicencia = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese la nueva licencia", cliente.getLicencia());
                String nuevaProvincia = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese la nueva provincia", cliente.getDirreccion().getProvincia());
                String nuevoSector = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo sector", cliente.getDirreccion().getSector());
                String nuevaCalle = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese la nueva calle", cliente.getDirreccion().getCalle());
                String nuevoNumeroStr = JOptionPane.showInputDialog(paneldeRegistros, "Ingrese el nuevo número de casa", cliente.getDirreccion().getNumeroDeCasa());
                int nuevoNumero;
                try {
                    nuevoNumero = Integer.parseInt(nuevoNumeroStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(paneldeRegistros, "El número de casa debe ser un valor numérico válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nuevoNombre != null && nuevoApellido != null && nuevoTelefono != null && nuevaLicencia != null && nuevaProvincia != null && nuevoSector != null && nuevaCalle != null) {
                    cliente.setNombre(nuevoNombre);
                    cliente.setApellido(nuevoApellido);
                    cliente.setTelefono(nuevoTelefono);
                    cliente.setLicencia(nuevaLicencia);
                    cliente.getDirreccion().setProvincia(nuevaProvincia);
                    cliente.getDirreccion().setSector(nuevoSector);
                    cliente.getDirreccion().setCalle(nuevaCalle);
                    cliente.getDirreccion().setNumeroDeCasa(nuevoNumero);
                    clienteDAO.actualizarCliente(cliente);
                    JOptionPane.showMessageDialog(paneldeRegistros, "Cliente actualizado correctamente!", "Exito!",
                            JOptionPane.INFORMATION_MESSAGE);
                    listarClientes();
                }
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al actualizar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al actualizar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarPorCedula(String cedula) throws FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros, "Por favor ingrese una cedula", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<ClienteModel> clientes = clienteDAO.verCliente(cedula);
            DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblclientes().getModel();
            modelo.setRowCount(0);
            if (!clientes.isEmpty()) {
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
            } else {
                JOptionPane.showMessageDialog(paneldeRegistros, "No se encontraron clientes con la cedula: " + cedula, "Error",
                        JOptionPane.ERROR_MESSAGE);
                listarClientes();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(paneldeRegistros, "Error al buscar el cliente: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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

    public void limpiarCampos() {
        registrodeClientes.getTxtregistroclientesnombre().setText("");
        registrodeClientes.getTxtregistroclientesapellido().setText("");
        registrodeClientes.getTxtregistroclientescedula().setText("");
        registrodeClientes.getTxtregistroclientestelefono().setText("");
        registrodeClientes.getTxtregistroclienteslicencia().setText("");
        registrodeClientes.getTxtregistroclientesprovincia().setText("");
        registrodeClientes.getTxtregistroclientescalle().setText("");
        registrodeClientes.getTxtregistroclientessector().setText("");
        registrodeClientes.getTxtregistroclientesnumdelacasa().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paneldeRegistros.getBtnregistrobuscar()) {
            String cedula = paneldeRegistros.getTxtpanelregistrocedula().getText();
            try {
                buscarPorCedula(cedula);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == registrodeClientes.getBtnregistroclientesagregar()) {
            agregar();
        } else if (e.getSource() == paneldeRegistros.getBtnregistroeliminar()) {
            String cedula = paneldeRegistros.getTxtpanelregistrocedula().getText();
            try {
                eliminarCliente(cedula);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == paneldeRegistros.getBtnregistroactualizar()) {
            try {
                String cedula = paneldeRegistros.getTxtpanelregistrocedula().getText();
                actualizarCliente(cedula);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
