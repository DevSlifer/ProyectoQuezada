package ConexionBD;

/**
 *
 * @author Supre
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Supre
 */
public class DBConnection {

    private static Connection conexion = null;

    public static Connection obtenerConexion() throws FileNotFoundException, SQLException {
        if (conexion != null && !conexion.isClosed()) {
            return conexion;
        } else {
            try {
                String directorioActual = System.getProperty("user.dir");

                String rutaArchivo = directorioActual + File.separator + "\\src\\Utils\\configuracion.txt";
                File archivo = new File(rutaArchivo);
                FileReader lector = new FileReader(archivo);
                String url;
                String usuario;
                String contraseña;

                BufferedReader buffer;
                buffer = new BufferedReader(lector);
                url = buffer.readLine();
                usuario = buffer.readLine();
                contraseña = buffer.readLine();
                buffer.close();

                conexion = DriverManager.getConnection(url, usuario, contraseña);
                return conexion;

            } catch (IOException e) {
                System.out.println("Mensaje (conexion):" + e.getMessage());
                return null;
            }
        }
    }

    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

}
