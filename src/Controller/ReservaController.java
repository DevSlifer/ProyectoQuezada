package Controller;

import Model.ReservaDAO;
import Model.ReservaModel;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaController {

    private final ReservaDAO reservaDAO;
    private static final Logger LOGGER = Logger.getLogger(ReservaController.class.getName());

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public boolean insertarReserva(String cedulaCliente, String numChasis, Date fechaReservacion,
            Date fechaDeEntrega, Date fechaDevolucion) {
        try {
            if (!validarDatosReserva(cedulaCliente, numChasis, fechaReservacion, fechaDeEntrega, fechaDevolucion)) {
                return false;
            }

            ReservaModel reserva = new ReservaModel();
            reserva.setCedulaCliente(cedulaCliente);
            reserva.setNumChasis(numChasis);
            reserva.setFechaReservacion(fechaReservacion);
            reserva.setFechaDeEntrega(fechaDeEntrega);
            reserva.setFechaDevolucion(fechaDevolucion);

            reservaDAO.insertarReserva(reserva);
            LOGGER.info("Reserva insertada exitosamente");
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la base de datos: {0}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar la reserva: {0}", e.getMessage());
            return false;
        }
    }

    public List<ReservaModel> obtenerReservas(String cedulaCliente) {
        try {
            return reservaDAO.leerReservas(cedulaCliente);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la base de datos: {0}", e.getMessage());
            return null;
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener las reservas: {0}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarReserva(String cedulaCliente, String numChasis) {
        try {
            if (cedulaCliente == null || cedulaCliente.trim().isEmpty()
                    || numChasis == null || numChasis.trim().isEmpty()) {
                LOGGER.warning("La cédula del cliente y el número de chasis son requeridos para eliminar una reserva");
                return false;
            }

            reservaDAO.eliminarReserva(cedulaCliente, numChasis);
            LOGGER.info("Reserva eliminada exitosamente");
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la base de datos: {0}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la reserva: {0}", e.getMessage());
            return false;
        }
    }

    private boolean validarDatosReserva(String cedulaCliente, String numChasis,
            Date fechaReservacion, Date fechaDeEntrega,
            Date fechaDevolucion) {
        if (cedulaCliente == null || cedulaCliente.trim().isEmpty()) {
            LOGGER.warning("La cédula del cliente es requerida");
            return false;
        }
        if (numChasis == null || numChasis.trim().isEmpty()) {
            LOGGER.warning("El número de chasis es requerido");
            return false;
        }
        if (fechaReservacion == null || fechaDeEntrega == null || fechaDevolucion == null) {
            LOGGER.warning("Todas las fechas son requeridas");
            return false;
        }
        if (fechaReservacion.after(fechaDeEntrega) || fechaDeEntrega.after(fechaDevolucion)) {
            LOGGER.warning("Las fechas ingresadas no son válidas");
            return false;
        }
        return true;
    }

    public boolean insertarReserva(String nombre, String apellido, String cedula,
            String marca, String modelo, int anio,
            Date fechaReservacion, Date fechaEntrega,
            Date fechaDevolucion) {
        try {
            if (!validarDatosReserva(nombre, apellido, cedula, marca, modelo,
                    anio, fechaReservacion, fechaEntrega, fechaDevolucion)) {
                return false;
            }

            ReservaModel reserva = new ReservaModel();
            reserva.setNombreCliente(nombre);
            reserva.setApellidoCliente(apellido);
            reserva.setCedulaCliente(cedula);
            reserva.setMarcaVehiculo(marca);
            reserva.setModeloVehiculo(modelo);
            reserva.setAnio(anio);
            reserva.setFechaReservacion(fechaReservacion);
            reserva.setFechaDeEntrega(fechaEntrega);
            reserva.setFechaDevolucion(fechaDevolucion);

            reservaDAO.insertarReserva(reserva);
            LOGGER.info("Reserva insertada exitosamente");
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la base de datos: {0}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar la reserva: {0}", e.getMessage());
            return false;
        }
    }

    private boolean validarDatosReserva(String nombre, String apellido, String cedula,
            String marca, String modelo, int anio,
            Date fechaReservacion, Date fechaEntrega,
            Date fechaDevolucion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            LOGGER.warning("El nombre es requerido");
            return false;
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            LOGGER.warning("El apellido es requerido");
            return false;
        }
        if (cedula == null || cedula.trim().isEmpty()) {
            LOGGER.warning("La cédula es requerida");
            return false;
        }
        if (marca == null || marca.trim().isEmpty()) {
            LOGGER.warning("La marca del vehículo es requerida");
            return false;
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            LOGGER.warning("El modelo del vehículo es requerido");
            return false;
        }
        if (anio <= 0) {
            LOGGER.warning("El año debe ser un número válido");
            return false;
        }
        if (fechaReservacion == null || fechaEntrega == null || fechaDevolucion == null) {
            LOGGER.warning("Todas las fechas son requeridas");
            return false;
        }
        if (fechaEntrega.before(fechaReservacion) || fechaDevolucion.before(fechaEntrega)) {
            LOGGER.warning("Las fechas ingresadas no son válidas");
            return false;
        }
        return true;
    }
}
