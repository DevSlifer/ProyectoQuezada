package Controller;

import Views.LoginView;
import Views.MainFrame;
import Model.UsuarioDAO;
import Model.UsuarioModel;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private final LoginView loginView;
    private final MainFrame mainFrame;
    private final UsuarioDAO usuarioDAO;

    public LoginController(LoginView loginView, MainFrame mainFrame) {
        this.loginView = loginView;
        this.mainFrame = mainFrame;
        this.usuarioDAO = new UsuarioDAO();

        this.loginView.addIngresarListener((ActionEvent e) -> {
            try {
                autenticarUsuario();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void autenticarUsuario() throws FileNotFoundException {
        String email = loginView.getUsuario().trim();
        String contrasena = loginView.getContrasena().trim();

        if (email.isEmpty() || contrasena.isEmpty()) {
            loginView.mostrarMensaje("Por favor, ingrese correo y contraseña.");
            return;
        }

        try {
            UsuarioModel usuario = usuarioDAO.obtenerUsuario(email, contrasena);
            if (usuario != null) {
                loginView.mostrarMensaje("Bienvenido, " + usuario.getEmail() + "!");
                loginView.dispose(); // Cierra el LoginView

                switch (usuario.getRol().toLowerCase()) {
                    case "admin" -> mainFrame.mostrarAdminPanel();
                    case "empleado" -> mainFrame.mostrarEmpleadoPanel();
                    case "cliente" -> mainFrame.mostrarClientePanel();
                    default -> throw new IllegalArgumentException("Rol no reconocido: " + usuario.getRol());
                }
            } else {
                loginView.mostrarMensaje("Correo o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            loginView.mostrarMensaje("Error al conectar con la base de datos.");
        }
    }
}
