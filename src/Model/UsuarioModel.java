// UsuarioModel.java
package Model;

public class UsuarioModel {

    private String contrasena;
    private String rol;
    private String email;

    // Constructor
    public UsuarioModel(String contrasena, String email) {
        this.contrasena = contrasena;
        this.email = email;
    }

    // Getters y Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
