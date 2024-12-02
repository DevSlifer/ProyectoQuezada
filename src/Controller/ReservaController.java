package Controller;

import Model.ClienteModel;
import Model.ReservaModel;
import Model.CarroModel;
import Model.DAOS.ReservaDAO;
import Views.ViewReservacionesdeClientes;
import Views.PaneldeReservaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservaController implements ActionListener {

    //Llamada a todas las clases y paneles necesarios
    private final ReservaDAO reservaDAO;
    private final ReservaModel reservaModel;
    private final ViewReservacionesdeClientes viewReservacionesdeClientes;
    private final PaneldeReservaciones paneldeReservaciones;

    public ReservaController(ViewReservacionesdeClientes viewReservacionesdeClientes, PaneldeReservaciones paneldeReservaciones) throws SQLException, FileNotFoundException {
        this.reservaDAO = new ReservaDAO();
        this.reservaModel = new ReservaModel();
        this.reservaModel.setCliente(new ClienteModel());
        this.reservaModel.setCarro(new CarroModel());
        this.paneldeReservaciones = paneldeReservaciones;
        this.viewReservacionesdeClientes = viewReservacionesdeClientes;
        this.paneldeReservaciones.getBtnpanelreservacionesguardar().addActionListener(this);
        this.viewReservacionesdeClientes.getBtnviewreservacionesdeclientesbuscar().addActionListener(this);
        this.viewReservacionesdeClientes.getBtnviewreservacionesdeclientesborrar().addActionListener(this);
        listarReservas(); // Una vez iniciado el componente automaticamente listar las reservas
    }

    //Listar todas las reservas
    public void listarReservas() throws FileNotFoundException, SQLException {
        try {
            DefaultTableModel modelo = (DefaultTableModel) viewReservacionesdeClientes.getJtableviewreservaciones().getModel();
            modelo.setRowCount(0);
            List<ReservaModel> reservas = reservaDAO.verReserva(null);
            for (ReservaModel reserva : reservas) {
                Object[] fila = new Object[13];
                fila[0] = reserva.getCliente().getNombre();
                fila[1] = reserva.getCliente().getApellido();
                fila[2] = reserva.getCliente().getCedula();
                fila[3] = reserva.getCliente().getTelefono();
                fila[4] = reserva.getCarro().getMarca();
                fila[5] = reserva.getCarro().getModelo();
                fila[6] = reserva.getCarro().getPlaca();
                fila[7] = reserva.getCarro().getPrecioPorDia();
                fila[8] = reserva.getFechaReservacion();
                fila[9] = reserva.getFechaDeEntrega();
                fila[10] = reserva.getFechaDevolucion();
                fila[11] = reserva.getDiasTotal();
                fila[12] = reserva.getMontoEstimado();
                modelo.addRow(fila);
            }
            viewReservacionesdeClientes.getJtableviewreservaciones().setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(viewReservacionesdeClientes,
                    "Error al listar los clientes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Buscar reserva por la cedula del cliente
    public void buscarReserva(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(viewReservacionesdeClientes, "El campo de cedula no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DefaultTableModel modelo = (DefaultTableModel) viewReservacionesdeClientes.getJtableviewreservaciones().getModel();
            modelo.setRowCount(0);
            List<ReservaModel> facturas = reservaDAO.verReserva(cedula);
            for (ReservaModel factura : facturas) {
                Object[] fila = new Object[13];
                fila[0] = factura.getCliente().getNombre();
                fila[1] = factura.getCliente().getApellido();
                fila[2] = factura.getCliente().getCedula();
                fila[3] = factura.getCliente().getTelefono();
                fila[4] = factura.getCarro().getMarca();
                fila[5] = factura.getCarro().getModelo();
                fila[6] = factura.getCarro().getPlaca();
                fila[7] = factura.getCarro().getPrecioPorDia();
                fila[8] = factura.getFechaReservacion();
                fila[9] = factura.getFechaDeEntrega();
                fila[10] = factura.getFechaDevolucion();
                fila[11] = factura.getDiasTotal();
                fila[12] = factura.getMontoEstimado();
                modelo.addRow(fila);
            }
            viewReservacionesdeClientes.getJtableviewreservaciones().setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(viewReservacionesdeClientes, "Error al buscar la reserva: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            listarReservas();
        }
    }

    public void agregarReserva() {
        if (validaCampos(paneldeReservaciones) > 1) {
            try {
                // Inicializar modelos
                if (reservaModel.getCliente() == null) {
                    reservaModel.setCliente(new ClienteModel());
                }
                if (reservaModel.getCarro() == null) {
                    reservaModel.setCarro(new CarroModel());
                }

                // Establecer valores
                reservaModel.getCliente().setCedula(paneldeReservaciones.getTxtpanelreservacionescedula().getText().trim());
                reservaModel.getCarro().setPlaca(paneldeReservaciones.getTxtpanelreservacionesplacadelvehiculo().getText().trim());

                // Convertir y validar fechas
                try {
                    reservaModel.setFechaDeEntrega(java.sql.Date.valueOf(
                            paneldeReservaciones.getTxtpanelreservacionesfechaentrega().getText().trim()));
                    reservaModel.setFechaDevolucion(java.sql.Date.valueOf(
                            paneldeReservaciones.getTxtpanelreservacionesfechadevolucion().getText().trim()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(paneldeReservaciones,
                            "Formato de fecha inválido. Use YYYY-MM-DD",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Intentar insertar
                int resultado = reservaDAO.insertarReserva(reservaModel);

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(paneldeReservaciones,
                            "Reserva guardada exitosamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    listarReservas();
                }
            } catch (SQLException e) {
                // Mostrar mensaje específico del SP
                JOptionPane.showMessageDialog(paneldeReservaciones,
                        "Error en la reserva: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(paneldeReservaciones,
                        "Error inesperado: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Eliminar reservacion por cedula
    public void eliminarReserva(String cedula) throws SQLException, FileNotFoundException {
        try {
            if (cedula.trim().isEmpty()) {
                JOptionPane.showMessageDialog(viewReservacionesdeClientes, "El campo de cedula no debe estar vacio!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(viewReservacionesdeClientes, "¿Estas seguro de eliminar la reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                reservaDAO.eliminarReserva(cedula);
                JOptionPane.showMessageDialog(viewReservacionesdeClientes, "Reserva eliminada con exito!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                listarReservas();
            } else {
                JOptionPane.showMessageDialog(viewReservacionesdeClientes, "La reserva no se ha eliminado", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(viewReservacionesdeClientes, "Error al eliminar la reserva: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int validaCampos(PaneldeReservaciones paneldeReservaciones) {
        int validacion = 2; // Cambiamos el valor inicial a 2, para saber si todos 
        //los campos estan vacios o llenos

        if (paneldeReservaciones.getTxtpanelreservacionescedula().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(paneldeReservaciones,
                    "El campo de cedula no debe estar vacio!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            paneldeReservaciones.getTxtpanelreservacionescedula().requestFocus();
            return 0;
        }

        if (paneldeReservaciones.getTxtpanelreservacionesfechaentrega().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(paneldeReservaciones,
                    "El campo de fecha de entrega no debe estar vacio!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            paneldeReservaciones.getTxtpanelreservacionesfechaentrega().requestFocus();
            return 0;
        }

        if (paneldeReservaciones.getTxtpanelreservacionesfechadevolucion().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(paneldeReservaciones,
                    "El campo de fecha de devolucion no debe estar vacio!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            paneldeReservaciones.getTxtpanelreservacionesfechadevolucion().requestFocus();
            return 0;
        }

        if (paneldeReservaciones.getTxtpanelreservacionesplacadelvehiculo().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(paneldeReservaciones,
                    "El campo de placa no debe estar vacio!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            paneldeReservaciones.getTxtpanelreservacionesplacadelvehiculo().requestFocus();
            return 0;
        }

        // Si todos los campos están llenos y válidos
        return validacion;
    }

    //Una vez agregada una reserva esge borra todos los campos
    private void limpiarCampos() {
        paneldeReservaciones.getTxtpanelreservacionescedula().setText("");
        paneldeReservaciones.getTxtpanelreservacionesfechaentrega().setText("");
        paneldeReservaciones.getTxtpanelreservacionesfechadevolucion().setText("");
        paneldeReservaciones.getTxtpanelreservacionesplacadelvehiculo().setText("");
    }

    //Para que funcione los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewReservacionesdeClientes.getBtnviewreservacionesdeclientesbuscar()) {
            String cedula = viewReservacionesdeClientes.getTxtviewreservacionesdeclientescedula().getText();
            try {
                buscarReserva(cedula);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == paneldeReservaciones.getBtnpanelreservacionesguardar()) {
            agregarReserva();
        } else if (e.getSource() == viewReservacionesdeClientes.getBtnviewreservacionesdeclientesborrar()) {
            String cedula = viewReservacionesdeClientes.getTxtviewreservacionesdeclientescedula().getText();
            try {
                eliminarReserva(cedula);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
