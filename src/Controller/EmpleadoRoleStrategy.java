package Controller;

import Utils.Constants;
import Views.PaneldeRegistros;
import Views.frmDashboard;

/**
 * Empleado role strategy implementation.
 * Defines access permissions for employee users.
 *
 * @author ProyectoQuezada Team
 */
public class EmpleadoRoleStrategy implements IRoleStrategy {

    @Override
    public void configureDashboardAccess(frmDashboard dashboard) {
        // Empleado has limited access - cannot manage employees
        dashboard.getPanelRegistros().setEnabled(true);
        dashboard.getRegistrodeempleados().setEnabled(false);  // Cannot register employees
        dashboard.getRegistrodeclientes().setEnabled(true);
        dashboard.getRegistrodevehiculos().setEnabled(true);
        dashboard.getPaneldefacturacion().setEnabled(true);
        dashboard.getViewdereservas().setEnabled(true);
    }

    @Override
    public void configurePanelAccess(PaneldeRegistros panelRegistros) {
        // Empleado cannot see or manage employees, but can update other records
        panelRegistros.getTblEmpleados().setVisible(false);
        panelRegistros.getBtnregistroeliminar().setVisible(false);  // Cannot delete
        panelRegistros.getBtnregistroactualizar().setVisible(true);  // Can update
        panelRegistros.getTxtpanelregistroempleado().setEditable(false);
        panelRegistros.getTxtpanelregistroempleado().setVisible(false);
    }

    @Override
    public String getRoleName() {
        return Constants.Roles.EMPLEADO;
    }
}
