package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Meta;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("metaController")
@SessionScoped
public class MetaController extends AbstractController<Meta> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.MetaFacade ejbFacade;

    public MetaController() {
        super(Meta.class);
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
