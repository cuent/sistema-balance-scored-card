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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mivkys
 */
@Entity
@Table(catalog = "gerenciales", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario"),
    @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion"),
    
    @NamedQuery(name = "Usuario.findByUsernameEmail", query = "SELECT u FROM Usuario u WHERE (u.username = :username OR u.email = :username)")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUsernameEmail ="Usuario.findByUsernameEmail";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String nombres;
    @Size(max = 32)
    @Column(length = 32)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String contrasena;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo Invalido")
    @Size(max = 64)
    @Column(length = 64)
    private String email;
    @Column(name = "TIPO_USUARIO")
    private Character tipoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Responsable> responsableSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<ResponsableActividad> responsableActividadSet;
    @OneToMany(mappedBy = "idAdministrador")
    private Set<Usuario> usuarioSet;
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idAdministrador;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String username, String nombres, String contrasena, Date fechaModificacion) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombres = nombres;
        this.contrasena = contrasena;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Character tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public Set<Responsable> getResponsableSet() {
        return responsableSet;
    }

    public void setResponsableSet(Set<Responsable> responsableSet) {
        this.responsableSet = responsableSet;
    }

    @XmlTransient
    public Set<ResponsableActividad> getResponsableActividadSet() {
        return responsableActividadSet;
    }

    public void setResponsableActividadSet(Set<ResponsableActividad> responsableActividadSet) {
        this.responsableActividadSet = responsableActividadSet;
    }

    @XmlTransient
    public Set<Usuario> getUsuarioSet() {
        return usuarioSet;
    }

    public void setUsuarioSet(Set<Usuario> usuarioSet) {
        this.usuarioSet = usuarioSet;
    }

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uc.sistemas.modelo.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
