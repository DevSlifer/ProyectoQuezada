/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Supre
 */
public class ClienteModel {
    
    
     //Getters y setters el acceso a la informacion , y seteo de la informacion
    //Con los mismos atributos de la base de datos
    private int idCliente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String licencia;
    private String telefono;
    private DireccionClienteModel dirreccion; //Objeto del "DireccionCliente para poder acceder desde esta misma clase"

    public DireccionClienteModel getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(DireccionClienteModel dirreccion) {
        this.dirreccion = dirreccion;
    }
    
    
    
    public ClienteModel() {
    }

    public ClienteModel(String nombre, String apellido, String cedula, String licencia, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.licencia = licencia;
        this.telefono = telefono;
    }

    // Getters and Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
