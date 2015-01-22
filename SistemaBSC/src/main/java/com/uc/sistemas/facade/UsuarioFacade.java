/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.facade;

import com.uc.sistemas.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mivkys
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "com.uc.sistemas_SistemaBSC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario getUsuario(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsernameEmail);
        query.setParameter("username", username);
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public Usuario getUsuarioUsername(String username) {
        Query query = this.em.createNamedQuery(Usuario.findByUsername);
        query.setParameter("username", username);
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    public Usuario getUsuarioEmail(String email) {
        Query query = this.em.createNamedQuery(Usuario.findByEmail);
        query.setParameter("email", email);
        try{
            return (Usuario)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
