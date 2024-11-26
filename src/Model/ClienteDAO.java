/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Supre
 */
public class ClienteDAO {

    public void insertarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCedula());
            stmt.setString(4, cliente.getLicencia());
            stmt.setString(5, cliente.getProvincia());
            stmt.setString(6, cliente.getSector());
            stmt.setString(7, cliente.getCalle());
            stmt.setInt(8, (cliente.getNumeroCasa()));
            stmt.setString(9, cliente.getTelefono());

            stmt.execute();
        }
    }

    public void actualizarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCedula());
            stmt.setString(4, cliente.getLicencia());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getProvincia());
            stmt.setString(7, cliente.getSector());
            stmt.setString(8, cliente.getCalle());
            stmt.setInt(9, cliente.getNumeroCasa());

            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(String cedula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_eliminar_cliente(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cedula);
            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar cliente: " + e.getMessage());
        }
    }

    public ClienteModel buscarClientePorCedula(String cedula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_leer_cliente(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cedula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ClienteModel cliente = new ClienteModel();
                    cliente.setIdCliente(rs.getInt("IDCliente"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setApellido(rs.getString("Apellido"));
                    cliente.setCedula(rs.getString("Cedula"));
                    cliente.setLicencia(rs.getString("Licencia"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setProvincia(rs.getString("Provincia"));
                    cliente.setSector(rs.getString("Sector"));
                    cliente.setCalle(rs.getString("Calle"));
                    cliente.setNumeroCasa(rs.getInt("Numero_Casa"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public List<ClienteModel> obtenerTodosLosClientes() throws SQLException, FileNotFoundException {
        List<ClienteModel> clientes = new ArrayList<>();
        String query = "{CALL sp_leer_cliente(?)}";

        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setNull(1, Types.VARCHAR); // Para obtener todos los clientes
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClienteModel cliente = new ClienteModel();
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setCedula(rs.getString("Cedula"));
                    cliente.setProvincia(rs.getString("Provincia"));
                    cliente.setSector(rs.getString("Sector"));
                    cliente.setCalle(rs.getString("Calle"));
                    cliente.setNumeroCasa(rs.getInt("NumeroDeCasa"));
                    cliente.setTelefono(rs.getString("Telefonos"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de clientes: " + e.getMessage());
        }
        return clientes;
    }
}
