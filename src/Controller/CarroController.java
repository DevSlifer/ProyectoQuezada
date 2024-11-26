package Controller;

import Model.CarroModel;
import Model.CarroDAO;
import Views.frmPanelCarros;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class CarroController {

    private final frmPanelCarros view;
    private final CarroDAO carroDAO;

    public CarroController(frmPanelCarros view) {
        this.view = view;
        this.carroDAO = new CarroDAO();
    }

    public void procesarRegistroCarro() {
        try {
            // Validar campos requeridos
            if (!validarCamposRequeridos()) {
                mostrarError("Todos los campos son requeridos");
                return;
            }

            // Validar formato de matrícula
            if (!validarFormatoMatricula(view.getMatricula())) {
                mostrarError("El formato de la matrícula no es válido");
                return;
            }

            // Validar año
            int año = validarAño();
            if (año == -1) {
                return;
            }

            // Validar placa
            if (!validarFormatoPlaca(view.getPlaca())) {
                mostrarError("El formato de la placa no es válido");
                return;
            }

            // Crear y poblar el modelo
            CarroModel carro = new CarroModel();
            carro.setMarca(view.getMarca());
            carro.setModelo(view.getModelo());
            carro.setAnio(año);
            carro.setTipoVehiculo("Sedan"); // Por ahora hardcodeado, debería venir de un combo en la vista
            carro.setNumPlaca(view.getPlaca());
            carro.setNumChasis(view.getPlaca()); // Por ahora usando la placa como número de chasis
            carro.setMatricula(view.getMatricula());

            // Insertar en la base de datos
            carroDAO.insertarCarro(carro);

            // Mostrar mensaje de éxito y limpiar campos
            mostrarMensaje("Vehículo registrado exitosamente");
            view.limpiarCampos();

        } catch (SQLException | FileNotFoundException ex) {
            manejarErrorBaseDatos(ex);
        } catch (Exception e) {
            mostrarError("Error al registrar el vehículo: " + e.getMessage());
        }
    }

    private boolean validarCamposRequeridos() {
        return !view.getMarca().trim().isEmpty()
                && !view.getModelo().trim().isEmpty()
                && !view.getAño().trim().isEmpty()
                && !view.getPlaca().trim().isEmpty()
                && !view.getMatricula().trim().isEmpty();
    }

    private int validarAño() {
        try {
            int año = Integer.parseInt(view.getAño());
            if (año < 2000) {
                mostrarError("El año debe ser mayor a 2000");
                return -1;
            }
            if (año > 2025) {
                mostrarError("El año no puede ser mayor al actual");
                return -1;
            }
            return año;
        } catch (NumberFormatException e) {
            mostrarError("El año debe ser un número válido");
            return -1;
        }
    }

    private boolean validarFormatoMatricula(String matricula) {
        // La matrícula debe tener entre 6 y 50 caracteres alfanuméricos
        return matricula.matches("^[A-Z0-9]{6,50}$");
    }

    private boolean validarFormatoPlaca(String placa) {
        // La placa debe tener exactamente 7 caracteres: 3 letras y 4 números
        return placa.matches("^[A-Z]{3}\\d{4}$");
    }

    public void buscarCarro(String matricula) {
        try {
            CarroModel carro = carroDAO.buscarCarroPorMatricula(matricula);
            if (carro != null) {
                // Actualizar campos en la vista
                actualizarVistaConCarro(carro);
            } else {
                mostrarError("No se encontró ningún vehículo con esa matrícula");
            }
        } catch (Exception e) {
            mostrarError("Error al buscar el vehículo: " + e.getMessage());
        }
    }

    public void eliminarCarro(String matricula) {
        try {
            if (JOptionPane.showConfirmDialog(view,
                    "¿Está seguro de eliminar este vehículo?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                carroDAO.eliminarCarro(matricula);
                mostrarMensaje("Vehículo eliminado exitosamente");
                view.limpiarCampos();
            }
        } catch (Exception e) {
            mostrarError("Error al eliminar el vehículo: " + e.getMessage());
        }
    }

    private void actualizarVistaConCarro(CarroModel carro) {
        view.setMarca(carro.getMarca());
        view.setModelo(carro.getModelo());
        view.setAño(String.valueOf(carro.getAnio()));
        view.setPlaca(carro.getNumPlaca());
        view.setMatricula(carro.getMatricula());
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
