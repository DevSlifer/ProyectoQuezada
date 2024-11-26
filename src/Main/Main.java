package Main;

import Controller.LoginController;
import Views.MainFrame;
import Views.LoginView;


public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        LoginView loginView = new LoginView();

        LoginController loginController = new LoginController(loginView, mainFrame);

        loginView.setVisible(true); // Mostrar la ventana de login inicialmente
    }
}


