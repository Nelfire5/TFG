/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bongoplayerpojocomunicaciones;

import java.io.Serializable;

/**
 *
 * @author Nelson
 */
public class Peticion implements Serializable{
    
    private Integer idOperacion;  
    private Integer pk; 
    private Object objeto; 
    
    public static final long serialVersionUID = 0x111L;
    
    public static final int INSERTAR_USUARIO = 1;
    public static final int ELIMINAR_USUARIO = 2;
    public static final int MODIFICAR_USUARIO = 3;
    public static final int LEER_USUARIO = 4;
    public static final int LEER_USUARIOS = 5;
    
    public static final int INSERTAR_LISTA = 6;
    public static final int ELIMINAR_LISTA = 7;
    public static final int MODIFICAR_LISTA = 8;
    public static final int LEER_LISTA = 9;
    public static final int LEER_LISTAS = 10;
    
    public static final int INSERTAR_CANCION = 11;
    public static final int ELIMINAR_CANCION = 12;
    public static final int MODIFICAR_CANCION= 13;
    public static final int LEER_CANCION = 14;
    public static final int LEER_CANCIONES = 15;
    

    public Peticion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Peticion(Integer idOperacion, Integer pk) {
        this.idOperacion = idOperacion;
        this.pk = pk;
    }

    public Peticion(Integer idOperacion, Object objeto) {
        this.idOperacion = idOperacion;
        this.objeto = objeto;
    }

    public Peticion(Integer idOperacion, Integer pk, Object objeto) {
        this.idOperacion = idOperacion;
        this.pk = pk;
        this.objeto = objeto;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    
    @Override
    public String toString() {
        return "Peticion{" + "idOperacion=" + idOperacion + ", pk=" + pk + ", objeto=" + objeto + '}';
    }
}
