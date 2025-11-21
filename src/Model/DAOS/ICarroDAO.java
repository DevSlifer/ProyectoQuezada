package Model.DAOS;

import Model.CarroModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Carro Data Access Object.
 * Follows Interface Segregation Principle (ISP) and Dependency Inversion Principle (DIP).
 *
 * @author ProyectoQuezada Team
 */
public interface ICarroDAO {

    /**
     * Retrieves cars from the database.
     *
     * @param matricula The matricula to search for, or null to retrieve all cars
     * @return List of cars matching the criteria
     * @throws FileNotFoundException If configuration file is not found
     * @throws SQLException If a database error occurs
     */
    List<CarroModel> verCarros(String matricula) throws FileNotFoundException, SQLException;

    /**
     * Inserts a new car into the database.
     *
     * @param carro The car to insert
     * @return 1 if successful
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int insertarCarro(CarroModel carro) throws SQLException, FileNotFoundException;

    /**
     * Updates an existing car in the database.
     *
     * @param carro The car with updated information
     * @return 1 if successful
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int actualizarCarro(CarroModel carro) throws SQLException, FileNotFoundException;

    /**
     * Deletes a car from the database.
     *
     * @param matricula The matricula of the car to delete
     * @return 1 if successful, 0 otherwise
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    int eliminarCarro(String matricula) throws SQLException, FileNotFoundException;
}
