package Controller;

import Views.*;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class DashBoardController {

    /*
    Todos los panales son JInternalFrames por eso un controlador para 
    traer todos los paneles creando una sola instancia del dashboard
     */
    private final frmDashboard dashboard;
    private final String rol;

    // Paneles
    private PaneldeRegistros panelRegistros;
    private RegistrodeCarros registroCarros;
    private RegistrodeClientes registroClientes;
    private RegistrodeEmpleados registroEmpleados;
    private PaneldeFacturacion1 panelFacturacion;
    private ViewReservacionesdeClientes viewReservaciones;
    private PaneldeReservaciones panelReservaciones;
    private AcercaDE acercaDe;

    // Controladores
    private CarroController carroController;
    private ClienteController clienteController;
    private EmpleadoController empleadoController;
    private FacturaController facturaController;
    private ReservaController reservaController;

    public DashBoardController(frmDashboard dashboard, String rol) {
        this.dashboard = dashboard;
        this.rol = rol;
        inicializarPaneles();
        configurarControladores();
        configurarMenuListeners();
    }

    private void inicializarPaneles() {
        panelRegistros = new PaneldeRegistros();
        registroCarros = new RegistrodeCarros();
        registroClientes = new RegistrodeClientes();
        registroEmpleados = new RegistrodeEmpleados();
        panelFacturacion = new PaneldeFacturacion1();
        viewReservaciones = new ViewReservacionesdeClientes();
        panelReservaciones = new PaneldeReservaciones();
    }

    private void configurarControladores() {
        try {
            carroController = new CarroController(registroCarros, panelRegistros);
            clienteController = new ClienteController(panelRegistros, registroClientes);
            empleadoController = new EmpleadoController(registroEmpleados, panelRegistros);
            facturaController = new FacturaController(panelFacturacion);
            reservaController = new ReservaController(viewReservaciones, panelReservaciones);
            dashboard.getAcercaDe().addMenuListener(new MenuAcercaDeListener());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboard,
                    "Error al inicializar controladores: " + e.getMessage());
        }
    }

    //Configuracion de los botones, para quu tengan acciones
    private void configurarMenuListeners() {
        dashboard.getPanelRegistros().addActionListener(e -> mostrarPanel(panelRegistros, "Panel de Registros"));
        dashboard.getRegistrodevehiculos().addActionListener(e -> mostrarPanel(registroCarros, "Registro de Vehículos"));
        dashboard.getRegistrodeclientes().addActionListener(e -> mostrarPanel(registroClientes, "Registro de Clientes"));
        dashboard.getRegistrodeempleados().addActionListener(e -> mostrarPanel(registroEmpleados, "Registro de Empleados"));
        dashboard.getPaneldefacturacion1().addActionListener(e -> mostrarPanel(panelFacturacion, "Panel de Facturación"));
        dashboard.getRegistrodereservaciones().addActionListener(e -> mostrarPanel(panelReservaciones, "Registro de Reservaciones"));
        dashboard.getViewdereservas().addActionListener(e -> mostrarPanel(viewReservaciones, "Ver Reservaciones"));
    }

    //Mostrar todos los panales
    private void mostrarPanel(JInternalFrame panel, String titulo) {
        if (!panel.isVisible()) {
            panel.setTitle(titulo);
            panel.setBounds(150, 50, 1000, 500);
            panel.setVisible(true);
            dashboard.getEscritorio().add(panel);
            panel.toFront();
        } else {
            panel.toFront();
        }
    }

    //EL listener del "Acerca De"
    private class MenuAcercaDeListener implements javax.swing.event.MenuListener {

        @Override
        public void menuSelected(javax.swing.event.MenuEvent evt) {
            mostrarAcercaDe();
        }

        @Override
        public void menuDeselected(javax.swing.event.MenuEvent evt) {
        }

        @Override
        public void menuCanceled(javax.swing.event.MenuEvent evt) {
        }
    }

    private void mostrarAcercaDe() {
        if (acercaDe == null || !acercaDe.isVisible()) {
            acercaDe = new AcercaDE();
            mostrarPanel(acercaDe, "Acerca de");
        } else {
            acercaDe.toFront();
        }
    }
}
