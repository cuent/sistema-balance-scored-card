/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mivkys
 */
@Entity
@Table(catalog = "gerenciales", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsable.findAll", query = "SELECT r FROM Responsable r"),
    @NamedQuery(name = "Responsable.findByIdResponsable", query = "SELECT r FROM Responsable r WHERE r.responsablePK.idResponsable = :idResponsable"),
    @NamedQuery(name = "Responsable.findByIdObjetivo", query = "SELECT r FROM Responsable r WHERE r.responsablePK.idObjetivo = :idObjetivo"),
    @NamedQuery(name = "Responsable.findByIdUsuario", query = "SELECT r FROM Responsable r WHERE r.responsablePK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Responsable.findByFechaModificacion", query = "SELECT r FROM Responsable r WHERE r.fechaModificacion = :fechaModificacion")})
public class Responsable implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsablePK responsablePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_OBJETIVO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico objetivoEstrategico;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Responsable() {
    }

    public Responsable(ResponsablePK responsablePK) {
        this.responsablePK = responsablePK;
    }

    public Responsable(ResponsablePK responsablePK, Date fechaModificacion) {
        this.responsablePK = responsablePK;
        this.fechaModificacion = fechaModificacion;
    }

    public Responsable(int idResponsable, int idObjetivo, int idUsuario) {
        this.responsablePK = new ResponsablePK(idResponsable, idObjetivo, idUsuario);
    }

    public ResponsablePK getResponsablePK() {
        return responsablePK;
    }

    public void setResponsablePK(ResponsablePK responsablePK) {
        this.responsablePK = responsablePK;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public ObjetivoEstrategico getObjetivoEstrategico() {
        return objetivoEstrategico;
    }

    public void setObjetivoEstrategico(ObjetivoEstrategico objetivoEstrategico) {
        this.objetivoEstrategico = objetivoEstrategico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsablePK != null ? responsablePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsable)) {
            return false;
        }
        Responsable other = (Responsable) object;
        if ((this.responsablePK == null && other.responsablePK != null) || (this.responsablePK != null && !this.responsablePK.equals(other.responsablePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.Responsable[ responsablePK=" + responsablePK + " ]";
    }
    
}
