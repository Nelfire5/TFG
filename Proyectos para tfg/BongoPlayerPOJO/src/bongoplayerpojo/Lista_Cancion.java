

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
    
    Integer lista;
    Integer cancion;
    
    static final long serialVersionUID= 0x5L;

    public Lista_Cancion() {
    }

    public Lista_Cancion(Integer lista) {
        this.lista = lista;
    }

    public Lista_Cancion(Integer lista, Integer cancion) {
        this.lista = lista;
        this.cancion = cancion;
    }

    public Integer getLista() {
        return lista;
    }

    public void setLista(Integer lista) {
        this.lista = lista;
    }

    public Integer getCancion() {
        return cancion;
    }

    public void setCancion(Integer cancion) {
        this.cancion = cancion;
    }

    @Override
    public String toString() {
        return "Lista_Cancion{" + "lista=" + lista + ", cancion=" + cancion + '}';
    }
    
    
    
}
