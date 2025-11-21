/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.UsuarioModel;
import Utils.Constants;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Usuario entity.
 * Refactored to implement IUsuarioDAO interface and follow SOLID principles.
 * Uses try-with-resources for proper resource management.
 *
 * @author ProyectoQuezada Team
 */
public class UsuarioDAO implements IUsuarioDAO {

    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

    /**
     * Authenticates a user by email and password.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public UsuarioModel leerUsario(String email, String clave) throws SQLException, FileNotFoundException {
        UsuarioModel usuario = null;

        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_LEER_USUARIO)) {

            cs.setString(1, email);
            cs.setString(2, clave);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioModel(email, clave);
                    usuario.setEmail(rs.getString("email"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setRol(rs.getString("rol"));

                    LOGGER.log(Level.INFO, "User authenticated successfully: {0}", email);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error authenticating user: " + email, e);
            throw e;
        }

        return usuario;
    }

    /**
     * Inserts a new user into the database with 'empleado' role.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int insertarUsuario(UsuarioModel usuario) throws FileNotFoundException, SQLException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_INSERTAR_USUARIO)) {

            cs.setString(1, usuario.getEmail());
            cs.setString(2, usuario.getContrasena());
            cs.setString(3, Constants.Roles.EMPLEADO);
            cs.execute();

            LOGGER.log(Level.INFO, "User inserted successfully: {0}", usuario.getEmail());
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting user", e);
            if (Constants.SQLStates.CUSTOM_ERROR.equals(e.getSQLState())) {
                throw new SQLException(e.getMessage());
            }
            throw e;
        }
    }
}
