package Controller;

import Views.PaneldeRegistros;
import Views.frmDashboard;

/**
 * Strategy interface for role-based access control.
 * Follows Open/Closed Principle - open for extension, closed for modification.
 * New roles can be added by creating new implementations without modifying existing code.
 *
 * @author ProyectoQuezada Team
 */
public interface IRoleStrategy {

    /**
     * Configures dashboard access based on the role.
     *
     * @param dashboard The dashboard to configure
     */
    void configureDashboardAccess(frmDashboard dashboard);

    /**
     * Configures panel access based on the role.
     *
     * @param panelRegistros The panel to configure
     */
    void configurePanelAccess(PaneldeRegistros panelRegistros);

    /**
     * Gets the role name.
     *
     * @return The role name
     */
    String getRoleName();
}
