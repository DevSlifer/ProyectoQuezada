package Main;

import Views.LoginView;
import Views.component.PanelLoginAndRegister;
import Controller.LoginController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            PanelLoginAndRegister loginPanel = loginView.getLoginAndRegister();
            
            // Inicializar el controlador de login
            LoginController loginController = new LoginController(
                loginView,
                loginPanel.getTxtEmail(),
                loginPanel.getTxtPass(),
                loginPanel.getBtnLogin()
            );
            
            // Mostrar ventana de login
            loginView.setVisible(true);
        });
    }
}