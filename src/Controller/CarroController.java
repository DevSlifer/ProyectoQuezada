package Controller;

import Model.CarroModel;
import Model.CarroDAO;
import Views.frmPanelCarros;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroController {
    private final frmPanelCarros view;
    private final CarroDAO carroDAO;
    private static final Logger LOGGER = Logger.getLogger(CarroController.class.getName());

    public CarroController(frmPanelCarros view) {
        this.view = view;
        this.carroDAO = new CarroDAO();
    }

    public boolean procesarRegistroCarro(CarroModel carro) {
        try {
            // Validar formato de matrícula
            if (!validarFormatoMatricula(carro.getMatricula())) {
                mostrarError("El formato de la matrícula no es válido");
                return false;
            }

            // Validar placa
            if (!validarFormatoPlaca(carro.getNumPlaca())) {
                mostrarError("El formato de la placa no es válido");
                return false;
            }

            // Validar kilometraje
            if (carro.getKilometraje() < 0) {
                mostrarError("El kilometraje no puede ser negativo");
                return false;
            }
            
            // Validar año
            if (carro.getAnio() < 2000) {
                mostrarError("El año debe ser mayor a 2000");
                return false;
            }

            // Agregar tipo de vehículo por defecto si es necesario
            carro.setTipoVehiculo("Sedan"); // Por ahora hardcodeado
            
            // Usar la placa como número de chasis por ahora
            carro.setNumChasis(carro.getNumPlaca());

            // Insertar en la base de datos
            carroDAO.insertarCarro(carro);
            mostrarMensaje("Vehículo registrado exitosamente");
            return true;

        } catch (SQLException | FileNotFoundException ex) {
            manejarErrorBaseDatos(ex);
            return false;
        } catch (Exception e) {
            mostrarError("Error al registrar el vehículo: " + e.getMessage());
            return false;
        }
    }

    public List<CarroModel> obtenerTodosLosCarros() {
        try {
            return carroDAO.obtenerTodosLosCarros();
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener la lista de carros", e);
            mostrarError("Error al obtener la lista de vehículos");
            return new ArrayList<>();
        }
    }

    public CarroModel buscarCarroPorMatricula(String matricula) {
        try {
            if (!validarFormatoMatricula(matricula)) {
                mostrarError("El formato de la matrícula no es válido");
                return null;
            }
            
            CarroModel carro = carroDAO.buscarCarroPorMatricula(matricula);
            if (carro != null) {
                actualizarVistaConCarro(carro);
            } else {
                mostrarError("No se encontró ningún vehículo con esa matrícula");
            }
            return carro;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar carro", e);
            mostrarError("Error al buscar el vehículo: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarCarro(CarroModel carro) {
        try {
            if (!validarDatosCarro(carro)) {
                return false;
            }
            
            carroDAO.actualizarCarro(carro);
            mostrarMensaje("Vehículo actualizado exitosamente");
            return true;
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar carro", e);
            manejarErrorBaseDatos(e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado al actualizar carro", e);
            mostrarError("Error al actualizar el vehículo: " + e.getMessage());
            return false;
        }
    }

    public void eliminarCarro(String matricula) {
        try {
            if (!validarFormatoMatricula(matricula)) {
                mostrarError("El formato de la matrícula no es válido");
                return;
            }

            if (JOptionPane.showConfirmDialog(view,
                    "¿Está seguro de eliminar este vehículo?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                carroDAO.eliminarCarro(matricula);
                mostrarMensaje("Vehículo eliminado exitosamente");
                view.limpiarCampos();
            }
        } catch (SQLException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar carro", e);
            manejarErrorBaseDatos(e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inesperado al eliminar carro", e);
            mostrarError("Error al eliminar el vehículo: " + e.getMessage());
        }
    }

    private boolean validarDatosCarro(CarroModel carro) {
        if (!validarFormatoMatricula(carro.getMatricula())) {
            mostrarError("El formato de la matrícula no es válido");
            return false;
        }
        if (!validarFormatoPlaca(carro.getNumPlaca())) {
            mostrarError("El formato de la placa no es válido");
            return false;
        }
        if (carro.getKilometraje() < 0) {
            mostrarError("El kilometraje no puede ser negativo");
            return false;
        }
        if (carro.getAnio() < 2000) {
            mostrarError("El año debe ser mayor a 2000");
            return false;
        }
        return true;
    }

    private boolean validarFormatoMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return false;
        }
        // La matrícula debe tener entre 6 y 50 caracteres alfanuméricos
        return matricula.matches("^[A-Z0-9]{6,50}$");
    }

    private boolean validarFormatoPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            return false;
        }
        // La placa debe tener exactamente 7 caracteres: 3 letras y 4 números
        return placa.matches("^[A-Z]{3}\\d{4}$");
    }

    private void actualizarVistaConCarro(CarroModel carro) {
        view.setMarca(carro.getMarca());
        view.setModelo(carro.getModelo());
        view.setAño(String.valueOf(carro.getAnio()));
        view.setPlaca(carro.getNumPlaca());
        view.setMatricula(carro.getMatricula());
        view.setKilometraje(String.valueOf(carro.getKilometraje()));
    }

    private void mostrarMensaje(String mensaje) {
        view.mostrarMensaje(mensaje);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(view, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void manejarErrorBaseDatos(Exception ex) {
        if (ex instanceof SQLException) {
            SQLException sqlEx = (SQLException) ex;
            String mensaje;
            switch (sqlEx.getErrorCode()) {
                case 1062:
                    mensaje = "Ya existe un vehículo con esa matrícula/placa/chasis";
                    break;
                default:
                    mensaje = "Error en la base de datos: " + sqlEx.getMessage();
            }
            mostrarError(mensaje);
        } else {
            mostrarError("Error de conexión: " + ex.getMessage());
        }
    }
}