package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Perspectiva;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("perspectivaController")
@SessionScoped
public class PerspectivaController extends AbstractController<Perspectiva> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.PerspectivaFacade ejbFacade;

    public PerspectivaController() {
        super(Perspectiva.class);
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
