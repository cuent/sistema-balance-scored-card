/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mivkys
 */
@Entity
@Table(name = "relacion_objetivo", catalog = "gerenciales", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionObjetivo.findAll", query = "SELECT r FROM RelacionObjetivo r"),
    @NamedQuery(name = "RelacionObjetivo.findByIdRelacionObjetivo", query = "SELECT r FROM RelacionObjetivo r WHERE r.idRelacionObjetivo = :idRelacionObjetivo")})
public class RelacionObjetivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RELACION_OBJETIVO", nullable = false)
    private Integer idRelacionObjetivo;
    @JoinColumn(name = "CONCEPTUALIZAR1", referencedColumnName = "ID_CONCEPTUALIZAR")
    @ManyToOne
    private Conceptualizar conceptualizar1;
    @JoinColumn(name = "CONCEPTUALIZAR2", referencedColumnName = "ID_CONCEPTUALIZAR")
    @ManyToOne
    private Conceptualizar conceptualizar2;

    public RelacionObjetivo() {
    }

    public RelacionObjetivo(Integer idRelacionObjetivo) {
        this.idRelacionObjetivo = idRelacionObjetivo;
    }

    public Integer getIdRelacionObjetivo() {
        return idRelacionObjetivo;
    }

    public void setIdRelacionObjetivo(Integer idRelacionObjetivo) {
        this.idRelacionObjetivo = idRelacionObjetivo;
    }

    public Conceptualizar getConceptualizar1() {
        return conceptualizar1;
    }

    public void setConceptualizar1(Conceptualizar conceptualizar1) {
        this.conceptualizar1 = conceptualizar1;
    }

    public Conceptualizar getConceptualizar2() {
        return conceptualizar2;
    }

    public void setConceptualizar2(Conceptualizar conceptualizar2) {
        this.conceptualizar2 = conceptualizar2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacionObjetivo != null ? idRelacionObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionObjetivo)) {
            return false;
        }
        RelacionObjetivo other = (RelacionObjetivo) object;
        if ((this.idRelacionObjetivo == null && other.idRelacionObjetivo != null) || (this.idRelacionObjetivo != null && !this.idRelacionObjetivo.equals(other.idRelacionObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.RelacionObjetivo[ idRelacionObjetivo=" + idRelacionObjetivo + " ]";
    }
    
}
