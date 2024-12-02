// UsuarioModel.java
package Model;

import java.util.Date;

public class UsuarioModel {

    
    //Atributos, getters y setters para la tabla de usuarios.
    private int idUsuario;
    private String email;
    private String contrasena;
    private String rol;
    private String estado;
    private Date fechaCreacion;

    public UsuarioModel() {
    }

    public UsuarioModel(String email, String contrasena, String rol) {
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = "activo";
        this.fechaCreacion = new Date();
    }

    public UsuarioModel(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }
    // Getters and Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
