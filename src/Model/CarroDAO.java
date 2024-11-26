package Model;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    // Insertar un nuevo carro
    public void insertarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_insertar_carro(?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, carro.getMatricula());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getModelo());
            stmt.setString(4, carro.getNumPlaca());
            stmt.setInt(5, carro.getAnio());

            stmt.execute();
        }
    }

    // Actualizar un carro existente
    public void actualizarCarro(CarroModel carro) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_actualizar_carro(?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setInt(1, carro.getIdCarro());
            stmt.setString(2, carro.getMatricula());
            stmt.setString(3, carro.getMarca());
            stmt.setString(4, carro.getModelo());
            stmt.setString(5, carro.getNumPlaca());
            stmt.setInt(6, carro.getAnio());

            stmt.execute();
        }
    }

    // Eliminar un carro por matrícula
    public void eliminarCarro(String matricula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_borrar_carro(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, matricula);
            stmt.execute();
        }
    }

    // Buscar un carro por matrícula
    public CarroModel buscarCarroPorMatricula(String matricula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_leer_carro(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CarroModel carro = new CarroModel();
                    carro.setIdCarro(rs.getInt("IDCarro"));
                    carro.setMatricula(rs.getString("Matricula"));
                    carro.setMarca(rs.getString("Marca"));
                    carro.setModelo(rs.getString("Modelo"));
                    carro.setNumPlaca(rs.getString("NumPlaca"));
                    carro.setAnio(rs.getInt("Año"));
                    return carro;
                }
            }
        }
        return null;
    }

    // Obtener todos los carros
    public List<CarroModel> obtenerTodosLosCarros() throws SQLException, FileNotFoundException {
        List<CarroModel> carros = new ArrayList<>();
        String query = "{CALL sp_leer_carro(?)}";

        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setNull(1, Types.VARCHAR);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CarroModel carro = new CarroModel();
                    carro.setMatricula(rs.getString("Matricula"));
                    carro.setMarca(rs.getString("Marca"));
                    carro.setModelo(rs.getString("Modelo"));
                    carro.setNumPlaca(rs.getString("Placa"));
                    carro.setAnio(rs.getInt("Anio"));
                    carros.add(carro);
                }
            }
        }
        return carros;

    }

    // Verificar disponibilidad de un carro por matrícula
    public boolean verificarDisponibilidad(String matricula) throws SQLException, FileNotFoundException {
        String query = "{CALL sp_verificar_disponibilidad_carro(?)}";
        try (Connection connection = DBConnection.obtenerConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        }
        return false;
    }
}
