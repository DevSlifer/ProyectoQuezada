package main;

import Controller.CarroController;
import Views.PaneldeRegistros;
import Views.frmDashboard;
import Views.RegistrodeCarros;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.sql.*;

public class Main {
    private final frmDashboard dashboard;
    private CarroController carroController;
    
    public Main() throws SQLException {
        this.dashboard = new frmDashboard();
        inicializarControladores();
    }
    
    private void inicializarControladores() throws SQLException {
        try {
            // Crear las vistas
            RegistrodeCarros registroCarros = new RegistrodeCarros();
            PaneldeRegistros panelRegistros = new PaneldeRegistros();
            // Crear el controlador pasando las vistas
            carroController = new CarroController(registroCarros, panelRegistros);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(dashboard,
                    "Error al inicializar la aplicación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Main main = new Main();
                main.dashboard.setLocationRelativeTo(null);
                main.dashboard.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error al iniciar la aplicación: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}