/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.FacturaModel;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import Model.ClienteModel;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Supre
 */
public class FacturaDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;

    //Insertar una nueva factura
    public int insertarFactura(FacturaModel factura) throws SQLException, FileNotFoundException {
        String sql = "call sp_insertar_factura(?,?,?,?,?)";
        try {

            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, factura.getCliente().getCedula());
            cs.setDate(2, new java.sql.Date(factura.getReserva().getFechaDeEntrega().getTime()));
            cs.setDate(3, new java.sql.Date(factura.getReserva().getFechaDevolucion().getTime()));
            cs.setInt(4, factura.getCargosAdicionales());
            cs.setDate(5, new java.sql.Date(factura.getFechaDePago().getTime()));
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

    //ver todas las facturas
    public List verFactura(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_leer_factura(?)"; //Parametro "Opcional", si es null, este 
        //Traera todas las facturas sino, buscara por la cedula.

        List<FacturaModel> infoFactura = new ArrayList();

        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            if (cedula != null) {
                cs.setString(1, cedula);
            } else {
                cs.setNull(1, Types.VARCHAR);//Parametro null
            }
            rs = cs.executeQuery();

            while (rs.next()) {
                FacturaModel factura = new FacturaModel();
                ClienteModel cliente = new ClienteModel();
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setCedula(rs.getString("Cedula"));

                factura.setCliente(cliente);

                factura.setMonto(rs.getDouble("Monto"));
                factura.setCargosAdicionales((int) rs.getDouble("Cargos_Adicionales"));
                factura.setFechaDePago(rs.getDate("Fecha_de_Pago"));
                infoFactura.add(factura);
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
        return infoFactura;
    }

    /*
    Actualizar factura por el cliente
     */
    public int actualizarFactura(FacturaModel factura) throws SQLException, FileNotFoundException {
        String sql = "call sp_actualizar_factura(?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            // Parámetro requerido, la cual es cedula
            cs.setString(1, factura.getCliente().getCedula());

            if (factura.getMonto() != 0) {
                cs.setLong(2, (long) factura.getMonto());
            } else {
                cs.setNull(2, Types.DECIMAL);
            }

            if (factura.getCargosAdicionales() != 0) {
                cs.setLong(3, factura.getCargosAdicionales());
            } else {
                cs.setNull(3, Types.DECIMAL);
            }

            if (factura.getFechaDePago() != null) {
                cs.setDate(4, new java.sql.Date(factura.getFechaDePago().getTime()));
            } else {
                cs.setNull(4, Types.VARCHAR);
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

    //ELiminar factura por cliente
    public int eliminarFactura(String cedula) throws SQLException, FileNotFoundException {
        String sql = "call sp_eliminar_factura(?,?)";
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

    //Llamar a la function del mysql
    public double calcularMontoPorCedula(String cedula, java.util.Date fechaInicio, java.util.Date fechaFin) throws SQLException, FileNotFoundException {
        String sql = "SELECT fn_calcular_monto_por_cedula(?, ?, ?) as monto";
        double monto = 0;

        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, cedula);

            // Convertir java.util.Date a java.sql.Date
            java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date sqlFechaFin = new java.sql.Date(fechaFin.getTime());

            cs.setDate(2, sqlFechaInicio);
            cs.setDate(3, sqlFechaFin);

            rs = cs.executeQuery();

            if (rs.next()) {
                monto = rs.getDouble("monto");
            }
            return monto;
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
    }
}
