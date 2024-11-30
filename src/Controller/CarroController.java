package Controller;

import Model.CarroModel;
import Model.DAOS.CarroDAO;
import Views.PaneldeRegistros;
import Views.RegistrodeCarros;
import Views.frmDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CarroController implements ActionListener {

    CarroDAO carroDAO;
    CarroModel carroModel;
    RegistrodeCarros registrodeCarros;
    PaneldeRegistros paneldeRegistros;

    public CarroController(RegistrodeCarros registrodeCarros, PaneldeRegistros paneldeRegistros) throws SQLException, FileNotFoundException {
        this.carroDAO = new CarroDAO();
        this.carroModel = new CarroModel();
        this.registrodeCarros = registrodeCarros;
        this.paneldeRegistros = paneldeRegistros;
        listarCarros();
        configurarListeners();
    }

    private void configurarListeners() {
        this.registrodeCarros.getBtnregistrocarrosagregar().addActionListener(this);
    }

    public void agregar() {
        String marca = registrodeCarros.getTxtregistrocarrosmarca().getText();
        String modelo = registrodeCarros.getTxtregistrocarrosmodelo().getText();
        int anio = Integer.parseInt(registrodeCarros.getTxtregistrocarrosaño().getText());
        String placa = registrodeCarros.getTxtregistrocarrosnumdeplaca().getText();
        double kilometraje = Double.parseDouble(registrodeCarros.getTxtregistrocarroskilometraje().getText());
        double precioPorDia = Double.parseDouble(registrodeCarros.getTxtregistrocarrospreciopordia().getText());
        String matricula = registrodeCarros.getTxtregistrocarrosmatricula().getText();

        carroModel.setMarca(marca);
        carroModel.setModelo(modelo);
        carroModel.setAnio(anio);
        carroModel.setPlaca(placa);
        carroModel.setKilometraje(kilometraje);
        carroModel.setPrecioPorDia(precioPorDia);
        carroModel.setMatricula(matricula);

        if (validarCampos(registrodeCarros) > 1) {
            try {
                carroDAO.insertarCarro(carroModel);
                JOptionPane.showMessageDialog(registrodeCarros, "Carro agregado correctamente!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                listarCarros();
                limpiarCampos();
            } catch (FileNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(registrodeCarros, "Error al agregar el carro!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int validarCampos(RegistrodeCarros registrodeCarros) {
        int validacion = 1;
        if (registrodeCarros.getTxtregistrocarrosmarca().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de marca no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosmarca().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrosmarca().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de modelo no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosmodelo().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrosaño().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de año no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosaño().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrosnumdeplaca().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de placa no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosnumdeplaca().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarroskilometraje().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de kilometraje no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarroskilometraje().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrospreciopordia().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de precio por dia no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrospreciopordia().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrosmatricula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de matricula no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosmatricula().requestFocus();
            validacion = 0;
        }
        return validacion;
    }

    private void limpiarCampos() {
        registrodeCarros.getTxtregistrocarrosmarca().setText("");
        registrodeCarros.getTxtregistrocarrosmodelo().setText("");
        registrodeCarros.getTxtregistrocarrosaño().setText("");
        registrodeCarros.getTxtregistrocarrosnumdeplaca().setText("");
        registrodeCarros.getTxtregistrocarroskilometraje().setText("");
        registrodeCarros.getTxtregistrocarrospreciopordia().setText("");
        registrodeCarros.getTxtregistrocarrosmatricula().setText("");
    }

    public void listarCarros() throws SQLException, FileNotFoundException {
        DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblCarro().getModel();
        modelo.setRowCount(0);
        List<CarroModel> carros = carroDAO.verCarros(null);

        for (CarroModel carro : carros) {
            Object[] fila = new Object[8];
            fila[0] = carro.getIdCarro();
            fila[1] = carro.getMarca();
            fila[2] = carro.getModelo();
            fila[3] = carro.getAnio();
            fila[4] = carro.getPlaca();
            fila[5] = carro.getMatricula();
            fila[6] = carro.getKilometraje();
            fila[7] = carro.getPrecioPorDia();
            modelo.addRow(fila);
        }
    }

    public static void main(String[] args) {
        try {
            frmDashboard dash = new frmDashboard();
            RegistrodeCarros registrodeCarros = new RegistrodeCarros();
            PaneldeRegistros paneldeRegistros = new PaneldeRegistros();
            CarroController controller = new CarroController(registrodeCarros, paneldeRegistros);
            dash.escritorio.add(registrodeCarros);
            dash.escritorio.add(paneldeRegistros);
            dash.setVisible(true);
            registrodeCarros.setVisible(true);
            paneldeRegistros.setVisible(true);
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e
    ) {
        if (e.getSource() == registrodeCarros.getBtnregistrocarrosagregar()) {
            agregar();
        }
    }

}
