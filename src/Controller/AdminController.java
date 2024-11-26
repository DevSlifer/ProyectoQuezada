package Controller;

import Model.ClienteDAO;
import Model.CarroModel;
import Model.ClienteModel;
import Views.frmPanelCarros;
import Views.frmPanelClientes;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.io.FileNotFoundException;

public class AdminController {

    private CarroController carroController = null;
    private ClienteDAO clienteDAO = null;
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
    private ClienteController clienteController = null;

    public AdminController(frmPanelCarros carrosView) throws InstantiationException {
        try {
            this.carroController = new CarroController(carrosView);
            this.clienteController = new ClienteController(new frmPanelClientes());

            this.clienteDAO = new ClienteDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Métodos para gestión de carros
    public void cargarDatosCarrosEnTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);

            List<CarroModel> carros = carroController.obtenerTodosLosCarros();
            for (CarroModel carro : carros) {
                modelo.addRow(new Object[]{
                    carro.getIdCarro(),
                    carro.getMatricula(),
                    carro.getMarca(),
                    carro.getModelo(),
                    carro.getNumPlaca(),
                    carro.getAnio(),
                    carro.getKilometraje()
                });
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar datos de carros", e);
            JOptionPane.showMessageDialog(null,
                    "Error al cargar los datos de los vehículos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarCarroEnTabla(String matricula, JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);

            CarroModel carro = carroController.buscarCarroPorMatricula(matricula);
            if (carro != null) {
                modelo.addRow(new Object[]{
                    carro.getIdCarro(),
                    carro.getMatricula(),
                    carro.getMarca(),
                    carro.getModelo(),
                    carro.getNumPlaca(),
                    carro.getAnio(),
                    carro.getKilometraje()
                });
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró ningún vehículo con esa matrícula",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar carro", e);
            JOptionPane.showMessageDialog(null,
                    "Error al buscar el vehículo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método de eliminación actualizado
    public boolean eliminarCarro(String matricula, JTable tabla) {
        try {
            if (JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar este vehículo?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                carroController.eliminarCarro(matricula);
                cargarDatosCarrosEnTabla(tabla); // Recargar tabla
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar carro", e);
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar el vehículo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean actualizarCarro(CarroModel carro) {
        try {
            if (!validarDatosCarro(carro)) {
                return false;
            }
            return carroController.actualizarCarro(carro);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar carro desde AdminController", e);
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el vehículo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Validaciones
    private boolean validarDatosCarro(CarroModel carro) {
        if (carro.getMarca() == null || carro.getMarca().trim().isEmpty()) {
            mostrarError("La marca es requerida");
            return false;
        }
        if (carro.getModelo() == null || carro.getModelo().trim().isEmpty()) {
            mostrarError("El modelo es requerido");
            return false;
        }
        if (carro.getNumPlaca() == null || !carro.getNumPlaca().matches("[A-Z]{3}\\d{4}")) {
            mostrarError("El número de placa debe tener el formato ABC1234");
            return false;
        }
        if (carro.getAnio() < 2000) {
            mostrarError("El año debe ser mayor a 2000");
            return false;
        }
        if (carro.getMatricula() == null || carro.getMatricula().trim().isEmpty()) {
            mostrarError("La matrícula es requerida");
            return false;
        }
        if (carro.getKilometraje() < 0) {
            mostrarError("El kilometraje no puede ser negativo");
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Métodos para obtener datos sin procesar
    public List<CarroModel> obtenerCarros() {
        return carroController.obtenerTodosLosCarros();
    }

    public CarroModel buscarCarroPorMatricula(String matricula) {
        return carroController.buscarCarroPorMatricula(matricula);
    }

    public List<ClienteModel> obtenerClientes() {
        try {
            return clienteDAO.obtenerTodosLosClientes();
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la lista de clientes", e);
            return new ArrayList<>();
        }
    }

    public ClienteModel buscarCliente(String cedula) {
        try {
            if (!validarFormatoCedula(cedula)) {
                mostrarError("El formato de la cédula no es válido");
                return null;
            }
            return clienteDAO.buscarClientePorCedula(cedula);
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar cliente", e);
            mostrarError("Error al buscar el cliente: " + e.getMessage());
            return null;
        }
    }

    public void eliminarCliente(String cedula) {
        try {
            if (!validarFormatoCedula(cedula)) {
                mostrarError("El formato de la cédula no es válido");
                return;
            }
            clienteDAO.eliminarCliente(cedula);
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar cliente", e);
            mostrarError("Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public boolean actualizarCliente(ClienteModel cliente) {
        try {
            if (!validarDatosCliente(cliente)) {
                return false;
            }
            clienteDAO.actualizarCliente(cliente);
            return true;
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar cliente", e);
            manejarErrorBaseDatos(e);
            return false;
        }
    }

    // Métodos de validación para clientes
    private boolean validarFormatoCedula(String cedula) {
        return Pattern.matches("^\\d{11}$", cedula);
    }

    private boolean validarFormatoTelefono(String telefono) {
        return Pattern.matches("^\\d{10}$", telefono);
    }

    private boolean validarDatosCliente(ClienteModel cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            mostrarError("El nombre es requerido");
            return false;
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            mostrarError("El apellido es requerido");
            return false;
        }
        if (!validarFormatoCedula(cliente.getCedula())) {
            mostrarError("El formato de la cédula no es válido");
            return false;
        }
        if (cliente.getLicencia() == null || cliente.getLicencia().trim().isEmpty()) {
            mostrarError("La licencia es requerida");
            return false;
        }
        if (!validarFormatoTelefono(cliente.getTelefono())) {
            mostrarError("El formato del teléfono no es válido");
            return false;
        }
        return true;
    }

    private void manejarErrorBaseDatos(Exception ex) {
        if (ex instanceof SQLException sqlEx) {
            String mensaje;
            switch (sqlEx.getErrorCode()) {
                case 1062:
                    mensaje = "Ya existe un registro con esos datos";
                    break;
                case 1452:
                    mensaje = "Error de integridad en los datos";
                    break;
                default:
                    mensaje = "Error en la base de datos: " + sqlEx.getMessage();
            }
            mostrarError(mensaje);
        } else {
            mostrarError("Error de conexión: " + ex.getMessage());
        }
    }

    public void buscarCarroEnTabla(JTable tbl_matricula, String matricula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
