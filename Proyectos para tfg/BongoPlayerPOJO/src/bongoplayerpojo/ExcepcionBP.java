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
public class ExcepcionBP extends Exception implements Serializable{
    
    private String mensajeErrorAdmin;
    private Integer codigoError;
    private String sentenciaSQL;
    private String mensajeErrorUser;
    
    static final long serialVersionUID= 0x2L;

    public ExcepcionBP() {
    }

    public ExcepcionBP(String mensajeErrorAdmin, Integer codigoError, String sentenciaSQL, String mensajeErrorUser) {
        this.mensajeErrorAdmin = mensajeErrorAdmin;
        this.codigoError = codigoError;
        this.sentenciaSQL = sentenciaSQL;
        this.mensajeErrorUser = mensajeErrorUser;
    }

    public ExcepcionBP(String mensajeErrorAdmin, String mensajeErrorUser) {
        this.mensajeErrorAdmin = mensajeErrorAdmin;
        this.mensajeErrorUser = mensajeErrorUser;
    }

    public String getMensajeErrorAdmin() {
        return mensajeErrorAdmin;
    }

    public void setMensajeErrorAdmin(String mensajeErrorAdmin) {
        this.mensajeErrorAdmin = mensajeErrorAdmin;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public String getMensajeErrorUser() {
        return mensajeErrorUser;
    }

    public void setMensajeErrorUser(String mensajeErrorUser) {
        this.mensajeErrorUser = mensajeErrorUser;
    }

    @Override
    public String toString() {
        return "ExcepcionBP{" + "mensajeErrorAdmin=" + mensajeErrorAdmin + ", codigoError=" + codigoError + ", sentenciaSQL=" + sentenciaSQL + ", mensajeErrorUser=" + mensajeErrorUser + '}';
    }
            
    
}
