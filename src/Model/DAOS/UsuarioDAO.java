/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.UsuarioModel;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.sql.*;

/**
 *
 * @author Supre
 */
public class UsuarioDAO {

    Connection connection;
    CallableStatement cs;
    ResultSet rs;

    //leer el usuario para su auntenticacion.
    public UsuarioModel leerUsario(String email, String clave) throws SQLException, FileNotFoundException {
        UsuarioModel usuario = null;

        String sql = "call sp_leer_usuario(?,?)";
        try {
            connection = DBConnection.obtenerConexion();
            cs = connection.prepareCall(sql);
            cs.setString(1, email);
            cs.setString(2, clave);
            rs = cs.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel(email, clave);
                usuario.setEmail(rs.getString("email"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
            return usuario;
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
