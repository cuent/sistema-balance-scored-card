/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mivkys
 */
@Embeddable
public class ResponsablePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_RESPONSABLE", nullable = false)
    private int idResponsable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OBJETIVO", nullable = false)
    private int idObjetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;

    public ResponsablePK() {
    }

    public ResponsablePK(int idResponsable, int idObjetivo, int idUsuario) {
        this.idResponsable = idResponsable;
        this.idObjetivo = idObjetivo;
        this.idUsuario = idUsuario;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public int getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(int idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idResponsable;
        hash += (int) idObjetivo;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsablePK)) {
            return false;
        }
        ResponsablePK other = (ResponsablePK) object;
        if (this.idResponsable != other.idResponsable) {
            return false;
        }
        if (this.idObjetivo != other.idObjetivo) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.ResponsablePK[ idResponsable=" + idResponsable + ", idObjetivo=" + idObjetivo + ", idUsuario=" + idUsuario + " ]";
    }
    
}
