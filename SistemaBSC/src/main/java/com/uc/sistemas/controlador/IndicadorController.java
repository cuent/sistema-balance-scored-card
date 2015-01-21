package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Indicador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("indicadorController")
@SessionScoped
public class IndicadorController extends AbstractController<Indicador> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.IndicadorFacade ejbFacade;

    public IndicadorController() {
        super(Indicador.class);
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
