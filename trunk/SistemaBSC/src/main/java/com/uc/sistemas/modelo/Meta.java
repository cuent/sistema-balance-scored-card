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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mivkys
 */
@Entity
@Table(catalog = "gerenciales", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m"),
    @NamedQuery(name = "Meta.findByIdMeta", query = "SELECT m FROM Meta m WHERE m.idMeta = :idMeta"),
    @NamedQuery(name = "Meta.findByDescripcion", query = "SELECT m FROM Meta m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Meta.findByFechaModificacion", query = "SELECT m FROM Meta m WHERE m.fechaModificacion = :fechaModificacion"),
    
    @NamedQuery(name = "Meta.FechaModificacion", query = "SELECT o.fechaModificacion FROM Meta o"),
    @NamedQuery(name = "Meta.findByIdObjetivoEstrategico", query = "SELECT k FROM Meta k WHERE k.idObjetivo.idObjetivoEstrategico = :idObjetivoEstrategico")})
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FechaModificacion ="Meta.FechaModificacion";
    public static final String findByIdObjetivoEstrategico = "Meta.findByIdObjetivoEstrategico";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_META", nullable = false)
    private Integer idMeta;
    @Size(max = 256)
    @Column(length = 256)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_OBJETIVO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivo;

    public Meta() {
    }

    public Meta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Meta(Integer idMeta, Date fechaModificacion) {
        this.idMeta = idMeta;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public ObjetivoEstrategico getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(ObjetivoEstrategico idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeta != null ? idMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.idMeta == null && other.idMeta != null) || (this.idMeta != null && !this.idMeta.equals(other.idMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.Meta[ idMeta=" + idMeta + " ]";
    }
    
}
