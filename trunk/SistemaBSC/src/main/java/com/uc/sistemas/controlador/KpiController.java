package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Kpi;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("kpiController")
@SessionScoped
public class KpiController extends AbstractController<Kpi> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.KpiFacade ejbFacade;
    public KpiController() {
        super(Kpi.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    @Override
    protected void postCreate() {
    }

    @Override
    protected void postUpdate() {
    }

    @Override
    protected void postDestroy() {
    }
}
