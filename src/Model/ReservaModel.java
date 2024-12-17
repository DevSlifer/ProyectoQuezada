package Model;

import java.util.Date;

public class ReservaModel {

    private int idReservacion;
    private Date fechaReservacion;
    private Date fechaDeEntrega;
    private Date fechaDevolucion;
    private boolean cancelacion;
    private int diasTotal;
    private double montoEstimado;
    private int idCliente;
    private int idCarro;
    private ClienteModel cliente; //Objeto del tipo "cliente" para poder acceder a todos sus datos
    private CarroModel carro; //Objeto del tipo "Carro" para poder acceder a todos sus datos y poder reservar

    public int getDiasTotal() {
        return diasTotal;
    }
    
    public void setDiasTotal(int diasTotal) {
        this.diasTotal = diasTotal;
    }

    public double getMontoEstimado() {
        return montoEstimado;
    }

    public void setMontoEstimado(double montoEstimado) {
        this.montoEstimado = montoEstimado;
    }

    

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public CarroModel getCarro() {
        return carro;
    }

    public void setCarro(CarroModel carro) {
        this.carro = carro;
    }    

    public ReservaModel() {
    }

    public ReservaModel(Date fechaDeEntrega, Date fechaDevolucion, int idCliente, int idCarro) {
        this.fechaReservacion = new Date();
        this.fechaDeEntrega = fechaDeEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.cancelacion = false;
        this.idCliente = idCliente;
        this.idCarro = idCarro;
    }

    // Getters and Setters
    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(boolean cancelacion) {
        this.cancelacion = cancelacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
}
