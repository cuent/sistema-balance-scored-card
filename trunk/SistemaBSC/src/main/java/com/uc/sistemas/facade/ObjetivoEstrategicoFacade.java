/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.facade;

import com.uc.sistemas.modelo.ObjetivoEstrategico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mivkys
 */
@Stateless
public class ObjetivoEstrategicoFacade extends AbstractFacade<ObjetivoEstrategico> {
    @PersistenceContext(unitName = "com.uc.sistemas_SistemaBSC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoEstrategicoFacade() {
        super(ObjetivoEstrategico.class);
    }
    
}
