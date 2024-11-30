/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.DireccionClienteModel;
import Model.ClienteModel;
import java.sql.*;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Supre
 */
public class ClienteDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;

    public int insertarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        String sql = "call sp_insertar_cliente(?,?,?,?,?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
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
            return 1;

        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List verCliente(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_leer_cliente(?)";

        List<ClienteModel> infoCliente = new ArrayList();

        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            if (cedula != null) {
                cs.setString(1, cedula);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            rs = cs.executeQuery();

            while (rs.next()) {
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
                direccion.setNumeroDeCasa(
                        rs.getInt("NumeroDeCasa"));
                cliente.setDirreccion(direccion);
                infoCliente.add(cliente);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return infoCliente;
    }

    public int actualizarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException {
        String sql = "call sp_actualizar_cliente(?,?,?,?,?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            // Parámetro requerido
            cs.setString(1, cliente.getCedula());

            // Parámetros opcionales del cliente
            if (cliente.getNombre() != null) {
                cs.setString(2, cliente.getNombre());
            } else {
                cs.setNull(2, Types.VARCHAR);
            }

            if (cliente.getApellido() != null) {
                cs.setString(3, cliente.getApellido());
            } else {
                cs.setNull(3, Types.VARCHAR);
            }

            if (cliente.getLicencia() != null) {
                cs.setString(4, cliente.getLicencia());
            } else {
                cs.setNull(4, Types.VARCHAR);
            }

            // Parámetros opcionales de dirección
            if (cliente.getDirreccion() != null) {
                if (cliente.getDirreccion().getProvincia() != null) {
                    cs.setString(5, cliente.getDirreccion().getProvincia());
                } else {
                    cs.setNull(5, Types.VARCHAR);
                }

                if (cliente.getDirreccion().getSector() != null) {
                    cs.setString(6, cliente.getDirreccion().getSector());
                } else {
                    cs.setNull(6, Types.VARCHAR);
                }

                if (cliente.getDirreccion().getCalle() != null) {
                    cs.setString(7, cliente.getDirreccion().getCalle());
                } else {
                    cs.setNull(7, Types.VARCHAR);
                }

                if (cliente.getDirreccion().getNumeroDeCasa() != 0) {
                    cs.setInt(8, cliente.getDirreccion().getNumeroDeCasa());
                } else {
                    cs.setNull(8, Types.INTEGER);
                }
            } else {
                cs.setNull(5, Types.VARCHAR);
                cs.setNull(6, Types.VARCHAR);
                cs.setNull(7, Types.VARCHAR);
                cs.setNull(8, Types.INTEGER);
            }

            if (cliente.getTelefono() != null) {
                cs.setString(9, cliente.getTelefono());
            } else {
                cs.setNull(9, Types.VARCHAR);
            }

            cs.execute();
            return 1;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int eliminarCliente(String cedula) throws SQLException, FileNotFoundException {
        String sql = "call sp_borrar_cliente(?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, cedula);

            cs.execute();
            return 1;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return 0;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
