/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAOS.FacturaDAO;
import Model.FacturaModel;
import Views.PaneldeFacturacion1;
import Views.frmDashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        paneldeFacturacion1.getBtnpanelfacturacionactualizar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacioneliminar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacionguardar().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturacionpagos().addActionListener(this);
        paneldeFacturacion1.getBtnpanelfacturaciongeneralrecibos().addActionListener(this);
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
            paneldeFacturacion1.getJtabledatos().setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1,
                    "Error al listar las facturas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public void buscarFactura(String cedula){
        try{
            if(cedula.trim().isEmpty()){
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

    public void agregarFactura() throws SQLException, FileNotFoundException {
        if (validarCampos(paneldeFacturacion1) > 1) {
            try {
                facturaModel.getCliente().setNombre(paneldeFacturacion1.getTxtpanelfacturacionnombre().getText());
                facturaModel.getCliente().setApellido(paneldeFacturacion1.getTxtpanelfacturacionapellido().getText());
                facturaModel.getCliente().setCedula(paneldeFacturacion1.getTxtpanelfacturacioncedula().getText());
                facturaModel.setMonto(Double.parseDouble(paneldeFacturacion1.getTxtpanelfacturacionmonto().getText()));
                facturaModel.setCargosAdicionales((int) Double.parseDouble(paneldeFacturacion1.getTxtfacturacioncargosadicionales().getText()));
                facturaModel.setFechaDePago(java.sql.Date.valueOf(paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().getText()));
                facturaDAO.insertarFactura(facturaModel);
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Factura guardada correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                borrarCampos();
                listarFacturas();
            } catch (SQLException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Error al guardar la factura: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarFactura(String cedula) {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Debe seleccionar una factura para eliminar",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirmacion = JOptionPane.showInternalConfirmDialog(paneldeFacturacion1, "¿Está seguro de eliminar la factura?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                facturaDAO.eliminarFactura(cedula);
                JOptionPane.showMessageDialog(paneldeFacturacion1, "Factura eliminada correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                listarFacturas();
            } else {
                JOptionPane.showMessageDialog(paneldeFacturacion1, "La factura no se ha eliminado", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(paneldeFacturacion1, "Error al eliminar la factura: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int validarCampos(PaneldeFacturacion1 facturas) {
        int validacion = 1;
        if (facturas.getTxtpanelfacturacionnombre().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(facturas, "El campo de nombre no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            facturas.getTxtpanelfacturacionnombre().requestFocus();
            validacion = 0;
        } else if (facturas.getTxtpanelfacturacioncedula().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(facturas, "El campo de cedula no puede estar vacion", "Error!", JOptionPane.ERROR_MESSAGE);
            facturas.getTxtpanelfacturacioncedula().requestFocus();
            validacion = 0;
        } else if (facturas.getTxtpanelfacturacionapellido().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(facturas, "El campo de apellido no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
            facturas.getTxtpanelfacturacionapellido().requestFocus();
            validacion = 0;
        }
        try {
            Double.parseDouble(facturas.getTxtfacturacioncargosadicionales().getText());
            Double.parseDouble(facturas.getTxtpanelfacturacionmonto().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(facturas, "El campo de monto y cargos adicionales deben ser numericos", "Error!", JOptionPane.ERROR_MESSAGE);
            facturas.getTxtpanelfacturacionmonto().requestFocus();
            validacion = 0;
        }
        try {
            java.sql.Date sqldate = java.sql.Date.valueOf(facturas.getTxtpaneldefacturacionfechadepago().getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(facturas, "El campo de fecha de pago debe tener el formato yyyy-mm-dd", "Error!", JOptionPane.ERROR_MESSAGE);
            facturas.getTxtpaneldefacturacionfechadepago().requestFocus();
        }
        return validacion;
    }

    private void borrarCampos() {
        paneldeFacturacion1.getTxtfacturacioncargosadicionales().setText("");
        paneldeFacturacion1.getTxtpaneldefacturacionfechadepago().setText("");
        paneldeFacturacion1.getTxtpanelfacturacionapellido().setText("");
        paneldeFacturacion1.getTxtpanelfacturacioncedula().setText("");
        paneldeFacturacion1.getTxtpanelfacturacionapellido().setText("");
        paneldeFacturacion1.getTxtpanelfacturacionnombre().setText("");
    }

    public static void main(String[] args) {
        frmDashboard dash = new frmDashboard();
        PaneldeFacturacion1 paneldeFacturacion1 = new PaneldeFacturacion1();
        FacturaController controller = new FacturaController(paneldeFacturacion1);
        dash.escritorio.add(paneldeFacturacion1);
        dash.setVisible(true);
        paneldeFacturacion1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == paneldeFacturacion1.getBtnpanelfacturacioneliminar()){
            String cedula = paneldeFacturacion1.getTxtpanelfacturacioncedula().getText();
            eliminarFactura(cedula);
        }
    }
}
