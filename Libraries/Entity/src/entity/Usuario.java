/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author jonathan
 */
public class Usuario {
    
    
    private String cedula;
    private String password;
    private String rol;

    public Usuario() {
    }

    
    public Usuario(String cedula, String password, String rol) {
        this.cedula = cedula;
        this.password = password;
        this.rol = rol;
    }
    
     public Usuario(String cedula, String rol) {
        this.cedula = cedula;
        this.password = null;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", password=" + password + ", rol=" + rol + '}';
    }
    
    
    
    
}
