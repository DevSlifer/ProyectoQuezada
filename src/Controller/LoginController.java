package Controller;

import Model.UsuarioModel;
import Model.DAOS.UsuarioDAO;
import Views.LoginView;
import Views.swing.MyPasswordField;
import Views.swing.MyTextField;
import Views.swing.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Views.frmDashboard;
import Views.PaneldeRegistros;
import Views.RegistrodeCarros;
import Views.component.PanelLoginAndRegister;

public class LoginController implements ActionListener {

    private final UsuarioDAO usuarioDAO;
    private final LoginView LV;
    private final MyTextField userText;
    private final MyPasswordField userPassword;
    private final Button btnIniciar;

    public LoginController(LoginView loginView, MyTextField userText, MyPasswordField userPassword, Button btnIniciar) {
        this.LV = loginView;
        this.userText = userText;
        this.userPassword = userPassword;
        this.btnIniciar = btnIniciar;
        this.usuarioDAO = new UsuarioDAO();
        btnIniciar.addActionListener(this);
    }

    private boolean validaCampos(MyTextField email, MyPasswordField password) {
        if (email.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(email, "El campo de email no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            email.requestFocus();
            return false;
        }
        if (password.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(password, "El campo de clave no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            password.requestFocus();
            return false;
        }
        return true;
    }

    private void configurarAccesosSegunRol(String rol, PaneldeRegistros panelRegistros) {
        boolean esEmpleado = "empleado".equals(rol);
        panelRegistros.getTblEmpleados().setVisible(!esEmpleado);
        panelRegistros.getBtnregistroeliminar().setVisible(!esEmpleado);
        panelRegistros.getTxtpanelregistroempleado().setEditable(!esEmpleado);
    }

    private void limpiarCampos() {
        userText.setText("");
        userPassword.setText("");
        userText.requestFocusInWindow();
    }

    public static void main(String[] args) {
        try {
            LoginView loginView = new LoginView();

            PanelLoginAndRegister loginPanel = loginView.getLoginAndRegister();
            MyTextField userText = loginPanel.getTxtEmail();
            MyPasswordField userPassword = loginPanel.getTxtPass();
            Button btnIniciar = loginPanel.getBtnLogin();

            LoginController loginController = new LoginController(loginView, userText, userPassword, btnIniciar);

            loginView.setVisible(true);


        } catch (Exception e) {
            System.err.println("Error al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar && validaCampos(userText, userPassword)) {
            String email = userText.getText();
            String password = String.valueOf(userPassword.getPassword());
            try {
                UsuarioModel usuario = usuarioDAO.leerUsario(email, password);
                if (usuario != null) {
                    LV.setVisible(false);

                    // Crear el dashboard y las vistas necesarias
                    frmDashboard frm = new frmDashboard();
                    PaneldeRegistros panelRegistros = new PaneldeRegistros();
                    RegistrodeCarros registroCarros = new RegistrodeCarros();

                    // Inicializar el controlador de carros
                    CarroController carroController = new CarroController(registroCarros, panelRegistros);



                    // Configurar accesos según rol
                    configurarAccesosSegunRol(usuario.getRol(), panelRegistros);

                    // Agregar los paneles al desktop y mostrar el dashboard
                    frm.escritorio.add(panelRegistros);
                    frm.setVisible(true);

                    // Forzar la actualización de la tabla de carros
                    carroController.listarCarros();

                } else {
                    JOptionPane.showMessageDialog(LV, "Credenciales incorrectas!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(LV, "Error: " + ex.getMessage());
            }
        }
    }
}
