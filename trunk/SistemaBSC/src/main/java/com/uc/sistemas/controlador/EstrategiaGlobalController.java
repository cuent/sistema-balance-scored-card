package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.EstrategiaGlobal;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.Date;

@Named("estrategiaGlobalController")
@SessionScoped
public class EstrategiaGlobalController extends AbstractController<EstrategiaGlobal> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.EstrategiaGlobalFacade ejbFacade;

    public EstrategiaGlobalController() {
        super(EstrategiaGlobal.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    public void create() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.create(); //To change body of generated methods, choose Tools | Templates.
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
