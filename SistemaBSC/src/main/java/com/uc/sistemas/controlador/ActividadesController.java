package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Actividades;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("actividadesController")
@SessionScoped
public class ActividadesController extends AbstractController<Actividades> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ActividadesFacade ejbFacade;
public ActividadesController() {
        super(Actividades.class);
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
