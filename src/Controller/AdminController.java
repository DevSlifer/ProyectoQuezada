/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ClienteDAO;
import Model.CarroDAO;
import Model.ClienteModel;
import Model.CarroModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supre
 */
public class AdminController {

    private final ClienteDAO clienteDAO;
    private final CarroDAO carroDAO;

    public AdminController() {
        this.clienteDAO = new ClienteDAO();
        this.carroDAO = new CarroDAO();
    }

    // Métodos para Clientes
    public List<ClienteModel> obtenerClientes() {
        try {
            return clienteDAO.obtenerTodosLosClientes();
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ClienteModel buscarCliente(String cedula) throws SQLException, FileNotFoundException {
        return clienteDAO.buscarClientePorCedula(cedula);
    }

    // Métodos para Carros
    public List<CarroModel> obtenerCarros() {
        try {
            return carroDAO.obtenerTodosLosCarros();
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public CarroModel buscarCarro(String matricula) throws SQLException, FileNotFoundException {
        return carroDAO.buscarCarroPorMatricula(matricula);
    }

    public void eliminarCliente(String cedula) throws SQLException, FileNotFoundException {
        clienteDAO.eliminarCliente(cedula);
    }

    public void eliminarCarro(String matricula) throws SQLException, FileNotFoundException {
        carroDAO.eliminarCarro(matricula);
    }

    public boolean verificarDisponibilidadCarro(String matricula) throws SQLException, FileNotFoundException {
        return carroDAO.verificarDisponibilidad(matricula);
    }
}
