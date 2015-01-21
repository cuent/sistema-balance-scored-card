/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mivkys
 */
@Entity
@Table(name = "estrategia_global", catalog = "gerenciales", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstrategiaGlobal.findAll", query = "SELECT e FROM EstrategiaGlobal e"),
    @NamedQuery(name = "EstrategiaGlobal.findByIdEstrategiaGlobal", query = "SELECT e FROM EstrategiaGlobal e WHERE e.idEstrategiaGlobal = :idEstrategiaGlobal"),
    @NamedQuery(name = "EstrategiaGlobal.findByNombre", query = "SELECT e FROM EstrategiaGlobal e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstrategiaGlobal.findByFechaModificacion", query = "SELECT e FROM EstrategiaGlobal e WHERE e.fechaModificacion = :fechaModificacion")})
public class EstrategiaGlobal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTRATEGIA_GLOBAL", nullable = false)
    private Integer idEstrategiaGlobal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estrategiaGlobal")
    private Set<ObjetivoEstrategico> objetivoEstrategicoSet;

    public EstrategiaGlobal() {
    }

    public EstrategiaGlobal(Integer idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public EstrategiaGlobal(Integer idEstrategiaGlobal, String nombre, Date fechaModificacion) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
        this.nombre = nombre;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdEstrategiaGlobal() {
        return idEstrategiaGlobal;
    }

    public void setIdEstrategiaGlobal(Integer idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public Set<ObjetivoEstrategico> getObjetivoEstrategicoSet() {
        return objetivoEstrategicoSet;
    }

    public void setObjetivoEstrategicoSet(Set<ObjetivoEstrategico> objetivoEstrategicoSet) {
        this.objetivoEstrategicoSet = objetivoEstrategicoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstrategiaGlobal != null ? idEstrategiaGlobal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrategiaGlobal)) {
            return false;
        }
        EstrategiaGlobal other = (EstrategiaGlobal) object;
        if ((this.idEstrategiaGlobal == null && other.idEstrategiaGlobal != null) || (this.idEstrategiaGlobal != null && !this.idEstrategiaGlobal.equals(other.idEstrategiaGlobal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.EstrategiaGlobal[ idEstrategiaGlobal=" + idEstrategiaGlobal + " ]";
    }
    
}
