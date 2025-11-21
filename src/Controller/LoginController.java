package Controller;

import Model.UsuarioModel;
import Model.DAOS.IUsuarioDAO;
import Model.DAOS.UsuarioDAO;
import Utils.Constants;
import Utils.ValidationUtils;
import Views.*;
import Views.swing.MyPasswordField;
import Views.swing.MyTextField;
import Views.swing.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

/**
 * Controller for login functionality.
 * Refactored to follow SOLID principles and use dependency injection.
 *
 * @author ProyectoQuezada Team
 */
public class LoginController implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    private final IUsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final MyTextField userText;
    private final MyPasswordField userPassword;
    private final Button btnIniciar;

    /**
     * Constructor with dependency injection.
     * Follows Dependency Inversion Principle (depends on IUsuarioDAO interface, not concrete class).
     */
    public LoginController(LoginView loginView, MyTextField userText,
            MyPasswordField userPassword, Button btnIniciar) {
        this(loginView, userText, userPassword, btnIniciar, new UsuarioDAO());
    }

    /**
     * Constructor with full dependency injection for testing.
     */
    public LoginController(LoginView loginView, MyTextField userText,
            MyPasswordField userPassword, Button btnIniciar, IUsuarioDAO usuarioDAO) {
        this.loginView = loginView;
        this.userText = userText;
        this.userPassword = userPassword;
        this.btnIniciar = btnIniciar;
        this.usuarioDAO = usuarioDAO;
        btnIniciar.addActionListener(this);
    }

    /**
     * Validates user credentials.
     * Uses ValidationUtils to follow Single Responsibility Principle.
     */
    private boolean validarCredenciales() {
        String email = userText.getText();
        String password = String.valueOf(userPassword.getPassword());

        if (!ValidationUtils.isNotEmpty(email)) {
            mostrarError(Constants.ErrorMessages.EMPTY_EMAIL, userText);
            return false;
        }

        if (!ValidationUtils.isNotEmpty(password)) {
            mostrarError(Constants.ErrorMessages.EMPTY_PASSWORD, userPassword);
            return false;
        }

        return true;
    }

    /**
     * Authenticates user with provided credentials.
     */
    private void autenticarUsuario() {
        try {
            String email = userText.getText().trim();
            String password = String.valueOf(userPassword.getPassword());

            UsuarioModel usuario = usuarioDAO.leerUsario(email, password);
            if (usuario != null) {
                iniciarSesion(usuario);
            } else {
                mostrarError(Constants.ErrorMessages.INVALID_CREDENTIALS, null);
                LOGGER.log(Level.WARNING, "Failed login attempt for email: {0}", email);
            }
        } catch (Exception ex) {
            mostrarError(Constants.ErrorMessages.AUTHENTICATION_ERROR + ": " + ex.getMessage(), null);
            LOGGER.log(Level.SEVERE, "Authentication error", ex);
        }
    }

    /**
     * Initializes user session and opens dashboard.
     */
    private void iniciarSesion(UsuarioModel usuario) {
        try {
            loginView.setVisible(false);
            frmDashboard dashboard = new frmDashboard();

            // Configurar accesos según rol usando Strategy Pattern
            RoleManager.configurarAccesosPorRol(usuario.getRol(), dashboard, new PaneldeRegistros());

            // Inicializar el controlador del dashboard
            DashBoardController dashboardController = new DashBoardController(dashboard, usuario.getRol());

            dashboard.setVisible(true);
            LOGGER.log(Level.INFO, "User logged in successfully: {0} with role: {1}",
                    new Object[]{usuario.getEmail(), usuario.getRol()});

        } catch (Exception ex) {
            mostrarError("Error al iniciar sesión: " + ex.getMessage(), null);
            LOGGER.log(Level.SEVERE, "Error opening dashboard", ex);
            loginView.setVisible(true); // Show login again if dashboard fails
        }
    }

    /**
     * Displays error message to user.
     * Uses constants for dialog titles.
     */
    private void mostrarError(String mensaje, JComponent componente) {
        JOptionPane.showMessageDialog(loginView, mensaje,
                Constants.DialogTitles.ERROR, JOptionPane.ERROR_MESSAGE);
        if (componente != null) {
            componente.requestFocus();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar && validarCredenciales()) {
            autenticarUsuario();
        }
    }
}
