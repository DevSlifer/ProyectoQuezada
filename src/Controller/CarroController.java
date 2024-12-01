package Controller;

import Model.CarroModel;
import Model.DAOS.CarroDAO;
import Views.PaneldeRegistros;
import Views.RegistrodeCarros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public final class CarroController implements ActionListener {

    CarroDAO carroDAO;
    CarroModel carroModel;
    RegistrodeCarros registrodeCarros;
    PaneldeRegistros paneldeRegistros;

    public CarroController(RegistrodeCarros registrodeCarros, PaneldeRegistros paneldeRegistros) throws SQLException, FileNotFoundException {
        this.carroDAO = new CarroDAO();
        this.carroModel = new CarroModel();
        this.registrodeCarros = registrodeCarros;
        this.paneldeRegistros = paneldeRegistros;
        this.registrodeCarros.getBtnregistrocarrosagregar().addActionListener(this);
        this.paneldeRegistros.getBtnregistroactualizar().addActionListener(this);
        this.paneldeRegistros.getBtnregistrobuscar().addActionListener(this);
        this.paneldeRegistros.getBtnregistroeliminar().addActionListener(this);
        listarCarros();
    }

    public void agregar() throws SQLException, FileNotFoundException {
        String marca = registrodeCarros.getTxtregistrocarrosmarca().getText();
        String modelo = registrodeCarros.getTxtregistrocarrosmodelo().getText();
        String placa = registrodeCarros.getTxtregistrocarrosnumdeplaca().getText();
        String matricula = registrodeCarros.getTxtregistrocarrosmatricula().getText();
        int anio;
        double kilometraje, precioPorDia;

        try {
            anio = Integer.parseInt(registrodeCarros.getTxtregistrocarrosaño().getText().trim());
            kilometraje = Double.parseDouble(registrodeCarros.getTxtregistrocarroskilometraje().getText().trim());
            precioPorDia = Double.parseDouble(registrodeCarros.getTxtregistrocarrospreciopordia().getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(registrodeCarros,
                    "Por favor, ingrese valores numéricos válidos",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        carroModel.setMarca(marca);
        carroModel.setModelo(modelo);
        carroModel.setAnio(anio);
        carroModel.setPlaca(placa);
        carroModel.setKilometraje(kilometraje);
        carroModel.setPrecioPorDia(precioPorDia);
        carroModel.setMatricula(matricula);

        if (validarCampos(registrodeCarros) > 0) {
            try {
                carroDAO.insertarCarro(carroModel);
                JOptionPane.showMessageDialog(registrodeCarros, "Carro agregado correctamente!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                listarCarros();
                limpiarCampos();
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(registrodeCarros, "Error al agregar el carro!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
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

    public void buscarPorMatricula(String matricula) {
        try {
            if (matricula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros,
                        "Por favor ingrese una matrícula", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<CarroModel> carros = carroDAO.verCarros(matricula);
            DefaultTableModel modelo = (DefaultTableModel) paneldeRegistros.getTblCarro().getModel();
            modelo.setRowCount(0);

            if (!carros.isEmpty()) {
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
            } else {
                JOptionPane.showMessageDialog(paneldeRegistros,
                        "No se encontró ningún carro con esa matrícula");
                listarCarros();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeRegistros,
                    "Error al buscar el carro: " + e.getMessage());
        }
    }

    public void eliminarPorMatricula(String matricula) {
        try {
            if (matricula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros,
                        "Por favor ingrese una matrícula", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(paneldeRegistros,
                    "¿Está seguro de eliminar el carro con matrícula " + matricula + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                int resultado = carroDAO.eliminarCarro(matricula);
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(paneldeRegistros,
                            "Carro eliminado exitosamente");
                    listarCarros();
                } else {
                    JOptionPane.showMessageDialog(paneldeRegistros,
                            "No se pudo eliminar el carro");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeRegistros,
                    "Error al eliminar el carro: " + e.getMessage());
        }
    }

    public void actualizarCarro(String matricula) {
        try {
            if (matricula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeRegistros,
                        "Por favor ingrese una matrícula", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<CarroModel> carros = carroDAO.verCarros(matricula);
            if (!carros.isEmpty()) {
                CarroModel carro = carros.get(0);

                String nuevoPrecioStr = JOptionPane.showInputDialog(paneldeRegistros,
                        "Ingrese el nuevo precio por día para el carro " + carro.getMarca() + " " + carro.getModelo(),
                        "Actualizar Precio",
                        JOptionPane.QUESTION_MESSAGE);

                if (nuevoPrecioStr != null && !nuevoPrecioStr.trim().isEmpty()) {
                    try {
                        double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                        carro.setPrecioPorDia(nuevoPrecio);

                        int resultado = carroDAO.actualizarCarro(carro);
                        if (resultado > 0) {
                            JOptionPane.showMessageDialog(paneldeRegistros,
                                    "Precio actualizado exitosamente");
                            listarCarros();
                        } else {
                            JOptionPane.showMessageDialog(paneldeRegistros,
                                    "No se pudo actualizar el precio");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(paneldeRegistros,
                                "Por favor ingrese un precio válido");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(paneldeRegistros,
                        "No se encontró ningún carro con esa matrícula");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeRegistros,
                    "Error al actualizar el precio: " + e.getMessage());
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
        } else if (registrodeCarros.getTxtregistrocarrosnumdeplaca().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de placa no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosnumdeplaca().requestFocus();
            validacion = 0;
        } else if (registrodeCarros.getTxtregistrocarrosmatricula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(registrodeCarros, "El campo de matricula no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            registrodeCarros.getTxtregistrocarrosmatricula().requestFocus();
            validacion = 0;
        }
        try {
            Integer.valueOf(registrodeCarros.getTxtregistrocarrosaño().getText().trim());
            Double.valueOf(registrodeCarros.getTxtregistrocarroskilometraje().getText().trim());
            Double.valueOf(registrodeCarros.getTxtregistrocarrospreciopordia().getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(registrodeCarros,
                    "Por favor, verifique que los campos numéricos contengan valores válidos",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrodeCarros.getBtnregistrocarrosagregar()) {
            try {
                agregar();
            } catch (SQLException ex) {
                Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == paneldeRegistros.getBtnregistrobuscar()) {
            String matricula = paneldeRegistros.getTxtpanelregistromatricula1().getText();
            buscarPorMatricula(matricula);
        } else if (e.getSource() == paneldeRegistros.getBtnregistroeliminar()) {
            String matricula = paneldeRegistros.getTxtpanelregistromatricula1().getText();
            eliminarPorMatricula(matricula);
        } else if (e.getSource() == paneldeRegistros.getBtnregistroactualizar()) {
            String matricula = paneldeRegistros.getTxtpanelregistromatricula1().getText();
            actualizarCarro(matricula);
        }
    }
}
