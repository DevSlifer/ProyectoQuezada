package Model;

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Supre
 */
public class FacturaModel {

    //Getters y setters el acceso a la informacion , y seteo de la informacion
    //Con los mismos atributos de la base de datos
    private int idFactura;
    private double monto;
    private int cargosAdicionales;
    private Date fechaDePago;
    private Date fechaInicio;
    private Date fechaFin;
    private ReservaModel reserva;
    private int idCliente; //Como en la entidad de la BD,un cliente puede hacer muchas reservas
    private ClienteModel cliente; //Objecto del tipo "Cliente" para poder acceder a sus datos

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public ReservaModel getReserva() {
        return reserva;
    }

    public void setReserva(ReservaModel reserva) {
        this.reserva = reserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public FacturaModel() {
    }

    public FacturaModel(double monto, int cargosAdicionales, Date fechaDePago, int idCliente) {
        this.monto = monto;
        this.cargosAdicionales = cargosAdicionales;
        this.fechaDePago = fechaDePago;
        this.idCliente = idCliente;
    }

    // Getters and Setters
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCargosAdicionales() {
        return cargosAdicionales;
    }

    public void setCargosAdicionales(int cargosAdicionales) {
        this.cargosAdicionales = cargosAdicionales;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
