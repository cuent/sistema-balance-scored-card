/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uc.sistemas.facade;

import com.uc.sistemas.controlador.util.Historial;
import com.uc.sistemas.modelo.EstrategiaGlobal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mivkys
 */
@Stateless
public class EstrategiaGlobalFacade extends AbstractFacade<EstrategiaGlobal> {

    @PersistenceContext(unitName = "com.uc.sistemas_SistemaBSC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstrategiaGlobalFacade() {
        super(EstrategiaGlobal.class);
    }

    public List<Historial> getItemsFechaModificacion() {
        List<EstrategiaGlobal> objetos = new ArrayList<>();
        objetos.addAll(this.findAll());
        List<Historial> historial = new ArrayList<>();
        for (EstrategiaGlobal objeto : objetos) {
            historial.add(new Historial(objeto.getFechaModificacion(), EstrategiaGlobal.class.getSimpleName(),objeto.getIdEstrategiaGlobal()));
        }
        return historial;
    }
}
