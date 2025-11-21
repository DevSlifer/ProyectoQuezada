package Controller;

import Utils.Constants;
import Views.PaneldeRegistros;
import Views.frmDashboard;

/**
 * Admin role strategy implementation.
 * Defines access permissions for admin users.
 *
 * @author ProyectoQuezada Team
 */
public class AdminRoleStrategy implements IRoleStrategy {

    @Override
    public void configureDashboardAccess(frmDashboard dashboard) {
        // Admin has access to all menu items
        dashboard.getPanelRegistros().setEnabled(true);
        dashboard.getRegistrodeempleados().setEnabled(true);
        dashboard.getRegistrodeclientes().setEnabled(true);
        dashboard.getRegistrodevehiculos().setEnabled(true);
        dashboard.getPaneldefacturacion().setEnabled(true);
        dashboard.getViewdereservas().setEnabled(true);
    }

    @Override
    public void configurePanelAccess(PaneldeRegistros panelRegistros) {
        // Admin can see and manage employees
        panelRegistros.getTblEmpleados().setVisible(true);
        panelRegistros.getBtnregistroeliminar().setVisible(true);
        panelRegistros.getBtnregistroactualizar().setVisible(true);
        panelRegistros.getTxtpanelregistroempleado().setEditable(true);
        panelRegistros.getTxtpanelregistroempleado().setVisible(true);
    }

    @Override
    public String getRoleName() {
        return Constants.Roles.ADMIN;
    }
}
