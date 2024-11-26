package Model;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CarroDAO {

    private static final Logger LOGGER = Logger.getLogger(CarroDAO.class.getName());

    public void insertarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_insertar_carro(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.obtenerConexion(); CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAnio());
            stmt.setString(4, carro.getNumPlaca());
            stmt.setInt(5, (int) carro.getKilometraje());
            stmt.setString(6, carro.getMatricula());

            stmt.execute();
        }
    }

    public List<CarroModel> obtenerTodosLosCarros() throws SQLException, FileNotFoundException {
        List<CarroModel> carros = new ArrayList<>();
        String query = "{CALL sp_leer_carro(null)}";

        try (Connection conn = DBConnection.obtenerConexion(); CallableStatement stmt = conn.prepareCall(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                carros.add(mapearResultSetACarro(rs));
            }
        }
        return carros;
    }

    public CarroModel buscarCarroPorMatricula(String matricula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_leer_carro(?)}";

        try (Connection conn = DBConnection.obtenerConexion(); CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSetACarro(rs);
                }
            }
        }
        return null;
    }

    public void actualizarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_actualizar_carro(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DBConnection.obtenerConexion(); CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAnio());
            stmt.setString(4, carro.getTipoVehiculo());
            stmt.setString(5, carro.getNumPlaca());
            stmt.setString(6, carro.getNumChasis());
            stmt.setString(7, carro.getMatricula());

            stmt.execute();
        }
    }

    public void eliminarCarro(String matricula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_borrar_carro(?)}";

        try (Connection conn = DBConnection.obtenerConexion(); CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, matricula);
            stmt.execute();
        }
    }

    private CarroModel mapearResultSetACarro(ResultSet rs) throws SQLException {
        CarroModel carro = new CarroModel();
        carro.setIdCarro(rs.getInt("IDCarro"));
        carro.setMarca(rs.getString("Marca"));
        carro.setModelo(rs.getString("Modelo"));
        carro.setAnio(rs.getInt("Anio"));
        carro.setNumPlaca(rs.getString("Placa"));
        carro.setMatricula(rs.getString("Matricula"));
        carro.setKilometraje(rs.getInt("kilometraje"));
        return carro;
    }
}
