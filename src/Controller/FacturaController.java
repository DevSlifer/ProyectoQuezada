/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ClienteModel;
import Model.DAOS.FacturaDAO;
import Model.FacturaModel;
import Views.PaneldeFacturacion1;
import Views.frmDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supre
 */
public class FacturaController implements ActionListener {

    private final PaneldeFacturacion1 paneldeFacturacion1;
    private final FacturaDAO facturaDAO;
    private final FacturaModel facturaModel;

    public FacturaController(PaneldeFacturacion1 paneldeFacturacion1) {
        this.paneldeFacturacion1 = paneldeFacturacion1;
        this.facturaDAO = new FacturaDAO();
        this.facturaModel = new FacturaModel();
        this.facturaModel.setCliente(new ClienteModel());
        paneldeFacturacion1.getBtnpanelfacturacionactualizar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacioneliminar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacionguardar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacionbuscar().addActionListener(this);
        listarFacturas();
    }

    public void listarFacturas() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) paneldeFacturacion1.getJtabledatos().getModel();
            modelo.setRowCount(0);
            List<FacturaModel> facturas = facturaDAO.verFactura(null);

            for (FacturaModel factura : facturas) {
                Object[] fila = new Object[6];
                fila[0] = factura.getCliente().getNombre();
                fila[1] = factura.getCliente().getApellido();
                fila[2] = factura.getCliente().getCedula();
                fila[3] = factura.getMonto();
                fila[4] = factura.getCargosAdicionales();
                fila[5] = factura.getFechaDePago();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1,
                    "Error al listar las facturas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarFactura(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Por favor ingrese la cedula del empleado",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirmacion = JOptionPane.showConfirmDialog(paneldeFacturacion1,
                    "¿Está seguro que desea eliminar la factura de la cedula " + cedula + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                facturaDAO.eliminarFactura(cedula);
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Factura eliminado correctamente!", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
                listarFacturas();
            } else {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Operación cancelada por el usuario", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1, "Error al eliminar la factura: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarFactura(String cedula) {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Debe ingresar una cedula para buscar", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DefaultTableModel modelo = (DefaultTableModel) paneldeFacturacion1.getJtabledatos().getModel();
            modelo.setRowCount(0);
            List<FacturaModel> facturas = facturaDAO.verFactura(cedula);
            Object[] fila = new Object[6];
            for (FacturaModel factura : facturas) {
                fila[0] = factura.getCliente().getNombre();
                fila[1] = factura.getCliente().getApellido();
                fila[2] = factura.getCliente().getCedula();
                fila[3] = factura.getMonto();
                fila[4] = factura.getCargosAdicionales();
                fila[5] = factura.getFechaDePago();
                modelo.addRow(fila);
            }
            paneldeFacturacion1.getJtabledatos().setModel(modelo);
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1, "Error al buscar la factura: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarFactura(String cedula) {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Por favor ingrese una cédula",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<FacturaModel> facturas = facturaDAO.verFactura(cedula);
            if (!facturas.isEmpty()) {
                FacturaModel factura = facturas.get(0);
                String montoStr = JOptionPane.showInputDialog(paneldeFacturacion1, "Ingrese el nuevo monto para la factura",
                        factura.getMonto());
                if (montoStr != null && !montoStr.trim().isEmpty()) {
                    try {
                        double nuevoMonto = Double.parseDouble(montoStr);
                        factura.setMonto(nuevoMonto);
                        String fechaStr = JOptionPane.showInputDialog(paneldeFacturacion1, "Ingrese la nueva fecha de pago (YYYY-MM-DD)",
                                factura.getFechaDePago());

                        if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                            try {
                                Date nuevaFecha = java.sql.Date.valueOf(fechaStr);
                                factura.setFechaDePago(nuevaFecha);
                                int resultado = facturaDAO.actualizarFactura(factura);
                                if (resultado > 0) {
                                    JOptionPane.showMessageDialog(paneldeFacturacion1, "Factura actualizada exitosamente");
                                    listarFacturas();
                                }
                            } catch (IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(paneldeFacturacion1,
                                        "Formato de fecha inválido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(paneldeFacturacion1,
                                "Por favor ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "No se encontró ninguna factura para esta cédula");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1, "Error al actualizar la factura: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregarFactura() {
        if (validarCampos()) {
            try {
                if (facturaModel.getCliente() == null) {
                    facturaModel.setCliente(new ClienteModel());
                }
                facturaModel.getCliente().setCedula(paneldeFacturacion1.getTxtpanelfacturacioncedula().getText().trim());
                facturaModel.setFechaInicio(java.sql.Date.valueOf(paneldeFacturacion1.getTxtpanelfacturacionfechainicio().getText().trim()));
                facturaModel.setFechaFin(java.sql.Date.valueOf(paneldeFacturacion1.getTxtpanelfacturacionfechafin().getText().trim()));
                facturaModel.setCargosAdicionales((int) Double.parseDouble(paneldeFacturacion1.getTxtfacturacioncargosadicionales().getText().trim()));
                facturaModel.setFechaDePago(java.sql.Date.valueOf(paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().getText().trim()));
                int resultado = facturaDAO.insertarFactura(facturaModel);
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(paneldeFacturacion1,
                            "Factura generada correctamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    listarFacturas();
                    limpiarCampos();
                    generarFacturaTxt(facturaModel);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(paneldeFacturacion1,
                        "Error al generar la factura: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private boolean validarCampos() {
        if (paneldeFacturacion1.getTxtpanelfacturacioncedula().getText().trim().isEmpty()) {
            mostrarError("Debe ingresar la cédula del cliente",
                    paneldeFacturacion1.getTxtpanelfacturacioncedula());
            return false;
        }

        if (paneldeFacturacion1.getTxtpanelfacturacionfechainicio().getText().trim().isEmpty()) {
            mostrarError("Debe ingresar la fecha de inicio",
                    paneldeFacturacion1.getTxtpanelfacturacionfechainicio());
            return false;
        }

        if (paneldeFacturacion1.getTxtpanelfacturacionfechafin().getText().trim().isEmpty()) {
            mostrarError("Debe ingresar la fecha fin",
                    paneldeFacturacion1.getTxtpanelfacturacionfechafin());
            return false;
        }

        if (paneldeFacturacion1.getTxtfacturacioncargosadicionales().getText().trim().isEmpty()) {
            mostrarError("Debe ingresar los cargos adicionales",
                    paneldeFacturacion1.getTxtfacturacioncargosadicionales());
            return false;
        }

        if (paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().getText().trim().isEmpty()) {
            mostrarError("Debe ingresar la fecha de pago",
                    paneldeFacturacion1.getTxtpaneldefacturacionfechadepago());
            return false;
        }

        try {
            java.sql.Date.valueOf(paneldeFacturacion1.getTxtpanelfacturacionfechainicio().getText().trim());
            java.sql.Date.valueOf(paneldeFacturacion1.getTxtpanelfacturacionfechafin().getText().trim());
            java.sql.Date.valueOf(paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().getText().trim());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1,
                    "Las fechas deben tener el formato YYYY-MM-DD",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(paneldeFacturacion1.getTxtfacturacioncargosadicionales().getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Los cargos adicionales deben ser un número válido",
                    paneldeFacturacion1.getTxtfacturacioncargosadicionales());
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje, JTextField campo) {
        JOptionPane.showMessageDialog(paneldeFacturacion1, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
    }

    private void generarFacturaTxt(FacturaModel factura) {
        try {
            File directory = new File("Facturas generadas");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            java.util.Date fechaActual = new java.util.Date();

            String fileName = String.format("Facturas generadas/Factura_%s_%s.txt",
                    factura.getCliente().getCedula(),
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(fechaActual));

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("=============== FACTURA ===============\n");
                writer.write(String.format("Fecha de emisión: %s\n\n",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fechaActual)));

                writer.write("DATOS DEL CLIENTE\n");
                writer.write(String.format("Cédula: %s\n\n", factura.getCliente().getCedula()));

                writer.write("DETALLES DE LA RESERVA\n");
                writer.write(String.format("Fecha de inicio: %s\n", factura.getFechaInicio()));
                writer.write(String.format("Fecha fin: %s\n", factura.getFechaFin()));
                writer.write(String.format("Fecha de pago: %s\n\n", factura.getFechaDePago()));

                writer.write("DETALLES DEL PAGO\n");
                writer.write(String.format("Monto calculado: $%.2f\n", factura.getMonto()));
                writer.write(String.format("Cargos adicionales: $%.2f\n", factura.getCargosAdicionales()));
                writer.write(String.format("Total a pagar: $%.2f\n",
                        factura.getMonto() + factura.getCargosAdicionales()));

                writer.write("\n====================================");

            }

            JOptionPane.showMessageDialog(paneldeFacturacion1,
                    "Factura generada exitosamente en: " + fileName,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1,
                    "Error al generar el archivo de la factura: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        paneldeFacturacion1.getTxtpanelfacturacioncedula().setText("");
        paneldeFacturacion1.getTxtpanelfacturacionfechainicio().setText("");
        paneldeFacturacion1.getTxtpanelfacturacionfechafin().setText("");
        paneldeFacturacion1.getTxtfacturacioncargosadicionales().setText("");
        paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paneldeFacturacion1.getBtnpanelfacturacionguardar()) {
            agregarFactura();
        } else if (e.getSource() == paneldeFacturacion1.getBtnpanelfacturacionactualizar()) {
            String cedula = paneldeFacturacion1.getTxtpanelfacturacioncedula().getText();
            actualizarFactura(cedula);
        } else if (e.getSource() == paneldeFacturacion1.getBtnpanelfacturacionbuscar()) {
            String cedula = paneldeFacturacion1.getTxtpanelfacturacioncedula().getText();
            buscarFactura(cedula);
        } else if (e.getSource() == paneldeFacturacion1.getBtnpanelfacturacioneliminar()) {
            String cedula = paneldeFacturacion1.getTxtpanelfacturacioncedula().getText();
            try {
                eliminarFactura(cedula);
            } catch (SQLException ex) {
                Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}