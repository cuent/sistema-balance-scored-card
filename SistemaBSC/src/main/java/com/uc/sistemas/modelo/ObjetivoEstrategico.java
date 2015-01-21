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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "objetivo_estrategico", catalog = "gerenciales", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoEstrategico.findAll", query = "SELECT o FROM ObjetivoEstrategico o"),
    @NamedQuery(name = "ObjetivoEstrategico.findByIdObjetivoEstrategico", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "ObjetivoEstrategico.findByDescripcion", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "ObjetivoEstrategico.findByFechaAlcanze", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.fechaAlcanze = :fechaAlcanze"),
    @NamedQuery(name = "ObjetivoEstrategico.findByFechaModificacion", query = "SELECT o FROM ObjetivoEstrategico o WHERE o.fechaModificacion = :fechaModificacion")})
public class ObjetivoEstrategico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    private Integer idObjetivoEstrategico;
    @Size(max = 256)
    @Column(length = 256)
    private String descripcion;
    @Column(name = "FECHA_ALCANZE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlcanze;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private Set<Indicador> indicadorSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoEstrategico")
    private Set<Responsable> responsableSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private Set<Kpi> kpiSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivo")
    private Set<Meta> metaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private Set<Conceptualizar> conceptualizarSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private Set<Actividades> actividadesSet;
    @JoinColumn(name = "ESTRATEGIA_GLOBAL", referencedColumnName = "ID_ESTRATEGIA_GLOBAL", nullable = false)
    @ManyToOne(optional = false)
    private EstrategiaGlobal estrategiaGlobal;
    @JoinColumn(name = "ID_PERSPECTIVA", referencedColumnName = "ID_PERSPECTIVA", nullable = false)
    @ManyToOne(optional = false)
    private Perspectiva idPerspectiva;

    public ObjetivoEstrategico() {
    }

    public ObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public ObjetivoEstrategico(Integer idObjetivoEstrategico, Date fechaModificacion) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlcanze() {
        return fechaAlcanze;
    }

    public void setFechaAlcanze(Date fechaAlcanze) {
        this.fechaAlcanze = fechaAlcanze;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public Set<Indicador> getIndicadorSet() {
        return indicadorSet;
    }

    public void setIndicadorSet(Set<Indicador> indicadorSet) {
        this.indicadorSet = indicadorSet;
    }

    @XmlTransient
    public Set<Responsable> getResponsableSet() {
        return responsableSet;
    }

    public void setResponsableSet(Set<Responsable> responsableSet) {
        this.responsableSet = responsableSet;
    }

    @XmlTransient
    public Set<Kpi> getKpiSet() {
        return kpiSet;
    }

    public void setKpiSet(Set<Kpi> kpiSet) {
        this.kpiSet = kpiSet;
    }

    @XmlTransient
    public Set<Meta> getMetaSet() {
        return metaSet;
    }

    public void setMetaSet(Set<Meta> metaSet) {
        this.metaSet = metaSet;
    }

    @XmlTransient
    public Set<Conceptualizar> getConceptualizarSet() {
        return conceptualizarSet;
    }

    public void setConceptualizarSet(Set<Conceptualizar> conceptualizarSet) {
        this.conceptualizarSet = conceptualizarSet;
    }

    @XmlTransient
    public Set<Actividades> getActividadesSet() {
        return actividadesSet;
    }

    public void setActividadesSet(Set<Actividades> actividadesSet) {
        this.actividadesSet = actividadesSet;
    }

    public EstrategiaGlobal getEstrategiaGlobal() {
        return estrategiaGlobal;
    }

    public void setEstrategiaGlobal(EstrategiaGlobal estrategiaGlobal) {
        this.estrategiaGlobal = estrategiaGlobal;
    }

    public Perspectiva getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Perspectiva idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivoEstrategico != null ? idObjetivoEstrategico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoEstrategico)) {
            return false;
        }
        ObjetivoEstrategico other = (ObjetivoEstrategico) object;
        if ((this.idObjetivoEstrategico == null && other.idObjetivoEstrategico != null) || (this.idObjetivoEstrategico != null && !this.idObjetivoEstrategico.equals(other.idObjetivoEstrategico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.ObjetivoEstrategico[ idObjetivoEstrategico=" + idObjetivoEstrategico + " ]";
    }
    
}
