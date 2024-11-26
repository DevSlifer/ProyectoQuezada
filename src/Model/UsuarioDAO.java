package Model;

import java.io.FileNotFoundException;
import java.sql.*;

public class UsuarioDAO {

    public UsuarioModel obtenerUsuario(String email, String contrasena) throws SQLException, FileNotFoundException {
        UsuarioModel usuario = null;
        Connection conn = DBConnection.obtenerConexion();

        String sql = "{CALL sp_leer_usuario(?, ?)}";
        CallableStatement cst = conn.prepareCall(sql);
        cst.setString(1, email);
        cst.setString(2, contrasena); // Contraseña en texto plano

        ResultSet rs = cst.executeQuery();

        if (rs.next()) {
            usuario = new UsuarioModel(contrasena, email);
            usuario.setEmail(rs.getString("email"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setRol(rs.getString("rol"));
            // Puedes asignar más campos si es necesario
        }

        rs.close();
        cst.close();

        return usuario;
    }
}
