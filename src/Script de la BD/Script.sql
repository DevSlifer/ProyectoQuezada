/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Supre
 * Created: Dec 2, 2024
 */

create database ProyectoFinal;
use  proyectofinal;

CREATE TABLE usuarios (
    IDUsuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('admin', 'empleado') NOT NULL,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

#Stored procedures para los usuarios
DELIMITER //

CREATE PROCEDURE sp_insertar_usuario
(
    IN p_email VARCHAR(100),
    IN p_contrasena VARCHAR(255),
    IN p_rol ENUM('empleado', 'admin')
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    IF p_rol NOT IN ('admin', 'empleado') THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Rol no válido. Los roles permitidos son: admin, empleado';
    END IF;

    START TRANSACTION;

    -- Inserción del usuario
    INSERT INTO usuarios (
        email,
        contrasena,
        rol,
        fecha_creacion
    )
    VALUES (
        p_email,
        SHA2(p_contrasena, 256),
        p_rol,
        NOW()
    );

    COMMIT;
END //

DELIMITER ;


DELIMITER //
CREATE PROCEDURE sp_leer_usuario(
    IN p_correo VARCHAR(50),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    SELECT email, contrasena, rol
    FROM usuarios
    WHERE email = p_correo AND contrasena = SHA2(p_contrasena, 256);
END //

DELIMITER ;

CREATE TABLE Empleado (
    IDEmpleado INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(20),
    Apellido VARCHAR(20),
    Salario DECIMAL(10,2),
    Cedula VARCHAR(13) UNIQUE,
    Telefono varchar(13) unique
);

#Procedures para Empleados

DELIMITER //
CREATE PROCEDURE sp_insertar_empleado(
    IN p_Nombre VARCHAR(20),
    IN p_Apellido VARCHAR(20),
    IN p_Salario DECIMAL(10,2),
    IN p_Cedula VARCHAR(13),
    in p_telefono varchar(13)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    INSERT INTO Empleado (Nombre, Apellido, Salario, Cedula, telefono)
    VALUES (p_Nombre, p_Apellido, p_Salario, p_Cedula, p_telefono);

    COMMIT;
end //

DELIMITER ;

#
DELIMITER //

CREATE PROCEDURE sp_leer_empleados(
    IN p_Cedula VARCHAR(11) 
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        SELECT 'Ocurrió un error al intentar obtener los empleados' AS MensajeError;
        RESIGNAL;
    END;

    SELECT * 
    FROM empleado
    WHERE (p_Cedula IS NULL OR Cedula = p_Cedula);
END //
DELIMITER ;

DELIMITER //
#
CREATE PROCEDURE sp_actualizar_empleado(
    IN p_Nombre VARCHAR(20),
    IN p_Apellido VARCHAR(20),
    IN p_Salario DECIMAL(10,2),
    IN p_Cedula VARCHAR(11),
    in p_telefono varchar(13)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    IF EXISTS (SELECT 1 FROM Empleado WHERE Cedula = p_Cedula) THEN
        UPDATE Empleado
        SET Nombre = coalesce(p_Nombre, nombre),
            Apellido = coalesce(p_Apellido, apellido),
            Salario = coalesce(p_Salario,salario),
            telefono = coalesce(p_telefono, telefono)
        WHERE Cedula = p_Cedula;
    ELSE
        SELECT 'El empleado especificado no existe' AS MensajeError;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_borrar_empleado(
    IN  p_Cedula varchar(11)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    DELETE FROM Empleado
    WHERE cedula = p_cedula;

    COMMIT;
end //

DELIMITER ;

DELIMITER //

CREATE TABLE Cliente (
    IDCliente INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(20),
    Apellido VARCHAR(20),
    Cedula VARCHAR(11) UNIQUE,
    Licencia VARCHAR(13) UNIQUE,
    telefono varchar (13)
);

CREATE VIEW InformacionCliente AS 
SELECT 
    Cliente.Nombre, 
    Cliente.apellido,
    Cliente.Cedula,
    Cliente.telefono,
    Cliente.licencia,
    DireccionCliente.Provincia, 
    DireccionCliente.Sector,
    DireccionCliente.Calle, 
    DireccionCliente.NumeroDeCasa
FROM Cliente
inner JOIN DireccionCliente ON Cliente.IDCliente = DireccionCliente.IDCliente
GROUP BY Cliente.IDCliente;


#Procedures para los clientes
delimiter //
create procedure sp_insertar_cliente
(
    IN p_Nombre VARCHAR(20),
    IN p_Apellido VARCHAR(20),
    IN p_Cedula VARCHAR(11),
    IN p_Licencia VARCHAR(13),
    IN p_Provincia VARCHAR(50),
    IN p_Sector VARCHAR(50),
    IN p_Calle VARCHAR(100),
    IN p_NumeroDeCasa INT,
    IN p_Telefono VARCHAR(15)
)
  begin  
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;
    
    insert into cliente(nombre, apellido, cedula,licencia, telefono) values 
    (p_nombre,p_apellido, p_cedula,p_licencia, p_telefono);
    SET @new_IDCliente = LAST_INSERT_ID();

	IF p_Provincia IS NOT NULL AND p_Sector IS NOT NULL THEN
        INSERT INTO DireccionCliente (Provincia, Sector, Calle, NumeroDeCasa, IDCliente)
        VALUES (p_Provincia, p_Sector, p_Calle, p_NumeroDeCasa, @new_IDCliente);
    END IF;
    COMMIT;
END //

DELIMITER ;

delimiter //
CREATE PROCEDURE sp_borrar_cliente(
    IN  p_Cedula varchar(11)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    DELETE FROM cliente
    WHERE cedula = p_cedula;

    COMMIT;
end //

delimiter //
CREATE PROCEDURE sp_leer_cliente(
    IN p_Cedula VARCHAR(11) 
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        SELECT 'Ocurrió un error al intentar obtener los clientes' AS MensajeError;
        RESIGNAL;
    END;

    SELECT * 
    FROM informacioncliente
    WHERE (p_Cedula IS NULL OR Cedula = p_Cedula);
END //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_actualizar_cliente(
    IN p_Cedula VARCHAR(11),
    IN p_Nombre VARCHAR(20),
    IN p_Apellido VARCHAR(20),
    IN p_Licencia VARCHAR(13),
    IN p_Provincia VARCHAR(50),
    IN p_Sector VARCHAR(50),
    IN p_Calle VARCHAR(100),
    IN p_NumeroDeCasa INT,
    IN p_Telefono VARCHAR(15) 
)
BEGIN
    DECLARE v_IDCliente INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SELECT IDCliente INTO v_IDCliente 
    FROM Cliente 
    WHERE Cedula = p_Cedula;
    
    IF v_IDCliente IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cliente no encontrado';
    END IF;

    START TRANSACTION;

    UPDATE Cliente
    SET 
        Nombre = COALESCE(p_Nombre, Nombre),
        Apellido = COALESCE(p_Apellido, Apellido),
        Licencia = COALESCE(p_Licencia, Licencia),
        Telefono = COALESCE(p_Telefono, Telefono)
    WHERE IDCliente = v_IDCliente;

    IF p_Provincia IS NOT NULL OR p_Sector IS NOT NULL OR p_Calle IS NOT NULL OR p_NumeroDeCasa IS NOT NULL THEN
        UPDATE DireccionCliente
        SET 
            Provincia = COALESCE(p_Provincia, Provincia),
            Sector = COALESCE(p_Sector, Sector),
            Calle = COALESCE(p_Calle, Calle),
            NumeroDeCasa = COALESCE(p_NumeroDeCasa, NumeroDeCasa)
        WHERE IDCliente = v_IDCliente;
    END IF;

    COMMIT;
END //

DELIMITER ;




CREATE TABLE DireccionCliente (
    IDDireccion INT PRIMARY KEY AUTO_INCREMENT,
    Provincia VARCHAR(50),
    Sector VARCHAR(50),
    Calle VARCHAR(100),
    NumeroDeCasa INT,
    IDCliente INT,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(IDCliente) ON DELETE CASCADE
);

CREATE TABLE Factura (
    IDFactura INT PRIMARY KEY AUTO_INCREMENT,
    Monto DECIMAL(10,2),
    CargosAdicionales INT DEFAULT 0,
    FechaDePago DATE,
    IDCliente INT,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(IDCliente) ON DELETE CASCADE
);

create view FacturasGeneradas as
Select c.nombre,c.apellido, c.cedula, f.monto, f.cargosadicionales as Cargos_adicionales, f.fechadepago as Fecha_de_pago
from cliente c
inner join factura f on c.idcliente = f.idcliente;

#Procedures para facturas
delimiter //
create procedure sp_leer_factura
(
	in p_cedula varchar(11)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        SELECT 'Ocurrió un error al intentar obtener las facturas' AS MensajeError;
        RESIGNAL;
    END;

    SELECT * 
    FROM facturasgeneradas
    WHERE (p_Cedula IS NULL OR Cedula = p_Cedula);
END //
delimiter ;


DELIMITER //

CREATE FUNCTION fn_calcular_monto_por_cedula(
    p_Cedula VARCHAR(20),
    p_FechaInicio DATE,
    p_FechaFin DATE
) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE v_PrecioPorDia DECIMAL(10,2);
    DECLARE v_DiasTotal INT;
    DECLARE v_MontoTotal DECIMAL(10,2);
    
    SELECT C.PrecioPorDia 
    INTO v_PrecioPorDia
    FROM Reserva R
    JOIN Cliente CL ON R.IDCliente = CL.IDCliente
    JOIN Carro C ON R.IDCarro = C.IDCarro
    WHERE CL.Cedula = p_Cedula
    AND R.FechaDeEntrega = p_FechaInicio
    AND R.FechaDevolucion = p_FechaFin
    AND R.Cancelacion = FALSE;
    
    SET v_DiasTotal = DATEDIFF(p_FechaFin, p_FechaInicio) + 1;
    
    SET v_MontoTotal = v_DiasTotal * IFNULL(v_PrecioPorDia, 0);
    
    RETURN v_MontoTotal;
END //

DELIMITER //

CREATE PROCEDURE sp_insertar_factura(
    IN p_Cedula VARCHAR(20),
    IN p_FechaInicio DATE,
    IN p_FechaFin DATE,
    IN p_CargosAdicionales INT,
    IN p_FechaDePago DATE
)
BEGIN
    DECLARE v_Monto DECIMAL(10,2);
    DECLARE v_IDCliente INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    SELECT IDCliente INTO v_IDCliente
    FROM Cliente
    WHERE Cedula = p_Cedula;
    
    IF v_IDCliente IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cliente no encontrado con la cédula proporcionada';
    END IF;
    
    SET v_Monto = fn_calcular_monto_por_cedula(p_Cedula, p_FechaInicio, p_FechaFin);
    
    IF v_Monto = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se encontró una reserva activa para este cliente en las fechas especificadas';
    END IF;
    
    START TRANSACTION;
    
    INSERT INTO Factura (
        Monto, 
        CargosAdicionales, 
        FechaDePago, 
        IDCliente
    )
    VALUES (
        v_Monto,
        p_CargosAdicionales,
        p_FechaDePago,
        v_IDCliente
    );
    
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_eliminar_factura(
    IN p_Cedula VARCHAR(20),
    OUT p_FacturasEliminadas INT
)
BEGIN
    DECLARE v_IDCliente INT;
    DECLARE v_existe_cliente INT;
    DECLARE v_existe_factura INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    SELECT COUNT(*), IDCliente 
    INTO v_existe_cliente, v_IDCliente
    FROM Cliente
    WHERE Cedula = p_Cedula;
    
    IF v_existe_cliente = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No existe un cliente con la cédula especificada';
    END IF;
    
    SELECT COUNT(*) INTO v_existe_factura
    FROM Factura
    WHERE IDCliente = v_IDCliente;
    
    IF v_existe_factura = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No existen facturas para el cliente especificado';
    END IF;
    
    START TRANSACTION;
    
    SET p_FacturasEliminadas = (
        SELECT COUNT(*) 
        FROM Factura 
        WHERE IDCliente = v_IDCliente
    );
    
    DELETE FROM Factura 
    WHERE IDCliente = v_IDCliente;
    
    COMMIT;
    
    SELECT 
        CL.Cedula,
        CL.Nombre,
        p_FacturasEliminadas as FacturasEliminadas
    FROM Cliente CL
    WHERE CL.IDCliente = v_IDCliente;
    
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_actualizar_factura(
    IN p_Cedula VARCHAR(20),
    IN p_NuevoMonto DECIMAL(10,2),
    IN p_NuevosCargosAdicionales INT,
    IN p_NuevaFechaPago DATE
)
BEGIN
    DECLARE v_IDCliente INT;
    DECLARE v_existe_cliente INT;
    DECLARE v_existe_factura INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    SELECT COUNT(*), IDCliente 
    INTO v_existe_cliente, v_IDCliente
    FROM Cliente
    WHERE Cedula = p_Cedula;
    
    IF v_existe_cliente = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No existe un cliente con la cédula especificada';
    END IF;
    
    SELECT COUNT(*) INTO v_existe_factura
    FROM Factura
    WHERE IDCliente = v_IDCliente;
    
    IF v_existe_factura = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No existe factura para el cliente especificado';
    END IF;
    
    START TRANSACTION;
    
    -- Actualizar la factura
    UPDATE Factura
    SET 
        Monto = CASE 
            WHEN p_NuevoMonto IS NOT NULL THEN p_NuevoMonto 
            ELSE Monto 
        END,
        CargosAdicionales = CASE 
            WHEN p_NuevosCargosAdicionales IS NOT NULL THEN p_NuevosCargosAdicionales 
            ELSE CargosAdicionales 
        END,
        FechaDePago = CASE 
            WHEN p_NuevaFechaPago IS NOT NULL THEN p_NuevaFechaPago 
            ELSE FechaDePago 
        END
    WHERE IDCliente = v_IDCliente;
    
    COMMIT;
    
    -- Mostrar la factura actualizada
    SELECT 
        F.*,
        C.Cedula,
        C.Nombre
    FROM Factura F
    JOIN Cliente C ON F.IDCliente = C.IDCliente
    WHERE F.IDCliente = v_IDCliente;
    
END //

DELIMITER ;

CREATE TABLE Carro (
    IDCarro INT PRIMARY KEY AUTO_INCREMENT,
    Marca VARCHAR(20),
    Modelo VARCHAR(20),
    Anio INT check(anio>2000),
    PrecioPorDia int,
    Placa VARCHAR(9) UNIQUE,
    Matricula VARCHAR(50) UNIQUE
);

#Procedures de carro
DELIMITER //
CREATE PROCEDURE sp_insertar_carro(
    IN p_Marca VARCHAR(20),
    IN p_Modelo VARCHAR(20),
    IN p_Anio INT,
    IN p_Placa VARCHAR(9),
    In p_kilometraje decimal(10,2),
    in p_preciopordia decimal(6,2),
    IN p_Matricula VARCHAR(50) 
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;
    START TRANSACTION;
    INSERT INTO Carro (Marca, Modelo, Anio, Placa,kilometraje, Matricula,preciopordia)
    VALUES (p_Marca, p_Modelo, p_Anio,  p_Placa,p_kilometraje,  p_Matricula, p_preciopordia);
    COMMIT;
END //


delimiter //sp_leer_cliente
CREATE PROCEDURE sp_actualizar_carro(
    IN p_Marca VARCHAR(20),
    IN p_Modelo VARCHAR(20),
    IN p_Anio INT,
    IN p_Placa VARCHAR(9),
    IN p_Matricula VARCHAR(50),
    in p_preciopordia int,
    in p_kilometraje decimal(10,2)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    UPDATE Carro
    SET 
        Marca = COALESCE(p_Marca, Marca),
        Modelo = COALESCE(p_Modelo, Modelo), 
        Anio = COALESCE(p_Anio, Anio), 
        Placa = COALESCE(p_Placa, Placa),
        Preciopordia = coalesce (p_preciopordia, preciopordia),
        kilometraje = coalesce (p_kilometraje, preciopordia)
    WHERE Matricula = p_Matricula;

    COMMIT;
END //

delimiter //
CREATE PROCEDURE sp_borrar_carro(
    IN  p_matricula varchar(50)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        ROLLBACK;
        RESIGNAL;
    end;

    START TRANSACTION;

    DELETE FROM carro
    WHERE matricula = p_matricula;

    COMMIT;
end //

delimiter //
CREATE PROCEDURE sp_leer_carro(
    IN p_matricula VARCHAR(50) 
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
    BEGIN
        SELECT 'Ocurrió un error al intentar obtener los carros' AS MensajeError;
        RESIGNAL;
    END;

    SELECT * 
    FROM carro
    WHERE (p_matricula IS NULL OR matricula = p_matricula);
END //
DELIMITER ;

CREATE TABLE Reserva (
    IDReservacion INT PRIMARY KEY AUTO_INCREMENT,
    FechaReservacion DATE,
    FechaDeEntrega DATE,
    FechaDevolucion DATE,
    Cancelacion BOOL DEFAULT FALSE,
    IDCliente INT,
    IDCarro INT,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(IDCliente) ON DELETE CASCADE,
    FOREIGN KEY (IDCarro) REFERENCES Carro(IDCarro) ON DELETE CASCADE
);

DELIMITER //

CREATE PROCEDURE sp_insertar_reserva(
    IN p_Cedula VARCHAR(11),
    IN p_Placa VARCHAR(9),
    IN p_FechaEntrega DATE,
    IN p_FechaDevolucion DATE
)
BEGIN
    DECLARE v_IDCliente INT;
    DECLARE v_IDCarro INT;
    DECLARE v_CarroDisponible BOOLEAN;
    
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        ROLLBACK;
        RESIGNAL;
    END;
    
    SELECT IDCliente INTO v_IDCliente 
    FROM Cliente 
    WHERE Cedula = p_Cedula;
    
    IF v_IDCliente IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cliente no encontrado. Debe registrar el cliente primero.';
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM Cliente WHERE IDCliente = v_IDCliente AND Licencia IS NOT NULL) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente debe tener una licencia registrada para realizar una reserva.';
    END IF;
    
    SELECT IDCarro INTO v_IDCarro 
    FROM Carro 
    WHERE Placa = p_Placa;
    
    IF v_IDCarro IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Carro no encontrado. Verifique la placa ingresada.';
    END IF;
    
    IF p_FechaEntrega >= p_FechaDevolucion THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de entrega debe ser anterior a la fecha de devolución.';
    END IF;
    
    IF p_FechaEntrega < CURDATE() THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de entrega no puede ser anterior a la fecha actual.';
    END IF;
    
    SELECT NOT EXISTS (
        SELECT 1 
        FROM Reserva 
        WHERE IDCarro = v_IDCarro 
        AND Cancelacion = FALSE
        AND (
            (p_FechaEntrega BETWEEN FechaDeEntrega AND FechaDevolucion) OR
            (p_FechaDevolucion BETWEEN FechaDeEntrega AND FechaDevolucion) OR
            (FechaDeEntrega BETWEEN p_FechaEntrega AND p_FechaDevolucion)
        )
    ) INTO v_CarroDisponible;
    
    IF NOT v_CarroDisponible THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El carro no está disponible para las fechas seleccionadas.';
    END IF;
    
    START TRANSACTION;
    
    INSERT INTO Reserva(
        FechaReservacion, 
        FechaDeEntrega, 
        FechaDevolucion, 
        IDCliente, 
        IDCarro
    ) VALUES (
        CURDATE(),
        p_FechaEntrega,
        p_FechaDevolucion,
        v_IDCliente,
        v_IDCarro
    );
    
    COMMIT;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_actualizar_reserva(
    IN p_Cedula VARCHAR(11),
    IN p_Nueva_Placa VARCHAR(9),
    IN p_Nueva_FechaEntrega DATE,
    IN p_Nueva_FechaDevolucion DATE
)
BEGIN
    DECLARE v_IDCliente INT;
    DECLARE v_IDCarro INT;
    DECLARE v_CarroDisponible BOOLEAN;
    DECLARE v_TieneReservaActiva BOOLEAN;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        ROLLBACK;
        RESIGNAL;
    END;
    
    -- Obtener ID del cliente
    SELECT IDCliente INTO v_IDCliente 
    FROM Cliente 
    WHERE Cedula = p_Cedula;
    
    IF v_IDCliente IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cliente no encontrado';
    END IF;
    
    -- Verificar si tiene reserva activa
    SELECT EXISTS(
        SELECT 1 FROM Reserva 
        WHERE IDCliente = v_IDCliente 
        AND Cancelacion = FALSE
    ) INTO v_TieneReservaActiva;
    
    IF NOT v_TieneReservaActiva THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente no tiene reservas activas';
    END IF;
    
    -- Obtener ID del nuevo carro
    SELECT IDCarro INTO v_IDCarro 
    FROM Carro 
    WHERE Placa = p_Nueva_Placa;
    
    IF v_IDCarro IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Carro no encontrado';
    END IF;
    
    -- Validar fechas
    IF p_Nueva_FechaEntrega >= p_Nueva_FechaDevolucion THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Fecha de entrega debe ser anterior a devolución';
    END IF;
    
    SELECT NOT EXISTS (
        SELECT 1 FROM Reserva 
        WHERE IDCarro = v_IDCarro 
        AND Cancelacion = FALSE
        AND IDCliente != v_IDCliente
        AND (
            (p_Nueva_FechaEntrega BETWEEN FechaDeEntrega AND FechaDevolucion) OR
            (p_Nueva_FechaDevolucion BETWEEN FechaDeEntrega AND FechaDevolucion) OR
            (FechaDeEntrega BETWEEN p_Nueva_FechaEntrega AND p_Nueva_FechaDevolucion)
        )
    ) INTO v_CarroDisponible;
    
    IF NOT v_CarroDisponible THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Carro no disponible para las fechas seleccionadas';
    END IF;
    
    START TRANSACTION;
    
    UPDATE Reserva
    SET 
        IDCarro = v_IDCarro,
        FechaDeEntrega = p_Nueva_FechaEntrega,
        FechaDevolucion = p_Nueva_FechaDevolucion
    WHERE IDCliente = v_IDCliente
    AND Cancelacion = FALSE;
    
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_eliminar_reserva(
    IN p_Cedula VARCHAR(11),
    IN p_Placa VARCHAR(9)
)
BEGIN
    DECLARE v_IDCliente INT;
    DECLARE v_IDCarro INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        ROLLBACK;
        RESIGNAL;
    END;
    
    IF p_Cedula IS NOT NULL THEN
        SELECT IDCliente INTO v_IDCliente 
        FROM Cliente 
        WHERE Cedula = p_Cedula;
        
        IF v_IDCliente IS NULL THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cliente no encontrado';
        END IF;
    END IF;
    
    IF p_Placa IS NOT NULL THEN
        SELECT IDCarro INTO v_IDCarro 
        FROM Carro 
        WHERE Placa = p_Placa;
        
        IF v_IDCarro IS NULL THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Carro no encontrado';
        END IF;
    END IF;
    
    START TRANSACTION;
    
    UPDATE Reserva 
    SET Cancelacion = TRUE
    WHERE 
        (v_IDCliente IS NULL OR IDCliente = v_IDCliente)
        AND (v_IDCarro IS NULL OR IDCarro = v_IDCarro)
        AND Cancelacion = FALSE;
    
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se encontraron reservas activas para cancelar';
    END IF;
    
    COMMIT;
END //

DELIMITER ;


create view  RentaCliente as
SELECT 
    C.Nombre, 
    C.Apellido, 
    C.Cedula,
    C.telefono,
    CA.Marca, 
    CA.Modelo, 
    CA.Placa,
    CA.PrecioPorDia,
    R.FechaReservacion,
    R.FechaDeEntrega, 
    R.FechaDevolucion,
    DATEDIFF(R.FechaDevolucion, R.FechaDeEntrega) + 1 AS DiasTotal,
    (DATEDIFF(R.FechaDevolucion, R.FechaDeEntrega) + 1) * CA.PrecioPorDia AS MontoEstimado
FROM Cliente C
INNER JOIN Reserva R ON C.IDCliente = R.IDCliente
INNER JOIN Carro CA ON CA.IDCarro = R.IDCarro
WHERE R.Cancelacion = FALSE;

DELIMITER //

CREATE PROCEDURE sp_leer_reservas(
    IN p_Cedula VARCHAR(11),
    IN p_Placa VARCHAR(9)
)
BEGIN
    SELECT * FROM rentacliente
    WHERE 
        (p_Cedula IS NULL OR Cedula = p_Cedula)
        AND (p_Placa IS NULL OR Placa = p_Placa)
    ORDER BY FechaReservacion DESC;
END //

DELIMITER ;


-- Indices para usuarios
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_rol ON usuarios(rol);

-- Indices para clientes
CREATE INDEX idx_cliente_cedula ON Cliente(Cedula);
CREATE INDEX idx_cliente_licencia ON Cliente(Licencia);

-- Indices para búsquedas en direcciones
CREATE INDEX idx_direccion_cliente ON DireccionCliente(IDCliente);
CREATE INDEX idx_direccion_provincia ON DireccionCliente(Provincia);

-- Indices para carros
CREATE INDEX idx_carro_placa ON Carro(Placa);
CREATE INDEX idx_carro_matricula ON Carro(Matricula);
CREATE INDEX idx_carro_marca_modelo ON Carro(Marca, Modelo);

-- Indices para reservas
CREATE INDEX idx_reserva_fechas ON Reserva(FechaDeEntrega, FechaDevolucion);
CREATE INDEX idx_reserva_cliente ON Reserva(IDCliente);
CREATE INDEX idx_reserva_carro ON Reserva(IDCarro);
CREATE INDEX idx_reserva_estado ON Reserva(Cancelacion);

-- Indices para facturas 
CREATE INDEX idx_factura_cliente ON Factura(IDCliente);
CREATE INDEX idx_factura_fecha ON Factura(FechaDePago);

#Triggers para la base de datos

DELIMITER //

-- Validar fechas de reserva
CREATE TRIGGER before_reserva_insert 
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF NEW.FechaDeEntrega > NEW.FechaDevolucion THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Fecha de entrega debe ser anterior a fecha de devolución';
    END IF;
END //

-- Validar licencia al crear reserva
CREATE TRIGGER before_reserva_licencia
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Cliente WHERE IDCliente = NEW.IDCliente AND Licencia IS NOT NULL) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cliente debe tener licencia para realizar reserva';
    END IF;
END //

-- Validar disponibilidad del carro
CREATE TRIGGER before_reserva_disponibilidad
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM Reserva 
        WHERE IDCarro = NEW.IDCarro 
        AND Cancelacion = FALSE
        AND ((NEW.FechaDeEntrega BETWEEN FechaDeEntrega AND FechaDevolucion)
        OR (NEW.FechaDevolucion BETWEEN FechaDeEntrega AND FechaDevolucion))
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Carro no disponible para las fechas seleccionadas';
    END IF;
END //

-- Validar año del carro
CREATE TRIGGER before_carro_insert
BEFORE INSERT ON Carro
FOR EACH ROW
BEGIN
    IF NEW.Anio <= 2000 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El año del carro debe ser mayor a 2000';
    END IF;
END //

DELIMITER ;

DELIMITER //


DELIMITER //

-- Validacion de que el kilometraje nuevo sea mayor al anterior
CREATE TRIGGER validar_kilometraje_update
BEFORE UPDATE ON Carro
FOR EACH ROW
BEGIN
    IF NEW.Kilometraje < OLD.Kilometraje THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nuevo kilometraje no puede ser menor al anterior';
    END IF;
END //

-- Validacion de que el kilometraje inicial sea 0 o positivo
delimiter //
CREATE TRIGGER validar_kilometraje_insert
BEFORE INSERT ON Carro
FOR EACH ROW
BEGIN
    IF NEW.Kilometraje < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El kilometraje no puede ser negativo';
    END IF;
END //

DELIMITER ;

DELIMITER //

-- Validar que el precio por día sea positivo
CREATE TRIGGER validar_precio_carro
BEFORE INSERT ON Carro
FOR EACH ROW
BEGIN
    IF NEW.PrecioPorDia <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio por día debe ser mayor a 0';
    END IF;
END //

-- Validar fechas de reserva (que la fecha de devolución sea posterior a la entrega)
CREATE TRIGGER validar_fechas_reserva
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF NEW.FechaDevolucion <= NEW.FechaDeEntrega THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de devolución debe ser posterior a la fecha de entrega';
    END IF;
    
    IF NEW.FechaDeEntrega < CURDATE() THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden hacer reservas con fechas pasadas';
    END IF;
END //

-- Validar disponibilidad del carro
CREATE TRIGGER validar_disponibilidad_carro
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM Reserva 
        WHERE IDCarro = NEW.IDCarro 
        AND Cancelacion = FALSE
        AND (
            (NEW.FechaDeEntrega BETWEEN FechaDeEntrega AND FechaDevolucion)
            OR (NEW.FechaDevolucion BETWEEN FechaDeEntrega AND FechaDevolucion)
        )
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El carro no está disponible para las fechas seleccionadas';
    END IF;
END //

-- Validar que el cliente tenga licencia antes de reservar
CREATE TRIGGER validar_licencia_cliente
BEFORE INSERT ON Reserva
FOR EACH ROW
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM Cliente 
        WHERE IDCliente = NEW.IDCliente 
        AND Licencia IS NOT NULL
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente debe tener licencia registrada para realizar una reserva';
    END IF;
END //

