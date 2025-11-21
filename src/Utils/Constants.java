package Utils;

/**
 * Constants class containing all application-wide constants.
 * Follows SOLID principles by centralizing configuration values.
 *
 * @author ProyectoQuezada Team
 */
public final class Constants {

    // Private constructor to prevent instantiation
    private Constants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }

    // Database Configuration
    public static final class Database {
        public static final String CONFIG_FILE_PATH = "src/Utils/configuracion.txt";
        public static final int DEFAULT_TIMEOUT = 30; // seconds

        private Database() {}
    }

    // SQL Stored Procedures
    public static final class SQL {
        // Cliente procedures
        public static final String SP_INSERTAR_CLIENTE = "call sp_insertar_cliente(?,?,?,?,?,?,?,?,?)";
        public static final String SP_LEER_CLIENTE = "call sp_leer_cliente(?)";
        public static final String SP_ACTUALIZAR_CLIENTE = "call sp_actualizar_cliente(?,?,?,?,?,?,?,?,?)";
        public static final String SP_BORRAR_CLIENTE = "call sp_borrar_cliente(?)";

        // Carro procedures
        public static final String SP_INSERTAR_CARRO = "call sp_insertar_carro(?,?,?,?,?,?,?)";
        public static final String SP_LEER_CARRO = "call sp_leer_carro(?)";
        public static final String SP_ACTUALIZAR_CARRO = "call sp_actualizar_carro(?,?,?,?,?,?,?)";
        public static final String SP_BORRAR_CARRO = "call sp_borrar_carro(?)";

        // Usuario procedures
        public static final String SP_LEER_USUARIO = "call sp_leer_usuario(?,?)";
        public static final String SP_INSERTAR_USUARIO = "call sp_insertar_usuario(?,?,?)";

        // Empleado procedures
        public static final String SP_INSERTAR_EMPLEADO = "call sp_insertar_empleado(?,?,?,?,?)";
        public static final String SP_LEER_EMPLEADO = "call sp_leer_empleado(?)";
        public static final String SP_ACTUALIZAR_EMPLEADO = "call sp_actualizar_empleado(?,?,?,?,?,?)";
        public static final String SP_BORRAR_EMPLEADO = "call sp_borrar_empleado(?)";

        private SQL() {}
    }

    // Error Messages
    public static final class ErrorMessages {
        public static final String EMPTY_FIELD = "Este campo no debe estar vacío";
        public static final String INVALID_NUMBER = "Debe ingresar un valor numérico válido";
        public static final String CONNECTION_ERROR = "Error al conectar con la base de datos";
        public static final String DATABASE_ERROR = "Error en la operación de base de datos";
        public static final String AUTHENTICATION_ERROR = "Credenciales incorrectas";
        public static final String INVALID_CREDENTIALS = "Email o contraseña incorrectos";
        public static final String EMPTY_EMAIL = "El campo de email no debe estar vacío";
        public static final String EMPTY_PASSWORD = "El campo de contraseña no debe estar vacío";
        public static final String EMPTY_CEDULA = "Por favor ingrese una cédula";
        public static final String CLIENTE_NOT_FOUND = "No se encontraron clientes con la cédula: ";
        public static final String DELETE_CONFIRMATION = "¿Está seguro que desea eliminar el cliente con cédula: ";

        private ErrorMessages() {}
    }

    // Success Messages
    public static final class SuccessMessages {
        public static final String CLIENTE_ADDED = "Cliente agregado correctamente";
        public static final String CLIENTE_UPDATED = "Cliente actualizado correctamente";
        public static final String CLIENTE_DELETED = "Cliente eliminado correctamente";
        public static final String CARRO_ADDED = "Vehículo agregado correctamente";
        public static final String CARRO_UPDATED = "Vehículo actualizado correctamente";
        public static final String CARRO_DELETED = "Vehículo eliminado correctamente";
        public static final String EMPLEADO_ADDED = "Empleado agregado correctamente";
        public static final String EMPLEADO_UPDATED = "Empleado actualizado correctamente";
        public static final String EMPLEADO_DELETED = "Empleado eliminado correctamente";

        private SuccessMessages() {}
    }

    // Dialog Titles
    public static final class DialogTitles {
        public static final String ERROR = "Error";
        public static final String SUCCESS = "Éxito";
        public static final String CONFIRMATION = "Confirmación";
        public static final String WARNING = "Advertencia";
        public static final String INFO = "Información";

        private DialogTitles() {}
    }

    // Roles
    public static final class Roles {
        public static final String ADMIN = "admin";
        public static final String EMPLEADO = "empleado";

        private Roles() {}
    }

    // SQL Error States
    public static final class SQLStates {
        public static final String CUSTOM_ERROR = "45000";
        public static final String INTEGRITY_CONSTRAINT_VIOLATION = "23000";

        private SQLStates() {}
    }

    // Validation Patterns
    public static final class ValidationPatterns {
        public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
        public static final String CEDULA_PATTERN = "^\\d{11}$"; // Dominican cedula: 11 digits
        public static final String PHONE_PATTERN = "^\\d{10}$"; // 10 digits

        private ValidationPatterns() {}
    }

    // Field Names (for validation messages)
    public static final class FieldNames {
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String CEDULA = "cédula";
        public static final String TELEFONO = "teléfono";
        public static final String EMAIL = "email";
        public static final String LICENCIA = "licencia";
        public static final String PROVINCIA = "provincia";
        public static final String SECTOR = "sector";
        public static final String CALLE = "calle";
        public static final String NUMERO_CASA = "número de casa";

        private FieldNames() {}
    }
}
