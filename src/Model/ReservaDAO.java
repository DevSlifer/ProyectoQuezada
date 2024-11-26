package Model;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // Método para insertar una reserva
    public void insertarReserva(ReservaModel reserva) throws FileNotFoundException {
        String query = "{CALL sp_insertar_reserva(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, reserva.getNombreCliente());
            statement.setString(2, reserva.getApellidoCliente());
            statement.setString(3, reserva.getCedulaCliente());
            statement.setString(4, reserva.getMarcaVehiculo());
            statement.setString(5, reserva.getModeloVehiculo());
            statement.setInt(6, reserva.getAnio());
            statement.setDate(7, new java.sql.Date(reserva.getFechaDeEntrega().getTime()));
            statement.setDate(8, new java.sql.Date(reserva.getFechaDevolucion().getTime()));

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Error al insertar reserva: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Método para actualizar una reserva
    public void actualizarReserva(ReservaModel reserva) throws FileNotFoundException {
        String query = "{CALL sp_actualizar_reserva(?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, reserva.getCedulaCliente());
            statement.setString(2, reserva.getNumChasis());
            statement.setDate(3, new java.sql.Date(reserva.getFechaReservacion().getTime()));
            statement.setDate(4, new java.sql.Date(reserva.getFechaDeEntrega().getTime()));
            statement.setDate(5, new java.sql.Date(reserva.getFechaDevolucion().getTime()));

            statement.execute();
        } catch (SQLException e) {
        }
    }

    // Método para eliminar una reserva
    public void eliminarReserva(String cedulaCliente, String numChasis) throws FileNotFoundException {
        String query = "{CALL sp_eliminar_reserva(?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, cedulaCliente);
            statement.setString(2, numChasis);

            statement.execute();
        } catch (SQLException e) {
        }
    }

    // Método para leer todas las reservas
    public List<ReservaModel> leerReservas(String cedulaCliente) throws FileNotFoundException {
        List<ReservaModel> reservas = new ArrayList<>();
        String query = "{CALL sp_leer_reserva(?)}";

        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement statement = connection.prepareCall(query)) {

            if (cedulaCliente == null || cedulaCliente.isEmpty()) {
                statement.setNull(1, Types.VARCHAR);
            } else {
                statement.setString(1, cedulaCliente);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ReservaModel reserva = new ReservaModel();
                    reserva.setNombreCliente(resultSet.getString("Nombre"));
                    reserva.setApellidoCliente(resultSet.getString("Apellido"));
                    reserva.setCedulaCliente(resultSet.getString("Cedula_Del_Cliente"));
                    reserva.setMarcaVehiculo(resultSet.getString("Marca"));
                    reserva.setModeloVehiculo(resultSet.getString("Modelo"));
                    reserva.setAnio(resultSet.getInt("Anio"));
                    reserva.setFechaReservacion(resultSet.getDate("Fecha_Reservacion"));
                    reserva.setFechaDeEntrega(resultSet.getDate("Fecha_De_Entrega"));
                    reserva.setFechaDevolucion(resultSet.getDate("Fecha_De_Devolucion"));
                    reserva.setNumChasis(resultSet.getString("NumChasis"));
                    reservas.add(reserva);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer reservas: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return reservas;
    }

    // Método para leer una reserva específica por cédula y número de chasis
    public ReservaModel leerReservaByCedulaAndChasis(String cedulaCliente, String numChasis) throws FileNotFoundException {
        ReservaModel reserva = new ReservaModel();
        String query = "{CALL sp_leer_reserva(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, cedulaCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = new ReservaModel();
                    reserva.setCedulaCliente(resultSet.getString("Cedula_Del_Cliente"));
                    reserva.setNumChasis(resultSet.getString("NumChasis"));
                    reserva.setFechaDeEntrega(resultSet.getDate("Fecha_De_Entrega"));
                    reserva.setFechaDevolucion(resultSet.getDate("Fecha_De_Devolucion"));
                }
            }
        } catch (SQLException e) {
        }
        return reserva;
    }
}
