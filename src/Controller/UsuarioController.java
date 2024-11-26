/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.UsuarioDAO;
import Model.UsuarioModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
/**
 *
 * @author Supre
 */
// UsuarioController.java
public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public UsuarioModel obtenerUsuario(String nombre, String contrasena) throws FileNotFoundException, SQLException {
        // Llama al método del DAO que obtiene el usuario de la base de datos
        return usuarioDAO.obtenerUsuario(nombre, contrasena);
    }
}
