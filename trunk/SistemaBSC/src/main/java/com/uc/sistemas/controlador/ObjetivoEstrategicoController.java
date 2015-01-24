package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.ObjetivoEstrategico;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("objetivoEstrategicoController")
@SessionScoped
public class ObjetivoEstrategicoController extends AbstractController<ObjetivoEstrategico> implements Serializable {

    @EJB
    private com.uc.sistemas.facade.ObjetivoEstrategicoFacade ejbFacade;
    
    public ObjetivoEstrategicoController() {
        super(ObjetivoEstrategico.class);
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
