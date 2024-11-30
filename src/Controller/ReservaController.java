package Controller;

import Model.ReservaModel;
import Model.DAOS.ReservaDAO;
import Views.ViewReservacionesdeClientes;
import Views.PaneldeReservaciones;
import Views.frmDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservaController {

    private final ReservaDAO reservaDAO;
    private final ReservaModel reservaModel;
    private final ViewReservacionesdeClientes viewReservacionesdeClientes;
    private final PaneldeReservaciones paneldeReservaciones;

    public ReservaController(ViewReservacionesdeClientes viewReservacionesdeClientes, PaneldeReservaciones paneldeReservaciones) throws SQLException, FileNotFoundException {
        this.reservaDAO = new ReservaDAO();
        this.reservaModel = new ReservaModel();
        this.viewReservacionesdeClientes = viewReservacionesdeClientes;
        this.paneldeReservaciones = paneldeReservaciones;
        listarReservas();
        //configurarListeners();
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        frmDashboard dash = new frmDashboard();
        ViewReservacionesdeClientes viewReservacionesdeClientes = new ViewReservacionesdeClientes();
        PaneldeReservaciones paneldeReservaciones = new PaneldeReservaciones();
        ReservaController controller = new ReservaController(viewReservacionesdeClientes, paneldeReservaciones);
        dash.escritorio.add(viewReservacionesdeClientes);
        dash.escritorio.add(paneldeReservaciones);
        dash.setVisible(true);
        viewReservacionesdeClientes.setVisible(true);
        paneldeReservaciones.setVisible(true);
    }

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
}
