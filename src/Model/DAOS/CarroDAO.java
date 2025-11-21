/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOS;

import Model.CarroModel;
import Utils.Constants;
import ConexionBD.DBConnection;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Carro entity.
 * Refactored to implement ICarroDAO interface and follow SOLID principles.
 * Uses try-with-resources for proper resource management.
 *
 * @author ProyectoQuezada Team
 */
public class CarroDAO implements ICarroDAO {

    private static final Logger LOGGER = Logger.getLogger(CarroDAO.class.getName());

    /**
     * Retrieves cars from the database.
     * If matricula is null, returns all cars; otherwise returns specific car.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public List<CarroModel> verCarros(String matricula) throws FileNotFoundException, SQLException {
        List<CarroModel> infoCarros = new ArrayList<>();

        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_LEER_CARRO)) {

            if (matricula != null) {
                cs.setString(1, matricula);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    CarroModel carro = mapResultSetToCarro(rs);
                    infoCarros.add(carro);
                }
            }

            LOGGER.log(Level.INFO, "Retrieved {0} carro(s)", infoCarros.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving carros", e);
            throw e;
        }

        return infoCarros;
    }

    /**
     * Maps a ResultSet row to a CarroModel object.
     * Extracted to avoid code duplication and improve maintainability (DRY principle).
     */
    private CarroModel mapResultSetToCarro(ResultSet rs) throws SQLException {
        CarroModel carro = new CarroModel();
        carro.setIdCarro(rs.getInt("IDCarro"));
        carro.setMarca(rs.getString("Marca"));
        carro.setModelo(rs.getString("Modelo"));
        carro.setAnio(rs.getInt("Anio"));
        carro.setPlaca(rs.getString("Placa"));
        carro.setKilometraje(rs.getDouble("kilometraje"));
        carro.setPrecioPorDia(rs.getDouble("PrecioPorDia"));
        carro.setMatricula(rs.getString("Matricula"));
        return carro;
    }

    /**
     * Inserts a new car into the database.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int insertarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_INSERTAR_CARRO)) {

            cs.setString(1, carro.getMarca());
            cs.setString(2, carro.getModelo());
            cs.setInt(3, carro.getAnio());
            cs.setString(4, carro.getPlaca());
            cs.setDouble(5, carro.getKilometraje());
            cs.setDouble(6, carro.getPrecioPorDia());
            cs.setString(7, carro.getMatricula());
            cs.execute();

            LOGGER.log(Level.INFO, "Carro inserted successfully: {0}", carro.getMatricula());
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting carro", e);
            if (Constants.SQLStates.CUSTOM_ERROR.equals(e.getSQLState())) {
                throw new SQLException(e.getMessage());
            }
            throw e;
        }
    }

    /**
     * Updates an existing car in the database.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int actualizarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_ACTUALIZAR_CARRO)) {

            cs.setNull(1, Types.VARCHAR);  // marca, no cambia
            cs.setNull(2, Types.VARCHAR);  // modelo, no cambia
            cs.setNull(3, Types.INTEGER);  // año, no cambiar
            cs.setNull(4, Types.VARCHAR);  // placa, no cambia
            cs.setString(5, carro.getMatricula());  // matrícula, parámetro requerido
            cs.setInt(6, (int) carro.getPrecioPorDia());  // precio, campo actualizable
            cs.setDouble(7, carro.getKilometraje());  // actualización del kilometraje

            cs.execute();
            LOGGER.log(Level.INFO, "Carro updated successfully: {0}", carro.getMatricula());
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating carro", e);
            throw e;
        }
    }

    /**
     * Deletes a car from the database by matricula.
     * Uses try-with-resources for automatic resource management.
     */
    @Override
    public int eliminarCarro(String matricula) throws SQLException, FileNotFoundException {
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement cs = connection.prepareCall(Constants.SQL.SP_BORRAR_CARRO)) {

            cs.setString(1, matricula);
            cs.execute();

            LOGGER.log(Level.INFO, "Carro deleted successfully: {0}", matricula);
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting carro: " + matricula, e);
            throw e;
        }
    }
}
