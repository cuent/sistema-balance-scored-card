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
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findByIdIndicador", query = "SELECT i FROM Indicador i WHERE i.idIndicador = :idIndicador"),
    @NamedQuery(name = "Indicador.findByDescripcion", query = "SELECT i FROM Indicador i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Indicador.findByLimiteInferior", query = "SELECT i FROM Indicador i WHERE i.limiteInferior = :limiteInferior"),
    @NamedQuery(name = "Indicador.findByLimiteSuperior", query = "SELECT i FROM Indicador i WHERE i.limiteSuperior = :limiteSuperior"),
    @NamedQuery(name = "Indicador.findByUnidades", query = "SELECT i FROM Indicador i WHERE i.unidades = :unidades"),
    @NamedQuery(name = "Indicador.findByFormula", query = "SELECT i FROM Indicador i WHERE i.formula = :formula"),
    @NamedQuery(name = "Indicador.findByValorActual", query = "SELECT i FROM Indicador i WHERE i.valorActual = :valorActual"),
    @NamedQuery(name = "Indicador.findByFechaModificacion", query = "SELECT i FROM Indicador i WHERE i.fechaModificacion = :fechaModificacion"),
    
    @NamedQuery(name = "Indicador.FechaModificacion", query = "SELECT o.fechaModificacion FROM Indicador o"),
    @NamedQuery(name = "Indicador.findByIdObjetivoEstrategico", query = "SELECT k FROM Indicador k WHERE k.idObjetivoEstrategico.idObjetivoEstrategico = :idObjetivoEstrategico")})
public class Indicador implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FechaModificacion ="Indicador.FechaModificacion";
    public static final String findByIdObjetivoEstrategico = "Indicador.findByIdObjetivoEstrategico";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INDICADOR", nullable = false)
    private Integer idIndicador;
    @Size(max = 128)
    @Column(length = 128)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMITE_INFERIOR", nullable = false)
    private int limiteInferior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMITE_SUPERIOR", nullable = false)
    private int limiteSuperior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String unidades;
    @Size(max = 256)
    @Column(length = 256)
    private String formula;
    @Column(name = "VALOR_ACTUAL")
    private Integer valorActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_OBJETIVO_ESTRATEGICO", referencedColumnName = "ID_OBJETIVO_ESTRATEGICO", nullable = false)
    @ManyToOne(optional = false)
    private ObjetivoEstrategico idObjetivoEstrategico;

    public Indicador() {
    }

    public Indicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Indicador(Integer idIndicador, int limiteInferior, int limiteSuperior, String unidades, Date fechaModificacion) {
        this.idIndicador = idIndicador;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.unidades = unidades;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getValorActual() {
        return valorActual;
    }

    public void setValorActual(Integer valorActual) {
        this.valorActual = valorActual;
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
        hash += (idIndicador != null ? idIndicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.idIndicador == null && other.idIndicador != null) || (this.idIndicador != null && !this.idIndicador.equals(other.idIndicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.Indicador[ idIndicador=" + idIndicador + " ]";
    }
    
}
