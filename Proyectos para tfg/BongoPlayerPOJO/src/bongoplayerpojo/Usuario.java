/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bongoplayerpojo;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Usuario implements Serializable{
    
    Integer usuarioId;
    String nombreUsuario;
    String apellido1;
    String apellido2;
    String correo;
    String contrasena;
    String iconoUsuario;
    String alias;

    static final long serialVersionUID= 0x6L;
    
    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String nombre, String apellido1, String apellido2, String correo, String contrasena, String iconoUsuario, String alias) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.contrasena = contrasena;
        this.iconoUsuario = iconoUsuario;
        this.alias = alias;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getIconoUsuario() {
        return iconoUsuario;
    }

    public void setIconoUsuario(String iconoUsuario) {
        this.iconoUsuario = iconoUsuario;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", nombre=" + nombreUsuario + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", correo=" + correo + ", contrasena=" + contrasena + ", iconoUsuario=" + iconoUsuario + ", alias=" + alias + '}';
    }
    
    
    
}
