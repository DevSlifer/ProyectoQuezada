/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utils.Constants;
import Views.PaneldeRegistros;
import Views.frmDashboard;
import java.util.HashMap;
import java.util.Map;

/**
 * Role Manager using Strategy Pattern.
 * Refactored to follow Open/Closed Principle (OCP) - open for extension, closed for modification.
 * New roles can be added by creating new strategy implementations without modifying this class.
 *
 * @author ProyectoQuezada Team
 */
public class RoleManager {

    private static final Map<String, IRoleStrategy> roleStrategies = new HashMap<>();

    // Static initialization block to register role strategies
    static {
        registerRoleStrategy(new AdminRoleStrategy());
        registerRoleStrategy(new EmpleadoRoleStrategy());
    }

    private RoleManager() {
        throw new UnsupportedOperationException("RoleManager is a utility class and cannot be instantiated");
    }

    /**
     * Registers a new role strategy.
     * Allows adding new roles without modifying existing code (OCP).
     *
     * @param strategy The role strategy to register
     */
    public static void registerRoleStrategy(IRoleStrategy strategy) {
        roleStrategies.put(strategy.getRoleName().toLowerCase(), strategy);
    }

    /**
     * Configures access based on user role using the Strategy pattern.
     *
     * @param rol The user's role
     * @param dashboard The dashboard to configure
     * @param panelRegistros The panel to configure
     * @throws IllegalArgumentException if role is not recognized
     */
    public static void configurarAccesosPorRol(String rol, frmDashboard dashboard, PaneldeRegistros panelRegistros) {
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede ser nulo o vacío");
        }

        IRoleStrategy strategy = roleStrategies.get(rol.toLowerCase());

        if (strategy == null) {
            throw new IllegalArgumentException("Rol no reconocido: " + rol);
        }

        strategy.configureDashboardAccess(dashboard);
        strategy.configurePanelAccess(panelRegistros);
    }

    /**
     * Gets the role strategy for a given role.
     *
     * @param rol The role name
     * @return The role strategy, or null if not found
     */
    public static IRoleStrategy getRoleStrategy(String rol) {
        if (rol == null) {
            return null;
        }
        return roleStrategies.get(rol.toLowerCase());
    }

    // Backward compatibility - deprecated constants
    @Deprecated
    public static final String ROLE_ADMIN = Constants.Roles.ADMIN;

    @Deprecated
    public static final String ROLE_EMPLEADO = Constants.Roles.EMPLEADO;
}
