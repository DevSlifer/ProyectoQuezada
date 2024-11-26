/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Supre
 */
public class CarroModel {
    private int idCarro;
    private String marca;
    private String modelo;
    private int anio;
    private String tipoVehiculo;
    private String NumPlaca;
    private String NumChasis;
    private String matricula;
    private double kilometraje;
    
    

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getMarca() {
        return marca;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }
    

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }



    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNumPlaca() {
        return NumPlaca;
    }

    public void setNumPlaca(String NumPlaca) {
        this.NumPlaca = NumPlaca;
    }

    public String getNumChasis() {
        return NumChasis;
    }

    public void setNumChasis(String NumChasis) {
        this.NumChasis = NumChasis;
    }
    
    
        @Override
    public String toString() {
        return "CarroModel{" +
                "idCarro=" + idCarro +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", placa='" + NumPlaca + '\'' +
                ", numChasis='" + NumChasis + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
