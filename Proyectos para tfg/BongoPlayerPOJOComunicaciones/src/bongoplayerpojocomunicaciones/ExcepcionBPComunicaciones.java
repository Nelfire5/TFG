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
public class ExcepcionBPComunicaciones extends Exception implements Serializable{
    
    private String memensajeErrorAdmin;
    private String memensajeErrorUser;
    
    public static final long serialVersionUID = 0x222L;

    public ExcepcionBPComunicaciones() {
    }

    public ExcepcionBPComunicaciones(String memensajeErrorAdmin, String memensajeErrorUser) {
        this.memensajeErrorAdmin = memensajeErrorAdmin;
        this.memensajeErrorUser = memensajeErrorUser;
    }

    public String getMemensajeErrorAdmin() {
        return memensajeErrorAdmin;
    }

    public void setMemensajeErrorAdmin(String memensajeErrorAdmin) {
        this.memensajeErrorAdmin = memensajeErrorAdmin;
    }

    public String getMemensajeErrorUser() {
        return memensajeErrorUser;
    }

    public void setMemensajeErrorUser(String memensajeErrorUser) {
        this.memensajeErrorUser = memensajeErrorUser;
    }

    @Override
    public String toString() {
        return "ExcepcionBPComunicaciones{" + "memensajeErrorAdmin=" + memensajeErrorAdmin + ", memensajeErrorUser=" + memensajeErrorUser + '}';
    }
    
    
    
}
