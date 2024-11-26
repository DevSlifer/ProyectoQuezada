package Controller;

import Model.ClienteModel;
import Model.ClienteDAO;
import Views.frmPanelClientes;
import javax.swing.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ClienteController {

    private final frmPanelClientes view;
    private final ClienteDAO clienteDAO;

    public ClienteController(frmPanelClientes view) {
        this.view = view;
        this.clienteDAO = new ClienteDAO();
    }

    public void procesarRegistroCliente() {
        try {
            if (!validarCamposRequeridos()) {
                mostrarError("Todos los campos marcados son requeridos");
                return;
            }

            if (!validarFormatoCedula(view.txtcedula1.getText())) {
                mostrarError("El formato de la cédula no es válido");
                return;
            }

            if (!validarFormatoTelefono(view.txttelefono.getText())) {
                mostrarError("El formato del teléfono no es válido");
                return;
            }

            ClienteModel cliente = new ClienteModel();
            cliente.setNombre(view.txtnombre.getText());
            cliente.setApellido(view.txtapellido.getText());
            cliente.setCedula(view.txtcedula1.getText());
            cliente.setLicencia(view.txtlicencia.getText());
            cliente.setTelefono(view.txttelefono.getText());
            cliente.setProvincia(view.txtprovincia.getText());
            cliente.setSector(view.txtsector.getText());
            cliente.setCalle(view.txtcalle.getText());
            try {
                cliente.setNumeroCasa(Integer.parseInt(view.txtnumdelacasa.getText()));
            } catch (NumberFormatException e) {
                mostrarError("El número de casa debe ser un valor numérico");
                return;
            }
            clienteDAO.insertarCliente(cliente);
            mostrarMensaje("Cliente registrado exitosamente");
            limpiarCampos();

        } catch (SQLException ex) {
            manejarErrorBaseDatos(ex);
        } catch (Exception e) {
            mostrarError("Error al registrar el cliente: " + e.getMessage());
        }
    }

    private boolean validarCamposRequeridos() {
        return !view.txtnombre.getText().trim().isEmpty()
                && !view.txtapellido.getText().trim().isEmpty()
                && !view.txtcedula1.getText().trim().isEmpty()
                && !view.txtlicencia.getText().trim().isEmpty()
                && !view.txttelefono.getText().trim().isEmpty();
    }

    private boolean validarFormatoCedula(String cedula) {
        return Pattern.matches("^\\d{11}$", cedula);
    }

    private boolean validarFormatoTelefono(String telefono) {
        return Pattern.matches("^\\d{10}$", telefono);
    }

    private void limpiarCampos() {
        view.txtnombre.setText("");
        view.txtapellido.setText("");
        view.txtcedula1.setText("");
        view.txtlicencia.setText("");
        view.txttelefono.setText("");
        view.txtprovincia.setText("");
        view.txtsector.setText("");
        view.txtcalle.setText("");
        view.txtnumdelacasa.setText("");
        view.txtnombre.requestFocus();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(view, mensaje);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(view, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void manejarErrorBaseDatos(SQLException ex) {
        String mensaje;
        mensaje = switch (ex.getErrorCode()) {
            case 1062 ->
                "Ya existe un cliente con esa cédula";
            case 1452 ->
                "Error de integridad en los datos";
            default ->
                "Error en la base de datos: " + ex.getMessage();
        };
        mostrarError(mensaje);
    }
}
