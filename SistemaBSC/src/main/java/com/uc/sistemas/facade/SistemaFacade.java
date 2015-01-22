/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.facade;

import com.uc.sistemas.modelo.Sistema;
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
public class SistemaFacade extends AbstractFacade<Sistema> {
    @PersistenceContext(unitName = "com.uc.sistemas_SistemaBSC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemaFacade() {
        super(Sistema.class);
    }
    public Sistema getSistema(Integer idUsuario) {
        Query query = this.em.createNamedQuery(Sistema.findByIdUsuario);
        query.setParameter("idUsuario", idUsuario);
        try{
            return (Sistema)query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
