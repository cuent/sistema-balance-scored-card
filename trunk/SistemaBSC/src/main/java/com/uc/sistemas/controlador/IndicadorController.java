package com.uc.sistemas.controlador;

import com.uc.sistemas.modelo.Indicador;

import java.io.Serializable;
import java.util.Date;
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
    public void create() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.create(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        Date d = new Date();
        this.getSelected().setFechaModificacion(d);
        super.update(); //To change body of generated methods, choose Tools | Templates.
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
