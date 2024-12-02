package Controller;

import Model.UsuarioModel;
import Model.DAOS.UsuarioDAO;
import Views.*;
import Views.swing.MyPasswordField;
import Views.swing.MyTextField;
import Views.swing.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class LoginController implements ActionListener {

    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final MyTextField userText;
    private final MyPasswordField userPassword;
    private final Button btnIniciar;

    public LoginController(LoginView loginView, MyTextField userText,
            MyPasswordField userPassword, Button btnIniciar) {
        this.loginView = loginView;
        this.userText = userText;
        this.userPassword = userPassword;
        this.btnIniciar = btnIniciar;
        this.usuarioDAO = new UsuarioDAO();
        btnIniciar.addActionListener(this);
    }

    //Valiar que todos los campos esten llenos
    private boolean validarCredenciales() {
        if (userText.getText().trim().isEmpty()) {
            mostrarError("El campo de email no debe estar vacío", userText);
            return false;
        }
        if (userPassword.getText().trim().isEmpty()) {
            mostrarError("El campo de clave no debe estar vacío", userPassword);
            return false;
        }
        return true;
    }

    //Obtenr la informacion de los campos
    private void autenticarUsuario() {
        try {
            String email = userText.getText();
            String password = String.valueOf(userPassword.getPassword());

            UsuarioModel usuario = usuarioDAO.leerUsario(email, password);
            if (usuario != null) {
                iniciarSesion(usuario);
            } else {
                mostrarError("Credenciales incorrectas", null);
            }
        } catch (Exception ex) {
            mostrarError("Error de autenticación: " + ex.getMessage(), null);
        }
    }

    //Inicio de seccion
    private void iniciarSesion(UsuarioModel usuario) {
        try {
            loginView.setVisible(false);
            frmDashboard dashboard = new frmDashboard();

            // Configurar accesos según rol
            RoleManager.configurarAccesosPorRol(usuario.getRol(), dashboard, new PaneldeRegistros());

            // Inicializar el controlador del dashboard
            DashBoardController dashboardController = new DashBoardController(dashboard, usuario.getRol());

            dashboard.setVisible(true);

        } catch (Exception ex) {
            mostrarError("Error al iniciar sesión: " + ex.getMessage(), null);
        }
    }

    //Errores sorpresas
    private void mostrarError(String mensaje, JComponent componente) {
        JOptionPane.showMessageDialog(loginView, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
