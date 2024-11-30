/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Supre
 */
public class DireccionClienteModel {

    private int idDireccion;
    private String provincia;
    private String sector;
    private String calle;
    private int numeroDeCasa;
    private int idCliente;

    public DireccionClienteModel() {
    }

    public DireccionClienteModel(String provincia, String sector, String calle, int numeroDeCasa, int idCliente) {
        this.provincia = provincia;
        this.sector = sector;
        this.calle = calle;
        this.numeroDeCasa = numeroDeCasa;
        this.idCliente = idCliente;
    }

    // Getters and Setters
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroDeCasa() {
        return numeroDeCasa;
    }

    public void setNumeroDeCasa(int numeroDeCasa) {
        this.numeroDeCasa = numeroDeCasa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
