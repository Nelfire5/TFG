



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
public class Cancion implements Serializable{
    
    Integer cancionId;
    String nombre;
    String duracion;
    String artista;
    String archivo;

    static final long serialVersionUID= 0x1L;
    
    public Cancion() {
    }

    public Cancion(Integer cancionId) {
        this.cancionId = cancionId;
    }

    public Cancion(Integer cancionId, String nombre, String duracion, String artista, String archivo) {
        this.cancionId = cancionId;
        this.nombre = nombre;
        this.duracion = duracion;
        this.artista = artista;
        this.archivo = archivo;
    }


    public Integer getCancionId() {
        return cancionId;
    }

    public void setCancionId(Integer cancionId) {
        this.cancionId = cancionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Cancion{" + "cancionId=" + cancionId + ", nombre=" + nombre + ", duracion=" + duracion + ", artista=" + artista + ", archivo=" + archivo + '}';
    }

    
    
    
    
}
