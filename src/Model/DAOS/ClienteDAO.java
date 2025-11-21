/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.DireccionClienteModel;
import Model.ClienteModel;
import Utils.Constants;
import java.sql.*;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Cliente entity.
 * Refactored to implement IClienteDAO interface and follow SOLID principles.
 * Uses try-with-resources for proper resource management.
 *
 * @author ProyectoQuezada Team
 */
public class ClienteDAO implements IClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());

    /**
     * Inserts a new client into the database.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int insertarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_INSERTAR_CLIENTE)) {

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApellido());
            cs.setString(3, cliente.getCedula());
            cs.setString(4, cliente.getLicencia());
            cs.setString(5, cliente.getDirreccion().getProvincia());
            cs.setString(6, cliente.getDirreccion().getSector());
            cs.setString(7, cliente.getDirreccion().getCalle());
            cs.setInt(8, cliente.getDirreccion().getNumeroDeCasa());
            cs.setString(9, cliente.getTelefono());
            cs.execute();

            LOGGER.log(Level.INFO, "Cliente inserted successfully: {0}", cliente.getCedula());
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting cliente", e);
            if (Constants.SQLStates.CUSTOM_ERROR.equals(e.getSQLState())) {
                throw new SQLException(e.getMessage());
            }
            throw e;
        }
    }

    /**
     * Retrieves clients from the database.
     * If cedula is null, returns all clients; otherwise returns specific client.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public List<ClienteModel> verCliente(String cedula) throws FileNotFoundException, SQLException {
        List<ClienteModel> infoCliente = new ArrayList<>();

        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_LEER_CLIENTE)) {

            if (cedula != null) {
                cs.setString(1, cedula);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ClienteModel cliente = mapResultSetToCliente(rs);
                    infoCliente.add(cliente);
                }
            }

            LOGGER.log(Level.INFO, "Retrieved {0} cliente(s)", infoCliente.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving clientes", e);
            throw e;
        }

        return infoCliente;
    }

    /**
     * Maps a ResultSet row to a ClienteModel object.
     * Extracted to avoid code duplication and improve maintainability (DRY principle).
     */
    private ClienteModel mapResultSetToCliente(ResultSet rs) throws SQLException {
        ClienteModel cliente = new ClienteModel();
        DireccionClienteModel direccion = new DireccionClienteModel();

        cliente.setNombre(rs.getString("Nombre"));
        cliente.setApellido(rs.getString("Apellido"));
        cliente.setCedula(rs.getString("Cedula"));
        cliente.setLicencia(rs.getString("Licencia"));
        cliente.setTelefono(rs.getString("Telefono"));

        direccion.setProvincia(rs.getString("Provincia"));
        direccion.setSector(rs.getString("Sector"));
        direccion.setCalle(rs.getString("Calle"));
        direccion.setNumeroDeCasa(rs.getInt("NumeroDeCasa"));

        cliente.setDirreccion(direccion);
        return cliente;
    }

    /**
     * Updates an existing client in the database.
     * All parameters except cedula are optional (null values keep existing data).
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int actualizarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_ACTUALIZAR_CLIENTE)) {

            // Required parameter
            cs.setString(1, cliente.getCedula());

            // Optional client parameters
            setStringOrNull(cs, 2, cliente.getNombre());
            setStringOrNull(cs, 3, cliente.getApellido());
            setStringOrNull(cs, 4, cliente.getLicencia());

            // Optional address parameters
            if (cliente.getDirreccion() != null) {
                setStringOrNull(cs, 5, cliente.getDirreccion().getProvincia());
                setStringOrNull(cs, 6, cliente.getDirreccion().getSector());
                setStringOrNull(cs, 7, cliente.getDirreccion().getCalle());
                setIntOrNull(cs, 8, cliente.getDirreccion().getNumeroDeCasa());
            } else {
                cs.setNull(5, Types.VARCHAR);
                cs.setNull(6, Types.VARCHAR);
                cs.setNull(7, Types.VARCHAR);
                cs.setNull(8, Types.INTEGER);
            }

            setStringOrNull(cs, 9, cliente.getTelefono());

            cs.execute();
            LOGGER.log(Level.INFO, "Cliente updated successfully: {0}", cliente.getCedula());
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating cliente", e);
            throw e;
        }
    }

    /**
     * Helper method to set a string parameter or null.
     * Reduces code duplication (DRY principle).
     */
    private void setStringOrNull(CallableStatement cs, int parameterIndex, String value) throws SQLException {
        if (value != null) {
            cs.setString(parameterIndex, value);
        } else {
            cs.setNull(parameterIndex, Types.VARCHAR);
        }
    }

    /**
     * Helper method to set an integer parameter or null.
     * Reduces code duplication (DRY principle).
     */
    private void setIntOrNull(CallableStatement cs, int parameterIndex, int value) throws SQLException {
        if (value != 0) {
            cs.setInt(parameterIndex, value);
        } else {
            cs.setNull(parameterIndex, Types.INTEGER);
        }
    }

    /**
     * Deletes a client from the database by cedula.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int eliminarCliente(String cedula) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_BORRAR_CLIENTE)) {

            cs.setString(1, cedula);
            cs.execute();

            LOGGER.log(Level.INFO, "Cliente deleted successfully: {0}", cedula);
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting cliente: " + cedula, e);
            throw e;
        }
    }
}
