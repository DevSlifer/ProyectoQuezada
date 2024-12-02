/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.ReservaModel;
import ConexionBD.DBConnection;
import Model.CarroModel;
import Model.ClienteModel;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Supre
 */
public class ReservaDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;
    
    //Insertar reserva mediante la placa del vehiculo y la cedula del cliente
    public int insertarReserva(ReservaModel reserva) throws SQLException, FileNotFoundException {
        String sql = "call sp_insertar_reserva(?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            cs.setString(1, reserva.getCliente().getCedula());
            cs.setString(2, reserva.getCarro().getPlaca());
            cs.setDate(3, new java.sql.Date(reserva.getFechaDeEntrega().getTime()));
            cs.setDate(4, new java.sql.Date(reserva.getFechaDevolucion().getTime()));

            boolean hasResults = cs.execute();
            return 1;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) {
                throw new SQLException(e.getMessage());
            }
            throw e;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Ver todas las reservas
    public List verReserva(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_leer_reservas(?,?)";
        List<ReservaModel> infoReserva = new ArrayList();
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            if (cedula != null) {
                cs.setString(1, cedula);
                cs.setNull(2, Types.VARCHAR); // Para el segundo parámetro del SP
            } else {
                cs.setNull(1, Types.VARCHAR);
                cs.setNull(2, Types.VARCHAR);
            }
            rs = cs.executeQuery();
            while (rs.next()) {
                ReservaModel reserva = new ReservaModel();
                ClienteModel cliente = new ClienteModel();
                CarroModel carro = new CarroModel();

                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setTelefono(rs.getString("Telefono"));

                carro.setMarca(rs.getString("Marca"));
                carro.setModelo(rs.getString("Modelo"));
                carro.setPlaca(rs.getString("Placa"));
                carro.setPrecioPorDia(rs.getDouble("PrecioPorDia"));

                reserva.setFechaReservacion(rs.getDate("FechaReservacion"));
                reserva.setFechaDeEntrega(rs.getDate("FechaDeEntrega"));
                reserva.setFechaDevolucion(rs.getDate("FechaDevolucion"));

                reserva.setDiasTotal(rs.getInt("DiasTotal"));
                reserva.setMontoEstimado(rs.getInt("MontoEstimado"));

                reserva.setCliente(cliente);
                reserva.setCarro(carro);

                infoReserva.add(reserva);
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
        return infoReserva;
    }

    //Actualizar reservas
    public int actualizarReserva(ReservaModel reserva) throws SQLException, FileNotFoundException {
        String sql = "call sp_actualizar_reserva(?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            // Parámetro requerido
            cs.setString(1, reserva.getCliente().getCedula());

            // Parámetros opcionales
            if (reserva.getCarro() != null && reserva.getCarro().getPlaca() != null) {
                cs.setString(2, reserva.getCarro().getPlaca());
            } else {
                cs.setNull(2, Types.VARCHAR); //Seteo de nuulo
            }

            if (reserva.getFechaDeEntrega() != null) {
                cs.setDate(3, new java.sql.Date(reserva.getFechaDeEntrega().getTime()));
            } else {
                cs.setNull(3, Types.DATE);
            }

            if (reserva.getFechaDevolucion() != null) {
                cs.setDate(4, new java.sql.Date(reserva.getFechaDevolucion().getTime()));
            } else {
                cs.setNull(4, Types.DATE);
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

    //Eliminar reserva de un cliente
    public int eliminarReserva(String cedula) throws FileNotFoundException, SQLException {
        String sql = "call sp_eliminar_reserva(?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, cedula);
            cs.setNull(2, Types.VARCHAR);
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
}
