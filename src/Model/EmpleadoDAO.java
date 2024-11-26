package Model;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public void insertarEmpleado(EmpleadoModel empleado) throws FileNotFoundException {
        String query = "{CALL sp_insertar_empleado(?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellido());
            statement.setDouble(3, empleado.getSalario());
            statement.setString(4, empleado.getCedula());
            statement.setInt(5, empleado.getIdDepartamento());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEmpleado(EmpleadoModel empleado) throws FileNotFoundException {
        String query = "{CALL sp_actualizar_empleado(?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, empleado.getIdEmpleado());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getApellido());
            statement.setDouble(4, empleado.getSalario());
            statement.setString(5, empleado.getCedula());
            statement.setInt(6, empleado.getIdDepartamento());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(int idEmpleado) throws FileNotFoundException {
        String query = "{CALL sp_borrar_empleado(?)}";
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, idEmpleado);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EmpleadoModel> leerEmpleados() throws FileNotFoundException {
        List<EmpleadoModel> empleados = new ArrayList<>();
        String query = "{CALL sp_leer_empleados()}";
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement statement = connection.prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EmpleadoModel empleado = new EmpleadoModel();
                empleado.setIdEmpleado(resultSet.getInt("IDEmpleado"));
                empleado.setNombre(resultSet.getString("Nombre"));
                empleado.setApellido(resultSet.getString("Apellido"));
                empleado.setSalario(resultSet.getDouble("Salario"));
                empleado.setCedula(resultSet.getString("Cedula"));
                empleado.setIdDepartamento(resultSet.getInt("IDDepartamento"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public EmpleadoModel leerEmpleadoPorId(int idEmpleado) throws FileNotFoundException {
        EmpleadoModel empleado = null;
        String query = "{CALL sp_leer_empleado_por_id(?)}";
        try (Connection connection = DBConnection.obtenerConexion();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, idEmpleado);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new EmpleadoModel();
                    empleado.setIdEmpleado(resultSet.getInt("IDEmpleado"));
                    empleado.setNombre(resultSet.getString("Nombre"));
                    empleado.setApellido(resultSet.getString("Apellido"));
                    empleado.setSalario(resultSet.getDouble("Salario"));
                    empleado.setCedula(resultSet.getString("Cedula"));
                    empleado.setIdDepartamento(resultSet.getInt("IDDepartamento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }
}