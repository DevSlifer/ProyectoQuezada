/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.EmpleadoModel;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Supre
 */
public class EmpleadoDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;

    public int insertarEmpleado(EmpleadoModel empleado) throws SQLException, FileNotFoundException {
        String sql = "call sp_insertar_empleado(?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, empleado.getNombre());
            cs.setString(2, empleado.getApellido());
            cs.setBigDecimal(3, new BigDecimal(empleado.getSalario()));
            cs.setString(4, empleado.getCedula());
            cs.setString(5, empleado.getTelefono());
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

    public List verEmpleado(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_leer_empleados(?)";

        List<EmpleadoModel> infoEmpleado = new ArrayList();

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
                EmpleadoModel empleado = new EmpleadoModel();

                empleado.setNombre(rs.getString("Nombre"));
                empleado.setApellido(rs.getString("Apellido"));
                empleado.setSalario(rs.getLong("Salario"));
                empleado.setCedula(rs.getString("Cedula"));
                empleado.setTelefono(rs.getString("Telefono"));

                infoEmpleado.add(empleado);
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
        return infoEmpleado;
    }

    public int eliminarEmpleado(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_borrar_empleado(?)";
        try {

            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, cedula);
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

    public int actualizarEmpleado(EmpleadoModel empleado) throws SQLException, FileNotFoundException {
        String sql = "call sp_actualizar_empleado(?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            // Parámetro requerido
            cs.setString(1, empleado.getCedula());

            // Parámetros opcionales del cliente
            if (empleado.getNombre() != null) {
                cs.setString(2, empleado.getNombre());
            } else {
                cs.setNull(2, Types.VARCHAR);
            }

            if (empleado.getApellido() != null) {
                cs.setString(3, empleado.getApellido());
            } else {
                cs.setNull(3, Types.VARCHAR);
            }

            if (empleado.getSalario() != 0) {
                cs.setLong(4, (long) empleado.getSalario());
            } else {
                cs.setNull(4, Types.DECIMAL);
            }

            if (empleado.getTelefono() != null) {
                cs.setString(5, empleado.getTelefono());
            } else {
                cs.setNull(5, Types.VARCHAR);
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

    public int eliminarReserva(String cedula) throws SQLException, FileNotFoundException {
        String sql = "call sp_eliminar_resverva(?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, cedula);
            cs.setNull(2, Types.INTEGER);
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

