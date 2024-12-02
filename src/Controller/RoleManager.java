/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Views.PaneldeRegistros;
import Views.frmDashboard;
/**
 *
 * @author Supre
 */
// RoleManager.java
//Clase para poder manejar los roles, admin y empleado
public class RoleManager {
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_EMPLEADO = "empleado";
    
    public static void configurarAccesosPorRol(String rol, frmDashboard dashboard, PaneldeRegistros panelRegistros) {
        switch(rol.toLowerCase()) {
            case ROLE_ADMIN:
                configurarAccesosAdmin(dashboard, panelRegistros);
                break;
            case ROLE_EMPLEADO:
                configurarAccesosEmpleado(dashboard, panelRegistros);
                break;
            default:
                throw new IllegalArgumentException("Rol no reconocido: " + rol);
        }
    }
    
    private static void configurarAccesosAdmin(frmDashboard dashboard, PaneldeRegistros panelRegistros) {
        // Accesos del menú principal
        dashboard.getPanelRegistros().setEnabled(true);
        dashboard.getRegistrodeempleados().setEnabled(true);
        dashboard.getRegistrodeclientes().setEnabled(true);
        dashboard.getRegistrodevehiculos().setEnabled(true);
        dashboard.getPaneldefacturacion().setEnabled(true);
        dashboard.getViewdereservas().setEnabled(true);
        
        // Accesos en PaneldeRegistros
        panelRegistros.getTblEmpleados().setVisible(true);
        panelRegistros.getBtnregistroeliminar().setVisible(true);
        panelRegistros.getBtnregistroactualizar().setVisible(true);
        panelRegistros.getTxtpanelregistroempleado().setEditable(true);
        panelRegistros.getTxtpanelregistroempleado().setVisible(true);
    }
    
    private static void configurarAccesosEmpleado(frmDashboard dashboard, PaneldeRegistros panelRegistros) {
        // Accesos del menú principal
        dashboard.getPanelRegistros().setEnabled(true);
        dashboard.getRegistrodeempleados().setEnabled(false);
        dashboard.getRegistrodeclientes().setEnabled(true);
        dashboard.getRegistrodevehiculos().setEnabled(true);
        dashboard.getPaneldefacturacion().setEnabled(true);
        dashboard.getViewdereservas().setEnabled(true);
        
        // Accesos restringidos en PaneldeRegistros
        panelRegistros.getTblEmpleados().setVisible(false);
        panelRegistros.getBtnregistroeliminar().setVisible(false);
        panelRegistros.getBtnregistroactualizar().setVisible(true);
        panelRegistros.getTxtpanelregistroempleado().setEditable(false);
        panelRegistros.getTxtpanelregistroempleado().setVisible(false);
    }
}
