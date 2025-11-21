package Model.DAOS;

import Model.UsuarioModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Interface for Usuario Data Access Object.
 * Follows Interface Segregation Principle (ISP) and Dependency Inversion Principle (DIP).
 *
 * @author ProyectoQuezada Team
 */
public interface IUsuarioDAO {

    /**
     * Authenticates a user by email and password.
     *
     * @param email User's email
     * @param clave User's password
     * @return UsuarioModel if authentication successful, null otherwise
     * @throws SQLException If a database error occurs
     * @throws FileNotFoundException If configuration file is not found
     */
    UsuarioModel leerUsario(String email, String clave) throws SQLException, FileNotFoundException;

    /**
     * Inserts a new user into the database.
     *
     * @param usuario The user to insert
     * @return 1 if successful
     * @throws FileNotFoundException If configuration file is not found
     * @throws SQLException If a database error occurs
     */
    int insertarUsuario(UsuarioModel usuario) throws FileNotFoundException, SQLException;
}
