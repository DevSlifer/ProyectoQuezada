package Model.DAOS;

import Model.ClienteModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Cliente Data Access Object.
 * Follows Interface Segregation Principle (ISP) and Dependency Inversion Principle (DIP).
 * Allows for different implementations and easier testing with mocks.
 *
 * @author ProyectoQuezada Team
 */
public interface IClienteDAO {

    /**
     * Inserts a new client into the database.
     *
     * @param cliente The client to insert
     * @return 1 if successful
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int insertarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException;

    /**
     * Retrieves clients from the database.
     *
     * @param cedula The cedula to search for, or null to retrieve all clients
     * @return List of clients matching the criteria
     * @throws FileNotFoundException If configuration file is not found
     * @throws SQLException If a database error occurs
     */
    List<ClienteModel> verCliente(String cedula) throws FileNotFoundException, SQLException;

    /**
     * Updates an existing client in the database.
     *
     * @param cliente The client with updated information
     * @return 1 if successful
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int actualizarCliente(ClienteModel cliente) throws SQLException, FileNotFoundException;

    /**
     * Deletes a client from the database.
     *
     * @param cedula The cedula of the client to delete
     * @return 1 if successful, 0 otherwise
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int eliminarCliente(String cedula) throws SQLException, FileNotFoundException;
}
