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
public class Lista_Cancion implements Serializable{
    
    Lista lista;
    Cancion cancion;
    
    static final long serialVersionUID= 0x5L;

    public Lista_Cancion() {
    }

    public Lista_Cancion(Lista lista) {
        this.lista = lista;
    }

    public Lista_Cancion(Lista lista, Cancion cancion) {
        this.lista = lista;
        this.cancion = cancion;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    @Override
    public String toString() {
        return "Lista_Cancion{" + "lista=" + lista + ", cancion=" + cancion + '}';
    }
    
    
    
}
