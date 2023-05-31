/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bongoplayerpojo;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Lista implements Serializable{
    
    Integer listaId;
    String nombreLista;
    String iconoLista;
    Date fechaCreacion;
    Usuario usuario;
    
    static final long serialVersionUID= 0x4L;

    public Lista() {
    }

    public Lista(Integer listaId) {
        this.listaId = listaId;
    }

    public Lista(Integer listaId, String nombre, String iconoLista, Date fechaCreacion, Usuario usuario) {
        this.listaId = listaId;
        this.nombreLista = nombre;
        this.iconoLista = iconoLista;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }

    public Integer getListaId() {
        return listaId;
    }

    public void setListaId(Integer listaId) {
        this.listaId = listaId;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getIconoLista() {
        return iconoLista;
    }

    public void setIconoLista(String iconoLista) {
        this.iconoLista = iconoLista;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Lista{" + "listaId=" + listaId + ", nombre=" + nombreLista + ", iconoLista=" + iconoLista + ", fechaCreacion=" + fechaCreacion + ", usuario=" + usuario + '}';
    }
    
    
}
