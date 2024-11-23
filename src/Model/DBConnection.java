/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
// Obtener la ruta del directorio actual
                String directorioActual = System.getProperty("user.dir");

// Construir la ruta completa del archivo
                String rutaArchivo = directorioActual + File.separator + "\\src\\BaseDeDatos\\configuracion.txt";
// Abrir el archivo
                File archivo = new File(rutaArchivo);
                FileReader lector = new FileReader(archivo);
                String url;
                String usuario;
                String contraseña;

// Leer el contenido del archivo y almacenarlo en variables
                BufferedReader buffer;
                buffer = new BufferedReader(lector);
// Leer el contenido del archivo y almacenarlo en variables
                url = buffer.readLine();
                usuario = buffer.readLine();
                contraseña = buffer.readLine();
// Cerrar el buffer de lectura
                buffer.close();
// Usar los valores leídos
                /* System.out.println("URL de la base de datos: " + url);
                System.out.println("Usuario de la base de datos: " + usuario);
                System.out.println("Contraseña de la base de datos: " + contraseña); */
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
