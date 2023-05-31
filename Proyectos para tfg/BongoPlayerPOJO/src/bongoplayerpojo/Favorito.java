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
public class Favorito implements Serializable{
    
    Usuario usuario;
    Cancion cancion;
    
    static final long serialVersionUID= 0x3L;

    public Favorito() {
    }

    public Favorito(Usuario usuario) {
        this.usuario = usuario;
    }

    public Favorito(Usuario usuario, Cancion cancion) {
        this.usuario = usuario;
        this.cancion = cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
    
    
}
