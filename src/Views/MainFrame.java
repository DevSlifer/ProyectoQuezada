package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private String rolUsuario;
    private String nombreUsuario;
    private JInternalFrame panelActivo;

    public MainFrame() {
        setTitle("Sistema de Gestión de Alquiler de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    private void initComponents() {
        // Inicializar el desktopPane con un color de fondo agradable
        desktopPane = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(240, 240, 245));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(desktopPane);
    }

    public void configurarSegunRol(String rol, String nombre) {
        this.rolUsuario = rol;
        this.nombreUsuario = nombre;
        setTitle("Sistema de Gestión - " + nombre + " (" + rol + ")");

        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo (común para todos)
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");
        menuItemCerrarSesion.addActionListener(e -> cerrarSesion());
        menuArchivo.add(menuItemCerrarSesion);
        menuBar.add(menuArchivo);

        // Menú Gestión
        JMenu menuGestion = new JMenu("Gestión");

        // Elementos comunes para ambos roles
        JMenuItem menuItemReservaciones = new JMenuItem("Reservaciones");
        JMenuItem menuItemVerReservas = new JMenuItem("Ver Reservas");
        JMenuItem menuItemClientes = new JMenuItem("Insertar Clientes");
        JMenuItem menuItemCarros = new JMenuItem("Insertar Caros");

        menuItemCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel(new frmPanelCarros());
            }
        });

        menuItemClientes.addActionListener(e -> {
            try {
                mostrarPanel(new frmPanelClientes());
            } catch (InstantiationException | ClassNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        menuItemReservaciones.addActionListener(e -> {
            try {
                mostrarPanel(new frmPanelReservaciones());
            } catch (InstantiationException | ClassNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuItemVerReservas.addActionListener(e -> {
            try {
                mostrarPanel(new frmPanelviewReservaClientes());
            } catch (InstantiationException | ClassNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuGestion.add(menuItemCarros);
        menuGestion.add(menuItemClientes);
        menuGestion.add(menuItemReservaciones);
        menuGestion.add(menuItemVerReservas);

        // Elementos solo para administradores
        if (rol.equalsIgnoreCase("admin")) {
            // Agregar Panel de Administración al principio
            JMenuItem menuItemAdmin = new JMenuItem("Panel de Administración");
            menuItemAdmin.addActionListener(e -> {
                try {
                    mostrarPanel(new frmPanelAdmin());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            menuGestion.insertSeparator(0);
            menuGestion.insert(menuItemAdmin, 0);

            // Resto de opciones admin
            //JMenuItem menuItemEmpleados = new JMenuItem("Gestión de Empleados");
            //JMenuItem menuItemFacturacion = new JMenuItem("Facturación");

            menuItemCarros.addActionListener(e -> mostrarPanel(new frmPanelCarros()));
            menuItemClientes.addActionListener(e -> {
                try {
                    mostrarPanel(new frmPanelClientes());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            /*menuItemEmpleados.addActionListener(e -> {
                try {
                    mostrarPanel(new frmPanelEmpleados());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            menuItemFacturacion.addActionListener(e -> {
                try {
                    mostrarPanel(new frmPaneldeFacturacion());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });*/

            menuGestion.add(new JSeparator());
            menuGestion.add(menuItemCarros);
            menuGestion.add(menuItemClientes);
            //menuGestion.add(menuItemEmpleados);
            //menuGestion.add(menuItemFacturacion);

            // Mostrar el panel de administración por defecto al iniciar sesión
            SwingUtilities.invokeLater(() -> {
                try {
                    mostrarPanel(new frmPanelAdmin());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        menuBar.add(menuGestion);

        // Menú Reportes (solo admin)
        if (rol.equalsIgnoreCase("admin")) {
            JMenu menuReportes = new JMenu("Reportes");
            // Aquí puedes agregar los items de reportes
            menuBar.add(menuReportes);
        }

        setJMenuBar(menuBar);
    }

    private void mostrarPanel(JInternalFrame nuevoPanel) {
        try {
            // Cerrar el panel activo si existe
            if (panelActivo != null && panelActivo.isVisible()) {
                panelActivo.dispose();
            }

            // Configurar el nuevo panel
            desktopPane.add(nuevoPanel);
            centrarPanel(nuevoPanel);
            nuevoPanel.setVisible(true);
            try {
                nuevoPanel.setSelected(true);
                nuevoPanel.requestFocus();
            } catch (java.beans.PropertyVetoException e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            }

            // Actualizar el panel activo
            panelActivo = nuevoPanel;

        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,
                    "Error al abrir el panel: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void centrarPanel(JInternalFrame frame) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = frame.getSize();

        // Si el frame no tiene tamaño establecido, darle un tamaño por defecto
        if (frameSize.width == 0 || frameSize.height == 0) {
            frameSize = new Dimension(800, 600);
            frame.setSize(frameSize);
        }

        frame.setLocation(
                (desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2
        );
    }

    private void cerrarSesion() {
        if (panelActivo != null) {
            panelActivo.dispose();
            panelActivo = null;
        }

        // Limpiar el menú
        setJMenuBar(null);

        // Ocultar el frame principal
        this.setVisible(false);

        // Mostrar el login
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
