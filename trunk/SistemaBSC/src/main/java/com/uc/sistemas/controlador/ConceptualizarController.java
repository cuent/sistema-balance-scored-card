package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Conceptualizar;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("conceptualizarController")
@SessionScoped
public class ConceptualizarController extends AbstractController<Conceptualizar> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ConceptualizarFacade ejbFacade;

    public ConceptualizarController() {
        super(Conceptualizar.class);
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
