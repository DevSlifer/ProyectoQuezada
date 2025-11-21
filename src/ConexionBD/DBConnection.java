package ConexionBD;

import Utils.Constants;
import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database connection manager using Singleton pattern.
 * Provides centralized connection management with proper resource handling.
 * Refactored to follow SOLID principles and best practices.
 *
 * @author ProyectoQuezada Team
 */
public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static Connection conexion = null;
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;

    // Private constructor to prevent instantiation
    private DBConnection() {
        throw new UnsupportedOperationException("DBConnection is a utility class and cannot be instantiated");
    }

    /**
     * Gets a database connection. Creates a new connection if one doesn't exist
     * or if the existing connection is closed.
     *
     * @return Database connection
     * @throws FileNotFoundException If configuration file is not found
     * @throws SQLException If a database access error occurs
     */
    public static synchronized Connection obtenerConexion() throws FileNotFoundException, SQLException {
        if (conexion != null && !conexion.isClosed()) {
            return conexion;
        }

        if (databaseUrl == null || databaseUser == null || databasePassword == null) {
            loadDatabaseConfiguration();
        }

        try {
            conexion = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            conexion.setAutoCommit(true);
            LOGGER.log(Level.INFO, "Database connection established successfully");
            return conexion;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish database connection", e);
            throw new SQLException(Constants.ErrorMessages.CONNECTION_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Loads database configuration from the configuration file.
     * Uses proper resource management with try-with-resources.
     *
     * @throws FileNotFoundException If configuration file is not found
     */
    private static void loadDatabaseConfiguration() throws FileNotFoundException {
        String directorioActual = System.getProperty("user.dir");
        String rutaArchivo = directorioActual + File.separator + Constants.Database.CONFIG_FILE_PATH;
        File archivo = new File(rutaArchivo);

        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            databaseUrl = buffer.readLine();
            databaseUser = buffer.readLine();
            databasePassword = buffer.readLine();

            if (databaseUrl == null || databaseUser == null || databasePassword == null) {
                throw new IllegalStateException("Configuration file is incomplete");
            }

            LOGGER.log(Level.INFO, "Database configuration loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading database configuration file", e);
            throw new FileNotFoundException("Could not read configuration file: " + e.getMessage());
        }
    }

    /**
     * Closes the database connection if it exists and is open.
     *
     * @throws SQLException If a database access error occurs
     */
    public static synchronized void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            try {
                conexion.close();
                LOGGER.log(Level.INFO, "Database connection closed successfully");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing database connection", e);
                throw e;
            } finally {
                conexion = null;
            }
        }
    }

    /**
     * Safely closes a ResultSet without throwing exceptions.
     *
     * @param rs The ResultSet to close
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing ResultSet", e);
            }
        }
    }

    /**
     * Safely closes a Statement without throwing exceptions.
     *
     * @param stmt The Statement to close
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing Statement", e);
            }
        }
    }

    /**
     * Safely closes a Connection without throwing exceptions.
     *
     * @param conn The Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing Connection", e);
            }
        }
    }
}
