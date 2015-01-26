/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.controlador.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mivkys
 */
public class Historial implements Serializable{
    private Date fechaModificacion;
    private String clase;
    private Integer id;
    public Historial() {
    }

    public Historial(Date fechaModificacion, String clase, Integer id) {
        this.fechaModificacion = fechaModificacion;
        this.clase = clase;
        this.id = id;
    }

    

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
}
