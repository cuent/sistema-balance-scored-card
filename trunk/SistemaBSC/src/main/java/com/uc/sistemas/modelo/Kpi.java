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
    @NamedQuery(name = "Kpi.findAll", query = "SELECT k FROM Kpi k"),
    @NamedQuery(name = "Kpi.findByIdKpi", query = "SELECT k FROM Kpi k WHERE k.idKpi = :idKpi"),
    @NamedQuery(name = "Kpi.findByDescripcion", query = "SELECT k FROM Kpi k WHERE k.descripcion = :descripcion"),
    @NamedQuery(name = "Kpi.findByKpi", query = "SELECT k FROM Kpi k WHERE k.kpi = :kpi"),
    @NamedQuery(name = "Kpi.findByFechaModificacion", query = "SELECT k FROM Kpi k WHERE k.fechaModificacion = :fechaModificacion")})
public class Kpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_KPI", nullable = false)
    private Integer idKpi;
    @Size(max = 256)
    @Column(length = 256)
    private String descripcion;
    @Size(max = 128)
    @Column(length = 128)
    private String kpi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;

    public Kpi() {
    }

    public Kpi(Integer idKpi) {
        this.idKpi = idKpi;
    }

    public Kpi(Integer idKpi, Date fechaModificacion) {
        this.idKpi = idKpi;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdKpi() {
        return idKpi;
    }

    public void setIdKpi(Integer idKpi) {
        this.idKpi = idKpi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public ObjetivoEstrategico getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(ObjetivoEstrategico idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKpi != null ? idKpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kpi)) {
            return false;
        }
        Kpi other = (Kpi) object;
        if ((this.idKpi == null && other.idKpi != null) || (this.idKpi != null && !this.idKpi.equals(other.idKpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.Kpi[ idKpi=" + idKpi + " ]";
    }
    
}
