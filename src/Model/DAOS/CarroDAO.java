
   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.CarroModel;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Supre
 */

/*
Data Access objtects para los carros.
*/
public class CarroDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;

    public List verCarros(String matricula) throws FileNotFoundException, SQLException {
        var sql = "call sp_leer_carro(?)"; 
        //Procedure para buscar todos los carros
        //Si el parametro es "null", este listara todos
        //Sino solamente buscara por la matricula
        List<CarroModel> infoCarros = new ArrayList();

        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql); 
            if (matricula != null) {
                cs.setString(1, matricula);
            } else {
                cs.setNull(1, Types.VARCHAR); //Seteandolo a "Null"
            }
            rs = cs.executeQuery();

            //Toda la informacion de los carros
            while (rs.next()) {
                CarroModel carro = new CarroModel();
                carro.setIdCarro(rs.getInt("IDCarro"));
                carro.setMarca(rs.getString("Marca"));
                carro.setModelo(rs.getString("Modelo"));
                carro.setAnio(rs.getInt("Anio"));
                carro.setPlaca(rs.getString("Placa"));
                carro.setKilometraje(rs.getDouble("kilometraje"));
                carro.setPrecioPorDia(rs.getDouble("PrecioPorDia"));
                carro.setMatricula(rs.getString("Matricula"));
                infoCarros.add(carro);
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
        return infoCarros;
    }

    //Metodo para agregar los carros.
    public int insertarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String sql = "call sp_insertar_carro(?,?,?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, carro.getMarca());
            cs.setString(2, carro.getModelo());
            cs.setInt(3, carro.getAnio());
            cs.setString(4, carro.getPlaca());
            cs.setDouble(5, carro.getKilometraje());
            cs.setDouble(6, carro.getPrecioPorDia());
            cs.setString(7, carro.getMatricula());

            cs.execute();
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

    //Metodo paara actualizar carros
    public int actualizarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String sql = "call sp_actualizar_carro(?,?,?,?,?,?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);

            cs.setNull(1, Types.VARCHAR);  // marca, no cambia
            cs.setNull(2, Types.VARCHAR);  // modelo, no cambia
            cs.setNull(3, Types.INTEGER);  // año, no cambiar
            cs.setNull(4, Types.VARCHAR);  // placa, no cambia
            cs.setString(5, carro.getMatricula());  // matrícula, para metro requerido
            cs.setInt(6, (int) carro.getPrecioPorDia());  // precio, campo actualizable
            cs.setDouble(7, carro.getKilometraje());  // actualizalacion del kilometraje

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

    //Para borrar por matrcula
    public int eliminarCarro(String matricula) throws SQLException, FileNotFoundException {
        String sql = "call sp_borrar_carro(?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, matricula);

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
